/*SellCustomerStocksUI - This UI class is for selling customer stocks*/
package ood.BankUI;

import ood.Account.Account;
import ood.Account.SecurityAccount;
import ood.Person.Customer;
import ood.Stock.Stock;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.UUID;

public class SellCustomerStocksUI {
    Customer customer;
    private LinkedHashMap<UUID, Account> accounts;
    private LinkedHashMap<UUID, Stock> stocks;

    private JFrame previousFrame;

    public SellCustomerStocksUI(Customer customer,JFrame frame){
        this.customer = customer;
        this.accounts = customer.getCustomerAccounts().getAccounts();
        this.stocks = customer.getCustomerStocks().getStocks();
        previousFrame = frame;
    }

    public void sellStocks() {
        LinkedHashMap<UUID,Account> accs = new LinkedHashMap<>();
        for(Account a : accounts.values()){
            if(a instanceof SecurityAccount) {
                accs.put(a.getAid(),a);}
        }
        int size = accs.size();

        if (size ==0){
            // there is no security account, prompt to create one
            UIToolBox.displayDialogBox("Alert!","Sorry, you don't have a security account! ",previousFrame);

        }
        else {
            // security account exists, view stock market

            SellFromCustomerCollectionUI sfcc = new SellFromCustomerCollectionUI();
            sfcc.stockToSell(customer);
        }

    }

}
