/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import services.*;
import javaFx.GuiLoginController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.Article;
import models.User;

/**
 * FXML Controller class
 *
 * @author medmo
 */
public class GuiAjoutArticleController implements Initializable {
    private String imageUrl = "";
    User connectedUser = GuiLoginController.user;

    @FXML
    private Label nomPrenom3;
    @FXML
    private VBox vbox2;
    @FXML
    private Button btnPanier;
    @FXML
    private ImageView articleImage;
    @FXML
    private Button browseButton;
    @FXML
    private TextField articleName;
    @FXML
    private TextField dimensionTextField;
    @FXML
    private Label nomLabel;
    @FXML
    private Label dimensionLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private Button btnAjout;
    @FXML
    private TextField prixTextField;
    @FXML
    private Label imageLabel;
    @FXML
    private AnchorPane bord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void onBrowseButtonClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(browseButton.getScene().getWindow());
        if (selectedFile != null) {

            // selectedFileField.setText(selectedFile.getAbsolutePath());
            // Read the contents of the file into a byte array

            File uploadedFile = new File(selectedFile.getAbsolutePath());
            String filePath = selectedFile.getAbsolutePath();
            try {
                // create a FileInputStream from the File object
                FileInputStream inputStream = new FileInputStream(uploadedFile);
                // create an Image object from the FileInputStream
                Image image = new Image(inputStream);// file.toURI().toString()

                articleImage.setImage(image);

            } catch (FileNotFoundException e) {
            }
            // set image path
            imageUrl = filePath;
        }
    }

    @FXML
    public void ajouterArticle(ActionEvent event) {
        System.out.println("button clicked");
        
        ServiceArticle sa = new ServiceArticle();

        // Récupérer les valeurs des champs de texte
        String nom = articleName.getText();
        String dimension = dimensionTextField.getText();
        String prix = prixTextField.getText();

        // Initialiser les messages d'erreur à vide
        String nomError = "";
        String dimensionError = "";
        String prixError = "";

        // Vérifier si chaque champ est vide et afficher une erreur correspondante si
        // c'est le cas
        if (nom.isEmpty()) {
            nomError = "Le nom est requis";
        }
        if (dimension.isEmpty()) {
            dimensionError = "La dimension est requise";
        }
        if (prix.isEmpty()) {
            prixError = "Le prix est requis";
        }
        
        // Si aucune erreur n'a été détectée, créer un nouvele nouvelle article et l'ajouter à la base de données
        if (nomError.isEmpty() && dimensionError.isEmpty() && prixError.isEmpty() ) {
            Article a = new Article(connectedUser.getId(), nom, dimension, Float.parseFloat(prix), imageUrl);
            sa.ajouter(a);
            System.out.println(a);

           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Article ajouté");
            alert.setHeaderText("L'article que vous a créez a été ajouté par succés");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
                  FXMLLoader loader = new
                  FXMLLoader(getClass().getResource("GuiArticleVendeur.fxml"));
                  try {
                  Parent root = loader.load();
                  bord.getChildren().setAll(root);
                 
                 } catch (IOException ex) {
                 System.out.println(ex);
                  }
                 
            }
        } else {
            // Afficher les messages d'erreur correspondants aux champs de texte vides ou invalides
            nomLabel.setText(nomError);
            dimensionLabel.setText(dimensionError);
            prixLabel.setText(prixError);
        }
    }

    @FXML
    private void goToPanier(ActionEvent event) {
    }
}
