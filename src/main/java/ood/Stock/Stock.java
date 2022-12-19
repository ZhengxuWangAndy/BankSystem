/*Stock.java - This class is used to represent the stocks
 */
package ood.Stock;

import java.util.UUID;

public class Stock {
    private String stockName;
    private int stockCount;
    private UUID stockUUID = null;

    private float purchasePrice;
    private float stockStatus;
    private float stockPrice;

    private float stockPriceHigh;
    private float stockPriceLow;

    public Stock() {}

    public Stock(String stockName, int stockCount, float stockPrice, float stockPriceHigh, float stockPriceLow,UUID stockUUID) {
        this.stockName = stockName;
        this.stockCount = stockCount;
        this.stockPrice = stockPrice;
        this.stockPriceHigh = stockPriceHigh;
        this.stockPriceLow = stockPriceLow;
        this.stockUUID = stockUUID;
    }

    public Stock(String stockName, int stockCount, float stockPrice, float stockPriceHigh, float stockPriceLow) {
        this.stockName = stockName;
        this.stockCount = stockCount;
        this.stockPrice = stockPrice;
        this.stockPriceHigh = stockPriceHigh;
        this.stockPriceLow = stockPriceLow;
        setStockUUID(UUID.randomUUID());
    }

    public Stock(String stockName, int stockCount, float purchasePrice, float stockPrice, float stockPriceHigh, float stockPriceLow, UUID stockUUID) {
        System.out.println("inside constructor");
        this.stockName = stockName;
        this.stockCount = stockCount;
        this.purchasePrice = purchasePrice;
        this.stockPrice = stockPrice;
        this.stockPriceHigh = stockPriceHigh;
        this.stockPriceLow = stockPriceLow;
        this.stockUUID =stockUUID;
        setStockStatus();
    }


    public Stock(String stockName, int stockCount, float purchasePrice, float stockPrice, float stockPriceHigh, float stockPriceLow) {
        System.out.println("inside constructor");
        this.stockName = stockName;
        this.stockCount = stockCount;
        this.purchasePrice = purchasePrice;
        this.stockPrice = stockPrice;
        this.stockPriceHigh = stockPriceHigh;
        this.stockPriceLow = stockPriceLow;
        setStockUUID(UUID.randomUUID());
        setStockStatus();
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public float getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus() {
        float difference = getPurchasePrice() - getStockPrice();
        this.stockStatus = getStockCount()*difference;
    }


    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public float getStockPriceHigh() {
        return stockPriceHigh;
    }

    public void setStockPriceHigh(float stockPriceHigh) {
        this.stockPriceHigh = stockPriceHigh;
    }

    public float getStockPriceLow() {
        return stockPriceLow;
    }

    public void setStockPriceLow(float stockPriceLow) {
        this.stockPriceLow = stockPriceLow;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockName='" + stockName + '\'' +
                ", stockCount=" + stockCount +
                ", stockUUID=" + stockUUID +
                ", stockPrice=" + stockPrice +
                '}';
    }

    public String toStringMyStocks() {
        return "Stock{" +
                "stockName='" + stockName + '\'' +
                ", stockCount=" + stockCount +
                ", stockUUID=" + stockUUID +
                ", stockPurchasePrice=" + purchasePrice +
                ", stockCurrentPrice=" + stockPrice +
                '}';
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public UUID getStockUUID() {
        return stockUUID;
    }

    public void setStockUUID(UUID stockUUID) {
        if(this.stockUUID==null)
        {this.stockUUID = stockUUID;}
        else{
            System.out.println("Cannot change the stock UUID as it already exists!");
        }

    }

}
