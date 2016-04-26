/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api.dataholders;

import glprojekt.api.database.HandlerDB;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas
 */
public class Employee {
    
    private int id;    
    private String firstName;
    private String surname;
    private Date dateOfBirth;
    
    private Address address;
    private Contact contact;
    
    private EmploymentInforamtion employmentInforamtion;

    public Employee(int id, String firstName, String surname, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;        
    }
    
    public void parseDetails(HashMap<String,ArrayList<String>> resultSet, int row){
        parseAddress(resultSet,row);
        parseContact(resultSet,row);
        parseEmploymentInforamtion(resultSet,row);
    }
    
    private void parseAddress(HashMap<String,ArrayList<String>> resultSet, int row){
        String country = resultSet.get("Country").get(row);
        String city = resultSet.get("City").get(row);
        String street = resultSet.get("Street").get(row);
        String postcode = resultSet.get("PostCode").get(row);
        
        address = new Address(country, city, street, postcode);
    }
    
    private void parseContact(HashMap<String,ArrayList<String>> resultSet, int row){
        String email = resultSet.get("Email").get(row);
        String phone = resultSet.get("Phone").get(row);
        
        contact = new Contact(email, phone);
    }
    
    private void parseEmploymentInforamtion(HashMap<String,ArrayList<String>> resultSet, int row){
        String position = resultSet.get("PositionName").get(row);
        String Contract = resultSet.get("Contract_type").get(row);
        String wage = resultSet.get("Salary_per_hour").get(row);
        Date workSince = null;
        
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");        
            java.util.Date utilDate = format.parse(resultSet.get("Start_work").get(row));
            workSince = new Date(utilDate.getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
            workSince = new Date(0,0,0);
        }
        
        employmentInforamtion = new EmploymentInforamtion(position, Contract, Float.parseFloat(wage), workSince);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public EmploymentInforamtion getEmploymentInforamtion() {
        return employmentInforamtion;
    }

    @Override
    public String toString() {
        return firstName+" "+surname;
    }
    
    
    
    public static ArrayList<Employee> parseEmployee(HashMap<String,ArrayList<String>> resultSet){
        ArrayList<Employee> result = new ArrayList<>();
        
        int maxRows = 0;
        for(String tmpKey : resultSet.keySet()){
            maxRows = resultSet.get(tmpKey).size();
            break;
        }
        
        for(int row = 0; row < maxRows; row++){
            String id = resultSet.get("EmployeeID").get(row);
            String firstName = resultSet.get("FirstName").get(row);
            String surName = resultSet.get("SurName").get(row);
            Date dob = null;
            
            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = format.parse(resultSet.get("BirthDate").get(row));
                dob = new Date(utilDate.getTime());
            } catch (ParseException ex) {
                ex.printStackTrace();
                dob = new Date(0,0,0);
            }
            
            Employee tmpEmployee = new Employee(Integer.parseInt(id),firstName,surName,dob);
            
            tmpEmployee.parseDetails(resultSet,row);
            result.add(tmpEmployee);
        }
        
        return result;
    }
    
}
