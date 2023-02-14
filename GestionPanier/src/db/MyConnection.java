/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
public class MyConnection {
  private  Connection  conn;
   String url = "jdbc:mysql://localhost:3306/mydb";
   String user = "root";
   String pwd = "1963";
   private static MyConnection instance;
    private MyConnection() {
        
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie!!!");
        } catch (SQLException ex) {
            System.out.println("probleme de Connexion");    
        }}

  public static MyConnection getInstance(){
        if (MyConnection.instance == null) {
            MyConnection.instance = new MyConnection();
        }
        return MyConnection.instance;
        
    }
  public static Connection getConnection() {
        return MyConnection.getInstance().conn;
    }
    }
    
    
    
    
    
    

