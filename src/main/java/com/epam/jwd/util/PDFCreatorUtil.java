package com.epam.jwd.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFCreatorUtil {
    private static int id = 654;

    public static File createPDF(String text) throws IOException, URISyntaxException, DocumentException {
        Document document = new Document();
        id++;
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Pharmacy_invoice_" + id + ".pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.GRAY);
        Font font1 = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);
        Paragraph paragraph1 = new Paragraph("SACRED HEART PHARMACY INVOICE", font1);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingBefore(50);
        document.add(paragraph1);
        document.add(paragraph);
        document.close();
        return new File("Pharmacy_invoice_" + id + ".pdf");
    }
}
