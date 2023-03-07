/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import models.User;
import Services.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class Afficher_utilisateurController implements Initializable {

    public static User user = new User();

    ObservableList<User> obList;
    ServiceUser a;
    Button btn;
    Button btnEx;
    Button btnModi;
    User U = new User();
    private TableColumn<User, Void> colModifBtn;
    private TableColumn<User, Void> colSuppBtn;

    @FXML
    private TableView<User> tableview;
    @FXML
    private TableColumn<User, String> ft_nomu;
    @FXML
    private TableColumn<User, String> ft_prenomu;
    @FXML
    private TableColumn<User, String> ft_emailu;
    @FXML
    private TableColumn<User, String> ft_mdpu;
    @FXML
    private TableColumn<User, String> ft_roleu;
    @FXML
    private TableColumn<User, String> ft_adresse;
    @FXML
    private TextField search_tv;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label nomPrenom;
    @FXML
    private VBox vbox2;
    @FXML
    private AnchorPane bord;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colSuppBtn = new TableColumn<>("Supprimer");
        tableview.getColumns().add(colSuppBtn);

        colModifBtn = new TableColumn<>("Modifier");
        tableview.getColumns().add(colModifBtn);

        a = new ServiceUser();
        obList = a.afficherUtilisateur();

        ft_nomu.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ft_prenomu.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        ft_emailu.setCellValueFactory(new PropertyValueFactory<>("email"));
        ft_mdpu.setCellValueFactory(new PropertyValueFactory<>("pass"));

        ft_roleu.setCellValueFactory(new PropertyValueFactory<>("role"));
        ft_adresse.setCellValueFactory(new PropertyValueFactory<>("address"));

        tableview.setItems(obList);

        addButtonModifToTable();

        addButtonDeleteToTable();

        //APPEL 
    }

    @FXML
    public void Afficher() {
        tableview.refresh();
        obList.clear();

        obList = a.afficherUtilisateur();

        ft_nomu.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ft_prenomu.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        ft_emailu.setCellValueFactory(new PropertyValueFactory<>("email"));
        ft_mdpu.setCellValueFactory(new PropertyValueFactory<>("pass"));

        ft_roleu.setCellValueFactory(new PropertyValueFactory<>("role"));
        ft_adresse.setCellValueFactory(new PropertyValueFactory<>("address"));

        tableview.setItems(obList);
    }

    public void addButtonModifToTable() {

        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {

            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {

                final TableCell<User, Void> cell = new TableCell<User, Void>() {

                    {

                        btn = new Button("Modifier");

                        btn.setOnAction((ActionEvent event) -> {

                            try {

                                user = tableview.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifier_utilisateur.fxml"));
                                try {
                                    Parent root = loader.load();
                                    bord.getChildren().setAll(root);

                                } catch (IOException ex) {
                                    System.out.println(ex);
                                }

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

        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {

                final TableCell<User, Void> cell = new TableCell<User, Void>() {

                    {
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {

                            U = tableview.getSelectionModel().getSelectedItem();
                            showConfirmation(U);
                            System.out.println(U);
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

    private void showConfirmation(User U) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voullez vous vraiment supprimer??");
        alert.setContentText("Utilisateur peut etre efface");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("pas selection!");
        } else if (option.get() == ButtonType.OK) {
            System.out.println(U.getId());
            a.supprimerUtilisateur(U.getId());
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
        FilteredList<User> filteredList = new FilteredList<>(obList, b -> true);

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

                if (reclamation.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (String.valueOf(reclamation.getPrenom()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (reclamation.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (reclamation.getPass().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else {

                    btn = new Button("Modifier");
                    btnEx = new Button("Exporter");
                    btnModi = new Button("Supprimer");
                    return false;
                }

            });

        });

        SortedList<User> sortedData = new SortedList<>(filteredList);

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
    private void goToNavigate(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiAdmin.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
