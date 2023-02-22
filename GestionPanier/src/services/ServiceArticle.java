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
    public Article get(int id) {
          try {
        String req = "SELECT * FROM `article` WHERE refA = ?";
        PreparedStatement pste=conn.prepareStatement(req);
        pste.setInt(1, id);
        
        ResultSet result = pste.executeQuery();
       result.next();
           Article resultArticle = new Article(result.getInt(1),result.getString(2),result.getString(3), result.getFloat(4), result.getString(6));
        return resultArticle;
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
    return null;
    }
}
