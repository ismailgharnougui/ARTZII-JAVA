/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author hp
 */



public class DBConnection {
   private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String db = "test"; // badelha b esm l base de donnée globale mte3na w tansech tbadel e table commandes w table user 
    private final String name = "root";
    private final String password = "";
    
    private static Connection instance;
    
    private DBConnection() {
        try{
            Class.forName(driver);
            instance = DriverManager.getConnection(url.concat(db), name, password);
            System.out.println("connection with database established");
        }catch(ClassNotFoundException classNotFoundException){
            System.out.println(driver + " not found");
            System.out.println(classNotFoundException.getMessage());
        }catch(SQLException sqlexeption){
            System.out.println("connection with database not established");
            System.out.println(sqlexeption.getMessage());
        }
    }
    
    public static Connection getInstance(){
        try {
            if(instance == null || instance.isClosed())
                new DBConnection();
        } catch (SQLException ex) {
            System.out.println("exception caught creating connection instance");
            System.out.println(ex.getMessage());
        }
        return instance;
    }
}
