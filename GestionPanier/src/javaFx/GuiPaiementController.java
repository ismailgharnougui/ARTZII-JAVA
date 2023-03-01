/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import services.*;
import com.stripe.exception.StripeException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.CharBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;
import models.Article;
import models.Basket;
import models.Client;
import models.Command;
import models.InvoiceGenerator;

/**
 * FXML Controller class
 *
 * @author rassa
 */
public class GuiPaiementController implements Initializable {

    @FXML
    private TextField anneeExp;

    @FXML
    private TextField carte;

    @FXML
    private TextField cvc;

    @FXML
    private TextField moisExp;

    @FXML
    private Button pay;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Pay(ActionEvent event) throws StripeException, Exception {
        ServiceCommand scom = new ServiceCommand();
        Command command;
        ServiceClient sc = new ServiceClient();
        Client client;

        ServiceBasket sb = new ServiceBasket();

        System.out.println(isNum(moisExp.getText()));
        if ((isValidVisaCardNo(carte.getText()) && (!carte.getText().isEmpty()) && (isNum(carte.getText())))
                && (!moisExp.getText().isEmpty()) && (isNum(moisExp.getText()))
                && (parseInt(anneeExp.getText()) >= LocalDate.now().getYear()) && (!anneeExp.getText().isEmpty())
                && (isNum(anneeExp.getText())) && (isNum(cvc.getText()))) {
            float f = (float) sb.get(4).getTotalCostTTC() * 32;
            int k = floatToInt(f);
            PaymentApi.pay(k);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Paiement");
            alert.setContentText("Paiement effectué avec succès \n Génération du fichier PDF");
            Optional<ButtonType> result2 = alert.showAndWait();
            if (result2.get() == ButtonType.OK) {

                client = sc.get(4);
                Basket panier = sb.get(client.getId());

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
                try {

                    OutputStream file = new FileOutputStream(new File(pdfFilename));
                    com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                    com.itextpdf.text.pdf.PdfWriter.getInstance(document, file);

                    // Inserting Image in PDF
                    com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("src/resources/logo.jpg");// Header
                                                                                                                  // Image
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
                            client.getPrenom() + " " + client.getNom()); // cl.getPrenom()+ " " +cl.getNom()
                    name.setIndentationLeft(20);
                    com.itextpdf.text.Paragraph contact = new com.itextpdf.text.Paragraph("");
                    contact.setIndentationLeft(20);
                    com.itextpdf.text.Paragraph address = new com.itextpdf.text.Paragraph(
                            "Adresse: " + client.getAddress()); // +cl.getAddress()
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
                    System.out.println("Pdf created successfully..");
                } catch (DocumentException | IOException e) {
                }

            }
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Paiement");
            alert.setContentText("Remplir les champs convenablement.");
            alert.show();
        }
    }

    private boolean isValidVisaCardNo(String text) {
        // Regex to check valid.
        // Visa Card number
        String regex = "^4[0-9]{12}(?:[0-9]{3})?$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // Convert the string to CharSequence
        CharSequence cs = CharBuffer.wrap(text);

        // Find match between given string
        // and regular expression
        // using Pattern.matcher()
        Matcher m = p.matcher(cs);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

    public static boolean isNum(String str) {
        String expression = "\\d+";
        return str.matches(expression);
    }

    public static int floatToInt(float value) {
        return (int) value;
    }

}

