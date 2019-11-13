import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//THIS IS JUST TO TEST GUIS, NOT NECESSARY FOR FINAL PROJECT

public class Test {
    static int row;
    public static void main(String[] args) {

        JFrame frame = new JFrame("GridLayout Example");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();

        JButton[] array = new JButton[4];
        int[] array2 = new int[4];
        for (int i = 0; i < 4; i++) {
            array2[i] = i;
        }

        for (int i = 0; i < 4; i++) {

            row = i;

            array[i] = new JButton("test");


            array[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    System.out.printf("%d", array2[row]);
                }


            });

            panel.add(array[i]);
        }


        frame.add(panel);
        frame.pack(); // set top-level window to "right" size to fit
        frame.setVisible(true);

    }
}

