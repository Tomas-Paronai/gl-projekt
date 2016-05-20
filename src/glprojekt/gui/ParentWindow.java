/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.gui;

import glprojekt.Main;
import glprojekt.api.OnDataChange;
import glprojekt.api.SettingsHandler;
import glprojekt.api.database.HandlerDB;

/**
 *
 * @author tomas
 */
public abstract class ParentWindow  extends javax.swing.JFrame{

    private OnDataChange listener;
    protected HandlerDB handlerDB;
    protected SettingsHandler settingsHandler;
       
    public ParentWindow(OnDataChange listener){        
        settingsHandler = new SettingsHandler();
        handlerDB = settingsHandler.getConnection();
        this.listener = listener;        
    }

    public ParentWindow() {
        settingsHandler = new SettingsHandler();
        handlerDB = settingsHandler.getConnection();
    }

    public void setListener(OnDataChange listener) {
        this.listener = listener;
    }     
    
    public void notifyDataChange(){
        if(listener != null){
            listener.dataChanged();
        }
    }
    
    
}
