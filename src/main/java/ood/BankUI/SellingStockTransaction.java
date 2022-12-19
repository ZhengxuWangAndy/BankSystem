/*SellingStockTransaction.java - This UI class is to select the security account to deposit the money to
 */
package ood.BankUI;

import ood.Account.Account;
import ood.Account.SecurityAccount;
import ood.Person.Customer;
import ood.Stock.Stock;
import ood.utils.DBReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.UUID;

public class SellingStockTransaction {
    Account accountSelected;
    private LinkedHashMap<UUID,Account> accounts;

    public Account getAccountSelected() {
        return accountSelected;
    }

    public void setAccountSelected(Account accountSelected) {
        this.accountSelected = accountSelected;
    }


    public void selectAccForTransaction(Customer customer, Stock stockSelected, int stockCount){
        JFrame frame = new JFrame("Hi " + customer.getUsername() + ", here are your accounts:");
        DBReader db = new DBReader();
        accounts = customer.getCustomerAccounts().getAccounts();
        final LinkedHashMap<String,Account> accountNames = new LinkedHashMap<>();
        for(Account account : accounts.values()){
            if(account instanceof SecurityAccount) {
                accountNames.put(account.getAid().toString(),account);}
        }
        String[] accountOptions = accountNames.keySet().toArray(new String[0]);

        final JComboBox<String> jComboBox = new JComboBox<>(accountOptions);
        jComboBox.setBounds(80, 50, 140, 20);
        frame.add(jComboBox);

        JButton jButton = new JButton("Select Account");
        jButton.setBounds(100, 100, 150, 20);
        frame.add(jButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 150, 80, 25);
        frame.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        });

        frame.setLayout(null);
        frame.setSize(350, 250);
        frame.setVisible(true);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Account account = accountNames.get(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
//                LinkedHashMap<String, Stock> stockMarket = getStockMarket();
                float sellingPriceStock = stockCount*stockSelected.getStockPrice();
                double bankAmt = account.getBalance();
                System.out.println("stock selected to sell -> "+ stockSelected.getStockName());
                System.out.println("market price of that stock -> "+ stockCount*stockSelected.getStockPrice());
                System.out.println("bank amt "+ bankAmt);
                System.out.println("stockCount "+stockCount);
                System.out.println("sellingPriceStock "+ sellingPriceStock);
                System.out.println("SELLING STOCK BEFORE BANK ACCOUNT MONEY ->"+customer.getAccounts().getAccount(account.getAid()).getBalance());

                db.changeData("SecurityAccounts", "amount", String.valueOf(bankAmt+sellingPriceStock), "aid", account.getAid().toString());
                customer.getAccounts().getAccount(account.getAid()).setBalance(bankAmt+sellingPriceStock);
                System.out.println(customer.getAccounts().getAccount(account.getAid()).getBalance());
                System.out.println("AFTERRRRR SELLING STOCK BEFORE BANK ACCOUNT MONEY ->"+customer.getAccounts().getAccount(account.getAid()).getBalance());

                frame.dispose();


            }
        });
    }
    public LinkedHashMap<String, Stock> getStockMarket(){
        DBReader db = new DBReader();

        LinkedList<LinkedHashMap<String, String>> stockMarket = db.getTable("StockMarket");
        final LinkedHashMap<String, Stock> stocksInfo = new LinkedHashMap<>();
        for(int i = 0; i < stockMarket.size(); i++){
            System.out.println(stockMarket.get(i).get("stockID"));

            Stock stockI = new Stock(stockMarket.get(i).get("stockName"), 100,Float.parseFloat(stockMarket.get(i).get("stockPrice")),Float.parseFloat(stockMarket.get(i).get("stockHighPrice")),0, UUID.fromString(stockMarket.get(i).get("stockID")));
            stocksInfo.put(stockMarket.get(i).get("stockID"),stockI);

        }

        return stocksInfo;
    }

}
