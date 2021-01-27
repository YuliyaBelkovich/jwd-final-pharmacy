package com.epam.jwd.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFCreatorUtil {
    private static int id=654;

    public static File createPDF(String text) throws IOException, URISyntaxException, DocumentException {
        Document document = new Document();
        id++;
       PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream("Pharmacy_invoice_" + id + ".pdf"));
        Path path = Paths.get(ClassLoader.getSystemResource("pharmay_logo.png").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(text, font);
        document.add(img);
        document.addHeader("SACRED HEART PHARMACY", "INVOICE");
        document.add(chunk);
        document.close();
        return new File("Pharmacy_invoice_" + id + ".pdf");
    }
}
