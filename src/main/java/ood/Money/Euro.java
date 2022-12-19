/*Euro.java is a child class of Currency. Their toString() methods are implemented so that they return the sign of their currency (e.g. “$”).*/
package ood.Money;

public class Euro extends Currency {

    public Euro() { super("Euro",1.05); }

    public Euro(String cname, double exchangeRate) {
        super(cname, exchangeRate);
    }

    public String toString(){ return "€"; }
}
