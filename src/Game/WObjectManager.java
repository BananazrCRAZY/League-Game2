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
	ArrayList<Pass> passes = new ArrayList<Pass>();
	Random ran;
	//changed for testing
	int score = 280;
	double numwall = -1;

	WObjectManager(Player play) {
		this.play = play;
	}

//	void addProjectile(Projectile pro) {
//		projectiles.add(pro);
//	}

	void addWall() {
		numwall++;
		if (score > 550 && score < 650 && numwall%5==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 70;
			walls.add(new WallObject(0,0, dis,50, 3));
			walls.add(new WallObject(start,0, 1100,50, 3));
		} else if (score > 450 && score < 550 && numwall%6==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 75;
			walls.add(new WallObject(0,0, dis,50, 2));
			walls.add(new WallObject(start,0, 1100,50, 2));
		} else if (score > 350 && score < 450 && numwall%7==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 75;
			walls.add(new WallObject(0,0, dis,50, 2));
			walls.add(new WallObject(start,0, 1100,50, 2));
		} else if (score > 300 && score < 350 && numwall%7==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 100;
			walls.add(new WallObject(0,0, dis,50, 2));
			walls.add(new WallObject(start,0, 1100,50, 2));
		} else if (score > 250 && score < 300 && numwall%8==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 100;
			walls.add(new WallObject(0,0, dis,50, 2));
			walls.add(new WallObject(start,0, 1100,50, 2));
		} else if (score > 210 && score < 250 && numwall%9==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 100;
			walls.add(new WallObject(0,0, dis,50, 2));
			walls.add(new WallObject(start,0, 1100,50, 2));
		} else if (score > 100 && score < 210 && numwall%9==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 100;
			walls.add(new WallObject(0,0, dis,50, 1));
			walls.add(new WallObject(start,0, 1100,50, 1));
		} else if (score >= 0 && score < 100 && numwall%10==0) {
			ran = new Random();
			int dis = ran.nextInt(Walls.WIDTH-100);
			int start = dis + 100;
			walls.add(new WallObject(0,0, dis,50, 1));
			walls.add(new WallObject(start,0, 1100,50, 1));
			//passes.add(new Pass(ran.nextInt(Walls.WIDTH-1),0,70,55));
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
		for (int i = 0; i < passes.size(); i++) {
			Pass ps = passes.get(i);
			if (ps.x >= Walls.WIDTH || ps.y >= Walls.HEIGHT) {
				ps.isActive = false;
			}
			ps.update();
			if (PurgeObjects(passes, ps)) {
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
		for (Pass p: passes) {
			p.draw(g);
		}


//		for (Projectile p: projectiles) {
//			p.draw(g);
//		}

		play.draw(g);
	}

	boolean PurgeObjects(ArrayList list, WGameObject go) {
		if (!go.isActive) {
			list.remove(go);
			score+=5;
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
		boolean noskip = true;
		
//		for(int k = 0; k < walls.size(); k++){
//			Pass p = passes.get(k);
//			if (play.collisionBox.intersects(p.collisionBox)) {
//				play.isActive = true;
//				noskip = false;
//			}
//		}
		
		if (noskip) {
			for(int i = 0; i < walls.size(); i++){
				WallObject a = walls.get(i);
				if (play.collisionBox.intersects(a.collisionBox)) {
					play.isActive = false;
					a.isActive = false;
					break;
				}
			}
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
	
	public int getScore() {
		return score;
	}
}
