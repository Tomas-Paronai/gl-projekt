/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt;

import glprojekt.api.database.HandlerDB;
import glprojekt.gui.Add_employee;
import glprojekt.gui.Delete_employee;

import glprojekt.gui.Login;

import glprojekt.gui.Main_screen;

import glprojekt.gui.Update_employee;

/**
 *
 * @author tomas
 */
public class Main {

    public static final String URL = "localhost:3306";
    public static final String USER = "root";
    public static final String DATABASE = "employees";
    public static final String PASS = "";
     public static  HandlerDB handler;
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {      
    /*Login log = new Login();
        log.setVisible(true);*/
     handler = new  HandlerDB("localhost:3306","employees","root","");
        Main_screen main = new Main_screen(null);
        main.setVisible(true);
    }
}
