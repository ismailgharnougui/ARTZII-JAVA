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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class ModifierutilisateurController implements Initializable {
  private TableView<Utilisateur> afficherutilisateure;
    @FXML
    private TextField fx_nom;
    @FXML
    private TextField fx_prenom;
    @FXML
    private TextField fx_email;
    @FXML
    private TextField fx_mdp;
    @FXML
    private Button modifier_utilisateur;
    @FXML
    private TextField fx_role;
    @FXML
    private TextField fx_adresse;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
 FXMLLoader loader = new FXMLLoader();
            
           loader.setLocation(getClass().getResource("/GUI/AfficherService.fxml"));
            Stage prStage = new Stage();
            
            Parent root;
        try {
            root = loader.load();
             Scene scene = new Scene(root);
            prStage.setScene(scene);
            Afficher_utilisateurController irc = loader.getController();
            CRUDUtilisateur sp = new CRUDUtilisateur();
            int id = irc.U.getidU();
            
            fx_nom.setText(irc.U.getnomU());
            
        } catch (IOException ex) {
            Logger.getLogger(ModifierutilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
      public void setnomU(String nomU) {
        fx_nom.setText(nomU);
    }
      
      public void setprenomU(String prenomU) {
          fx_prenom.setText(prenomU);
      }
      public void setemailU(String emailU) {
          fx_email.setText(emailU);
      }
       public void setmdpU(String mdpU) {
        fx_mdp.setText(mdpU);
    }
        public void setroleU(String roleU) {
        fx_role.setText(roleU);
    }
         public void setadresse(String adresse) {
        fx_adresse.setText(adresse);
    }
      
  
      
      int idS;
      public void setId(int id) {
          
          idS = id;
          System.out.println("her id "+idS);
      }
    
    @FXML
    private void ajoutServiceHandle(MouseEvent event) {
    }


               

    


  

  

   
          
     
   


    @FXML
    private void modifHAndle(ActionEvent event) {
        
      try{
        
       
        CRUDUtilisateur ss = new CRUDUtilisateur();
        
        Utilisateur s = new Utilisateur();
        s.setnomU(fx_nom.getText());
  
       
       s.setprenomU(fx_prenom.getText());
               s.setidU(idS);
               s.setroleU(fx_role.getText());
                              s.setmdpU(fx_mdp.getText());
               s.setemailU(fx_email.getText());
               s.setadresse(fx_adresse.getText());

             
               
               
               
        ss.modifierUtilisateur(s);
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
   alert.setHeaderText("!!! Modification effectuer avec suucces !!!");
   alert.setContentText("succes");
   alert.showAndWait();
   
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Afficher_utilisateur.fxml"));
        Stage prStage = new Stage();
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
   
      }catch(Exception e) {
          e.printStackTrace();
      }
    }

    
    String img="";
    void setImg(String servImg) {
    img = servImg;
    
    }

    String catLib="";
    void setCatLib(String catLiB) {
        catLib = catLiB;
    }

    @FXML
    private void gotoServices(ActionEvent event) throws IOException {
   
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/AfficherService.fxml"));
        Stage prStage = new Stage();
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
}
