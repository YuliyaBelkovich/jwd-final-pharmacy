package com.epam.jwd.service.mail;

public class MailService {
    private static MailService instance = new MailService();

    private MailService(){}

    public static MailService getInstance() {
        return instance;
    }

    public void sendTextEmail(String sendToEmail, String mailSubject, String mailText) {
        MailThread mailThread = new MailThread(sendToEmail, mailSubject, mailText);
        mailThread.start();
    }

    public void sendPDFEmail(String sendToEmail, String mailSubject, String mailText, String pdfText) {
        PDFMailThread mailThread = new PDFMailThread(sendToEmail, mailSubject, mailText, pdfText);
        mailThread.start();
    }

}
