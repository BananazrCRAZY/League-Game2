package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int GAMEOVER = 2;
	final int INSTRUCTIONS = 3;
	
	int currentState = MENU;
	Font titleF;
	Font subTextF;
	Font smallTextF;
	Timer frameDraw;
	Timer wallSpawn;
	WObjectManager wom;
	Player p;
	boolean paused = false;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	GamePanel() {
		titleF = new Font("Arial", Font.PLAIN, 130);
		 subTextF = new Font("Arial", Font.PLAIN, 50);
		 smallTextF = new Font("Arial", Font.PLAIN, 25);
		 p = new Player(550, 600, 50, 50);
		 wom = new WObjectManager(p);
		 wallSpawn = new Timer(1000, wom);
		 
		 if (needImage) {
			    loadImage ("wallsfillerbackground.jpg");
			}
		 
		 frameDraw = new Timer(1000/60,this);
		 frameDraw.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
			if (!paused) {
				drawGameState(g);
			}
		}else if(currentState == GAMEOVER){
		    drawGameoverState(g);
		} else if (currentState == INSTRUCTIONS) {
			drawIntructionsState(g);
		}
	}
	
	void updateMenuState() {
		
	}
	void updateGameState() {
		if (!paused) {
			wom.update();
			if (!p.isActive) {
				currentState = GAMEOVER;
			}
		}
	}
	void updateGameoverState() {

	}
	void updateInstruState() {
		
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(titleF);
		g.setColor(Color.WHITE);
		g.drawString("WALLS", 385, 250);
		
		g.setFont(subTextF);
		g.drawString("Press ENTER to PLAY", 355, 500);
		
		g.setFont(smallTextF);
		g.drawString("Press SPACE for INSTRUCTIONS", 420, 600);
		
		g.drawString("'Q' Quit", 20, 40);
	}
	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, Walls.WIDTH, Walls.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		}
		
		wom.draw(g);
	}
	void drawGameoverState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(titleF);
		g.setColor(Color.RED);
		g.drawString("GAMEOVER", 220, 250);
		
		g.setFont(subTextF);
		g.setColor(Color.WHITE);
		g.drawString("SCORE: " + wom.getScore(), 440, 400);
		
		g.setFont(smallTextF);
		g.drawString("Press ENTER for MENU", 250, 600);
		g.drawString("Press 'Q' to QUIT", 800, 600);
	}
	void drawIntructionsState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(titleF);
		g.setColor(Color.BLUE);
		g.drawString("INSTUCTIONS", 160, 150);
		
		g.setFont(subTextF);
		g.setColor(Color.WHITE);
		g.drawString("GOAL: To SURVIVE for as long as possible", 130, 270);
		
		g.drawString("PLAY: MOVE using the ARROW KEYS", 130, 340);
		g.drawString("don't get hit by the walls by", 290, 400);
		g.drawString("moving to the holes in the walls", 290, 460);
		
		g.drawString("LOSE: If you get hit by a wall GAMEOVER", 130, 540);
		
		g.setFont(smallTextF);
		g.drawString("Press ENTER to RETURN", 450, 610);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
			if (!paused) {
				updateGameState();
			}
		}else if(currentState == GAMEOVER){
		    updateGameoverState();
		} else if (currentState == INSTRUCTIONS) {
			updateInstruState();
		}
		repaint();
		
		if (p.y <= 0) {
			p.y = 1;
		}
		if (p.y >= Walls.HEIGHT-50) {
			p.y = Walls.HEIGHT-51;
		}
		if (p.x <= 0) {
			p.x = 1;
		}
		if (p.x >= Walls.WIDTH-50) {
			p.x = Walls.WIDTH-51;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == GAMEOVER) {
		        currentState = MENU;
		        p = new Player(550, 600, 50, 50);
				wom = new WObjectManager(p);
		    } else if (currentState == INSTRUCTIONS) {
		    	currentState = MENU;
		    } else {
		        currentState++;
		        if (currentState == GAME) {
		        	startGame();
		        }else if (currentState == GAMEOVER) {
		        	stopGame();
		        }
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    if (p.y > 0) {
		    	p.up();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			if (p.x > 0) {
		    	p.left();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    	p.right();
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    	p.down();
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			if (currentState == MENU) {
				currentState = INSTRUCTIONS;
			}
		}
		
		if ((currentState == GAMEOVER || currentState == MENU || currentState == INSTRUCTIONS) && (e.getKeyCode()==81 || e.getKeyCode()==113)) {
			System.exit(0);
		}
		if (currentState==GAME && (e.getKeyCode()==81 || e.getKeyCode()==113)) {
			stopGame();
			paused = true;
			int quit = JOptionPane.showOptionDialog(null, "Are you sure you would like to QUIT?", "Pop-up Title", 0, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Back to GAME", "QUIT"}, null);
			if (quit == 1) {
				System.exit(0);
			} else {
				paused = false;
				wallSpawn.start();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
	
	void startGame() {
		wallSpawn = new Timer(1000, wom);
		wallSpawn.start();
	}
	void stopGame() {
		wallSpawn.stop();
	}
}
