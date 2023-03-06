/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A18.entities;


import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author User
 */

public class Evenement {
    private  int idEvent;
    private  String NomEvent;
    private  String AdresseEvent;
    private  int CapaciteEvent;
    public  int NbrTicketdispo;
    private  String DateDebutEvent;
    private  String DateFinEvent;
    private  String DescriptionEvent;
    private  float PrixEntre;
    private  String image1;
   // public int getnbrTicketAchete;
    public int getnbrTicketAchete;
    
    public Evenement(){

}

    public Evenement(int idEvent, String NomEvent, String AdresseEvent, int CapaciteEvent, int NbrTicketdispo, String DateDebutEvent, String DateFinEvent, String DescriptionEvent, float PrixEntre, String image1) {
        this.idEvent = idEvent;
        this.NomEvent = NomEvent;
        this.AdresseEvent = AdresseEvent;
        this.CapaciteEvent = CapaciteEvent;
        this.NbrTicketdispo = NbrTicketdispo;
        this.DateDebutEvent = DateDebutEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.PrixEntre = PrixEntre;
        this.image1 = image1;
    }

    public Evenement(String NomEvent, String AdresseEvent, int CapaciteEvent, int NbrTicketdispo, String DateDebutEvent, String DateFinEvent, String DescriptionEvent, float PrixEntre) {
        this.NomEvent = NomEvent;
        this.AdresseEvent = AdresseEvent;
        this.CapaciteEvent = CapaciteEvent;
        this.NbrTicketdispo = NbrTicketdispo;
        this.DateDebutEvent = DateDebutEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.PrixEntre = PrixEntre;
    }
    

    public Evenement(String NomEvent, String AdresseEvent, int CapaciteEvent, int NbrTicketdispo, String DateDebutEvent, String DateFinEvent, String DescriptionEvent, float PrixEntre, String image1) {
        this.NomEvent = NomEvent;
        this.AdresseEvent = AdresseEvent;
        this.CapaciteEvent = CapaciteEvent;
        this.NbrTicketdispo = NbrTicketdispo;
        this.DateDebutEvent = DateDebutEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.PrixEntre = PrixEntre;
        this.image1 = image1;
    }
    
    

    public Evenement(int idEvent, String NomEvent, String AdresseEvent, int CapaciteEvent, int NbrTicketdispo, String DateDebutEvent, String DateFinEvent, String DescriptionEvent, float PrixEntre) {
        this.idEvent = idEvent;
        this.NomEvent = NomEvent;
        this.AdresseEvent = AdresseEvent;
        this.CapaciteEvent = CapaciteEvent;
        this.NbrTicketdispo = NbrTicketdispo;
        this.DateDebutEvent = DateDebutEvent;
        this.DateFinEvent = DateFinEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.PrixEntre = PrixEntre;
    }



    public int getIdEvent() {
        return idEvent;
    }

    public String getNomEvent() {
        return NomEvent;
    }

    public String getAdresseEvent() {
        return AdresseEvent;
    }

    /**
     *
     * @return
     */
    public int getCapaciteEvent() {
        return CapaciteEvent;
    }

    public String getDateDebutEvent() {
        return DateDebutEvent;
    }

    public String getDateFinEvent() {
        return DateFinEvent;
    }


    public String getDescriptionEvent() {
        return DescriptionEvent;
    }

    public float getPrixEntre() {
        return PrixEntre;
    }

    public void setidEvent(int id) {
        this.idEvent = id;
    }

    public void setNomEvent(String NomEvent) {
        this.NomEvent = NomEvent;
    }

    public void setAdresseEvent(String AdresseEvent) {
        this.AdresseEvent = AdresseEvent;
    }

    public void setCapaciteEvent(int CapaciteEvent) {
        this.CapaciteEvent = CapaciteEvent;
    }

    public void setDateDebutEvent(String DateDebutEvent) {
        this.DateDebutEvent = DateDebutEvent;
    }

    public void setDateFinEvent(String DateFinEvent) {
        this.DateFinEvent = DateFinEvent;
    }

    public void setDescriptionEvent(String DescriptionEvent) {
        this.DescriptionEvent = DescriptionEvent;
    }

    public void setPrixEntre(float PrixEntre) {
        this.PrixEntre = PrixEntre;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvent=" + idEvent + ", NomEvent=" + NomEvent + ", AdresseEvent=" + AdresseEvent + ", CapaciteEvent=" + CapaciteEvent + ", NbrTicketdispo=" + NbrTicketdispo + ", DateDebutEvent=" + DateDebutEvent + ", DateFinEvent=" + DateFinEvent + ", DescriptionEvent=" + DescriptionEvent + ", PrixEntre=" + PrixEntre + ", image1=" + image1 + ", getnbrTicketAchete=" + getnbrTicketAchete + '}';
    }

    

    

    public int getNbrTicketdispo() {
        return NbrTicketdispo;
    }

    public void setNbrTicketdispo(int NbrTicketdispo) {
        this.NbrTicketdispo = NbrTicketdispo;
    }

    public Object toLowerCase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

 
    
}