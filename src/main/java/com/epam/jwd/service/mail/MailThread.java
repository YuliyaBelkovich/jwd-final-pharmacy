package com.epam.jwd.service.mail;

import com.epam.jwd.util.PropertyReaderUtil;
import com.epam.jwd.util.SessionCreatorUtil;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailThread extends Thread {
    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;

    private static final Properties PROPERTIES = new PropertyReaderUtil().loadProperties("C:/Users/Asus/IdeaProjects/jwd-final-pharmacy/src/main/resources/mail.properties");

    public MailThread(String sendToEmail, String mailSubject, String mailText) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
    }

    protected void init() {

        Session mailSession = (new SessionCreatorUtil(PROPERTIES)).createSession();
        mailSession.setDebug(true);

        message = new MimeMessage(mailSession);
        try {

            message.setSubject(mailSubject);
            message.setContent(mailText, "text/html");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (AddressException e) {
            System.err.print("Incorrect email address:" + sendToEmail + " " + e);

        } catch (MessagingException e) {
            System.err.print("Message generation error: " + e);
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

