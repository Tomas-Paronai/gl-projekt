/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.gui.Images.queries;

/**
 *
 * @author dominik
 */
public class QueryD { 
    private String addToEmployee = "INSERT INTO employee (`ID`, `FirstName`, `LastName`, ``Sex``, `Birth_date`, `Start_date`) VALUES (?,?,?,?,?,?)";
    private String addToContact = "INSERT INTO `contact` "
                                    +"(`Employee_id`, `Phone`, `Email`) "
                                    + "VALUES (?,?,?)";
    
    public QueryD(){
        
    }

    public String getAddToEmployee() {
        return addToEmployee;
    }

    public void setAddToEmployee(String addToEmployee) {
        this.addToEmployee = addToEmployee;
    }

    public String getAddToContact() {
        return addToContact;
    }

    public void setAddToContact(String addToContact) {
        this.addToContact = addToContact;
    }
    
    
}
