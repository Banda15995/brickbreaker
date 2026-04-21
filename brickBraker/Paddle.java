
package brickBraker;

import java.awt.Graphics;

/**
 * Minimal Paddle for Ball/BallTest.
 * Ball expects these PUBLIC fields: x, y, width, height
 */
public class Paddle {
    public int x, y, width, height;

    public Paddle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }
}
