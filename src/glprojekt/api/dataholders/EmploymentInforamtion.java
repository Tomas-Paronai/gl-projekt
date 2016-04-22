/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api.dataholders;

import java.sql.Date;

/**
 *
 * @author tomas
 */
public class EmploymentInforamtion {
    
    private String position;
    private String contract;
    private float hourWage;
    private Date employedSince;    

    public EmploymentInforamtion(String position, String contract, float hourWage, Date employedSince) {
        this.position = position;
        this.contract = contract;
        this.hourWage = hourWage;
        this.employedSince = employedSince;
    }

    public String getPosition() {
        return position;
    }

    public String getContract() {
        return contract;
    }

    public float getHourWage() {
        return hourWage;
    }

    public Date getEmployedSince() {
        return employedSince;
    }
    
    
}
