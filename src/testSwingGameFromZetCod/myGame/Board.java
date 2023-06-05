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
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 16;
    private Timer timer;
    private Player1 player1;
    private Boss boss;
    private Missile missile;
    private EnemyMissile enemyMissile;
    private List<Enemy> enemies;
    private DrawMyObj drawMyObj;
    private boolean inGame;
    private int gameState = 0;
    private int life;
    private int bossLife = 50;
    private List<Boss> bossList;

    private final int[][] pos = {
            {1900, 29}, {2100, 59}, {1380, 89},
//            {780, 109}, {580, 139}, {680, 239},
//            {790, 259}, {760, 50}, {790, 150},
//            {980, 209}, {560, 45}, {510, 70},
//            {930, 159}, {590, 80}, {530, 60},
//            {940, 59}, {990, 30}, {920, 200},
//            {900, 259}, {660, 50}, {540, 90},
//            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30}
    };

    public Board() {

        initBoard();
    }

    // Проверка состояния игры
    private void inGame() {

        if (!inGame) timer.stop();
    }

    private void initBoard() {

        addKeyListener(new MyKeyAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        inGame = true;
        life = 100;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        drawMyObj = new DrawMyObj(this);

        player1 = new Player1(PSTART_X, PSTART_Y);
        missile = player1.getMissiles();
        enemyMissile = new EnemyMissile();
        initEnemy();
        // создание таймера который и будет основой игрового цикла
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initEnemy() {

        boss = new Boss(B_WIDTH*3, B_HEIGHT/2);
        enemies = new ArrayList<>();
        bossList = new ArrayList<>();

        for (int[] a : pos) {

            bossList.add(new Boss(a[0], a[1]));
        }

        for (int[] p : pos) {

            enemies.add(new Enemy(p[0], p[1]));
        }
    }
        // Метод который все рисует
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            drawUI(g);
            Player1.drawPlayer(g, player1);
            Missile.drawMissile(g, missile);
            Boss.drawBossList(g,bossList);
            Boss.drawBoss(g, boss);
            Enemy.drawEnemy(g, enemies);
            EnemyMissile.drawEnemyMiss(g, enemyMissile);
        } else if (life == 0){
            drawMyObj.drawGameOver(g); // хрен какая-то надо переделывать со счетчиком стадий
        }
        if (enemies.size() == 0) {
            inGame = false;
            drawMyObj.drawWin(g);
        }
    }
        // Методы которые, обрисовывают объекты. Надо бы их засунуть в классы. Не вышло
    private void drawUI(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawString("Enemys left: " + enemies.size(), 5, 15);
        g.drawString("Life: " + life, 95, 15);
    }
//    здесь конец отрисовки графики

    // ИГРОВАЯ ПЕТЛЯ
    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        if (life == 0) {

            inGame = false;
            return;
        }

        Enemy.updateEnemy(enemies, inGame);
        Player1.updateProtagonist(player1);
        Missile.updateMiss(missile);
        Boss.updateBoss(boss);
        boss.updateBossList(bossList);

        for (Boss boss1 : bossList) {

            if (boss1.getX() == B_WIDTH/2) {

                enemyMissile = new EnemyMissile(boss1.getX(),boss1.getY());
            }
        }

        if (boss.getX() == B_WIDTH/2) {

            enemyMissile = new EnemyMissile(boss.getX(),boss.getY());
        }

        if (boss.getX() == 350) {

            enemyMissile = new EnemyMissile(boss.getX(),boss.getY());
        }

        if (boss.getX() == 100) {

            enemyMissile = new EnemyMissile(boss.getX(),boss.getY());
        }

        EnemyMissile.updateEnMiss(enemyMissile);
        checkCollisions();

        repaint(); // вызов экшен перформеда
    }

    // проверка на получение урона
    public void checkCollisions() {

        Rectangle playerRectangle = player1.getBounds();
        Rectangle missRectangle = missile.getBounds();
        Rectangle bossRectangle = boss.getBounds();
        Rectangle enemyMissRect = enemyMissile.getBounds();
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
            if (missile.isVisible()) {
            if (missRectangle.intersects(rEnemy)) {

                missile.setVisible(false);
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

                    if (!missile.isVisible()) {

                        missile = new Missile(player1.getX(), player1.getY());
                    }
            }
        }
    }
}
