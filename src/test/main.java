/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import utils.MyConnection;
import Entites.Utilisateur;
import Services.CRUDUtilisateur;


/**
 *
 * @author belkn
 */
public class main {

   
    public static void main(String[] args) {
        
        MyConnection connexion = MyConnection.getInstance();
       Utilisateur U = new Utilisateur("ma","ftou","ma@email.com","99","admin","ariana");
            
        CRUDUtilisateur crud = new CRUDUtilisateur();
       //crud.ajouterUtilisateur(U);
       System.out.println(crud.afficherUtilisateur());
       // Utilisateur U = new Utilisateur(26,"ma","ftou","ma@email.com","99","client");
       //crud.modifierUtilisateur(U);
       //crud.supprimerUtilisateur(23);
       crud.afficherUtilisateur();
     
        
    }
    
}