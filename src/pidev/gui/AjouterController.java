/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pidev.DAO.CRUDLivreurs;
import pidev.entities.Livreurs;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterController implements Initializable {

    
    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxPrenom;
    @FXML
    private TextField fxRegion;
    @FXML
    private Button fxAjout;
    @FXML
    private TextField fxNumTel;
    @FXML
    private Label fxNom_Label;
    @FXML
    private Label fxPrenom_Label;
    @FXML
    private Label fxNum_tel_Label;
    @FXML
    private Label fxRegion_Label;
    @FXML
    private TextField erreurfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void AddLivreurs(ActionEvent event) {
       
        if (fxNom.getText().isEmpty() || fxPrenom.getText().isEmpty() || fxRegion.getText().isEmpty()  || fxNumTel.getText().isEmpty()) {
         erreurfx.setText("Tous les champs sont obligatoires.");
    } else if (!fxNom.getText().matches("[a-z A-Z]+")) {
        erreurfx.setText("Le nom est invalide.");
    }else if (!fxPrenom.getText().matches("[a-z A-Z]+")) {
        erreurfx.setText("Le prenom  est invalide.");
    }else if (!fxRegion.getText().matches("[a-z A-Z]+")) {
        erreurfx.setText("La region est invalide.");
    } 
    else if (!fxNumTel.getText().matches("[0-9]{8}")) {
        erreurfx.setText("Les numero de telephone doit avoir 8 chiffre");
    }
    else {
        
        String nom=fxNom.getText();
        String prenom=fxPrenom.getText();
        String num_tel=fxNumTel.getText();
        String region_livreur=fxRegion.getText();
        
        Livreurs liv2 = new Livreurs( num_tel, nom, prenom,region_livreur);
        CRUDLivreurs crud = new CRUDLivreurs();
        crud.ajouterlivreurs(liv2);
        
    }

    }
    
    @FXML
    private void Retour(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Livreur.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
}
