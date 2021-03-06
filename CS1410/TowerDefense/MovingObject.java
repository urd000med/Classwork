/*
@ Author : Ethan Smith
@ Date : 4/14/29
@ Assignment : Tower defense
@ File: MovingObject
*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MovingObject extends MapObject {
	
	protected double vx;
	protected double vy;
	
	public MovingObject(int posx,int posy,int radius) {
		// creates a generic object which isn't actually moving
		// used for checking if you've clicked on a tower.
		super(posx,posy,radius);
		midX= posx;
		midY=posy;
		this.hitboxRadius = radius;
	}
	
	public MovingObject(int posx, int posy, BufferedImage bi, int imageW, int imageH, double vx, double vy)
	{
		super(posx, posy, bi,  imageW, imageH);
		this.vx=vx;
		this.vy=vy;
	}
	
	public void drawImage(Graphics g)
	{
		g.drawImage(bi,posx+=vx, posy+=vy,imageW,imageH,null);
		midX+=vx;
		midY+=vy;
	}
	public double getVx() {
		return vx;
	}
	public double getVy() {
		return vy;
	}
	public boolean isOutside() {
		 //if the bullet is outside the boundaries of the map, destroy it

		if (midX < -10 || midX > 610 || midY < -10 || midX > 610) {
			return true;
		}
		return false;
	}

}
