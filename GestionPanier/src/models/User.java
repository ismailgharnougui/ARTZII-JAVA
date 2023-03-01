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
public class User {
    
    private int id;
    private String nom;
    private String prenom;
     private String email;
    private String address;
    private String role;
    private String pass;
   

    public User() {
    }

    public User(int id,String email, String pass) {
        this.id=id;
        this.email = email;
        this.pass = pass;
    }
    
    public User(String nom, String prenom, String email, String pass, String role, String address) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.address = address;
        this.role = role;
        this.pass = pass;
    }
public User(int id, String nom, String prenom, String email, String address, String role, String pass) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.address = address;
        this.role = role;
        this.pass = pass;
        
    }
     public User(int id, String nom, String prenom, String address) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
    }

    public User(String nom, String prenom, String address) {
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

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPass() {
        return pass;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", address=" + address + ", role=" + role + ", pass=" + pass + '}';
    }
    

    
    
}
