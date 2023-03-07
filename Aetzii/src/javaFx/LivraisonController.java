/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CRUDCommande;
import services.CRUDLivreurs;
import models.Commandes;
import models.Livreurs;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class LivraisonController implements Initializable {
    @FXML
    private TableView<Commandes> Commande_table;
    @FXML
    private TableColumn<Commandes, String> adresseCom1;
    @FXML
    private TableColumn<Commandes, String> regionCom1;
    @FXML
    private TableColumn<Commandes, Integer> useridCom1;
    private TableColumn<Commandes, String> detailsCom1;
    @FXML
    private TableColumn<?, ?> affectationCom1;
    @FXML
    private AnchorPane bord;
    @FXML
    private Label nomPrenom3;
    @FXML
    private VBox vbox2;
    @FXML
    private Button btnNaviguer;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Commande_table.setItems(FXCollections.observableArrayList(new CRUDCommande().afficherCommandes()));
        adresseCom1.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        regionCom1.setCellValueFactory(new PropertyValueFactory<>("region"));
        useridCom1.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        
        
        affectationCom1.setCellValueFactory(new PropertyValueFactory<>("actions"));
        affectationCom1.setEditable(false);
        affectationCom1.setSortable(false);
        
        affectationCom1.setCellFactory(
                (param) -> {
                    TableCell tc = new TableCell(){
                     
                        HBox box;
                        Button affect = new Button("affect");
                            
                            @Override
                        protected void updateItem(Object item, boolean empty) {
                            super.updateItem(item, empty); 
                            if(!empty) {
                                
                                //inspect event listener
                                affect.setOnAction((event) -> {
                                    Commandes comm = (Commandes)getTableRow().getItem();//1
                                    /*Livreurs Liv = (Livreurs)getTableRow().getItem();
                                   affect(comm);
                                    System.out.println(comm);*/
                                    ArrayList<Livreurs> ls = new ArrayList<>(new CRUDLivreurs().afficherlivreurs());//2
                                    
                                    String region = comm.getRegion();
                                    ls = new ArrayList(ls.stream().filter(l->l.getRegion_livreur().equals(region))
                                      .collect(Collectors.toList()));//3
				
                                    //String region_liv = Liv.getRegion_livreur();
                                     //comm.setLivreur_id(ls.get(0));
                                    if (ls.isEmpty())
                                        System.out.println("mafamech livreur fl region hethika");
                                    else
                                    {
                                        
                                        new CRUDCommande().affecterLivreur(comm, ls.get(0));
                                        sendSMS.sendSMS(ls.get(0));
                                    }//4
                                });
                                 box = new HBox(affect);
                                 
                                setGraphic(box);
                            }
                        }
                        
                        private Stage affect(Commandes comm) {
                            FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                                "Afficher_Livreur.fxml"
                            )
                            );
                            Stage stage = new Stage();
                            try {
                                stage.setScene(
                                        new Scene(loader.load())
                                );
                            } catch (IOException ex) {
                               Logger.getLogger(Afficher_LivreurController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                            stage.show();

                            return stage;
                            }
                    };
                            return tc ;
                });
                            
                            
                }        

    @FXML
    private void Retour(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Livreur.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void goToNavigate(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiAdmin.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
