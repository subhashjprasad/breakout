package breakout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

import utilities.GDV5; //Graphics library

public class Breakout extends GDV5 {
	
	//Game variables
	String state = "main menu";
	int lives;
	int level = 1;
	boolean once = false;
	int score = 0;
	boolean pressed = false;
	int delay = 0;
	
	//Objects
	Paddle paddle = new Paddle(GDV5.getMaxWindowX() / 2 - 80, GDV5.getMaxWindowY() - 60, 160, 20);
	Ball[] balls;
	Ball ball = new Ball(GDV5.getMaxWindowX() / 2 - 10, GDV5.getMaxWindowY() / 2 - 10, 20, 20, true);
	Images images = new Images();
	Mouse mouse = new Mouse();
	static Font font;
	Stroke stroke = new BasicStroke(4F);
	static Brick[] bricks;
	int r, g, b;
	Color c_blue = new Color(0, 255, 255);
	Color c_red = new Color(255, 0, 0);
	Color c_green = new Color(0, 255, 0);
	Scoreboard scoreboard = new Scoreboard();
	
	public Breakout() { //Constructor
		setFrames(120);
		addMouseListener(mouse);
	}
	
	public static void main(String[] args) { //Main method
		Breakout breakout = new Breakout();
		breakout.start();
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/dogica.ttf")).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/dogica.ttf")));
			
		} 
		catch(IOException | FontFormatException e) {}
		
	}
	
	@Override
	public void update() { //Runs every frame of the game and handles all of the game logic stuff
		state = mouse.state;
		level = mouse.level;
		if (mouse.levelStart) { levelStart(); mouse.levelStart = false; }
		switch (state) {
		case "main menu":
			break;
		case "game":
			if (once == false) {
				levelStart();
				once = true;
			}
			updateGame();
			break;
		case "lose":
			break;
		case "win":
			break; }
		skip();
	}
	
	public void levelStart() { //Runs once when the level starts
		
		delay = 80;
		
		bricks = new Brick[level * 8]; //Creates brick array
		
		for (int i = 0; i < bricks.length; i++) { //Initializes each brick
			bricks[i] = new Brick((i % 8) * 75, (i / 8) * 40 + 40, 75, 40, (bricks.length - i - 1) / 8 + 1);
		}
		
		if (level == 1) {
			lives = 10;
			score = 0;
		}
		
		balls = new Ball[100];
		balls[0] = ball;
		
		ball.x = GDV5.getMaxWindowX() / 2 - 10;
		ball.y = GDV5.getMaxWindowY() / 2 - 10;
		ball.boosted = false;
		ball.dX = (int)(Math.random()) * 6 - 3 ;
		ball.dY = 3;

	}
	
	public void updateGame() { //Subsystem of update method that only runs during the game state
		
		//Paddle events
		paddle.keyPressed();
		paddle.keyReleased();
		paddle.move();
		
		for (int i = 0; i < balls.length; i++) {
			if (balls[i] != null) {
				paddle.paddleCheck(balls[i]);
			}
		}
		
		//Ball events
		for (int i = 0; i < balls.length; i++) {
			if (balls[i] != null) {
				if (i == 0) {
					if (delay > 0) delay--;
					else balls[i].move(this);
				}
				else balls[i].move(this);
			}
		}
		
		//Brick events
		for (int i = 0; i < bricks.length; i++) {
			
			for (int j = 0; j < balls.length; j++) {
				if (balls[j] != null) {
					bricks[i].brickCheck(balls[j], this);
				}
			}
			
		}
		
		winCheck();
		
	}

	@Override
	public void draw(Graphics2D win) { //Runs every frame of the game and draws the graphics to the screen
		
		switch (state) {
		case "main menu": 
			win.setColor(c_blue);
			win.setFont(font.deriveFont(65F));
			win.drawString("BREAKOUT", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(65F)).stringWidth("BREAKOUT")) / 2, GDV5.getMaxWindowY() / 4 - win.getFontMetrics(font.deriveFont(65F)).getHeight() / 2 + 30);
			win.setFont(font.deriveFont(40F));
			win.drawString("PLAY", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(40F)).stringWidth("PLAY")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(40F)).getHeight() / 2);
			win.setStroke(stroke);
			win.drawRoundRect(GDV5.getMaxWindowX() / 2 - 150, GDV5.getMaxWindowY() / 2 - 40, 300, 80, 20, 20);
			win.setFont(font.deriveFont(20F));
			win.drawString("HOW TO PLAY", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(20F)).stringWidth("HOW TO PLAY")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(20F)).getHeight() / 2 + 220);
			win.setStroke(stroke);
			win.drawRoundRect(GDV5.getMaxWindowX() / 2 - 150, GDV5.getMaxWindowY() / 2 + 180, 300, 80, 20, 20);
			win.drawString("SUBHASH PRASAD", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(20F)).stringWidth("SUBHASH PRASAD")) / 2, GDV5.getMaxWindowY() - win.getFontMetrics(font.deriveFont(20F)).getHeight());
			break;
		case "how to play":
			win.setColor(c_blue);
			win.setFont(font.deriveFont(10F));
			win.drawString("use the left and right arrow keys to move the paddle.", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(10F)).stringWidth("use the left and right arrow keys to move the paddle.")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(10F)).getHeight() / 2 - 220);
			win.drawString("bounce the ball back and break all the bricks to win!", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(10F)).stringWidth("bounce the ball back and break all the bricks to win.")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(10F)).getHeight() / 2 - 190);
			win.drawString("don't let the ball hit the bottom, you'll lose a life!", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(10F)).stringWidth("don't let the ball hit the bottom, you'll lose a life!")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(10F)).getHeight() / 2 - 160);
			
			win.setColor(Color.blue);
			win.drawString("blue bricks are just normal bricks.", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(10F)).stringWidth("blue bricks are just normal bricks.")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(10F)).getHeight() / 2 - 100);
			win.setColor(Color.yellow);
			win.drawString("yellow bricks will slow down the ball when you hit them.", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(10F)).stringWidth("yellow bricks will slow down the ball when you hit them.")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(10F)).getHeight() / 2 - 70);
			win.setColor(Color.red);
			win.drawString("red bricks will spawn another ball when you hit them.", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(10F)).stringWidth("red bricks will spawn another ball when you hit them.")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(10F)).getHeight() / 2 - 40);
			win.setColor(Color.pink);
			win.drawString("pink bricks will give your ball enough power", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(10F)).stringWidth("pink bricks will give your ball enough power")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(10F)).getHeight() / 2 - 10);
			win.drawString("to pass through a few more bricks.", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(10F)).stringWidth("to pass through a few more bricks.")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(10F)).getHeight() / 2 + 20);
			
			win.setColor(c_blue);
			win.drawString("press escape at any time to go back to main menu.", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(10F)).stringWidth("press escape at any time to go back to main menu.")) / 2, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(10F)).getHeight() / 2 + 100);
			break;
		case "game": 
			win.setColor(c_blue);
			win.setFont(font);
			win.drawString("LIVES: " + lives, 10, GDV5.getMaxWindowY() - 10);
			win.drawString("LEVEL: " + level, GDV5.getMaxWindowX() - win.getFontMetrics(font).stringWidth("LEVEL: " + level) - 10, GDV5.getMaxWindowY() - 10);
			
			for (int i = 0; i < balls.length; i++) {
				if (balls[i] != null) {
					if (i == 0) win.drawImage(images.ballblue, balls[i].x, balls[i].y, this);
					else win.drawImage(images.ballred, balls[i].x, balls[i].y, this);
				}
			}
			win.drawImage(images.paddle, paddle.x, paddle.y, this);
			
			for (Brick b : bricks) { //Drawing each brick if it's not broken
				b.draw(win, images, this);
			}
			
			scoreboard.draw(win, score, font);
			break;
		case "lose":
			win.setColor(c_red);
			win.setFont(font);
			win.drawString("YOU LOSE!", GDV5.getMaxWindowX() / 2 - win.getFontMetrics(font).stringWidth("YOU LOSE!") / 2, GDV5.getMaxWindowY() / 2 - 10 - 50);
			win.setFont(font);
			win.drawString("RESTART", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font).stringWidth("RESTART")) / 2, GDV5.getMaxWindowY() / 2 + 60 + win.getFontMetrics(font).getHeight() / 2);
			win.setStroke(stroke);
			win.drawRoundRect(GDV5.getMaxWindowX() / 2 - 100, GDV5.getMaxWindowY() / 2 + 30, 200, 60, 20, 20);
			break;
		case "win":
			win.setColor(c_green);
			win.setFont(font);
			win.drawString("YOU WIN!", GDV5.getMaxWindowX() / 2 - win.getFontMetrics(font).stringWidth("YOU WIN!") / 2, GDV5.getMaxWindowY() / 2 - 10 - 50);
			win.setFont(font);
			win.drawString("NEXT LEVEL", GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font).stringWidth("NEXT LEVEL")) / 2, GDV5.getMaxWindowY() / 2 + 60 + win.getFontMetrics(font).getHeight() / 2);
			win.setStroke(stroke);
			win.drawRoundRect(GDV5.getMaxWindowX() / 2 - 125, GDV5.getMaxWindowY() / 2 + 30, 250, 60, 20, 20);
			break;
		}
	}
	
	public void restart() { //Resets when the ball hits the bottom
		if (lives > 1) lives--;
		else {
			mouse.state = "lose";
			score = 0;
		}
		ball.x = GDV5.getMaxWindowX() / 2 - 10;
		ball.y = GDV5.getMaxWindowY() / 2 - 10;
		ball.boosted = false;
		ball.dX = (int)(Math.random()) * 6 - 3 ;
		ball.dY = 3;
		delay = 80;
	}
		
	public void winCheck() {
		int count = 0;
		
		for (int i = 0; i < bricks.length; i++) {
			if (bricks[i].broken == true) count++;
		}
		
		if (count == bricks.length) {
			mouse.state = "win";
		}
	}
	
	public void skip() {
		if (GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
			pressed = true;
		}
		
		if (!GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
			if (pressed) {
				mouse.level++;
				levelStart();
				pressed = false;
			}
		}
		
		if (GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) {
			mouse.state = "main menu";
			mouse.levelStart = true;
			once = false;
		}
	
	}

}
