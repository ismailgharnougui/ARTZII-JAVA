/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import models.Artiicle;
import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
public class PDFExporter {
    
    
    
    

    public static void exportToPDF(TableView tableView, String fileName) throws IOException {
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        
             // Create a content stream to draw on the new page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            
            // Define font styles and sizes
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Article DATA") / 1000 * 16;
            float startX = (page.getMediaBox().getWidth() - titleWidth) / 2;
            float startY = page.getMediaBox().getHeight() - 50;
            float cellMargin = 10;
            float tableWidth = page.getMediaBox().getWidth() - (cellMargin * 2);
            float tableHeight = page.getMediaBox().getHeight() - 200;
            float rowHeight = 20;
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            float colWidth = tableWidth / tableView.getColumns().size();
            
            // Draw the title
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);
            contentStream.showText("Article DATA");
            contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("Table View Export");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(100, 650);
        contentStream.showText("Table Data");
        contentStream.endText();

        System.out.println(tableView.getItems());
        ObservableList<Artiicle> data = tableView.getItems();
        int numOfColumns = 2;
        float[] columnWidths = new float[numOfColumns];

        for (int i = 0; i < numOfColumns; i++) {
            TableColumn column = (TableColumn) tableView.getColumns().get(i);
            columnWidths[i] = (tableWidth / numOfColumns);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
            contentStream.newLineAtOffset(100 + (columnWidths[i] * i), 630);
            contentStream.showText(column.getText());
            contentStream.endText();
        }

        float tableTopY = 610;

        for (Artiicle row : data) {
            for (int i = 0; i < numOfColumns; i++) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.newLineAtOffset(100 + (columnWidths[i] * i), tableTopY);
                contentStream.showText(row.getArtLib());  
            contentStream.showText(row.getArtDesc());



                contentStream.endText();
            }
            tableTopY -= 20;
        }

        contentStream.close();

        document.save(fileName);
        document.close();

    }
}
