package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utilities.GDV5;

public class Particle extends Rectangle {
	
	int count = 20;
	
	public Particle(int x, int y, int w, int h) { //Constructor
		super(x, y, w, h);
	}
	
	public void draw(Graphics2D win) {
		win.setColor(Color.white);
		win.fill(this);
	}
	
	public int randomMove() {
		x += (int)(Math.random() * 7) - 3;
		y += (int)(Math.random() * 7) - 3;
		
		if (count > 0) {
			count--;
			return 0;
		} else {
			return 1;
		}
	}

}
