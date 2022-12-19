/*ViewCustomerStocksUI.java- This class has the UI to view the stocks a customer has
 */
package ood.BankUI;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.UUID;
import ood.Account.*;
import ood.Money.Currency;
import ood.Person.Customer;
import ood.Stock.Stock;
import ood.utils.DBReader;

public class ViewCustomerStocksUI {
    Customer customer;
    private LinkedHashMap<UUID,Account> accounts;
    private LinkedHashMap<UUID,Stock> stocks;

    private JFrame previousFrame;

    public ViewCustomerStocksUI(Customer customer,JFrame frame){
        this.customer = customer;
        this.accounts = customer.getCustomerAccounts().getAccounts();
        this.stocks = customer.getCustomerStocks().getStocks();
        previousFrame = frame;
    }

    public void viewStocks() {
        JFrame frame = new JFrame("Hi " + customer.getUsername() + ", here are your stocks:");

        this.stocks = customer.getCustomerStocks().getStocks();

        final LinkedHashMap<String,Stock> stockNames = new LinkedHashMap<>();
//        for(Stock stock : stocks.values()){
//            if (stock.getStockCount()==0){
//                customer.getCustomerStocks().getStocks().remove(stock.getStockUUID());
//            }}
//        this.stocks = customer.getCustomerStocks().getStocks();
        for(Stock stock : stocks.values()){

            stockNames.put(stock.toStringMyStocks(),stock);
            System.out.println("@@@@@@@@@");
            System.out.println(stock);

        }
        String[] stockNameOptions = stockNames.keySet().toArray(new String[0]);

        int size = stockNames.size();

        if (size ==0){
            // there are no stocks
            UIToolBox.displayDialogBox("Alert!","Sorry, you don't have any stocks! ",frame);

        }
        else{
            // view the stocks
            final JComboBox<String> jComboBox = new JComboBox<>(stockNameOptions);
            jComboBox.setBounds(80, 50, 140, 20);
            frame.add(jComboBox);

            JButton backButton = new JButton("Back");
            backButton.setBounds(100, 150, 80, 25);
            frame.add(backButton);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();

                }
            });

            frame.setLayout(null);
            frame.setSize(350, 250);
            frame.setVisible(true);

        }


    }
}
