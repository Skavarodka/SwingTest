package tetSwing;

import javax.swing.*;
import java.awt.*;
// класс рисования двух типов фигур с текстом
 class Figure extends JComponent {
    private static final long serialVersionUID = 1L;
    private Color color;
    private int type;
    private String text;

    //// параметры: цвет и тип фигуры
    Figure(Color color, int type, String text) {
        this.color = color;
        this.type = type;
        this.text = text;
        setOpaque(false);
    }

    public void paintComponent(Graphics graphics) {
        //прорисовка фигуры
        graphics.setColor(color);
        switch (type) {
            case 0: graphics.fillOval(0, 0, 90, 90); break;
            case 1: graphics.fillRect(0, 0, 130, 80); break;
        }
        graphics.setColor(Color.yellow);
        graphics.drawString(text, 10, 35);
    }
}
public class JLayeredPaneTest extends JFrame {
    private static final long serialVersionUID = 1L;

    public JLayeredPaneTest() {
        //        // создание окна
        super("Example LayeredTest");
        //
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //
        JLayeredPane lp = getLayeredPane();
        //
        Figure figure1 = new Figure(Color.red, 0, "Fig popUp");
        Figure figure2 = new Figure(Color.blue, 0, "F1");
        Figure figure3 = new Figure(Color.cyan, 1 , "F2");
        //определение местоположения фигур в окне
        figure1.setBounds(10, 40, 120, 120);
        figure2.setBounds(60, 120, 160, 180);
        figure3.setBounds(90, 55, 250, 180);
        // добавление фигур в различные слои
        lp.add(figure1, JLayeredPane.POPUP_LAYER  );
        lp.add(figure2, JLayeredPane.PALETTE_LAYER);
        lp.add(figure3, JLayeredPane.PALETTE_LAYER);
        // смена позиции одной из фигур
        lp.setPosition(figure3,0);
        //добавление кнопки
        JButton newButton = new JButton();
        getContentPane().add(newButton);
        // определение размера и открытие окна
        setSize(300,300);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new JLayeredPaneTest();
    }
}