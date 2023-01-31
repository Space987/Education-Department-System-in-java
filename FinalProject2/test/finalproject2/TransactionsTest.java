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
public class TransactionsTest {
    
    public TransactionsTest() {
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
     * Test of makeTranBetween method, of class TransactionsModel.
     */
    @Test
    public void testMakeTranBetween() throws SQLException {
        System.out.println("makeTranBetween");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel(1111, 2222, 500);
        instance.makeTranBetween(con, 0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of reverseTransaction method, of class TransactionsModel.
     */
    @Test
    public void testReverseTransaction() throws SQLException {
        System.out.println("reverseTransaction Test when corrcet");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel();
        boolean result = instance.reverseTransaction(con, 2222);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of reverseDeposit method, of class TransactionsModel.
     */
    @Test
    public void testReverseDeposit() throws SQLException {
        System.out.println("reverseDeposit");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel();
        boolean result = instance.reverseDeposit(con, 2222);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of reverseWithdraw method, of class TransactionsModel.
     */
    @Test
    public void testReverseWithdraw() throws SQLException {
        System.out.println("reverseWithdraw");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel();
        instance.reverseWithdraw(con, 2222);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateTransfer method, of class TransactionsModel.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCalculateTransfer() throws SQLException {
        System.out.println("calculateTransfer");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel(1111, 2222, 500);
        boolean result = instance.calculateTransfer(con);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of Deposit method, of class TransactionsModel.
     */
    @Test
    public void testDeposit() throws SQLException {
        System.out.println("Deposit");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel(2222, 500);
        instance.Deposit(con);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of Withdraw method, of class TransactionsModel.
     */
    @Test
    public void testWithdraw() throws SQLException {
        System.out.println("Withdraw");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel(1111, 500);
        boolean result = instance.Withdraw(con);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of checkIsActive method, of class TransactionsModel.
     */
    @Test
    public void testCheckIsActive() throws SQLException {
        System.out.println("checkIsActive");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel();
        boolean expResult = true;
        boolean result = instance.checkIsActive(con, 1212);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of viewTransactions method, of class TransactionsModel.
     */
    @Test
    public void testViewTransactions() throws SQLException {
        System.out.println("viewTransactions");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel();
        ResultSet result = instance.viewTransactions(con);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of matchTransac method, of class TransactionsModel.
     */
    @Test
    public void testMatchTransac() throws SQLException {
        System.out.println("matchTransac");
        Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel();
        ResultSet result = instance.matchTransac(con, 1000);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of howManyActive method, of class TransactionsModel.
     */
    @Test
    public void testHowManyActive() throws SQLException {
        System.out.println("howManyActive");
         Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");
        TransactionsModel instance = new TransactionsModel();
        int result = instance.howManyActive(con);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}