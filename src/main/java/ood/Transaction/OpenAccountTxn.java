/*OpenAccountTxn.java:This class is for the transaction of opening an account
 */
package ood.Transaction;

import ood.Account.Account;
import ood.Person.Customer;
import ood.utils.DBReader;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.UUID;

public class OpenAccountTxn extends Transaction{
  public OpenAccountTxn(Customer o, Account a){
    super("Open Account",o,a);
    this.fee = Transaction.TxnFee;

    DBReader db = new DBReader();
    LocalDateTime now = LocalDateTime.now();
    LinkedHashMap<String,String> row = new LinkedHashMap<>();
    row.put("tid", UUID.randomUUID().toString());
    row.put("From_aid",a.getAid().toString());
    row.put("To_aid", null);
    row.put("amount", "0");
    row.put("fee", String.valueOf(Transaction.TxnFee));
    row.put("date", now.toString());
    row.put("From_accNum", null);
    row.put("To_accNum", null);
    row.put("type", "openAccount");
    db.addData("Transactions",row);
  }
}
