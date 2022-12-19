/*
NonDepositAccount.java: child class of Account. It has a protected
double variable to store the balance in this account, and it has methods
 for increasing and decreasing money in the account (no need to specify the currency).
* */

package ood.Account;

import ood.Person.Customer;

import java.util.UUID;

public class NonDepositAccount extends Account{
  protected double lowerLimit = 0;

  public void setBalance(double balance) {
    this.balance = balance;
  }

  protected double balance = 0;

  public NonDepositAccount(UUID id, Customer c, double amount){
    super(id,c);
    balance = amount;
  }

  public double getLowerLimit(){ return lowerLimit;}
  public double getBalance(){ return balance;}

  public void increaseMoney(double amount){
    this.balance += amount;
  }

  public void decreaseMoney(double amount){
    if(this.balance-amount < lowerLimit)
      throw new UnsupportedOperationException("Insufficient balance. "
          + "The balance of this account must be at least $"+lowerLimit);
    this.balance -= amount;
  }
}
