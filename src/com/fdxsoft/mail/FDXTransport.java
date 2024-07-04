/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdxsoft.mail;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author Lic. Federico Martinez Escamilla
 * Clase para el envio de correos electronicos
 */
public class FDXTransport {
    // Create the message part

    private BodyPart messageBodyPart = new MimeBodyPart();
    private Multipart multipart = new MimeMultipart();
    
    public boolean loadAttachments(List<String> attachments, String msg) {
        try {
            messageBodyPart.setContent(msg,"text/html");
            //messageBodyPart.setText(msg);
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            for (int i = 0; i < attachments.size(); i++) {
                String filename = (String) attachments.get(i);
                File f = new File(filename);
                if (!f.exists()) {
                    continue;
                }
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
            }
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(FDXTransport.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @SuppressWarnings("static-access")
    public synchronized boolean sendMail(FDXMessage msg) throws Exception {
        Authenticator auth = new FDXAuthenticator(msg.getSMTPUser(), msg.getSMTPPassword());
        Session session;
        try {
            session = Session.getInstance(msg.getProps(), auth);
        } catch (Exception e) {
            session = Session.getDefaultInstance(msg.getProps(), auth);
        }

        Message message = new MimeMessage(session);
        
        message.setHeader("Disposition-Notification-To", "ventas@fdxsoft.com"); //Solicitud de confirmacion de entrega
        InternetAddress[] fromAddress = new InternetAddress[1];
        try {
            fromAddress[0] = new InternetAddress(msg.getSMTPMailAcount(), msg.getFromName());

            InternetAddress toAddress = new InternetAddress();
            InternetAddress bccAddress = new InternetAddress();
            InternetAddress ccAddress = new InternetAddress();

            for(int i=0;i<msg.toList.length;i++){
                toAddress = new InternetAddress(msg.toList[i].trim());
                message.addRecipient(RecipientType.TO, toAddress);
            }
                        
            if (!msg.getBcc().trim().equals("")) {
                bccAddress = new InternetAddress(msg.getBcc());
                message.addRecipient(RecipientType.BCC, bccAddress);
            }
            if (!msg.getCc().trim().equals("")) {
                ccAddress = new InternetAddress(msg.getCc());
                message.addRecipient(RecipientType.CC, ccAddress);
            }
            message.addFrom(fromAddress);
            
            message.setSubject(msg.getSubject());
            message.setContent(msg.getBody(), "text/html");

            Transport tr = session.getTransport("smtp");

            if (msg.isHasAttachments()) {
                if (loadAttachments(msg.attachments, msg.getBody())) {
                    message.setContent(multipart);
                }
            }
            tr.send(message);

            tr.close();
        } catch (UnsupportedEncodingException ex) {
            throw new Exception(ex.getMessage());
        } catch (MessagingException ex) { 
            throw new Exception(ex.getMessage());
        }
        return true;
    }
}
