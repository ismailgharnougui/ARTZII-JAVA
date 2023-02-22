/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import Entites.Article;
import Services.CRUDArticle;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author ismae
 */
public class Afficher_articleController implements Initializable {

    @FXML
    private ListView<Article> afficherarticle;
    @FXML
    private Button supprimer;
    @FXML
    private Button mod;
    static int refA;
    static String nomA;
    static String dimensionA;
    static int prixA;
    static int CatId;
    
    
  
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ListView<Article> list1= afficherarticle;
        CRUDArticle inter = new CRUDArticle();
        List<Article> list2 = inter.afficherArticle();
        for (int i = 0; i < list2.size(); i++) {
            Article A = list2.get(i);
            list1.getItems().add(A);

        }    }    

    @FXML
    private void supprimer_article(ActionEvent event) {
    ListView<Article> list1 = afficherarticle;
        CRUDArticle inter = new CRUDArticle();
        int selectedIndex = list1.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Article A = list1.getSelectionModel().getSelectedItem();
            System.out.println(A.getRef());
            inter.supprimerArticle(A.getRef());
            list1.getItems().remove(selectedIndex);
        } else {
            System.out.println("Veuillez sélectionner un article à supprimer.");
        }

    }

    @FXML
    private void mod(ActionEvent event) {
        
           ListView<Article> list = afficherarticle;
        CRUDArticle inter = new CRUDArticle();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        Article A = list.getSelectionModel().getSelectedItem();
        refA=A.getRef();
        nomA= A.getNom();
dimensionA=A.getDimension();
prixA=A.getPrix();
        //Prix_jour= Integer.toString(v.getPrix_jours()) ;

        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("modifierarticle.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Location_articleController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    }
    
