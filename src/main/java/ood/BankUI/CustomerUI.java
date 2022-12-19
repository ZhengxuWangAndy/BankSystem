/*CustomerUI.java: This class is the UI for the customer once they are logged in*/
package ood.BankUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ood.Account.Account;
import ood.Person.Customer;

public class CustomerUI {
  private Customer customer;

  public CustomerUI(Customer customer){ this.customer = customer; }

  public void customerUI(){
    final JFrame frame = new JFrame(customer.getUsername());
    Color c1 = new Color(182, 228, 250);
    frame.getContentPane().setBackground(c1);
    JLabel label = new JLabel("What would you like to do?");
    frame.add(label);

    JButton openAccount = UIToolBox.addButton("Open Account",20,50,200,30);
    frame.add(openAccount);
    openAccount.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        OpenAccUI oaui = new OpenAccUI(customer,frame);
        frame.setVisible(false);
        oaui.openAcc();
      }
    });

    JButton viewAccount = UIToolBox.addButton("View Account",250,50,200,30);
    frame.add(viewAccount);
    viewAccount.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ViewAccUI vaui = new ViewAccUI(customer,frame);
        vaui.viewAcc();
      }
    });

//    Withdraw
//    Choose an account (savings/checking/loan) and enter a number
    JButton withdraw = UIToolBox.addButton("Withdraw Money",20,100,200,30);
    frame.add(withdraw);
    withdraw.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MoneyUI mui = new MoneyUI(customer,frame);
        mui.withdraw();
      }
    });

//    Deposit
//    Choose an account (savings/checking/loan) and enter a number
    JButton deposit = UIToolBox.addButton("Deposit Money",250,100,200,30);
    frame.add(deposit);
    deposit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MoneyUI mui = new MoneyUI(customer,frame);
        mui.deposit();
      }
    });

//    Transfer
//    Choose accounts and enter number
    JButton transfer = UIToolBox.addButton("Transfer Money",20,150,200,30);
    frame.add(transfer);
    transfer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MoneyUI mui = new MoneyUI(customer,frame);
        mui.transfer();
      }
    });

    JButton exchange = UIToolBox.addButton("Exchange Currency",250,150,200,30);
    frame.add(exchange);
    exchange.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MoneyUI mui = new MoneyUI(customer,frame);
        mui.exchange();
      }
    });

    JButton loan = UIToolBox.addButton("Request Loan",20,200,200,30);
    frame.add(loan);
    loan.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        RequestLoanUI rlui = new RequestLoanUI(customer,frame);
        rlui.requestLoan();
      }
    });


    JButton payInterest = UIToolBox.addButton("Pay Interest",250,200,200,30);
    frame.add(payInterest);
    payInterest.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MoneyUI mui = new MoneyUI(customer,frame);
        mui.payInterest();
      }
    });

//        tradeStock
//    View stocks // customer stock
//    Sell stock -> enter stock id you want to sell
//    Buy stock -> takes to stockmarket
//    Buy stocks -> enter stock id you want to buy
    JButton tradeStock = UIToolBox.addButton("Trade Stock",20,250,200,30);
    frame.add(tradeStock);
    tradeStock.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        CustomerStockUI csui = new CustomerStockUI(customer);
        csui.tradeStock();
      }
    });

    JButton closeAccount = UIToolBox.addButton("Close Account",250,250,200,30);
    frame.add(closeAccount);
    closeAccount.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        OpenAccUI caui = new OpenAccUI(customer,frame);
        frame.setVisible(false);
        caui.closeAcc();
      }
    });

    JButton backButton = new JButton("Back");
    backButton.setBounds(150,300,200,30);
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

}
