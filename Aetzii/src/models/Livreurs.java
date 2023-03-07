/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * @author hp
 */
public class Livreurs {
   
    private int id;
    private String num_tel, nom, prenom, region_livreur;

    public Livreurs(String num_tel, String nom, String prenom, String region_livreur) {
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.region_livreur = region_livreur;
    }

    public Livreurs(int id, String num_tel, String nom, String prenom, String region_livreur) {
        this.id = id;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.region_livreur = region_livreur;
    }

    public Livreurs() {
    }

    public Livreurs(String num_tel, String nom, String prenom) {
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Livreurs(int id, String num_tel, String nom, String prenom) {
        this.id = id;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRegion_livreur() {
        return region_livreur;
    }

    public void setRegion_livreur(String region_livreur) {
        this.region_livreur = region_livreur;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.num_tel);
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + Objects.hashCode(this.prenom);
        hash = 53 * hash + Objects.hashCode(this.region_livreur);
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
        final Livreurs other = (Livreurs) obj;
        if (!Objects.equals(this.num_tel, other.num_tel)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.region_livreur, other.region_livreur)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livreurs{" + "id=" + id + ", num_tel=" + num_tel + ", nom=" + nom + ", prenom=" + prenom + ", region_livreur=" + region_livreur + '}';
    }

    

    
        


    
   

    

    
    
    
}
