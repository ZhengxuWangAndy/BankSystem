/*DepositTxn.java: child class of Transaction. Has an additional Currency variable since we support deposits in different currencies.
Its constructor checks whether the Account of this Transaction is a CheckingAccount;
if so, the transaction fee occurs.
* */
package ood.Transaction;

import ood.Account.Account;
import ood.Account.CheckingAccount;
import ood.Money.Currency;
import ood.Person.Customer;
import ood.utils.DBReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.UUID;

public class DepositTxn extends Transaction{
  private Currency currency;

  public DepositTxn(Customer o, Account a, Currency c, double amount){
    super("Deposit",o,a);
    this.currency = c;
    this.amount = amount;
    if(a instanceof CheckingAccount)
      this.fee = Transaction.TxnFee;
    DBReader db = new DBReader();
    LocalDateTime now = LocalDateTime.now();
    LinkedHashMap<String,String> row = new LinkedHashMap<>();
    row.put("tid", UUID.randomUUID().toString());
    row.put("From_aid",a.getAid().toString());
    row.put("To_aid", null);
    row.put("amount", String.valueOf(amount));
    row.put("fee", String.valueOf(Transaction.TxnFee));
    row.put("date", now.toString());
    row.put("From_accNum", null);
    row.put("To_accNum", null);
    row.put("type", "deposit");
    db.addData("Transactions",row);
  }

}
