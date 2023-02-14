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
    private final double tax= 0.07;
    
    public Basket(int refClient) {
        this.refClient = refClient;
        articles = new ArrayList<>();
        totalCost = 0;
    }

    public Basket() {
        articles = new ArrayList<>();
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
        return totalCost * (1 + tax);
    }
    public double getTotalCostHT() {
        return totalCost;
    }
   
    public int getRefClient() {
        return refClient;
    }
    
    public void setRefClient(int refClient) {
        this.refClient = refClient;
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


