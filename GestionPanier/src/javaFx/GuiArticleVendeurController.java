/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import models.Article;
import models.User;
import services.ServiceArticle;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author medmo
 */
public class GuiArticleVendeurController implements Initializable {

    ServiceUser sc = new ServiceUser();
    User artiste;
    ServiceArticle sa = new ServiceArticle();

    @FXML
    private Label nomPrenom3;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox1;
    @FXML
    private Pane articlePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        artiste = sc.get(12);

        List<Article> articles = sa.getArticles(12); // to edit with current connected user
        System.out.println(articles);
        //vbox1.setFillWidth(true);

        for (Article article : articles) {
            Pane articlePane1 = new Pane();
            articlePane1.setPrefSize(450.0, 395.0);
            articlePane1.setMinHeight(360);
            articlePane1.setMaxWidth(430);
            articlePane1.setMaxHeight(360);
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
            title.setLayoutX(180.0);
            title.setLayoutY(58.0);
            title.setText(article.getNom());
            Font titleFont = new Font(20.0);
            title.setFont(titleFont);
            title.setStyle("-fx-font-weight: bold;");

            Label price = new Label();
            price.setLayoutX(280.0);
            price.setLayoutY(325.0);
            price.setStyle("-fx-font-weight: bold;");
            price.setText("PRIX : " + (float) article.getPrix() + " DT");
            Font priceFont = new Font(20.0);
            price.setFont(priceFont);

            ImageView groupIcon = new ImageView();
            groupIcon.setFitHeight(47.0);
            groupIcon.setFitWidth(43.0);
            groupIcon.setLayoutX(5.0);
            groupIcon.setPickOnBounds(true);
            groupIcon.setPreserveRatio(true);

            Image groupIconSource = new Image(getClass().getResource("../resources/ic_usr.png").toExternalForm());
            groupIcon.setImage(groupIconSource);

            Label username = new Label();
            username.setLayoutX(50.0);
            username.setText(artiste.getNom() + " " + artiste.getPrenom());
            Font usernameFont = new Font(20.0);
            username.setFont(usernameFont);

            Label dateAdded = new Label();
            dateAdded.setLayoutX(56.0);
            dateAdded.setLayoutY(30.0);
            dateAdded.setText("Today");
            dateAdded.setTextFill(Color.web("#6e6c6c"));
            Font dateFont = new Font(15.0);
            dateAdded.setFont(dateFont);

            ImageView supprimerImageView = new ImageView();
            supprimerImageView.setFitHeight(26);
            supprimerImageView.setFitWidth(35);
            supprimerImageView.setLayoutX(393);
            supprimerImageView.setLayoutY(12);
            supprimerImageView.setPickOnBounds(true);
            supprimerImageView.setPreserveRatio(true);
            supprimerImageView.setCursor(Cursor.HAND);

            Image supprimerImage = new Image(getClass().getResourceAsStream("../resources/trash.png"));
            supprimerImageView.setImage(supprimerImage);

            supprimerImageView.setOnMouseClicked(event -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirm Deletion");
                alert.setHeaderText("Are you sure you want to delete this article?");
                alert.setContentText("This action cannot be undone.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    Pane parent = (Pane) articlePane1.getParent();
                    System.out.println("here to delete");
                    // sa.deleteArticle(article.);

                    // parent.getChildren().remove(articlePane);
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

            /*
             * modifyImageView.setOnMouseClicked(event -> {
             * x=article;
             * try {
             * Parent root = null;
             * //FXMLLoader loader = new
             * FXMLLoader(getClass().getResource("GuiModifyArticle.fxml"));
             * 
             * root = FXMLLoader.load(getClass().getResource("GuiModifyArticle.fxml"));
             * 
             * Scene scene = new Scene(root);
             * 
             * // get the stage from the ImageView
             * Stage stage = (Stage) addButton.getScene().getWindow();
             * 
             * // set the new scene to the stage
             * stage.setScene(scene);
             * stage.show();
             * } catch (IOException e) {
             * e.printStackTrace();
             * }
             * 
             * 
             * });
             */

            articlePane1.getChildren().addAll(image, title, price, groupIcon, username, dateAdded, supprimerImageView,
                    modifyImageView);

            vbox1.getChildren().add(articlePane1);
            // panesList.add(articlePane);
            // panesDictionary.put(articlePane,article.getIdArticle());

        }
         // Set margin between labels
        vbox1.setSpacing(20);

    }
}
