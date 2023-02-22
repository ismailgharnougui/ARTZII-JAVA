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
 * @author ismae
 */


public class ModifierarticleController implements Initializable {
    private ListView<Article> afficherarticle;

    @FXML
    private TextField fx_nom;
    @FXML
    private TextField fx_dimension;
    @FXML
    private TextField fx_prix;
    @FXML
    private TextField fx_catid;
    @FXML
    private Button modifier_article;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 fx_nom.setText(Afficher_articleController.nomA);
        fx_dimension.setText(Afficher_articleController.dimensionA);
        fx_prix.setText(Integer.toString(Afficher_articleController.prixA));
        fx_catid.setText(Integer.toString(Afficher_articleController.CatId));
            }    

    @FXML
    private void modifier_article(ActionEvent event) {  
        CRUDArticle inter = new CRUDArticle();
        String nomA = fx_nom.getText();        
        String dimensionA = fx_dimension.getText();
        int prixA = Integer.parseInt(fx_prix.getText());
        int catId = Integer.parseInt(fx_catid.getText());
        Article A = new Article(Afficher_articleController.refA,nomA, dimensionA,prixA, catId);
        inter.modifierArticle(A);
    }
    
}
