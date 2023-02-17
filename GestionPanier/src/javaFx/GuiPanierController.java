/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import services.*;
import models.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medmo
 */
public class GuiPanierController implements Initializable {
    
    @FXML
    private Label totalCout;
     @FXML
    private Label nomPrenom2;
     @FXML
    private Label nomPrenom3;
    @FXML
    private Label address;
     @FXML
    private VBox vbox1;
      @FXML
    private VBox vbox2;
       @FXML
    private AnchorPane bord;
         
    private StringProperty totalCoutContent= new SimpleStringProperty();
    
    private StringProperty nomPrenomContent= new SimpleStringProperty();
    
    private StringProperty addressContent= new SimpleStringProperty();

    ServiceBasket sb = new ServiceBasket();
    Basket panier;
    ServiceClient sc = new ServiceClient();
    Client client;
    @FXML
    private Label nomPrenom;
    
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         List<Article> articles = sb.get(3).getArticles();
         System.out.println(articles);
        
        
          for (Article article : articles) {
        Pane articlePane = new Pane();
        articlePane.setMinHeight(75);
        articlePane.setStyle("-fx-background-color: #f2d590; -fx-background-radius: 10; -fx-padding: 10px;");
        
        Label articleRefLabel = new Label("Ref: " + article.getRef());
        articleRefLabel.setLayoutX(50);
        articleRefLabel.setLayoutY(10);
        articleRefLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        Label articleDimensionLabel = new Label("Dimension: " + article.getDimension());
        articleDimensionLabel.setLayoutX(50);
        articleDimensionLabel.setLayoutY(30);
        articleDimensionLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        Label articlePrixLabel = new Label("Prix: " + article.getPrix());
        articlePrixLabel.setLayoutX(50);
        articlePrixLabel.setLayoutY(50);
        articlePrixLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        articlePane.getChildren().addAll(articleRefLabel, articleDimensionLabel, articlePrixLabel);
        vbox1.getChildren().add(articlePane);
    }
         // Set margin between labels
    vbox1.setSpacing(8);
    
        
        client=sc.get(3);
        
        //A remplacer avec la session
        panier=sb.get(client.getId());
        
        totalCoutContent.setValue(panier.getTotalCostHT()+" DT");
        totalCout.textProperty().bindBidirectional(totalCoutContent);
        
        nomPrenomContent.setValue(client.getPrenom()+" "+client.getNom());
        nomPrenom2.textProperty().bindBidirectional(nomPrenomContent);
        nomPrenom3.textProperty().bindBidirectional(nomPrenomContent);
        
        addressContent.setValue(client.getAddress());
        address.textProperty().bindBidirectional(addressContent);
       
    }    
    
    @FXML
    void onCommanderButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiCommand.fxml"));
        try{
            Parent root = loader.load();
            bord.getChildren().setAll(root);
           
           }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    
}
