package testSwingGameFromZetCod.skelet;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Board1 extends JPanel {
    //public Board(){}

    //далее код для отрисовки пончика
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawDonut(g);
    }

    private void drawDonut(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        RenderingHints renderingHints =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        renderingHints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        graphics2D.setRenderingHints(renderingHints);

        Dimension size = getSize();
        double wight = size.getWidth();
        double high = size.getHeight();

        Ellipse2D e = new Ellipse2D.Double(0,0,80,130);
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(Color.gray);

        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform at
                    = AffineTransform.getTranslateInstance(wight/2, high/2);
            at.rotate(Math.toRadians(deg));
            graphics2D.draw(at.createTransformedShape(e));
        }
    }
}
