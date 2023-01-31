/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ben_1
 */
public class ClientModel extends BankController{

    // creation of private objects
    private int clientId;
    private String firstName;
    private String lastName;
    private String identification;
    private String address;

    // Constructor 
    public ClientModel(int id, String fname, String lname, String identity, String addy) {

        clientId = id;
        firstName = fname;
        lastName = lname;
        identification = identity;
        address = addy;
    }

    // Constructor with paramenter
    public ClientModel(int id) {

        clientId = id;

    }

    // Empty Constructor 
    public ClientModel() {

    }

    // This method creates the accounts
    public boolean createClient(Connection con) {

        boolean check = false;
        try {

            // the string that hold the sql statement
            String accSql = "SELECT ClientId FROM Clients";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            ResultSet accResult = accStmt.executeQuery();

            // Check that the id is unique 
            while (accResult.next()) {
                if (clientId == accResult.getInt("ClientId")) {
                    check = true;
                }
            }
            accStmt.close();
            accResult.close();

            // If Check equals false then insert into databse 
            if (!check) {
                String accSql2 = "INSERT INTO Clients (ClientId, FirstName, LastName, Identification, Address) VALUES ('" + clientId + "','" + firstName + "','" + lastName + "','" + identification + "','" + address + "')";
                PreparedStatement accStmt2 = con.prepareStatement(accSql2);
                accStmt2.executeUpdate();

                accStmt2.close();
                con.close();
                return true;
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

        return false;
    }

    // This method is for the viewing of the ClientModel 
    public ResultSet viewClient(Connection con) {

        ResultSet accResult = null;
        try {

            // the string that holds the select sql statment 
            String accSql = "SELECT * FROM Clients";
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

            // This is used to catch all of the execption
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

    // Used to see the specific Client that the user pickced
    public ResultSet viewSpecificClient(Connection con, int id) {

        ResultSet accResult = null;
        try {

            // the string that holds the select sql statment 
            String accSql = "SELECT * FROM Clients WHERE ClientId ='" + id + "'";
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

    // This method is used to update the client
    public void updateClients(Connection con, int id, String fname, String lname, String identity, String address) {

        try {
            // the string that holds the select sql statment to update the client
            String updateSql = "UPDATE Clients SET FirstName ='" + fname + "' ,LastName ='" + lname + "' ,Identification ='" + identity + "' ,Address ='" + address + "' WHERE ClientId ='" + id + "'";
            PreparedStatement client2 = con.prepareStatement(updateSql);
            client2.executeUpdate();
            client2.close();

        } catch (java.sql.SQLException e) {
            System.out.println("SQLEXCEPTION:" + e);

        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);
        }
    }

    // This method is used for matching the client id with the id that is inputed 
    public ResultSet matchClients(Connection con, int id) {

        ResultSet clientResult = null;
        try {

            // A string select statement that that checks for the matching id using the sql statement 
            String clientSql = "SELECT * FROM Clients WHERE ClientId ='" + id + "'";
            PreparedStatement clientStmt = con.prepareStatement(clientSql);
            clientResult = clientStmt.executeQuery();

            
            if (clientResult.next() == false) {
                return null;
            } else {
                do {
                    return clientResult;
                } while (clientResult.next());
            }
        } catch (java.sql.SQLException e) {
            System.out.println("SQLEXCEPTION:" + e);

        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);
        }

        return clientResult;
    }

}
