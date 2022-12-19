/*Yuan.java is a child class of Currency. Their toString() methods are implemented so that they return the sign of their currency (e.g. “$”).*/
package ood.Money;

public class Yuan extends Currency {

    public Yuan() {
        super("Yuan",0.14);
    }

    public Yuan(String cname, double exchangeRate) {
        super(cname, exchangeRate);
    }

    public String toString(){ return "¥";}
}
