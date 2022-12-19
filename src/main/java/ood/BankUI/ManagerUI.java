/*ManagerUI.java - This class takes a manager variable as input to display UI of that manager.
 */
package ood.BankUI;

import ood.Person.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerUI {

    private Manager manager;
    private String customer_id;

    public ManagerUI(Manager manager) { this.manager = manager; }

    public void managerUI() {
        JFrame frame = new JFrame("Manager User Interface");
        JButton viewDailyTransactionBtn = UIToolBox.addButton("View Daily Transaction(s)", 50, 50);
        frame.add(viewDailyTransactionBtn);
        viewDailyTransactionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                TransactionsReport transactionsReport = new TransactionsReport(manager, frame);
                transactionsReport.transactionsReportUI();
            }
        });

        JButton viewAllCustomerBtn = UIToolBox.addButton("View Customers", 50, 100);
        frame.add(viewAllCustomerBtn);
        viewAllCustomerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CustomerInfo customerInfo = new CustomerInfo(manager, frame);
                customerInfo.customerInfoUI();
            }
        });

        JButton viewLoanerBtn = UIToolBox.addButton("View All Loaners", 50, 150);
        frame.add(viewLoanerBtn);
        viewLoanerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                LoanerInfo loanerInfo = new LoanerInfo(manager, frame);
                loanerInfo.loanerInfoUI();
            }
        });

        JButton managerStockBtn = UIToolBox.addButton("Manage Stocks", 50, 200);
        frame.add(managerStockBtn);
        managerStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ManageStock manageStock = new ManageStock(manager, frame);
                manageStock.manageStockUI();
            }
        });
        frame.setSize(350,350);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
