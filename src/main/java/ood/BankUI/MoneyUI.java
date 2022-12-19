/*MoneyUI.java: This class handles all UIs related to a customer’s
money transaction, including withdraw, deposit, transfer, exchange and pay interest.*/
package ood.BankUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.UUID;
import javax.swing.*;
import ood.Account.Account;
import ood.Account.DepositAccount;
import ood.Account.LoanAccount;
import ood.Money.Currency;
import ood.Money.Dollar;
import ood.Money.Euro;
import ood.Money.Yuan;
import ood.Person.Customer;

public class MoneyUI {
  private Customer customer;
  private JFrame previousFrame;

  public MoneyUI(Customer customer, JFrame frame){
    this.customer = customer;
    this.previousFrame = frame;
  }

  public void withdraw() {
    LinkedHashMap<UUID, DepositAccount> accounts = customer.getCustomerAccounts()
        .getDepositAccounts();

    if(accounts==null || accounts.size()==0)
      throw new UnsupportedOperationException("You do not have any deposit account yet! "
          + "Please open a new Checking or Savings account.");

    JFrame frame = new JFrame("Withdrawal");
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    JTextArea title = new JTextArea("From which deposit account would you like to withdraw?");
    title.setBounds(90, 10, 400, 50);
    title.setLineWrap(true);
    title.setEditable(false);
    frame.add(title);


    final LinkedHashMap<String, DepositAccount> accountNames = new LinkedHashMap<>();
    for (DepositAccount account : accounts.values()) {
      accountNames.put(account.toString(), account);
    }
    String[] accountOptions = accountNames.keySet().toArray(new String[0]);

    final JComboBox<String> jComboBox = new JComboBox<>(accountOptions);
    jComboBox.setBounds(80, 150, 200, 20);
    frame.add(jComboBox);

    JButton choose = new JButton("Choose");
    choose.setBounds(250, 250, 80, 40);
    frame.add(choose);

    choose.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        DepositAccount account = accountNames.get(
            jComboBox.getItemAt(jComboBox.getSelectedIndex()));
        withdrawFromAccount(account);
      }
    });
  }

  public void withdrawFromAccount(DepositAccount account){
    final JFrame frame = new JFrame("Withdrawal from "+account.toString());
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    JTextArea title = new JTextArea("How much money and which currency would you like "
        + "withdraw from "+account.toString()+"?");
    title.setBounds(90, 10, 400, 50);
    title.setLineWrap(true);
    title.setEditable(false);
    frame.add(title);

    JLabel askamount = new JLabel("Please input the amount here:");
    askamount.setBounds(40, 80, 400, 20);
    frame.add(askamount);

    final JTextField amount = new JTextField("20");
    amount.setBounds(400, 80, 100, 20);
    frame.add(amount);

    JLabel askcurrency = new JLabel("Which currency would you like?");
    askcurrency.setBounds(40, 160, 400, 20);
    frame.add(askcurrency);

    final LinkedHashMap<String, Currency> currencies = account.getCurrencies();
    String[] currencyOptions = currencies.keySet().toArray(new String[0]);

    final JComboBox<String> jComboBox = new JComboBox<>(currencyOptions);
    jComboBox.setBounds(400, 160, 100, 20);
    frame.add(jComboBox);

    JButton withdraw = new JButton("Withdraw");
    withdraw.setBounds(250, 250, 80, 40);
    frame.add(withdraw);

    final DepositAccount withdrawAccount = account;
    withdraw.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        double moneyAmount = Double.parseDouble(amount.getText());
        Currency currency = currencies.get(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
        try{
          customer.withdraw(withdrawAccount,currency,moneyAmount);
          UIToolBox.displayMsg("Successfully Withdrew "+currency+moneyAmount);
        } catch (UnsupportedOperationException exception){
          exception.printStackTrace();
          UIToolBox.displayMsg("Failed to Withdraw "+currency+moneyAmount);
        }
        frame.dispose();
        previousFrame.setVisible(true);
      }
    });
  }


  public void deposit() {
    LinkedHashMap<UUID, DepositAccount> accounts = customer.getCustomerAccounts()
        .getDepositAccounts();

    if(accounts==null || accounts.size()==0)
      throw new UnsupportedOperationException("You do not have any deposit account yet! "
          + "Please open a new Checking or Savings account.");

    JFrame frame = new JFrame("Deposit");
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    JTextArea title = new JTextArea("Into which deposit account would you like to deposit?");
    title.setBounds(90, 10, 400, 50);
    title.setLineWrap(true);
    title.setEditable(false);
    frame.add(title);

    final LinkedHashMap<String, DepositAccount> accountNames = new LinkedHashMap<>();
    for (DepositAccount account : accounts.values()) {
      accountNames.put(account.toString(), account);
    }
    String[] accountOptions = accountNames.keySet().toArray(new String[0]);

    final JComboBox<String> jComboBox = new JComboBox<>(accountOptions);
    jComboBox.setBounds(80, 150, 200, 20);
    frame.add(jComboBox);

    JButton choose = new JButton("Choose");
    choose.setBounds(250, 250, 80, 40);
    frame.add(choose);

    choose.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        DepositAccount account = accountNames.get(
            jComboBox.getItemAt(jComboBox.getSelectedIndex()));
        depositIntoAccount(account);
      }
    });
  }

  public void depositIntoAccount(DepositAccount account){
    final JFrame frame = new JFrame("Deposit Into"+account.toString());
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    JTextArea title = new JTextArea("How much money and which currency would you like "
        + "deposit into "+account.toString()+"?");
    title.setBounds(90, 10, 400, 50);
    title.setLineWrap(true);
    title.setEditable(false);
    frame.add(title);

    JLabel askamount = new JLabel("Please input the amount here:");
    askamount.setBounds(40, 80, 400, 20);
    frame.add(askamount);

    final JTextField amount = new JTextField("20");
    amount.setBounds(400, 80, 100, 20);
    frame.add(amount);

    JLabel askcurrency = new JLabel("Which currency are you depositing?");
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

    JButton deposit = new JButton("Deposit");
    deposit.setBounds(250, 250, 80, 40);
    frame.add(deposit);

    final DepositAccount depositAccount = account;
    deposit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        double moneyAmount = Double.parseDouble(amount.getText());
        assert(moneyAmount>0);

        Currency currency = currencies.get(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
        try{
          customer.deposit(depositAccount,currency,moneyAmount);
          UIToolBox.displayMsg("Successfully Deposited "+currency+moneyAmount);
        } catch (UnsupportedOperationException exception){
          exception.printStackTrace();
          UIToolBox.displayMsg("Failed to Deposit "+currency+moneyAmount);
        }
        frame.dispose();
        previousFrame.setVisible(true);
      }
    });
  }

  public void transfer(){
    LinkedHashMap<UUID,Account> accounts = customer.getCustomerAccounts().getAccounts();
    if(accounts==null || accounts.size()<=1)
      throw new UnsupportedOperationException("You currently have fewer than two accounts! "
          + "You need to have at least two accounts to transfer!");

    final JFrame frame = new JFrame("Transfer");
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    JTextArea title = new JTextArea("Which two accounts would you like to transfer between?");
    title.setBounds(90, 10, 400, 50);
    title.setLineWrap(true);
    title.setEditable(false);
    frame.add(title);

    JLabel asksrc = new JLabel("The source account:");
    asksrc.setBounds(40, 80, 400, 20);
    frame.add(asksrc);

    final LinkedHashMap<String,Account> accountNames = new LinkedHashMap<>();
    for(Account account : accounts.values()){
      accountNames.put(account.toString(),account);
    }
    String[] accountOptions = accountNames.keySet().toArray(new String[0]);

    final JComboBox<String> jComboBox1 = new JComboBox<>(accountOptions);
    jComboBox1.setBounds(400, 80, 100, 20);
    frame.add(jComboBox1);

    JLabel askamount = new JLabel("The amount to transfer:");
    askamount.setBounds(40, 160, 400, 20);
    frame.add(askamount);

    final JTextField amount = new JTextField("100");
    amount.setBounds(400, 160, 100, 20);
    frame.add(amount);

    JLabel askdest = new JLabel("The destination account:");
    askdest.setBounds(40, 240, 400, 20);
    frame.add(askdest);

    final JComboBox<String> jComboBox2 = new JComboBox<>(accountOptions);
    jComboBox2.setBounds(400, 240, 100, 20);
    frame.add(jComboBox2);

    JButton transfer = new JButton("Transfer");
    transfer.setBounds(250, 300, 80, 40);
    frame.add(transfer);

    transfer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Account srcAccount = accountNames.get(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
        double moneyAmount = Double.parseDouble(amount.getText());
        assert(moneyAmount>0);
        Account destAccount = accountNames.get(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()));
        try{
          customer.transfer(srcAccount,moneyAmount,destAccount);
          UIToolBox.displayMsg("Successfully Deposited "+moneyAmount);
        } catch (UnsupportedOperationException exception){
          exception.printStackTrace();
          UIToolBox.displayMsg("Failed to Deposit "+moneyAmount);
        }
        frame.dispose();
        previousFrame.setVisible(true);
      }
    });
  }

  public void exchange(){
    LinkedHashMap<UUID, DepositAccount> accounts = customer.getCustomerAccounts()
        .getDepositAccounts();

    if(accounts==null || accounts.size()==0)
      throw new UnsupportedOperationException("You do not have any deposit account yet! "
          + "Please open a new Checking or Savings account.");

    JFrame frame = new JFrame("Exchange");
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    JTextArea title = new JTextArea("In which deposit account would you like to exchange currencies?");
    title.setBounds(90, 10, 400, 50);
    title.setLineWrap(true);
    title.setEditable(false);
    frame.add(title);

    final LinkedHashMap<String, DepositAccount> accountNames = new LinkedHashMap<>();
    for (DepositAccount account : accounts.values()) {
      accountNames.put(account.toString(), account);
    }
    String[] accountOptions = accountNames.keySet().toArray(new String[0]);

    final JComboBox<String> jComboBox = new JComboBox<>(accountOptions);
    jComboBox.setBounds(80, 150, 200, 20);
    frame.add(jComboBox);

    JButton choose = new JButton("Choose");
    choose.setBounds(250, 250, 80, 40);
    frame.add(choose);

    choose.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        DepositAccount account = accountNames.get(
            jComboBox.getItemAt(jComboBox.getSelectedIndex()));
        exchangeInAccount(account);
      }
    });
  }

  public void exchangeInAccount(DepositAccount account){
    final JFrame frame = new JFrame("Exchange in "+account);
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    JTextArea title = new JTextArea("Which two currencies would you like to transfer between?");
    title.setBounds(90, 10, 400, 50);
    title.setLineWrap(true);
    title.setEditable(false);
    frame.add(title);

    JLabel asksrc = new JLabel("The source currency:");
    asksrc.setBounds(40, 80, 400, 20);
    frame.add(asksrc);

    LinkedHashMap<String, Currency> currencies = account.getCurrencies();
    String[] currencyOptions = currencies.keySet().toArray(new String[0]);
    final JComboBox<String> jComboBox1 = new JComboBox<>(currencyOptions);
    jComboBox1.setBounds(400, 80, 100, 20);
    frame.add(jComboBox1);

    JLabel askamount = new JLabel("The amount in source currency to exchange:");
    askamount.setBounds(40, 160, 400, 20);
    frame.add(askamount);

    final JTextField amount = new JTextField("100");
    amount.setBounds(400, 160, 100, 20);
    frame.add(amount);

    JLabel askdest = new JLabel("The destination currency:");
    askdest.setBounds(40, 240, 400, 20);
    frame.add(askdest);

    final LinkedHashMap<String, Currency> allCurrencies = new LinkedHashMap<>();
    allCurrencies.put("Dollar", new Dollar());
    allCurrencies.put("Euro", new Euro());
    allCurrencies.put("Yuan", new Yuan());
    System.out.println(allCurrencies.keySet());

    final JComboBox<String> jComboBox2 = new JComboBox<>(allCurrencies.keySet().toArray(new String[0]));
    jComboBox2.setBounds(400, 240, 100, 20);
    frame.add(jComboBox2);

    JButton exchange = new JButton("Exchange");
    exchange.setBounds(250, 300, 80, 40);
    frame.add(exchange);

    final DepositAccount depositAccount = account;
    exchange.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Currency srcCurrency = allCurrencies.get(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
        double moneyAmount = Double.parseDouble(amount.getText());
        assert(moneyAmount>0);
        Currency destCurrency = allCurrencies.get(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()));
        try{
          customer.exchange(depositAccount,srcCurrency,destCurrency,moneyAmount);
          UIToolBox.displayMsg("Successfully Exchanged "+moneyAmount);
        } catch (UnsupportedOperationException exception){
          exception.printStackTrace();
          UIToolBox.displayMsg("Failed to Exchange "+moneyAmount);
        }
        frame.dispose();
        previousFrame.setVisible(true);
      }
    });
  }

  public void payInterest(){
    LinkedHashMap<UUID,Account> accounts = customer.getCustomerAccounts().getLoanAccounts();
    if(accounts==null || accounts.size()==0)
      throw new UnsupportedOperationException("You do not have any loan account!");

    JFrame frame = new JFrame("Pay Interest");
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    JTextArea title = new JTextArea("For which loan account would you like to deposit?");
    title.setBounds(90, 10, 400, 50);
    title.setLineWrap(true);
    title.setEditable(false);
    frame.add(title);

    final LinkedHashMap<String, Account> accountNames = new LinkedHashMap<>();
    for (Account account : accounts.values()) {
      accountNames.put(account.toString(), account);
    }
    String[] accountOptions = accountNames.keySet().toArray(new String[0]);

    final JComboBox<String> jComboBox = new JComboBox<>(accountOptions);
    jComboBox.setBounds(80, 150, 200, 20);
    frame.add(jComboBox);

    JButton choose = new JButton("Choose");
    choose.setBounds(250, 250, 80, 40);
    frame.add(choose);

    choose.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        LoanAccount account = (LoanAccount) accountNames.get(
            jComboBox.getItemAt(jComboBox.getSelectedIndex()));
        payInterestForAccount(account);
      }
    });
  }

  public void payInterestForAccount(LoanAccount account){
    final JFrame frame = new JFrame("Pay interest for "+account.toString());
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    final double interest = account.getInterest();

    JLabel askamount = new JLabel("Please confirm that you will be paying "+interest);
    askamount.setBounds(40, 80, 400, 20);
    frame.add(askamount);

    JButton pay = new JButton("Pay");
    pay.setBounds(250, 250, 80, 40);
    frame.add(pay);

    final LoanAccount loanAccount = account;
    pay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try{
          loanAccount.payInterest();
          UIToolBox.displayMsg("Successfully Paid "+interest);
        } catch (UnsupportedOperationException exception){
          exception.printStackTrace();
          UIToolBox.displayMsg("Failed to Pay "+interest);
        }
        frame.dispose();
        previousFrame.setVisible(true);
      }
    });
  }
}
