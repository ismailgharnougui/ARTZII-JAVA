package GUI;

import Entities.Categorie;
import Service.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Ajouter_categorieController implements Initializable {

    @FXML
    private Button btnAjouter;
    @FXML
    private AnchorPane nh;
    @FXML
    private TextField libtx;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterCategorie(ActionEvent event) {
        String CatLib = libtx.getText();

        if (CatLib.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur !! Veuillez entrer un libellé ! ");
            alert.show();
        } else {
            Categorie c = new Categorie(CatLib);
            ServiceCategorie SC = new ServiceCategorie();
            SC.ajouterCategorie(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Categorie ajouté avec succés!");
            alert.show();
        }
    }

    @FXML
    private void ajoutCategorieHandler(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("afficher_categorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
