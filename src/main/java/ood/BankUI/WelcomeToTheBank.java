/*WelcomeToTheBank.java -  This class is the UI for the Bank ATM
 */
package ood.BankUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class WelcomeToTheBank {

    public void welcomeFrame(){
        JFrame f = new JFrame("Welcome to the Bank");
        Color c1 = new Color(184, 182, 250);
        f.getContentPane().setBackground(c1);
//        final JTextField tf=new JTextField();
//        tf.setBounds(50,50, 150,20);

        JButton bankManager = UIToolBox.addButton("Bank Manager",50,100,200,30);
        JButton customer = UIToolBox.addButton("Customer",50,150,200,30);


//        JButton customer=new JButton("Customer");
//        customer.setBounds(50,150,200,30);


        bankManager.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                LoginUI login = new LoginUI();
                login.login("ManagerInfo");

            }
        });

        customer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                CustomerWelcomeUI customer = new CustomerWelcomeUI();
                customer.displayCustomerTypeWelcomePage();

            }
        });
        f.add(bankManager);
        f.add(customer);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }

}