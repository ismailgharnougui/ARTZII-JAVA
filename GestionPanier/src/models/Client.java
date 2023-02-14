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
public class Client {
    
    private int id;
    private String nom;
    private String prenom;
    private String address;

    public Client() {
    }

    public Client(int id, String nom, String prenom, String address) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
    }

    public Client(String nom, String prenom, String address) {
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
    }
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", address=" + address + '}';
    }

    
    
    
}
