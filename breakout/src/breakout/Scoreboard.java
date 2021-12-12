package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import utilities.GDV5;

public class Scoreboard {

	public void draw(Graphics2D win, int score, Font font) {
		win.setFont(font.deriveFont(100F));
		Color blue = new Color(0, 255, 255, 150);
		win.setColor(blue);
		win.drawString("" + score, GDV5.getMaxWindowX() / 2 - (win.getFontMetrics(font.deriveFont(100F)).stringWidth("" + score)) / 2 + 5, GDV5.getMaxWindowY() / 2 + win.getFontMetrics(font.deriveFont(100F)).getHeight() / 2);
	}

}
