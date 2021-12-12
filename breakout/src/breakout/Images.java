package breakout;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Images {
	
	//Images
	BufferedImage ballblue; {
		try { 
			File file = new File("src/images/ballblue.png");
			FileInputStream fis = new FileInputStream(file);
			ballblue = ImageIO.read(fis);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	BufferedImage ballred; {
		try { 
			File file = new File("src/images/ballred.png");
			FileInputStream fis = new FileInputStream(file);
			ballred = ImageIO.read(fis);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	BufferedImage paddle; {
		try { 
			File file = new File("src/images/paddle.png");
			FileInputStream fis = new FileInputStream(file);
			paddle = ImageIO.read(fis);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	BufferedImage brickblue; {
		try { 
			File file = new File("src/images/brickblue.png");
			FileInputStream fis = new FileInputStream(file);
			brickblue = ImageIO.read(fis);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	BufferedImage brickpink; {
		try { 
			File file = new File("src/images/brickpink.png");
			FileInputStream fis = new FileInputStream(file);
			brickpink = ImageIO.read(fis);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	BufferedImage brickred; {
		try { 
			File file = new File("src/images/brickred.png");
			FileInputStream fis = new FileInputStream(file);
			brickred = ImageIO.read(fis);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	BufferedImage brickyellow; {
		try { 
			File file = new File("src/images/brickyellow.png");
			FileInputStream fis = new FileInputStream(file);
			brickyellow = ImageIO.read(fis);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}