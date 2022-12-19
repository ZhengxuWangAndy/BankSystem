/*BuyStockTxn.java : it is a child class of Transaction.
Have an additional Stock variable and an integer of stock numbers
* */
package ood.Transaction;

import ood.Account.SecurityAccount;
import ood.Person.Customer;
import ood.Stock.Stock;

public class BuyStockTxn extends Transaction{
  private Stock stock;
  private int stockNum;

  public BuyStockTxn(Customer o, SecurityAccount a, Stock s, int stockNum, double amount){
    super("Buy Stock",o,a);
    this.stock = s;
    this.stockNum = stockNum;
    this.amount = amount;
  }

}
