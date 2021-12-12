package breakout;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class Paddle extends Rectangle {
	
	//Object variables
	boolean left_pressed = false;
	boolean right_pressed = false;
	int left_move, right_move;
	double mini_right_move, mini_left_move;
	double acc = 0.6;
	int maxSpeed = 12;
	
	public Paddle(int x, int y, int w, int h) { //Constructor
		super(x, y, w, h);
	}
	
	public void move() { //Moves the paddle
		
		x += right_move - left_move; //Updates x position
		
		//Checks for wall collision
		if (this.getX() >= GDV5.getMaxWindowX() - this.width) { //Right side
			this.x = GDV5.getMaxWindowX() - this.width;
		} else if (this.getX() <= 0) { //Left side
			this.x = 0;
		}

		//Acceleration and deceleration of paddle
		if ((right_pressed) && (right_move <= maxSpeed)) {
			mini_right_move += acc;
			right_move = (int)mini_right_move;
		}
		if ((left_pressed) && (left_move <= maxSpeed)) {
			mini_left_move += acc;
			left_move = (int)mini_left_move;
		}
		if (!(right_pressed) && (right_move >= 0)) {
			mini_right_move -= acc;
			right_move = (int)mini_right_move;
		}
		if (!(left_pressed) && (left_move >= 0)) {
			mini_left_move -= acc;
			left_move = (int)mini_left_move;
		}
	}
	
	public void paddleCheck(Ball b) { //Checks for collision with ball
		if (b.intersects(this)) {
			b.dY *= -1; //Reverses ball's y-direction
			
			b.y = this.y - 20; //Makes sure that the ball isn't clipping into the paddle
			
			//b.dX += (right_move - left_move) / 6; //Changes ball's x-direction based on paddle movement
		}
	}
	
	public void keyPressed() { //Checks for keys being pressed
		if (GDV5.KeysPressed[KeyEvent.VK_LEFT]) {
			left_pressed = true;
		}
		if (GDV5.KeysPressed[KeyEvent.VK_RIGHT]) {
			right_pressed = true;
		}
	}
	
	public void keyReleased() { //Checks for keys not being pressed
		if (!GDV5.KeysPressed[KeyEvent.VK_LEFT]) {
			left_pressed = false;
		}
		if (!GDV5.KeysPressed[KeyEvent.VK_RIGHT]) {
			right_pressed = false;
		}
	}
}
