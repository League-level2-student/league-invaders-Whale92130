import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font textFont;
	Font smallFont;
	Timer frameDraw;
	Timer alienSpawn;
	boolean drawRules = false;
	Rocketship ship = new Rocketship(250, 700, 50, 50);
	ObjectManager obManage = new ObjectManager(ship);

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
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

	void updateMenuState() {

	}

	void updateGameState() {
		obManage.update();
	}

	void updateEndState() {

	}
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("League Invaders", 75, 200);
		g.setFont(textFont);
		g.drawString("Press ENTER to start", 100, 400);
		g.drawString("Press SPACE for instructions", 75, 600);
		if (drawRules == true) {
			g.setFont(smallFont);
			g.drawString("1. Use arrow keys for movement", 75, 650);
			g.drawString("2. Press SPACE to shoot", 75, 700);
			g.drawString("3. Don't die", 75, 750);
		}
		
	}
	
	void drawGameState(Graphics g) {
		// g.setColor(Color.black);
		// g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		
		obManage.draw(g);
		
		if (ship.isActive == false) {
			boolean hi = true;
			while (hi == true) {
			hi = false;
			stopGame();
			currentState = END;
			}
		}

		
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 100, 200);
		g.setFont(textFont);
		g.drawString("Press ENTER to restart", 100, 400);
		String score1 = String.valueOf(obManage.getScore());
		g.drawString("Your Score Was: " + score1, 10, 30);

	}

	GamePanel() {
		this.titleFont = new Font("Arial", Font.PLAIN, 48);
		this.textFont = new Font("Arial", Font.PLAIN, 28);
		this.smallFont = new Font("Arial", Font.PLAIN, 16);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
			loadImage("space.png");
		}

	}
	
	
	
	void startGame() {
		alienSpawn = new Timer(1000, obManage);
		alienSpawn.start();
		ship.isActive = true;
		obManage.score =0;
	}

	void stopGame() {
		alienSpawn.stop();
		System.out.println("stop");
		ship.x = 250;
		ship.y = 700;
		obManage.clearAliens();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();

		} else if (currentState == END) {

			updateEndState();
		}
		repaint();
		// System.out.println("action");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			drawRules = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;

			} else {
				currentState++;
				if (currentState == GAME) {
					startGame();
				} else if (currentState == END) {
					stopGame();
				}

			}
		}
		if (currentState == GAME) {

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("UP");
				if (ship.y > 0) {
					ship.up();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("DOWN");
				if (ship.y >= 720) {

				} else {
					ship.down();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				System.out.println("LEFT");
				if (ship.x > 0) {
					ship.left();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				System.out.println("RIGHT");
				if (ship.x >= 450) {

				} else {
					ship.right();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				obManage.addProjectile(ship.getProjectile());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
