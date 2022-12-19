/*DepositAccount.java: child class of Account. It has an additional
protected Wallet variable to support deposits in different currencies.
It also includes methods for increasing and decreasing money in the account
(need to specify the currency), and exchanging currencies. The total balance
of a deposit account is always calculated in dollars.
 */
package ood.Account;

import java.util.LinkedHashMap;
import java.util.UUID;

import ood.Money.Currency;
import ood.Money.Wallet;
import ood.Person.Customer;

public class DepositAccount extends Account{
  protected Wallet wallet;

  public DepositAccount(){super();}

  public DepositAccount(UUID id, Customer o, Currency c, double amount){
    super(id, o);
    this.wallet = new Wallet();
    this.wallet.addMoney(c,amount);
  }

  public void setAccount(Customer o, Currency c, double amount){
    super.setAccount(o);
    this.wallet = new Wallet();
    this.wallet.addMoney(c,amount);
  }

  public double getBalance(){
    return wallet.getBalance();
  }

  @Override
  public void setBalance(double amt) {

  }

  ;

  public LinkedHashMap<String,Currency> getCurrencies(){return this.wallet.getCurrencies();}
  public LinkedHashMap<Currency, Double> getMoneys(){
    return this.wallet.getMoneys();
  }

  public int getCurrencyNum(){ return this.wallet.getCurrencyNum(); }

  public double getMoneyByCurrency(Currency currency){ return wallet.getMoneyByCurrency(currency);}

  public void increaseMoney(Currency c, double amount){
    this.wallet.addMoney(c,amount);
    //this.balance += m.getDollarAmount();
  }
  public void decreaseMoney(Currency c, double amount){
    this.wallet.withdrawMoney(c,amount); //need to catch error
    //this.balance -= m.getDollarAmount();
  }

  public void exchange(Currency src, Currency dest, double srcAmount){
    this.wallet.exchange(src,dest,srcAmount);
  }
}
