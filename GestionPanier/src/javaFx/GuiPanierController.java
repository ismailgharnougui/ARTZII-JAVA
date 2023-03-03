/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import javaFx.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import services.*;
import models.*;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author medmo
 */
public class GuiPanierController implements Initializable {
  User connectedUser = GuiLoginController.user;
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

    private final StringProperty totalCoutContent = new SimpleStringProperty();

    private final StringProperty nomPrenomContent = new SimpleStringProperty();

    private final StringProperty addressContent = new SimpleStringProperty();

    ServiceBasket sb = new ServiceBasket();
    Basket panier;
    ServiceUser sc = new ServiceUser();
    User client;
    @FXML
    private Label nomPrenom;
    @FXML
    private ImageView supprimer;
    @FXML
    private Button btnNaviguer;
    @FXML
    private Pane panePPPPP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Article> articles = sb.get(connectedUser.getId()).getArticles();
        System.out.println(articles);

        for (Article article : articles) {

            Pane pane = new Pane();
            pane.setLayoutX(11);
            pane.setPrefHeight(154);
            pane.setPrefWidth(470);
            pane.setStyle("-fx-background-color:EBC45C; -fx-background-radius: 10;");

            ImageView imageView = new ImageView();
            imageView.setFitHeight(138);
            imageView.setFitWidth(177);
            imageView.setLayoutX(14);
            imageView.setLayoutY(9);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);

            Image imageSource = null;
            File uploadedFile = new File(article.getImageUrl());
            try {
                // create a FileInputStream from the File object
                FileInputStream inputStream = new FileInputStream(uploadedFile);
                // create an Image object from the FileInputStream
                imageSource = new Image(inputStream);// file.toURI().toString()

            } catch (FileNotFoundException e) {
            }
            imageView.setImage(imageSource);

            Label artworkLabel = new Label();
            artworkLabel.setLayoutX(213);
            artworkLabel.setLayoutY(38);
            artworkLabel.setPrefHeight(31);
            artworkLabel.setPrefWidth(188);
            artworkLabel.setText(article.getNom());
            artworkLabel.setStyle("-fx-font-weight: bold;-fx-font-style: italic;");

            Label artistLabel1 = new Label();
            artistLabel1.setLayoutX(213);
            artistLabel1.setLayoutY(63);
            artistLabel1.setPrefHeight(31);
            artistLabel1.setPrefWidth(100);
            artistLabel1.setText("Dimension :");
            artistLabel1.setStyle("-fx-font-weight: bold;");

            Label artistLabel2 = new Label();
            artistLabel2.setLayoutX(310);
            artistLabel2.setLayoutY(63);
            artistLabel2.setPrefHeight(31);
            artistLabel2.setPrefWidth(112);
            artistLabel2.setText(article.getDimension() + " cm");

            Font artistFont2 = Font.font(15);
            artistLabel2.setFont(artistFont2);

            Label prixLabel1 = new Label();
            prixLabel1.setLayoutX(376);
            prixLabel1.setLayoutY(125);
            prixLabel1.setPrefHeight(31);
            prixLabel1.setPrefWidth(42);
            prixLabel1.setText("Prix :");

            prixLabel1.setStyle("-fx-font-weight: bold;");

            Label dateLabel = new Label();
            dateLabel.setLayoutX(212);
            dateLabel.setLayoutY(87);
            dateLabel.setPrefHeight(31);
            dateLabel.setPrefWidth(89);
            dateLabel.setText("Ajouté le : ");

            dateLabel.setStyle("-fx-font-weight: bold;");

            Label prixLabel2 = new Label();
            prixLabel2.setLayoutX(415);
            prixLabel2.setLayoutY(125);
            prixLabel2.setPrefHeight(31);
            prixLabel2.setPrefWidth(100);
            prixLabel2.setText("" + article.getPrix() + " DT");

            prixLabel2.setStyle("-fx-font-weight: bold;");

            Label dateValueLabel = new Label();
            dateValueLabel.setLayoutX(291);
            dateValueLabel.setLayoutY(87);
            dateValueLabel.setPrefHeight(31);
            dateValueLabel.setPrefWidth(124);
            dateValueLabel.setText("12 janvier 2023");

            Font dateValueFont = Font.font(15);
            dateValueLabel.setFont(dateValueFont);

            ImageView supprimerImageView = new ImageView();
            supprimerImageView.setFitHeight(26);
            supprimerImageView.setFitWidth(35);
            supprimerImageView.setLayoutX(450);
            supprimerImageView.setLayoutY(12);
            supprimerImageView.setPickOnBounds(true);
            supprimerImageView.setPreserveRatio(true);

            Image supprimerImage = new Image(getClass().getResourceAsStream("../resources/trash.png"));
            supprimerImageView.setImage(supprimerImage);
            supprimerImageView.setOnMouseClicked(event -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmer la suppression");
                alert.setHeaderText("Êtes-vous sûr de supprimer cet article de votre panier ?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    Pane parent = (Pane) pane.getParent();
                    sb.supprimerArticle(connectedUser.getId(), article.getRef());
                    panier.getArticles().remove(article);
                    parent.getChildren().remove(pane);
                }
                totalCoutContent.setValue(panier.getTotalCostHT() + " DT");
            });

            pane.getChildren().addAll(imageView, artworkLabel, artistLabel1, artistLabel2, prixLabel1, dateLabel,
                    prixLabel2, dateValueLabel, supprimerImageView);
            vbox1.getChildren().add(pane);
        }

        // Set margin between labels
        vbox1.setSpacing(8);

        // A remplacer avec la session
        panier = sb.get(connectedUser.getId());

        totalCoutContent.setValue(panier.getTotalCostHT() + " DT");
        totalCout.textProperty().bindBidirectional(totalCoutContent);

        nomPrenomContent.setValue(connectedUser.getPrenom() + " " + connectedUser.getNom());
        nomPrenom2.textProperty().bindBidirectional(nomPrenomContent);
        nomPrenom3.textProperty().bindBidirectional(nomPrenomContent);

        addressContent.setValue(connectedUser.getAddress());
        address.textProperty().bindBidirectional(addressContent);

    }

    @FXML
    void onCommanderButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiCommand.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void goToNavigate(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiArticles.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}