package wincondition;
import javax.swing.*;
import java.awt.*;

public class WinTest {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JframeTest();
            }
        });
    }
    public static void JframeTest(){
        JFrame jFrame = new JFrame("TestFrame");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Test  Label");
        jFrame.getContentPane().add(label);

        jFrame.setPreferredSize(new Dimension(200, 100));

        jFrame.pack();
        jFrame.setVisible(true);
    }
}
