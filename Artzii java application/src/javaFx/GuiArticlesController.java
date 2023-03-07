/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import javaFx.GuiLoginController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.Article;
import models.User;
import services.ServiceArticle;
import services.ServiceBasket;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author medmo
 */
public class GuiArticlesController implements Initializable {

    User connectedUser = GuiLoginController.user;

    @FXML
    private Label nomPrenom3;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox1;

    ServiceUser sc = new ServiceUser();

    ServiceArticle sa = new ServiceArticle();
    @FXML
    private Button btnPanier;
    @FXML
    private AnchorPane bord;
    @FXML
    private Button btnPanier1;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        List<Article> articles = sa.afficherArticles();

        for (Article article : articles) {
            Pane articlePane1 = new Pane();
            articlePane1.setPrefSize(450.0, 395.0);
            articlePane1.setMinHeight(360);
            articlePane1.setMaxWidth(430);
            articlePane1.setStyle("-fx-background-color: #f8f5f5; -fx-background-radius: 10; -fx-padding:10px;");

            DropShadow shadow = new DropShadow();
            shadow.setRadius(10.0);
            shadow.setColor(Color.gray(0.5));
            shadow.setOffsetX(2.0);
            shadow.setOffsetY(2.0);

            articlePane1.setEffect(shadow);

            ImageView image = new ImageView();
            image.setFitHeight(218.0);
            image.setFitWidth(287.0);
            image.setLayoutX(78);
            image.setLayoutY(90.0);
            image.setPickOnBounds(true);
            image.setPreserveRatio(true);

            Image imageSource = null;// = new Image(getClass().getResource(article.getImage_url()).toExternalForm());

            File uploadedFile = new File(article.getImageUrl());

            System.out.println("this is the file " + uploadedFile);
            String filePath = article.getImageUrl();
            try {
                // create a FileInputStream from the File object
                FileInputStream inputStream = new FileInputStream(uploadedFile);
                // create an Image object from the FileInputStream
                imageSource = new Image(inputStream);// file.toURI().toString()

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            image.setImage(imageSource);

            Label title = new Label();
            title.setLayoutX(150.0);
            title.setLayoutY(47.0);
            title.setText(article.getNom());
            title.setFont(Font.font("titleFont", FontWeight.BOLD, 23));
            title.setAlignment(Pos.CENTER);

            Label price = new Label();
            price.setLayoutX(280.0);
            price.setLayoutY(325.0);
            price.setText("PRIX : " + (float) article.getPrix() + " DT");
            price.setFont(Font.font("priceFont", FontWeight.BOLD, 20));

            ImageView groupIcon = new ImageView();
            groupIcon.setFitHeight(47.0);
            groupIcon.setFitWidth(43.0);
            groupIcon.setLayoutX(7.0);
            groupIcon.setLayoutY(7.0);
            groupIcon.setPickOnBounds(true);
            groupIcon.setPreserveRatio(true);

            Image groupIconSource = new Image(getClass().getResource("../resources/ic_usr.png").toExternalForm());
            groupIcon.setImage(groupIconSource);

            Label username = new Label();
            username.setLayoutX(57.0);
            username.setLayoutY(8.0);
            username.setText(connectedUser.getNom() + " " + connectedUser.getPrenom());
            Font usernameFont = new Font(20.0);
            username.setFont(usernameFont);

            Label dateAdded = new Label();
            dateAdded.setLayoutX(56.0);
            dateAdded.setLayoutY(30.0);
            dateAdded.setText("Today");
            dateAdded.setTextFill(Color.web("#6e6c6c"));
            Font dateFont = new Font(15.0);
            dateAdded.setFont(dateFont);

            ImageView ajouPanierImageView = new ImageView();
            ajouPanierImageView.setFitHeight(26);
            ajouPanierImageView.setFitWidth(35);
            ajouPanierImageView.setLayoutX(393);
            ajouPanierImageView.setLayoutY(12);
            ajouPanierImageView.setPickOnBounds(true);
            ajouPanierImageView.setPreserveRatio(true);
            ajouPanierImageView.setCursor(Cursor.HAND);

            Image supprimerImage = new Image(getClass().getResourceAsStream("../resources/AjoutPanier.png"));
            ajouPanierImageView.setImage(supprimerImage);

            ajouPanierImageView.setOnMouseClicked(event -> {

                ServiceBasket sb = new ServiceBasket();

                if (sb.ajouter(connectedUser.getId(), article.getRef()) == false) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Article exist");
                    alert.setHeaderText("Cette article existe déja dans le panier");
                    // alert.setContentText("This action cannot be undone.");
                    Optional<ButtonType> result = alert.showAndWait();
                } else {
                    sb.ajouter(12, article.getRef());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Ajout au panier");
                    alert.setHeaderText("Vous êtes sur d'ajouter cette article dans votre panier ?");
                    // alert.setContentText("This action cannot be undone.");
                    Optional<ButtonType> result = alert.showAndWait();

                }
            });

            ImageView modifyImageView = new ImageView();
            modifyImageView.setFitHeight(26);
            modifyImageView.setFitWidth(35);
            modifyImageView.setLayoutX(340);
            modifyImageView.setLayoutY(12);
            modifyImageView.setPickOnBounds(true);
            modifyImageView.setPreserveRatio(true);
            modifyImageView.setCursor(Cursor.HAND);

            Image modifyImage = new Image(getClass().getResourceAsStream("../resources/edit.png"));
            modifyImageView.setImage(modifyImage);

            articlePane1.getChildren().addAll(image, title, price, groupIcon, username, dateAdded, ajouPanierImageView);

            vbox1.getChildren().add(articlePane1);

        }
        // Set margin between labels
        vbox1.setSpacing(20);
        vbox1.setStyle("-fx-padding: 4px 0;");

    }

    @FXML
    private void goToPanier(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiPanier.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void goToReclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void goToLogn(MouseEvent event) {
        connectedUser = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiLogin.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void goToEvent(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("espace.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
