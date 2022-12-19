/*Currency.java: represents a currency. It stores the String
name of the currency and its double exchange rate (the units
in dollars equivalent to 1 unit of this currency) as protected variables. It has an abstract toString() method that is yet to implement in its child classes*/
package ood.Money;

import java.util.UUID;

public abstract class Currency {
    protected String cname;
    protected double exchangeRate; // 1 this currency = ? dollars

    public Currency() {
        this.cname = "";
        this.exchangeRate = 1;
    }

    public Currency(String cname, double exchangeRate) {
        this.cname = cname;
        this.exchangeRate = exchangeRate;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public boolean equals(Currency other){
        return(other.getCname()==cname);
    }

    public abstract String toString();
}
