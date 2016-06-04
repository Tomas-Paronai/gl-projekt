/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt;

import glprojekt.gui.Add_employee;

import glprojekt.gui.Login;

import glprojekt.gui.Main_screen;
import glprojekt.gui.Search_Employee;
import glprojekt.gui.Settings;

import glprojekt.gui.Update_employee;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author tomas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {      
        Login log = new Login();
        log.setVisible(true);
        UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
}
    }

