/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author medmo
 */
public class GuiAdminController implements Initializable {

    @FXML
    private Label nomPrenom3;
    @FXML
    private VBox vbox2;
    @FXML
    private Button btnNaviguer;
    @FXML
    private AnchorPane bord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToNavigate(ActionEvent event) {
    }

    @FXML
    private void goToArticles(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherArticle.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void goToCommands(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiBackofficeCommand.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void goToUsers(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher_utilisateur.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void goToLogn(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiLogin.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void goToReclamations(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Reponse.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
