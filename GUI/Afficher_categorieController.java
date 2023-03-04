package GUI;

import Entities.Categorie;
import Service.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Afficher_categorieController implements Initializable {

    @FXML
    private ListView<Categorie> afficherCategorie;
    @FXML
    private Button supprimerCategorie;
    @FXML
    private Button modifierCategorie;
    @FXML
    private Button ajouterCategorie;

    static String CatLib;
    static int CatId;
    @FXML
    private Button btnRetour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Categorie> list1 = afficherCategorie;
        ServiceCategorie inter = new ServiceCategorie();
        List<Categorie> list2 = inter.afficherCategorie();
        for (int i = 0; i < list2.size(); i++) {
            Categorie A = list2.get(i);
            list1.getItems().add(A);
        }
    }

    @FXML
    private void ajouter_categorie(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("ajouter_categorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void modifier_categorie(ActionEvent event) {
        ListView<Categorie> list = afficherCategorie;
        ServiceCategorie inter = new ServiceCategorie();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une catégorie à modifier !!");
            alert.showAndWait();
        }

        Categorie A = list.getSelectionModel().getSelectedItem();
        CatId = A.getCatId();
        CatLib = A.getCatLib();

        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("modifiercategorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void supprimer_categorie(ActionEvent event) {
        ListView<Categorie> list1 = afficherCategorie;
        ServiceCategorie inter = new ServiceCategorie();
        int selectedIndex = list1.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Categorie A = list1.getSelectionModel().getSelectedItem();
            System.out.println(A.getCatId());
            inter.supprimerCategorie(A);
            list1.getItems().remove(selectedIndex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("catégorie supprimé avec succés !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un categorie à supprimer!");
            alert.showAndWait();
        }
    }

    @FXML
    private void RetourHandleButton(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("Location.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
