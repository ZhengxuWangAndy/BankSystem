/*SignupUI.java: A class for signing up*/
package ood.BankUI;

import ood.Person.Customer;
import ood.utils.DBReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedHashMap;
import java.util.UUID;

public class SignupUI {

    public void signup() {
        // Creating instance of JFrame
        final JFrame signup = new JFrame("Signup Page");
        // Setting the width and height of frame
        signup.setSize(350, 200);
//        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. DIV HTML
         */
        final JPanel panel = new JPanel();
        Color c1 = new Color(153, 238, 247);
        panel.setBackground(c1);
        // adding panel to frame
        signup.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        placeComponents(panel);
        panel.getComponent(4).addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //get input
                System.out.println("get clicked");
                String username = ((JTextField) panel.getComponent(1)).getText();
                String password = String.valueOf(((JPasswordField) panel.getComponent(3)).getPassword());
                System.out.println("get info");
                // save to db
                DBReader db = new DBReader();
                LinkedHashMap<String,String> info = new LinkedHashMap<>();
                info.put("userID",UUID.randomUUID().toString());
                info.put("username",username);
                info.put("password",password);
                Boolean check = db.addData("CustomerInfo",info);
                if (check){
                    JOptionPane.showMessageDialog(null, "Signup Successes!\nWelcome " + username);
                    signup.setVisible(false);
                    // create a customer instance
                    Customer c = new Customer(UUID.fromString(info.get("UserID")), username, password);
                    CustomerUI cui = new CustomerUI(c);
                    cui.customerUI();
                }else {
                    JOptionPane.showMessageDialog(null, "Signup failed, try to change username.");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        signup.setVisible(true);

    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        // Creating JLabel
        JLabel userLabel = new JLabel("UserName");

        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // Same process for password label and text field.
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);


        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // Creating login button
        JButton loginButton = new JButton("Create");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
    }

}
