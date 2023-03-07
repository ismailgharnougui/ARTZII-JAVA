/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.util.List;
import pidev.entities.Commandes; //ki tbadel l commandes mteek tansech taamel l import 
import pidev.entities.Livreurs;

/**
 *
 * @author hp
 */
public interface InterfacesServices {
    
    

/**
 *
 * @author hp
 */

    
    public void ajouterlivreurs(Livreurs liv);

    public void supprimerlivreurs(int id);
    
    
    public List<Livreurs> afficherlivreurs();
    
    
    public void modifierlivreurs (Livreurs liv);


    
    
    
    };



