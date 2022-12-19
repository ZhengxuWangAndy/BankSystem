/*BuyFromStockMarketUI.java - This class has the UI to
buy the stocks from the stock market

 * */
package ood.BankUI;

import ood.Person.Customer;
import ood.Stock.Stock;
import ood.Stock.StockMarket;
import ood.utils.DBReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.UUID;

public class BuyFromStockMarketUI {

    Stock stockSelected;

    public Stock getStockSelected() {
        return stockSelected;
    }

    public void setStockSelected(Stock stockSelected) {
        this.stockSelected = stockSelected;
    }

    public void displayStocksToBuyMarket(Customer customer){


        DBReader db = new DBReader();
        LinkedList<LinkedHashMap<String, String>> stockInfo = db.getTable("StockMarket");
        StockMarket stockMarket = new StockMarket();


        final JFrame frame = new JFrame();


        JLabel askamount = new JLabel("Please enter the number of stocks to buy here: (minimum is 1)");
        askamount.setBounds(40, 40, 400, 20);
        frame.add(askamount);

        final JTextField amount = new JTextField("1");
        amount.setBounds(450, 40, 100, 20);
        frame.add(amount);

        JLabel askStock = new JLabel("Which stock would you like to buy?");
        askStock.setBounds(40, 10, 400, 20);
        frame.add(askStock);

        final LinkedHashMap<String, Stock> stocks = new LinkedHashMap<>();

        for(int i = 0; i < stockInfo.size(); i++){
            System.out.println(stockInfo.get(i).get("stockID"));
            Stock stock = new Stock(stockInfo.get(i).get("stockName"), 100,Float.parseFloat(stockInfo.get(i).get("stockPrice")),Float.parseFloat(stockInfo.get(i).get("stockHighPrice")),0, UUID.fromString(stockInfo.get(i).get("stockID")));
            stocks.put(stockInfo.get(i).get("stockName"),stock);
            stockMarket.addStockToMarket(stock);

        }

//        for(int i = 0; i < stockInfo.size(); i++){
//          stocks.put(stockInfo.get(i).get("stockName"),stockInfo.get(i));
//
//        }

        String[] stockOptions = stocks.keySet().toArray(new String[0]);

        final JComboBox<String> jComboBox = new JComboBox<>(stockOptions);
        jComboBox.setBounds(400, 15, 100, 20);
        frame.add(jComboBox);


        JButton viewStockInfo = new JButton("Select Stock");
        viewStockInfo.setBounds(250, 100, 120, 40);
        frame.add(viewStockInfo);

        JTextArea title = new JTextArea();
        title.setBounds(90, 150, 400, 100);
        title.setLineWrap(true);
        title.setEditable(false);
        frame.add(title);

        viewStockInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setStockSelected(stocks.get(jComboBox.getItemAt(jComboBox.getSelectedIndex())));
                System.out.println("STOCK SELECTED IN THE BUY -> " + stockSelected);

                title.setText("Stock Name: "+ stockSelected.getStockName() + "\t Stock ID: "+stockSelected.getStockUUID().toString()
                        + "\n Stock Price: "+stockSelected.getStockPrice());

            }
        });


        JButton open = new JButton("Buy");
        open.setBounds(250, 250, 80, 40);
        frame.add(open);

        JButton backButton = new JButton("Back");
        backButton.setBounds(250, 300, 80, 25);
        frame.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setSize(600,400);
        frame.setLayout(null);
        frame.setVisible(true);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int stockCount = Integer.parseInt(amount.getText());
                System.out.println("BUY STOCK COUNT -- "+ stockCount);
//                if (stockCount<stockSelected.getStockCount()){
                    // add customerinfoTable

                frame.dispose();
//                    DBReader db = new DBReader();
//                    LinkedList<LinkedHashMap<String, String>> stockM = db.getTable("StockMarket");
//                    float stockMP=0;
//
//                    for(int i = 0; i < stockM.size(); i++){
//                        if (stockM.get(i).get("stockID").equals(stockSelected.getStockUUID().toString())){
//                            stockMP = Float.parseFloat(stockM.get(i).get("stockPrice"));
//                            break;
//                        }}
//
//                    if (stockMP==0){
//                        stockMP=stockSelected.getStockPrice();
//                    }

                LinkedHashMap<String, String> row = new LinkedHashMap<>();
                row.put("userID",customer.getID().toString());
                row.put("stockID", stockSelected.getStockUUID().toString());
                row.put("stockCount", String.valueOf(stockCount));
                row.put("purchasePrice", String.valueOf(stockSelected.getStockPrice()));
                row.put("stockName", stockSelected.getStockName());
                row.put("stockMarketPrice", String.valueOf(stockSelected.getStockPrice()));
                db.addData("CustomerStockInfo", row);

                // write transactions
                LinkedHashMap<String, String> tran = new LinkedHashMap<>();
                LocalDateTime now = LocalDateTime.now();
                tran.put("tid", UUID.randomUUID().toString());
                tran.put("From_aid", customer.getID().toString());
                tran.put("To_aid", null);
                tran.put("amount", String.valueOf(stockCount * stockSelected.getStockPrice()));
                tran.put("fee", "0");
                tran.put("date", now.toString());
                tran.put("From_accNum", null);
                tran.put("To_accNum", null);
                tran.put("StockAmount", String.valueOf(stockCount));
                tran.put("StockID", stockSelected.getStockUUID().toString());
                tran.put("type", "buyStock");
                db.addData("Transactions",tran);




                Stock newStock = new Stock(stockSelected.getStockName(),stockCount,stockSelected.getStockPrice(),stockSelected.getStockPrice(),0,0, stockSelected.getStockUUID());


                customer.getCustomerStocks().getStocks().put(stockSelected.getStockUUID(),newStock);


                StockTransactionUI stui = new StockTransactionUI();
                stui.selectAccForTransaction(customer, stockSelected, stockCount);

//accounts.withdraw(a,c,amount);
//                    transactions.newTransaction(new WithdrawTxn(this,a,c,amount));

//                } else{
//                    // prompt invalid stock count
//                    UIToolBox.displayDialogBox("Alert!","Sorry, you have entered the invalid count! ",frame);
//                    frame.dispose();
//                }
        }});
    }
}
