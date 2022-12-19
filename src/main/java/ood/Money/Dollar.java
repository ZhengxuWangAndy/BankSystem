/*Dollar.java is a child class of Currency. Their toString() methods are implemented so that they return the sign of their currency (e.g. “$”).
* */
package ood.Money;

import java.util.UUID;

public class Dollar extends Currency {

    public Dollar() { super("Dollar",1); }

    public Dollar(String cname, double exchangeRate) {
        super(cname, exchangeRate);
    }

    public String toString(){ return "$"; }
}
