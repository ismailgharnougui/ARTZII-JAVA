/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import models.Reclamation;
import services.CRUDReclamation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author 21628
 */
public class AdminStatReclmationController implements Initializable {

    @FXML
    private PieChart idpiechart;
    @FXML
    private Label caption;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
                   CRUDReclamation rc  = new CRUDReclamation();
         ObservableList<Reclamation> reclist = rc.afficherReclamation();
                   List<Reclamation> myArray = new ArrayList<Reclamation>();

    for (int i =0; i<reclist.size(); i++){
       myArray.add(reclist.get(i));
    }
    
 ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Reclmation resolu", myArray.stream().filter(e->e.getEtat().compareTo("resolu")==0).count()),
                new PieChart.Data("Reclamation non resolu", myArray.stream().filter(e->e.getEtat().compareTo("nonresolu")==0).count()));
 
              
 idpiechart.setData(pieChartData);
 idpiechart.setTitle("Reclamation ");
 
     

for (final PieChart.Data data : idpiechart.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                caption.setLayoutX(e.getSceneX());
                caption.setLayoutY(e.getSceneY());
                float pourcentage=(float)data.getPieValue()*100/myArray.size();
                String formattedString = String.format("%.02f", pourcentage);

                caption.setText(String.valueOf(formattedString+"%"));
                
             }
        });
}

    
    
    }    
    
}
