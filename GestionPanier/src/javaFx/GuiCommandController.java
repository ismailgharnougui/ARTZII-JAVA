/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import models.Article;
import models.Basket;
import models.Client;
import models.Command;
import services.ServiceBasket;
import services.ServiceClient;
import services.ServiceCommand;

/**
 * FXML Controller class
 *
 * @author medmo
 */
public class GuiCommandController implements Initializable {

    
    @FXML
    private RadioButton livArtziiNow;
    @FXML
    private RadioButton livDomicile;
    @FXML
    private AnchorPane bord;
    @FXML
    private Label nomPrenom;
    @FXML
    private VBox vbox2;
    @FXML
    private Label nomPrenom2;
    @FXML
    private VBox vbox1;
    @FXML
    private Label address1;
    @FXML
    private ToggleGroup modeLivraison;
    @FXML
    private ToggleGroup modePayement;
    @FXML
    private Label sousTotale;
    @FXML
    private Label totalCommand;
    
    ServiceBasket sb = new ServiceBasket();
    Basket panier;
    ServiceClient sc = new ServiceClient();
    Client client;
    ServiceCommand scom = new ServiceCommand();
    Command command;
    
    private StringProperty sousTotaleContent= new SimpleStringProperty();
    private StringProperty totalCommandContent= new SimpleStringProperty();
    private StringProperty nomPrenomContent= new SimpleStringProperty();
    private StringProperty addressContent= new SimpleStringProperty();

    
   public int pos;
    @FXML
    private Button confirmerCommande;
    @FXML
    private Button modifierAddress;
    @FXML
    private RadioButton payCash;
    @FXML
    private RadioButton payEnLigne;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        client=sc.get(4);
       
        // TODO  
        pos=1;
         List<Article> articles = sb.get(4).getArticles();
         System.out.println(articles);
         vbox1.setFillWidth(true);
       
          for (Article article : articles) {
              
             Pane pane = new Pane();
             pane.setMinHeight(79);
             pane.setMaxHeight(79);
pane.setStyle("-fx-background-color: ffffff; -fx-background-radius: 0;");

Label indexLabel = new Label("Article "+pos+" de "+ articles.size());
indexLabel.setLayoutX(19.0);
indexLabel.setLayoutY(-2.0);
indexLabel.setPrefHeight(31.0);
indexLabel.setPrefWidth(120.0);
indexLabel.setFont(Font.font("System Bold Italic", 15.0));
indexLabel.setStyle("-fx-font-weight: bold;");

Label titleLabel = new Label(""+article.getNom());
titleLabel.setLayoutX(50.0);
titleLabel.setLayoutY(23.0);
titleLabel.setPrefHeight(31.0);
titleLabel.setPrefWidth(112.0);
titleLabel.setFont(Font.font(15.0));

Label deliveryLabel = new Label("Prix :");
deliveryLabel.setLayoutX(25.0);
deliveryLabel.setLayoutY(46.0);
deliveryLabel.setPrefHeight(31.0);
deliveryLabel.setPrefWidth(342.0);
deliveryLabel.setFont(Font.font(15.0));
deliveryLabel.setStyle("-fx-font-weight: bold;");


Label priceLabel = new Label(article.getPrix()+" DT");
priceLabel.setLayoutX(67.0);
priceLabel.setLayoutY(46.0);
priceLabel.setPrefHeight(31.0);
priceLabel.setPrefWidth(100.0);

Label quantityLabel = new Label("1x");
quantityLabel.setLayoutX(29.0);
quantityLabel.setLayoutY(23.0);
quantityLabel.setPrefHeight(31.0);
quantityLabel.setPrefWidth(24.0);
quantityLabel.setFont(Font.font("System Bold Italic", 15.0));
quantityLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #ffffff;");


Line line = new Line(-139.00001525878906, 1.3999786376953125, 350.0, 1.3999786376953125);
line.setStrokeWidth(1.5);
line.setLayoutX(30.0);
line.setLayoutY(78.0);

pane.getChildren().addAll(indexLabel, titleLabel, deliveryLabel, priceLabel, quantityLabel, line);
 vbox1.getChildren().add(pane);
 pos++; 
          }
          vbox1.setSpacing(0.2);
          
           sousTotaleContent.setValue(sb.get(4).getTotalCostHT()+"");
        sousTotale.textProperty().bindBidirectional(sousTotaleContent);
        
         totalCommandContent.setValue((sb.get(4).getTotalCostHT()+7)+"");
        totalCommand.textProperty().bindBidirectional(totalCommandContent);
        
        nomPrenomContent.setValue(client.getNom()+" "+client.getPrenom());
        nomPrenom.textProperty().bindBidirectional(nomPrenomContent);
        nomPrenom2.textProperty().bindBidirectional(nomPrenomContent);
        
        addressContent.setValue(client.getAddress());
        address1.textProperty().bindBidirectional(addressContent);
        
        //Ajout dans le commande
        //command.setIdClient(4);
        
         }

    @FXML
    private void ajouterCommande(ActionEvent event) throws IOException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmer la commande");
    alert.setHeaderText("Êtes-vous sûr de confirmer cette  commande ?");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK){
          Command command = new Command();
           client=sc.get(4);
           
         
           //insertion dans la commande
            command.setIdClient(4);
          command.setTotalCost((float)sb.get(4).getTotalCostHT()+7);
          
          FXMLLoader loader = new FXMLLoader(getClass().getResource("your_fxml_file.fxml"));
          
          
          //checking livraison method
          if (livArtziiNow.isSelected()) {
                
                command.setLivMethod("Artzii now");
        } else if (livDomicile.isSelected()) {
            command.setLivMethod("Livraison domicile");
        } else {
            System.out.println("No option selected");
        }
            
            
           //cheking paiement method
            if (payCash.isSelected()) {
                
                command.setPayMethod("Cash");
        } else if (payEnLigne.isSelected()) {
            command.setPayMethod("En ligne");
        } else {
            System.out.println("No option selected");
        }
            
          System.out.println(command);
          
          scom.ajouter(command);
         
    }
         
    }
     
    
    
}
