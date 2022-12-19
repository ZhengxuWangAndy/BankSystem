/*StockMarket.java - This class represents the stock market with a collection of stocks
 */
package ood.Stock;

import java.util.ArrayList;

public class StockMarket {
    private ArrayList<Stock> stocks;
    public StockMarket() {
        stocks = new ArrayList<Stock>();
    }

    public StockMarket(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public void addStockToMarket(Stock stock){
        stocks.add(stock);
    }

    public void displayStockMarket(){
        for(int i=0;i<stocks.size();i++){
            System.out.println(stocks.get(i));
        }
    }
}
