/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import IServices.IUtilisateur;
import utils.MyConnection;
import Entites.Utilisateur;
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


public class CRUDUtilisateur implements IUtilisateur{
Statement ste;
Connection conn = MyConnection.getInstance().getConnection();
 ObservableList<Utilisateur>obList = FXCollections.observableArrayList();
 ObservableList<Utilisateur>obListCat = FXCollections.observableArrayList();


    public void ajouterUtilisateur(Utilisateur U) {
    try {
        ste = conn.createStatement();
        String req = "Insert into utilisateur values(0,'"+U.getnomU()+"','"+U.getprenomU()+"','"+U.getemailU()+"','"+U.getmdpU()+"','"+U.getroleU()+"','"+U.getadresse()+"')";
        ste.executeUpdate(req);
        System.out.println("utilisateur ajouté");
    } catch (SQLException ex) {
            System.out.println("utilisateur non ajouté!!!!");    }
    }

     
    public void modifierUtilisateur(Utilisateur U) {
        try {
            String req = "UPDATE `utilisateur` SET `nomU` = '" + U.getnomU() + "', `emailU` = '" + U.getemailU() + "', `mdpU` = '" + U.getmdpU() + "', `prenomU` = '" + U.getprenomU() +"', `roleU` = '" + U.getroleU() + "',`adresse` = '" + U.getadresse() + "' WHERE `utilisateur`.`idU` = " + U.getidU();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimerUtilisateur (int idU) {
        try {
            String req = "DELETE FROM `utilisateur` WHERE idU = " + idU;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Utilisateur> afficherUtilisateur() {
       String sql = "SELECT * FROM utilisateur";
        List<Utilisateur> listeUtilisateur = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int idU = result.getInt(1);
                String nomU = result.getString(2);
                String prenomU = result.getString(3);
                String emailU = result.getString(4);
                String mdpU = result.getString(5);
                String roleU = result.getString(6);
                String adresse = result.getString(7);

                Utilisateur U = new Utilisateur(idU, nomU, prenomU, emailU, mdpU, roleU, adresse);
                obList.add(U);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obList;
        
        
        
    }

    public void ajouterUtilisateur2(Utilisateur U) {
        try {
            String req = "INSERT INTO `utilisateur` (`nomU`, `prenomU`,`emailU`,`mdpU`,`roleU`,`adresse`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
             ps.setString(1, U.getnomU());
            ps.setString(2, U.getprenomU());
            ps.setString(3, U.getemailU());
            ps.setString(4, U.getmdpU());
            ps.setString(5, U.getroleU());
            ps.setString(6, U.getadresse());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
}
 

