package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class WObjectManager implements ActionListener{
	Player play;
	ArrayList<WallObject> walls = new ArrayList<WallObject>();
	ArrayList<WProjectile> projectiles = new ArrayList<WProjectile>();
	Random ran;
	//changed for testing
	int score = 400;
	int points = 5;
	double numwall = -1;

	WObjectManager(Player play) {
		this.play = play;
	}

	void addProjectile(WProjectile pro) {
		projectiles.add(pro);
	}

	void addWall() {
		numwall++;

		if (score > 550 && score < 650 && numwall%4==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 70;
			walls.add(new WallObject(0,0, dis,25, 3, null));
			walls.add(new WallObject(start,0, 1100,25, 3, null));
			// Stage 7 460-540
		} else if (score > 450 && score < 550 && numwall%4==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 75;
			walls.add(new WallObject(0,0, dis,25, 2, null));
			walls.add(new WallObject(start,0, 1100,25, 2, null));
			// Stage 6 360-440
		} else if (score > 350 && score < 450 && numwall%5==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 75;
			walls.add(new WallObject(0,0, dis,25, 2, null));
			walls.add(new WallObject(start,0, 1100,25, 2, null));
			// Stage 5 310-350
		} else if (score > 300 && score <= 350 && numwall%5==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 100;
			walls.add(new WallObject(0,0, dis,25, 2, null));
			walls.add(new WallObject(start,0, 1100,25, 2, null));
			// Stage 4 260-300
		} else if (score > 250 && score <= 300 && numwall%6==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 100;
			walls.add(new WallObject(0,0, dis,25, 2, null));
			walls.add(new WallObject(start,0, 1100,25, 2, null));
			// Stage 3 220-250
		} else if (score > 210 && score <= 250 && numwall%7==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 100;
			walls.add(new WallObject(0,0, dis,25, 2, null));
			walls.add(new WallObject(start,0, 1100,25, 2, null));
			// Stage 2 110-200
		} else if (score > 100 && score < 210 && numwall%8==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 100;
			walls.add(new WallObject(0,0, dis,25, 1, null));
			walls.add(new WallObject(start,0, 1100,25, 1, null));
			// Stage 1 0-90
		} else if (score >= 0 && score < 100 && numwall%9==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 100;
			walls.add(new WallObject(0,0, dis,25, 1, null));
			walls.add(new WallObject(start,0, 1100,25, 1, null));
		}
	}

	void update() {
		if (!play.isActive) {

		}
		for (int i = 0; i < walls.size(); i++) {
			WallObject a = walls.get(i);
			if (a.x >= Walls.WIDTH || a.y >= Walls.HEIGHT) {
				a.isActive = false;
			}
			a.update();
			if (PurgeObjects(walls, a)) {
				i--;
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			WProjectile p = projectiles.get(i);
			if (p.x >= 704 || p.x <= 5 || p.y <= -20) {
				p.isActive = false;
			}
			p.update();
			if (PurgeObjects(projectiles, p)) {
				i--;
			}
		}
		play.update();
		checkCollision();
	}

	void draw(Graphics g) {
		for (WallObject a: walls) {
			a.draw(g);
		}

		for (WProjectile p: projectiles) {
			p.draw(g);
		}

		play.draw(g);
	}

	boolean PurgeObjects(ArrayList list, WGameObject go) {
		if (!go.isActive) {
			list.remove(go);
			if (list == walls) {
				score+=points;
			}
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addWall();
		if (points==2 && score==404) {
			points = 4;
		}
	}

	public void checkCollision() {
		for(int i = 0; i < walls.size(); i++){
			WallObject a = walls.get(i);
			if (play.collisionBox.intersects(a.collisionBox)) {
				if (GamePanel.lives > 0) {
					GamePanel.lives--;
					a.isActive = false;
					break;
				} else {
					play.isActive = false;
					a.isActive = false;
					break;
				}
			}
			for(int j = 0; j < projectiles.size(); j++){
				WProjectile p = projectiles.get(j);
				if (p.collisionBox.intersects(a.collisionBox)) {
					p.isActive = false;
					a.isActive = false;
					score++;
				}
			}
		}
	}

	public int getScore() {
		return score;
	}
}
