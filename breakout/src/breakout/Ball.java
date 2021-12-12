package breakout;

import java.awt.Rectangle;

import utilities.GDV5;

public class Ball extends Rectangle{
	
	//Object variables
	int dX = 3;
	int dY = 3;
	boolean boosted = false;
	int boost = 1;
	boolean player;
	int charge = 0;
	
	public Ball(int x, int y, int w, int h, boolean p) { //Constructor
		super(x, y, w, h);
		player = p;
	}
	
	public void move(Breakout breakout) { //Moves the ball
		if (!player) charge = 0;
		
		//Updates x and y position
		x += dX;
		y += dY;
		
		//Checks for wall collision
		if ((this.getX() >= GDV5.getMaxWindowX() - this.width) || (this.getX() <= 0)) dX *= -1;
		if ((this.getY() <= 0) || (this.getY() >= GDV5.getMaxWindowY() - this.height)) dY *= -1;
		
		//Checks for death
		if (player) {
			if (this.getY() >= GDV5.getMaxWindowY() - 40 - this.height) breakout.restart();
		}
	}
}
