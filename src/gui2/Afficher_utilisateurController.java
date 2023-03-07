/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;
import controller.QRcodeController;
import Entites.Utilisateur;
import Services.CRUDUtilisateur;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class Afficher_utilisateurController implements Initializable {

    ObservableList<Utilisateur> obList;
    CRUDUtilisateur a;
    Button btn;
    Button btnEx;
    Button btnModi;
    Utilisateur U = new Utilisateur();
    private TableColumn<Utilisateur, Void> colModifBtn;
    private TableColumn<Utilisateur, Void> colSuppBtn;
    private AnchorPane body;
    @FXML
    private TableView<Utilisateur> tableview;
    @FXML
    private TableColumn<Utilisateur, String> ft_nomu;
    @FXML
    private TableColumn<Utilisateur, String> ft_prenomu;
    @FXML
    private TableColumn<Utilisateur, String> ft_emailu;
    @FXML
    private TableColumn<Utilisateur, String> ft_mdpu;
    @FXML
    private TableColumn<Utilisateur, String> ft_roleu;
    @FXML
    private TableColumn<Utilisateur, String> ft_adresse;
    @FXML
    private TextField search_tv;
    @FXML
    private Button btnAjouter;
    private TableView<Utilisateur> table2;
    private TableColumn<Utilisateur,String> xx;
    @FXML
    private ImageView qr_code;
    @FXML
    private Button fx_button;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colSuppBtn = new TableColumn<>("Supprimer");
        tableview.getColumns().add(colSuppBtn);

        colModifBtn = new TableColumn<>("Modifier");
        tableview.getColumns().add(colModifBtn);

        a = new CRUDUtilisateur();
        obList = a.afficherUtilisateur();

        ft_nomu.setCellValueFactory(new PropertyValueFactory<>("nomU"));
        ft_prenomu.setCellValueFactory(new PropertyValueFactory<>("prenomU"));

        ft_emailu.setCellValueFactory(new PropertyValueFactory<>("emailU"));
        ft_mdpu.setCellValueFactory(new PropertyValueFactory<>("mdpU"));

        ft_roleu.setCellValueFactory(new PropertyValueFactory<>("roleU"));
        ft_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
 xx.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableview.setItems(obList);
  table2.setItems(obList);
        addButtonModifToTable();

        addButtonDeleteToTable();

        //APPEL 
    }

    @FXML
    public void Afficher() {
        tableview.refresh();
        obList.clear();

        obList = a.afficherUtilisateur();

        ft_nomu.setCellValueFactory(new PropertyValueFactory<>("nomU"));
        ft_prenomu.setCellValueFactory(new PropertyValueFactory<>("prenomU"));

        ft_emailu.setCellValueFactory(new PropertyValueFactory<>("emailU"));
        ft_mdpu.setCellValueFactory(new PropertyValueFactory<>("mdpU"));

        ft_roleu.setCellValueFactory(new PropertyValueFactory<>("roleU"));
        ft_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        tableview.setItems(obList);
    }

    public void addButtonModifToTable() {

        Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>> cellFactory = new Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>>() {
            @Override
            public TableCell<Utilisateur, Void> call(final TableColumn<Utilisateur, Void> param) {

                final TableCell<Utilisateur, Void> cell = new TableCell<Utilisateur, Void>() {

                    {

                        btn = new Button("Modifier");

                        btn.setOnAction((ActionEvent event) -> {

                            try {

                                U = tableview.getSelectionModel().getSelectedItem();

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui2/modifierutilisateur.fxml"));
                                Parent root = loader.load();
                                ModifierutilisateurController controller = loader.getController();
                                controller.setnomU(U.getnomU());
                                controller.setprenomU(U.getprenomU());
                                controller.setemailU(U.getemailU());
                                controller.setmdpU(U.getmdpU());
                                controller.setroleU(U.getroleU());
                                controller.setadresse(U.getadresse());

                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colModifBtn.setCellFactory(cellFactory);

    }

    Button btnSupprimer;

    public void addButtonDeleteToTable() {

        Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>> cellFactory = new Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>>() {
            @Override
            public TableCell<Utilisateur, Void> call(final TableColumn<Utilisateur, Void> param) {

                final TableCell<Utilisateur, Void> cell = new TableCell<Utilisateur, Void>() {

                    {

                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {

                            U = tableview.getSelectionModel().getSelectedItem();
                            showConfirmation(U);

                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnSupprimer);
                        }
                    }
                };
                return cell;
            }
        };

        colSuppBtn.setCellFactory(cellFactory);

    }

    private Label label;

    private void showConfirmation(Utilisateur U) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voullez vous vraiment supprimer??");
        alert.setContentText("Utilisateur peut etre efface");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("pas selection!");
        } else if (option.get() == ButtonType.OK) {
            System.out.println(U.getidU());
            a.supprimerUtilisateur(U.getidU());
            obList.clear();
            Afficher();
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Exit!");
        } else {
            this.label.setText("-");
        }
    }

//Metier function search
    public void filter() {
        FilteredList<Utilisateur> filteredList = new FilteredList<>(obList, b -> true);

        search_tv.textProperty().addListener((observable, oldValue, newValue) -> {

            if (search_tv.getText().isEmpty()) {
                //Reload button
                addButtonModifToTable();
                addButtonDeleteToTable();

            }
            filteredList.setPredicate(reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
                    btn = new Button("Modifier");
                    btnEx = new Button("Exporter");
                    btnModi = new Button("Supprimer");

                    return true;
                }

                //
                String lowerCaseFilter = newValue.toLowerCase();

                if (reclamation.getnomU().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (String.valueOf(reclamation.getprenomU()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (reclamation.getemailU().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (reclamation.getmdpU().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else {

                    btn = new Button("Modifier");
                    btnEx = new Button("Exporter");
                    btnModi = new Button("Supprimer");
                    return false;
                }

            });

        });

        SortedList<Utilisateur> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        tableview.setItems(sortedData);

    }

//METIER SEARCH
    @FXML
    private void searchquery(KeyEvent event) {

        filter();
    }

    @FXML
    private void gotoAjouter(ActionEvent event) throws IOException {

        Parent page1 = FXMLLoader.load(getClass().getResource("ajouter_utilisateur.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void QRcode(ActionEvent event) {
        
        String nom=ft_nomu.getText();
                String prenom=ft_prenomu.getText();
                try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String Information = "nom : "+nom+"\n"+"prenom : "+prenom;
            int width = 300;
            int height = 300;
            
            BufferedImage bufferedImage = null; 
            ByteMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(java.awt.Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(java.awt.Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
            
            

            
            qr_code.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            // TODO
        } catch (WriterException ex) {
            Logger.getLogger(Afficher_utilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
                

    }

