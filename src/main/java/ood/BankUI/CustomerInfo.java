/*CustomerInfo.java - This UI class is called when manager clicked on the
View Customers button in the ManagerUI page. It will display customers’ information,
such as custom id. Manager can use a specific customer’s id to view their accounts.
 */
package ood.BankUI;
/**
 * For manager to view customer accounts
 */

import ood.Account.CheckingAccount;
import ood.Account.LoanAccount;
import ood.Account.SavingsAccount;
import ood.Account.SecurityAccount;
import ood.Money.Currency;
import ood.Money.Dollar;
import ood.Money.Euro;
import ood.Money.Yuan;
import ood.Person.Customer;
import ood.Stock.Stock;
import ood.utils.DBReader;
import ood.Person.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CustomerInfo {

    private DBReader db = new DBReader();
    private LinkedList<LinkedHashMap<String, String>> customers;
    private JFrame previousFrame;

    public CustomerInfo(Manager manager, JFrame frame) {
        this.customers = db.getTable("customerinfo");
        this.previousFrame = frame;
    }

    public void customerInfoUI(){
        JFrame frame = new JFrame("Customers Information Table");
        JPanel panel = new JPanel();
        JLabel colName = new JLabel("User Id/Username/Password:");
        panel.add(colName);
        for (int i = 0; i < customers.size(); i++) {
            JLabel data = new JLabel(customers.get(i).values().toString());
            panel.add(data);
        }
        JLabel getIdLabel = new JLabel("Enter a Customer Id for Details");
        panel.add(getIdLabel);
        JTextField getIdTextF = new JTextField(16);
        panel.add(getIdTextF);
        JButton getIdBtn = new JButton("Submit");
        panel.add(getIdBtn);
        JButton returnBtn = new JButton("Return");
        panel.add(returnBtn);
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                previousFrame.setVisible(true);
            }
        });
        getIdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get all userID
                ArrayList<String> customerIdList = new ArrayList<>();
                for (int i = 0; i < customers.size(); i++) {
                    customerIdList.add(customers.get(i).get("userID"));
                }

                // check whether valid ID
                if (customerIdList.contains(getIdTextF.getText())){
                    //customer
                    // create a customer instance
                    LinkedHashMap<String, String> info = db.getData("CustomerInfo","userID",getIdTextF.getText());

                    Customer c = new Customer(UUID.fromString(getIdTextF.getText()), info.get("username"), info.get("password"));
                    LinkedList<LinkedHashMap<String, String>> deposit = db.getTable("DepositAccounts");
                    LinkedList<LinkedHashMap<String, String>> loan = db.getTable("LoanAccounts");
                    LinkedList<LinkedHashMap<String, String>> security = db.getTable("SecurityAccounts");
                    LinkedList<LinkedHashMap<String, String>> currencies = db.getTable("Currencies");

                    LinkedList<LinkedHashMap<String, String>> customerStockInfo = db.getTable("CustomerStockInfo");


                    for(int i = 0; i < customerStockInfo.size(); i++){
                        if (customerStockInfo.get(i).get("userID").equals(c.getID().toString())){
                            System.out.println(customerStockInfo.get(i).get("stockID"));

                            Stock customerStock = new Stock(customerStockInfo.get(i).get("stockName"), Integer.parseInt(customerStockInfo.get(i).get("stockCount")),Float.parseFloat(customerStockInfo.get(i).get("purchasePrice")),Float.parseFloat(customerStockInfo.get(i).get("stockMarketPrice")),0,0);
                            System.out.println("!!!!!!");
                            System.out.println(customerStock);
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
                }else{
                    JOptionPane.showMessageDialog(null, "Unrecognized ID");
                }
//                if (getIdTextF.getText().equals(customers.get(i).get("userID"))){
//
//                    //ViewAccUI viewAccUI = new ViewAccUI()
//                }
            }
        });
        frame.add(panel);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
