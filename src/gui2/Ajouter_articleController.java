/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import Entites.Article;
import Services.CRUDArticle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


/**
 * FXML Controller class
 *
 * @author ismae
 */
public class Ajouter_articleController implements Initializable {

    @FXML
    private TextField fx_nomA;
    @FXML
    private TextField fx_dimensionA;
    @FXML
    private TextField fx_prixA;
    @FXML
    private TextField fx_catId;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {

        String nomA = fx_nomA.getText();
        String dimensionA = fx_dimensionA.getText();
        int prixA = Integer.parseInt(fx_prixA.getText());
        int catId = Integer.parseInt(fx_catId.getText());
         if(prixA<0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur le prix est negatif");
        alert.show();
        }else if(nomA.length()==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner un nom d article");
        alert.show();
        }else if (dimensionA.length()==0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner un dimension d article");
        alert.show();
        }/*else if (catId!=(1,2,3){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur donner un catID");
        alert.show();
        }*/else {
        Article A = new Article(nomA, dimensionA, prixA, catId);
        CRUDArticle crud = new CRUDArticle();
        crud.ajouterArticle3(A);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Article insérée avec succés!");
        alert.show();
    }
    }

}
