/*WithdrawTxn.java: child class of Transaction. Has an additional Currency
variable. Transaction fee always occurs.
* */
package ood.Transaction;

import ood.Account.Account;
import ood.Account.CheckingAccount;
import ood.Money.Currency;
import ood.Person.Customer;
import ood.utils.DBReader;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.UUID;

public class WithdrawTxn extends Transaction{
  private Currency currency;

  public WithdrawTxn(Customer o, Account a, Currency c, double amount){
    super("Withdrawal",o,a);
    this.currency = c;
    this.amount = amount;
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
    row.put("type", "withdraw");
    db.addData("Transactions",row);
  }

}
