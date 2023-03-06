/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.connexion3A18.services;

import edu.connexion3A18.entities.Evenement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import edu.connexion3A18.utils.Myconnection;
import java.time.LocalDate;

/**
 *
 * @author GOLDEN
 */
public class EvenementCRUD {

    Connection cnx2;

    public EvenementCRUD() {
        cnx2 = Myconnection.getInstance().getCnx();
    }

    public void ajouterEvenement(Evenement E) {
        try {
            String requete2 = "INSERT INTO Evenement (NomEvent,AdresseEvent,CapaciteEvent,nbrTicketdispo,DateDebutEvent,DateFinEvent,DescriptionEvent,PrixEntre,image1)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, E.getNomEvent());
            pst.setString(2, E.getAdresseEvent());
            pst.setInt(3, E.getCapaciteEvent());
            pst.setInt(4, E.getNbrTicketdispo());
            pst.setString(5, E.getDateDebutEvent());
            pst.setString(6, E.getDateFinEvent());
            pst.setString(7, E.getDescriptionEvent());
            //pst.setString(7, E.getDescriptionEvent());
            pst.setFloat(8, E.getPrixEntre());
            String uri = fichier.path1;

            pst.setString(9, uri);
            pst.executeUpdate();
            System.out.println("Evènement ajouté avec succés!!!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Evenement> afficherEvenemets() {
        List<Evenement> myList = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT * FROM Evenement";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setidEvent(rs.getInt("idEvent"));
                e.setNomEvent(rs.getString("NomEvent"));
                e.setAdresseEvent(rs.getString("AdresseEvent"));
                e.setCapaciteEvent(rs.getInt("CapaciteEvent"));
                e.setNbrTicketdispo(rs.getInt("NbrTicketdispo"));
                e.setDateDebutEvent(rs.getString("DateDebutEvent"));
                e.setDateFinEvent(rs.getString("DateFinEvent"));
                e.setDescriptionEvent(rs.getString("DescriptionEvent"));
                e.setPrixEntre(rs.getFloat("PrixEntre"));
                e.setImage1(rs.getString("image1"));

                myList.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public ObservableList<Evenement> afficherEvenemets2() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT * FROM Evenement";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setNomEvent(rs.getString("NomEvent"));
                e.setAdresseEvent(rs.getString("AdresseEvent"));
                e.setCapaciteEvent(rs.getInt("CapaciteEvent"));
                e.setNbrTicketdispo(rs.getInt("nbrTicketdispo"));
                e.setDateDebutEvent(rs.getString("DateDebutEvent"));
                e.setDateFinEvent(rs.getString("DateFinEvent"));

                e.setDescriptionEvent(rs.getString("DescriptionEvent"));
                e.setPrixEntre(rs.getFloat("PrixEntre"));
                e.setImage1(rs.getString("Image1"));
                myList.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public void supprimerEvent(int a) throws SQLException {

        try {
            //System.out.println("Entrez l'Id de l'évènement supprimer");
            //Scanner sc5 = new Scanner(System.in);
            //String a = sc5.nextLine();

            String requete4 = "delete from Evenement where idEvent=" + a;
            PreparedStatement ste = cnx2.prepareStatement(requete4);
            ste.executeUpdate();

            System.out.println("Evènement supprimé aves succeés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ChercherEvenement(int id) throws SQLException {
        System.out.println("entre l'Id de l'évènement à Chercher");
        Scanner sc = new Scanner(System.in);
        String h = sc.nextLine();
        List<Evenement> Evenement = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Evenement where idEvent=" + h;
            // System.out.println("==<>>>>> " + sql);
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setidEvent(rs.getInt(1));
                e.setNomEvent(rs.getString("NomEvent"));
                e.setAdresseEvent(rs.getString("AdresseEvent"));
                e.setCapaciteEvent(rs.getInt("CapaciteEvent"));
                e.setNbrTicketdispo(rs.getInt("NbrTicketdispo"));
                e.setDateDebutEvent(rs.getString("DateDebutEvent"));
                e.setDateFinEvent(rs.getString("DateFinEvent"));

                e.setDescriptionEvent(rs.getString("DescriptionEvent"));
                e.setPrixEntre(rs.getFloat("PrixEntre"));
                System.out.println(e);
                Evenement.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void ModifierEvenement(Evenement e, int id) {
        try {
            String sql = "UPDATE Evenement SET NomEvent=? ,AdresseEvent=? ,CapaciteEvent=?,nbrTicketdispo=?,DateDebutEvent=?,DateFinEvent=?,DescriptionEvent=?,PrixEntre=?,image1=? WHERE idEvent=?";
            PreparedStatement ste = cnx2.prepareStatement(sql);
            String uri = fichier.path1;
//            uri= uri.replace("\\", "\\\\");
            ste.setString(1, e.getNomEvent());
            ste.setString(2, e.getAdresseEvent());
            ste.setInt(3, e.getCapaciteEvent());
            ste.setInt(4, e.getNbrTicketdispo());
            ste.setString(5, e.getDateDebutEvent());
            ste.setString(6, e.getDateFinEvent());
            ste.setString(7, e.getDescriptionEvent());
            // ste.setString(7, e.getDescriptionEvent());
            ste.setFloat(8, e.getPrixEntre());
            ste.setString(9, uri);
            ste.setInt(10,id);
            ste.executeUpdate();
            System.out.println("Modification effectuée avec succés !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Evenement GetEvent(String id) throws SQLException {

        try {
            String sql = "SELECT * FROM Evenement where idEvent=" + id;

            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setidEvent(rs.getInt("idEvent"));
                e.setNomEvent(rs.getString("NomEvent"));
                e.setAdresseEvent(rs.getString("AdresseEvent"));
                e.setCapaciteEvent(rs.getInt("CapaciteEvent"));
                e.setNbrTicketdispo(rs.getInt("nbrTicketdispo"));
                e.setDateDebutEvent(rs.getString("DateDebutEvent"));
                e.setDateFinEvent(rs.getString("DateFinEvent"));

                e.setDescriptionEvent(rs.getString("DescriptionEvent"));
                e.setPrixEntre(rs.getFloat("PrixEntre"));

                return e;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("aucun event");
        return null;
    }

    public boolean ModifierEvenementNbrTique(Evenement E, int id) throws SQLException {

        try {
            String req = "UPDATE Evenement SET `nbrTicketdispo`=? WHERE Evenement.`idEvent` = ?";
            java.sql.PreparedStatement pst = (java.sql.PreparedStatement) cnx2.prepareStatement(req);
            pst.setInt(1, E.getNbrTicketdispo());
            pst.setInt(2, id);

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("Modification effectuée avec sucées");
        return true;

    }

    public Evenement getEvenementById(int id) {
        Evenement e = new Evenement();

        try {
            String requete3 = "SELECT * FROM Evenement WHERE idEvent=" + id;
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            if (rs.next()) {
                e.setidEvent(rs.getInt("idEvent"));
                e.setNomEvent(rs.getString("NomEvent"));
                e.setAdresseEvent(rs.getString("AdresseEvent"));
                e.setCapaciteEvent(rs.getInt("CapaciteEvent"));
                e.setNbrTicketdispo(rs.getInt("NbrTicketdispo"));
                e.setDateDebutEvent(rs.getString("DateDebutEvent"));
                e.setDateFinEvent(rs.getString("DateFinEvent"));

                e.setDescriptionEvent(rs.getString("DescriptionEvent"));
                e.setPrixEntre(rs.getFloat("PrixEntre"));
                // Ajoutez d'autres propriétés en fonction de votre classe Evenement
                System.out.println(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return e;
    }

}
