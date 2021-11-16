package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class WObjectManager implements ActionListener{
	Player play;
	ArrayList<WallObject> walls = new ArrayList<WallObject>();
	Random ran = new Random();
	int score = 0;

	WObjectManager(Player play) {
		this.play = play;
	}

//	void addProjectile(Projectile pro) {
//		projectiles.add(pro);
//	}

	void addWall() {
		walls.add(new WallObject(ran.nextInt(Walls.WIDTH),0,50,50));
	}

	void update() {
		if (!play.isActive) {
			
		}
		for (int i = 0; i < walls.size(); i++) {
			WallObject a = walls.get(i);
			if (a.x >= 690 || a.x <= -10 || a.y >= 820) {
				a.isActive = false;
			}
			a.update();
			if (PurgeObjects(walls, a)) {
				i--;
			}
		}
//		for (int i = 0; i < projectiles.size(); i++) {
//			Projectile p = projectiles.get(i);
//			if (p.x >= 704 || p.x <= 5 || p.y <= -20) {
//				p.isActive = false;
//			}
//			p.update();
//			if (PurgeObjects(projectiles, p)) {
//				i--;
//			}
//		}
		play.update();
		checkCollision();
	}

	void draw(Graphics g) {
		for (WallObject a: walls) {
			a.draw(g);
		}

//		for (Projectile p: projectiles) {
//			p.draw(g);
//		}

		play.draw(g);
	}

	boolean PurgeObjects(ArrayList list, WGameObject go) {
		if (!go.isActive) {
			list.remove(go);
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addWall();
	}

	public void checkCollision() {
		for(int i = 0; i < walls.size(); i++){
			WallObject a = walls.get(i);
			if (play.collisionBox.intersects(a.collisionBox)) {
				play.isActive = false;
				a.isActive = false;
				break;
			}
//			for(int j = 0; j < projectiles.size(); j++){
//				Projectile p = projectiles.get(j);
//				if (p.collisionBox.intersects(a.collisionBox)) {
//					p.isActive = false;
//					a.isActive = false;
//					score++;
//				}
//			}
		}
	}
	
	public int getScore() {
		return score;
	}
}
