/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt;

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

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {      
        Add_employee employee = new Add_employee();
        employee.setVisible(true);
    }
}
