/*CustomerWelcomeUI.java - This class has the UI for the Customer
 */
package ood.BankUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerWelcomeUI {
    public void displayCustomerTypeWelcomePage(){
        JFrame customerFrame=new JFrame("Customers Page");

        JButton newCustomer = UIToolBox.addButton("New Customer",50,100,200,30);
        JButton existingCustomer = UIToolBox.addButton("Existing Customer",50,150,200,30);

        newCustomer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SignupUI customer = new SignupUI();
                customer.signup();

            }
        });

        existingCustomer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                LoginUI login = new LoginUI();
                login.login("CustomerInfo");

            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(90, 200, 80, 25);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerFrame.dispose();

            }
        });

        customerFrame.add(backButton);

        customerFrame.add(newCustomer);
        customerFrame.add(existingCustomer);
        customerFrame.setSize(400,400);
        customerFrame.setLayout(null);
        customerFrame.setVisible(true);
    }
}

