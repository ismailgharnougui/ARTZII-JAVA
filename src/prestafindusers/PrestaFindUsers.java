/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prestafindusers;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;  
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.GmailSender;
import Entites.Utilisateur;

/**
 *
 * @author User31
 */
public class PrestaFindUsers extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       Utilisateur U = new Utilisateur("ma","ftou","mariem.ftouhi@esprit.tn","99","admin","ariana");
        String from = "wintermorningmeowmeow@gmail.com";//email statique aandou acces bch yabaath les emails 
        String password = "pmzyzqtlnzfmvlub";
        String to = "mariem.ftouhi@esprit.tn";
        String subject = "Reset mot de passe";
        String body = "mot de passe oubliee est :"+U.getmdpU();        

        GmailSender.sendEmail(to, from, password, subject, body);
//        SMSSender.sendSMS("test SMS");

        Parent root = FXMLLoader.load(getClass().getResource("ajouter_utilisateur.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
