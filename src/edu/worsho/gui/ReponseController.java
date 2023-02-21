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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 21628
 */
public class ReponseController implements Initializable {

    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<?, ?> id;
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
    private Pane addreponseform;
    @FXML
    private Button btnvalider;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnRepondre;
    @FXML
    private TextArea contenuRep;
    @FXML
    private Button btnafficherreponse;
    @FXML
    private Pane reponsePane;
    @FXML
    private Label reponselabel;
    @FXML
    private Button btnModifierRep;
    private Reponse SelectedReponse;

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
                Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
               
                if(recSelected!=null){
              btnsupprimer.setDisable(false);
              if(recSelected.getEtat().compareTo("nonresolu")==0){
              btnRepondre.setDisable(false);
              btnafficherreponse.setDisable(true);
              }else{
                            btnRepondre.setDisable(true);
                  btnafficherreponse.setDisable(false);

              }
                }else{
                btnsupprimer.setDisable(true);
              btnRepondre.setDisable(true);
              btnafficherreponse.setDisable(true);

                }
            }
           
        });

    }    
       public void Refresh(){
           CRUDReclamation rc  = new CRUDReclamation();
         ObservableList<Reclamation> list = rc.afficherReclamation();
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
    private void ouvrirRepondreForm(ActionEvent event) {
                reponsePane.setVisible(false);

        addreponseform.setVisible(true);
    }

    @FXML
    private void saveReponse(ActionEvent event) {
                               Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
                                          CRUDReclamation rc  = new CRUDReclamation();

CRUDReponse rp = new CRUDReponse();
Reponse r = new Reponse(contenuRep.getText(),recSelected.getId());
rp.ajouterReponse(r);
recSelected.setEtat("resolu");
rc.modifierReclamation(recSelected);
contenuRep.setText("");

addreponseform.setVisible(false);
 Refresh();
 
    }

    @FXML
    private void afficherReponse(ActionEvent event) {
        addreponseform.setVisible(false);
        reponsePane.setVisible(true);
        Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
        CRUDReponse rp = new CRUDReponse();
        SelectedReponse = rp.getReponseByIdReclamation(recSelected.getId());
        reponselabel.setText(SelectedReponse.getContenuRep());
        
    }

    @FXML
    private void supprimerReponse(ActionEvent event) {
               CRUDReponse rp = new CRUDReponse();
rp.supprimerReponse(SelectedReponse.getIdRep());
   CRUDReclamation rc  = new CRUDReclamation();
   Reclamation r= rc.getReclamationById(SelectedReponse.getIdReclmation());
   r.setEtat("nonresolu");
   rc.modifierReclamation(r);
   Refresh();
  reponsePane.setVisible(false);

   
    }

    @FXML
    private void ModifierRep(ActionEvent event) {
    }
}
