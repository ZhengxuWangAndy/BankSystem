/*UIToolBox.java -  This class contains static functions that are reused throughout the code
 */
package ood.BankUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UIToolBox {
    public static JButton addButton(String buttonName, int positionX, int positionY){
        JButton buttonInstance=new JButton(buttonName);
        buttonInstance.setBounds(positionX,positionY,200,30);
        return buttonInstance;
    }

    public static JButton addButton(String buttonName, int positionX, int positionY, int width, int height){
        JButton buttonInstance=new JButton(buttonName);
        buttonInstance.setBounds(positionX,positionY,width,height);
        return buttonInstance;
    }

    public static void displayMsg(String msg){
        JOptionPane pane = new JOptionPane("");
        final JDialog dialog = pane.createDialog(msg);

        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    public static void displayDialogBox(String boxName,String msg, JFrame frame){
        JOptionPane pane = new JOptionPane();
        pane.showMessageDialog(frame,msg,boxName,JOptionPane.WARNING_MESSAGE);

    }
}
