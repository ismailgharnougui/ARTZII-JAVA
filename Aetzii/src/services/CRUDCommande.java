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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import models.Commandes;
import models.Livreurs;
import models.Users;
import services.InterfacesCommande;


/**
 *
 * @author hp
 */
 public class CRUDCommande implements InterfacesCommande {

     
    Statement st;
Connection conn = MyConnection.getInstance().getConnection();

    public ArrayList<Commandes> afficherCommandes() {
        ArrayList<Commandes> ls = new ArrayList();
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from `commandes`");
            while(rs.next()){
                ls.add(
                        new Commandes(
                                rs.getInt("id"),
                                rs.getString("adresse"),
                                rs.getString("region"), // region tbaddelha b'esm l champs li 3amlou mokhtar fel commande
                                rs.getDate("date_creation"),
                                new Livreurs(),
                                new Users(1, "fadi"),
                                rs.getBoolean("livraison")
                        )
                );
            }
        }catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return ls;
    }
    
    @Override
    public void affecterLivreur(Commandes comm, Livreurs liv) {
        
     {
        try{
            
         String req="UPDATE `commandes` SET livreur_id =  ? WHERE id= ? ;";
            PreparedStatement stmt=conn.prepareStatement(req);
            stmt.setInt(1, liv.getId());
         stmt.setInt(2, comm.getId());
        stmt.executeUpdate();
            System.out.println("Livreur effectué");
        
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
         
    
    }
    }
    
    
 }

    

    
    


