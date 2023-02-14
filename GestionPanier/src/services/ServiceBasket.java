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
import java.util.ArrayList;
import java.util.List;


public class ServiceBasket implements InterfaceServiceBasket{
Statement ste;
Connection conn = MyConnection.getInstance().getConnection();


    @Override
    public void ajouter(int idClient, int idArticle) {
         try {
            String req = "INSERT INTO `basket` (`id_client`, `id_product`) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, idClient);
            ps.setInt(2, idArticle);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public void supprimerArticle(int idArticle) {
        try {
            String req = "DELETE FROM `basket` WHERE id_product = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, idArticle);
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
            st.executeUpdate(req);
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

