/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author GOLDEN
 */
public class Ticket {
      private int IdTicket;
      
    private float PrixTicket;
    private String NomEvent;
    private String NomClient;
    private int nbr_ticket;

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String NomClient) {
        this.NomClient = NomClient;
    }

    public int getNbr_ticket() {
        return nbr_ticket;
    }

    public void setNbr_ticket(int nbr_ticket) {
        this.nbr_ticket = nbr_ticket;
    }
    
    

    public Ticket() {
    }

    public Ticket(int IdTicket, float PrixTicket, String NomEvent, String NomClient, int nbr_ticket) {
        this.IdTicket = IdTicket;
        this.PrixTicket = PrixTicket;
        this.NomEvent = NomEvent;
        this.NomClient = NomClient;
        this.nbr_ticket = nbr_ticket;
    }

    public Ticket(int IdTicket,float PrixTicket, String NomEvent) {
        this.IdTicket = IdTicket;
       
        this.PrixTicket = PrixTicket;
        this.NomEvent = NomEvent;
        
    }

  /*  public Ticket(int IdTicket, float PrixTicket,  String NomEvent ) {
        this.IdTicket = IdTicket;
        this.PrixTicket = PrixTicket;
        this.NomEvent = NomEvent;
     
    }

    public Ticket(float PrixTicket, int idEvent, String TypeTicket) {
         this.IdTicket = IdTicket;
        this.PrixTicket = PrixTicket;
        this.NomEvent = NomEvent;
      
    }*/

    public int getIdTicket() {
        return IdTicket;
    }

    public float getPrixTicket() {
        return PrixTicket;
    }

    public String getNomEvent() {
        return NomEvent;
    }

   

    public void setIdTicket(int IdTicket) {
        this.IdTicket = IdTicket;
    }

    public void setPrixTicket(float PrixTicket) {
        this.PrixTicket = PrixTicket;
    }

    public void setNomEvent(String NomEvent) {
        this.NomEvent = NomEvent;
    }

   

    @Override
    public String toString() {
        return "Ticket{" + "IdTicket=" + IdTicket + ", PrixTicket=" + PrixTicket + ", NomEvent=" + NomEvent + '}';
    }
    
    
}
