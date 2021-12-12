package breakout;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import utilities.GDV5;

public class Mouse extends MouseAdapter{

	//Object variables
	String state = "main menu";
	int level = 1;
	boolean levelStart = false;
	
	@Override
	public void mouseReleased(MouseEvent e) {
		switch (state) {
		case "main menu":
			if (((e.getX() > GDV5.getMaxWindowX() / 2 - 150) && (e.getX() < GDV5.getMaxWindowX() / 2 + 150)) && ((e.getY() > GDV5.getMaxWindowY() / 2 - 40) && (e.getY() < GDV5.getMaxWindowY() / 2 + 40))) {
				state = "game";
			}
			if (((e.getX() > GDV5.getMaxWindowX() / 2 - 150) && (e.getX() < GDV5.getMaxWindowX() / 2 + 150)) && ((e.getY() > GDV5.getMaxWindowY() / 2 + 180) && (e.getY() < GDV5.getMaxWindowY() / 2 + 260))) {
				state = "how to play";
			}
			break;
		case "lose":
			if (((e.getX() > GDV5.getMaxWindowX() / 2 - 100) && (e.getX() < GDV5.getMaxWindowX() / 2 + 100)) && ((e.getY() > GDV5.getMaxWindowY() / 2 + 30) && (e.getY() < GDV5.getMaxWindowY() / 2 + 90))) {
				state = "game";
				level = 1;
				levelStart = true;
			}
			break;
		case "win":
			if (((e.getX() > GDV5.getMaxWindowX() / 2 - 100) && (e.getX() < GDV5.getMaxWindowX() / 2 + 100)) && ((e.getY() > GDV5.getMaxWindowY() / 2 + 30) && (e.getY() < GDV5.getMaxWindowY() / 2 + 90))) {
				state = "game";
				level += 1;
				levelStart = true;
			}
			break;
		}
		
		
	}
	
}