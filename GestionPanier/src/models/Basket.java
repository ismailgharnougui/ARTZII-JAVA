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

   import java.util.ArrayList;

public class Basket {
    private int refClient;
    private ArrayList<Article> articles;
    private double totalCost;
    private String dateAjout;
    private final double tax= 0.07;
    
     public Basket() {
        articles = new ArrayList<>();
    }
    public Basket(int refClient) {
        this.refClient = refClient;
        articles = new ArrayList<>();
        totalCost = 0;
    }

    public Basket(int refClient, String dateAjout) {
        this.refClient = refClient;
        this.dateAjout = dateAjout;
    }
    
    public void addArticle(Article article) {
        articles.add(article);
        totalCost += article.getPrix();
    }
   
    public void removeArticle(Article article) {
        articles.remove(article);
        totalCost -= article.getPrix();
    } 
    
    public ArrayList<Article> getArticles() {
        return articles;
    }
    
    public double getTotalCostTTC() {
        return articles.stream().mapToDouble(x->x.getPrix()).sum()* (1 + tax);
    }
    public double getTotalCostHT() {
        return articles.stream().mapToDouble(x->x.getPrix()).sum();
    }
   
    public int getRefClient() {
        return refClient;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getDateAjout() {
        return dateAjout;
    }
    
    
    
    public void setRefClient(int refClient) {
        this.refClient = refClient;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }
    
    
    public double getTax() {
        return tax;
    }
    

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Basket{" + "refClient=" + refClient + ", articles=" + articles + ", totalCost=" + totalCost + ", tax=" + tax + '}';
    }

    
    
    
}


