/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import Entites.Utilisateur;
import Services.CRUDUtilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class ModifierutilisateurController implements Initializable {
  private ListView<Utilisateur> afficherarticle;
    @FXML
    private TextField fx_nom;
    @FXML
    private TextField fx_prenom;
    @FXML
    private TextField fx_email;
    @FXML
    private TextField fx_mdp;
    @FXML
    private Button modifier_utilisateur;
    @FXML
    private TextField fx_role;
    @FXML
    private TextField fx_adresse;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
fx_nom.setText(Afficher_utilisateurController.nomU);
        fx_prenom.setText(Afficher_utilisateurController.prenomU);
        fx_email.setText(Afficher_utilisateurController.emailU);
        fx_mdp.setText(Afficher_utilisateurController.mdpU);
        fx_role.setText(Afficher_utilisateurController.roleU);
        fx_adresse.setText(Afficher_utilisateurController.adresse);

               }    

    @FXML
    private void modifier_utilisateur(ActionEvent event) {
           CRUDUtilisateur inter = new CRUDUtilisateur();
        String nomU = fx_nom.getText();        
        String prenomU = fx_prenom.getText();
        String emailU = fx_email.getText();
        String mdpU = fx_mdp.getText();
        String roleU = fx_role.getText();
        String adresse = fx_adresse.getText();
      
        Utilisateur U = new Utilisateur(Afficher_utilisateurController.idU,nomU, prenomU,emailU, mdpU,roleU,adresse);
        inter.modifierUtilisateur(U);
    
    }
    
}

  
