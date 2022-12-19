/*SavingsAccount.java is a child class of DepositAccount. They override the toString() method.
SavingsAccount has a public final static double variable to store the interest rate for savings accounts.
 */
package ood.Account;

import ood.Money.Currency;
import ood.Person.Customer;

import java.util.UUID;

public class SavingsAccount extends DepositAccount {
  public final static double interestRate = 0.02;

  public SavingsAccount(){super();}
  public SavingsAccount(UUID id, Customer o, Currency c, double amount){
    super(id,o,c,amount);
  }

  public String toString(){
    return "Savings "+super.toString();
  }
}
