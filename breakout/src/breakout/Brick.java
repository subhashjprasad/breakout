package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utilities.GDV5;

public class Brick extends Rectangle {
	
	boolean broken = false;
	Particle[] particles;
	int row;
	String powerup = "";
	
	public Brick(int x, int y, int w, int h, int r) { //Constructor
		super(x, y, w, h);
		row = r;
		int p = (int)(Math.random() * 7);
		if (p == 1) {
			powerup = "speed";
		} else if (p == 2) {
			powerup = "ball";
		} else if (p == 3) {
			powerup = "charge";
		} else {
			powerup = "none";
		}
	}
	
	public void brickCheck(Ball b, Breakout br) { //Checks for collision with ball (and moves particles)
		if ((b.intersects(this)) && (broken == false)) {
			
			if (GDV5.collisionDirection(this, b, b.dX, b.dY) == 0) { //Intersects from the right
				
				if (powerup == "charge") {
					b.charge += 2;
				}
				
				if (b.charge == 0) b.dX *= -1; 
				else b.charge--;
				
				b.x = this.x + 75; //Makes sure that the ball isn't clipping into the paddle
				
				broken = true;
				
				if ((b.boosted) && (powerup != "speed")) {
					if (b.dX > 0) b.dX += b.boost;
					else if (b.dX < 0) b.dX -= b.boost;
					if (b.dY > 0) b.dY += b.boost;
					else if (b.dY < 0) b.dY -= b.boost;
					
					b.boosted = false;
				}
				
				if ((b.boosted == false) && (powerup == "speed")) {
					if (b.dX > 0) b.dX -= b.boost;
					else if (b.dX < 0) b.dX += b.boost;
					if (b.dY > 0) b.dY -= b.boost;
					else if (b.dY < 0) b.dY += b.boost;
					
					b.boosted = true;
				}
				
				if (powerup == "ball" ) {
					for (int k = 0; k < br.balls.length; k++) {
						if (br.balls[k] == null) {
							br.balls[k] = new Ball(this.x + 27, this.y + 10, 20, 20, false);
							br.balls[k].charge = 0;
							break;
						}
					}
				}
				
				br.score += row * 100;
				
				particles = new Particle[20];
				
				for (int i = 0; i < 6; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + (i * 12), (int)(Math.random() * 3) + this.y, 3, 3);
				}
				for (int i = 6; i < 12; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + ((i - 6) * 12), (int)(Math.random() * 3) + this.y + 40, 3, 3);
				}
				for (int i = 12; i < 16; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x, (int)(Math.random() * 3) + this.y + ((i - 12) * 10), 3, 3);
				}
				for (int i = 16; i < 20; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + 75, (int)(Math.random() * 3) + this.y + ((i - 16) * 10), 3, 3);
				}
			} else if (GDV5.collisionDirection(this, b, b.dX, b.dY) == 2) { //Intersects from the left
				
				if (powerup == "charge") {
					b.charge += 2;
				}
				
				if (b.charge == 0) b.dX *= -1; 
				else b.charge--;
				
				b.x = this.x - 20; //Makes sure that the ball isn't clipping into the paddle
				
				broken = true;
				
				if ((b.boosted) && (powerup != "speed")) {
					if (b.dX > 0) b.dX += b.boost;
					else if (b.dX < 0) b.dX -= b.boost;
					if (b.dY > 0) b.dY += b.boost;
					else if (b.dY < 0) b.dY -= b.boost;
					
					b.boosted = false;
				}
				
				if ((b.boosted == false) && (powerup == "speed")) {
					if (b.dX > 0) b.dX -= b.boost;
					else if (b.dX < 0) b.dX += b.boost;
					if (b.dY > 0) b.dY -= b.boost;
					else if (b.dY < 0) b.dY += b.boost;
					
					b.boosted = true;
				}
				
				if (powerup == "ball" ) {
					for (int k = 0; k < br.balls.length; k++) {
						if (br.balls[k] == null) {
							br.balls[k] = new Ball(this.x + 27, this.y + 10, 20, 20, false);
							br.balls[k].charge = 0;
							break;
						}
					}
				}
				
				br.score += row * 100;
				
				particles = new Particle[20];
				
				for (int i = 0; i < 6; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + (i * 12), (int)(Math.random() * 3) + this.y, 3, 3);
				}
				for (int i = 6; i < 12; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + ((i - 6) * 12), (int)(Math.random() * 3) + this.y + 40, 3, 3);
				}
				for (int i = 12; i < 16; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x, (int)(Math.random() * 3) + this.y + ((i - 12) * 10), 3, 3);
				}
				for (int i = 16; i < 20; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + 75, (int)(Math.random() * 3) + this.y + ((i - 16) * 10), 3, 3);
				}
			} else if (GDV5.collisionDirection(this, b, b.dX, b.dY) == 1) { //Intersects from the top
				
				if (powerup == "charge") {
					b.charge += 2;
				}
				
				if (b.charge == 0) b.dY *= -1; 
				else b.charge--;
				
				b.y = this.y - 20; //Makes sure that the ball isn't clipping into the paddle
				
				broken = true;
				
				if ((b.boosted) && (powerup != "speed")) {
					if (b.dX > 0) b.dX += b.boost;
					else if (b.dX < 0) b.dX -= b.boost;
					if (b.dY > 0) b.dY += b.boost;
					else if (b.dY < 0) b.dY -= b.boost;
					
					b.boosted = false;
				}
				
				if ((b.boosted == false) && (powerup == "speed")) {
					if (b.dX > 0) b.dX -= b.boost;
					else if (b.dX < 0) b.dX += b.boost;
					if (b.dY > 0) b.dY -= b.boost;
					else if (b.dY < 0) b.dY += b.boost;
					
					b.boosted = true;
				}
				
				if (powerup == "ball" ) {
					for (int k = 0; k < br.balls.length; k++) {
						if (br.balls[k] == null) {
							br.balls[k] = new Ball(this.x + 27, this.y + 10, 20, 20, false);
							br.balls[k].charge = 0;
							break;
						}
					}
				}
				
				br.score += row * 100;
				
				particles = new Particle[20];
				
				for (int i = 0; i < 6; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + (i * 12), (int)(Math.random() * 3) + this.y, 3, 3);
				}
				for (int i = 6; i < 12; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + ((i - 6) * 12), (int)(Math.random() * 3) + this.y + 40, 3, 3);
				}
				for (int i = 12; i < 16; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x, (int)(Math.random() * 3) + this.y + ((i - 12) * 10), 3, 3);
				}
				for (int i = 16; i < 20; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + 75, (int)(Math.random() * 3) + this.y + ((i - 16) * 10), 3, 3);
				}
			} else if (GDV5.collisionDirection(this, b, b.dX, b.dY) == 3) { //Intersects from the bottom
				
				if (powerup == "charge") {
					b.charge += 2;
				}
				
				if (b.charge == 0) b.dY *= -1;
				else b.charge--;
				
				b.y = this.y + 40; //Makes sure that the ball isn't clipping into the paddle
				
				broken = true;
				
				if ((b.boosted) && (powerup != "speed")) {
					if (b.dX > 0) b.dX += b.boost;
					else if (b.dX < 0) b.dX -= b.boost;
					if (b.dY > 0) b.dY += b.boost;
					else if (b.dY < 0) b.dY -= b.boost;
					
					b.boosted = false;
				}
				
				if ((b.boosted == false) && (powerup == "speed")) {
					if (b.dX > 0) b.dX -= b.boost;
					else if (b.dX < 0) b.dX += b.boost;
					if (b.dY > 0) b.dY -= b.boost;
					else if (b.dY < 0) b.dY += b.boost;
					
					b.boosted = true;
				}
				
				if (powerup == "ball" ) {
					for (int k = 0; k < br.balls.length; k++) {
						if (br.balls[k] == null) {
							br.balls[k] = new Ball(this.x + 27, this.y + 10, 20, 20, false);
							br.balls[k].charge = 0;
							break;
						}
					}
				}
				
				br.score += row * 100;
				
				particles = new Particle[20];
				
				for (int i = 0; i < 6; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + (i * 12), (int)(Math.random() * 3) + this.y, 3, 3);
				}
				for (int i = 6; i < 12; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + ((i - 6) * 12), (int)(Math.random() * 3) + this.y + 40, 3, 3);
				}
				for (int i = 12; i < 16; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x, (int)(Math.random() * 3) + this.y + ((i - 12) * 10), 3, 3);
				}
				for (int i = 16; i < 20; i++) {
					particles[i] = new Particle((int)(Math.random() * 3) + this.x + 75, (int)(Math.random() * 3) + this.y + ((i - 16) * 10), 3, 3);
				}
			}
		}
		
		if (particles != null) {
			for (int i = 0; i < particles.length; i++) {
				if (particles[i].randomMove() == 1) {
					particles = null;
					break;
				}
			}
		}
	
	}
	
	public void draw(Graphics2D win, Images images, Breakout br) {

		if (broken == false) {
			if (powerup == "none") win.drawImage(images.brickblue, x, y, br);
			else if (powerup == "speed") win.drawImage(images.brickyellow, x, y, br);
			else if (powerup == "ball") win.drawImage(images.brickred, x, y, br);
			else if (powerup == "charge") win.drawImage(images.brickpink, x, y, br);
		}
		if (particles != null) {
			for (Particle p : particles) {
				p.draw(win);
			}
		}
	}
}
