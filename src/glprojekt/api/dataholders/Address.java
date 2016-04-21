/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api.dataholders;

/**
 *
 * @author tomas
 */
public class Address {
    
    private String country;
    private String city;
    private String street;
    private String postCode;

    public Address(String country, String city, String street, String postCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }       
    
    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostCode() {
        return postCode;
    }
    
    
}
