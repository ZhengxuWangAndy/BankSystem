/*
CheckingAccount.java is a child class of DepositAccount. They both override the toString() method.
* */
package ood.Account;

import ood.Money.Currency;
import ood.Person.Customer;

import java.util.UUID;

public class CheckingAccount extends DepositAccount {
  public final static double interestRate = 0.01;

  public CheckingAccount(){super();}
  public CheckingAccount(UUID id, Customer o, Currency c, double amount){
    super(id,o,c,amount);
  }

  public String toString(){
    return "Checking "+super.toString();
  }
}
