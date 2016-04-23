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
                
            } catch (SAXException | IOException | ParserConfigurationException ex) {
                ex.printStackTrace();                
            }
        }
    }
    
    public boolean connectToWithOption(String url, String db, String user, String pass){
        connectedDB = new HandlerDB(url,db,user,pass);
        if(connectedDB.connect()){
            
            if(JOptionPane.showConfirmDialog(null, "Save in bookmarks?", "Save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                insertConnection(url,db,user,pass,true);
            }           
            
            connectedDB.disconnect();
            return true;
        }
        return false;
    }
    
    public boolean connectTo(String url, String db, String user, String pass){
        connectedDB = new HandlerDB(url,db,user,pass);
        if(connectedDB.connect()){   
            
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

    private void insertConnection(String url, String db, String user) {
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
        
        parser.reload();
        connections = parser.getConnectionsArrayList(); 
    }
    
    private void insertConnection(String url, String db, String user, String password, boolean active) {
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
        
        parser.reload();
        connections = parser.getConnectionsArrayList(); 
    }

    private void setConnectionsFalse() {
        parser.changeElementValue("connections", "db_connection", "active", "false");
        parser.changeElementValue("connections", "db_connection", "pass", "-");
    }

    private void connectToActive() {
        for(DBConnection tmpConn : connections){
            if(tmpConn.isActive()){
                connectedDB = new HandlerDB(tmpConn.getUrl(),tmpConn.getDatabase(),tmpConn.getUser(),tmpConn.getPassword());
            }
        }
    }
    
    
}
