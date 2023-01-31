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
public class AccountsTest {
    
    public AccountsTest() {
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
     * Test of createAccount method, of class Accounts.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCreateAccount() throws SQLException {
        System.out.println("createAccount");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        AccountsModel instance = new AccountsModel(4444, 1221, "Checkings");
        boolean expResult = true;
        boolean result = instance.createAccount(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of viewAccounts method, of class Accounts.
     */
    @Test
    public void testViewAccounts() throws SQLException {
        System.out.println("viewAccounts");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        AccountsModel instance = new AccountsModel();
        ResultSet result = instance.viewAccounts(con);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of matchClients method, of class Accounts.
     * @throws java.sql.SQLException
     */
    @Test
    public void testMatchClients() throws SQLException {
        System.out.println("matchClients");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        AccountsModel instance = new AccountsModel();
        boolean expResult = true;
        boolean result = instance.matchClients(con, 1111);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of DeActivate method, of class Accounts.
     */
    @Test
    public void testDeActivate() throws SQLException {
        System.out.println("DeActivate");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        AccountsModel instance = new AccountsModel();
        boolean expResult = true;
        boolean result = instance.DeActivate(con, 4444);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of ReActivate method, of class Accounts.
     */
    @Test
    public void testReActivate() throws SQLException {
        System.out.println("ReActivate");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        AccountsModel instance = new AccountsModel();
        instance.ReActivate(con, 4444);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of viewSpecificAccount method, of class Accounts.
     */
    @Test
    public void testViewSpecificAccount() throws SQLException {
        System.out.println("viewSpecificAccount");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        AccountsModel instance = new AccountsModel();
        ResultSet result = instance.viewSpecificAccount(con, 4444);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of matchAccount method, of class Accounts.
     */
    @Test
    public void testMatchAccount() throws SQLException {
        System.out.println("matchAccount");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        AccountsModel instance = new AccountsModel();
        ResultSet result = instance.matchAccount(con, 4444);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of viewActiveAccounts method, of class AccountsModel.
     */
    @Test
    public void testViewActiveAccounts() throws SQLException {
        System.out.println("ViewActiveAccounts");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        AccountsModel instance = new AccountsModel();
        ResultSet result = instance.viewActiveAccounts(con);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
