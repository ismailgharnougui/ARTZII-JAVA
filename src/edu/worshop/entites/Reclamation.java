/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.entites;

import java.util.Objects;


public class Reclamation {
    private int id;
    private String TypeR;
    private String Description;
    private String Objet;
    private String DateR;
    private String etat;

    public Reclamation() {
    }
     public Reclamation(int id, String TypeR, String Description, String Objet, String etat) {
        this.id = id;
        this.TypeR = TypeR;
        this.Description = Description;
        this.Objet = Objet;
        this.etat = etat;
    }
     
        public Reclamation(String TypeR, String Description, String Objet, String etat) {
        this.TypeR = TypeR;
        this.Description = Description;
        this.Objet = Objet;
        this.etat = etat;
    }

    public Reclamation(int id, String TypeR, String Description, String Objet, String DateR, String etat) {
        this.id = id;
        this.TypeR = TypeR;
        this.Description = Description;
        this.Objet = Objet;
        this.DateR = DateR;
        this.etat = etat;
    }
   
   
    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", TypeR=" + TypeR + ", Description=" + Description + ", Objet=" + Objet + ", DateR=" + DateR + ", etat=" + etat + '}';
    }
    public int getId() {
        return id;
    }

    public String getTypeR() {
        return TypeR;
    }

    public String getDateR() {
        return DateR;
    }
     public String getEtat() {
        return etat;
    }

    public String getDescription() {
        return Description;
    }

    public String getObjet() {
        return Objet;
    }

     
     

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeR(String TypeR) {
        this.TypeR = TypeR;
    }

    public void setDateR(String DateR) {
        this.DateR = DateR;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setObjet(String Objet) {
        this.Objet = Objet;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.TypeR);
        hash = 11 * hash + Objects.hashCode(this.DateR);
        hash = 11 * hash + Objects.hashCode(this.etat);

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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.TypeR, other.TypeR)) {
            return false;
        }
        return true;
    }
    
    
    
}
