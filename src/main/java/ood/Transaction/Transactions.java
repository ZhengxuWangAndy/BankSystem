/*Transactions.java: This class represents a collection of transactions.
It contains a linked hashmap that maps the UUID (of a transaction) to a
Transaction. It has function for getting transactions by a specified date.
* */
package ood.Transaction;

import java.util.LinkedHashMap;
import java.util.UUID;
import ood.Transaction.Transaction;

public class Transactions {

    LinkedHashMap<UUID, Transaction> transactionHistory;

    public Transactions() {
        this.transactionHistory = new LinkedHashMap<>();
    }

    public void newTransaction(Transaction t) {
        this.transactionHistory.put(t.getTid(), t);
    }

    public Transaction getTransaction(UUID tid) {
        return this.transactionHistory.get(tid);
    }

    public Transactions getTransactionsByDate(String date) {
        //LinkedHashMap<String, Transaction> dailyTransactions = new LinkedHashMap<>();
        Transactions dailyTransactions = new Transactions();
        for (Transaction t : transactionHistory.values()) {
            if (date.equals(t.getDate())) {
                dailyTransactions.newTransaction(t);
            }
        }
        return dailyTransactions;
    }

}