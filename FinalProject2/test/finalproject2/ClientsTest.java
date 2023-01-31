/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class ClientsTest {
    
    public ClientsTest() {
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
     * Test of createClient method, of class ClientModel.
     */
    @Test
    public void testCreateClient() throws ClassNotFoundException, SQLException {
        System.out.println("createClient test");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        ClientModel instance = new ClientModel(2222, "Jimmy", "Newtron", "Drivers", "MTLS");
        boolean expResult = true;
        boolean result = instance.createClient(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of viewClient method, of class ClientModel.
     * @throws java.sql.SQLException
     */
    @Test
    public void testViewClient() throws SQLException {
        System.out.println("viewClient test");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        ClientModel instance = new ClientModel();
        ResultSet result = instance.viewClient(con);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of viewSpecificClient method, of class ClientModel.
     */
    @Test
    public void testViewSpecificClient() throws SQLException {
        System.out.println("viewSpecificClient");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        ClientModel instance = new ClientModel();
        int id = 1111;
        ResultSet result = instance.viewSpecificClient(con, id);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of updateClients method, of class ClientModel.
     * @throws java.sql.SQLException
     */
    @Test
    public void testUpdateClients() throws SQLException {
        System.out.println("updateClients");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        ClientModel instance = new ClientModel();
        instance.updateClients(con, 1221, "Max", "Newt", "Driv", "CHI");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of matchClients method, of class ClientModel.
     */
    @Test
    public void testMatchClients() throws SQLException {
        System.out.println("matchClients");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        ClientModel instance = new ClientModel();
        ResultSet result = instance.matchClients(con, 1111);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
