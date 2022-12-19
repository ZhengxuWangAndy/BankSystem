/*ExchangeTxn.java: child class of Transaction. Has two additional
Currency variables: the source currency and the destination currency.
Its constructor checks whether the Account of this Transaction is a CheckingAccount;
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

public class ExchangeTxn extends Transaction{
  private Currency srcCurrency;
  private Currency destCurrency;

  public ExchangeTxn(Customer o, Account a, Currency c1, Currency c2, double dollarAmount){
    super("Exchange",o,a);
    this.srcCurrency = c1;
    this.destCurrency = c2;
    this.amount = dollarAmount;
    if(a instanceof CheckingAccount)
      this.fee = Transaction.TxnFee;

    DBReader db = new DBReader();
    LocalDateTime now = LocalDateTime.now();
    LinkedHashMap<String,String> row = new LinkedHashMap<>();
    row.put("tid", UUID.randomUUID().toString());
    row.put("From_aid",a.getAid().toString());
    row.put("To_aid", null);
    row.put("amount", String.valueOf(dollarAmount));
    row.put("fee", String.valueOf(Transaction.TxnFee));
    row.put("date", now.toString());
    row.put("From_accNum", null);
    row.put("To_accNum", null);
    row.put("type", "exchange");
    db.addData("Transactions",row);

  }

}
