package Game;

import java.awt.Rectangle;

public class WGameObject {
	 int x;
	 int y;
	 int width;
	 int height;
	 double speed;
	 String name;
	 boolean isActive = true;
	 Rectangle collisionBox;
	 
	 public WGameObject(int x, int y, int width, int height, double speed, String name) {
		 this.x = x;
		 this.y = y;
		 this.width = width;
		 this.height = height;
		 this.speed = speed;
		 this.name = name;
		 collisionBox = new Rectangle(x, y, width, height);
	 }
	 
	 void update() {
		 collisionBox.setBounds(x, y, width, height);
	 }
}
