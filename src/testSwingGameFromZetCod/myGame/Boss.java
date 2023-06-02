package testSwingGameFromZetCod.myGame;

import java.awt.*;
import java.util.Random;

public class Boss extends Sprite{

    Random thrnd = new Random();
    public Boss (int x, int y) {

        super(x, y);
        initBoss();
    }

    private void initBoss() {

        String s = "src/images/bomb.png";
        loadImage(s);
        getImageDimension();
    }

    public void move() {

        //
        if (x < 0) {

            x = INITIAL_X;
            y = thrnd.nextInt(INITIAL_Y-this.height);
        }

        if (x == INITIAL_X / 2) {

            EnemyMissile a = new EnemyMissile(x + width, y + height / 2);
            a.moveLeft();
        }

        x -= 1;
    }

    public static void drawBoss(Graphics g, Boss boss) {

        if (boss.isVisible()) {

            g.drawImage(boss.getImage(), boss.getX(), boss.getY(), null);
        }
    }

    public static void updateBoss(Boss boss) {

        if (boss.isVisible()) {

            boss.move();
        }
    }

}
