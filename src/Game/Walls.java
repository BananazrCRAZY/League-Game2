package Game;
import java.awt.Color;

import javax.swing.JFrame;

public class Walls {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 650;
	JFrame f;
	GamePanel gp;
	public static void main(String[] args) {
		Walls wall = new Walls();
	}
	
	public Walls() {
		f = new JFrame();
		gp = new GamePanel();
		setup(WIDTH, HEIGHT);
		f.addKeyListener(gp);
	}
	
	public void setup(int wid, int hei) {
		f.add(gp);
		f.setSize(wid, hei);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
