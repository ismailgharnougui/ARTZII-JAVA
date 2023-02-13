/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.Objects;

/**
 *
 * @author belkn
 */

/*public enum UserRole {
    ADMIN("admin"),
    ARTIST("artist"),
    CLIENT("client");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}*/
public class Utilisateur {
    private int idU;
    private String nomU,prenomU,emailU,mdpU,roleU;

    public Utilisateur(int idU, String nomU, String prenomU, String emailU,String mdpU,String roleU) {
        this.idU = idU;
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.emailU = emailU;
        this.mdpU = mdpU;
        this.roleU =roleU;
    }

    public Utilisateur(String nomU, String prenomU,String emailU,String mdpU,String roleU) {
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.emailU = emailU;
        this.mdpU = mdpU;
          this.roleU = roleU;
    }
    public Utilisateur() {
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "idU=" + idU + ", nomU=" + nomU + ", prenomU=" + prenomU + ", emailU=" + emailU +", mdpU=" + mdpU + ", roleU=" + roleU + '}';
    }

    public int getidU() {
        return idU;
    }

    public String getnomU() {
        return nomU;
    }

    public String getprenomU() {
        return prenomU;
    }
     public String getemailU() {
        return emailU;
    }
      public String getmdpU() {
        return mdpU;
    }
        public String getroleU() {
        return roleU;
    }

    public void setidU(int idU) {
        this.idU = idU;
    }

    public void setnomU(String nomU) {
        this.nomU = nomU;
    }

    public void setprenomU(String prenomU) {
        this.prenomU = prenomU;
    }
      public void setemailU(String emailU) {
        this.emailU = emailU;
    }
        public void setmdpU(String mdpU) {
        this.mdpU = mdpU;
    }
 public void setroleU(String roleU) {
        this.roleU =roleU;
    }


   
    
    
    
}
