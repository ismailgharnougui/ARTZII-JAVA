/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Categorie;
import IServices.ICategorie;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ismae
 */

public class CRUDCategorie implements ICategorie{
Statement ste;
Connection conn = MyConnection.getInstance().getConnection();
        
    
    public void ajouterCategorie(Categorie C) {
    try {
        ste = conn.createStatement();
        String req = "Insert into categorie values(0,'"+C.getCatLib()+"')";
        ste.executeUpdate(req);
        System.out.println("Categorie ajouté");
    } catch (SQLException ex) {
            System.out.println("Categorie non ajouté!!!!");    }
    }

     
    
    public void modifierCategorie(Categorie C) {
        try {
            String req = "UPDATE `categorie` SET `CatLib` = '" + C.getCatLib()+ "' WHERE `categorie`.`CatId` = " + C.getCatId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  
    public void supprimerCategorie(int id) {
        try {
            String req = "DELETE FROM `categorie` WHERE CatId = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
@Override
    public List<Categorie> afficherCategorie() {
       List<Categorie> list = new ArrayList<>();
        try {
            String req = "Select * from categorie";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Categorie A = new Categorie();
             A.setCatLib(RS.getString(2));
             A.setCatId(RS.getInt(1));
             
             list.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

   
    public void ajouterCategorie2(Categorie C) {
        try {
            String req = "INSERT INTO `categorie` (`CatLib`) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1,C.getCatLib());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public boolean getCategorie(Categorie C) {
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("SELECT * FROM categorie WHERE CatId = ?");
            ps.setInt(1, C.getCatId());
                    ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }



}    


