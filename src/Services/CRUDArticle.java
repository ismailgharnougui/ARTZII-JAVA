/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IArticle;
import Utils.MyConnection;
import Entites.Article;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CRUDArticle implements IArticle{
Statement ste;
Connection conn = MyConnection.getInstance().getConnection();
    
    public void ajouterArticle(Article A) {
    try {
        ste = conn.createStatement();
        String req = "Insert into article values(0,'"+A.getNom()+"','"+A.getDimension()+"','"+A.getPrix()+"','" +A.getDate()+"','"+A.getCatId()+"')";
        ste.executeUpdate(req);
        System.out.println("Article ajouté");
    } catch (SQLException ex) {
            System.out.println("Article non ajouté!!!!");    }
    }

     
    
    public void modifierArticle(Article A) {
        try {
            String req = "UPDATE `article` SET `nomA` = '" + A.getNom() + "', `dimensionA` = '" + A.getDimension() +"', `prixA` = '"+A.getPrix()+"', `CatId`= '"+A.getCatId()+"' WHERE `article`.`refA` = " + A.getRef()  ;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Article updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  
    public void supprimerArticle(int id) {
        try {
            String req = "DELETE FROM `article` WHERE refA = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Article deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
@Override
    public List<Article> afficherArticle() {
      //  obList.clear();

       List<Article> list = new ArrayList<>();
        try {
            String req = "Select * from article";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
                      System.out.println("hiii"); // mathbt endik data fi table?? afifchage service temchich 5ater

             Article A = new Article();
             A.setNom(RS.getString("nomA"));
             A.setRef(RS.getInt("refA"));
             A.setDimension(RS.getString("dimensionA"));
             A.setPrix(RS.getInt("prixA"));
             list.add(A);
             //ba3thlk al msgnr msg helo // isamel wink hel message w jib code hoto hne
             
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

   
    public void ajouterArticle2(Article A) {
        try {
            String req = "INSERT INTO `article` (`nomA`, `dimensionA`, `prixA` ,`CatId`,`date`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(3,A.getPrix());
            ps.setString(2, A.getDimension());
            ps.setString(1, A.getNom());
            ps.setInt(4,A.getCatId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

  
  }
      public void ajouterArticle3(Article A) {
        try {
            String req = "INSERT INTO `article` (`nomA`, `dimensionA`, `prixA` ,`CatId`) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(3,A.getPrix());
            ps.setString(2, A.getDimension());
            ps.setString(1, A.getNom());
            ps.setInt(4,A.getCatId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

  
  }
    
    
    
      public boolean getArticle(Article A) {
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("SELECT * FROM article WHERE CatId = ?");
            ps.setInt(1,A.getCatId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //System.out.println(rs.getString("ServLib"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
 public List <Article>getArticleByCategorie() {
        String sql ="select * from Article A "
                + "JOIN Categorie C ON C.CatId=A.CatId";
        List<Article> listeService = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int refA = result.getInt(1);
                String nomA = result.getString(2);
                String dimensionA = result.getString(3);
                String image = result.getString(4);
                int prixA = result.getInt(5);
                Date date = result.getDate(6);
                int CatId= result.getInt(7);

                Article A = new Article(refA,nomA,dimensionA,image ,prixA,date,CatId);
                listeService.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listeService;
    }
}
