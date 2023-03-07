/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
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
 * @author hp
 */
public class PIDevSoloInit extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try{
        root=FXMLLoader.load(getClass().getResource("Afficher_Livreur.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Les Livreurs!");
        primaryStage.setScene(scene);
        primaryStage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
        ex.printStackTrace();
    }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}