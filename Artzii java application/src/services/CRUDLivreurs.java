/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import db.MyConnection;
import models.Livreurs;
import services.InterfacesServices;

/**
 *
 * @author hp
 */
public class CRUDLivreurs implements InterfacesServices {

    Statement st;
Connection conn = MyConnection.getInstance().getConnection();
    
   
    
    
    
    
    @Override
    public void ajouterlivreurs(Livreurs liv) {
     try {
          
          st = conn.createStatement();
          String req= "Insert into livreurs(num_tel,nom,prenom,region_livreur) values ('"+liv.getNum_tel()+"','"+liv.getNom()+"', '"+liv.getPrenom()+"', '"+liv.getRegion_livreur()+"')";
          st.executeUpdate(req);
          System.out.println("Livreurs ajouté!!");
      } catch (SQLException ex) {
             System.out.println("Personne non ajouté!!!!");
             System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerlivreurs(int id) {
    
        try{
            String req=" DELETE from `livreurs` WHERE id= " +id;
            Statement st= conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Livreur supprimé");
        }
        catch (SQLException ex){
            System.out.println("Livreur non supprimé!!!");
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public List<Livreurs> afficherlivreurs() {
    
        List<Livreurs> livreur = new ArrayList<>();
        try{
            st = conn.createStatement();
            String req="Select * from `livreurs`";
            ResultSet result =st.executeQuery(req);
            while(result.next()){
                
                livreur.add(new Livreurs(result.getInt("id"), result.getString("num_tel"),result.getString("nom"),result.getString("prenom"),result.getString("region_livreur")));
                
            }
            
            System.out.println(livreur);
        }catch(SQLException ex){
            System.out.println(ex);
            
        }
    
        return livreur;
    }

    
    
    @Override
    public void modifierlivreurs(Livreurs liv) {
        try {
            String req = "UPDATE `livreurs` SET `num_tel` = '" + liv.getNum_tel() + "', `nom` = '" + liv.getNom() + "', `prenom` = '" + liv.getPrenom() + "' WHERE "
                    + "`id` = " + liv.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Livreur updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    
}
