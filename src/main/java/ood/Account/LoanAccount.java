/*LoanAccount.java: child class of NonDepositAccount. It has a public final
static double variable to store the interest rate for loan accounts. It includes
a payInterest() method to pay the due on loans, and it also overrides the toString() method.
* */
package ood.Account;

import ood.Person.Customer;

import java.util.UUID;

public class LoanAccount extends NonDepositAccount {
  public final static double interestRate = 0.05;

  public LoanAccount(UUID id, Customer c, double amount){
    super(id,c,amount);
  }

  public double getInterest(){ return this.balance*interestRate; }

  public void payInterest(){
    this.decreaseMoney(this.getInterest());
  }

  public String toString(){
    return "Loan "+super.toString();
  }
}
