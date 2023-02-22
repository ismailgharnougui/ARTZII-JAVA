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
    private String imageUrl;

    public Article() {
    }
    
    public Article(String nom, String dimension, float prix) {
        this.nom=nom;
        this.dimension = dimension;
        this.prix = prix;
    }
    
     public Article(int ref,String nom,String dimension, float prix,String imageUrl) {
         this.ref = ref;
        this.nom=nom;
        this.dimension = dimension;
        this.prix = prix;
        this.imageUrl=imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Article{" + "ref=" + ref + ", nom=" + nom + ", dimension=" + dimension + ", prix=" + prix + ", imageUrl=" + imageUrl + '}';
    }
    
    
}
