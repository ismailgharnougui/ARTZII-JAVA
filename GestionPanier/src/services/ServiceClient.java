/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Article;
import models.Client;

/**
 *
 * @author medmo
 */
public class ServiceClient implements InterfaceServiceClient{
Statement ste =null;
Connection conn = MyConnection.getInstance().getConnection();

    @Override
    public Client get(int id) {

    
          try {
        String req = "SELECT * FROM `users` WHERE id = ?";
        PreparedStatement pste=conn.prepareStatement(req);
        pste.setInt(1, id);
        
        ResultSet result = pste.executeQuery();
       result.next();
           Client resultClient = new Client(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
        return resultClient;
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
    return null;
    }
    
        }

