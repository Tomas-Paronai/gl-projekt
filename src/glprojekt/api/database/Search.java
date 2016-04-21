/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api.database;

import java.util.ArrayList;
import java.util.HashMap;
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
            select.selectWithQuery(query);
             table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
        }
        
    }
//     private void Search(String id){ 
//         
//        try{
//              if(handler.connect()){
//               select.selectWithQuery("SELECT * from  employee where id like '"+id+"'");
//               table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
//           }
//        }catch(Exception e){
//            
//            JOptionPane.showMessageDialog(null, e);
//        }      
//    }
//    
//    private void SearchName(String name){
//        if(handler.connect()){
//            select.selectWithQuery("SELECT * from  employee where name like '"+name+"'");
//            table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
//        }
//    }
//    private void SearchSurname(String surname){
//        if(handler.connect()){
//            select.selectWithQuery("SELECT * from  employee where surname like '"+surname+"'");
//            table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
//        }
//    }
//    private void SearchSex(String sex){
//        if(handler.connect()){
//            select.selectWithQuery("SELECT * from  employee where sex like '"+sex+"'");
//            table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
//        }
//    }
//    
//    private void SearchSexName(String name, String sex) {
//       if(handler.connect()){
//            select.selectWithQuery("SELECT * from  employee where name like '"+name+"' and sex like '"+sex+"'");
//            table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
//        }
//    }
//    
//    private void SearchSexSurname(String surname, String sex) {
//         if(handler.connect()){
//            select.selectWithQuery("SELECT * from  employee where surname like '"+surname+"' and sex like '"+sex+"'");
//            table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
//        }
//    }
//     
//    private void SearchSurnameName(String surname, String name) {
//         if(handler.connect()){
//            select.selectWithQuery("SELECT * from  employee where surname like '"+surname+"' and name like '"+name+"'");
//            table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
//        }
//    }
//    
//     private void SearchNameId(String name, String id) {
//         if(handler.connect()){
//            select.selectWithQuery("SELECT * from  employee where name like '"+name+"' and id like '"+id+"'");
//            table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
//        }
//    }
//
//    private void SearchSurnameId(String surname, String id) {
//     if(handler.connect()){
//            select.selectWithQuery("SELECT * from  employee where surname like '"+surname+"' and id like '"+id+"'");
//            table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
//        }
//    }
//    
//     private void SearchSexId(String sex, String id) {
//        if(handler.connect()){
//            select.selectWithQuery("SELECT * from  employee where sex like '"+sex+"' and id like '"+id+"'");
//            table.setModel(new DefaultTableModel(select.getData(),select.getColumns()));
//        }
//    }
}
