package Game;

import java.awt.Rectangle;

public class WGameObject {
	 int x;
	 int y;
	 int width;
	 int height;
	 double speed;
	 boolean isActive = true;
	 Rectangle collisionBox;
	 
	 public WGameObject(int x, int y, int width, int height, double speed) {
		 this.x = x;
		 this.y = y;
		 this.width = width;
		 this.height = height;
		 this.speed = speed;
		 collisionBox = new Rectangle(x, y, width, height);
	 }
	 
	 void update() {
		 collisionBox.setBounds(x, y, width, height);
	 }
}
