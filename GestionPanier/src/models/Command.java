/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author medmo
 */
public class Command {
    private int id;
    private Client cl;
    private ArrayList<Article> articles;
    private String comDate;
    private double totalCost;
    private String payMethod;

    public Command() {
        articles = new ArrayList<>();
    }

    public Command(int id, Client cl, String comDate, double totalCost, String payMethod) {
        articles = new ArrayList<>();
        this.id = id;
        this.cl=cl;
        this.comDate = comDate;
        this.totalCost = totalCost;
        this.payMethod = payMethod;
    }

    public Command(Client cl, ArrayList<Article> articles, String comDate, double totalCost, String payMethod) {
        this.cl=cl;
        this.articles = articles;
        this.comDate = comDate;
        this.totalCost = totalCost;
        this.payMethod = payMethod;
    }
    

    public int getId() {
        return id;
    }

    public Client getCl() {
        return cl;
    }

    public String getComDate() {
        return comDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCl(Client cl) {
        this.cl = cl;
    }

    

    public void setComDate(String comDate) {
        this.comDate = comDate;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    @Override
    public String toString() {
        return "Command{" + "id=" + id + ", cl=" + cl + ", articles=" + articles + ", comDate=" + comDate + ", totalCost=" + totalCost + ", payMethod=" + payMethod + '}';
    }

    

   public void addArticle(Article article) {
        articles.add(article);
        totalCost += article.getPrix();
    }
    
    
}
