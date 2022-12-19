/*BuyCustomerStocksUI.java -  This class has the UI to buy the stocks
for the customer*/
package ood.BankUI;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.UUID;
import ood.Account.*;
import ood.Person.Customer;
import ood.Stock.Stock;

public class BuyCustomerStocksUI {
    Customer customer;
    private LinkedHashMap<UUID,Account> accounts;
    private LinkedHashMap<UUID,Stock> stocks;

    private JFrame previousFrame;

    public BuyCustomerStocksUI(Customer customer,JFrame frame){
        this.customer = customer;
        this.accounts = customer.getCustomerAccounts().getAccounts();
        this.stocks = customer.getCustomerStocks().getStocks();
        previousFrame = frame;
    }

    public void buyStocks() {
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

            BuyFromStockMarketUI smui = new BuyFromStockMarketUI();
            smui.displayStocksToBuyMarket(customer);

        }

    }

}
