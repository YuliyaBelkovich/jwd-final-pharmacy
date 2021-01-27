package com.epam.jwd.service.mail;

import com.epam.jwd.util.PDFCreatorUtil;
import com.epam.jwd.util.PropertyReaderUtil;
import com.epam.jwd.util.SessionCreatorUtil;
import com.itextpdf.text.DocumentException;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Properties;

public class PDFMailThread extends Thread {

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private String pdfText;
    private static final Properties PROPERTIES = new PropertyReaderUtil().loadProperties("src/main/resources/mail.properties");


    public PDFMailThread(String sendToEmail, String mailSubject, String mailText, String pdfText) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.pdfText = pdfText;

    }

    protected void init() {

        Session mailSession = (new SessionCreatorUtil(PROPERTIES)).createSession();
        File file = null;
        try {
            file = PDFCreatorUtil.createPDF(pdfText);
        } catch (IOException | DocumentException | URISyntaxException e) {
            e.printStackTrace();
        }
        mailSession.setDebug(true);

        message = new MimeMessage(mailSession);
        try {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mailText, "text/html");
            message.setSubject(mailSubject);
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(file);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (AddressException e) {
            System.err.print("Incorrect email address:" + sendToEmail + " " + e);

        } catch (MessagingException e) {
            System.err.print("Message generation error: " + e);
// in log file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        init();
        try {

            Transport.send(message);
        } catch (MessagingException e) {
            System.err.print("Message sending error: " + e);

        }
    }
}
