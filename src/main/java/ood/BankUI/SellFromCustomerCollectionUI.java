/*SellFromCustomerCollectionUI.java - This UI class is to view the
 stocks the customer has
 */
package ood.BankUI;

import ood.Account.Account;
import ood.Account.SecurityAccount;
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

public class SellFromCustomerCollectionUI {

    Stock stockChosen;

    public Stock getStockChosen() {
        return stockChosen;
    }

    public void setStockChosen(Stock stockChosen) {
        this.stockChosen = stockChosen;
    }

    public void stockToSell(Customer customer) {

        final JFrame frame = new JFrame();

        LinkedHashMap<UUID,Stock> stocks = customer.getCustomerStocks().getStocks();;


        final LinkedHashMap<String, Stock> stockNames = new LinkedHashMap<>();
        for (Stock stock : stocks.values()) {
            stockNames.put(stock.getStockName(), stock);
            System.out.println("STOCKS I HAVE TO SELL -> "+stock);

        }
        String[] stockNameOptions = stockNames.keySet().toArray(new String[0]);

        int size = stockNames.size();

        if (size == 0) {
            // there are no stocks
            UIToolBox.displayDialogBox("Alert!", "Sorry, you don't have any stocks! ", frame);
            frame.dispose();

        } else {

            JLabel askamount = new JLabel("Please enter the number of stocks to sell here: (minimum is 1)");
            askamount.setBounds(40, 40, 400, 20);
            frame.add(askamount);

            final JTextField amount = new JTextField("1");
            amount.setBounds(450, 40, 100, 20);
            frame.add(amount);

            JLabel askStock = new JLabel("Which stock would you like to sell?");
            askStock.setBounds(40, 10, 400, 20);
            frame.add(askStock);

            // view the stocks
            final JComboBox<String> jComboBox = new JComboBox<>(stockNameOptions);
            jComboBox.setBounds(450, 15, 140, 20);
            frame.add(jComboBox);

            JButton viewStockInfo = new JButton("Select Stock");
            viewStockInfo.setBounds(250, 100, 120, 40);
            frame.add(viewStockInfo);

            JTextArea title = new JTextArea();
            title.setBounds(90, 150, 450, 100);
            title.setLineWrap(true);
            title.setEditable(false);
            frame.add(title);

            viewStockInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    setStockChosen(stockNames.get(jComboBox.getItemAt(jComboBox.getSelectedIndex())));
                    System.out.println("CHOSEN ONE!!! - "+ stockChosen);

                    // updating the stock price as per the stock market value
                    DBReader db = new DBReader();
                    LinkedList<LinkedHashMap<String, String>> stockMarket = db.getTable("StockMarket");
                    float gainMargin = 0;
                    float lossMargin = 0;
                    final LinkedHashMap<String, Stock> stocksInfo = new LinkedHashMap<>();
                    for(int i = 0; i < stockMarket.size(); i++){
                        if (stockMarket.get(i).get("stockID").equals(stockChosen.getStockUUID().toString())){
                            System.out.println("STOCK PRICE BEFORE TAKING FROM STOCK MARKET---"+ stockChosen.getStockPrice());
                            stockChosen.setStockPrice(Integer.parseInt(stockMarket.get(i).get("stockPrice")));
//                            stockChosen.setStockPrice(1000); // test data
                            System.out.println("STOCK PRICE after TAKING FROM STOCK MARKET---"+ stockChosen.getStockPrice());


                            float difference = stockChosen.getStockCount()*(stockChosen.getStockPrice()-stockChosen.getPurchasePrice());

                            if(difference<0){
                                 lossMargin= difference;
                            }
                            else{
                                gainMargin = difference;
                            }

                            db.changeData("CustomerStockInfo","stockMarketPrice",
                                    String.valueOf(stockChosen.getStockPrice()),"stockID",stockChosen.getStockUUID().toString());
                            customer.getCustomerStocks().getStocks().put(stockChosen.getStockUUID(),stockChosen);
                            break;
                        }
                    }

                    title.setText("Stock Name: "+ stockChosen.getStockName() + "\t Stock Count: "+stockChosen.getStockCount()
                            + "\n Stock Price: "+stockChosen.getStockPrice() + "\t Stock Purchase Price: "+stockChosen.getPurchasePrice()
                            + "\n Expected Gain Margin: "+gainMargin + "\t Expected Loss Margin: "+lossMargin);

                }
            });


            JButton open = new JButton("Sell");
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

            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int stockCount = Integer.parseInt(amount.getText());
                    System.out.println("SELL STOCK COUNT --> " + stockCount);
                    System.out.println("STOCK CHOSEN TO SELL --> " + stockChosen);

                    if (stockCount<stockChosen.getStockCount()){
                        // to sell the stocks
                        // calculate gain - if no gain, nothing to add to security account, else add
                        // update the stock count in the customer
                        System.out.println("STOCK BEFORE SELLING --> "+ customer.getCustomerStocks().getStocks().get(stockChosen.getStockUUID()));
                        stockChosen.setStockCount(stockChosen.getStockCount()-stockCount);
                        customer.getCustomerStocks().getStocks().put(stockChosen.getStockUUID(),stockChosen);
                        System.out.println("STOCK after (count track) SELLING --> "+ customer.getCustomerStocks().getStocks().get(stockChosen.getStockUUID()));

//                        for (Stock stock: customer.getCustomerStocks().getStocks().values()){
//                            if (stock.getStockUUID().toString().equals(stockChosen.getStockUUID().toString())){
//                                stock.setStockCount(stockChosen.getStockCount()-stockCount);
//                                customer.getCustomerStocks().getStocks().put(stockChosen.getStockUUID(),stock);
//                                break;
//                            }
//                        }

                        frame.dispose();
                        DBReader db = new DBReader();
                        db.changeData("CustomerStockInfo","stockCount",String.valueOf(stockChosen.getStockCount()),"stockID", stockChosen.getStockUUID().toString());

                        // write transactions
                        LinkedHashMap<String, String> tran = new LinkedHashMap<>();
                        LocalDateTime now = LocalDateTime.now();
                        tran.put("tid", UUID.randomUUID().toString());
                        tran.put("From_aid", customer.getID().toString());
                        tran.put("To_aid", null);
                        tran.put("amount", String.valueOf(stockCount * stockChosen.getStockPrice()));
                        tran.put("fee", "0");
                        tran.put("date", now.toString());
                        tran.put("From_accNum", null);
                        tran.put("To_accNum", null);
                        tran.put("StockAmount", String.valueOf(stockCount));
                        tran.put("StockID", stockChosen.getStockUUID().toString());
                        tran.put("type", "sellStock");
                        db.addData("Transactions",tran);

                        SellingStockTransaction sstui = new SellingStockTransaction();
                        sstui.selectAccForTransaction(customer, stockChosen, stockCount);

                    } else if (stockCount==stockChosen.getStockCount()) {
                        // remove from customer info
                        System.out.println("STOCK BEFORE(remove) SELLING --> "+ customer.getCustomerStocks().getStocks().get(stockChosen.getStockUUID()));

                        customer.getCustomerStocks().getStocks().remove(stockChosen.getStockUUID());
                        System.out.println("STOCK after(REMOVE) (count track) SELLING --> "+ customer.getCustomerStocks().getStocks().get(stockChosen.getStockUUID()));

                        DBReader db = new DBReader();
                        // write transactions
                        LinkedHashMap<String, String> tran = new LinkedHashMap<>();
                        LocalDateTime now = LocalDateTime.now();
                        tran.put("tid", UUID.randomUUID().toString());
                        tran.put("From_aid", customer.getID().toString());
                        tran.put("To_aid", null);
                        tran.put("amount", String.valueOf(stockCount * stockChosen.getStockPrice()));
                        tran.put("fee", "0");
                        tran.put("date", now.toString());
                        tran.put("From_accNum", null);
                        tran.put("To_accNum", null);
                        tran.put("StockAmount", String.valueOf(stockCount));
                        tran.put("StockID", stockChosen.getStockUUID().toString());
                        tran.put("type", "sellStock");
                        db.addData("Transactions",tran);

                        db.removeData("CustomerStockInfo", "stockID", stockChosen.getStockUUID().toString());
                        SellingStockTransaction sstui = new SellingStockTransaction();
                        sstui.selectAccForTransaction(customer, stockChosen, stockCount);
//                        for (Stock stock: customer.getCustomerStocks().getStocks().values()){
//                            if (stock.getStockUUID().toString().equals(stockChosen.getStockUUID().toString())){
//                                break;
//                            }
//                        }

                    }
                    else{
                        // prompt invalid stock count
                        UIToolBox.displayDialogBox("Alert!","Sorry, you have entered the invalid count! ",frame);
                        frame.dispose();
                    }
                }});


        }
        frame.setSize(600,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public LinkedHashMap<String, Stock> getStockMarket(){
        DBReader db = new DBReader();

        LinkedList<LinkedHashMap<String, String>> stockMarket = db.getTable("StockMarket");
        final LinkedHashMap<String, Stock> stocksInfo = new LinkedHashMap<>();
        for(int i = 0; i < stockMarket.size(); i++){
            System.out.println(stockMarket.get(i).get("stockID"));

            Stock stockI = new Stock(stockMarket.get(i).get("stockName"), 10,Float.parseFloat(stockMarket.get(i).get("stockPrice")),Float.parseFloat(stockMarket.get(i).get("stockHighPrice")),4, UUID.fromString(stockMarket.get(i).get("stockID")));
            stocksInfo.put(stockMarket.get(i).get("stockID"),stockI);

        }

        return stocksInfo;
    }


}
