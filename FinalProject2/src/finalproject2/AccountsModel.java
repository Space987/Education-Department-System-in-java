/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject2;

import static finalproject2.BankController.langChoice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author ben_1
 */
public class AccountsModel extends BankController{

    // creation of private objects
    private int clientId;
    private int accountNumber;
    private String accountType;
    private double balance = 0.0;
    private boolean isActive = true;

    //Constructor
    public AccountsModel(int accNum, int id, String type) {

        accountNumber = accNum;
        clientId = id;
        accountType = type;

    }

    //Empty Constructor
    public AccountsModel() {

    }

    // This method creates the accounts 
    public boolean createAccount(Connection con) {

        String date = "";
        Date date2 = new Date();

        if (langChoice == 1) {

            DateFormat formatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US);
            date = formatter.format(date2);

        } else if (langChoice == 2) {
            DateFormat formatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.FRANCE);
            date = formatter.format(date2);

        }

        boolean check = false;
        try {

            // the string that hold the sql statement
            String accSql2 = "SELECT AccountNumber FROM Accounts";
            PreparedStatement accStmt2 = con.prepareStatement(accSql2);
            ResultSet accResult2 = accStmt2.executeQuery();

            while (accResult2.next()) {
                if (accountNumber == accResult2.getInt("AccountNumber")) {
                    check = true;
                }
            }
            accStmt2.close();
            accResult2.close();

            if (!check) {

                // the string that hold the sql statement
                String accSql = "INSERT INTO Accounts (AccountNumber, ClientId, AccountType, OpenDate, Balance, isActive) VALUES ('" + accountNumber + "','" + clientId + "','" + accountType + "','" + date + "','" + balance + "','" + isActive + "')";
                PreparedStatement accStmt = con.prepareStatement(accSql);
                accStmt.executeUpdate();

                accStmt.close();
                con.close();
                return true;
            }

        } catch (java.sql.SQLException e) {

            // The exception handeling
            System.out.println("SQLEXCEPTION:" + e);
            while (e != null) {
                System.out.println("SQLSTATE:" + e.getSQLState());
                System.out.println("Message:" + e.getMessage());
                e = e.getNextException();
                System.out.println("");
            }

        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);
            e.printStackTrace();
        }

        return false;
    }

    // This method is for the viewing of the Accounts 
    public ResultSet viewAccounts(Connection con) {

        ResultSet accResult = null;
        try {

            // the string that holds the select sql statment 
            String accSql = "SELECT * FROM Accounts";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            accResult = accStmt.executeQuery();
            if (accResult.next() == false) {
                return null;
            } else {
                do {
                    return accResult;
                } while (accResult.next());
            }

        } catch (java.sql.SQLException e) {

            System.out.println("SQLEXCEPTION:" + e);
            while (e != null) {
                System.out.println("SQLSTATE:" + e.getSQLState());
                System.out.println("Message:" + e.getMessage());
                e = e.getNextException();
                System.out.println("");
            }

        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);
            e.printStackTrace();
        }

        return accResult;
    }
    
     public ResultSet viewActiveAccounts(Connection con) {

        ResultSet accResult = null;
        try {

            // the string that holds the select sql statment 
            String accSql = "SELECT * FROM Accounts WHERE isActive != 'false'";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            accResult = accStmt.executeQuery();
            
            if (accResult.next() == false) {
                return null;
            } else {
                do {
                    return accResult;
                } while (accResult.next());
            }



        } catch (java.sql.SQLException e) {

            System.out.println("SQLEXCEPTION:" + e);
            while (e != null) {
                System.out.println("SQLSTATE:" + e.getSQLState());
                System.out.println("Message:" + e.getMessage());
                e = e.getNextException();
                System.out.println("");
            }

        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);
            e.printStackTrace();
        }

        return accResult;
    }


    // This method is used for matching the client id with the id that is inputed 
    public boolean matchClients(Connection con, int id) {

        boolean match = false;
        try {

            // A string select statement that that checks for the matching id using the sql statement 
            String clientSql = "SELECT ClientId FROM Clients WHERE ClientId ='" + id + "'";
            PreparedStatement clientStmt = con.prepareStatement(clientSql);
            ResultSet clientResult = clientStmt.executeQuery();

            if (clientResult.next()) {
                match = true;
                con.close();
                clientStmt.close();
                clientResult.close();
                return match;
            }
        } catch (java.sql.SQLException e) {
            System.out.println("SQLEXCEPTION:" + e);

        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);
        }

        return match;
    }

    // Method used to deactivate an account 
    public boolean DeActivate(Connection con, int id) {

        boolean f = false;

        try {

            // the string that holds the select sql statment 
            String clientSql = "SELECT Balance FROM Accounts WHERE AccountNumber='" + id + "'";
            PreparedStatement clientStmt = con.prepareStatement(clientSql);
            ResultSet clientResult = clientStmt.executeQuery();

            // Makes sure that the balance is 0
            if (clientResult.getInt("Balance") != 0) {

                return false;
            } else {

                clientStmt.close();
                clientResult.close();

                // the string that holds the select sql statment that updates the specific account 
                String updateSql = "UPDATE Accounts SET isActive = '" + f + "' WHERE AccountNumber ='" + id + "'";
                PreparedStatement client2 = con.prepareStatement(updateSql);
                client2.executeUpdate();
                con.close();
                client2.close();
                return true;
            }

        } catch (java.sql.SQLException e) {
            System.out.println("SQLEXCEPTION:" + e);

        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);

        }

        return false;

    }

    // Method used reactivate an account using the client ID 
    public void ReActivate(Connection con, int id) {

        boolean t = true;
        try {

            // the string that holds the select sql statment 
            String updateSql = "UPDATE Accounts SET isActive = '" + t + "' WHERE AccountNumber ='" + id + "'";
            PreparedStatement clientStmt = con.prepareStatement(updateSql);
            clientStmt.executeUpdate();

        } catch (java.sql.SQLException e) {
            System.out.println("SQLEXCEPTION:" + e);

        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);
        }

    }

    // This method is used to display a specific Account that the user picks 
    public ResultSet viewSpecificAccount(Connection con, int id) {

        ResultSet accResult = null;
        try {

            // the string that holds the select sql statment 
            String accSql = "SELECT * FROM Accounts WHERE AccountNumber ='" + id + "'";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            accResult = accStmt.executeQuery();

            if (accResult.next() == false) {
                return null;
            } else {
                do {
                    return accResult;
                } while (accResult.next());
            }
        } catch (java.sql.SQLException e) {

            System.out.println("SQLEXCEPTION:" + e);
            while (e != null) {
                System.out.println("SQLSTATE:" + e.getSQLState());
                System.out.println("Message:" + e.getMessage());
                e = e.getNextException();
                System.out.println("");
            }

        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);
            e.printStackTrace();
        }

        return accResult;
    }

    public ResultSet matchAccount(Connection con, int id) {

        ResultSet accResult = null;
        try {

            // the string that holds the select sql statment 
            String accSql = "SELECT * FROM Accounts WHERE AccountNumber ='" + id + "'";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            accResult = accStmt.executeQuery();

            // Returns only the specific Accounts that the user wants 
            if (accResult.next() == false) {
                return null;
            } else {
                do {
                    return accResult;
                } while (accResult.next());
            }

        } catch (java.sql.SQLException e) {

            System.out.println("SQLEXCEPTION:" + e);
            while (e != null) {
                System.out.println("SQLSTATE:" + e.getSQLState());
                System.out.println("Message:" + e.getMessage());
                e = e.getNextException();
                System.out.println("");
            }

        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);
            e.printStackTrace();
        }

        return accResult;
    }

}
