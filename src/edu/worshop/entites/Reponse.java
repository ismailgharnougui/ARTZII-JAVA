/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.entites;


public class Reponse {
    private int idRep;
    private String dateRep;
    private String contenuRep;

    public Reponse() {
    }

    public Reponse(String contenuRep) {
        this.contenuRep = contenuRep;
    }
    

    public Reponse(int idRep, String dateRep, String contenuRep) {
        this.idRep = idRep;
        this.dateRep = dateRep;
        this.contenuRep = contenuRep;
    }

    public int getIdRep() {
        return idRep;
    }

    public String getDateRep() {
        return dateRep;
    }

    public String getContenuRep() {
        return contenuRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }

    public void setDateRep(String dateRep) {
        this.dateRep = dateRep;
    }

    public void setContenuRep(String contenuRep) {
        this.contenuRep = contenuRep;
    }

    @Override
    public String toString() {
        return "Reponse{" + "idRep=" + idRep + ", dateRep=" + dateRep + ", contenuRep=" + contenuRep + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idRep;
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
        final Reponse other = (Reponse) obj;
        if (this.idRep != other.idRep) {
            return false;
        }
        return true;
    }
    
    
    
}
