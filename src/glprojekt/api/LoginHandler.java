/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api;

import glprojekt.api.database.HandlerDB;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author tomas
 */
public class LoginHandler {
    
    private SettingsHandler settings;
    
    public LoginHandler(){
        settings = new SettingsHandler();
    }
    
    public boolean loginToApp(String username, String password){
        HandlerDB handlerDB = settings.getConnection();
        
        if(handlerDB != null){
            try {
                handlerDB.executeForResult("SELECT * FROM login WHERE username='"+username+"' and password='"+password+"'");
                return true;
            } catch (HandlerDB.DBHandlerException ex) {
                ex.printStackTrace();
                return false;
            }
        }        
        
        return false;
    }
}
