/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject2;

/**
 *
 * @author ben_1
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ben_1
 */
public class TransactionsModel extends BankController{

     // creation of private objects
    private int transactionId = 1000;
    private int toAccNum;
    private int fromAccNum;
    private String transactionDetail;
    private double value;

    // Constructor 
    public TransactionsModel(int fromAcc, int toAcc, double val) {

        toAccNum = toAcc;
        fromAccNum = fromAcc;
        value = val;
    }
  
    // Constructor 
    public TransactionsModel(int toAcc, double val) {

        toAccNum = toAcc;
        value = val;
    }

    // Empty Constructor 
    public TransactionsModel() {
    }

    
    // this method is what makes the transaction between one account to another
    public void makeTranBetween(Connection con, int type) {

        BankController myDate = new BankController();
        int lastValue = 0;
        try {

            if (type == 0) {
                
                if(myDate.langChoice == 1){
                transactionDetail = "Transfer";
                }
                else {
                transactionDetail = "Transfère";
                }
                
            }
            
            else if (type == 0 && myDate.langChoice == 2){
                transactionDetail = "Transfère";
            }

            if (type == 1 && myDate.langChoice == 1) {
                transactionDetail = "Deposit";
            }
            
             else if (type == 1 && myDate.langChoice == 2){
                transactionDetail = "Dépôt";
            }

            if (type == 2 && myDate.langChoice == 1) {
                transactionDetail = "Withdraw";
            }
            
            else if (type == 2 && myDate.langChoice == 2){
                transactionDetail = "Retire";
            }
            
            if (type == 3 && myDate.langChoice == 1) {
                transactionDetail = "Reverse Transfer";
            }
            
            else if (type == 3 && myDate.langChoice == 2){
                transactionDetail = "Transfère Reversé";
            }

            if (type == 4 && myDate.langChoice == 1) {
                transactionDetail = "Reverse Deposit";
            }
            
            else if (type == 4 && myDate.langChoice == 2){
                transactionDetail = "Dépôt Reversé";
            }

            if (type == 5 && myDate.langChoice == 1) {
                transactionDetail = "Reverse Withdraw";
            }
            
            else if (type == 5 && myDate.langChoice == 2){
                transactionDetail = "Retire Reversé";
            }

            // the string that hold the sql statement
            String accSql2 = "SELECT TransactionId FROM Transactions";
            PreparedStatement accStmt2 = con.prepareStatement(accSql2);
            ResultSet accResult = accStmt2.executeQuery();

            while (accResult.next()) {

                lastValue = accResult.getInt("TransactionId");
            }

            accStmt2.close();
            accResult.close();

            if (lastValue != 0) {
                transactionId = lastValue + 1;

            }
            // the string that hold the sql statement
            String accSql = "INSERT INTO Transactions (TransactionId, ToAccountNumber, FromAccountNumber, TransactionDetail, Value) VALUES ('" + transactionId + "','" + toAccNum + "','" + fromAccNum + "','" + transactionDetail + "','" + value + "')";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            accStmt.executeUpdate();

            accStmt.close();
            con.close();

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

    }
    
    // this method reverses the TransactionsModel if the user 
    public boolean reverseTransaction(Connection con, int id) {

        double total2 = 0;
        double total1 = 0;
        int fromAcc = 0;
        int toAcc = 0;
        double matchValue = 0;
        try {

            
            String accSql2 = "SELECT * FROM Transactions WHERE TransactionId='" +id +"'";
            PreparedStatement accStmt2 = con.prepareStatement(accSql2);
            ResultSet accResult1 = accStmt2.executeQuery();
            
            fromAcc = accResult1.getInt("FromAccountNumber");
            toAcc = accResult1.getInt("ToAccountNumber");
            matchValue = accResult1.getDouble("Value");
            
            accStmt2.close();
            accResult1.close();
            
            String accSql = "SELECT Balance FROM Accounts WHERE AccountNumber ='" + fromAcc + "'";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            ResultSet accResult = accStmt.executeQuery();
            total1 = accResult.getDouble("Balance");

            
            accStmt.close();
            accResult.close();

            
            String accSql3 = "SELECT Balance FROM Accounts WHERE AccountNumber ='" + toAcc + "'";
            PreparedStatement accStmt3 = con.prepareStatement(accSql3);
            ResultSet accResult3 = accStmt3.executeQuery();
            total2 = accResult3.getDouble("Balance");

            accStmt3.close();
            accResult3.close();

            if (matchValue <= total2) {
                String updateSql = "UPDATE Accounts SET Balance = '" + (matchValue + total1) + "' WHERE AccountNumber ='" +  fromAcc + "'";
                PreparedStatement client2 = con.prepareStatement(updateSql);
                client2.executeUpdate();

                String updateSql2 = "UPDATE Accounts SET Balance = '" + (total2 - matchValue) + "' WHERE AccountNumber ='" +  toAcc + "'";
                PreparedStatement client = con.prepareStatement(updateSql2);
                client.executeUpdate();
                return true;
            } else {

                return false;
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
    
    public boolean reverseDeposit(Connection con, int id) {

        double total1 = 0;
        int toAcc = 0;
        
        double matchValue = 0;
        try {

            
            String accSql2 = "SELECT * FROM Transactions WHERE TransactionId='" +id +"'";
            PreparedStatement accStmt2 = con.prepareStatement(accSql2);
            ResultSet accResult1 = accStmt2.executeQuery();
            
            toAcc = accResult1.getInt("ToAccountNumber");
            matchValue = accResult1.getDouble("Value");
            
            accStmt2.close();
            accResult1.close();
            

            
            String accSql3 = "SELECT Balance FROM Accounts WHERE AccountNumber ='" + toAcc + "'";
            PreparedStatement accStmt3 = con.prepareStatement(accSql3);
            ResultSet accResult3 = accStmt3.executeQuery();
            total1 = accResult3.getDouble("Balance");

            accStmt3.close();
            accResult3.close();
            
            if (matchValue <= total1) {
                String updateSql = "UPDATE Accounts SET Balance = '" + (total1 - matchValue) + "' WHERE AccountNumber ='" + toAcc + "'";
            PreparedStatement client2 = con.prepareStatement(updateSql);
            client2.executeUpdate();
            client2.close();
            return true;
            }
            else{
            
                return false;
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
    
    public void reverseWithdraw(Connection con, int id) {

        double total1 = 0;
        int toAcc = 0;
        
        double matchValue = 0;
        try {

            
            String accSql2 = "SELECT * FROM Transactions WHERE TransactionId='" +id +"'";
            PreparedStatement accStmt2 = con.prepareStatement(accSql2);
            ResultSet accResult1 = accStmt2.executeQuery();
            
            toAcc = accResult1.getInt("ToAccountNumber");
            matchValue = accResult1.getDouble("Value");
            
            accStmt2.close();
            accResult1.close();
            

            
            String accSql3 = "SELECT Balance FROM Accounts WHERE AccountNumber ='" + toAcc + "'";
            PreparedStatement accStmt3 = con.prepareStatement(accSql3);
            ResultSet accResult3 = accStmt3.executeQuery();
            total1 = accResult3.getDouble("Balance");

            accStmt3.close();
            accResult3.close();
            
                String updateSql = "UPDATE Accounts SET Balance = '" + (total1 + matchValue) + "' WHERE AccountNumber ='" + toAcc + "'";
            PreparedStatement client2 = con.prepareStatement(updateSql);
            client2.executeUpdate();
            client2.close();


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


    }


    public boolean calculateTransfer(Connection con) {

        double total2 = 0;
        double total1 = 0;
        try {

            
            String accSql = "SELECT Balance FROM Accounts WHERE AccountNumber ='" + fromAccNum + "'";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            ResultSet accResult = accStmt.executeQuery();
            total1 = accResult.getDouble("Balance");

            accStmt.close();
            accResult.close();

            String accSql3 = "SELECT Balance FROM Accounts WHERE AccountNumber ='" + toAccNum + "'";
            PreparedStatement accStmt3 = con.prepareStatement(accSql3);
            ResultSet accResult3 = accStmt3.executeQuery();
            total2 = accResult3.getDouble("Balance");

            accStmt3.close();
            accResult3.close();

            if (value <= total1) {
                String updateSql = "UPDATE Accounts SET Balance = '" + (value + total2) + "' WHERE AccountNumber ='" + toAccNum + "'";
                PreparedStatement client2 = con.prepareStatement(updateSql);
                client2.executeUpdate();

                String updateSql2 = "UPDATE Accounts SET Balance = '" + (total1 - value) + "' WHERE AccountNumber ='" + fromAccNum + "'";
                PreparedStatement client = con.prepareStatement(updateSql2);
                client.executeUpdate();
                return true;
            } else {

                return false;
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

    public void Deposit(Connection con) {

        double total1 = 0;
        try {

            String accSql3 = "SELECT Balance FROM Accounts WHERE AccountNumber ='" + toAccNum + "'";
            PreparedStatement accStmt3 = con.prepareStatement(accSql3);
            ResultSet accResult3 = accStmt3.executeQuery();
            total1 = accResult3.getDouble("Balance");

            accStmt3.close();
            accResult3.close();

            String updateSql = "UPDATE Accounts SET Balance = '" + (value + total1) + "' WHERE AccountNumber ='" + toAccNum + "'";
            PreparedStatement client2 = con.prepareStatement(updateSql);
            client2.executeUpdate();

            client2.close();

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

    }

    public boolean Withdraw(Connection con) {

        double total1 = 0;
        try {

            String accSql3 = "SELECT Balance FROM Accounts WHERE AccountNumber ='" + toAccNum + "'";
            PreparedStatement accStmt3 = con.prepareStatement(accSql3);
            ResultSet accResult3 = accStmt3.executeQuery();
            total1 = accResult3.getDouble("Balance");

            accStmt3.close();
            accResult3.close();

            if (value <= total1) {
                String updateSql = "UPDATE Accounts SET Balance = '" + (total1 - value) + "' WHERE AccountNumber ='" + toAccNum + "'";
                PreparedStatement client2 = con.prepareStatement(updateSql);
                client2.executeUpdate();

                client2.close();
                return true;
            } else {
                return false;
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

    public boolean checkIsActive(Connection con, int acc) {

        String acc1 = "";

        try {

            String accSql = "SELECT isActive FROM Accounts WHERE AccountNumber ='" + acc + "'";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            ResultSet accResult = accStmt.executeQuery();

            if (accResult.next() == false) {
                return false;
            } else {
        
            
            acc1 = accResult.getString("isActive");
           accResult.close();
           accStmt.close();

            if (acc1 == "true") {
                 
                return true;
            } else {
                if (acc1 == "false") {

                    return false;
                } else {

                    return true;
                }
            }
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
    
    public int howManyActive(Connection con) {


        int i = 0;
        
        try {

            String accSql = "SELECT isActive FROM Accounts";
            PreparedStatement accStmt = con.prepareStatement(accSql);
            ResultSet accResult = accStmt.executeQuery();

            if (accResult.next() == false) {
                return 0;
            } else {
                do {
                    if(accResult.getString("isActive").equals("true") || accResult.getString("isActive").equals("vrai")){
                 i++;
                }
                } while (accResult.next());
            }
            
            accResult.close();
            accStmt.close();


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

        return i;
    }

    public ResultSet viewTransactions(Connection con) {

        ResultSet accResult = null;
        try {

            String accSql = "SELECT * FROM Transactions";
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
    
    public ResultSet matchTransac(Connection con, int id) {

        ResultSet accResult = null;
        try {

            String accSql = "SELECT * FROM Transactions WHERE TransactionId ='" + id + "'";
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
}
