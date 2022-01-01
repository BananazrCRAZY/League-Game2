package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class WallObject extends WGameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public WallObject(int x, int y, int width, int height, double speed, String name) {
		super(x, y, width, height, speed, name);
		
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
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
		if (GamePanel.highlight) {
			g.setColor(Color.YELLOW);
			g.drawRect(collisionBox.x, collisionBox.y, width, height);
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
