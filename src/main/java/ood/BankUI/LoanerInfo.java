/*LoanerInfo.java - This UI class is called when manager clicked on the View
Loaners button in the ManagerUI page. It will display loaners’ information,
such as loan amount.*/
package ood.BankUI;

import ood.Person.Manager;
import ood.utils.DBReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class LoanerInfo {

    private DBReader db = new DBReader();
    private LinkedList<LinkedHashMap<String, String>> loaner;
    private JFrame previousFrame;

    public LoanerInfo(Manager manager, JFrame frame) {
        this.loaner = db.getTable("loanaccounts");
        this.previousFrame = frame;
    }

    public void loanerInfoUI() {
        JFrame frame = new JFrame("Loaders Information Table");
        JPanel panel = new JPanel();
        JLabel colName = new JLabel("User Id/Account Id/Amount/Account Number:");
        panel.add(colName);
        for (int i = 0; i < loaner.size(); i++) {
            JLabel data = new JLabel(loaner.get(i).values().toString());
            panel.add(data);
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
        frame.setSize(600, 200);
        frame.setVisible(true);
    }
}
