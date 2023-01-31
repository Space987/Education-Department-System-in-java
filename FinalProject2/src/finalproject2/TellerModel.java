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
public class TellerModel extends BankController{

    // creation of private objects
    private String accUser;
    private String accPass;
    private String accPass2;
    private String accName;

    // Empty Constructors 
    public TellerModel() {

    }

    // Constructor 
    public TellerModel(String user, String pass, String pass2, String name) {

        accUser = user;
        accPass = pass;
        accPass2 = pass2;
        accName = name;
    }

    // Constructor with two parameters 
    public TellerModel(String user, String pass) {

        accUser = user;
        accPass = pass;
    }

    // This method checks if the user typed in the right information for the signed
    public boolean signUp(Connection con) {

        boolean finished = !accUser.equals("") && !accPass.equals("") && !accPass2.equals("") && accPass.equals(accPass2);

        try {

            if (finished) {

                // the string that holds the sql statement
                String accSql = "SELECT Username FROM Teller Where Username ='" + accUser + "'";
                PreparedStatement accStmt = con.prepareStatement(accSql);
                ResultSet accResult = accStmt.executeQuery();
                finished = finished && !accResult.next();

                if (finished) {

                    // this inserts the information added into the database
                    accSql = "INSERT INTO Teller(Username, Password, Name) VALUES ('" + accUser + "','" + accPass + "','" + accName + "')";
                    PreparedStatement stmt2 = con.prepareStatement(accSql);
                    stmt2.executeUpdate();
                }
                accStmt.close();
                con.close();

            }
        } catch (java.sql.SQLException e) {

            finished = false;
            System.out.println("SQLEXCEPTION:" + e);
            while (e != null) {
                System.out.println("SQLSTATE:" + e.getSQLState());
                System.out.println("Message:" + e.getMessage());
                e = e.getNextException();
                System.out.println("");
            }

        } catch (java.lang.Exception e) {

            finished = false;
            System.out.println("EXCEPTION:" + e);
            e.printStackTrace();
        }

        return finished;
    }

    // This method is for the logins
    public String Login(Connection con) {

        String accName = "";
        try {

            // This method takes the information from the user and makes sure that it exist in the databse
            if (accUser.equals(accUser) && accPass.equals(accPass)) {
                String accSql = "SELECT Name FROM Teller WHERE Username ='" + accUser + "' AND Password ='" + accPass + "'";
                PreparedStatement accStmt = con.prepareStatement(accSql);
                ResultSet accResult = accStmt.executeQuery();

                if (accResult.next()) {
                    accName = accResult.getString(1);

                }
                accStmt.close();
                con.close();

            }
        } catch (java.sql.SQLException e) {

            System.out.println("SQLEXCEPTION:" + e);
        } catch (java.lang.Exception e) {

            System.out.println("EXCEPTION:" + e);
        }
        return accName;
    }

    // 
    public boolean Check(Connection con) {

        boolean check = false;
        try {

            // This holds the SQL commend string that selects the teller
            String accSql = "SELECT * FROM Teller";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            ResultSet accResult = accStmt.executeQuery();

            String count = "";

            while (accResult.next()) {
                count = accResult.getString("Name");
            }

            if (!count.equals("")) {
                check = true;
            }

        } catch (java.sql.SQLException e) {

            return check;
        } catch (java.lang.Exception e) {

            return check;
        }
        return check;
    }

}
