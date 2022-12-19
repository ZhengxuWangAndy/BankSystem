/*SecurityAccount.java: child class of NonDepositAccount.
Note that its constructor will check the initial amount of
money used to create an instance of this class, since security
accounts must be opened with at least $1000. It also overrides the toString() method.
* */
package ood.Account;

import ood.Person.Customer;
import ood.Stock.CustomerStocks;

import java.util.UUID;

public class SecurityAccount extends NonDepositAccount {

  public SecurityAccount(UUID id, Customer c, double amount) {
    super(id, c, amount);
    if (amount < 1000)
      throw new UnsupportedOperationException("New security accounts must be "
          + "opened with at least $1000!");
  }

  public String toString(){
    return "Security "+super.toString();
  }

}
