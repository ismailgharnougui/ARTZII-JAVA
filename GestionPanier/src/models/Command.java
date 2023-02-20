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
    private int idClient;
    private String comDate;
    private float totalCost;
    private String payMethod;
    private String livMethod;

    public Command() {
    }

    public Command(int idClient, float totalCost, String payMethod, String livMethod) {
        this.idClient = idClient;
        this.totalCost = totalCost;
        this.payMethod = payMethod;
        this.livMethod = livMethod;
    }
    
    
    

    public int getId() {
        return id;
    }
    public int getIdClient() {
        return idClient;
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

    public String getLivMethod() {
        return livMethod;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public void setComDate(String comDate) {
        this.comDate = comDate;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void setLivMethod(String livMethod) {
        this.livMethod = livMethod;
    }

    @Override
    public String toString() {
        return "Command{" + "id=" + id + ", idClient=" + idClient + ", comDate=" + comDate + ", totalCost=" + totalCost + ", payMethod=" + payMethod + ", livMethod=" + livMethod + '}';
    }
    

    

    
}
