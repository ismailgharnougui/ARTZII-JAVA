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

public class ServiceArticle implements InterfaceServiceArticle{
Statement ste =null;

Connection conn = MyConnection.getInstance().getConnection();
 
    @Override
    public Article get(int id) {
          try {
        String req = "SELECT * FROM `articles` WHERE refA = ?";
        PreparedStatement pste=conn.prepareStatement(req);
        pste.setInt(1, id);
        
        ResultSet result = pste.executeQuery();
       result.next();
           Article resultArticle = new Article(result.getInt(1),result.getInt(2),result.getString(3),result.getString(4), result.getFloat(5), result.getString(7));
        return resultArticle;
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
    return null;
    }

    @Override
    public List<Article> getArticles(int id) {
  List<Article> articles = new ArrayList<>();
        try {
        String req = "SELECT * FROM articles where idArtiste = "+id;
        ste=conn.createStatement();
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) { 
            Article resultArticle = new Article(result.getInt(1),result.getInt(2), result.getString(3), result.getString(4), result.getFloat(5),result.getString(7));

            articles.add(resultArticle);
        }
        System.out.println(articles);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return articles;    
    }
    


    @Override
    public List<Article> afficherArticles() {
    List<Article> articles = new ArrayList<>();
        try {
        String req = "SELECT * FROM articles";
        ste=conn.createStatement();
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) { 
            Article resultArticle = new Article(result.getInt(1),result.getInt(2), result.getString(3), result.getString(4), result.getFloat(5),result.getString(7));

            articles.add(resultArticle);
        }
        System.out.println(articles);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return articles;    
    }

    @Override
    public void supprimerArticle(int idArtiste, int idArticle) {
        try {
            String req = "DELETE FROM `articles` WHERE idArtiste = ? and refA = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, idArtiste);
            st.setInt(2, idArticle);
            st.executeUpdate();
            System.out.println("Article deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void ajouter(Article article) {
         try {
            String req = "INSERT INTO `articles` (idArtiste , nomA , dimensionA , prixA , image_url) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, article.getIdArtiste());
            ps.setString(2, article.getNom());
            ps.setString(3, article.getDimension());
            ps.setDouble(4, article.getPrix());
            ps.setString(5, article.getImageUrl());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
     @Override
     public void modifierArticle(Article article) {
        try {
            
            String req = "UPDATE articles  SET nomA = ?, dimensionA = ?, prixA = ?, image_url = ? WHERE refA = ?";

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, article.getNom());
            ps.setString(2, article.getDimension());
            ps.setFloat(3, (float)article.getPrix());
            ps.setString(4, article.getImageUrl());
            ps.setInt(5, article.getRef());

            ps.executeUpdate();
            System.out.println("Article updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    
}
