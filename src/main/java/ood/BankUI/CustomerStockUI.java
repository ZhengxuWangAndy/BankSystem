/*CustomerStockUI.java - This class has the UI to buy, sell and view the stocks*/
package ood.BankUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.UUID;
import javax.swing.*;

import ood.Account.Account;
import ood.Person.Customer;
import ood.Stock.CustomerStocks;
import ood.Stock.Stock;

public class CustomerStockUI {
  private Customer customer;
  private LinkedHashMap<UUID,Account> accounts;

  private LinkedHashMap<UUID, Stock> customerStocks;

  public CustomerStockUI(Customer customer){
    this.customer = customer;
//    this.customerStocks = customer.getCustomerStocks().getStocks();
    this.accounts = customer.getCustomerAccounts().getAccounts();

  }

  public void tradeStock() {
    JFrame frame = new JFrame("Trading Stocks");
    JLabel label = new JLabel("Here are your options:");

    JButton buyStock = UIToolBox.addButton("Buy Stock",20,50,200,30);
    frame.add(buyStock);
    buyStock.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
//        Open stock market
        BuyCustomerStocksUI bcsui = new BuyCustomerStocksUI(customer,frame);
        bcsui.buyStocks();
      }
    });

    JButton sellStock = UIToolBox.addButton("Sell Stock",250,50,200,30);
    frame.add(sellStock);
    sellStock.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //sell stocks
        SellCustomerStocksUI cssui = new SellCustomerStocksUI(customer,frame);
        cssui.sellStocks();
      }
    });

    JButton viewStocks = UIToolBox.addButton("View Stocks",20,100,200,30);
    frame.add(viewStocks);
    viewStocks.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ViewCustomerStocksUI vcsui = new ViewCustomerStocksUI(customer,frame);
        vcsui.viewStocks();
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

}
