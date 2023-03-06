/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import models.InvoiceGenerator;
import javaFx.GuiLoginController;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javax.swing.JFileChooser;
import models.Article;
import models.Basket;
import models.User;
import models.Command;
import services.ServiceBasket;
import services.ServiceUser;
import services.ServiceCommand;

public class GuiCommandController implements Initializable {
    User connectedUser = GuiLoginController.user;
    @FXML
    private RadioButton livArtziiNow;
    @FXML
    private RadioButton livDomicile;
    @FXML
    private AnchorPane bord;
    @FXML
    private Label nomPrenom;
    @FXML
    private VBox vbox2;
    @FXML
    private Label nomPrenom2;
    @FXML
    private VBox vbox1;
    @FXML
    private Label address1;
    @FXML
    private ToggleGroup modeLivraison;
    @FXML
    private ToggleGroup modePayement;
    @FXML
    private Label sousTotale;
    @FXML
    private Label totalCommand;
    @FXML
    private Button confirmerCommande;
    @FXML
    private RadioButton payCash;
    @FXML
    private RadioButton payEnLigne;

    ServiceBasket sb = new ServiceBasket();
    Basket panier;
    ServiceUser sc = new ServiceUser();
    ServiceCommand scom = new ServiceCommand();
    Command command;

    private final StringProperty sousTotaleContent = new SimpleStringProperty();
    private final StringProperty totalCommandContent = new SimpleStringProperty();
    private final StringProperty nomPrenomContent = new SimpleStringProperty();
    private final StringProperty addressContent = new SimpleStringProperty();

    public int pos;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pos = 1;
        List<Article> articles = sb.get(connectedUser.getId()).getArticles();
        System.out.println(articles);
        vbox1.setFillWidth(true);

        for (Article article : articles) {

            Pane pane = new Pane();
            pane.setMinHeight(79);
            pane.setMaxHeight(79);
            pane.setStyle("-fx-background-color: ffffff; -fx-background-radius: 0;");

            Label indexLabel = new Label("Article " + pos + " de " + articles.size());
            indexLabel.setLayoutX(19.0);
            indexLabel.setLayoutY(-2.0);
            indexLabel.setPrefHeight(31.0);
            indexLabel.setPrefWidth(120.0);
            indexLabel.setFont(Font.font("System Bold Italic", 15.0));
            indexLabel.setStyle("-fx-font-weight: bold;");

            Label titleLabel = new Label("" + article.getNom());
            titleLabel.setLayoutX(50.0);
            titleLabel.setLayoutY(23.0);
            titleLabel.setPrefHeight(31.0);
            titleLabel.setPrefWidth(112.0);
            titleLabel.setFont(Font.font(15.0));

            Label deliveryLabel = new Label("Prix :");
            deliveryLabel.setLayoutX(25.0);
            deliveryLabel.setLayoutY(46.0);
            deliveryLabel.setPrefHeight(31.0);
            deliveryLabel.setPrefWidth(342.0);
            deliveryLabel.setFont(Font.font(15.0));
            deliveryLabel.setStyle("-fx-font-weight: bold;");

            Label priceLabel = new Label(article.getPrix() + " DT");
            priceLabel.setLayoutX(67.0);
            priceLabel.setLayoutY(46.0);
            priceLabel.setPrefHeight(31.0);
            priceLabel.setPrefWidth(100.0);

            Label quantityLabel = new Label("1x");
            quantityLabel.setLayoutX(29.0);
            quantityLabel.setLayoutY(23.0);
            quantityLabel.setPrefHeight(31.0);
            quantityLabel.setPrefWidth(24.0);
            quantityLabel.setFont(Font.font("System Bold Italic", 15.0));
            quantityLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #ffffff;");

            Line line = new Line(-139.00001525878906, 1.3999786376953125, 350.0, 1.3999786376953125);
            line.setStrokeWidth(1.5);
            line.setLayoutX(30.0);
            line.setLayoutY(78.0);

            pane.getChildren().addAll(indexLabel, titleLabel, deliveryLabel, priceLabel, quantityLabel, line);
            vbox1.getChildren().add(pane);
            pos++;
        }
        vbox1.setSpacing(0.2);

        sousTotaleContent.setValue(sb.get(connectedUser.getId()).getTotalCostHT() + "");
        sousTotale.textProperty().bindBidirectional(sousTotaleContent);

