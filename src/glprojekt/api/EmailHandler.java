/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tomas
 */
public class EmailHandler {
    
    private String sender;
    private String host;
    private ArrayList<String> recipents;
    
    private Properties properties;
    private Session session;
    private MimeMessage message;
    
    public EmailHandler(String sender, String host){
        this.sender = sender;
        this.host = host;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ArrayList<String> getRecipents() {
        return recipents;
    }

    public void setRecipents(ArrayList<String> recipents) {
        this.recipents = recipents;
    }   
    
    
    public void addRecipent(String recipent){
        if(recipents == null){
            recipents = new ArrayList<>();
        }
        recipents.add(recipent);
    }
    
    public boolean prepareEmail(String subject){
        
        if(host == null || sender == null){
            return false;
        }
        
        properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        session = Session.getDefaultInstance(properties);
        message = new MimeMessage(session);
        
        try{
            message.setFrom(new InternetAddress(sender));
            for(String tmpRecipent : recipents){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(tmpRecipent));
            }
            message.setSubject(subject);            
            return true;
        } catch (AddressException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

        
        return false;
    }
    
    public boolean sendMail(String text){
        if(message == null){
            return false;
        }
        
        try {
            message.setText(text);
            Transport.send(message);
            clearMail();
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public boolean sendMail(){
        if(message == null){
            return false;
        }
        
        try {
            Transport.send(message);
            clearMail();
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void clearMail(){
        properties = null;
        session = null;
        message = null;
        recipents = null;        
    }
}
