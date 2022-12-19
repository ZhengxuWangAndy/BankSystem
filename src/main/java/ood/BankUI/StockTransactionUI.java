/*StockTransactionUI.java -  This class is to select the security
account the customer wants to use to buy the stock
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
import java.util.UUID;

public class StockTransactionUI {

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

        JButton jButton = new JButton("Select");
        jButton.setBounds(100, 100, 90, 20);
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

                float costPriceStock = stockCount*stockSelected.getStockPrice();
                double bankAmt = account.getBalance();
                System.out.println("STOCK TRANSACTION -- ACCOUNT SELECTED TO BUY THE STOCK -> "+ account.getAid().toString());
                System.out.println("STOCK TRANSACTION -- cost of stock  -> "+ costPriceStock);
                System.out.println("STOCK TRANSACTION -- existing money -> "+ bankAmt);


                if (bankAmt-costPriceStock>=0){
                    DBReader db = new DBReader();
                    System.out.println("BEFORE STOCK TRANSACTION -> "+customer.getAccounts().getAccount(account.getAid()).getBalance());

                    db.changeData("SecurityAccounts", "amount", String.valueOf(bankAmt-costPriceStock), "aid", account.getAid().toString());
                    customer.getAccounts().getAccount(account.getAid()).setBalance(bankAmt-costPriceStock);

                    System.out.println("AFTER STOCK TRANSACTION  ->  "+ customer.getAccounts().getAccount(account.getAid()).getBalance());
                }
                else{
                    UIToolBox.displayDialogBox("ALERT!", "Insufficient balance to make the transaction!",frame);
                }

                frame.dispose();



            }
        });
    }

}
