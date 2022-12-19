/*Wallet.java: This class is used to support deposits in different currencies. It has two private
 linked hashmaps, one maps String to Currency to store all the currencies present in this wallet,
 and the other maps Currency to Double to store the value of each currency in this wallet. This
 class enables a user to deposit, get their balance, and withdraw in a specific currency, and to
 exchange between two currencies.
 */
package ood.Money;

import ood.Account.Account;
import ood.Account.DepositAccount;
import ood.Money.Currency;
import java.util.LinkedHashMap;
import java.util.UUID;

public class Wallet {
    private LinkedHashMap<String,Currency> currencyMap;
    private LinkedHashMap<Currency, Double> moneyMap;
    //private double totalBalance; //in dollars

    public Wallet() {
        this.currencyMap = new LinkedHashMap<>();
        this.moneyMap = new LinkedHashMap<>();
    }

    public int getCurrencyNum(){ return currencyMap.size();}
    public LinkedHashMap<Currency, Double> getMoneys(){ return this.moneyMap; }
    public LinkedHashMap<String,Currency> getCurrencies(){return this.currencyMap;}

    public double getBalance(){
        double balance = 0;
        for(Currency c : moneyMap.keySet()){
            balance += Wallet.getDollarAmount(c,moneyMap.get(c));
        }
        return balance;
    }

    public double getMoneyByCurrency(Currency c){
        Currency currency = currencyMap.get(c.getCname());
        if(currency==null) //no such currency in this wallet
            throw new IllegalArgumentException("No such currency!");
        return moneyMap.get(currency);
    }

    public void addMoney(Currency c, double amount) {
        Currency currency = currencyMap.get(c.getCname());

        if (currency==null) { //no such currency yet
            currencyMap.put(c.getCname(),c);
            moneyMap.put(c, amount);
        }
        else
            moneyMap.put(currency, moneyMap.get(currency) + amount);
        //updateBalance(getDollarAmount(c,amount));
    }

    public void withdrawMoney(Currency c, Double amount) {
        Currency currency = currencyMap.get(c.getCname());

        if (currency==null) //no such currency
            throw new UnsupportedOperationException("No such currency!");
        if(moneyMap.get(currency)<amount)
            throw new UnsupportedOperationException("Not enough money to withdraw!");

        moneyMap.put(currency, moneyMap.get(currency)-amount);
        if(moneyMap.get(currency)==0) {
            moneyMap.remove(currency);
            currencyMap.remove(currency.getCname());
        }
    }

    public void exchange(Currency src, Currency dest, double srcAmount){
        assert(currencyMap.containsValue(src));
        Currency srcC = currencyMap.get(src.getCname());
        Currency destC = currencyMap.get(dest.getCname());

        double currentAmount = this.getMoneyByCurrency(src);
        if(moneyMap.get(srcC)<srcAmount)
            throw new UnsupportedOperationException("You do not have enough money in "+src.getCname());

        double destAmount = srcAmount*src.getExchangeRate()/ dest.getExchangeRate();

        if(moneyMap.get(srcC)==srcAmount) {
            moneyMap.remove(srcC);
            currencyMap.remove(src.getCname());
        }
        else
            moneyMap.put(srcC,moneyMap.get(srcC)-srcAmount);

        if(destC!=null)
            moneyMap.put(destC,moneyMap.get(destC)+destAmount);
        else{
            currencyMap.put(dest.getCname(),dest);
            moneyMap.put(dest,destAmount);
        }
    }

    public static double getDollarAmount(Currency c, double cAmount){
        return cAmount * c.getExchangeRate();
    }
}
