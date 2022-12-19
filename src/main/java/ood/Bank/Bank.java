/*Bank.java: This class stores everything in the bank, including
the list of currencies the bank supports, the list of customers, the
list of transactions, and the StockMarket.
 */

package ood.Bank;

import ood.Money.Currency;
import ood.Person.Customer;
import ood.Transaction.Transactions;
import ood.Stock.StockMarket;
import java.util.LinkedHashMap;
import java.util.UUID;

public class Bank {

    private LinkedHashMap<UUID, Currency> currencies;
    private LinkedHashMap<UUID, Customer> customers;
    private Transactions transactions;
    private StockMarket stocks;

    public Bank(){}

    Bank(StockMarket stocks){
        this.stocks = stocks;
    }

    public Bank(LinkedHashMap currencies, LinkedHashMap customers, Transactions transactions, StockMarket stocks) {
        this.currencies = currencies;
        this.customers = customers;
        this.transactions = transactions;
        this.stocks = stocks;
    }

    public String toString() {
        String s = "";
        s += "Currencies: " + currencies + " Customers: " + customers + " Transactions: " + transactions + " Stocks: " + stocks;
        return s;
    }
}
