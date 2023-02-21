/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worsho.gui;

import edu.worshop.entites.Reclamation;
import edu.worshop.entites.Reponse;
import edu.worshop.services.CRUDReclamation;
import edu.worshop.services.CRUDReponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 21628
 */
public class ReclamationController implements Initializable {

   
    @FXML
    private TextField tfTypeR;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfObjet;
    @FXML
    private Button btnValider;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<?, ?> TypeR;
    @FXML
    private TableColumn<?, ?> dateR;
    @FXML
    private TableColumn<?, ?> etat;
    @FXML
    private TableColumn<?, ?> Description;
    @FXML
    private TableColumn<?, ?> Objet;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnmodifier;
    private String actionForm="ajouter";
    private int idUser=1;
    @FXML
    private Label reponsevalue;
    @FXML
    private Button btnafficherReponse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     Refresh();
     ObservableList selectedCells = tableReclamation.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                reponsevalue.setText("");
                
                Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
               
                if(recSelected!=null){
                    if(recSelected.getEtat().compareTo("resolu")!=0){
              btnSupprimer.setDisable(false);
              btnmodifier.setDisable(false);
              btnafficherReponse.setVisible(false);
                    }else{
                  btnSupprimer.setDisable(true);
                  btnmodifier.setDisable(true);
                  btnafficherReponse.setVisible(true);

                    }
              
                }else{
                btnSupprimer.setDisable(true);
              btnmodifier.setDisable(true);
               btnafficherReponse.setVisible(false);


                }
            }
           
        });

       
    }    

    @FXML
    private void saveReclamation(ActionEvent event) {
            CRUDReclamation rc  = new CRUDReclamation();

       if(actionForm.compareTo("ajouter")==0){
    String TypeR =tfTypeR.getText();
    String Description =tfDescription.getText();
    String objet =tfObjet.getText();
    String etat ="nonresolu";
    
   Reclamation r = new Reclamation(0,TypeR,Description,objet,etat,idUser);
    rc.ajouterReclamation(r);
        Refresh();

       }else{
                   Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();

recSelected.setTypeR(tfTypeR.getText());
recSelected.setDescription(tfDescription.getText());
recSelected.setObjet(tfObjet.getText());
System.out.print(recSelected);
rc.modifierReclamation(recSelected);
actionForm="ajouter";
         Refresh();
      
       }
       tfTypeR.setText("");
tfDescription.setText("");
tfObjet.setText("");


    }
    public void Refresh(){
           CRUDReclamation rc  = new CRUDReclamation();
         ObservableList<Reclamation> list = rc.afficherReclamation(idUser);
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
       TypeR.setCellValueFactory(new PropertyValueFactory<>("TypeR"));
         dateR.setCellValueFactory(new PropertyValueFactory<>("DateR"));
         etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
         Objet.setCellValueFactory(new PropertyValueFactory<>("Objet"));
       
         tableReclamation.setItems(list);
         
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {
                        Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
           CRUDReclamation rc  = new CRUDReclamation();
rc.supprimerReclamation(recSelected.getId());
    Refresh();

    }

    @FXML
    private void remplirModifierform(ActionEvent event) {
        this.actionForm="modifier";
        Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
tfTypeR.setText(recSelected.getTypeR());
tfDescription.setText(recSelected.getDescription());
tfObjet.setText(recSelected.getObjet());

       
        
    }

    @FXML
    private void AfficherReponse(ActionEvent event) {
                Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
               CRUDReponse rp = new CRUDReponse();
              Reponse r= rp.getReponseByIdReclamation(recSelected.getId());
               reponsevalue.setText(r.getContenuRep());
               
    }
    
}
