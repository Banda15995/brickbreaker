package game;
/**
* Project: GamePanel
* author ALTAF KHAN
* version 2.0
* Created: 4/02/2026
* Description: 
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener, Runnable {

	//* size of the game window
	static final int WIDTH = 800, HEIGHT = 600;

	//* game objects
	Ball ball;
	Paddle paddle;
	Brick[][] bricks;

	//* game thread and running state
	Thread gamethread;
	boolean running = false;

	public GamePanel() {
		//* sets the size of the panel
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		//* sets the background color to black
		setBackground(Color.black);

		//* allows the panel to receive keyboard input
		setFocusable(true);
		addKeyListener(this);

		//* creates the ball in the center of the screen
		ball = new Ball(WIDTH / 2, HEIGHT / 2, 10);

		//* creates the paddle near the bottom of the screen
		paddle = new Paddle(WIDTH / 2 - 50, HEIGHT - 40, 100, 12);

		//* creates the brick grid
		bricks = new Brick[5][10];
		initBricks();
	}

	void initBricks() {
		//* loops through each row and column to place bricks
		for (int row = 0; row < bricks.length; row++) {
			for (int col = 0; col < bricks[row].length; col++) {
				//* sets the position of each brick
				int X = col * 78 + 10;
				int Y = row * 30 + 50;
				bricks[row][col] = new Brick(X, Y, 70, 20);
			}
		}
	}

	public void launchBall() {
		//* sets the game to running and starts the game thread
		running = true;
		gamethread = new Thread(this);
		gamethread.start();
	}

	@Override
	public void run() {
		//* sets the target frames per second
		double fps = 60;

		//* calculates the time between each frame in nanoseconds
		double interval = 1_000_000_000 / fps;
		double nextTime = System.nanoTime() + interval;

		//* keeps the game loop going while running is true
		while (running) {
			update();
			repaint();

			//* sleeps the thread to keep the game at 60 fps
			double remaining = nextTime - System.nanoTime();
			if (remaining > 0) {
				try { Thread.sleep((long)(remaining / 1_000_000)); }
				catch (InterruptedException e) { e.printStackTrace(); }
			}
			nextTime += interval;
		}
	}

	void update() {
		//* moves the ball and paddle each frame
		ball.move();
		paddle.update();

		//* ball bounces off walls
		if (ball.x <= 0 || ball.x + ball.size >= WIDTH) ball.xSpeed *= -1;
		if (ball.y <= 0) ball.ySpeed *= -1;

		//* ball bounces off the paddle
		if (ball.getBounds().intersects(paddle.getBounds())) ball.ySpeed *= -1;

		//* ball hits a brick — hides the brick and bounces
		for (Brick[] row : bricks)
			for (Brick b : row)
				if (b != null && b.visible && ball.getBounds().intersects(b.getBounds())) {
					b.visible = false;
					ball.ySpeed *= -1;
				}

		//* ball falls out of bounds — game over
		if (ball.y > HEIGHT) running = false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		//* clears the screen and redraws everything each frame
		super.paintComponent(g);
		ball.draw(g);
		paddle.draw(g);

		//* draws all visible bricks
		for (Brick[] row : bricks)
			for (Brick b : row)
				if (b != null && b.visible) b.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//* moves the paddle left or right when arrow keys are pressed
		if (e.getKeyCode() == KeyEvent.VK_LEFT)  paddle.left  = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) paddle.right = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//* stops the paddle when arrow keys are released
		if (e.getKeyCode() == KeyEvent.VK_LEFT)  paddle.left  = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) paddle.right = false;
	}

	//* required by KeyListener but not used
	@Override
	public void keyTyped(KeyEvent e) {}
}
