/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
/**
 *
 * @author medmo
 */
import db.MyConnection;
import models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ServiceBasket implements InterfaceServiceBasket{
Statement ste=null;
Connection conn = MyConnection.getInstance().getConnection();

@Override
public boolean ajouter(int idClient, int idArticle) {
    boolean added = false;
    try {
        String selectQuery = "SELECT * FROM basket WHERE id_client=? and id_article=?";
        PreparedStatement selectPs = conn.prepareStatement(selectQuery);
        selectPs.setInt(2, idArticle);
        selectPs.setInt(1, idClient);
        ResultSet resultSet = selectPs.executeQuery();
        
        if (!resultSet.next()) {
            // idArticle does not exist in the database, add a new entry
            String insertQuery = "INSERT INTO `basket` (`id_client`, `id_article`) VALUES (?,?)";
            PreparedStatement insertPs = conn.prepareStatement(insertQuery);
            insertPs.setInt(1, idClient);
            insertPs.setInt(2, idArticle);
            insertPs.executeUpdate();
            added = true;
        } else {
            // idArticle already exists in the database
            System.out.println("idArticle already exists in the database");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return added;
}

     @Override
    public void supprimerArticle(int idClient, int idArticle) {
        try {
            String req = "DELETE FROM `basket` WHERE id_client = ? and id_article = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, idClient);
            st.setInt(2, idArticle);
            st.executeUpdate();
            System.out.println("Basket item deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void vider(int idClient) {
       try {
            String req = "DELETE FROM `basket` WHERE id_client = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, idClient);
            st.executeUpdate();
            System.out.println("Basket Empty !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Basket get(int idClient) {
        Basket bask= new Basket();
        ServiceArticle sa = new ServiceArticle();
          try {
        String req = "SELECT * FROM `basket` WHERE id_client = ?";
        PreparedStatement pste=conn.prepareStatement(req);
        pste.setInt(1, idClient);
        
        ResultSet result = pste.executeQuery();
        while(result.next()){
           Article resultArticle = sa.get(result.getInt(2));
      bask.addArticle(resultArticle);
     
        }
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
          bask.setRefClient(idClient);
         double totalCost = bask.getArticles().stream().mapToDouble(x->x.getPrix()).sum();
         bask.setTotalCost(totalCost);
         
    return bask;
    }

   
    
  
}

