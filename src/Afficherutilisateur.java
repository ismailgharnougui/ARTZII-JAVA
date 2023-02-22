import Entites.Utilisateur;
import IServices.IUtilisateur;
import Services.CRUDUtilisateur;
import gui2.UtilisateurController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author khmir
 */
public class AfficherController implements Initializable {

    private ListView<Utilisateur> afficherutilisateur;
    private TextField supsup;
    
    static String nomU;
    static String prenomU;
    static String emailU;
    static String mdpU;
    static String roleU;
    static String adresse;
    @FXML
    private TextField ftnomU;
    @FXML
    private TextField ftprenomU;
    @FXML
    private TextField ftemailU;
    @FXML
    private TextField ftmdpU;
    @FXML
    private TextField ftadresse;
    @FXML
    private TextField ftroleU;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Utilisateur> list1 = afficherutilisateur;
        IUtilisateur inter = new CRUDUtilisateur();
        List<Utilisateur> list2 = inter.afficherUtilisateur();
        for (int i = 0; i < list2.size(); i++) {
            Utilisateur U = list2.get(i);
            list1.getItems().add(U);

        }

        // TODO
    }


    private void mod(ActionEvent event, ListView<Utilisateur> afficher) {
         ListView<Utilisateur> list = afficher;
        IUtilisateur inter = new CRUDUtilisateur();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        Utilisateur U = list.getSelectionModel().getSelectedItem();
        nomU=U.getnomU();
        prenomU= U.getprenomU(); 
        emailU =U.getemailU();
       mdpU=U.getmdpU();
       roleU=U.getroleU();
       adresse=U.getadresse();

        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("modifierutilissateur.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    



}
public class Afficherutilisateur implements Initializable {

    @FXML
    private ListView<Utilisateur> afficherutilisateur;
    @FXML
    private Button supprimer;
    private TextField supsup;
    @FXML
    private Button mod;
  
    static String nomU;
    static String prenomU;
    static String emailU;
    static String mdpU;
  static String roleU;
    static String adresse;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Utilisateur> list1 = afficherutilisateur;
        CRUDUtilisateur inter = new CRUDUtilisateur();
        List<Utilisateur> list2 = inter.afficherUtilisateur();
        for (int i = 0; i < list2.size(); i++) {
            Utilisateur U = list2.get(i);
            list1.getItems().add(U);

        }

        // TODO
    }

    @FXML
    private void supprimerutilisateur(ActionEvent event) {
                ListView<Utilisateur> list = afficherutilisateur;
        CRUDUtilisateur inter = new CRUDUtilisateur();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Utilisateur U = list.getSelectionModel().getSelectedItem();
            System.out.println(U.getidU());
            inter.supprimerUtilisateur(U.getidU());
            list.getItems().remove(selectedIndex);
        } else {
            System.out.println("Veuillez sélectionner un utilisateur à supprimer.");
        }
    }

    @FXML
    private void mod(ActionEvent event) {
       
}