package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class WallObject extends WGameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public WallObject(int x, int y, int width, int height, double speed) {
		super(x, y, width, height, speed);
		
		if (needImage) {
		    loadImage ("wall.png");
		}
	}
	
	void update() {
		y+=speed;
		super.update();
	}
	
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.YELLOW);
			g.fillRect(x, y, width, height);
		}
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
