package testSwingGameFromZetCod.myGame;

public class Missile extends Sprite {

    private final int BOARD_WIDTH = 390;

    private final int BOARD_HEIGHT = 290;
    private final int MISSILE_SPEED = 2;

    public Missile() {

        //
    }
    public Missile(int x, int y) {

        super(x, y);

        initMissile();
    }

    private void initMissile() {

        loadImage("src/testSwingGameFromZetCod/skelet/snake/resources/head.png");
        getImageDimension();
    }

    public void moveLeft() {

        x -= MISSILE_SPEED;

        if (x < 0) {

            visible = false;
        }
    }

    public void moveRight() {

        x += MISSILE_SPEED;

        if (x > BOARD_WIDTH) {

            visible = false;
        }
    }
}
