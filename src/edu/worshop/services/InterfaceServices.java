/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.services;

import edu.worshop.entites.Reclamation;
import java.util.List;


public interface InterfaceServices {
    public void ajouterReclamation(Reclamation r);
    public void modifierReclamation(Reclamation r);
    public void supprimerReclamation(int id);
    public List<Reclamation> afficherReclamation();
    
    
}
