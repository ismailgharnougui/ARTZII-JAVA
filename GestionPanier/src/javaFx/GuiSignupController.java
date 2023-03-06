/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.User;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author medmo
 */
public class GuiSignupController implements Initializable {

    @FXML
    private ImageView btnReturn;
    @FXML
    private AnchorPane bord;
    @FXML
    private Button btnSignup;
    @FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private TextField adresseTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confirmPasswordTextField;
    @FXML
    private RadioButton roleArtiste;
    @FXML
    private ToggleGroup roleUser;
    @FXML
    private RadioButton roleClient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void returnTo(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiHome.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

     @FXML
private void signup(ActionEvent event) { 
    
        ServiceUser su = new ServiceUser();
        String roleUtilisateur = null;
        
    // Récupérer les valeurs des champs de texte
    String nom = nomTextField.getText();
    String prenom = prenomTextField.getText();
    String email = emailTextField.getText();
    String adresse = adresseTextField.getText();
    String password = passwordTextField.getText();
    String confirmPassword = confirmPasswordTextField.getText();

    // Initialiser les messages d'erreur à vide
    String nomError = "";
    String prenomError = "";
    String emailError = "";
    String adresseError = "";
    String passwordError = "";
    String confirmPasswordError = "";

    // Vérifier si chaque champ est vide et afficher une erreur correspondante si c'est le cas
    if (nom.isEmpty()) {
        nomError = "Le nom est requis";
    }
    if (prenom.isEmpty()) {
        prenomError = "Le prénom est requis";
    }
    if (email.isEmpty()) {
        emailError = "L'email est requis";
    }
    if (adresse.isEmpty()) {
        adresseError = "L'adresse est requise";
    }
     // Check password length
    if (password.length() < 8) {
         passwordError = "Le mot de passe doit contenir au moins 8 caractères";
    }

    if (password.isEmpty()) {
        passwordError = "Le mot de passe est requis";
    }
    if (confirmPassword.isEmpty()) {
        confirmPasswordError = "La confirmation du mot de passe est requise";
    }

    // Vérifier si l'adresse email est valide et afficher une erreur si elle ne l'est pas
    if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
        emailError = "L'adresse email est invalide";
    }
    
     
    // Vérifier si le mot de passe et sa confirmation correspondent et afficher une erreur si ce n'est pas le cas
    if (!password.equals(confirmPassword)) {
        confirmPasswordError = "Les mots de passe ne correspondent pas";
    }
    
    // set the user role
    if(roleArtiste.isSelected()){ roleUtilisateur = "Artiste"; }
    if(roleClient.isSelected()){ roleUtilisateur = "Client"; }

    // Si aucune erreur n'a été détectée, créer un nouvel utilisateur et l'ajouter à la base de données
    if (nomError.isEmpty() && prenomError.isEmpty() && emailError.isEmpty() && adresseError.isEmpty() && passwordError.isEmpty() && confirmPasswordError.isEmpty()) {
        User user = new User(nom, prenom, email, password, roleUtilisateur , adresse);
        
        
        try {
            boolean exist = su.ajouterUser(user);
            if (!exist){
            System.out.println("existant");
            }
            else{
            su.ajouterUser(user);
            // Afficher un message de confirmation si l'inscription a réussi
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inscription réussie");
            alert.setHeaderText(null);
            alert.setContentText("Votre inscription a été enregistrée avec succès.");
             Optional<ButtonType> result2 = alert.showAndWait();

            if (result2.get() == ButtonType.OK){
                returnTo(null);
            }
            
            }
            
        } catch (SQLException e) {
            // Afficher un message d'erreur si une exception SQL se produit
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'inscription");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'inscription. Veuillez réessayer plus tard.");
            alert.showAndWait();
        }
    } else {
        // Afficher les messages d'erreur correspondants aux champs de texte vides ou invalides
        nomLabel.setText(nomError);
        prenomLabel.setText(prenomError);
        emailLabel.setText(emailError);
        adresseLabel.setText(adresseError);
        passwordLabel.setText(passwordError);
        confirmPasswordLabel.setText(confirmPasswordError);
    }
}
}
