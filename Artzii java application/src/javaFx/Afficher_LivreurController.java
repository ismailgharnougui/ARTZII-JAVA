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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CRUDLivreurs;
import models.Livreurs;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Afficher_LivreurController implements Initializable {

   private ArrayList<Livreurs> lsLivreurs = new ArrayList<>(new CRUDLivreurs().afficherlivreurs());
    
    @FXML
    private TableColumn<Livreurs, String> fxnum_tel_column;
    @FXML
    private TableColumn<Livreurs, String> fxnom_column;
    @FXML
    private TableColumn<Livreurs, String> fxprenom_column;
    @FXML
    private TableColumn<Livreurs, String> fxregion_livreur_column;
    @FXML
    private TableView<Livreurs> livreursTable;
    @FXML
    private Button fxAjouter_Livreur;
    private Livreurs selectedTableIndex;
    @FXML
    private TableColumn<?, ?> fxactions_column;
    @FXML
    private Button affecterLivreur;
    @FXML
    private TextField search_barre;
    @FXML
    private Label nomPrenom3;
    @FXML
    private VBox vbox2;
    @FXML
    private Button btnNaviguer;
    @FXML
    private AnchorPane bord;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        search_barre.setOnKeyReleased((event) -> {
            System.out.println(search_barre.getText());
            if(search_barre.getText().equals("")){
                livreursTable.setItems(FXCollections.observableArrayList(lsLivreurs));
            }else{
                livreursTable.setItems(FXCollections.observableArrayList(
                        lsLivreurs.stream().filter((t) -> {
                            return t.getNom().startsWith(search_barre.getText()) 
                                    || t.getNum_tel().startsWith(search_barre.getText())
                                    || t.getPrenom().startsWith(search_barre.getText())
                                    || t.getRegion_livreur().startsWith(search_barre.getText())
                                    ;
                        }).collect(Collectors.toList())
                ));
            }
            fxactions_column.setCellFactory(
                (param) -> {
                    TableCell tc = new TableCell(){
                     
                        HBox box;
                        Button update = new Button("modifier");
                        Button delete = new Button("supprimer");

                        @Override
                        protected void updateItem(Object item, boolean empty) {
                            super.updateItem(item, empty); 
                            if(!empty) {
                                
                                //inspect event listener
                                update.setOnAction((event) -> {
                                    Livreurs Liv = (Livreurs)getTableRow().getItem();
                                   update(Liv);
                                    System.out.println(Liv);
                                    String nom = Liv.getNom();
                                     
                                });
                                
                                delete.setOnAction((event) -> {
                                    Livreurs Liv = (Livreurs)getTableRow().getItem();
                                    int i = getTableRow().getIndex();
                                    new CRUDLivreurs().supprimerlivreurs(Liv.getId());
                                    livreursTable.getItems().remove(i);
                                    livreursTable.refresh();
                                });
                                
                                box = new HBox(update, delete);
                                setGraphic(box);
                            }
                        }
                        
                        private Stage update(Livreurs Liv) {
                            FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                                "Modifier.fxml"
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

                            ModifierController controller = loader.getController();
                            controller.initData(Liv);

                            stage.show();

                            return stage;
                        }
                        
                };
                            return tc ;
                });
        
            
        });
        
        livreursTable.setItems(FXCollections.observableArrayList(new CRUDLivreurs().afficherlivreurs()));
        fxnum_tel_column.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        fxnom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        fxprenom_column.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        fxregion_livreur_column.setCellValueFactory(new PropertyValueFactory<>("region_livreur"));
        
        fxactions_column.setCellValueFactory(new PropertyValueFactory<>("actions"));
        fxactions_column.setEditable(false);
        fxactions_column.setSortable(false);
        
        fxactions_column.setCellFactory(
                (param) -> {
                    TableCell tc = new TableCell(){
                     
                        HBox box;
                        Button update = new Button("modifier");
                        Button delete = new Button("supprimer");

                        @Override
                        protected void updateItem(Object item, boolean empty) {
                            super.updateItem(item, empty); 
                            if(!empty) {
                                
                                //inspect event listener
                                update.setOnAction((event) -> {
                                    Livreurs Liv = (Livreurs)getTableRow().getItem();
                                   update(Liv);
                                    System.out.println(Liv);
                                    String nom = Liv.getNom();
                                     
                                });
                                
                                delete.setOnAction((event) -> {
                                    Livreurs Liv = (Livreurs)getTableRow().getItem();
                                    int i = getTableRow().getIndex();
                                    new CRUDLivreurs().supprimerlivreurs(Liv.getId());
                                    livreursTable.getItems().remove(i);
                                    livreursTable.refresh();
                                });
                                
                                box = new HBox(update, delete);
                                setGraphic(box);
                            }
                        }
                        
                        private Stage update(Livreurs Liv) {
                            FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                                "Modifier.fxml"
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

                            ModifierController controller = loader.getController();
                            controller.initData(Liv);

                            stage.show();

                            return stage;
                        }
                        
                };
                            return tc ;
                });
        
        
        
        livreursTable.setRowFactory(tv -> {
		     TableRow<Livreurs> myRow = new TableRow<>();
		     myRow.setOnMouseClicked ((MouseEvent event) -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            int myIndex =  livreursTable.getSelectionModel().getSelectedIndex();
		 
		            Livreurs  liv = livreursTable.getItems().get(myIndex);
//		            fxnum_tel_column.setText(livreursTable.getItems().get(myIndex).getNum_tel());
//                            fxnom_column.setText(livreursTable.getItems().get(myIndex).getNom());
//                            fxprenom_column.setText(String.valueOf(livreursTable.getItems().get(myIndex).getPrenom()));
//                            fxregion_livreur_column.setText(String.valueOf(livreursTable.getItems().get(myIndex).getRegion_livreur()));
                            
                           this.selectedTableIndex = liv;
                         
                           
		        }
		     });
		        return myRow;
                   });
            }    

    @FXML
    private void AjouterLivreur(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter.fxml"));
Parent root = loader.load();
Scene scene = new Scene(root);
Stage stage = new Stage();
stage.setScene(scene);
stage.show();

    }

    

         public void Delete(ActionEvent event) {
        int myIndex = livreursTable.getSelectionModel().getSelectedIndex();
		 
        int id = Integer.parseInt(String.valueOf(livreursTable.getItems().get(myIndex).getId()));
                     

        CRUDLivreurs crud = new CRUDLivreurs();
        crud.supprimerlivreurs(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("suppression");
        alert.setHeaderText("suppression");
        alert.setContentText("Deletedd!");
        alert.showAndWait();
                livreursTable.setItems(FXCollections.observableArrayList(new CRUDLivreurs().afficherlivreurs()));

    }
        

    private void Modifier(ActionEvent event) throws IOException {
        
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifier.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    private void AffecterLivreur(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Livraison.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

    }

    @FXML
    private void Statistique(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistique.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
//                StatistiqueController c = loader.getController();
//                c.start();
                Stage stage = new Stage();
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
    
    
    
    
    
    
    
