/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.MyConnection;
import models.Reclamation;
import java.sql.Connection;
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


public class CRUDReclamation implements InterfaceServices{
Statement ste=null;
Connection conn = MyConnection.getInstance().getConnection();
        
    @Override
    public void ajouterReclamation(Reclamation r) {
    try {
        ste = conn.createStatement();
        String req = "Insert into Reclamation(TypeR, etat, Description,objet,iduser) values('"+r.getTypeR()+"','"+r.getEtat()+"','"+r.getDescription()+"','"+r.getObjet()+"','"+r.getIdUser()+"')";
        ste.executeUpdate(req);
        System.out.println("Reclamation ajouté");
    } catch (SQLException ex) {
            System.out.println("Reclamation non ajouté!!!!");    }
    }

     
    @Override
    public void modifierReclamation(Reclamation r) {
        try {
            String req = "UPDATE `Reclamation` SET `TypeR` = '" + r.getTypeR() + "', `etat` = '" + r.getEtat()+ "', `Description` = '" + r.getDescription() + "', `objet` = '" + r.getObjet()  + "' WHERE `Reclamation`.`id` = " + r.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  @Override
    public void supprimerReclamation(int id) {
        try {
            String req = "DELETE FROM `Reclamation` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public ObservableList<Reclamation>  afficherReclamation() {
    try {
        ste= conn.createStatement();
    } catch (SQLException ex) {
        System.err.println("erreur");
    }
    ObservableList<Reclamation> rec = FXCollections.observableArrayList();
        try {
        String req = "SELECT * FROM `Reclamation`";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Reclamation resultReclamation = new Reclamation(result.getInt("id"),result.getString("TypeR"),result.getString("Description"), result.getString("objet"), result.getString("dateR"), result.getString("etat"),result.getInt("iduser"));
            rec.add(resultReclamation);
        }
        System.out.println(rec);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return rec;
    }
public  ObservableList<Reclamation> afficherReclamation(int idUser) {
    try {
        ste= conn.createStatement();
    } catch (SQLException ex) {
        System.err.println("erreur");
    }
    ObservableList<Reclamation> rec = FXCollections.observableArrayList();
        try {
        String req = "SELECT * FROM Reclamation where iduser = '" +idUser+"'" ;
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Reclamation resultReclamation = new Reclamation(result.getInt("id"),result.getString("TypeR"),result.getString("Description"), result.getString("objet"), result.getString("dateR"), result.getString("etat"),result.getInt("iduser"));
            
            rec.add(resultReclamation);
        }
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return rec;
    }


public Reclamation getReclamationById(int id) {
 try {
        ste= conn.createStatement();
    } catch (SQLException ex) {
        System.err.println("erreur");
    }
        try {
        String req = "SELECT * FROM Reclamation where id="+id;
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Reclamation resultReclamation = new Reclamation(result.getInt("id"),result.getString("TypeR"),result.getString("Description"), result.getString("objet"), result.getString("dateR"), result.getString("etat"),result.getInt("iduser"));
            return resultReclamation;
        }
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
        return null;
        
    }
}
