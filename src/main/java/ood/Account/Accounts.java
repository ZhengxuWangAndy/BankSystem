/*
This class is used to store all the accounts of a user. It has one private variable,
a linked hash map, that maps UUID (of an account) to a generic type T that extends Account.
The implementation of this class simplifies the actions including getting the total deposit of a user,
getting all accounts of a specific type of a user, transfer between two accounts of a user,
and checking the collateral of a user.
* */
package ood.Account;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.UUID;
import ood.Money.Currency;
import ood.Money.Dollar;
import ood.Money.Wallet;

public class Accounts <T extends Account> {
  private LinkedHashMap<UUID,T> accounts;
  //private Wallet wallet;
//  private double totalBalance;
//  private double totalDeposit;
//  private double totalLoan;

  public Accounts(){
    accounts = new LinkedHashMap<>();
    //wallet = new Wallet();
  }

  public double getTotalDeposit(){
    double totalDeposit = 0;
    for(DepositAccount account : this.getDepositAccounts().values()){
      totalDeposit += account.getBalance();
    }
    return totalDeposit;
  }

  public LinkedHashMap<UUID,T> getAccounts(){ return this.accounts; }

  public LinkedHashMap<UUID,Account> getCheckingAccounts(){
    LinkedHashMap<UUID,Account> accs = new LinkedHashMap<>();
    for(T a : accounts.values()){
      if(a instanceof CheckingAccount) accs.put(a.getAid(),a);
    }
    return accs;
  }

  public LinkedHashMap<UUID,Account> getSavingsAccounts(){
    LinkedHashMap<UUID,Account> accs = new LinkedHashMap<>();
    for(T a : accounts.values()){
      if(a instanceof SavingsAccount) accs.put(a.getAid(),a);
    }
    return accs;
  }

  public LinkedHashMap<UUID, SavingsAccount> getSavingsForSecurity(){
    LinkedHashMap<UUID,SavingsAccount> accs = new LinkedHashMap<>();
    for(T a : accounts.values()){
      if(a instanceof SavingsAccount && a.getBalance()>5000 &&
          ((SavingsAccount) a).getMoneyByCurrency(new Dollar())>=1000 )
        accs.put(a.getAid(),(SavingsAccount) a);
    }
    return accs;
  }

  public LinkedHashMap<UUID,DepositAccount> getDepositAccounts(){
    LinkedHashMap<UUID,DepositAccount> accs = new LinkedHashMap<>();
    for(T a : accounts.values()){
      if(a instanceof DepositAccount) accs.put(a.getAid(),(DepositAccount)a);
    }
    return accs;
  }

  public LinkedHashMap<UUID,Account> getLoanAccounts(){
    LinkedHashMap<UUID,Account> accs = new LinkedHashMap<>();
    for(T a : accounts.values()){
      if(a instanceof LoanAccount) accs.put(a.getAid(),a);
    }
    return accs;
  }

  public LinkedHashMap<UUID,Account> getSecurityAccounts(){
    LinkedHashMap<UUID,Account> accs = new LinkedHashMap<>();
    for(T a : accounts.values()){
      if(a instanceof SecurityAccount) accs.put(a.getAid(),a);
    }
    return accs;
  }

  public void addAccount(T a){
    accounts.put(a.getAid(),a);

  //  wallet.updateAccount(a);
  }

  public void closeAccount(T a){
    accounts.remove(a.getAid());
   // wallet.removeAccount(a);
  }

  public Account getAccount(UUID aid){
    for(UUID id : accounts.keySet()){
      if(aid.equals(id))
        return accounts.get(id);
    }
    throw new NoSuchElementException("No such account.");
  }

  public void withdraw(DepositAccount a, Currency c, double amount){
    assert(accounts.containsValue(a));
    a.decreaseMoney(c,amount);
  }

  public void deposit(DepositAccount a, Currency c, double amount){
    assert(accounts.containsValue(a));
    a.increaseMoney(c,amount);
  }

  public void withdraw(Account a, double amount){
    assert(accounts.containsValue(a));
    if(a instanceof DepositAccount)
      withdraw((DepositAccount) a,new Dollar(),amount);
    else
      ((NonDepositAccount) a).decreaseMoney(amount);
  }

  public void deposit(Account a, double amount){
    assert(accounts.containsValue(a));
    if(a instanceof DepositAccount)
      deposit((DepositAccount) a,new Dollar(),amount);
    else
      ((NonDepositAccount) a).increaseMoney(amount);
  }

  public void transfer(Account srcAccount, double moneyAmount, Account destAccount){
    assert(accounts.containsValue(srcAccount) && accounts.containsValue(destAccount));
    withdraw(srcAccount,moneyAmount);
    deposit(destAccount,moneyAmount);
  }

  public void exchange(DepositAccount a, Currency srcC, Currency destC, double srcAmount){
    assert(accounts.containsValue(a));
    a.exchange(srcC,destC,srcAmount);
  }

  public boolean checkCollateral(double loanAmount){
    //need to have at least five times the loan amount in deposit
    if(this.getTotalDeposit()>5*loanAmount) return true;
    return false;
  }

}
