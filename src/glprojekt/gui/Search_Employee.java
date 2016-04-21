package glprojekt.gui;

import glprojekt.api.OnDataChange;
import glprojekt.api.database.HandlerDB;
import glprojekt.api.database.HandlerDB.DBHandlerException;
import glprojekt.api.database.Search;
import glprojekt.api.database.Select;
import glprojekt.gui.Images.queries.Query;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Search_Employee extends ParentWindow {

    private ArrayList<HashMap<String, String>> result;
    JTable table;


    Search_Employee(OnDataChange listener, JTable EmployeesTable) {
        super(listener);
        initComponents();
        result = new ArrayList<>();
        this.table = EmployeesTable;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSearchId = new javax.swing.JTextField();
        jSearchSurname = new javax.swing.JTextField();
        jSearchName = new javax.swing.JTextField();
        jSearchSex = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSearchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Employee Id :");

        jLabel2.setText("Name :");

        jLabel3.setText("Surname :");

        jLabel4.setText("Sex :");

        jLabel5.setText("Search employees");

        jSearchButton.setText("Search");
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSearchId)
                            .addComponent(jSearchSurname)
                            .addComponent(jSearchName)
                            .addComponent(jSearchSex, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel5)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jSearchId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jSearchSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jSearchSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        checkEmpty();
        this.dispose();
    }//GEN-LAST:event_jSearchButtonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchId;
    private javax.swing.JTextField jSearchName;
    private javax.swing.JTextField jSearchSex;
    private javax.swing.JTextField jSearchSurname;
    // End of variables declaration//GEN-END:variables

    private void checkEmpty() {
        if (jSearchId.getText().isEmpty() == false) {
            String id = jSearchId.getText().toString();
            HashMap<String, String> tmpHash = new HashMap<String, String>();
            tmpHash.put("id", id);
            result.add(tmpHash);
        }
        if(jSearchName.getText().isEmpty()==false){
           String name = jSearchName.getText(); 
           HashMap<String, String> tmpHash = new HashMap<String, String>();
           tmpHash.put("name", name);
            result.add(tmpHash);
        }
        
        if(jSearchSurname.getText().isEmpty()==false){
           String surname = jSearchSurname.getText(); 
           HashMap<String, String> tmpHash = new HashMap<String, String>();
           tmpHash.put("Surname", surname);
            result.add(tmpHash);
        }
        if(jSearchSex.getText().isEmpty()==false){
           String sex = jSearchSex.getText(); 
           HashMap<String, String> tmpHash = new HashMap<String, String>();
           tmpHash.put("Sex", sex);
            result.add(tmpHash);
        }
        if (result.isEmpty() == false) {
            Search search = new Search(table);
            search.getSearch(result);
        }
    }
}
