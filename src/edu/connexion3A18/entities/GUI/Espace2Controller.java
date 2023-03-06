/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A18.entities.GUI;

/**
 *
 * @author GOLDEN
 */

import com.jfoenix.controls.JFXButton;
import edu.connexion3A18.entities.Evenement;
import edu.connexion3A18.entities.Ticket;
import edu.connexion3A18.services.EvenementCRUD;
import edu.connexion3A18.services.TicketCRUD;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;



public class Espace2Controller implements Initializable{

    @FXML
    private TableView<Ticket> TicketTable;

    @FXML
    private TableColumn<Ticket, String> idTicket;

    @FXML
    private TableColumn<Ticket, String> prix_tick;

    @FXML
    private TableColumn<Ticket, String> nomEventTick;

    @FXML
    private TableColumn<Ticket, String> nomClientTick;

    @FXML
    private TableColumn<Ticket, String> NbrTicketsAchetesTick;


    @FXML
    private TextField prix;

    @FXML
    private TextField nomEvent;

    @FXML
    private Button AddEvenement1;

    @FXML
    private Button SuppEvent1;

    @FXML
    private JFXButton bar2;

    @FXML
    private JFXButton bar1;

    @FXML
    private AnchorPane paneslide;

    @FXML
    private ComboBox<String> cbx_id;

    @FXML
    private TextField nomClient;
    @FXML
    private TextField nbr_tickets;

    @FXML
    void AddEvent(ActionEvent event) {
        // Get the input values from the text fields and combo box
    String prixVal = prix.getText();
    String nomEventVal = nomEvent.getText();
    String nomClientVal = nomClient.getText();
    String nbrTicketsVal = nbr_tickets.getText();
    String idVal = cbx_id.getValue();

    // Validate the input values
    if (prixVal == null || prixVal.isEmpty() || nomEventVal == null || nomEventVal.isEmpty()
            || nomClientVal == null || nomClientVal.isEmpty() || nbrTicketsVal == null
            || nbrTicketsVal.isEmpty() || idVal == null || idVal.isEmpty()) {
        // Display an error message if any of the fields are empty
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all fields.");
        alert.showAndWait();
        return;
    }

    // Convert the number of tickets to an integer
    int nbrTickets = Integer.parseInt(nbrTicketsVal);

    // Create a new Ticket object with the input values
    Ticket newTicket = new Ticket(Integer.parseInt(idVal), Float.parseFloat(prixVal), nomEventVal, nomClientVal, nbrTickets);

    // Add the new ticket to the table view
    TicketTable.getItems().add(newTicket);

    // Clear the input fields
    prix.clear();
    nomEvent.clear();
    nomClient.clear();
    nbr_tickets.clear();
    cbx_id.getSelectionModel().clearSelection();
    }

    @FXML
    void SupprimerEvent(ActionEvent event) throws SQLException {
        Ticket selectedEvent = TicketTable.getSelectionModel().getSelectedItem();
    if (selectedEvent == null) {
        // Aucun événement sélectionné
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucun ticket sélectionné");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un ticket à supprimer.");
        alert.showAndWait();
        return;
    }
    // Demander une confirmation de la suppression
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de la suppression");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer le ticket sélectionné ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Supprimer l'événement de la base de données
        TicketCRUD ecd = new TicketCRUD();
        ecd.supprimerTicket(selectedEvent.getIdTicket());
        // Supprimer l'événement de la TableView
        TicketTable.getItems().remove(selectedEvent);
        // Afficher un message de succès
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Suppression réussie");
        alert2.setHeaderText(null);
        alert2.setContentText("Le ticket a été supprimé avec succès.");
        alert2.showAndWait();
    }
    }


    @FXML
    void run1(MouseEvent event) {

    }

    @FXML
    void run2(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Remplir la liste déroulante ComboBox avec les ID des événements existants dans la base de données
    EvenementCRUD evenementCRUD = new EvenementCRUD();
    List<Evenement> evenements = evenementCRUD.afficherEvenemets();
    ObservableList<String> evenementIds = FXCollections.observableArrayList();
    for (Evenement evenement : evenements) {
        evenementIds.add(String.valueOf(evenement.getIdEvent()));
    }
    cbx_id.setItems(evenementIds);

    // Associer la sélection du ComboBox avec l'affichage des informations de l'événement correspondant
    cbx_id.setOnAction((event) -> {
        String selectedEventId = cbx_id.getSelectionModel().getSelectedItem();
        Evenement selectedEvent = evenementCRUD.getEvenementById(Integer.parseInt(selectedEventId));
        if (selectedEvent != null) {
            nomEvent.setText(selectedEvent.getNomEvent());
            prix.setText(Float.toString(selectedEvent.getPrixEntre()));
        }
    });
    
        TicketCRUD ecd = new TicketCRUD();
        List<Ticket> EV = ecd.afficherTickets();
        ObservableList<Ticket> tickList = FXCollections.observableArrayList();

        tickList.addAll(EV);
        idTicket.setCellValueFactory(new PropertyValueFactory<>("IdTicket"));
        nomClientTick.setCellValueFactory(new PropertyValueFactory<>("NomClient"));
        prix_tick.setCellValueFactory(new PropertyValueFactory<>("PrixTicket"));
        nomEventTick.setCellValueFactory(new PropertyValueFactory<>("NomEvent"));
        NbrTicketsAchetesTick.setCellValueFactory(new PropertyValueFactory<>("nbr_ticket"));
        TicketTable.setItems(tickList);
    }


}
