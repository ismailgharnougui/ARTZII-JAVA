/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Categorie;
import Entities.Article;
import Service.ServiceCategorie;
import Service.ServiceArticle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ismae
 */
    public class ModifierArticleController implements Initializable {

    @FXML
    private TextField libtx;
    
    @FXML
    private TextField prixtv;
    @FXML
    private TextField desctv;
    @FXML
    private ComboBox<String> dispotv;
    @FXML
    private ImageView imagev;
    @FXML
    private Label file_path;
    @FXML
    private Button btnMod;
    @FXML
    private ComboBox<String> cat_cb;

    /**
     * Initializes the controller class.
     */
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
                            
           FXMLLoader loader = new FXMLLoader();
            
           loader.setLocation(getClass().getResource("/GUI/AfficherService.fxml"));
            Stage prStage = new Stage();
            
            Parent root;
        try {
            root = loader.load();
             Scene scene = new Scene(root);
            prStage.setScene(scene);
            AfficherArticleController irc = loader.getController();
            ServiceArticle sp = new ServiceArticle();
            int id = irc.A.getArtId();
            
            libtx.setText(irc.A.getArtLib());
            
        } catch (IOException ex) {
            Logger.getLogger(ModifierArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        
        // TODO
        }
    }

    
      public void setLabel(String name) {
        libtx.setText(name);
    }
 
      public void setDesc(String de) {
          desctv.setText(de);
      }
      
      public void setDispo(String dis) {
          
                    List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
            ObservableList obList = FXCollections.observableList(list);
        dispotv.getItems().clear();
        dispotv.setItems(obList);
      }
      public void setPrix(String pri) {
          prixtv.setText(pri);
      }
      
      
      int idS;
      public void setId(int id) {
          
          idS = id;
          System.out.println("her id "+idS);
      }
    
    @FXML
    private void ajoutArticleHandle(MouseEvent event) {
    }



    @FXML
    private void uploadimageHandler(MouseEvent event) {
    }

    @FXML
    private void modifHAndle(ActionEvent event) {
        
      try{
        
       
        ServiceArticle ss = new ServiceArticle();
        
        Article s = new Article();
       s.setArtPrix(Integer.parseInt(prixtv.getText()));
        s.setArtDesc(desctv.getText());
  
        dispotv.setValue("select");
        
        if (dispotv.getValue()=="select") {
           s.setArtDispo(0);
        }
       
        else {
                 s.setArtDispo(Integer.parseInt(dispotv.getValue().toString()));
   
        }
        
               s.setArtId(idS);
               s.setArtLib(libtx.getText());
               
               s.setArtImg(img);
               s.setCatLib(catLib); // RUNI w jarb
               
               
               
        ss.modifierArticle(s);
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
   alert.setHeaderText("!!! Modification effectuer avec suucces !!!");
   alert.setContentText("succes");
   alert.showAndWait();
   
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/AfficherArticle.fxml"));
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
    private void gotoArticles(ActionEvent event) throws IOException {
   
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/AfficherArticle.fxml"));
        Stage prStage = new Stage();
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
}

