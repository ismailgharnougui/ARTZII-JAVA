/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.MyConnection;
import models.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceArticle implements InterfaceServiceArticle{
Statement ste =null;

Connection conn = MyConnection.getInstance().getConnection();
 
    @Override
    public void ajouterArticle(Article a) {
      
        try {
            String req = "INSERT INTO `articles` (`dimension`, `prix`) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, a.getDimension());
            ps.setFloat(2, a.getPrix());
         
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerArticle(int id) {
 try {
            String req = "DELETE FROM `articles` WHERE ref = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Article deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public List<Article> afficherArticles() {
    try {
        ste= conn.createStatement();
    } catch (SQLException ex) {
        Logger.getLogger(ServiceArticle.class.getName()).log(Level.SEVERE, null, ex);
    }
    List<Article> articless = new ArrayList<Article>();
        try {
        String req = "SELECT * FROM `articles`";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Article resultArticle = new Article(result.getInt(1), result.getString(2), result.getFloat(3));
            articless.add(resultArticle);
        }
        System.out.println(articless);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return articless;
    }

    @Override
    public Article get(int id) {
          try {
        String req = "SELECT * FROM `articles` WHERE ref = ?";
        PreparedStatement pste=conn.prepareStatement(req);
        pste.setInt(1, id);
        
        ResultSet result = pste.executeQuery();
       result.next();
           Article resultArticle = new Article(result.getInt(1), result.getString(2), result.getFloat(3));
        return resultArticle;
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
    return null;
    }
    
    
}
