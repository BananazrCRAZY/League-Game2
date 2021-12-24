package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	final int MENU = 0;
	final int GAME = 1;
	final int GAMEOVER = 2;
	final int INSTRUCTIONSA = 3;
	final int CHOOSE = 4;
	final int INSTRUCTIONSB = 5;
	final int INSTRUCTIONSC = 6;
	final int INSTRUCTIONSD = 7;
	final int INSTRUCTIONSE = 8;
	
	
	int currentState = MENU;
	Font titleF;
	Font subTextF;
	Font smallTextF;
	Timer frameDraw;
	Timer wallSpawn;
	int timeint = -1;
	WObjectManager wom;
	
	Player p;
	String name = "blue player.png";
	int size = 50;
	double speed = 9;
	boolean paused = false;
	
	public boolean defaultPower = true;
	public boolean speedPower = false;
	public boolean breakerPower = false;
	public boolean fourPower = false;
	int ammo = 2;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	GamePanel() {
		titleF = new Font("Arial", Font.PLAIN, 130);
		 subTextF = new Font("Arial", Font.PLAIN, 50);
		 smallTextF = new Font("Arial", Font.PLAIN, 25);
		 p = new Player(550, 600, size, size, speed, name);
		 wom = new WObjectManager(p);
		 wallSpawn = new Timer(1000, wom);
		 
		 if (needImage) {
			 loadImage ("wallsback.jpg");
		}
		 
		 frameDraw = new Timer(1000/60,this);
		 frameDraw.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(currentState == MENU){
			timeint++;
		    drawMenuState(g);
		}else if(currentState == GAME){
			if (!paused) {
				drawGameState(g);
			}
		}else if(currentState == GAMEOVER){
		    drawGameoverState(g);
		} else if (currentState == INSTRUCTIONSA) {
			drawIntructionAState(g);
		} else if (currentState == CHOOSE) {
			drawChooseState(g);
		} else if (currentState == INSTRUCTIONSB) {
			drawInstructionBState(g);
		} else if (currentState == INSTRUCTIONSC) {
			drawInstructionCState(g);
		} else if (currentState == INSTRUCTIONSD) {
			drawInstructionDState(g);
		} else if (currentState == INSTRUCTIONSE) {
			drawInstructionEState(g);
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
	void updateInstruAState() {

	}
	void updateChooseState() {

	}
	void updateInstruBState() {

	}
	void updateInstruCState() {

	}
	void updateInstruSDtate() {

	}
	void updateInstruEState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(titleF);
		g.setColor(Color.WHITE);
		g.drawString("WALLS", 385, 250);

		g.setFont(subTextF);
		if (!(timeint % 20 == 0) && timeint > 0) {
			g.drawString("Press ENTER to PLAY", 355, 500);
		} else if (timeint > 0) timeint = -10;
		
		g.setFont(smallTextF);
		g.drawString("Press SPACE for INSTRUCTIONS", 420, 600);
	}
	void drawGameState(Graphics g) {
		drawImage("wallsback.jpg", g, 0, 0, Walls.WIDTH, Walls.HEIGHT);
		wom.draw(g);
		
		g.setFont(subTextF);
		g.setColor(Color.WHITE);
		g.drawString(wom.score+"", 1100, 50);
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
	void drawIntructionAState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(titleF);
		g.setColor(Color.BLUE);
		g.drawString("INSTUCTIONS", 160, 150);
		
		g.setFont(smallTextF);
		g.setColor(Color.WHITE);
		g.drawString("How To Play: Dodge the moving walls and survive as long as possible.", 200, 200);
		g.drawString("If you get hit by a wall you lose.", 420, 245);
		g.drawString("Press ENTER for MENU", 10, 25);
		drawImage("wallsback.jpg", g, 300, 270, 600, 350);
		g.drawString("<- ARROW KEYS For NEXT->", 430, 670);
	}
	void drawChooseState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(subTextF);
		g.setColor(Color.RED);
		g.drawString("Power Selection", 435, 100);
		
		g.setFont(smallTextF);
		g.setColor(Color.WHITE);
		g.drawString("No Power 'F'", 1000, 50);
		
		drawImage("wallsfillerbackground.jpg", g, 150, 200, 274, 400);
		g.drawString("SPEEDSTER 'A'", 187, 650);
		drawImage("wallsfillerbackground.jpg", g, 463, 200, 274, 400);
		g.drawString("WALL BREAKER 'S'", 483, 650);
		drawImage("wallsfillerbackground.jpg", g, 776, 200, 274, 400);
		g.drawString(" 404 ERROR 'D'", 820, 650);
	}
	void drawInstructionBState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(titleF);
		g.setColor(Color.BLUE);
		g.drawString("INSTUCTIONS", 160, 150);
		
		g.setFont(subTextF);
		g.setColor(Color.WHITE);
		g.drawString("CONTROLS", 450, 240);
		
		g.setFont(smallTextF);
		g.drawString("Move", 350, 325);
		g.drawString("Arrow Keys", 750, 325);
		g.drawString("Close Game/Pause", 350, 375);
		g.drawString("Q", 750, 375);
		g.drawString("Continue/Leave Game", 350, 425);
		g.drawString("ENTER", 750, 425);
		g.drawString("Shoot (Breaker Power)", 350, 475);
		g.drawString("SPACE", 750, 475);
		g.drawString("Press ENTER for MENU", 10, 25);
		g.drawString("<- ARROW KEYS For NEXT->", 430, 670);
	}
	void drawInstructionCState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(titleF);
		g.setColor(Color.BLUE);
		g.drawString("INSTUCTIONS", 160, 150);
		drawImage("wallsfillerbackground.jpg", g, 150, 200, 274, 400);
		
		g.setFont(smallTextF);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER for MENU", 10, 25);
		g.drawString("<- ARROW KEYS For NEXT->", 430, 670);
	}
	void drawInstructionDState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(titleF);
		g.setColor(Color.BLUE);
		g.drawString("INSTUCTIONS", 160, 150);
		
		g.setFont(smallTextF);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER for MENU", 10, 25);
		g.drawString("<- ARROW KEYS For NEXT->", 430, 670);
	}
	void drawInstructionEState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(titleF);
		g.setColor(Color.BLUE);
		g.drawString("INSTUCTIONS", 160, 150);
		
		g.setFont(smallTextF);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER for MENU", 10, 25);
		g.drawString("<- ARROW KEYS For NEXT->", 430, 670);
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
		} else if (currentState == INSTRUCTIONSA) {
			updateInstruAState();
		} else if (currentState == CHOOSE) {
			updateChooseState();
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
		        p = new Player(550, 600, size, size, speed, name);
				wom = new WObjectManager(p);
				ammo = 2;
				if (fourPower) {
					wom.points = 2;
				}
		    } else if (currentState == INSTRUCTIONSA || currentState == INSTRUCTIONSB || currentState == INSTRUCTIONSC || currentState == INSTRUCTIONSD || currentState == INSTRUCTIONSE) {
		    	currentState = MENU;
		    } else if (currentState == CHOOSE) {
		    	//same as f
		    	currentState=MENU;
		    }else {
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
			if (currentState == INSTRUCTIONSC || currentState == INSTRUCTIONSD || currentState == INSTRUCTIONSE) {
				currentState--;
			} else if (currentState == INSTRUCTIONSA) {
				currentState = INSTRUCTIONSE;
			} else if (currentState == INSTRUCTIONSB) {
				currentState = INSTRUCTIONSA;
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			p.right();
			if (currentState == INSTRUCTIONSC || currentState == INSTRUCTIONSD || currentState == INSTRUCTIONSB) {
				currentState++;
			} else if (currentState == INSTRUCTIONSA) {
				currentState = INSTRUCTIONSB;
			} else if (currentState == INSTRUCTIONSE) {
				currentState = INSTRUCTIONSA;
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			p.down();
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			if (currentState == MENU) {
				currentState = INSTRUCTIONSA;
			}
			if (currentState == GAME && breakerPower && ammo > 0) {
				wom.addProjectile(p.getProjectile());
				ammo--;
			}
		}

		if (e.getKeyCode()==81 || e.getKeyCode()==113) {
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
		if (currentState==MENU && (e.getKeyCode()==99 || e.getKeyCode()==67)) {
			currentState=CHOOSE;
			System.out.println("CHOOSE A CHARACTER");
		}
		if (currentState==CHOOSE) {
			// F no power
			if (e.getKeyCode()==70 || e.getKeyCode()==102) {
				System.out.println("Chosen Default");
				name = "blue player.png";
				size = 50;
				speed = 9;
				p = new Player(550, 600, 50, 50, 9, name);
				wom = new WObjectManager(p);
				wom.points = 5;
				powersFalse();
				defaultPower = true;
				currentState=MENU;
			}
			// A speedster
			if (e.getKeyCode()==65 || e.getKeyCode()==97) {
				System.out.println("Chosen Speedster");
				name = "speedblueplayer.png";
				size = 45;
				speed = 14;
				p = new Player(550, 600, 45, 45, 14, name);
				wom = new WObjectManager(p);
				wom.points = 5;
				powersFalse();
				speedPower = true;
				currentState=MENU;
			}
			// S breaker
			if (e.getKeyCode()==83 || e.getKeyCode()==115) {
				System.out.println("Chosen Wall Breaker");
				name = "breakerblueplayer.png";
				size = 55;
				speed = 6.9;
				p = new Player(550, 600, 55, 55, 6.9, name);
				wom = new WObjectManager(p);
				wom.points = 5;
				powersFalse();
				breakerPower = true;
				currentState=MENU;
			}
			// D 
			if (e.getKeyCode()==68 || e.getKeyCode()==100) {
				System.out.println("Chosen 404 ERROR");
				name = "404blueplayer.png";
				size = 50;
				speed = 11;
				p = new Player(550, 600, 50, 50, 11, name);
				wom = new WObjectManager(p);
				wom.points = 2;
				powersFalse();
				fourPower = true;
				currentState=MENU;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void drawImage(String file, Graphics g, int x, int y, int w, int h) {
		loadImage(file);
		if (gotImage) {
			g.drawImage(image, x, y, w, h, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, w, h);
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
	
	void startGame() {
		wallSpawn = new Timer(1000, wom);
		wallSpawn.start();
	}
	void stopGame() {
		wallSpawn.stop();
	}
	
	void powersFalse() {
		defaultPower = false;
		speedPower = false;
		breakerPower = false;
		fourPower = false;
	}
}
