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
    private int idArtiste;
    private String nom;
    private String dimension;
    private float prix;
    private String imageUrl;

    public Article() {
    }
            
    public Article(int idArtiste, String nom, String dimension, float prix, String imageUrl) {
        this.idArtiste = idArtiste;
        this.nom = nom;
        this.dimension = dimension;
        this.prix = prix;
        this.imageUrl = imageUrl;
    }
     public Article(String nom, String dimension, float prix) {
        this.nom=nom;
        this.dimension = dimension;
        this.prix = prix;
    }
     
    
     public Article(int ref,int idArtiste,String nom,String dimension, float prix,String imageUrl) {
         this.ref = ref;
         this.idArtiste = idArtiste;
        this.nom=nom;
        this.dimension = dimension;
        this.prix = prix;
        this.imageUrl=imageUrl;
    }

    public int getRef() {
        return ref;
    }

    public int getIdArtiste() {
        return idArtiste;
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

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }

    @Override
    public String toString() {
        return "Article{" + "ref=" + ref + ", idArtiste=" + idArtiste + ", nom=" + nom + ", dimension=" + dimension + ", prix=" + prix + ", imageUrl=" + imageUrl + '}';
    }
    
}
