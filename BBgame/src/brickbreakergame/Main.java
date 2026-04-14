package game;
/**
* Project: BB game
* author Altaf Khan
* version 3.0
* Created:
* Description:
* Creates the window and adds the GamePanel to it.
*/
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        // Create the main game window with a title
        JFrame frame = new JFrame("Brick Breaker");

        // Create the game panel where all the action happens
        GamePanel game = new GamePanel();

        // Add the game panel to the window
        frame.add(game);

        // Resize the window to fit the game panel
        frame.pack();

        // Close the game when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prevent the user from resizing the window
        frame.setResizable(false);

        // Center the window on the screen
        frame.setLocationRelativeTo(null);

        // Make the window visible
        frame.setVisible(true);
    }
}