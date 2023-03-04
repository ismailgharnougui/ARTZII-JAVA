/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import Entites.Utilisateur;
import Services.CRUDUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class Ajouter_utilisateurController implements Initializable {

    @FXML
    private TextField fx_nom;
    @FXML
    private TextField fx_prenom;
    @FXML
    private TextField fx_email;
    @FXML
    private TextField fx_mdp;
    @FXML
    private Button ajouter;
    @FXML
    private TextField fx_role;
    @FXML
    private TextField fx_adresse;
    @FXML
    private Button btnAfficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {

         String nomU =fx_nom.getText();
        String prenomU = fx_prenom.getText();
              String emailU = fx_email.getText();
        String mdpU = fx_mdp.getText();
        String roleU = fx_role.getText();
        String adresse = fx_adresse.getText();

         if(nomU.length()==0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner un nom d utilisateur");
        alert.show();
        }else if(prenomU.length()==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner un prenom d utilisateur");
        alert.show();
        }else if (emailU.indexOf("@")==-1){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner un email valide");
        alert.show();
        
        }else if (mdpU.length()==0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner un mot de passe");
        alert.show();
        }else if (roleU.length()==0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner role");
        alert.show();
        }else if (adresse.length()==0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner une adresse");
        alert.show();
        
        }else {
        Utilisateur U = new Utilisateur(nomU, prenomU, emailU, mdpU,roleU,adresse);
        CRUDUtilisateur crud = new CRUDUtilisateur();
        crud.ajouterUtilisateur(U);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur crée avec succés!");
        alert.show();
    }
    }
    
    @FXML
    private void gotoAfficher(ActionEvent event) throws IOException {
        
        Parent page1 = FXMLLoader.load(getClass().getResource("/gui2/afficher_utilisateur.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    }
    
