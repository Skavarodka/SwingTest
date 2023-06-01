package testSwingGameFromZetCod.myGame;

public class EnemyMissile extends Missile {
    //

    public EnemyMissile() {
        //
    }
    public EnemyMissile(int x, int y) {

        super(x, y);

        initMissile();
    }

    private void initMissile() {

        loadImage("src/testSwingGameFromZetCod/skelet/snake/resources/head.png");
        getImageDimension();
    }

    @Override
    public void moveLeft() {

        x -= 5;

        if (x < 0) {

            visible = false;
        }
    }
}
