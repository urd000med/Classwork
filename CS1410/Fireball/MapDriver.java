/*
@ Author (EDITED BY) Ethan Smith
@ This code is actually pretty gross, I kind of dont want my name on it
@ DATE 3/28/19
@ Project : Fireball
@ Favorite Color : Red

*/
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial") //prevents the serial warning

//JFrame is the class we need to handle all of the drawing
public class MapDriver extends JFrame implements ActionListener{
	
	private int x = 0;
	private int y = 0;
	//Bullet1 b1 = new Bullet1(0,0,20,20);
	BufferedImage fireball;
	BufferedImage crystal;
	MovingObject m1;
	Timer t = new Timer(5,this);

//This is  special method that continually paints our window
//It exists by default, but by creating it here we override the default
	@SuppressWarnings("unused")
	//@Override
	public void paint(Graphics g){
		//setDoubleBuffered(true);
		//the super key word calls the parent 
		//so first it paints what the parent needs and then adds our elements
		
		// why TF we do this? it breaks the animation
		//super.paint(g);
		try{
			//I found a crystal file for us to use
			//Buffered image loads a picture in memory for easy access and passing instead of accessing from hard drive
			
			// this is much better right here
			g.setColor(new Color(0,0,0));
			g.fillRect(0, 0, 600, 600);
			
			BufferedImage crystal = ImageIO.read(new File("Crystal.png"));
			fireball = ImageIO.read(new File("FireBall.png"));


			//1. now go create an object class for use in drawing objects that don't move
			StationaryObject o1 = new StationaryObject(40,40, crystal, 50, 75);
			o1.drawImage(g);
			
			
			//We can now use this to draw any stationary object in our field
			//Including tiles
			
			//2. Create a black dot that races across the screen to demonstrate the
			//paint loop
				//g.setColor(Color.WHITE);
			//MovingObject m1 = new MovingObject(40,40,fireball,10,10,5,5);
			m1.drawImage(g);
				//g.fillOval(x+=20,y+=20,10,10);
				
			//3. Create a bullet1 class to replace fill oval
			
			//4. implement that class
			//Bullet1 b1 = new Bullet1(0,0,20,20);
			//Still doesn't move. Anyone know why? Because it's creating a new bullet everytime it loops
			//b1.drawImage(g);
			
			//For homework create a moving object class that inherits from StationaryObject
			//(See the provide provided framework file)
			//The end product should be a fireball that goes across the screen using the 
			//fireball png provided. 
			
				
			Thread.sleep(150);//got to slow things down to see
			t.start();
			//repaint();//refresh the screen so things aren't drawn on top of each other
			
		}catch (IOException e) {
			e.printStackTrace();
		}//additional catch because we are using the sleep
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	//@Override
	//public void update (Graphics g) {
	//	paint(g);
	//}
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	public void setfireball() {
		try {
		fireball = ImageIO.read(new File("FireBall.png"));
		BufferedImage crystal = ImageIO.read(new File("Crystal.png"));
		m1 = new MovingObject(40,40,fireball,25,25,5,5);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main (String [] args){
		//make instance of itself, making a new JFrame which makes a new window
		MapDriver m = new MapDriver();
		//set size of window
		m.setfireball();
		//m.doubleBuffered(true);
		m.setSize(600, 600);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
		//
	}
	
}

