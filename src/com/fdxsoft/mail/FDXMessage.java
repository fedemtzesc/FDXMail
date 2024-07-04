/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdxsoft.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Lic. Federico Martinez Escamilla
 */
public class FDXMessage {
    //Parametros de acceso al servidor SMTP

    private String SMTPServer;
    private String SMTPMailAcount;
    private String SMTPUser;
    private String SMTPPassword;
    private String SMTPPort;

    //Parametros del Mensaje
    private String from = "";
    private String fromName = "";
    private String to = "";
    private String bcc = "";
    private String cc = "";
    private String subject = "";
    private String body = "";
    public List<String> attachments = new ArrayList<String>();
    public String toList[];
    private Properties props = new Properties();

    //Parametros de manejo interno
    private boolean hasAttachments;

    public FDXMessage() {
        loadConfig();
        hasAttachments = false;
    }

    private boolean loadConfig() {
        try {
            getProps().load(FDXMessage.class.getClassLoader().getResourceAsStream("com/fdxsoft/config/email.properties"));
            from = getProps().getProperty("mail.account");
            SMTPMailAcount = getProps().getProperty("mail.account");
            SMTPUser = getProps().getProperty("mail.user");
            SMTPPassword = getProps().getProperty("mail.password");
            SMTPServer = getProps().getProperty("mail.smtp.host");
            SMTPPort = getProps().getProperty("mail.smtp.port");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param fileName Nombre del archivo a anexar al correo
     */
    public void addAttachment(String fileName) {
        attachments.add(fileName);
        hasAttachments = true;
    }

    /**
     * @return the SMTPServer
     */
    public String getSMTPServer() {
        return SMTPServer;
    }

    /**
     * @param SMTPServer the SMTPServer to set
     */
    public void setSMTPServer(String SMTPServer) {
        this.SMTPServer = SMTPServer;
    }

    /**
     * @return the SMTPUser
     */
    public String getSMTPUser() {
        return SMTPUser;
    }

    /**
     * @param SMTPUser the SMTPUser to set
     */
    public void setSMTPUser(String SMTPUser) {
        this.SMTPUser = SMTPUser;
    }

    /**
     * @return the SMTPPassword
     */
    public String getSMTPPassword() {
        return SMTPPassword;
    }

    /**
     * @param SMTPPassword the SMTPPassword to set
     */
    public void setSMTPPassword(String SMTPPassword) {
        this.SMTPPassword = SMTPPassword;
    }

    /**
     * @return the SMTPPort
     */
    public String getSMTPPort() {
        return SMTPPort;
    }

    /**
     * @param SMTPPort the SMTPPort to set
     */
    public void setSMTPPort(String SMTPPort) {
        this.SMTPPort = SMTPPort;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        to = to.replace(';', ',');
        this.toList = to.split(",");
    }

    /**
     * @return the bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * @param bcc the bcc to set
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * @return the cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the props
     */
    public Properties getProps() {
        return props;
    }

    /**
     * @return the SMTPMailAcount
     */
    public String getSMTPMailAcount() {
        return SMTPMailAcount;
    }

    /**
     * @param SMTPMailAcount the SMTPMailAcount to set
     */
    public void setSMTPMailAcount(String SMTPMailAcount) {
        this.SMTPMailAcount = SMTPMailAcount;
    }

    /**
     * @return the hasAttachments
     */
    public boolean isHasAttachments() {
        return hasAttachments;
    }

    /**
     * @return the fromName
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * @param fromName the fromName to set
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
}
