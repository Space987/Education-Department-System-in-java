/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ben_1
 */
public class TellerTest {
    
    public TellerTest() {
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
     * Test of signUp method, of class TellerModel.
     */
    @Test
    public void testSignUp() throws SQLException, ClassNotFoundException {
        System.out.println("signUp test");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TellerModel instance = new TellerModel("1234", "9999" , "9999", "Kyle");
        assertEquals(true, instance.signUp(con));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of Login method, of class TellerModel.
     */
    @Test
    public void testLogin() throws ClassNotFoundException, SQLException {
        System.out.println("Login test");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TellerModel instance = new TellerModel("1234", "9999" , "9999", "Kyle");
        String expResult = "Kyle";
        String result = instance.Login(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of Check method, of class TellerModel.
     */
    @Test
    public void testCheck() throws ClassNotFoundException, SQLException {
        System.out.println("Check test");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TellerModel instance = new TellerModel();
        boolean expResult = true;
        boolean result = instance.Check(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
