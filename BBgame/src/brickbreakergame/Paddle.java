//Ethan Martinez BrickBreaker Paddle(s) Portion
//sorry for all the comments I overly comment and can delete if needed!
//push at 8:00pm!!!!
package brickbreakergame;
//import java.util.Scanner;
//removed scanner *4/14/26
import javax.swing.Keystroke; 
import java.awt.event.KeyEvent;
//allows for VK(s) virtual keys
import java.awt.event.KeyListener;
//the guidlines for what happens with the keyboard (we can use it for bricks if we wanted/will talk in class) *powerups??!?*
//I think keystroke is already imported could be wrong though
//(w800, h600) <-Jframes size limits


//Adding implements KeyListener will tell our main this is the section that ultilizes those inputs later in JFrame
public class Paddle implements KeyListener {
//This is one of two paddles and Paddle class may change to Paddle1 *4/13/26
//Theres 2 ways to do this either if statements or utilizing the keystroke command which is tied to the Jframe
// Scannner Keys1 = new Scanner(System.in); (was going to use this but requires ENTER after every input so no go)

  private int x = **xx;
  private int y = 700;
//This is the Paddles1 starting point in JFrame (should be middle and almost at bottom)

  public void keyPressed(KeyEvent Input1)
  {
    int button = Input1.getKeyCode();
 //VK stands for virtual key! Its much easier to call then getting the hexcode number and when I add a second paddle I can use VK to call the arrow keys as oppsed to finding their respective hexnumber 
 //Was going to use arrow keys at first but for now just want to test this all with A and D to make it easier (*can change ASAP if needed*)


  //add hold down button options
  if (button == KeyEvent.VK_A) { x = x -1; }
 //moves paddle1 to the left (change -1 if we want to increase the space it moves)
  if (button == KeyEvent.VK_D) { x = x +1; }
 //moves paddle1 to the right (change +1 if we want to increase the space it moves)
  }
    
  public void keyReleased(KeyEvent Input1) {}
  public void keyTyped(KeyEvent Input1) {}
 //Thses two are required becasue of the Keylistner tool (its dumb but it wont run without them)
  
//[TODO] 
//Create a second paddle on the inverse so like -
//Ask if we want paddle to be able to turn (tilt on an axis)
//If Brick or Bricks wants to add effects that could invert paddles control code (ie: make it use VK_W or VK_S when (blankbrick) hit)
//Move ball collison to paddle folder  
//Need setters and getters 
//If keys calls goes to me then goes to him then back to me?

  
}
