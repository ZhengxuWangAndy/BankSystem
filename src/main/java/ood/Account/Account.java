/*
 An abstract class used to represent a bank account.
 It stores the UUID account id and the Customer owner as protected variables.
 This class overrides the toString() method. Its getBalance() method is abstract and
 yet to be implemented in non-abstract child classes.

* */
package ood.Account;

import ood.Transaction.Transaction;
import ood.Transaction.Transactions;
import ood.Person.Customer;
import java.util.UUID;

public abstract class Account {
  protected UUID aid;
  protected Customer owner;

  public Account(){
    this.aid = UUID.randomUUID();
  }

  public Account(UUID id, Customer o){
    this.aid = id;
    this.owner = o;
  }

  public void setAccount(Customer o){
    this.owner = o;
  }

  public UUID getAid(){ return this.aid; }
  public Customer getOwner(){ return this.owner; }
  public abstract double getBalance();
  public abstract void setBalance(double amt);

  public String toString(){
    String str = "Account "+this.aid.toString();
    return str;
  }

}
