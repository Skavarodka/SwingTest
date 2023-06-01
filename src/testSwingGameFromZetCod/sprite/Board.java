package testSwingGameFromZetCod.sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Класс на котором все спрайты отрисовываются.
// Главный класс в котором и происходит все действия
public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private SpaceShip spaceShip;
    private final int DELAY = 10;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        spaceShip = new SpaceShip();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync(); // linux trick
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(),this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        step();
    }

    private void step() {

        spaceShip.move();
        //Перемещаем спрайт и перекрашиваем изменившуюся часть доски.
        // Мы используем небольшую технику оптимизации,
        // которая перерисовывает только небольшую область окна,
        // ту что фактически изменилась.
        repaint(spaceShip.getX() - 3, spaceShip.getY() - 3,
                spaceShip.getWidth() + 5, spaceShip.getHeight() + 5);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            spaceShip.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            spaceShip.keyPressed(e);
        }
    }
}
