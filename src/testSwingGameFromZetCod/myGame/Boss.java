package testSwingGameFromZetCod.myGame;

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

//            Missile aa = new Missile(x + width, y + height / 2);
//            aa.moveLeft();

            a.moveLeft();
        }

        x -= 1;
    }

}
