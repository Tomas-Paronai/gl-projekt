/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BousH
 */
public class Search {
    
    HandlerDB handler ;
    Select select ;
    JTable table;
    
    public Search(JTable table){
        handler = new HandlerDB("localhost:3306", "employees", "root", "");
        select = new Select(handler);
        this.table = table;
    }
    
    public void getSearch(ArrayList<HashMap<String,String>> map){
        if(map.size()>0){
            String query = "SELECT * FROM employee where ";
            for(int i=0;i<map.size();i++){
                String key = "";
                for(String tmpkey : map.get(i).keySet()){
                    key = tmpkey;
                }
                query+=key+" = '"+map.get(i).get(key)+"'";
                if(i+1<map.size()){
                    query+=" and ";
                    
                }
            }
            try {
                select.selectWithQuery(query);
                table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
            } catch (HandlerDB.DBHandlerException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"No Result found");
            }
             
        }
        
    }

}
