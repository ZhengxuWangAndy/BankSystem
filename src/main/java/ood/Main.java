// this is the main class for execution
package ood;

import java.util.*;

import ood.Account.*;
import ood.BankUI.*;
import ood.Money.*;
import ood.Person.Customer;
import ood.Person.Manager;
import ood.utils.DBReader;
public class Main {
    public static void main(String[] args) {
        WelcomeToTheBank welcome = new WelcomeToTheBank();
        welcome.welcomeFrame();
    }
}
