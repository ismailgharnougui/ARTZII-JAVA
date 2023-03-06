/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package edu.connexion3A18.entities.GUI;

import edu.connexion3A18.entities.Evenement;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author GOLDEN
 */
public class Mainpage extends Application {   
     static  Evenement ev = new Evenement();
    @Override
    public void start(Stage stage)throws Exception{
        
        try  {
            Parent root = FXMLLoader.load(getClass().getResource("espace.fxml"));
            
            
            Scene scene = new Scene(root);
             stage.setScene(scene);
            stage.show();
           
        } catch (IOException ex) {
             System.out.println(ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
