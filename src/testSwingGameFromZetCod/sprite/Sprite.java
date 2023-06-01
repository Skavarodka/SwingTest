package testSwingGameFromZetCod.sprite;

import javax.swing.*;
import java.awt.*;

//Класс родитель для будущих спрайтов
public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    //конструктор устанавливающий расположение спрайта
    // и делает его видимым
    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    protected void getImageDimension() {
        //Определяет ширину и высоту спрайта
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    //Гетеры и сеттеры
    public Image getImage() {

        return image;
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public boolean isVisible() {

        return visible;
    }

    public void setVisible(Boolean visible) {

        this.visible = visible;
    }
}
