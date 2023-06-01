package testSwingGameFromZetCod.myGame;



public class Enemy extends Sprite {



    public Enemy (int x, int y) {

        super(x, y);
        initEnemy();
    }

    private void initEnemy() {

        loadImage("src/testSwingGameFromZetCod/skelet/snake/resources/camalet.png");
        getImageDimension();
    }

    public void move() {

        //
        if (x < 0) {

            x = INITIAL_X;
        }
        x -= 1;
    }
}
