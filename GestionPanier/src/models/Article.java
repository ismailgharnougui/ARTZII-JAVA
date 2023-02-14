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
    private String dimension;
    private float prix;
    String dateAjout;

    public Article() {
    }
    
    public Article(String dimension, float prix) {
        this.dimension = dimension;
        this.prix = prix;
    }
    
     public Article(int ref,String dimension, float prix) {
         this.ref = ref;
        this.dimension = dimension;
        this.prix = prix;
    }

    public int getRef() {
        return ref;
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

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }
    
    

    @Override
    public String toString() {
        return "article{" + "ref=" + ref + ", dimension=" + dimension + ", prix=" + prix + '}';
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