        totalCommandContent.setValue((sb.get(connectedUser.getId()).getTotalCostHT() + 7) + "");
        totalCommand.textProperty().bindBidirectional(totalCommandContent);

        nomPrenomContent.setValue(connectedUser.getNom() + " " + connectedUser.getPrenom());
        nomPrenom.textProperty().bindBidirectional(nomPrenomContent);
        nomPrenom2.textProperty().bindBidirectional(nomPrenomContent);

        addressContent.setValue(connectedUser.getAddress());
        address1.textProperty().bindBidirectional(addressContent);
    }

    @FXML
    private void ajouterCommande(ActionEvent event) throws IOException {
        if (payEnLigne.isSelected()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Payement");
            alert.setHeaderText("Vous êtes en train de redirectionner ver la page de paiement, confirmer?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GuiPaiement.fxml"));
                try {
                    Parent root = loader.load();
                    bord.getChildren().setAll(root);

                } catch (IOException ex) {
                    System.out.println(ex);
                }

            }
        }

        else if (payCash.isSelected()) {

            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Confirmer la commande");
            alert2.setHeaderText("Êtes-vous sûr de confirmer cette  commande ?");
            Optional<ButtonType> result2 = alert2.showAndWait();

            if (result2.get() == ButtonType.OK) {
                Command command = new Command();
                // insertion dans la commande
                command.setIdClient(connectedUser.getId());
                command.setTotalCost((float) sb.get(connectedUser.getId()).getTotalCostHT() + 7);
                // checking livraison method
                if (livArtziiNow.isSelected()) {
                    command.setLivMethod("Artzii now");
                } else if (livDomicile.isSelected()) {
                    command.setLivMethod("Livraison domicile");
                } else {
                    System.out.println("No option selected");
                }
                command.setPayMethod("Cash");
                scom.ajouter(command);
            }
            
            //PDF FILE
            String pdfFilename;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                pdfFilename = fileToSave.getAbsolutePath();
                System.out.println("Save as file: " + pdfFilename);
            } else {
                // User canceled the file chooser
                return;
            }
            
            panier = sb.get(connectedUser.getId());
            System.out.println("User connecté"+connectedUser);
            System.out.println("le panier"+panier);
            // String pdfFilename = "Facture.pdf" ;
            try {

                OutputStream file = new FileOutputStream(new File(pdfFilename));
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                com.itextpdf.text.pdf.PdfWriter.getInstance(document, file);

                // Inserting Image in PDF
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("src/resources/logo.jpg");// Header
                                                                                                             
                image.scaleAbsolute(445f, 100.5f);// image width,height

                PdfPTable irdTable = new PdfPTable(2);
                irdTable.addCell(InvoiceGenerator.getIRDCell("N° facture"));
                irdTable.addCell(InvoiceGenerator.getIRDCell("Date facture"));
                irdTable.addCell(InvoiceGenerator.getIRDCell("XE1234"));
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                irdTable.addCell(InvoiceGenerator.getIRDCell(formattedDateTime + "")); // pass invoice date

                PdfPTable irhTable = new PdfPTable(3);
                irhTable.setWidthPercentage(100);

                irhTable.addCell(InvoiceGenerator.getIRHCell("", PdfPCell.ALIGN_RIGHT));
                irhTable.addCell(InvoiceGenerator.getIRHCell("", PdfPCell.ALIGN_RIGHT));
                irhTable.addCell(InvoiceGenerator.getIRHCell("Facture", PdfPCell.ALIGN_RIGHT));
                irhTable.addCell(InvoiceGenerator.getIRHCell("", PdfPCell.ALIGN_RIGHT));
                irhTable.addCell(InvoiceGenerator.getIRHCell("", PdfPCell.ALIGN_RIGHT));
                PdfPCell invoiceTable = new PdfPCell(irdTable);
                invoiceTable.setBorder(0);
                irhTable.addCell(invoiceTable);

                FontSelector fs = new FontSelector();
                com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13,
                        com.itextpdf.text.Font.BOLD);
                fs.addFont(font);
                Phrase bill = fs.process("Facture à"); // customer information
                com.itextpdf.text.Paragraph name = new com.itextpdf.text.Paragraph(
                        connectedUser.getPrenom() + " " + connectedUser.getNom()); // cl.getPrenom()+ " " +cl.getNom()
                name.setIndentationLeft(20);
                com.itextpdf.text.Paragraph contact = new com.itextpdf.text.Paragraph("");
                contact.setIndentationLeft(20);
                com.itextpdf.text.Paragraph address = new com.itextpdf.text.Paragraph(
                        "Adresse: " + connectedUser.getAddress()); // +cl.getAddress()
                address.setIndentationLeft(20);

                PdfPTable billTable = new PdfPTable(6); // one page contains 15 records
                billTable.setWidthPercentage(100);
                billTable.setWidths(new float[] { 1, 2, 5, 2, 1, 2 });
                billTable.setSpacingBefore(30.0f);
                billTable.addCell(InvoiceGenerator.getBillHeaderCell("Ref"));
                billTable.addCell(InvoiceGenerator.getBillHeaderCell("Article"));
                billTable.addCell(InvoiceGenerator.getBillHeaderCell("Description"));
                billTable.addCell(InvoiceGenerator.getBillHeaderCell("Dimension"));
                billTable.addCell(InvoiceGenerator.getBillHeaderCell("Quant"));
                billTable.addCell(InvoiceGenerator.getBillHeaderCell("Prix"));

                int pos = 1;
                for (Article article : panier.getArticles()) {

                    billTable.addCell(InvoiceGenerator.getBillRowCell(pos++ + ""));
                    billTable.addCell(InvoiceGenerator.getBillRowCell(article.getNom()));
                    billTable.addCell(InvoiceGenerator.getBillRowCell("Piece d'art"));
                    billTable.addCell(InvoiceGenerator.getBillRowCell(article.getDimension() + ""));
                    billTable.addCell(InvoiceGenerator.getBillRowCell("x1"));
                    billTable.addCell(InvoiceGenerator.getBillRowCell(article.getPrix() + " DT"));
                }

                for (int i = 0; i <= 4; i++) {
                    billTable.addCell(InvoiceGenerator.getBillRowCell(" "));
                    billTable.addCell(InvoiceGenerator.getBillRowCell(""));
                    billTable.addCell(InvoiceGenerator.getBillRowCell(""));
                    billTable.addCell(InvoiceGenerator.getBillRowCell(""));
                    billTable.addCell(InvoiceGenerator.getBillRowCell(""));
                    billTable.addCell(InvoiceGenerator.getBillRowCell(""));
                }

                PdfPTable validity = new PdfPTable(1);
                validity.setWidthPercentage(100);
                validity.addCell(InvoiceGenerator.getValidityCell(" "));
                validity.addCell(InvoiceGenerator.getValidityCell("Garantie"));
                validity.addCell(InvoiceGenerator.getValidityCell(
                        " * Les articles achetés sont livrés avec une garantie d'un an \n (si applicable)"));
                PdfPCell summaryL = new PdfPCell(validity);
                summaryL.setColspan(3);
                summaryL.setPadding(1.0f);
                billTable.addCell(summaryL);

                PdfPTable accounts = new PdfPTable(2);
                accounts.setWidthPercentage(100);
                accounts.addCell(InvoiceGenerator.getAccountsCell("Sous total"));
                accounts.addCell(InvoiceGenerator.getAccountsCellR(panier.getTotalCost() + " DT"));
                accounts.addCell(InvoiceGenerator.getAccountsCell("Tax (2.5%)"));
                accounts.addCell(InvoiceGenerator.getAccountsCellR(panier.getTotalCost() * 0.025 + " DT"));
                accounts.addCell(InvoiceGenerator.getAccountsCell("Total"));
                accounts.addCell(InvoiceGenerator.getAccountsCellR(panier.getTotalCostTTC() + " DT"));
                PdfPCell summaryR = new PdfPCell(accounts);
                summaryR.setColspan(3);
                billTable.addCell(summaryR);

                PdfPTable describer = new PdfPTable(1);
                describer.setWidthPercentage(100);
                describer.addCell(InvoiceGenerator.getdescCell(" "));

                document.open();// PDF document opened........

                document.add(image);
                document.add(irhTable);
                document.add(bill);
                document.add(name);
                document.add(contact);
                document.add(address);
                document.add(billTable);
                document.add(describer);

                document.close();

                file.close();
                System.out.println("Pdf crée avec succée");
            } catch (DocumentException | IOException e) {
            }
        }
    }
}