/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.connexion3A18.tests;

import edu.connexion3A18.entities.Evenement;
import edu.connexion3A18.entities.Ticket;
import edu.connexion3A18.services.TicketCRUD;
import edu.connexion3A18.services.EvenementCRUD;
import edu.connexion3A18.utils.Myconnection;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Aziza
 */
public class MainClass {
    public static void main(String[] args) throws SQLException {
        Myconnection con = new Myconnection();
      
EvenementCRUD ecd = new EvenementCRUD();
       Date df = new Date();
       
      Evenement e2 = new Evenement("hb","ariana",3000,5,"22/02/2022 à 10.00h","22/02/2022 à 12h","hfuyfhdf", 50,"hh");
     //  System.out.println(ecd.GetEventfromId(19)); 
   //ecd.ajouterEvenement(e2);
     //ecd.ModifierEvenement(e2, 24);
   // System.out.println(ecd.afficherEvenemets2());
    // ecd.ChercherEvenement(9);
      // System.out.println(ecd.ModifierEvenement(e2, 1));
    // System.out.println(ecd.afficherEvenemets());
   //  ecd.supprimerEvent(24);
      TicketCRUD tcd = new TicketCRUD();
        
     Ticket t1 = new Ticket ();
       //tcd.ajouterTicket(25);
      //tcd.SupprimerTicket(23);
       
    
       
    }
    }

