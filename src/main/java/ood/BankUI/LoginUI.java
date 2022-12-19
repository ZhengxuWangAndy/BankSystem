/*LoginUI.java - This class contains the UI for the Login
 */
package ood.BankUI;

import ood.Account.*;
import ood.Bank.Bank;
import ood.Money.Currency;
import ood.Money.Dollar;
import ood.Money.Euro;
import ood.Money.Yuan;
import ood.Person.Customer;
import ood.Person.Manager;
import ood.Stock.Stock;
import ood.Stock.StockMarket;
import ood.utils.DBReader;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class LoginUI {
    public void login(final String tablename){

        // Creating instance of JFrame
        final JFrame login = new JFrame("Login Page");
        // Setting the width and height of frame
        login.setSize(400, 400);
//        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. DIV HTML
         */
        final JPanel panel = new JPanel();
        Color c1 = new Color(184, 182, 250);
        panel.setBackground(c1);
        // adding panel to frame
        login.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        Bank bank = new Bank();
        DBReader db = new DBReader();

        LinkedList<LinkedHashMap<String, String>> stockInfo = db.getTable("StockMarket");
        StockMarket stockMarket = new StockMarket();


        for(int i = 0; i < stockInfo.size(); i++){
            System.out.println(stockInfo.get(i).get("stockID"));

            Stock stock = new Stock(stockInfo.get(i).get("stockName"), 10,Float.parseFloat(stockInfo.get(i).get("stockPrice")),Float.parseFloat(stockInfo.get(i).get("stockHighPrice")),4);
            System.out.println("!!!LOGINNNNN!!!");
            System.out.println(stock);
            stockMarket.addStockToMarket(stock);
        }

        placeComponents(panel,login);
        panel.getComponent(4).addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("get clicked");
                String username = ((JTextField) panel.getComponent(1)).getText();
                String password = String.valueOf(((JPasswordField) panel.getComponent(3)).getPassword());
                System.out.println("get info");
                DBReader db = new DBReader();
                LinkedHashMap<String,String> info = db.getData(tablename,"username",username);
                try {
                    if(password.equals(info.get("password"))){
                        System.out.println("correct");
                        // connect to customer info page
                        JOptionPane.showMessageDialog(null, "Welcome back " + username);
                        login.setVisible(false);

                        if(tablename.equals("CustomerInfo")){
                            //customer
                            // create a customer instance
                            Customer c = new Customer(UUID.fromString(info.get("userID")), username, password);
                            LinkedList<LinkedHashMap<String, String>> deposit = db.getTable("DepositAccounts");
                            LinkedList<LinkedHashMap<String, String>> loan = db.getTable("LoanAccounts");
                            LinkedList<LinkedHashMap<String, String>> security = db.getTable("SecurityAccounts");
                            LinkedList<LinkedHashMap<String, String>> currencies = db.getTable("Currencies");

                            LinkedList<LinkedHashMap<String, String>> customerStockInfo = db.getTable("CustomerStockInfo");


                            for(int i = 0; i < customerStockInfo.size(); i++){
                                if (customerStockInfo.get(i).get("userID").equals(c.getID().toString())){
                                    System.out.println(customerStockInfo.get(i).get("stockID"));
                                    Stock customerStock = new Stock(customerStockInfo.get(i).get("stockName"),
                                            Integer.parseInt(customerStockInfo.get(i).get("stockCount")),
                                            Float.parseFloat(customerStockInfo.get(i).get("purchasePrice")),
                                            Float.parseFloat(customerStockInfo.get(i).get("stockMarketPrice")),0,0,UUID.fromString(customerStockInfo.get(i).get("stockID")));

                                    c.addStock(customerStock);

                                }
                            }
//
                            // read deposit accounts to this customer
                            for(int i = 0; i < deposit.size(); i++){
                                if (deposit.get(i).get("uid").equals(c.getID().toString())){
                                    Currency cur = null;
                                    for(int j = 0; j < currencies.size(); j++) {
                                        if(deposit.get(i).get("cid").equals(currencies.get(j).get("cid"))){
                                            if (currencies.get(j).get("currencyName").equals("USD")){
                                                cur = new Dollar();
                                            } else if (currencies.get(j).get("currencyName").equals("EUR")) {
                                                cur = new Yuan();
                                            }else if (currencies.get(j).get("currencyName").equals("CNY")){
                                                cur = new Euro();
                                            }

                                        }
                                    }

                                    if(deposit.get(i).get("type").equals("checking")){
                                        CheckingAccount a = new CheckingAccount(UUID.fromString(deposit.get(i).get("aid")), c, cur, Double.parseDouble(deposit.get(i).get("amount")));
                                        c.retrieveAccount(a);
                                    }else {
                                        SavingsAccount a = new SavingsAccount(UUID.fromString(deposit.get(i).get("aid")), c, cur, Double.parseDouble(deposit.get(i).get("amount")));
                                        c.retrieveAccount(a);
                                    }
                                }
                            }

                            // read loan accounts to this customer
                            for(int i = 0; i < loan.size(); i++){
                                if (loan.get(i).get("uid").equals(c.getID().toString())){

                                    LoanAccount a = new LoanAccount(UUID.fromString(loan.get(i).get("aid")), c, Double.parseDouble(loan.get(i).get("amount")));
                                    c.retrieveAccount(a);

                                }
                            }

                            // read security accounts to this customer
                            for(int i = 0; i < security.size(); i++){
                                if (security.get(i).get("uid").equals(c.getID().toString())){
                                    SecurityAccount a = new SecurityAccount(UUID.fromString(security.get(i).get("aid")), c, Double.parseDouble(security.get(i).get("amount")));
                                    c.retrieveAccount(a);
                                }
                            }


                            CustomerUI cui = new CustomerUI(c);
                            cui.customerUI();

//                            ViewAccUI viewAcc = new ViewAccUI(c, null);
////                            viewAcc.viewAccFrame(c);
//                            viewAcc.viewAcc();
                        }else{
                            //manager
                            Manager m = new Manager(UUID.fromString(info.get("userID")), username, password);
                            DBReader reader = new DBReader();
//                            Manager m = new Manager(UUID.randomUUID(), "admin3", "admin3");
                            List<LinkedHashMap<String, String>> c = reader.getTable("customerinfo");
                            System.out.println(c.size());
                            ManagerUI managerUI = new ManagerUI(m);
                            managerUI.managerUI();
//                            ViewAccUI viewAcc = new ViewAccUI();
//                            viewAcc.viewAccFrame(m);

                        }
                    }
                    else {
                        System.out.println("wrong");
                        JOptionPane.showMessageDialog(null, "wrong password or username");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("wrong");
                    JOptionPane.showMessageDialog(null, "wrong password or username");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        login.setVisible(true);

    }

    private static void placeComponents(JPanel panel, JFrame frame) {

        panel.setLayout(null);

        // Creating JLabel
        JLabel userLabel = new JLabel("UserName");

        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);


        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // Same process for password label and text field.
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);


        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // Creating login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(90, 80, 80, 25);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        });
    }


}
