/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entites.Utilisateur;
import java.util.List;

/**
 *
 * @author belkn
 */
public interface IUtilisateur {
    public void ajouterUtilisateur(Utilisateur U);
    public void ajouterUtilisateur2 (Utilisateur U);
    public void modifierUtilisateur(Utilisateur U);
    public void supprimerUtilisateur(int id);
    public List<Utilisateur > afficherUtilisateur ();
    
    
}
