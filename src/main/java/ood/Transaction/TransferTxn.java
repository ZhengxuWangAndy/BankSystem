/*TransferTxn.java: child class of Transaction. Has an additional
Account variable for the destination account of this transfer. Its
constructor checks whether the destination account is a CheckingAccount;
if so, the transaction fee occurs.
 */
package ood.Transaction;

import ood.Account.Account;
import ood.Account.CheckingAccount;
import ood.Money.Currency;
import ood.Person.Customer;
import ood.utils.DBReader;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.UUID;

public class TransferTxn extends Transaction{
  private Account dest;

  public TransferTxn(Customer o, Account src, Account dest, double amount){
    super("Transfer",o,src);
    this.dest = dest;
    this.amount = amount;

    if(dest instanceof CheckingAccount)
      this.fee = Transaction.TxnFee;

    DBReader db = new DBReader();
    LinkedHashMap<String, String> row = new LinkedHashMap<>();
    row.put("tid", UUID.randomUUID().toString());
    row.put("From_aid", src.getAid().toString());
    row.put("To_aid", dest.getAid().toString());
    row.put("amount", String.valueOf(amount));
    row.put("fee", String.valueOf(Transaction.TxnFee));
    row.put("date", LocalDateTime.now().toString());
    row.put("From_accNum", "888");
    row.put("To_accNum", "999");
    db.addData("Transactions",row);
  }

}
