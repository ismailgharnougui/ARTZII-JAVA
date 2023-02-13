/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entites.Role;
import IServices.IRole;
import utils.MyConnection;
import Entites.Role;
import IServices.IRole;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author marie
 */
public class CRUDRole implements IRole{
Statement ste;
Connection conn = MyConnection.getInstance().getConnection();
        

    public void ajouterRole(Role R) {
    try {
        ste = conn.createStatement();
        String req = "Insert into role values(0,'"+R.getroleU()+"')";
        ste.executeUpdate(req);
        System.out.println("role ajouté");
    } catch (SQLException ex) {
            System.out.println("role non ajouté!!!!");    }
    }

     
    public void modifierRole(Role R) {
        try {
            String req = "UPDATE `role` SET `roleU` = '" + R.getroleU();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("role updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimerRole (String roleU) {
        try {
            String req = "DELETE FROM `role` WHERE roleU = " + roleU;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("role deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Role> afficherRole() {
       List<Role> list = new ArrayList<>();
        try {
            String req = "Select * from role";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Role R  = new Role();
             R.setroleU(RS.getString(1));
             
             list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void ajouterRole2(Role R) {
        try {
            String req = "INSERT INTO `role` (`roleU`) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, R.getroleU());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
  
}
 


