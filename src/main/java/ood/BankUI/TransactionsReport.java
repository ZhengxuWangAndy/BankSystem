/*TransactionsReport.java - This UI class is called when manager clicked on the View Daily Transactions button in the ManagerUI page. It will display customers’ transactions in current date.
 */
package ood.BankUI;

import ood.Person.Customer;
import ood.Person.Manager;
import ood.utils.DBReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class TransactionsReport {

    private DBReader db = new DBReader();
    private LinkedList<LinkedHashMap<String, String>> transactions;
    private JFrame previousFrame;

    public TransactionsReport(Manager manager, JFrame frame) {
        this.transactions = db.getTable("transactions");
        this.previousFrame = frame;
    }

    public void transactionsReportUI() {
        JFrame frame = new JFrame("Daily Transactions Report");
        JPanel panel = new JPanel();
        JLabel colName = new JLabel("Transaction Id/ Sender Customer Id/ Receiver Customer Id/ Amount/ Fee/ Date/" +
                " Sender Account Number/ Receiver Account Number/ Stock Amount/ StockID/ type:");
        panel.add(colName);

        for (int i = 0; i < transactions.size(); i++){
//            DateTimeFormatter date = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss");
            LocalDate now = LocalDate.now();
            LocalDate time = LocalDate.parse(transactions.get(i).get("date").substring(0,10));
            int days = now.compareTo(time);    //compute transaction date and now
            if (days < 1){
                JLabel data = new JLabel(transactions.get(i).values().toString());
                panel.add(data);
            }
        }
        JButton returnBtn = new JButton("Return");
        panel.add(returnBtn);
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                previousFrame.setVisible(true);
            }
        });
        frame.add(panel);
        frame.setSize(1000, 500);
        frame.setVisible(true);
    }
}
