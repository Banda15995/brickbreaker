package brickbreakergame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * 
 * 
 */

/**
 * 
 */
public class Bricks {

	    // 2D array storing all bricks in rows and columns
	    Brick[][] bricks;

	    // Number of rows and columns in the brick layout
	    int rows;
	    int cols;

	    // Constructor initializes the grid and immediately creates all bricks
	    public Bricks(int r, int c) {
	        rows = r;
	        cols = c;
	        bricks = new Brick[rows][cols];
	        createBricks();
	    }

	    // Creates and positions all bricks in a structured grid
	    private void createBricks() {
	        int brickWidth = 60;     // Width of each brick
	        int brickHeight = 20;    // Height of each brick
	        int startX = 50;         // Starting X position for the first brick
	        int startY = 50;         // Starting Y position for the first brick
	        int gap = 5;             // Space between bricks

	        // Colors cycle by row 
	        Color brickColor = Color.black;
	        
	        // Loop through each row and column to place bricks
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	               
	            	// Calculate brick position based on grid spacing
	                int x = startX + c * (brickWidth + gap);
	                int y = startY + r * (brickHeight + gap);

	                // Create and store the brick
	                bricks[r][c] = new Brick(x, y, brickWidth, brickHeight, brickColor);
	            }
	        }
	    }

	    // Draws all visible bricks on the screen
	    public void draw(Graphics g) {
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                bricks[r][c].draw(g);
	            }
	        }
	    }

	    // Checks if the ball collides with any brick
	    public boolean checkBallCollision(Rectangle ballBounds) {
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (bricks[r][c].checkCollision(ballBounds)) {
	                    return true; // Stop after first collision
	                }
	            }
	        }
	        return false;
	    }

	    // Counts how many bricks are visible 
	    public int countVisibleBricks() {
	        int count = 0;
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                if (bricks[r][c].visible) { 
	                    count++;
	                }
	            }
	        }
	        return count;
	    }

	    // Returns true if all bricks have been destroyed
	    public boolean allBricksDestroyed() {
	        return countVisibleBricks() == 0;
	    }

	    // Resets all bricks back 
	    public void resetAllBricks() {
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                bricks[r][c].resetBrick();
	            }
	        }
	     }  
}