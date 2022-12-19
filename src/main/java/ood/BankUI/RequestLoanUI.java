/*RequestLoanUI.java: This class is the UI for the customer to request a loan.
The customer must have collateral (which, in our design, means they have at least 5
times the amount of loan in their deposit) to open a loan account.
 */
package ood.BankUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ood.Person.Customer;

public class RequestLoanUI {
  private Customer customer;
  private JFrame previousFrame;

  public RequestLoanUI(Customer c, JFrame frame){
    this.customer = c;
    this.previousFrame = frame;
  }

  public void requestLoan(){
    final JFrame frame = new JFrame("Request Loan");
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setVisible(true);

    JLabel askamount = new JLabel("How much would you like to loan?");
    askamount.setBounds(40, 80, 400, 20);
    frame.add(askamount);

    final JTextField amount = new JTextField("100");
    amount.setBounds(400, 80, 100, 20);
    frame.add(amount);

    JButton loan = new JButton("Loan");
    loan.setBounds(250, 250, 80, 40);
    frame.add(loan);

    loan.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        double loanAmount = Double.parseDouble(amount.getText());
        try{
          customer.newLoan(loanAmount);
          UIToolBox.displayMsg("Successfully Loaned "+loanAmount);
        } catch (UnsupportedOperationException exception){
          exception.printStackTrace();
          UIToolBox.displayMsg("Failed to Loan "+loanAmount);
        }
        frame.dispose();
        previousFrame.setVisible(true);
      }
    });
  }
}
