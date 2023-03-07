/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pidev.DAO.CRUDCommande;
import pidev.entities.Commandes;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class StatistiqueController implements Initializable {

    private BarChart<String, Number> Statique;
    @FXML
    private AnchorPane chartHolder;
      private ArrayList<Commandes> lsComm = new ArrayList<>(new CRUDCommande().afficherCommandes());

    /**
     * Initializes the controller class.
     */
    
    
    public void start() {
        System.out.println(lsComm);
        System.out.println(lsComm.stream().filter((t) -> {
                    return t.isLivraison(); //To change body of generated lambdas, choose Tools | Templates.
                }).count());
        // Données des commandes à livrer et des commandes non livrées
     
        
        
        ObservableList<BarChart.Data<String, Number>> livreesData = FXCollections.observableArrayList(
           new BarChart.Data<>("Janvier", lsComm.stream().filter((t) -> {
                    return t.isLivraison(); //To change body of generated lambdas, choose Tools | Templates.
                }).count()),
                
                new BarChart.Data<>("Fevrier", lsComm.stream().filter((t) -> {
                    return t.isLivraison(); //To change body of generated lambdas, choose Tools | Templates.
                }).count()),
                
                new BarChart.Data<>("Mars", lsComm.stream().filter((t) -> {
                    return t.isLivraison(); //To change body of generated lambdas, choose Tools | Templates.
                }).count())
                
        );
        ObservableList<BarChart.Data<String, Number>> nonLivreesData = FXCollections.observableArrayList(
            new BarChart.Data<>("Janvier", lsComm.stream().filter((t) -> {
                    return t.isLivraison(); //To change body of generated lambdas, choose Tools | Templates.
                }).count()),
                
                
                new BarChart.Data<>("Fevrier", lsComm.stream().filter((t) -> {
                    return t.isLivraison(); //To change body of generated lambdas, choose Tools | Templates.
                }).count()),
                
                new BarChart.Data<>("Mars", lsComm.stream().filter((t) -> {
                    return t.isLivraison(); //To change body of generated lambdas, choose Tools | Templates.
                }).count())
        );
        
        // Série des commandes à livrer
        BarChart.Series<String, Number> livreesSeries = new BarChart.Series<>("Commandes livrées", livreesData);
        
        // Série des commandes non livrées
        BarChart.Series<String, Number> nonLivreesSeries = new BarChart.Series<>("Commandes non livrées", nonLivreesData);
        
        // Axe horizontal avec les mois
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Mois");
        
        // Axe vertical avec le nombre de commandes
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre de commandes");
        
        // Création du BarChart
        BarChart<String, Number> Statique = new BarChart<>(xAxis, yAxis);
        Statique.setTitle("Commandes à livrer vs commandes non livrées");
        Statique.setLegendVisible(true);
        
        // Ajout des séries au BarChart
        Statique.getData().addAll(livreesSeries, nonLivreesSeries);
        
        chartHolder.getChildren().add(Statique);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start();
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

    
    
}
