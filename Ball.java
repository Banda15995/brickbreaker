
public class Ball {

    // Position of the ball
    private int x;
    private int y;

    // How fast the ball moves
    private int dx;
    private int dy;

    // Size of the ball
    private final int DIAMETER = 20;

    // Constructor
    public Ball(int startX, int startY) {
        x = startX;
        y = startY;
        
    // dx moves the ball left/right
    // dy moves the ball up/down
        dx = 1;   // move right
        dy = -2;  // move up
    }

    // Move the ball and check walls
    public void move(int panelWidth, int panelHeight) {
        // Move the ball
        x = x + dx;
        y = y + dy;

        // Left wall
        if (x <= 0) {
            dx = -dx;
        }

        // Right wall
        if (x + DIAMETER >= panelWidth) {
            dx = -dx;
        }

        // Top wall
        if (y <= 0) {
            dy = -dy;
        }
    }


    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getDiameter() { return DIAMETER; }
    
    // // Draw the ball
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }
}
