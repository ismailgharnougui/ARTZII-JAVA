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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI GF63
 */
public class modifiercategorie implements Initializable {

    @FXML
    private TextField libtx;
    @FXML
    private Button btnRetour;
    @FXML
    private Button botModif;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modifier_categorie(ActionEvent event) {
        ServiceCategorie SC = new ServiceCategorie();
        String CatLib = libtx.getText();

        Categorie c = new Categorie(Afficher_categorieController.CatId, CatLib);
        SC.modifierCategorie(c);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success Message");
        alert.setHeaderText(null);
        alert.setContentText("Catégorie modifié avec succés !");
        alert.showAndWait();
    }

    @FXML
    private void RetourHandleButton(ActionEvent event) {
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
