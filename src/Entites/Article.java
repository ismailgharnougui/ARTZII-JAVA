/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author belkn
 */
public class Article{
    private int refA;
    private String nomA,dimensionA,image;
    private int prixA ,CatId;
    private Date date;
    

    public Article(int refA, String nomA, String dimensionA ,String image ,int prixA,Date date,int CatId) {
        this.refA = refA;
        this.nomA = nomA;
        this.dimensionA = dimensionA;
        this.image=image;
        this.prixA=prixA;
        this.CatId=CatId;
        this.date=date;
    }

public Article(String nomA, String dimensionA ,String image ,int prixA,Date date,int CatId) {
        this.nomA = nomA;
        this.dimensionA = dimensionA;
        this.image=image;
        this.prixA=prixA;
        this.CatId=CatId;
        this.date=date;
    }

    public Article() {
    }

    public Article(String nomA, String dimensionA, int prixA, int catId) {
        this.nomA = nomA;
        this.dimensionA = dimensionA;
        this.prixA=prixA;
        this.CatId=CatId;    }

    public Article(int refA, String nomA, String dimensionA, int prixA, int catId) {
   this.refA = refA;
        this.nomA = nomA;
        this.dimensionA = dimensionA;
        this.prixA=prixA;
        this.CatId=CatId;
    }

  
    public String toString() {
        return "Article{" + "Réference article=" + refA + ", nom d'article=" + nomA + ", dimension=" + dimensionA +"image"+image+"prix d'article"+prixA+ "Date"+date+"Categorie"+CatId+ '}';
    }

    public int getRef() {
        return refA;
    }

    public String getNom() {
        return nomA;
    }

    public String getDimension() {
        return dimensionA;
    }
    public int getPrix(){
        return prixA;
    }
    public int getCatId(){
        return CatId;
    }
    public Date getDate(){
        return date;
    }
       public String getImage() {
        return image;
    }
    public void setRef(int refA) {
        this.refA = refA;
    }

    public void setNom(String nomA) {
        this.nomA = nomA;
    }

    public void setDimension(String dimensionA) {
        this.dimensionA = dimensionA;
    }
public void setPrix(int prixA){
    this.prixA=prixA;
}
public void setCatId(int CatId){
    this.CatId=CatId;
}
 public void setDate(Date date){
     this.date=date;
 }       


    public void setImage(String image) {
        this.image = image;
    }
}
