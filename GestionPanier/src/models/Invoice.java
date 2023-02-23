package models;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.pdfa.PdfADocument;
import java.io.*;

public class Invoice {
    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException {
        
        String path= "facture2.pdf";
        PdfWriter pdfWriter=new PdfWriter(path);
        PdfDocument pdfDocument= new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);
        
        document.add(new Paragraph("Helqjnevoihnqioevnoiqnedvonzsovnionzvoin"));
        document.close();
  } 
    
}