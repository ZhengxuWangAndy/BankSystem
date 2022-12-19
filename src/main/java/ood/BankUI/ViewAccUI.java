/*ViewAccUI.java: This class is the UI for the customer to view account. The customer
can choose which account they wish to view, and the balance will be displayed.
 */
package ood.BankUI;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.UUID;
import ood.Account.*;
import ood.Money.Currency;
import ood.Person.Customer;

public class ViewAccUI {
  Customer customer;
  private LinkedHashMap<UUID,Account> accounts;
  private JFrame previousFrame;

  public ViewAccUI(Customer customer,JFrame frame){
    this.customer = customer;
    this.accounts = customer.getCustomerAccounts().getAccounts();
    previousFrame = frame;
  }

  public void viewAcc() {
    JFrame frame = new JFrame("Hi " + customer.getUsername() + ", here are your accounts:");

    final LinkedHashMap<String,Account> accountNames = new LinkedHashMap<>();
    for(Account account : accounts.values()){
      accountNames.put(account.toString(),account);
    }
    String[] accountOptions = accountNames.keySet().toArray(new String[0]);

    final JComboBox<String> jComboBox = new JComboBox<>(accountOptions);
    jComboBox.setBounds(80, 50, 140, 20);
    frame.add(jComboBox);

    JButton jButton = new JButton("View");
    jButton.setBounds(100, 100, 90, 20);
    frame.add(jButton);

    JButton backButton = new JButton("Back");
    backButton.setBounds(100, 150, 80, 25);
    frame.add(backButton);

    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();

      }
    });

    frame.setLayout(null);
    frame.setSize(350, 250);
    frame.setVisible(true);

    jButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Account account = accountNames.get(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
        displayAccount(account);
      }
    });

  }

  public void displayAccount(Account account){

    //JFrame frame = new JFrame("Here is your account "+account.getAid()+":");
    JFrame frame = new JFrame(account.toString());

    JLabel balance = new JLabel("Total Balance: "+account.getBalance());

    GridLayout gl;
    if(account instanceof DepositAccount) { //balance and money by currency
      LinkedHashMap<Currency, Double> moneyByCurrency = ((DepositAccount) account).getMoneys();
      gl = new GridLayout(moneyByCurrency.size()+1, 0);

      frame.add(balance);

      for(Currency currency: moneyByCurrency.keySet()){
        JLabel label = new JLabel(currency.getCname()+": "+moneyByCurrency.get(currency));
        frame.add(label);
      }

      JLabel interest;
      if(account instanceof CheckingAccount)
        interest = new JLabel("Interest Rate: "+CheckingAccount.interestRate);
      else
        interest = new JLabel("Interest Rate: "+SavingsAccount.interestRate);
      frame.add(interest);

      frame.setLayout(gl);
    }

    else if(account instanceof LoanAccount){
      gl = new GridLayout(2, 0);
      frame.setLayout(gl);
      frame.add(balance);
      JLabel interest = new JLabel("Interest Rate: "+LoanAccount.interestRate);
      frame.add(interest);
    }


    else  //just the balance for other nondeposit accounts
      frame.add(balance);

    frame.setSize(400,300);
    frame.setVisible(true);
  }

}