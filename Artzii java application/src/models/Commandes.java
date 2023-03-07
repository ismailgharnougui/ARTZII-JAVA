/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.Objects;

// COMMANDE HEDHI AMALTHA ENA BCH NAAMEL TEST BADALHA BL ENTITY COMMANDE MTEEK //

/**
 *
 * @author hp
 */
public class Commandes {
  
   private int id ;
   private String addresse , region;
   private Date date_creation;   
   private Livreurs livreur_id;
   private Users user_id;
   private boolean livraison;

    public Commandes(int id, String addresse, String region, Date date_creation, Livreurs livreur_id, Users user_id, boolean livraison) {
        this.id = id;
        this.addresse = addresse;
        this.region = region;
        this.date_creation = date_creation;
        this.livreur_id = livreur_id;
        this.user_id = user_id;
        this.livraison = livraison;
    }

    public Commandes(int id, String addresse, String region, Date date_creation, Livreurs livreur_id, Users user_id) {
        this.id = id;
        this.addresse = addresse;
        this.region = region;
        this.date_creation = date_creation;
        this.livreur_id = livreur_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Livreurs getLivreur_id() {
        return livreur_id;
    }

    public void setLivreur_id(Livreurs livreur_id) {
        this.livreur_id = livreur_id;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public boolean isLivraison() {
        return livraison;
    }

    public void setLivraison(boolean livraison) {
        this.livraison = livraison;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.addresse);
        hash = 97 * hash + Objects.hashCode(this.region);
        hash = 97 * hash + Objects.hashCode(this.date_creation);
        hash = 97 * hash + Objects.hashCode(this.livreur_id);
        hash = 97 * hash + Objects.hashCode(this.user_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commandes other = (Commandes) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.addresse, other.addresse)) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.date_creation, other.date_creation)) {
            return false;
        }
        if (!Objects.equals(this.livreur_id, other.livreur_id)) {
            return false;
        }
        if (!Objects.equals(this.user_id, other.user_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commandes{" + "id=" + id + ", addresse=" + addresse + ", region=" + region + ", date_creation=" + date_creation + ", livreur_id=" + livreur_id + ", user_id=" + user_id + ", livraison=" + livraison + '}';
    }

    
   
   
   

    

    



}


