/*OpenAccUI.java: This class is the UI for the customer to open an account. The customer can
choose which type of account (Checking, Savings or Security) they wish to open and the initial balance.*/
package ood.BankUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.UUID;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import ood.Account.*;
import ood.Money.*;
import ood.Person.Customer;
import ood.utils.DBReader;

public class OpenAccUI {
  private Customer customer;
  private JFrame previousFrame;

  public OpenAccUI(Customer customer, JFrame frame){
    this.customer = customer;
    this.previousFrame = frame;
  }

  public void openAcc(){
    JFrame frame = new JFrame("Opening Account");
    Color c1 = new Color(243, 250, 182);
    frame.getContentPane().setBackground(c1);
    JLabel label = new JLabel("Which type of account would you like to open?");

    JButton savings = UIToolBox.addButton("Savings Account",20,50,200,30);
    frame.add(savings);
    savings.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openSavingsAcc();
      }
    });

    JButton checking = UIToolBox.addButton("Checking Account",250,50,200,30);
    frame.add(checking);
    checking.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openCheckingAcc();
      }
    });

    JButton security = UIToolBox.addButton("Security Account",20,100,200,30);
    frame.add(security);
    security.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openSecurityAcc();
      }
    });

    JButton backButton = new JButton("Back");
    backButton.setBounds(150,150,200,30);
    frame.add(backButton);

    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();

      }
    });

    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);
  }

  public void openSavingsAcc(){
    openDepositAcc("Opening Savings Account", new SavingsAccount());
  }

  public void openCheckingAcc(){
    openDepositAcc("Opening Checking Account", new CheckingAccount());
  }

  public void openDepositAcc(String frameTitle, DepositAccount account){
    final JFrame frame = new JFrame();

    JTextArea title = new JTextArea("How much money and which currency would you like "
        + "to put in your new savings account?");
    title.setBounds(90, 10, 400, 50);
    title.setLineWrap(true);
    title.setEditable(false);
    frame.add(title);

    JLabel askamount = new JLabel("Please input the amount here: (minimum is 100)");
    askamount.setBounds(40, 80, 400, 20);
    frame.add(askamount);

    final JTextField amount = new JTextField("100");
    amount.setBounds(400, 80, 100, 20);
    frame.add(amount);

    JLabel askcurrency = new JLabel("Which currency is it?");
    askcurrency.setBounds(40, 160, 400, 20);
    frame.add(askcurrency);

    final LinkedHashMap<String, Currency> currencies = new LinkedHashMap<>();
    currencies.put("Dollar", new Dollar());
    currencies.put("Euro", new Euro());
    currencies.put("Yuan", new Yuan());
    String[] currencyOptions = currencies.keySet().toArray(new String[0]);

    final JComboBox<String> jComboBox = new JComboBox<>(currencyOptions);
    jComboBox.setBounds(400, 160, 100, 20);
    frame.add(jComboBox);

    JButton open = new JButton("Open");
    open.setBounds(250, 250, 80, 40);
    frame.add(open);

    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    final DepositAccount newAccount = account;
    open.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        double moneyAmount = Double.parseDouble(amount.getText());
        Currency currency = currencies.get(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
        newAccount.setAccount(customer,currency,moneyAmount);
        customer.addAccount(newAccount);
        frame.dispose();
        DBReader db = new DBReader();
        LinkedList<LinkedHashMap<String, String>> curTable = db.getTable("Currencies");

        LinkedHashMap<String, String> row = new LinkedHashMap<>();
        row.put("uid",customer.getID().toString());
        row.put("aid", newAccount.getAid().toString());
        for (int i = 0; i < curTable.size(); i++) {
          if (currency.getCname().equals("Dollar") && curTable.get(i).get("currencyName").equals("USD")) {
            row.put("cid",curTable.get(i).get("cid"));
          }else if (currency.getCname().equals("Euro") && curTable.get(i).get("currencyName").equals("EUR")) {
            row.put("cid",curTable.get(i).get("cid"));
          }else if (currency.getCname().equals("Yuan") && curTable.get(i).get("currencyName").equals("CNY")) {
            row.put("cid",curTable.get(i).get("cid"));
          }
        }
        row.put("amount",String.valueOf(moneyAmount));
        if (newAccount instanceof CheckingAccount) {
          row.put("type", "checking");
        }else{
          row.put("type", "saving");
        }
        row.put("accNum", "88888");
        db.addData("DepositAccounts", row);
        successMsg("Successfully opened account!");
        previousFrame.setVisible(true);
      }
    });
  }


  public void openSecurityAcc(){

    final LinkedHashMap<UUID, SavingsAccount> savingsAccounts
        = customer.getCustomerAccounts().getSavingsForSecurity();

    System.out.println(savingsAccounts.size());

    if(savingsAccounts==null || savingsAccounts.size()==0) {
      final JFrame frame = new JFrame();
      frame.setSize(600,400);
      frame.setLayout(null);
      frame.setVisible(true);

      JTextArea msg = new JTextArea("In order to open a security account, you must have "
          + "more than $5000 worth of money and more than 1000 in USD "
          + "in at least one of your savings accounts!"
          + " You do not yet qualify for opening a security account!"
          + " You will be returned to the previous page soon.");
      msg.setBounds(90, 10, 400, 80);
      msg.setLineWrap(true);
      msg.setEditable(false);
      frame.add(msg);

      Timer timer = new Timer(3000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          frame.dispose();
          previousFrame.setVisible(true);
        }
      });
      return;
    }

    final JFrame frame = new JFrame();
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);


    JTextArea title = new JTextArea("From which eligible savings account and how much money "
        + "(at least $1000) would you like to transfer to your new savings account?");
    title.setBounds(90, 10, 400, 50);
    title.setLineWrap(true);
    title.setEditable(false);
    frame.add(title);

    final LinkedHashMap<String,SavingsAccount> accountNames = new LinkedHashMap<>();
    for(SavingsAccount savingsAccount : savingsAccounts.values()){
      accountNames.put(savingsAccount.toString(),savingsAccount);
    }
    String[] accountOptions = accountNames.keySet().toArray(new String[0]);

    final JComboBox<String> jComboBox = new JComboBox<>(accountOptions);
    jComboBox.setBounds(80, 150, 200, 20);
    frame.add(jComboBox);

    final JTextField amount = new JTextField("1000");
    amount.setBounds(400, 150, 100, 20);
    frame.add(amount);

    JButton open = new JButton("Open");
    open.setBounds(250, 250, 80, 40);
    frame.add(open);

    open.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SavingsAccount savingsAccount = accountNames.get(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
        double moneyAmount = Double.parseDouble(amount.getText());
        assert(moneyAmount>=1000);
        customer.withdraw(savingsAccount,new Dollar(),moneyAmount);
        SecurityAccount securityAccount = new SecurityAccount(UUID.randomUUID(),customer,moneyAmount);
        customer.addAccount(securityAccount);
        frame.dispose();

        DBReader db = new DBReader();

        // write to db of new security account
        LinkedHashMap<String, String> row = new LinkedHashMap<>();
        row.put("uid",customer.getID().toString());
        row.put("aid", securityAccount.getAid().toString());
        row.put("amount",String.valueOf(moneyAmount));
        row.put("accNum", "88888");
        db.addData("SecurityAccounts", row);

        successMsg("Successfully opened account!");
        previousFrame.setVisible(true);
      }
    });
  }

  public void closeAcc(){
    LinkedHashMap<UUID,Account> accounts = customer.getCustomerAccounts().getAccounts();
    if(accounts==null || accounts.size()==0)
      throw new UnsupportedOperationException("You do not have any open account!");

    final JFrame frame = new JFrame("Which account would you like to close?");

    final LinkedHashMap<String,Account> accountNames = new LinkedHashMap<>();
    for(Account account : accounts.values()){
      accountNames.put(account.toString(),account);
    }
    String[] accountOptions = accountNames.keySet().toArray(new String[0]);

    final JComboBox<String> jComboBox = new JComboBox<>(accountOptions);
    jComboBox.setBounds(80, 50, 140, 20);
    frame.add(jComboBox);

    JButton jButton = new JButton("Close");
    jButton.setBounds(100, 100, 90, 20);
    frame.add(jButton);

    frame.setLayout(null);
    frame.setSize(350, 250);
    frame.setVisible(true);

    jButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Account account = accountNames.get(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
        customer.closeAccount(account);
        frame.dispose();
        successMsg("Successfully closed account!");
        previousFrame.setVisible(true);
      }
    });
  }

  public void successMsg(String msg){
    JOptionPane pane = new JOptionPane("");
    final JDialog dialog = pane.createDialog(msg);

    Timer timer = new Timer(3000, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dialog.dispose();
      }
    });

    timer.setRepeats(false);
    timer.start();
    dialog.setVisible(true);
  }

}
