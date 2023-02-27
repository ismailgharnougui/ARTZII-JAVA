/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import models.Chart;
import models.Client;
import models.Command;
import services.ServiceClient;
import services.ServiceCommand;
//import com.easypost.EasyPost;

/**
 * FXML Controller class
 *
 * @author medmo
 */
public class GuiBackofficeCommandController implements Initializable {
     /* static {
        EasyPost.apiKey = "EZTK363efa032abc4cbf99e79d3e7588cce7vdaiHvdTrDiJCocO2kkx6Q";
    } */

    @FXML
    private Label nomPrenom;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox1;
    
     ServiceCommand scom = new ServiceCommand();
    Command command;
    ServiceClient sc = new ServiceClient();
    Client client;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         List<Command> commands = scom.afficherCommands();
         System.out.println(commands);
         vbox1.setFillWidth(true);
       for (Command command : commands) {
           Pane pane = new Pane();
pane.setPrefHeight(62.0);
pane.setMinHeight(62.0);
pane.setPrefWidth(840.0);
pane.setStyle("-fx-background-color: #F5F2DC;");

Label label1 = new Label(command.getId()+"");
label1.setLayoutX(9.0);
label1.setLayoutY(18.0);
label1.setFont(new Font(16.0));

Label label2 = new Label( sc.get(command.getIdClient()).getNom()+" "+ sc.get(command.getIdClient()).getPrenom());
label2.setLayoutX(38.0);
label2.setLayoutY(18.0);
label2.setPrefHeight(25.0);
label2.setPrefWidth(128.0);
label2.setFont(new Font(16.0));

Label label3 = new Label(sc.get(command.getIdClient()).getAddress());
label3.setLayoutX(175.0);
label3.setLayoutY(18.0);
label3.setPrefHeight(25.0);
label3.setPrefWidth(150.0);
label3.setFont(new Font(16.0));

Label label4 = new Label(command.getLivMethod());
label4.setLayoutX(320.0);
label4.setLayoutY(18.0);
label4.setPrefHeight(25.0);
label4.setPrefWidth(135.0);
label4.setFont(new Font(16.0));

Label label5 = new Label(command.getPayMethod());
label5.setLayoutX(485.0);
label5.setLayoutY(18.0);
label5.setPrefHeight(25.0);
label5.setPrefWidth(80.0);
label5.setFont(new Font(16.0));

Label label6 = new Label(command.getTotalCost()+"");
label6.setLayoutX(601.0);
label6.setLayoutY(18.0);
label6.setPrefHeight(25.0);
label6.setPrefWidth(97.0);
label6.setFont(new Font(16.0));

ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList("En cours", "En attente","Livré", "Annulé"));
choiceBox.getSelectionModel().select(command.getEtatCommande());
choiceBox.setLayoutX(680.0);
choiceBox.setLayoutY(18.0);
choiceBox.setPrefHeight(25.0);
choiceBox.setPrefWidth(90.0);


ImageView imageView1 = new ImageView();
Image image1 = new Image(getClass().getResourceAsStream("../resources/trash.png"));
imageView1.setImage(image1);
imageView1.setFitHeight(23.0);
imageView1.setFitWidth(24.0);
imageView1.setLayoutX(810.0);
imageView1.setLayoutY(22.0);
imageView1.setPickOnBounds(true);
imageView1.setPreserveRatio(true);
imageView1.setOnMouseClicked((MouseEvent event) -> {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmer la suppression");
    alert.setHeaderText("Êtes-vous sûr de supprimer cette commande ?");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK){
    Pane parent = (Pane) pane.getParent();
    scom.supprimer(command.getId());
    commands.remove(command);
    parent.getChildren().remove(pane);
    }
});

ImageView imageView2 = new ImageView();
Image image2 = new Image(getClass().getResourceAsStream("../resources/edit.png"));
imageView2.setImage(image2);
imageView2.setFitHeight(25.0);
imageView2.setFitWidth(25.0);
imageView2.setLayoutX(785.0);
imageView2.setLayoutY(21.0);
imageView2.setPickOnBounds(true);
imageView2.setPreserveRatio(true);
imageView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                   alert.setTitle("Confirmer la mmodification");
                   alert.setHeaderText("Êtes-vous sûr de modifier l'état de cette commande  en "+choiceBox.getValue()+" ?");
                   Optional<ButtonType> result = alert.showAndWait();
                   
                   if (result.get() == ButtonType.OK){
                       Command comm = new Command(command.getId(),choiceBox.getValue());
                       scom.modifierCommand(comm);
                   }          }
           });

Line line = new Line();
line.setStrokeWidth(0.4);
line.setStartX(-411.0);
line.setStartY(9.400012969970703);
line.setEndX(429.0);
line.setEndY(9.400012969970703);
line.setLayoutX(411.0);
line.setLayoutY(53.0);

pane.getChildren().addAll(label1, label2, label3, label4, label5, label6, choiceBox, imageView1, imageView2, line);
 vbox1.getChildren().add(pane);
       }
        vbox1.setSpacing(2);
    }    
    
    
    
    
    @FXML
    void displayChart(ActionEvent event) throws SQLException {
        Chart chart = new Chart();
        chart.main(new String[]{});
    }
    
}
