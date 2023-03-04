/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Article;
import Service.ServiceArticle;
import Utils.PDFExporter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ismae
 */
public class AfficherArticleController implements Initializable {
  
    @FXML
    private TableView<Article> tableview;
   
    ObservableList<Article>obList ;
    ServiceArticle a;
Button btn;
Button btnEx;
Button btnModi;
Article A=new Article();
    private TableColumn<Article, Void> colModifBtn;
    private TableColumn<Article, Void> colSuppBtn;    
    private TableColumn<Article, Void> colExpBtn;

    @FXML
    private TableColumn<Article, String > ft_servlib;
    @FXML
    private TableColumn<Article, Integer> ft_servdispo;
    @FXML
    private TableColumn<Article, Integer> ft_prix;
    
    @FXML
    private TableColumn<Article, String > ft_servcatlib;
    @FXML
    private TableColumn<Article, String> ft_servdesc;
    @FXML
    private TextField search_tv;
    @FXML
    private Button btnAjouter;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         colSuppBtn = new TableColumn<>("Supprimer");
            tableview.getColumns().add(colSuppBtn);    
            
             colModifBtn = new TableColumn<>("Modifier");
            tableview.getColumns().add(colModifBtn);   
            
            
             colExpBtn = new TableColumn<>("Exporter");
            tableview.getColumns().add(colExpBtn);   
                


        a= new  ServiceArticle();
       obList= a.afficherArticle();
       
            ft_servlib.setCellValueFactory(new PropertyValueFactory<>("ArtLib"));

               ft_prix.setCellValueFactory(new PropertyValueFactory<>("ArtPrix"));
               ft_servdesc.setCellValueFactory(new PropertyValueFactory<>("ArtDesc"));
 
                              ft_servdispo.setCellValueFactory(new PropertyValueFactory<>("ArtDispo"));
                              ft_servcatlib.setCellValueFactory(new PropertyValueFactory<>("CatLib"));

       
   
                      tableview.setItems(obList);


            addButtonModifToTable();
            
       
            addButtonDeleteToTable();
            
            //APPEL 
            addButtonPDFToTable();
     
    }    
    public void Afficher(){
        tableview.refresh();
            obList.clear();

          //  obList   = a.getArticleByCategorie();
       obList= a.afficherArticle();
 
            
          ft_servcatlib.setCellValueFactory(new PropertyValueFactory<>("ArtLib"));
                              ft_servdispo.setCellValueFactory(new PropertyValueFactory<>("ArtDesc"));      
                              ft_servcatlib.setCellValueFactory(new PropertyValueFactory<>("CatLib"));


       
             
            tableview.setItems(obList);
    }
    public void addButtonModifToTable() {


        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> cellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {

                final TableCell<Article, Void> cell = new TableCell<Article, Void>() {


                    { 

                       btn = new Button("Modifier");

                        btn.setOnAction((ActionEvent event) -> {
                            
               try {
                   
                                 
                              A = tableview.getSelectionModel().getSelectedItem();
                              
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/modifierArticle.fxml"));
        Parent root = loader.load();
        ModifierArticleController controller = loader.getController();
        controller.setLabel(A.getArtLib()); 
  controller.setDesc(A.getArtDesc()); 
  controller.setPrix(String.valueOf(A.getArtPrix()));
  controller.setDispo(String.valueOf(A.getArtDispo()));
  controller.setId(A.getArtId());
  controller.setImg(A.getArtImg());
  controller.setCatLib(A.getCatLib());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
                              
                              
               }catch(Exception e) {
                   e.printStackTrace();
               }
           
                                                                
                            
                        });
                        
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);  
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;    
            }
        };

        colModifBtn.setCellFactory(cellFactory);


        
        
        
   
      }
    
    Button btnSupprimer;
    
      
         public void addButtonDeleteToTable() {
              


        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> cellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {

                final TableCell<Article, Void> cell = new TableCell<Article, Void>() {


                    { 

                        btnSupprimer = new Button("Supprimer");
                         btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {
                            
                             
                              A = tableview.getSelectionModel().getSelectedItem();
                              showConfirmation(A);
                              
                        });

                   
                        
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);  
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnSupprimer);
                        }
                    }
                };
                return cell;    
            }
        };

        colSuppBtn.setCellFactory(cellFactory);


        
        
        
   
      }
         
         
         
         
         
         
         //EXPORT PDF BUTTON
         Button btnExport; 
         public void addButtonPDFToTable() {
              


        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> cellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {

                final TableCell<Article, Void> cell = new TableCell<Article, Void>() {


                    { 

                        btnExport = new Button("Exporter");
                         btnExport = new Button("Exporter");
                        btnExport.setOnAction((ActionEvent event) -> {
                            
                             
                            try {
                                PDFExporter.exportToPDF(tableview, "Servicesfile");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                              
                        });

                   
                        
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);  
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnExport);
                        }
                    }
                };
                return cell;    
            }
        };

        colExpBtn.setCellFactory(cellFactory);


        
        
        
   
      }

         
         
         
         
         
         
         
         
            private Label label;
private void showConfirmation(Article A) {
        
 
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Suppression");
      alert.setHeaderText("Voullez vous vraiment supprimer??");
      alert.setContentText("Article peut etre effacer");
 
      // option != null.
      Optional<ButtonType> option = alert.showAndWait();
 
      if (option.get() == null) {
         this.label.setText("pas selection!");
      } else if (option.get() == ButtonType.OK) {
          System.out.println(A.getArtId());
          a.supprimerArticle(A);
          obList.clear();
          Afficher();
      } else if (option.get() == ButtonType.CANCEL) {
         this.label.setText("Exit!");
      } else {
         this.label.setText("-");
      }
   }




//Metier function search
 public void filter() {  
        FilteredList<Article> filteredList = new FilteredList<>(obList,b->true);
        
        search_tv.textProperty().addListener((observable,oldValue,newValue) ->{
                    
        if(search_tv.getText().isEmpty()) {
            //Reload button
            addButtonModifToTable();
            addButtonDeleteToTable();
            addButtonPDFToTable();
            
        
        }
            filteredList.setPredicate(reclamation-> {
                if(newValue == null || newValue.isEmpty()) {
                                       btn=new Button("Modifier");
                                       btnEx = new Button("Exporter");
                                       btnModi = new Button("Supprimer");

                    return true;
                }
                
                //
                String lowerCaseFilter =newValue.toLowerCase();
                
                if(reclamation.getArtLib().toLowerCase().indexOf(lowerCaseFilter)!=-1 ) {

                    return true;
                }
                else if(String.valueOf(reclamation.getArtDispo()).toLowerCase().indexOf(lowerCaseFilter)!=-1) {

                    return true;
                }
  else if(String.valueOf(reclamation.getArtPrix()).toLowerCase().indexOf(lowerCaseFilter)!=-1) {

                    return true;
                }
               
               
               
                else{
                                        
 btn=new Button("Modifier");
                                       btnEx = new Button("Exporter");
                                       btnModi = new Button("Supprimer");
                    return false;
                }

            });
            
        });

        
        SortedList<Article> sortedData= new SortedList<>(filteredList);
        
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());


        tableview.setItems(sortedData);
        
        

    }

//METIER SEARCH
    @FXML
    private void searchquery(KeyEvent event) {
     
        filter();
    }

    @FXML
    private void gotoAjouter(ActionEvent event) throws IOException {
        
        Parent page1 = FXMLLoader.load(getClass().getResource("AjouterArticle.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
}

