/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glprojekt.api.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BousH
 */
public class HandlerDBTest {
    
    public HandlerDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connect method, of class HandlerDB.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        HandlerDB instance = new HandlerDB("localhost:3306", "employees", "root", "");
        boolean expResult = true;
        boolean result = instance.connect();
        assertEquals(expResult, result);
          }

    /**
     * Test of disconnect method, of class HandlerDB.
     */
    @Test
    public void testDisconnect() {
        System.out.println("disconnect");
        HandlerDB instance = new HandlerDB("localhost:3306", "employees", "root", "");
        instance.disconnect();
        
    }

    

    /**
     * Test of prepareStatement method, of class HandlerDB.
     */
    @Test
    public void testPrepareStatement() throws Exception {
        System.out.println("prepareStatement");
        String query = "query";
        HandlerDB instance =  new HandlerDB("localhost:3306", "employees", "root", "");
        instance.prepareStatement(query);
        
    }

    /**
     * Test of updateStatement method, of class HandlerDB.
     */
    @Test
    public void testUpdateStatement_int_String() {
        System.out.println("updateStatement");
        int position = 4;
        String value = "Update";
        HandlerDB instance = new HandlerDB("localhost:3306", "employees", "root", "");
        instance.updateStatement(position, value);
      
    }

   
    @Test
    public void testGetLastID() {
        System.out.println("getLastID");
        HandlerDB instance =  new HandlerDB("localhost:3306", "employees", "root", "");
        int expResult = 21;
        int result = instance.getLastID();
        assertEquals(expResult, result);
        
    }

    
}
