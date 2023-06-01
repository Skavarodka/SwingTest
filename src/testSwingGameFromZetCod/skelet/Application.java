package testSwingGameFromZetCod.skelet;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    public Application() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        setSize(300,300);

        setTitle("GAME?MAYBE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}
