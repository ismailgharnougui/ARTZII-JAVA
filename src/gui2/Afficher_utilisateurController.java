/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import Entites.Utilisateur;
import Services.CRUDUtilisateur;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;



import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class Afficher_utilisateurController implements Initializable {

    @FXML
    private ListView<Utilisateur> afficherutilisateur;
    @FXML
    private Button supprimer;
    @FXML
    private Button mod;
 static int idU;
    static String nomU;
    static String prenomU;
      static String emailU;
    static String mdpU;
    static String roleU;
        static String adresse;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
ListView<Utilisateur> list1= afficherutilisateur;
        CRUDUtilisateur inter = new CRUDUtilisateur();
        List<Utilisateur> list2 = inter.afficherUtilisateur();
        for (int i = 0; i < list2.size(); i++) {
            Utilisateur A = list2.get(i);
            list1.getItems().add(A);

        }      }    

    @FXML
    private void supprimer_utilisateur(ActionEvent event) {
        ListView<Utilisateur> list1= afficherutilisateur;
        CRUDUtilisateur inter = new CRUDUtilisateur();
        int selectedIndex = list1.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Utilisateur A = list1.getSelectionModel().getSelectedItem();
            System.out.println(A.getidU());
            inter.supprimerUtilisateur(A.getidU());
            list1.getItems().remove(selectedIndex);
        } else {
            System.out.println("Veuillez sélectionner un utilisateur à supprimer.");
        }
    }

    @FXML
    private void mod(ActionEvent event) {
             ListView<Utilisateur> list1= afficherutilisateur;
        CRUDUtilisateur inter = new CRUDUtilisateur();
        int selectedIndex = list1.getSelectionModel().getSelectedIndex();
        Utilisateur A = list1.getSelectionModel().getSelectedItem();
       

 idU=A.getidU();
 nomU=A.getnomU();
 prenomU=A.getprenomU();
 emailU=A.getprenomU();
 emailU=A.getemailU();
 mdpU=A.getmdpU();
 roleU=A.getroleU();
 adresse=A.getadresse();
  

        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("modifierutilisateur.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Location_utilisateurController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}



   
