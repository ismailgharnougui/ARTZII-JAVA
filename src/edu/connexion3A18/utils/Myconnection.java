  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.connexion3A18.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aziza
 */
public class Myconnection {
    private String URL = "jdbc:mysql://localhost:3306/esprit3a18";
    private String Login = "root";
    private String Password = "";
    private Connection cnx;
    private static Myconnection instance;
    
    public Myconnection(){
        try {
            cnx = DriverManager.getConnection(URL, Login, Password);
            System.out.println("Connexion Réussite");
            
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    public Connection getCnx(){
       
        return cnx;
    }
    public static Myconnection getInstance(){
            if(instance==null){
        instance = new Myconnection();}
    return instance;
    }
}
