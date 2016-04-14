/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.gui;

import glprojekt.api.OnDataChange;
import glprojekt.api.WindowDataHandler;

/**
 *
 * @author tomas
 */
public abstract class ParentWindow  extends javax.swing.JFrame{

    protected WindowDataHandler dataHandler;
    private OnDataChange listener;
       
    public ParentWindow(OnDataChange listener){
        dataHandler = new WindowDataHandler();
        this.listener = listener;        
    }
    
    public void notifyDataChange(){
        if(listener != null){
            listener.dataChanged();
        }
    }
    
    
}
