package game;
/**
* Project: BB game
* author ALTAF KHAN
* version 2.0
* Created: 3/28/2026
* Description: 
*/
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//* start
		JFrame obj = new JFrame ("BB game");
		
		//* creates the game panel
		GamePanel panel = new GamePanel();
		obj.add(panel);
		
		//* size of the window
		obj.setBounds(10, 10, 800, 800);
		
		//* title of window
		obj.setTitle("BB game");
		
		//* stops the user from Resizing the window 
		obj.setResizable(false);
		
		//* allows the user to see the window
		obj.setVisible(true);
		
		//*closes the whole game after exiting the Window
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//* starts the game loop
		panel.launchBall();
		 
		//* end 
	}
}
