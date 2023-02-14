/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.services;

import edu.workshop.utils.MyConnection;
import edu.worshop.entites.Reclamation;
import edu.worshop.entites.Reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CRUDReponse implements RepInterface{
Statement ste=null;
Connection conn = MyConnection.getInstance().getConnection();

    public void ajouterReponse(Reponse r) {
    try {
        ste = conn.createStatement();
        String req = "Insert into Reponse(contenuRep) values('"+r.getContenuRep()+"')";
        ste.executeUpdate(req);
        System.out.println("Reponse ajouté");
    } catch (SQLException ex) {
            System.out.println("Reponse non ajouté!!!!");    }
    }

  

    public void modifierReponse(Reponse r) {
      try {
            String req = "UPDATE `Reponse` SET `ContenuRep` = '" + r.getContenuRep()  +  "' WHERE `idRep` = " + r.getIdRep();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reponse updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void supprimerReponse(int idRep) {
      try {
            String req = "DELETE FROM `Reponse` WHERE idRep = " + idRep;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reponse deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }




    }

    
    public List<Reponse> afficherReponse() {
    try {
        ste= conn.createStatement();
    } catch (SQLException ex) {
        System.err.println("erreur");
    }
    List<Reponse> rec = new ArrayList<Reponse>();
        try {
        String req = "SELECT * FROM `Reponse`";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Reponse resultReponse = new Reponse(result.getInt(1), result.getString(2), result.getString(3));
            rec.add(resultReponse);
        }
        System.out.println(rec);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return rec;
    }

 

   
}
