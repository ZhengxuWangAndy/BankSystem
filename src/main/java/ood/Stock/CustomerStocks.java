/*CustomerStock.java - This class is used to represent the stocks
 a customer has.*/
package ood.Stock;

import java.util.LinkedHashMap;
import java.util.UUID;


public class CustomerStocks {

    private LinkedHashMap<UUID, Stock> stocks;
    private int totalPrice;

    public CustomerStocks() {
        this.stocks = new LinkedHashMap<>();
        this.totalPrice = 0;
    }

    public CustomerStocks(int totalPrice) {
        this.stocks = new LinkedHashMap<>();
        this.totalPrice = totalPrice;
    }

    public LinkedHashMap<UUID, Stock> getStocks(){ return this.stocks; }

    public void setStocks(LinkedHashMap<UUID, Stock> stocks) {
        this.stocks = stocks;
    }

    public void addStock(Stock s) {
        System.out.println("in customer stocks");
        this.stocks.put(s.getStockUUID(), s);
        System.out.println(s);

//        if (!this.stocks.containsKey(s.getStockUUID()))
//            this.stocks.put(s.getStockUUID(), s);
    }

    public void deleteStock(Stock s) {
        if (!this.stocks.containsKey(s.getStockUUID()))
            System.out.println("Stock does not exist!");
        else
            this.stocks.remove(s.getStockUUID());

        if (s.getStockCount() < 0)
            System.out.println("The amount of stock cannot be less than zero");
        else if (s.getStockCount() == 0)
            this.stocks.remove(s.getStockUUID());
    }
}
