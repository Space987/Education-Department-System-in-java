/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author ben_1
 */
public class BankController {

    public static int langChoice;

    public static Connection createConnection() {

        try {

            Class.forName("org.sqlite.JDBC");

            Connection con = DriverManager.getConnection("jdbc:sqlite://C://Users//ben_1//Desktop//BankTest.db");

            return con;

        } catch (ClassNotFoundException e) {

            System.out.println("SQL Driver not found [" + e + "]");

            System.exit(0);

        } catch (SQLException e) {

            System.out.println("SQL Exception [" + e + "]");

            System.exit(0);

        }

        System.out.println("Succesfull connection");

        return null;

    }

    public static void main(String[] args) throws SQLException {

        boolean test = false;
        int choice = 0;
        int mainMenu = 0;
        String loginNum = "";
        String loginPass = "";
        String loginPass2 = "";
        String name = "";

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to your Banking Service! / Bienvenue dans votre service bancaire!");
        System.out.println("--------------------------------------------------------------------------- \n");
        System.out.println("What Language Would you like to use? / Quelle langue souhaitez-vous utiliser? \n");
        System.out.println("Press 1 for English");
        System.out.println("Press 2 for French");

        langChoice = input.nextInt();
        ResourceBundle messages = null;

        if (langChoice == 2) {
            Locale currentLocale;
            currentLocale = new Locale("fr", "FR");
            messages = ResourceBundle.getBundle("com.i18n.MessagesBundle_fr_FR", currentLocale);
        } else if (langChoice == 1) {
            Locale currentLocale;
            currentLocale = new Locale("en", "US");
            messages = ResourceBundle.getBundle("com.i18n.MessagesBundle_en_US", currentLocale);
        } else {

            System.out.println("You can only press 1 or 2! / Vous ne pouvez appuyer que sur 1 ou 2!");
        }

        System.out.println("");
        System.out.println(messages.getString("welcomeTeller"));
        System.out.println(messages.getString("press1Option"));
        System.out.println(messages.getString("press2Option"));
        choice = input.nextInt();

        switch (choice) {

            case 1:
                boolean tellCheck1 = false;

                TellerModel acc = new TellerModel();
                if (!acc.Check(createConnection())) {

                    while (!tellCheck1) {
                        System.out.println(messages.getString("noTellerAcc"));

                        System.out.println(messages.getString("createName"));
                        name = input.next();

                        System.out.println(messages.getString("createLogin"));
                        loginNum = input.next();

                        System.out.println(messages.getString("createPassword"));
                        loginPass = input.next();

                        System.out.println(messages.getString("createPassword2"));
                        loginPass2 = input.next();

                        TellerModel createAcc = new TellerModel(loginNum, loginPass, loginPass2, name);
                        if (createAcc.signUp(createConnection())) {
                            System.out.println(messages.getString("Welcome") + createAcc.Login(createConnection()) + "!");
                            tellCheck1 = true;
                        } else {
                            System.out.println(messages.getString("passNoMatch"));
                        }

                    }
                } else {

                    while (!test) {
                        System.out.println(messages.getString("enterLoginNumb"));
                        loginNum = input.next();

                        System.out.println(messages.getString("enterLoginPassword"));
                        loginPass = input.next();

                        TellerModel loginAcc = new TellerModel(loginNum, loginPass);

                        if (loginAcc.Login(createConnection()) != "") {
                            System.out.println(messages.getString("Welcome") + loginAcc.Login(createConnection()) + "!");
                            test = true;
                        } else {
                            System.out.println(messages.getString("wrongInfo"));
                        }
                    }
                }
                break;

            case 2:
                boolean tellCheck = false;
                while (!tellCheck) {
                    System.out.println(messages.getString("createAcc"));

                    System.out.println(messages.getString("createName"));
                    name = input.next();

                    System.out.println(messages.getString("createLogin"));
                    loginNum = input.next();

                    System.out.println(messages.getString("createPassword"));
                    loginPass = input.next();

                    System.out.println(messages.getString("createPassword2"));
                    loginPass2 = input.next();

                    TellerModel createAcc = new TellerModel(loginNum, loginPass, loginPass2, name);
                    if (createAcc.signUp(createConnection())) {
                        System.out.println(messages.getString("Welcome") + createAcc.Login(createConnection()) + "!");
                        tellCheck = true;
                    } else {
                        System.out.println(messages.getString("uniqueID"));

                    }
                }

                break;
        }

        while (mainMenu != 4) {

            System.out.println(messages.getString("selectOptions"));
            System.out.println(messages.getString("press1ClientMenu"));
            System.out.println(messages.getString("press2AccMenu"));
            System.out.println(messages.getString("press3TransMenu"));
            System.out.println(messages.getString("press4Exit"));

            mainMenu = input.nextInt();

            if (mainMenu > 4) {

                System.out.println(messages.getString("invalidMenu"));
                continue;
            }
            switch (mainMenu) {

                case 1:
                    int clientMainMenu = 0;

                    while (clientMainMenu != 3) {

                        System.out.println(messages.getString("WelcomeClient"));
                        System.out.println(messages.getString("press1Client"));
                        System.out.println(messages.getString("press2Client"));
                        System.out.println(messages.getString("press3ExitClient"));
                        clientMainMenu = input.nextInt();

                        switch (clientMainMenu) {

                            case 1:
                                String rowUp = null;
                                String clientFirstName = null;
                                String clientLastName = null;
                                boolean wasUpdated = false;

                                boolean wrong = false;
                                ClientModel client1 = new ClientModel();
                                ResultSet rs = client1.viewClient(createConnection());

                                if (rs == null) {

                                    System.out.println(messages.getString("noClients"));
                                    break;
                                } else {

                                    do {
                                        String row = messages.getString("ClientID") + " " + rs.getInt("ClientId") + "\n\n" + messages.getString("ClientFirstName") + " " + rs.getString("FirstName") + "\n\n" + messages.getString("ClientLastName") + " " + rs.getString("LastName") + "\n\n" + messages.getString("ClientIndentification") + " " + rs.getString("Identification") + "\n\n" + messages.getString("ClientAddress") + " " + rs.getString("Address") + "\n-------------------- \n";
                                        System.out.println(row + "\n");
                                    } while (rs.next());
                                    rs.close();
                                    while (!wrong) {
                                        int clientChoice = 0;
                                        int clientSubMenu = 0;

                                        System.out.println(messages.getString("enterClientID"));
                                        clientChoice = input.nextInt();

                                        ClientModel client2 = new ClientModel();
                                        ResultSet rs2 = client2.matchClients(createConnection(), clientChoice);

                                        if (rs2 != null) {
                                            wrong = true;

                                            while (clientSubMenu != 3) {

                                                if (wasUpdated) {

                                                    rowUp = clientFirstName + " " + clientLastName;
                                                } else {
                                                    ClientModel client3 = new ClientModel();
                                                    ResultSet rs4 = client3.matchClients(createConnection(), clientChoice);
                                                    rowUp = rs4.getString("FirstName") + " " + rs4.getString("LastName");
                                                    rs4.close();
                                                }

                                                rs2.close();
                                                System.out.println(messages.getString("selectSubClientMenu") + rowUp + " \n");

                                                System.out.println(messages.getString("press1ClientSubMenu"));
                                                System.out.println(messages.getString("press2ClientSubMenu"));
                                                System.out.println(messages.getString("press3ToExitAccounts"));
                                                clientSubMenu = input.nextInt();

                                                switch (clientSubMenu) {

                                                    case 1:
                                                        ClientModel client3 = new ClientModel();
                                                        ResultSet rs3 = client3.viewSpecificClient(createConnection(), clientChoice);

                                                        String row22 = messages.getString("ClientID") + " " + rs3.getInt("ClientId") + "\n\n" + messages.getString("ClientFirstName") + " " + rs3.getString("FirstName") + "\n\n" + messages.getString("ClientLastName") + " " + rs3.getString("LastName") + "\n\n" + messages.getString("ClientIndentification") + " " + rs3.getString("Identification") + "\n\n" + messages.getString("ClientAddress") + " " + rs3.getString("Address") + "\n-------------------- \n";
                                                        System.out.println(row22 + "\n");

                                                        rs3.close();
                                                        break;
                                                    case 2:

                                                        System.out.println(messages.getString("enterClientFirstName"));
                                                        clientFirstName = input.next();

                                                        System.out.println(messages.getString("enterClientLastName"));
                                                        clientLastName = input.next();

                                                        System.out.println(messages.getString("enterClientIdentification"));
                                                        String clientIdentification = input.next();

                                                        System.out.println(messages.getString("enterClientAddress"));
                                                        String clientAddress = input.next();

                                                        ClientModel client4 = new ClientModel();

                                                        client4.updateClients(createConnection(), clientChoice, clientFirstName, clientLastName, clientIdentification, clientAddress);

                                                        System.out.println(messages.getString("ClientIsUpdated"));
                                                        wasUpdated = true;
                                                        break;
                                                    case 3:
                                                        break;
                                                }
                                            }

                                        } else {

                                            System.out.println(messages.getString("ClientIdMatch"));

                                        }
                                    }
                                }
                                break;
                            case 2:

                                System.out.println(messages.getString("enterClientID"));
                                int clientId = input.nextInt();

                                System.out.println(messages.getString("enterClientFirstName"));
                                clientFirstName = input.next();

                                System.out.println(messages.getString("enterClientLastName"));
                                clientLastName = input.next();

                                System.out.println(messages.getString("enterClientIdentification"));
                                String clientIdentification = input.next();

                                System.out.println(messages.getString("enterClientAddress"));
                                String clientAddress = input.next();

                                ClientModel client = new ClientModel(clientId, clientFirstName, clientLastName, clientIdentification, clientAddress);
                                if (client.createClient(createConnection())) {

                                    System.out.println("\n" + clientFirstName + " " + clientLastName + " " + messages.getString("clientAdded"));
                                } else {

                                    System.out.println(messages.getString("clientAlreadyExists"));
                                }

                                break;
                            case 3:

                                break;

                        }
                    }
                    break;

                case 2:
                    int accountMainMenu = 0;

                    while (accountMainMenu != 3) {
                        System.out.println(messages.getString("welcomeAccMenu"));
                        System.out.println(messages.getString("press1ViewAccounts"));
                        System.out.println(messages.getString("press2CreateAccount"));
                        System.out.println(messages.getString("press3ToExitAccounts"));
                        accountMainMenu = input.nextInt();

                        switch (accountMainMenu) {

                            case 1:
                                String currency = "";
                                boolean wrong2 = false;
                                AccountsModel acc = new AccountsModel();
                                ResultSet rs = acc.viewAccounts(createConnection());

                                if (rs == null) {

                                    System.out.println(messages.getString("noAccounts"));
                                    break;
                                } else {

                                    do {

                                        if (langChoice == 2) {
                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                            currency = formatter.format(rs.getDouble("Balance"));
                                        } else if (langChoice == 1) {
                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                            currency = formatter.format(rs.getDouble("Balance"));
                                        }
                                        String row = messages.getString("AccountNumber") + " " + rs.getInt("AccountNumber") + "\n\n" + messages.getString("AccountClientID") + " " + rs.getInt("ClientId") + "\n\n" + messages.getString("AccountType") + " " + rs.getString("AccountType") + "\n\n" + messages.getString("AccountBalance") + " " + currency + "\n\n" + messages.getString("OpenDate") + " " + rs.getString("OpenDate") + "\n\n" + messages.getString("isActive") + " " + rs.getString("isActive") + "\n-------------------- \n";
                                        System.out.println(row + "\n");
                                    } while (rs.next());

                                    rs.close();
                                    while (!wrong2) {
                                        int accChoice2 = 0;
                                        int accSubMenu = 0;
                                        System.out.println(messages.getString("enterIdManageAcc"));
                                        accChoice2 = input.nextInt();

                                        AccountsModel acc2 = new AccountsModel();
                                        ResultSet rsacc = acc2.matchAccount(createConnection(), accChoice2);

                                        if (rsacc != null) {
                                            wrong2 = true;

                                            int row = rsacc.getInt("AccountNumber");

                                            rsacc.close();
                                            while (accSubMenu != 4) {
                                                System.out.println(messages.getString("welcomeAccSubMenu") + row + "? \n");

                                                System.out.println(messages.getString("press1AccSubMenu"));
                                                System.out.println(messages.getString("press2AccSubMenu"));
                                                System.out.println(messages.getString("press3AccSubMenu"));
                                                System.out.println(messages.getString("press4Exit"));
                                                accSubMenu = input.nextInt();

                                                switch (accSubMenu) {

                                                    case 1:
                                                        String currency2 = "";
                                                        AccountsModel acc3 = new AccountsModel();
                                                        ResultSet rs6 = acc3.viewSpecificAccount(createConnection(), accChoice2);

                                                        if (langChoice == 2) {
                                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                                            currency2 = formatter.format(rs6.getDouble("Balance"));
                                                        } else if (langChoice == 1) {
                                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                                            currency2 = formatter.format(rs6.getDouble("Balance"));
                                                        }
                                                        String row3 = messages.getString("AccountNumber") + " " + rs6.getInt("AccountNumber") + "\n\n" + messages.getString("AccountClientID") + " " + rs6.getInt("ClientId") + "\n\n" + messages.getString("AccountType") + " " + rs6.getString("AccountType") + "\n\n" + messages.getString("AccountBalance") + " " + currency2 + "\n\n" + messages.getString("OpenDate") + " " + rs6.getString("OpenDate") + "\n\n" + messages.getString("isActive") + " " + rs6.getString("isActive") + "\n-------------------- \n";
                                                        System.out.println(row3 + "\n");

                                                        rs6.close();
                                                        break;

                                                    case 2:

                                                        AccountsModel acc5 = new AccountsModel();
                                                        if (acc5.DeActivate(createConnection(), accChoice2)) {

                                                            System.out.println(messages.getString("deactivatedAcc"));

                                                        } else {
                                                            System.out.println(messages.getString("checkDeactivatedAcc"));
                                                        }

                                                        break;
                                                    case 3:
                                                        AccountsModel acc6 = new AccountsModel();
                                                        acc6.ReActivate(createConnection(), accChoice2);
                                                        System.out.println(messages.getString("activeAccounts"));

                                                        break;
                                                    case 4:

                                                        break;
                                                }
                                            }

                                        } else {

                                            System.out.println(messages.getString("accountmatcher "));

                                        }
                                    }

                                    break;
                                }
                            case 2:
                                boolean accCheck = false;

                                ClientModel client = new ClientModel();
                                ResultSet rsCli = client.viewClient(createConnection());

                                if (rsCli == null) {

                                    System.out.println(messages.getString("clientRequired"));
                                    break;
                                } else {
                                    rsCli.close();
                                    System.out.println(messages.getString("enterAccID"));
                                    int accId = input.nextInt();

                                    while (!accCheck) {
                                        System.out.println(messages.getString("enterClientChosser"));
                                        int clientId = input.nextInt();
                                        AccountsModel acc3 = new AccountsModel();
                                        accCheck = acc3.matchClients(createConnection(), clientId);
                                        if (accCheck) {

                                            System.out.println(messages.getString("enterTypeAcc"));
                                            String accType = input.next();

                                            AccountsModel acc6 = new AccountsModel(accId, clientId, accType);

                                            if (acc6.createAccount(createConnection())) {
                                                System.out.println(messages.getString("accCreated"));
                                            } else {

                                                System.out.println(messages.getString("accIdExists"));
                                            }

                                        } else {

                                            System.out.println(messages.getString("accClientIdExists"));

                                        }
                                    }
                                }
                                break;
                            case 3:

                                break;
                        }

                    }
                    break;
                case 3:
                    int actionChoice = 0;
                    while (actionChoice != 6) {
                        System.out.println(messages.getString("welcomeTransMenu"));
                        System.out.println(messages.getString("press1ViewTrans"));
                        System.out.println(messages.getString("press2TransAcc"));
                        System.out.println(messages.getString("press3DepositTrans"));
                        System.out.println(messages.getString("press4WithdrawTrans"));
                        System.out.println(messages.getString("press5ReverseTrans"));
                        System.out.println(messages.getString("press6ExitTrans"));
                        actionChoice = input.nextInt();

                        switch (actionChoice) {

                            case 1:
                                String currency0 = "";
                                TransactionsModel trac = new TransactionsModel();
                                ResultSet trs = trac.viewTransactions(createConnection());

                                if (trs == null) {

                                    System.out.println(messages.getString("noTransaction"));
                                    break;
                                } else {

                                    do {

                                        if (langChoice == 2) {
                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                            currency0 = formatter.format(trs.getDouble("Value"));
                                        } else if (langChoice == 1) {
                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                            currency0 = formatter.format(trs.getDouble("Value"));
                                        }
                                        String rowt = messages.getString("viewTransNumber") + " " + trs.getInt("TransactionId") + "\n\n" + messages.getString("viewToTransAccNumber") + " " + trs.getInt("ToAccountNumber") + "\n\n" + messages.getString("viewFromTransAccNumber") + " " + trs.getInt("FromAccountNumber") + "\n\n" + messages.getString("viewTransDetails") + " " + trs.getString("TransactionDetail") + "\n\n" + messages.getString("viewTranValue") + " " + currency0 + "\n-------------------- \n";
                                        System.out.println(rowt + "\n");
                                    } while (trs.next());

                                    trs.close();
                                    break;
                                }
                            case 2:
                                String currencyAcc = "";
                                TransactionsModel tr = new TransactionsModel();

                                if (tr.howManyActive(createConnection()) < 2 ) {

                                    System.out.println(messages.getString("minTwoAccounts"));
                                    break;
                                } else {
                                    
                                    System.out.println(messages.getString("currentAccounts"));
                                   
                                    AccountsModel acc = new AccountsModel();
                                    ResultSet rsat = acc.viewActiveAccounts(createConnection());
                                      do {
                                    if (langChoice == 2) {
                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                            currencyAcc = formatter.format(rsat.getDouble("Balance"));
                                        } else if (langChoice == 1) {
                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                            currencyAcc = formatter.format(rsat.getDouble("Balance"));
                                        
                                        String rowt = messages.getString("AccountNumber") + " " + rsat.getInt("AccountNumber") + "\n\n" + messages.getString("AccountClientID") + " " + rsat.getInt("ClientId") + "\n\n" + messages.getString("AccountType") + " " + rsat.getString("AccountType") + "\n\n" + messages.getString("AccountBalance") + " " + currencyAcc + "\n\n" + messages.getString("OpenDate") + " " + rsat.getString("OpenDate") + "\n\n" + messages.getString("isActive") + " " + rsat.getString("isActive") + "\n-------------------- \n";
                                        System.out.println(rowt + "\n");
                                        }
                                    } while (rsat.next());
                                      
                                    rsat.close();
                                    
                                    System.out.println(messages.getString("enterTransInfo"));

                                    System.out.println(messages.getString("formTransAcc"));
                                    int trac1 = input.nextInt();

                                    System.out.println(messages.getString("toTransAcc"));
                                    int trac2 = input.nextInt();

                                    TransactionsModel transc = new TransactionsModel();
                                    if (transc.checkIsActive(createConnection(), trac1) && transc.checkIsActive(createConnection(), trac2)) {

                                        System.out.println(messages.getString("howMuchMoneyTrans"));
                                        double bal = input.nextDouble();
                                        TransactionsModel tranFinal = new TransactionsModel(trac1, trac2, bal);
                                        if (tranFinal.calculateTransfer(createConnection())) {

                                            tranFinal.makeTranBetween(createConnection(), 0);

                                            String currency3 = "";
                                            if (langChoice == 2) {
                                                NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                                currency3 = formatter.format(bal);
                                            } else if (langChoice == 1) {
                                                NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                                currency3 = formatter.format(bal);
                                            }

                                            System.out.println(messages.getString("successTrans") + currency3 + "\n");
                                        } else {
                                            System.out.println(messages.getString("insuficientFundsTrans"));
                                        }
                                    } else {
                                        System.out.println(messages.getString("activeTransAccounts"));
                                    }
                                    break;
                                }
                            case 3:

                                TransactionsModel trdep = new TransactionsModel();

                                if (trdep.howManyActive(createConnection()) < 1) {

                                    System.out.println(messages.getString("minOneDepAccounts"));
                                    break;
                                } else {
                                    System.out.println(messages.getString("accDepositTo"));

                                    System.out.println(messages.getString("toTransAcc"));
                                    int depAcc = input.nextInt();

                                    TransactionsModel depTran = new TransactionsModel();
                                    if (depTran.checkIsActive(createConnection(), depAcc)) {

                                        System.out.println(messages.getString("howMuchMoneyTrans"));
                                        double depVal = input.nextDouble();
                                        TransactionsModel depTran2 = new TransactionsModel(depAcc, depVal);
                                        depTran2.Deposit(createConnection());
                                        depTran2.makeTranBetween(createConnection(), 1);

                                        String currency4 = "";
                                        if (langChoice == 2) {
                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                            currency4 = formatter.format(depVal);
                                        } else if (langChoice == 1) {
                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                            currency4 = formatter.format(depVal);
                                        }
                                        System.out.println(messages.getString("successTransDepo") + currency4 + "\n");
                                    } else {

                                        System.out.println(messages.getString("TransAccActive"));
                                    }

                                    break;
                                }
                            case 4:

                                TransactionsModel trwith = new TransactionsModel();

                                if (trwith.howManyActive(createConnection()) < 1) {

                                    System.out.println(messages.getString("minOneWtihAccounts"));
                                    break;
                                } else {

                                    System.out.println(messages.getString("withdrawTrans"));

                                    System.out.println(messages.getString("withdrawFromTrans"));
                                    int withAcc = input.nextInt();

                                    TransactionsModel withTran = new TransactionsModel();
                                    if (withTran.checkIsActive(createConnection(), withAcc)) {

                                        System.out.println(messages.getString("withdraeHowMuchTrans"));
                                        double withVal = input.nextDouble();
                                        TransactionsModel withTran2 = new TransactionsModel(withAcc, withVal);
                                        if (withTran2.Withdraw(createConnection())) {

                                            withTran2.makeTranBetween(createConnection(), 2);

                                            String currency5 = "";
                                            if (langChoice == 2) {
                                                NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                                currency5 = formatter.format(withVal);
                                            } else if (langChoice == 1) {
                                                NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                                currency5 = formatter.format(withVal);
                                            }
                                            System.out.println(messages.getString("successWithdrawTrans") + currency5 + "\n");
                                        } else {
                                            System.out.println(messages.getString("failWithdrawTrans"));
                                        }
                                    } else {

                                        System.out.println(messages.getString("TransAccActive"));
                                    }
                                    break;
                                }
                            case 5:
                                String currency6 = "";
                                String currency7 = "";
                                String currency8 = "";
                                String currency9 = "";
                                TransactionsModel trac3 = new TransactionsModel();
                                ResultSet trs2 = trac3.viewTransactions(createConnection());

                                if (trs2 == null) {

                                    System.out.println(messages.getString("noTransaction"));
                                    break;
                                } else {

                                    do {
                                        if (langChoice == 2) {
                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                            currency6 = formatter.format(trs2.getDouble("Value"));
                                        } else if (langChoice == 1) {
                                            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                            currency6 = formatter.format(trs2.getDouble("Value"));
                                        }

                                         String rowt = messages.getString("viewTransNumber") + " " + trs2.getInt("TransactionId") + "\n\n" + messages.getString("viewToTransAccNumber") + " " + trs2.getInt("ToAccountNumber") + "\n\n" + messages.getString("viewFromTransAccNumber") + " " + trs2.getInt("FromAccountNumber") + "\n\n" + messages.getString("viewTransDetails") + " " + trs2.getString("TransactionDetail") + "\n\n" + messages.getString("viewTranValue") + " " + currency6 + "\n-------------------- \n";
                                        System.out.println(rowt + "\n");
                                    } while (trs2.next());

                                    trs2.close();

                                    boolean wrong3 = false;
                                    int tracChoice = 0;
                                    while (!wrong3) {

                                        System.out.println(messages.getString("reverseTrans"));
                                        tracChoice = input.nextInt();

                                        TransactionsModel trc1 = new TransactionsModel();
                                        ResultSet rsacc = trc1.matchTransac(createConnection(), tracChoice);

                                        if (rsacc != null) {
                                            wrong3 = true;

                                            int from1 = rsacc.getInt("FromAccountNumber");
                                            int to1 = rsacc.getInt("ToAccountNumber");
                                            String detMatch = rsacc.getString("TransactionDetail");
                                            double bal1 = rsacc.getDouble("Value");
                                            rsacc.close();

                                            if (detMatch.equals("Transfer")) {

                                                if (langChoice == 2) {
                                                    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                                    currency7 = formatter.format(bal1);
                                                } else if (langChoice == 1) {
                                                    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                                    currency7 = formatter.format(bal1);
                                                }
                                                
                                                System.out.println(messages.getString("sureReverseTrans") + currency7 + " " + messages.getString("FromTransAccount2") + " " + from1 + " " + messages.getString("ToAccounttrans2") + " " + to1 + "? \n");
                                                System.out.println(messages.getString("MakeSureTrans"));

                                                String yesNo = input.next();

                                                if (yesNo.equals("yes") || yesNo.equals("oui")) {

                                                    TransactionsModel trInverse = new TransactionsModel(from1, to1, bal1);
                                                    if (trInverse.reverseTransaction(createConnection(), tracChoice)) {
                                                        trInverse.makeTranBetween(createConnection(), 3);
                                                        System.out.println(messages.getString("reverseTranOF") + " " + currency7 + " " + messages.getString("FromTransAccount2") + " " + from1 + " " + messages.getString("ToAccounttrans2") + " " + to1 + " " + messages.getString("DepoWasASuccFinal"));
                                                    } else {
                                                        System.out.println(messages.getString("reverseInsuficientFundsTrans"));
                                                    }
                                                } else if (yesNo.equals("no") || yesNo.equals("non")) {
                                                    break;

                                                }
                                            } else if (detMatch.equals("Deposit")) {

                                                if (langChoice == 2) {
                                                    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                                    currency8 = formatter.format(bal1);
                                                } else if (langChoice == 1) {
                                                    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                                    currency8 = formatter.format(bal1);
                                                }
                                                
                                                System.out.println(messages.getString("areYouSureDepo") + currency8 + messages.getString("areYouSureDepo2") + to1 + "? \n");
                                                System.out.println(messages.getString("MakeSureTrans"));

                                                String yesNo = input.next();

                                                if (yesNo.equals("yes") || yesNo.equals("oui")) {

                                                    TransactionsModel trDeposit = new TransactionsModel(to1, bal1);
                                                    if (trDeposit.reverseDeposit(createConnection(), tracChoice)) {
                                                        trDeposit.makeTranBetween(createConnection(), 4);
                                                        System.out.println(messages.getString("DepTransFinal") + currency8 + messages.getString("areYouSureDepo2") + to1 + messages.getString("DepoWasASuccFinal"));
                                                    } else {

                                                        System.out.println(messages.getString("TransFundsAreNotEnoughtFinal"));
                                                    }
                                                } else if (yesNo.equals("no")) {
                                                    break;

                                                }

                                            } else if (detMatch.equals("Withdraw")) {

                                                 if (langChoice == 2) {
                                                    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                                                    currency9 = formatter.format(bal1);
                                                } else if (langChoice == 1) {
                                                    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
                                                    currency9 = formatter.format(bal1);
                                                }
                                                 
                                                System.out.println(messages.getString("AreYouSureWithdrawISGood") + currency9 + messages.getString("FromTransAccount2") + to1 + "? \n");
                                                System.out.println("Type yes or no\n");

                                                String yesNo = input.next();

                                                if (yesNo.equals("yes")) {

                                                    TransactionsModel trWith = new TransactionsModel(to1, bal1);
                                                    trWith.reverseWithdraw(createConnection(), tracChoice);
                                                    trWith.makeTranBetween(createConnection(), 5);
                                                    System.out.println(messages.getString("WithdrawTrans3") + currency9 + messages.getString("areYouSureDepo2") + to1 + messages.getString("DepoWasASuccFinal"));

                                                } else if (yesNo.equals("no")) {
                                                    break;

                                                }
                                            }

                                        } else {

                                            System.out.println(messages.getString("IdMatcherFinal"));
                                        }
                                    }
                                    break;
                                }
                            case 6:
                                break;
                        }
                    }
                    break;

                case 4:
                    break;
            }
        }
    }
}
