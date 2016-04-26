/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api;

import java.util.ArrayList;

/**
 *
 * @author tomas
 */
public class DBConnection {
    
    private String id;
    private String url;
    private String database;
    private String user;
    private String password;
    private String bookmark;
    private boolean active;

    public DBConnection(String id, String url, String database, String user, boolean active) {
        this(id,url,database,user);
        this.active = active;
    }

    DBConnection(String id, String url, String database, String user) {
        this.id = id;
        this.url = url;
        this.database = database;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }    
    
    
    public String getUrl() {
        return url;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        if(bookmark == null){
            return database;
        }
        else{
            return bookmark;
        }
    }
    
    public static boolean idExists(String id, ArrayList<DBConnection> connections ){
        for(DBConnection tmpConn : connections){
            if(tmpConn.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void setPassword(String pass){
        password = pass;
    }
    
    public String getPassword() {
        return password;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }  
    
    
}
