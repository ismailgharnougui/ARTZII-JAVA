package javaFx;

import models.Categorie;
import models.Artiicle;
import services.ServiceCategorie;
import services.ServiceArtiicle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI GF63
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private TextField libtx;
    @FXML
    private TextField desctv;
    @FXML
    private TextField dispotv;
    @FXML
    private Button btnAjouter;
    
    @FXML
    private TextField prixtv;

    Artiicle article = new Artiicle();
    ServiceArtiicle ss = new ServiceArtiicle();
    @FXML
    private ImageView imagev;
    @FXML
    private AnchorPane nh;
    @FXML
    private Label file_path;
    @FXML
    private ComboBox<String> cat_cb;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        //GET CATREGORIES LISTE DEROULANTE FOR JOIN !
                        ObservableList<String>list = FXCollections.observableArrayList();
                        ServiceCategorie sc = new ServiceCategorie();
                        
                        
                      
                        ObservableList<Categorie>obList = FXCollections.observableArrayList();
                        obList =sc.afficherCategorie();

        cat_cb.getItems().clear();
        
        for(Categorie nameCat : obList) {
            System.out.println("hii");
            list.add(nameCat.getCatLib());
                        System.out.println("hii"+list);

                    cat_cb.setItems(list);

        }
        // TODO
    }

    @FXML
    private void ajoutArticleHandle(ActionEvent event) {
        String ArtLib = libtx.getText();
        String ArtDesc = desctv.getText();
        int dispo = Integer.parseInt(dispotv.getText());
        int prix = Integer.parseInt(prixtv.getText());
        
        
     
        
        

        if (prix < 0) {
          
            
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez entrer un prix supérieur à 0 !");
            alert.show();
            
            
        } else if (ArtLib.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez entrer libellé !");
            alert.show();
        } else if (ArtDesc.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez entrer description !");
            alert.show();

     
        } else if (dispo < 0) {
            System.out.println(dispo);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("erreur donner un dispo entre 0 ");
            alert.show();

        } else if (dispo > 1) {
            System.out.println(dispo);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("erreur donner un dispo 1");
            alert.show();

        } else {
            ss.ajouterArticle(new Artiicle(ArtLib, ArtDesc, dispo, file_path.getText(), prix,cat_cb.getValue()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Article ajouté avec succés !");
            alert.showAndWait();
        }
    }

    @FXML
    private void uploadimageHandler(MouseEvent event) {
        FileChooser open = new FileChooser();
        Stage stage = (Stage) nh.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if (file != null) {
            String path = file.getName();
            file_path.setText(path);
            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            imagev.setImage(image);
        } else {
            System.out.println("NO DATA EXIST!");
        }
    }

    @FXML
    private void ajoutArticleHandle(MouseEvent event) {
    }

    @FXML
    private void ajoutArticleHandler(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("AfficherArticle.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
