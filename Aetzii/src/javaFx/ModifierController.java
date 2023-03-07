/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.Debug.id;
import services.CRUDLivreurs;
import models.Livreurs;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ModifierController implements Initializable {

    int id;
    @FXML
    private TextField tfnum_tel;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfregion;
    private TextField tfid;
    @FXML
    private Label modifnum_tel;
    @FXML
    private Label modifnom;
    @FXML
    private Label modifprenom;
    @FXML
    private Label modifregion;
    @FXML
    private ImageView fx_code;
    @FXML
    private Button qr_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    }    
    
    @FXML
    private void Modifier(ActionEvent event) {
        
        
        String nom=tfnom.getText();
        String prenom=tfprenom.getText();
        String num_tel=tfnum_tel.getText();
        String region_livreur=tfregion.getText();
        
        
        Livreurs Liv = new Livreurs(id ,num_tel, nom, prenom,region_livreur);
        CRUDLivreurs crud = new CRUDLivreurs();
        crud.modifierlivreurs(Liv);
        
        
        
    }
    
    void initData(Livreurs Liv) {
        this.id = Liv.getId();
        System.out.println(Liv);
        tfnum_tel.setText(Liv.getNum_tel());
        tfnom.setText(Liv.getNom());
        tfprenom.setText((Liv.getPrenom()));
        tfregion.setText(Liv.getRegion_livreur());
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

    @FXML
    private void QRcode(ActionEvent event) {
         String num_telx =tfnum_tel.getText(); 
        String nomx = tfnom.getText();
        String prenomx=tfprenom.getText();
        String regionx = tfregion.getText();
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String Information = "nom : "+nomx+"\n"+"prenom : "+prenomx +"\n"+"numero de telephone :"+num_telx +"\n"+"region : "+regionx ;
            int width = 300;
            int height = 300;
            
            BufferedImage bufferedImage = null; 
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
            
            

            
            fx_code.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            // TODO
        } catch (WriterException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
