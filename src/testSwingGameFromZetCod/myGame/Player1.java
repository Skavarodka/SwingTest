package testSwingGameFromZetCod.myGame;



import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player1 extends Sprite {

    private int dx;
    private int dy;
    private List<Missile> missiles;

    public Player1(int x, int y) {

        super(x, y);

        initProtagonist();
    }

    private void initProtagonist() {

        missiles = new ArrayList<>();
        loadImage("src/testSwingGameFromZetCod/skelet/snake/resources/apple.png");
        getImageDimension();
    }

    public void move() {

        x += dx;
        y += dy;
        // границы экрана за которые нельзя заходить
        if (x < 1) x = 1;
        if (y < 1) y = 1;
    }

    public List<Missile> getMissiles() {

        return missiles;
    }

    public void fire() {

        missiles.add(new Missile(x + width, y + height / 2));
        //y + height / 2 для того чтобы пули летели ровно из яблока
        //y + height пульки будут лететь снизу из пустого пространства
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

//        if (key == KeyEvent.VK_SPACE) {
//
//            fire();
//        }

        if (key == KeyEvent.VK_LEFT) {

            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {

            dy = -1;
        }

        if ((key == KeyEvent.VK_DOWN)) {

            dy = 1;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {

            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {

            dy = 0;
        }
    }
}
