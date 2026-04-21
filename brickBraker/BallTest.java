
package brickBraker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BallTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ball Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            GamePanel panel = new GamePanel();
            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            panel.requestFocusInWindow();
        });
    }

    // A tiny panel that runs the timer loop
    static class GamePanel extends JPanel implements ActionListener {

        private static final int WIDTH = 800;
        private static final int HEIGHT = 600;

        private final Paddle paddle;
        private final Ball ball;

        private boolean leftPressed = false;
        private boolean rightPressed = false;

        private final Timer timer;

        GamePanel() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.BLACK);
            setFocusable(true);

            paddle = new Paddle(WIDTH / 2 - 60, HEIGHT - 80, 120, 14);
            ball = new Ball(WIDTH / 2, HEIGHT / 2);
            ball.reset();
            ball.sitOnPaddle(paddle);

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int k = e.getKeyCode();
                    if (k == KeyEvent.VK_LEFT) leftPressed = true;
                    if (k == KeyEvent.VK_RIGHT) rightPressed = true;

                    if (k == KeyEvent.VK_SPACE) ball.launch();

                    if (k == KeyEvent.VK_R) {
                        ball.reset();
                        ball.sitOnPaddle(paddle);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    int k = e.getKeyCode();
                    if (k == KeyEvent.VK_LEFT) leftPressed = false;
                    if (k == KeyEvent.VK_RIGHT) rightPressed = false;
                }
            });

            timer = new Timer(16, this); // ~60 FPS
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Paddle movement
            if (leftPressed) paddle.x -= 8;
            if (rightPressed) paddle.x += 8;

            // Clamp paddle
            if (paddle.x < 0) paddle.x = 0;
            if (paddle.x + paddle.width > WIDTH) paddle.x = WIDTH - paddle.width;

            // Ball movement
            boolean lost = ball.move(paddle, WIDTH, HEIGHT);
            if (lost) {
                ball.reset();
                ball.sitOnPaddle(paddle);
            }

            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.WHITE);
            paddle.draw(g);
            ball.draw(g);

            g.drawString("Ball: (" + ball.getX() + ", " + ball.getY() + ")", 20, 20);
            g.drawString("LEFT/RIGHT move | SPACE launch | R reset", 20, 40);
        }
    }
}
