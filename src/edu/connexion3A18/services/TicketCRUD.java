/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A18.services;

import edu.connexion3A18.entities.Evenement;
import edu.connexion3A18.entities.Ticket;
import java.awt.Event;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import edu.connexion3A18.utils.Myconnection;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author User
 */
public class TicketCRUD {

    EvenementCRUD ecd = new EvenementCRUD();
    Connection cnx2;

    public TicketCRUD() {
        cnx2 = Myconnection.getInstance().getCnx();
    }

    public void ajouterTicket(int id, String nClient, int nbr) throws SQLException {
        String s = String.valueOf(id);
        Evenement e;
        e = ecd.GetEvent(s);
        if ((e.getNbrTicketdispo()) > 1) {
            try {
                String requete6 = "INSERT INTO Ticket (IdTicket,NomClient,PrixTicket,NomEvent,nbr_ticket)"
                        + "VALUES(?,?,?,?,?)";
                PreparedStatement pst = cnx2.prepareStatement(requete6);
                pst.setInt(1, e.getIdEvent());
                pst.setString(2, nClient);
                pst.setFloat(3, e.getPrixEntre());
                pst.setString(4, e.getNomEvent());
                pst.setInt(5, nbr);
                pst.executeUpdate();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.out.println("Pas des tickets disponibles");
        }
    }

    public ObservableList<Ticket> afficherTickets() {
        ObservableList<Ticket> myList1 = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT * FROM Ticket";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setIdTicket(rs.getInt(1));
                t.setNomClient(rs.getString("NomClient"));
                t.setPrixTicket(rs.getFloat("PrixTicket"));
                t.setNomEvent(rs.getString("NomEvent"));
                t.setNbr_ticket(rs.getInt("nbr_ticket"));

                myList1.add(t);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList1;
    }
    
    public void supprimerTicket(int a) throws SQLException {

        try {
            //System.out.println("Entrez l'Id de l'évènement supprimer");
            //Scanner sc5 = new Scanner(System.in);
            //String a = sc5.nextLine();

            String requete4 = "delete from ticket where idTicket=" + a;
            PreparedStatement ste = cnx2.prepareStatement(requete4);
            ste.executeUpdate();

            System.out.println("Ticket supprimé aves succeés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static void sendSms(String numero, String message) {
         String ACCOUNT_SID = "AC5c749493c3a329a543b2824ec4b95fea";
         String AUTH_TOKEN = "1c449f2b262c0d03ceb7139d65ad2e0b";
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message msg = Message.creator(
                    new PhoneNumber(numero),
                    new PhoneNumber("+15076390746"),
                    message
            ).create();
            System.out.println("SMS sent: " + msg.getSid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
