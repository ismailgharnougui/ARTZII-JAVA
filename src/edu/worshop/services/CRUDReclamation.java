/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.services;

import edu.workshop.utils.MyConnection;
import edu.worshop.entites.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CRUDReclamation implements InterfaceServices{
Statement ste=null;
Connection conn = MyConnection.getInstance().getConnection();
        
    @Override
    public void ajouterReclamation(Reclamation r) {
    try {
        ste = conn.createStatement();
        String req = "Insert into Reclamation(TypeR, etat, Description,objet) values('"+r.getTypeR()+"','"+r.getEtat()+"','"+r.getDescription()+"','"+r.getObjet()+"')";
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
    public List<Reclamation> afficherReclamation() {
    try {
        ste= conn.createStatement();
    } catch (SQLException ex) {
        System.err.println("erreur");
    }
    List<Reclamation> rec = new ArrayList<Reclamation>();
        try {
        String req = "SELECT * FROM `Reclamation`";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Reclamation resultReclamation = new Reclamation(result.getInt(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5), result.getString(6));
            rec.add(resultReclamation);
        }
        System.out.println(rec);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return rec;
    }

}
