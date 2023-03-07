/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.sql.Connection;
import pidev.DAO.CRUDCommande;
import pidev.entities.Livreurs;
import pidev.DAO.CRUDLivreurs;
import pidev.DB.DBConnection;
import pidev.entities.Commandes;

/**
 *
 * @author hp
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection connexion = DBConnection.getInstance();
     
        Livreurs liv = new Livreurs( "25252525","hamza", "ayedi");
        CRUDLivreurs crud =new CRUDLivreurs();
        crud.ajouterlivreurs(liv);
        crud.supprimerlivreurs(4);
        Livreurs liv1 = new Livreurs("23232323", "toukebri", "benToukebri");
        crud.ajouterlivreurs(liv1);
        System.out.println(crud.afficherlivreurs());
        CRUDCommande serviceCommande = new CRUDCommande();
        //Commandes comm =new Commandes(3);
       
        Livreurs liv2 = new Livreurs(5,"20045423", "Ahmed", "sabri");
        crud.modifierlivreurs(liv2);
         //serviceCommande.affecterLivreur(comm, liv2);
        Livreurs liv5 =new Livreurs (2,"92554521","samir","loussif","tunis");
       crud.ajouterlivreurs(liv5);
       //Commandes comm1 = new Commandes(5);
       //serviceCommande.affecterLivreur(comm, liv2);
      
    }
    
}
