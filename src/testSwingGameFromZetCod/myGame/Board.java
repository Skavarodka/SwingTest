package testSwingGameFromZetCod.myGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {

    private final int PSTART_X = 30;
    private final int PSTART_Y = 29;
    private Boss boss;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 15;
    private Timer timer;
    private Player1 player1;
    private Missile missile;
    private EnemyMissile enemyMissile;
    private List<Enemy> enemies;
    private boolean inGame;
    private int gameState = 0;
    private int life;
    private int bossLife = 200;

    private final int[][] pos = {
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30}
    };

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new MyKeyAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        inGame = true;
        life = 100;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        player1 = new Player1(PSTART_X, PSTART_Y);
        missile = new Missile();
        enemyMissile = new EnemyMissile();
        initEnemy();
        // создание таймера который и будет основой анимации
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initEnemy() {

        boss = new Boss(B_WIDTH, B_HEIGHT/2);
        enemies = new ArrayList<>();

        for (int[] p : pos) {

            enemies.add(new Enemy(p[0], p[1]));
        }
    }
        // Метод который все рисует
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (inGame) {
            drawObjects(g);
            drawMissile(g);
            drawBoss(g);
            drawEnemyMiss(g);
        } else if (life == 0) {
            drawGameOver(g);
        } else if (enemies.size() == 0) {
            drawWin(g);
        }
    }
        // Методы которые, обрисовывают объекты.
    public void drawEnemyMiss(Graphics g) {

        if (enemyMissile.isVisible()) {

            g.drawImage(enemyMissile.getImage(), enemyMissile.getX(),
                    enemyMissile.getY(), this);
        }
    }

    public void drawMissile(Graphics g) {

        if (missile.isVisible()) {

            g.drawImage(missile.getImage(), missile.getX(),
                    missile.getY(), this);
        }
    }

    public void drawBoss(Graphics g) {

        if (boss.isVisible()) {

            g.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);
        }
    }

    private void drawWin(Graphics g) {

        String winMessage = "Yo Win";
        Font font = new Font("Magneto", Font.BOLD, 17);
        FontMetrics fontMetrics = getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(winMessage,
                (B_WIDTH - fontMetrics.stringWidth(winMessage)) / 2,
                B_HEIGHT / 2);
    }

    private void drawObjects(Graphics g) {

        if (player1.isVisible()) {

            g.drawImage(player1.getImage(), player1.getX(),
                    player1.getY(), this);
        }

       // List<EnemyMissile> eMissiles = enemies.get()
        List<Missile> missiles = player1.getMissiles();
        for (Missile missile : missiles) {

            if (missile.isVisible()) {

                g.drawImage(missile.getImage(), missile.getX(),
                        missile.getY(), this);
            }
        }

        for (Enemy enemy : enemies) {

            if (enemy.isVisible()) {

                g.drawImage(enemy.getImage(), enemy.getX(),
                        enemy.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Enemys left: " + enemies.size(), 5, 15);
        g.drawString("Life: " + life, 95, 15);
    }

    private void drawGameOver(Graphics g) {

        String gameOverString = "Game Over";
        Font font = new Font("Magneto", Font.BOLD, 17);
        FontMetrics fontMetrics = getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(gameOverString,
                (B_WIDTH - fontMetrics.stringWidth(gameOverString)) / 2,
                B_HEIGHT / 2);
    }
//    здесь конец отрисовки графики
    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();
        updateEnemy();
        updateMissiles();
        updateProtagonist();
        updateMiss();
        updateBoss();
        updateEnMiss();
        checkCollisions();

        repaint(); // все рисуется одновременно, потому что мне лень делать по нормальному
    }

    private void inGame() {

        if (!inGame) timer.stop();
    }

    private void updateBoss() {

        if (boss.isVisible()) {

            boss.move();

            if (boss.getX() == B_WIDTH/2) {

                 enemyMissile = new EnemyMissile(boss.getX(),boss.getY());
            }
        }
    }
    private void updateMiss() {

        if (missile.isVisible()) {

            missile.moveRight();
        }
    }

    private void updateEnMiss() {

        if (enemyMissile.isVisible()) {

            enemyMissile.moveLeft();
        }
    }
    private void updateMissiles() {

        List<Missile> missiles = player1.getMissiles();

        for (int i = 0; i < missiles.size(); i++) {

            Missile missile = missiles.get(i);

            if (missile.isVisible()) {

                missile.moveRight();
            } else {

                missiles.remove(i);
            }
        }
    }

    private void updateProtagonist() {

        if (player1.isVisible()) player1.move();
    }

    private void updateEnemy() {

        if (enemies.isEmpty()) {

            inGame = false;
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {

            Enemy enemy = enemies.get(i);

            if (enemy.isVisible()) {

                enemy.move();
            } else {

                enemies.remove(i);
            }
        }
    }
    // проверка на получение урона
    public void checkCollisions() {

        Rectangle playerRectangle = player1.getBounds();
        // ПЕРСОНАЖ И ВРАГИ СТАЛКИВАЮТСЯ
        for (Enemy enemy : enemies) {

            Rectangle rEnemy = enemy.getBounds();

            if (playerRectangle.intersects(rEnemy)) {
                life--;
                if (life == 0) {

                    player1.setVisible(false);
                    enemy.setVisible(false);
                    inGame = false;
                }
            }
        }

        Rectangle missRectangle = missile.getBounds();
        Rectangle bossRectangle = boss.getBounds();
        Rectangle enemyMissRect = enemyMissile.getBounds();
        // ВРАЖЕСКИЕ ПРОЖЕКТАЙЛЫ И ИГРОК
        if (enemyMissRect.intersects(playerRectangle)) {

            life--;
            if (life == 0) {

                player1.setVisible(false);
                boss.setVisible(false);
                inGame = false;
            }
        }
        // БОСС И ПРОЖЕКТАЙЛЫ ИГРОКА
        if (missRectangle.intersects(bossRectangle)) {

            bossLife--;
            missile.setVisible(false);
            if (bossLife == 0) {

                boss.setVisible(false);
            }
        }
        // БОСС И ИГРОК
        if (playerRectangle.intersects(bossRectangle)) {

            life--;
            if (life == 0) {

                player1.setVisible(false);
                boss.setVisible(false);
                inGame = false;
            }
        }
        // ВРАГИ И ПРОЖЕКТАЙЛЫ ИГРОКА
        for (Enemy enemy : enemies) {

            Rectangle rEnemy = enemy.getBounds();

            if (missRectangle.intersects(rEnemy)) {

                missile.setVisible(false);
                enemy.setVisible(false);
            }
        }
        // ПУЛЕМЕТ И ВРАГИ \УДАЛИТЬ/
        List<Missile> missileList = player1.getMissiles();
        for (Missile m : missileList) {

            Rectangle rMiss = m.getBounds();

            for (Enemy enemy : enemies) {

                Rectangle rEnemy = enemy.getBounds();

                if (rMiss.intersects(rEnemy)) {

                    m.setVisible(false);
                    enemy.setVisible(false);
                }
            }
        }
    }
    // КЛАСС КОТОРЫЙ СУЕТСЯ В ТАЙМЕР ДЛЯ УПРАВЛЕНИЯ
    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player1.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player1.keyPressed(e);

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (inGame) {

                    if (!missile.isVisible()) {

                        missile = new Missile(player1.getX(), player1.getY());
                    }
                }
            }
        }
    }
}
