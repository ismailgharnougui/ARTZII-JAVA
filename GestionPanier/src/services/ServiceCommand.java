/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import services.ServiceBasket;
import db.MyConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Article;
import models.Basket;
import models.Command;

/**
 *
 * @author medmo
 */
public class ServiceCommand implements InterfaceServiceCommand{
  Statement ste;
   Connection conn = MyConnection.getInstance().getConnection();


    @Override
    public void ajouter(Command c) {
       try {
            String req = "INSERT INTO `commands` (`id_client`, `mode_paiement`,`mode_livraison`, `cout_totale`) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            
            ps.setInt(1, c.getIdClient());
            ps.setString(2, c.getPayMethod());
            ps.setString(3, c.getLivMethod());
            ps.setDouble(4, c.getTotalCost());
         
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(int id) {
        PreparedStatement stmt = null;
        try {

            // Préparation de la requête de suppression
            String sql = "DELETE FROM commands WHERE id_client = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            // Exécution de la requête
            int result = stmt.executeUpdate();

            if (result > 0) {
                System.out.println("La commande avec l'ID " + id + " a été supprimée.");
            } else {
                System.out.println("Aucune commande avec l'ID " + id + " n'a été trouvée.");
            }

        } catch (SQLException ex) {
            System.err.println("Une erreur s'est produite lors de la suppression de la commande : " + ex.getMessage());
        }
    }
/*
    @Override
    public Command recupererCommClient(int idClient) {
        
        ServiceArticle sa = new ServiceArticle();
        ServiceClient sc = new ServiceClient();
        Command command = new Command();
        PreparedStatement stmt = null;
        ResultSet rs = null;
               try {
        String req = "SELECT * FROM `commands` WHERE id_client = ?";
        PreparedStatement pste=conn.prepareStatement(req);
        pste.setInt(1, idClient);
        
        ResultSet result = pste.executeQuery();
        while(result.next()){
            command.setComDate(result.getString("date_commande"));
           Article resultArticle = sa.get(result.getInt(2));
      command.addArticle(resultArticle);
        }
        command.setCl(sc.get(idClient)); 
        command.setPayMethod("Paiement CASH à la livraison");
        command.setTotalCost(command.getArticles().stream().mapToDouble(x->x.getPrix()).sum());
       
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
        return command;
    }

*/

   
}
