/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api;

import glprojekt.api.database.HandlerDB;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author tomas
 */
public class SettingsHandler {
    
    private HandlerDB connectedDB;
    private File settingsFile;
    private XmlParser parser;
    private boolean loggedin;
    
    private EmailHandler emailHandler;
    
    private ArrayList<DBConnection> connections;
    
    public SettingsHandler(){
        settingsFile = new File("settings.xml");
        if(!settingsFile.exists()){
            try {
                settingsFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
                settingsFile = null;
            }
        }
        
        if(settingsFile != null){
            try {
                
                parser = new XmlParser(settingsFile);
                connections = parser.getConnectionsArrayList();
                connectToActive();
                
                initMail();
                
            } catch (SAXException | IOException | ParserConfigurationException ex) {
                ex.printStackTrace();                
            }
        }
    }

    public SettingsHandler(boolean loggedin) {
        this();
        this.loggedin = loggedin;        
    }

    public boolean isLoggedin() {
        return loggedin;
    }
    
    
    
    
    public boolean connectToWithOption(String url, String db, String user, String pass){
        connectedDB = new HandlerDB(url,db,user,pass);
        if(connectedDB.connect()){
            
            if(JOptionPane.showConfirmDialog(null, "Save in bookmarks?", "Save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                String name = JOptionPane.showInputDialog(null, "Name bookmark.");
                insertConnection(url,db,user,pass,name,true);
            }           
            
            connectedDB.disconnect();
            return true;
        }
        return false;
    }
    
    public boolean connectTo(String url, String db, String user, String pass){
        connectedDB = new HandlerDB(url,db,user,pass);
        if(connectedDB.connect()){            
            setConnectionsFalse();
            connectedDB.disconnect();
            return true;
        }
        return false;
    }
    
    public HandlerDB getConnection(){
        return connectedDB;
    }

    public ArrayList<DBConnection> getConnections() {
        return connections;
    }

    private void insertConnection(String url, String db, String user, String bookmark) {
        if(!parser.dataElementExists("connections")){
            parser.insertDataElement("settings", "connections");
        }
        
        int id = 1;
        while(DBConnection.idExists(String.valueOf(id), connections)){
            id++;
        }
        
        parser.insertDataElement("connections", "db_connection", String.valueOf(id));
        parser.insertValueElement("db_connection", String.valueOf(id), "url", url);
        parser.insertValueElement("db_connection", String.valueOf(id), "database", db);
        parser.insertValueElement("db_connection", String.valueOf(id), "user", user);
        parser.insertValueElement("db_connection", String.valueOf(id), "pass", "-");
        
        if(bookmark != null){
            parser.insertValueElement("db_connection", String.valueOf(id), "bookmark", bookmark);
        }
        else{
            parser.insertValueElement("db_connection", String.valueOf(id), "bookmark", "");
        }
        
        parser.reload();
        connections = parser.getConnectionsArrayList(); 
    }
    
    private void insertConnection(String url, String db, String user, String password, String bookmark, boolean active) {
        if(!parser.dataElementExists("connections")){
            parser.insertDataElement("settings", "connections");
        }
        
        setConnectionsFalse();
        
        int id = 1;
        while(DBConnection.idExists(String.valueOf(id), connections)){
            id++;
        }
        
        parser.insertDataElement("connections", "db_connection", String.valueOf(id));
        parser.insertValueElement("db_connection", String.valueOf(id), "url", url);
        parser.insertValueElement("db_connection", String.valueOf(id), "database", db);
        parser.insertValueElement("db_connection", String.valueOf(id), "user", user);
        parser.insertValueElement("db_connection", String.valueOf(id), "pass", password);
        parser.insertValueElement("db_connection", String.valueOf(id), "active", "true");
        
        if(bookmark != null){
            parser.insertValueElement("db_connection", String.valueOf(id), "bookmark", bookmark);
        }
        else{
            parser.insertValueElement("db_connection", String.valueOf(id), "bookmark", "");
        }
        
        parser.reload();
        connections = parser.getConnectionsArrayList(); 
    }

    private void setConnectionsFalse() {
        parser.changeElementValue("connections", "db_connection", "active", "false");
        parser.changeElementValue("connections", "db_connection", "pass", "-");
    }
    
    public void setConnectionSetting(String id, String elementOfValue, String newValue){
        parser.changeElementValue("connections", "db_connection", id, elementOfValue, newValue);
    }
    
    public boolean deleteConnection(String id){
        connections.remove(id);
        boolean tmpBool = parser.removeDataElement("connections", "db_connection", id);
        parser.reload();
        return tmpBool;
    }
    
    private void connectToActive() {
        for(DBConnection tmpConn : connections){
            if(tmpConn.isActive()){
                connectedDB = new HandlerDB(tmpConn.getUrl(),tmpConn.getDatabase(),tmpConn.getUser(),tmpConn.getPassword());
            }
        }
    }
    
    public void saveEmail(String email, String host, String password){
        if(!parser.dataElementExists("mailer")){
            parser.insertDataElement("settings", "mailer");
        }
        
        if(!parser.dataElementExists("email")){
            parser.insertValueElement("mailer", "email", email);
        }
        else{
            parser.changeElementValue("settings", "mailer", "email", email);
        }
        
        if(!parser.dataElementExists("host")){
            parser.insertValueElement("mailer", "host", host);
        }
        else{
            parser.changeElementValue("settings", "mailer", "host", host);
        }
        
        if(!parser.dataElementExists("password")){
            parser.insertDataElement("mailer", "password",password);
        }
        else{
            parser.changeElementValue("settings", "mailer", "password", password);
        }
    }

    private void initMail() {
        if(parser.dataElementExists("email") && parser.dataElementExists("host")){
            this.emailHandler = new EmailHandler(parser.getElementValue("mailer", "email"), parser.getElementValue("maielr", "host"));
        }
    }

    public EmailHandler getEmailHandler() {
        return emailHandler;
    }
    
    
}
