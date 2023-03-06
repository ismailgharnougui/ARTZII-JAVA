/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Reponse;
import java.util.List;


public interface RepInterface {
    public void ajouterReponse(Reponse Rep);
    public void modifierReponse(Reponse Rep);
    public void supprimerReponse(int idRep);
    public List<Reponse> afficherReponse();
    
    
}
