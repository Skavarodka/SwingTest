package testSwingGameFromZetCod.animationstar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//использование свингТаймера для анимации
public class Board extends JPanel implements ActionListener {
    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 10;

    private Image star;
    private Timer timer;
    private int x, y;

    public Board() {
        initBoard();
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("src/testSwingGameFromZetCod/skelet/snake/resources/apple.png");
        star = ii.getImage();
    }

    private void initBoard() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        loadImage();

        x = INITIAL_X;
        y = INITIAL_Y;

        timer = new Timer(DELAY, this);
        timer.start();
    }
    // таймер.старт вызывает метод пейнткомпонент который и рисует
    //поэтому мы его переопределяем, фактически рисует дравСтар
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawStar(g);
    }

    private void drawStar(Graphics g) {
        g.drawImage(star, x, y, this);
        //Toolkit.getDefaultToolkit().sync(); //эта хрень для линукса оптимизирует синхроннность отрисовки
    }
    // здесь двигаем объект по экрану экшнПерформед вызывается таймером постоянно
    //репейнт вызывает пэйнтКомпонент
    @Override
    public void actionPerformed(ActionEvent e) {
        x += 1;
        y += 1;

        if (y > B_HEIGHT) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }
        repaint();
    }
}
