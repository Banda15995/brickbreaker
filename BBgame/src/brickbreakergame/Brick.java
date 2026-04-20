package brickbreakergame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
//hi arid
public class Brick {
    // Brick position
    int x, y;

    // Brick size
    int width, height;

    // Whether the brick is still visible 
    boolean visible;

    // Tracks whether a collision occurred this frame
    boolean collision;

    // Number of hits the brick can take before disappearing
    int hitsRemaining;

    // Brick color
    Color color;

    // Constructor initializes brick properties
    Brick(int px, int py, int w, int h, Color c) {
        x = px;
        y = py;
        width = w;
        height = h;
        visible = true;        
        collision = false;     
        hitsRemaining = 3;     
        color = c;            
    }

    // Draws the brick on screen if it is visible
    void draw(Graphics g) {
        if (visible) {
            g.setColor(color);
            g.fillRect(x, y, width, height); 

            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);  
        }
    }

    // Returns the rectangular for collision detection
    Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Reduces hit count 
    void hit() {
        if (visible) {
            hitsRemaining--;        
            if (hitsRemaining <= 0) {
                visible = false;    
            }
        }
    }

    // Checks if the ball intersects the brick
    boolean checkCollision(Rectangle ballBounds) {
        if (visible && ballBounds.intersects(getBounds())) {
            collision = true;   
            hit();              
            return true;
        }
        collision = false;     
        return false;
    }

    // Restores the brick 
    void resetBrick() {
        visible = true;
        collision = false;
        hitsRemaining = 3;      
    } 
} 
