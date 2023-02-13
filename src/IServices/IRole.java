/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;
import Entites.Role;
import java.util.List;

/**
 *
 * @author marie
 */
public interface IRole {
     public void ajouterRole(Role R);
    public void ajouterRole2 (Role R);
    public void modifierRole(Role R);
    public void supprimerRole(String roleU);
    public List<Role> afficherRole ();
    
}
