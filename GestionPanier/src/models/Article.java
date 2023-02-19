/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author medmo
 */
public class Article {
    private int ref;
    private String nom;
    private String dimension;
    private float prix;

    public Article() {
    }
    
    public Article(String nom, String dimension, float prix) {
        this.nom=nom;
        this.dimension = dimension;
        this.prix = prix;
    }
    
     public Article(int ref,String nom,String dimension, float prix) {
         this.ref = ref;
           this.nom=nom;
        this.dimension = dimension;
        this.prix = prix;
    }

    public int getRef() {
        return ref;
    }

    public String getNom() {
        return nom;
    }
    

    public String getDimension() {
        return dimension;
    }

    public float getPrix() {
        return prix;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Article{" + "ref=" + ref + ", nom=" + nom + ", dimension=" + dimension + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.ref;
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
        final Article other = (Article) obj;
        if (this.ref != other.ref) {
            return false;
        }
        return true;
    }
    
    
    
}
