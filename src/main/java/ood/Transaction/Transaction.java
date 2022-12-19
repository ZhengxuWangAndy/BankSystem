/*Transaction.java: This class represents a transaction. It stores the
UUID (transaction id), Date, String name, Customer who made the transaction,
Account of this transaction, the amount and the fee of this transaction all
as protected variables. It also stores the TxnFee as a public final static double variable.
* */
package ood.Transaction;

import ood.Money.Currency;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import ood.Account.Account;
import ood.Money.Currency;
import ood.Person.Customer;

public class Transaction {
    public final static double TxnFee = 10;

    // Format date as 04-12-2022 10:43:53
    //private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    protected UUID tid;
    protected Date date;
    protected String name;
    protected Customer owner;
    protected Account src;
    //protected Account dest;
    //protected Currency currency;
    //private String time;
    protected double amount;
    protected double fee;

    public Transaction(String name,Customer o, Account a) {
        this.tid = UUID.randomUUID();
        this.name = name;
        this.date = new Date();
        this.owner = o;
        this.src = a;
    }

//    public Transaction(String name, Account sender, Account receiver, Currency currency, double amount, double fee) {
//        this.tid = UUID.randomUUID();
//        this.receiver = receiver;
//        this.sender = sender;
//        this.currency = currency;
//        this.time = LocalDateTime.now().format(dtf);
//        this.amount = amount;
//        this.fee = amount * 0.05;
//    }

    public UUID getTid() {
        return tid;
    }

    public void setTid(UUID tid) {
        this.tid = tid;
    }

//    public Account getReceiver() {
//        return receiver;
//    }
//
//    public void setReceiver(Account receiver) {
//        this.receiver = receiver;
//    }
//
//    public Account getSender() {
//        return sender;
//    }
//
//    public void setSender(Account sender) {
//        this.sender = sender;
//    }

//    public Currency getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(Currency currency) {
//        this.currency = currency;
//    }

    public Date getDate() {
        return date;
    }

//    public void setTime(String time) {
//        this.date = time;
//    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }


}
