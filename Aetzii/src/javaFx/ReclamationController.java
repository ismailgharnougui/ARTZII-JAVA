/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import java.io.IOException;
import models.Reclamation;
import models.Reponse;
import services.CRUDReclamation;
import services.CRUDReponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author 21628
 */
public class ReclamationController implements Initializable {

   
    @FXML
    private TextField tfTypeR;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfObjet;
    @FXML
    private Button btnValider;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<?, ?> TypeR;
    @FXML
    private TableColumn<?, ?> dateR;
    @FXML
    private TableColumn<?, ?> etat;
    @FXML
    private TableColumn<?, ?> Description;
    @FXML
    private TableColumn<?, ?> Objet;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnmodifier;
    private String actionForm="ajouter";
    private int idUser=1;
    @FXML
    private Label reponsevalue;
    @FXML
    private Button btnafficherReponse;
    public static final String ACCOUNT_SID = "AC42e7b8e7f305a86408122fb767b0e3dc";
    public static final String AUTH_TOKEN = "025dca3699a08acb76b6ca4c9551cf3d";
    @FXML
    private TextField numtell;
    @FXML
    private VBox vbox2;
    @FXML
    private Pane erreurPane;
    @FXML
    private Label erreurvalue;
    @FXML
    private Label nomPrenom3;
    @FXML
    private Button btnNaviguer;
    @FXML
    private AnchorPane bord;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
      
        
     Refresh();
     ObservableList selectedCells = tableReclamation.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                reponsevalue.setText("");
                
                Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
               
                if(recSelected!=null){
                    if(recSelected.getEtat().compareTo("resolu")!=0){
              btnSupprimer.setDisable(false);
              btnmodifier.setDisable(false);
              btnafficherReponse.setVisible(false);
                    }else{
                  btnSupprimer.setDisable(true);
                  btnmodifier.setDisable(true);
                  btnafficherReponse.setVisible(true);

                    }
              
                }else{
                btnSupprimer.setDisable(true);
              btnmodifier.setDisable(true);
               btnafficherReponse.setVisible(false);


                }
            }
           
        });

       
    }    
    private boolean isNumeric(String ch){
        for(int i=0;i<ch.length();i++)
        {
            if(ch.charAt(i)>'9'||ch.charAt(i)<'0'){
                return false;
            }
        }
        return true;
        
    }
    /*
    private void verifBadWord(String ch){
         NeutrinoAPIClient neutrinoAPI = new NeutrinoAPIClient("<your-user-id>", "<your-api-key>");
        Map<String, String> params = new HashMap<>();

        // The character to use to censor out the bad words found
        params.put("censor-character", "");

        // Which catalog of bad words to use, we currently maintain two bad word catalogs:
        // • strict - the largest database of bad words which includes profanity, obscenity, sexual, rude,
        //   cuss, dirty, swear and objectionable words and phrases. This catalog is suitable for
        //   environments of all ages including educational or children's content
        // • obscene - like the strict catalog but does not include any mild profanities, idiomatic
        //   phrases or words which are considered formal terminology. This catalog is suitable for adult
        //   environments where certain types of bad words are considered OK
        params.put("catalog", "strict");

        // The content to scan. This can be either a URL to load from, a file upload (multipart/form-data)
        // or an HTML content string
        params.put("content", "https://en.wikipedia.org/wiki/Profanity");

        APIResponse response = neutrinoAPI.badWordFilter(params);
        if (response.getData().isPresent()) {
            JsonObject data = response.getData().get();
            System.out.println("API Response OK: ");
            
            // An array of the bad words found
            System.out.printf("bad-words-list: %s%n", data.get("bad-words-list"));
            
            // Total number of bad words detected
            System.out.printf("bad-words-total: %s%n", data.get("bad-words-total"));
            
            // The censored content (only set if censor-character has been set)
            System.out.printf("censored-content: %s%n", data.get("censored-content"));
            
            // Does the text contain bad words
            System.out.printf("is-bad: %s%n", data.get("is-bad"));
            
        } else {
            // API request failed, you should handle this gracefully!
            System.err.printf("API Error: %s, Error Code: %d, HTTP Status Code: %d%n", response.getErrorMessage(), response.getErrorCode(), response.getHttpStatusCode());
            response.getErrorCause().ifPresent(cause -> System.err.printf("Error Caused By: %s%n", cause));
        }
    }*/
    @FXML
    private void saveReclamation(ActionEvent event) {
            CRUDReclamation rc  = new CRUDReclamation();
    String TypeR =tfTypeR.getText();
    String Description =tfDescription.getText();
    String objet =tfObjet.getText();
    String tell=numtell.getText();
  //  verifBadWord(Description);
    if(TypeR.length()==0||Description.length()==0||objet.length()==0||tell.length()==0){
       erreurPane.setVisible(true);
      erreurvalue.setText("Tous les champs sont obligatoires ");
    }else if(tell.length()!=8||isNumeric(tell)==false){
        
         erreurPane.setVisible(true);
      erreurvalue.setText("Contact doit etre une chaine numérique de 8 caractères ");
    }else{
        
    erreurvalue.setText("");
             erreurPane.setVisible(false);

    
       if(actionForm.compareTo("ajouter")==0){
 
    String etat ="nonresolu";
   
   Reclamation r = new Reclamation(0,TypeR,Description,objet,etat,idUser);
    rc.ajouterReclamation(r);
    /*
    try{
      Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+216"+numtell.getText()),
                new com.twilio.type.PhoneNumber("+12766246381"),
                "Votre réclamation a été ajoutée avec succès. Nous allons l'examiner dès que possible et vous contacterons si nous avons besoin de plus d'informations. Merci de nous avoir contacté.")
            .create();
    }catch(Exception e){
        System.out.print("erreur"+e.getMessage());
    }  */
        Refresh();

       }else{
          Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();

recSelected.setTypeR(tfTypeR.getText());
recSelected.setDescription(tfDescription.getText());
recSelected.setObjet(tfObjet.getText());
System.out.print(recSelected);
rc.modifierReclamation(recSelected);
actionForm="ajouter";
         Refresh();
      
       }
       tfTypeR.setText("");
tfDescription.setText("");
tfObjet.setText("");
numtell.setText("");
    }

    }
    public void Refresh(){
           CRUDReclamation rc  = new CRUDReclamation();
         ObservableList<Reclamation> list = rc.afficherReclamation(idUser);
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
       TypeR.setCellValueFactory(new PropertyValueFactory<>("TypeR"));
         dateR.setCellValueFactory(new PropertyValueFactory<>("DateR"));
         etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
         Objet.setCellValueFactory(new PropertyValueFactory<>("Objet"));
       
         tableReclamation.setItems(list);
         
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {
                        Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
           CRUDReclamation rc  = new CRUDReclamation();
rc.supprimerReclamation(recSelected.getId());
    Refresh();

    }

    @FXML
    private void remplirModifierform(ActionEvent event) {
        this.actionForm="modifier";
        Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
tfTypeR.setText(recSelected.getTypeR());
tfDescription.setText(recSelected.getDescription());
tfObjet.setText(recSelected.getObjet());

       
        
    }

    @FXML
    private void AfficherReponse(ActionEvent event) {
        System.out.println("enter0");
                Reclamation recSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
               CRUDReponse rp = new CRUDReponse();
              Reponse r= rp.getReponseByIdReclamation(recSelected.getId());
               reponsevalue.setText(r.getContenuRep());
               System.out.println(r.getContenuRep());
               
    }

    @FXML
    private void goToNavigate(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiArticles.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void goToPanier(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiPanier.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
