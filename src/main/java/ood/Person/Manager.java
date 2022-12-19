/*Manager.java: This is a child class that extends the Person.java.
Each manager will manage an ArrayList of customers.*/
package ood.Person;

import ood.Account.*;
import ood.Transaction.*;
import ood.utils.DBReader;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Manager extends Person{

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private ArrayList<Customer> customers;

    public Manager(UUID ID, String username, String password) {
        super(ID, username, password);
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customers) { this.customers.add(customers); }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public Customer getOneCustomer(String uuid) {
        for (int i = 0; i < customers.size(); i++){
            if (customers.get(i).getID().equals(uuid))
                return customers.get(i);
        }
        return null;
    }

    public ArrayList<Customer> getLoader() {
        ArrayList<Customer> loaders = new ArrayList<>();
        for (Customer c : customers) {
            if (!c.getCustomerAccounts().getLoanAccounts().isEmpty()) {
                loaders.add(c);
            }
        }
        return loaders;
    }

    public LinkedHashMap getDailyTransactions() {
//        org.joda.time.format.DateTimeFormatter date = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss");
//        Date now = LocalDateTime.now().toDate();
//
        LinkedHashMap<String, Transactions> dailyTransactions = new LinkedHashMap<>();
//        DBReader db = new DBReader();
//        LinkedList<LinkedHashMap<String,String>> table = db.getTable("Transactions");
//        for (int i = 0; i < table.size(); i++){
//             Date time = DateTime.parse(table.get(i).get("date"), date).toDate();
//             int days = now.compareTo(time);    //compute transaction date and now
//             System.out.println(days);
//        }



//        String currentDate = LocalDateTime.now().format(dtf);
//        for (Customer c : customers.values()) {
//            dailyTransactions.put(c.getID(), c.getTransactions().getTransactionsByDate(currentDate));
//        }
        return dailyTransactions;
    }
}