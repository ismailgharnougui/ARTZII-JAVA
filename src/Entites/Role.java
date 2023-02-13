/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author marie
 */
public class Role {
     private String roleU;
     public Role( String roleU) {
        this.roleU = roleU;
     }
     public Role() {
    }
      public String toString() {
        return "Role{" + "roleU=" + roleU +  '}';
    }
       public String getroleU() {
        return roleU;
    }
        public void setroleU(String roleU) {
        this.roleU =roleU;
    }
        
}
