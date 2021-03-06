/*
@ Author : Ethan Smith
@ Date : 4/25/29
@ Assignment : Tower defense
@ File: FireTower
*/
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.*;
import java.awt.Color;

public class FireTower extends Tower{
	public FireTower(int posx, int posy, BufferedImage bi, int imageW, int imageH) {
		super(posx, posy, bi, imageW, imageH);
		range = hitboxRadius*2;
		speed = 2;
		power = 1;
		velocity=2;
		System.out.println(range);
		try {
			//initalize all of the tower images and save them as files to be accessed
			t[0] = ImageIO.read(new File("assets/fire1.png"));
			t[1] = ImageIO.read(new File("assets/fire2.png"));
			t[2] = ImageIO.read(new File("assets/fire3.png"));
			changeImage(t[0]);
		}
		catch (IOException e) {
			System.out.println("Unable to generate tower due to IO exception");
		}
	}
	public boolean upgrade () {
		if (level < 3) {
			level++;
			//power++;
			speed++;
			velocity++;
			range +=hitboxRadius;
			this.changeImage(t[level-1]);
			return true;
		}else {
			System.out.println("MAX LEVEL ALREADY");
			return false;
		}
	}
	public void drawImage(Graphics g) {
		g.drawImage(bi,posx, posy,imageW,imageH,null);
		//draws all the bullets associated with a tower
		for ( int z=bullets.size()-1;z>0;z--) {
			if (bullets.get(z).isOutside()) {
					bullets.remove(z);
				}
		}
		//makes the bullets have random colors, simulating a fireblast.
		for ( int z=0;z<bullets.size();z++) {
			double r = Math.random();
			Color c;
			if (r > .8) {
				c = Color.WHITE;
			}
			else if (r > .3) {
				c = Color.ORANGE;
			}
			else if (r > .1) {
				c = Color.YELLOW;
			}
			else {
				c = Color.RED;
			}

			bullets.get(z).drawImage(g,c);
		}
	}
	public void fire() {
		//bullets.add(new bullet1(midX,midY,10,10));
		// i hate trig, almost as much as I hate Calc lmao help
			//targets with a guess based on where the orc is moving
			// 20 can be changed if the tower is over or underfiring
			double predictedVy = target.getMidY() + (target.getVy()*3);
			double predictedVx = target.getMidX() + target.getVx()*3;
			if (target != null) {
				double angle = Math.atan2((double)(predictedVy-midY),(double)(predictedVx-midX));
				int zz = speed;
				while (zz > 0) {
					double vx = velocity*(Math.cos(angle))+(Math.random()* .3);
					double vy = velocity*(Math.sin(angle))+(Math.random()* .3);

					bullets.add(new Bullet1(midX,midY,vx,vy,this,range,bsize));
					zz--;
				}

		// is it fucking Trigonometry time ? I fucking hate everything
	}
}
}
