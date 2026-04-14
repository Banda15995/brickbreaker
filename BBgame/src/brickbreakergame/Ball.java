
package brickBraker;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball {

    int x;
    int y;
    int size = 14;

    int dx = 4;
    int dy = -4;

    boolean launched = false;

    // PNG image stored here
    private BufferedImage ballImg;

    // Constructor
    public Ball(int startX, int startY) {
        x = startX;
        y = startY;

        // READ image once
        try {
            ballImg = ImageIO.read(getClass().getResource("ball.png"));
        } catch (IOException | IllegalArgumentException e) {
            ballImg = null;
            System.out.println("Could not load ball.png. Put it in src/brickBraker/");
        }
    }

    public boolean move(Paddle paddle, int boardWidth, int boardHeight) {

        // optional: keep on paddle until launch
        if (!launched) {
            sitOnPaddle(paddle);
            return false;
        }

        x += dx;
        y += dy;

        // left wall
        if (x <= 0) {
            x = 0;
            dx = -dx;
        }

        // right wall
        if (x + size >= boardWidth) {
            x = boardWidth - size;
            dx = -dx;
        }

        // top wall
        if (y <= 0) {
            y = 0;
            dy = -dy;
        }

        // paddle collision
        if (y + size >= paddle.y
                && y + size <= paddle.y + paddle.height
                && x + size >= paddle.x
                && x <= paddle.x + paddle.width) {

            y = paddle.y - size;
            dy = -Math.abs(dy);

            int hitPos = (x + size / 2) - (paddle.x + paddle.width / 2);
            dx = hitPos / 8;
            if (dx == 0) dx = 1;
        }

        // fell below screen
        return (y > boardHeight);
    }

    public void sitOnPaddle(Paddle paddle) {
        x = paddle.x + paddle.width / 2 - size / 2;
        y = paddle.y - size - 2;
    }

    public void reset() {
        dx = 4;
        dy = -4;
        launched = false;
    }

    public void launch() {
        launched = true;
    }

    // DRAW (use PNG)
    public void draw(Graphics g) {
        if (ballImg != null) {
            g.drawImage(ballImg, x, y, size, size, null);
        } else {
            g.fillOval(x, y, size, size);
        }
    }
}
