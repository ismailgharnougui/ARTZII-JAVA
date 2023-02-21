/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worsho.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author 21628
 */
public class firstwindow extends Application {
    private String typeUser;
    
    
    @Override
    public void start(Stage primaryStage) {
       // typeUser="user";
        typeUser="admin";
        try {
         if(typeUser.compareTo("user")==0){
           Parent root = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
        Scene scene = new Scene(root, 905, 400);
                    
                    primaryStage.setTitle("Artzii");
                    primaryStage.setScene(scene);
                    primaryStage.show();
         }else{
           Parent root = FXMLLoader.load(getClass().getResource("Reponse.fxml"));
        Scene scene = new Scene(root, 905, 400);
                    
                    primaryStage.setTitle("Artzii");
                    primaryStage.setScene(scene);
                    primaryStage.show();
         
         }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
