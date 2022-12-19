/*ManageStock.java -  This UI class is for the stock data*/
package ood.BankUI;

import ood.Person.Manager;
import ood.utils.DBReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ManageStock {

    private DBReader db = new DBReader();
    private LinkedList<LinkedHashMap<String, String>> stocks;
    private JFrame previousFrame;

    public ManageStock(Manager manager, JFrame frame) {
        this.stocks = db.getTable("stockmarket");
        this.previousFrame = frame;
    }

    public void manageStockUI() {
        JFrame frame = new JFrame("Manage Stock Window");
        JPanel panel = new JPanel();
        JLabel colName = new JLabel("Stock Id/Stock Name/Stock Price/Stock High Price/Date");
        panel.add(colName);
        for (int i = 0; i < stocks.size(); i++) {
            JLabel data = new JLabel(stocks.get(i).values().toString());
            panel.add(data);
        }
        frame.add(panel);
        JLabel getIdLabel = new JLabel("Enter a Stock Id to Change Price");
        panel.add(getIdLabel);
        JTextField getIdTextF = new JTextField(20);
        panel.add(getIdTextF);
        JTextField getPriceTextF = new JTextField(5);
        panel.add(getPriceTextF);
        JButton getIdBtn = new JButton("Submit");
        panel.add(getIdBtn);
        JButton returnBtn = new JButton("Return");
        panel.add(returnBtn);
        getIdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.changeData("stockmarket", "stockPrice", getPriceTextF.getText(), "stockID", getIdTextF.getText());
                String highPrice = db.getData("stockmarket", "stockID", getIdTextF.getText()).get("stockHighPrice");
                System.out.println(highPrice);
                if (Integer.parseInt(getPriceTextF.getText()) > Integer.parseInt(highPrice))
                    db.changeData("stockmarket", "stockHighPrice", getPriceTextF.getText(), "stockID", getIdTextF.getText());
                frame.setVisible(false);
                previousFrame.setVisible(true);
            }
        });
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                previousFrame.setVisible(true);
            }
        });
        frame.setSize(600, 500);
        frame.setVisible(true);
    }
}
