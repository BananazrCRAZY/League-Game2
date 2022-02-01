package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	Rectangle collisionBox;
	Font titleF;
	Font subTextF;
	Font smallTextF;
	Timer frameDraw;
	Timer wallSpawn;
	int timeint = -1;
	WObjectManager wom;
	public static boolean highlight =  false;
	
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
	public static int lives = 0;
	Random ran;	
	
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
		 ran = new Random();
		 
//		 if (needImage) {
//			 loadImage ("wallsback.jpg");
//			 loadImage ("speedwallsbanner.png");
//			 
//		}
		 
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
		g.setColor(Color.RED);
		g.drawString("Quit 'Q'", 50, 50);
		g.setColor(Color.YELLOW);
		g.drawString("Power Selection 'P'", 50, 650);
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
		wom.draw(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(subTextF);
		g.setColor(Color.RED);
		g.drawString("Power Selection", 435, 100);
		
		g.setFont(smallTextF);
		g.setColor(Color.WHITE);
		g.drawString("No Power '1'", 1050, 50);
		
		drawImage("speedwallsbanner.png", g, 150, 200, 274, 400);
		g.drawString("SPEEDSTER", 210, 650);
		drawImage("breakerwallsbanner.png", g, 463, 200, 274, 400);
		g.drawString("BREAKER", 540, 650);
		drawImage("fourwallsbanner.png", g, 776, 200, 274, 400);
		g.drawString(" 404 ERROR", 840, 650);
		g.setColor(Color.YELLOW);
		g.drawString("CLICK to select a POWER", 50, 50);
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
		g.drawString("WASD/Arrow Keys", 750, 325);
		g.drawString("Close Game/Pause", 350, 375);
		g.drawString("Q", 750, 375);
		g.drawString("Continue/Leave Game", 350, 425);
		g.drawString("ENTER", 750, 425);
		g.drawString("Power Ability", 350, 475);
		g.drawString("SPACE", 750, 475);
		g.drawString("Highlight Walls & Player", 350, 525);
		g.drawString("Toggle H", 750, 525);
		g.drawString("Select", 350, 575);
		g.drawString("Click", 750, 575);
		g.drawString("Press ENTER for MENU", 10, 25);
		g.drawString("<- ARROW KEYS For NEXT->", 430, 670);
	}
	void drawInstructionCState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Walls.WIDTH, Walls.HEIGHT);
		
		g.setFont(titleF);
		g.setColor(Color.BLUE);
		g.drawString("INSTUCTIONS", 160, 150);
		drawImage("speedwallsbanner.png", g, 250, 200, 274, 400);
		
		g.setFont(subTextF);
		g.setColor(Color.WHITE);
		g.drawString("POWER: SPEEDSTER", 550, 230);
		g.drawString("  - Smaller Size", 550, 300);
		g.drawString("  - Increased Speed", 550, 370);
		g.drawString("Ability- Warp:", 550, 470);
		g.drawString("  - Warp Forward Even", 550, 540);
		g.drawString("    Through Walls", 550, 610);
		
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
		drawImage("breakerwallsbanner.png", g, 250, 200, 274, 400);
		
		g.setFont(subTextF);
		g.setColor(Color.WHITE);
		g.drawString("POWER: BREAKER", 550, 230);
		g.drawString("  - Slower & Bigger Size", 550, 300);
		g.drawString("  - 2 Lives", 550, 370);
		g.drawString("Ability- Destroy:", 550, 470);
		g.drawString("  - Shoots Wall Breaking", 550, 540);
		g.drawString("    Bullet", 550, 610);
		
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
		drawImage("fourwallsbanner.png", g, 250, 200, 274, 400);
		
		g.setFont(subTextF);
		g.setColor(Color.WHITE);
		g.drawString("POWER: 404 ERROR", 550, 230);
		g.drawString("  - ?Speed Unknown?", 550, 300);
		g.drawString("  - 2 Lives", 550, 370);
		g.drawString("Ability- Point:", 550, 470);
		g.drawString("  - Given 4 Points Per Wall", 550, 540);
		g.drawString("    Until 404 Points Reached", 550, 610);
		
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
				ammo = 2;
				if (fourPower) {
					fourPowerC();
				} else if (breakerPower) {
					breakerPowerC();
				} else if (speedPower) {
					speedsterPowerC();
				} else if (defaultPower) {
					defaultPowerC();
				}
				p = new Player(550, 600, size, size, speed, name);
				wom = new WObjectManager(p);
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
		
		if (e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode() == 119 || e.getKeyCode() == 87) {
		    if (p.y > 0) {
		    	p.up();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode() == 97 || e.getKeyCode() == 65) {
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
		if (e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode() == 100 || e.getKeyCode() == 68) {
			p.right();
			if (currentState == INSTRUCTIONSC || currentState == INSTRUCTIONSD || currentState == INSTRUCTIONSB) {
				currentState++;
			} else if (currentState == INSTRUCTIONSA) {
				currentState = INSTRUCTIONSB;
			} else if (currentState == INSTRUCTIONSE) {
				currentState = INSTRUCTIONSA;
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode() == 115 || e.getKeyCode() == 83) {
			p.down();
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			if (currentState == MENU) {
				currentState = INSTRUCTIONSA;
			}
			if (currentState == GAME) {
				if (breakerPower && ammo > 0) {
					wom.addProjectile(p.getProjectile());
					ammo--;
				}
				if (speedPower && ammo > 0) {
					p.y-=100;
					ammo--;
				}
			}
		}
		// q quit
		if (e.getKeyCode()==81 || e.getKeyCode()==113) quit();
		// c choose
		if ((currentState==MENU || currentState == GAMEOVER) && (e.getKeyCode()==80 || e.getKeyCode()==112)) {
			currentState=CHOOSE;
			
		}
		
		if (e.getKeyCode()==72 || e.getKeyCode()==104) highlight = !highlight;
		if (currentState == CHOOSE && e.getKeyCode() == 49) {
			defaultPowerC();
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
//		System.out.println("x:" + e.getX());
//		System.out.println("y:" + e.getY());
		if (currentState == MENU) {
			if (e.getX() >= 30) {
				if (e.getX() <= 150 && e.getY() >= 40 && e.getY() <= 105) { 
					quit();
				} else if (e.getX() <= 300 && e.getY() >= 620 && e.getY() <= 700) {
					currentState = CHOOSE;
				}
			}
			if (e.getX() >= 350 && e.getX() <= 890 && e.getY() >= 480 && e.getY() <= 535) {
				currentState = GAME;
				startGame();
			}
			if (e.getX() >= 400 && e.getX() <= 815 && e.getY() >= 590 && e.getY() <= 645) {
				currentState = INSTRUCTIONSA;
			}
		}
		if (currentState == 3 || currentState == 5 || currentState == 6 || currentState == 7 || currentState == 8) {
			if (e.getX() >= 10 && e.getX() <= 300 && e.getY() >= 0 && e.getY() <= 60) {
				currentState = MENU;
			}
			if (e.getX() >= 430 && e.getX() <= 470 && e.getY() >= 690 && e.getY() <= 705) {
				if (currentState == INSTRUCTIONSC || currentState == INSTRUCTIONSD || currentState == INSTRUCTIONSE) {
					currentState--;
				} else if (currentState == INSTRUCTIONSA) {
					currentState = INSTRUCTIONSE;
				} else if (currentState == INSTRUCTIONSB) {
					currentState = INSTRUCTIONSA;
				}
			} else if (e.getX() >= 740 && e.getX() <= 780 && e.getY() >= 675 && e.getY() <= 705) {
				if (currentState == INSTRUCTIONSC || currentState == INSTRUCTIONSD || currentState == INSTRUCTIONSB) {
					currentState++;
				} else if (currentState == INSTRUCTIONSA) {
					currentState = INSTRUCTIONSB;
				} else if (currentState == INSTRUCTIONSE) {
					currentState = INSTRUCTIONSA;
				}
			}
		}
		if (currentState == CHOOSE) {
			if (e.getX() >= 150 && e.getX() <= 424 && e.getY() >= 200 && e.getY() <= 670) {
				speedsterPowerC();
			}
			if (e.getX() >= 463 && e.getX() <= 737 && e.getY() >= 200 && e.getY() <= 670) {
				breakerPowerC();
			}
			if (e.getX() >= 776 && e.getX() <= 1050 && e.getY() >= 200 && e.getY() <= 670) {
				fourPowerC();
			}
			if (e.getX() >= 1040 && e.getX() <= 1180 && e.getY() >= 40 && e.getY() <= 100) {
				defaultPowerC();
			}
		}
		if (currentState == GAMEOVER) {
			if (e.getX() >= 250 && e.getX() <= 530 && e.getY() >= 600 && e.getY() <= 640) {
				currentState = MENU;
				ammo = 2;
				if (fourPower) {
					fourPowerC();
				} else if (breakerPower) {
					breakerPowerC();
				} else if (speedPower) {
					speedsterPowerC();
				} else if (defaultPower) {
					defaultPowerC();
				}
				p = new Player(550, 600, size, size, speed, name);
				wom = new WObjectManager(p);
			}
			if (e.getX() >= 800 && e.getX() <= 1000 && e.getY() >= 600 && e.getY() <= 640) {
				quit();
			}
		}
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
			gotImage = false;
			needImage = true;
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
	
	void defaultPowerC() {
		name = "blue player.png";
		size = 50;
		speed = 9;
		p = new Player(550, 600, 50, 50, 9, name);
		wom = new WObjectManager(p);
		wom.points = 5;
		lives = 0;
		powersFalse();
		defaultPower = true;
		currentState=MENU;
	}
	void speedsterPowerC() {
		name = "speedblueplayer.png";
		size = 45;
		speed = 14;
		p = new Player(550, 600, 45, 45, 14, name);
		wom = new WObjectManager(p);
		wom.points = 5;
		lives = 0;
		powersFalse();
		speedPower = true;
		currentState=MENU;
	}
	void breakerPowerC() {
		name = "breakerplayer.png";
		size = 55;
		speed = 6.9;
		p = new Player(550, 600, 55, 55, 6.9, name);
		wom = new WObjectManager(p);
		wom.points = 5;
		lives = 1;
		powersFalse();
		breakerPower = true;
		currentState=MENU;
	}
	void fourPowerC() {
		ran = new Random();
		speed = ran.nextInt(15-7)+7;
		name = "fourofourplayer.png";
		size = 50;
		p = new Player(550, 600, 50, 50, speed, name);
		wom = new WObjectManager(p);
		wom.points = 2;
		lives = 1;
		powersFalse();
		fourPower = true;
		currentState=MENU;
	}
	
	void quit() {
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
