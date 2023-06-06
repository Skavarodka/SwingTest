package testSwingGameFromZetCod.myGame;

import java.awt.*;



public class DrawMyObj {

    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;

    Board board;

    public DrawMyObj(Board board) {

        this.board = board;
    }

    public void drawStart(Graphics g) {

        String winMessage = "Press Enter to start";
        Font font = new Font("Magneto", Font.BOLD, 17);
        FontMetrics fontMetrics = board.getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(winMessage,
                (B_WIDTH - fontMetrics.stringWidth(winMessage)) / 2,
                B_HEIGHT / 2);
    }

    public void drawWin(Graphics g) {

        String winMessage = "Yo Win";
        Font font = new Font("Magneto", Font.BOLD, 17);
        FontMetrics fontMetrics = board.getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(winMessage,
                (B_WIDTH - fontMetrics.stringWidth(winMessage)) / 2,
                B_HEIGHT / 2);
    }


    public void drawGameOver(Graphics g) {

        String gameOverString = "Game Over";
        Font font = new Font("Magneto", Font.BOLD, 17);
        FontMetrics fontMetrics = board.getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(gameOverString,
                (B_WIDTH - fontMetrics.stringWidth(gameOverString)) / 2,
                B_HEIGHT / 2);
    }
}
