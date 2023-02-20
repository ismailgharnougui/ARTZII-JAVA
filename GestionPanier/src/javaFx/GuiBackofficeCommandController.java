/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import models.Article;
import models.Command;
import services.ServiceCommand;

/**
 * FXML Controller class
 *
 * @author medmo
 */
public class GuiBackofficeCommandController implements Initializable {

    @FXML
    private Label nomPrenom;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox1;
    
     ServiceCommand scom = new ServiceCommand();
    Command command;

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
pane.setPrefWidth(840.0);
pane.setStyle("-fx-background-color: #F5F2DC;");

Label label1 = new Label("1");
label1.setLayoutX(9.0);
label1.setLayoutY(18.0);
label1.setFont(new Font(16.0));

Label label2 = new Label("Mokhtar Hadded");
label2.setLayoutX(41.0);
label2.setLayoutY(18.0);
label2.setPrefHeight(25.0);
label2.setPrefWidth(128.0);
label2.setFont(new Font(16.0));

Label label3 = new Label("Bizerte");
label3.setLayoutX(199.0);
label3.setLayoutY(18.0);
label3.setPrefHeight(25.0);
label3.setPrefWidth(112.0);
label3.setFont(new Font(16.0));

Label label4 = new Label("livraison domicile");
label4.setLayoutX(295.0);
label4.setLayoutY(18.0);
label4.setPrefHeight(25.0);
label4.setPrefWidth(135.0);
label4.setFont(new Font(16.0));

Label label5 = new Label("CASH");
label5.setLayoutX(463.0);
label5.setLayoutY(18.0);
label5.setPrefHeight(25.0);
label5.setPrefWidth(80.0);
label5.setFont(new Font(16.0));

Label label6 = new Label("950 DT");
label6.setLayoutX(596.0);
label6.setLayoutY(18.0);
label6.setPrefHeight(25.0);
label6.setPrefWidth(97.0);
label6.setFont(new Font(16.0));

Label label7 = new Label("En cours");
label7.setLayoutX(685.0);
label7.setLayoutY(18.0);
label7.setPrefHeight(25.0);
label7.setPrefWidth(80.0);
label7.setFont(new Font(16.0));

ImageView imageView1 = new ImageView();
Image image1 = new Image(getClass().getResourceAsStream("../resources/trash.png"));
imageView1.setImage(image1);
imageView1.setFitHeight(23.0);
imageView1.setFitWidth(24.0);
imageView1.setLayoutX(813.0);
imageView1.setLayoutY(22.0);
imageView1.setPickOnBounds(true);
imageView1.setPreserveRatio(true);

ImageView imageView2 = new ImageView();
Image image2 = new Image(getClass().getResourceAsStream("../resources/edit.png"));
imageView2.setImage(image2);
imageView2.setFitHeight(25.0);
imageView2.setFitWidth(25.0);
imageView2.setLayoutX(785.0);
imageView2.setLayoutY(21.0);
imageView2.setPickOnBounds(true);
imageView2.setPreserveRatio(true);

Line line = new Line();
line.setStartX(-411.0);
line.setStartY(9.400012969970703);
line.setEndX(429.0);
line.setEndY(9.400012969970703);
line.setLayoutX(411.0);
line.setLayoutY(53.0);

pane.getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, imageView1, imageView2, line);
 vbox1.getChildren().add(pane);
       }
        vbox1.setSpacing(2);
    }    
    
}
