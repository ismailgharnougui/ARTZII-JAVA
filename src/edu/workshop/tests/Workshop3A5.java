/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.tests;

import edu.workshop.utils.MyConnection;
import edu.worshop.entites.Reclamation;
import edu.worshop.services.CRUDReclamation;

/**
 *
 * @author belkn
 */
public class Workshop3A5 {

   
    public static void main(String[] args) {
        
        MyConnection connexion = MyConnection.getInstance();
        //Reclamation r = new Reclamation("rectType2", "en cours", "je redige ce ..","objet2");
        
        CRUDReclamation crud = new CRUDReclamation();
        //crud.ajouterReclamation(r);
        //crud.supprimerReclamation(3);
        //crud.afficherReclamation();
        crud.modifierReclamation(new Reclamation(2,"type14","acqscqsc","ijij","zfzfzfzf"));
    }
    
}
