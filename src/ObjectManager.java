import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	int score = 0;

	ObjectManager(Rocketship ship) {
		rocket = ship;
	}
	int getScore() {
		return score;
	}
	void addProjectile(Projectile project) {
		projectiles.add(project);
	}

	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	void update() {
		if (rocket.isActive == true) {
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if (aliens.get(i).y > LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive = false;
			}
		}
		for (int o = 0; o < projectiles.size(); o++) {
			projectiles.get(o).update();
			if (projectiles.get(o).y > LeagueInvaders.HEIGHT) {
				projectiles.get(o).isActive = false;
			}
		}
		rocket.Update();
		
		checkCollision();
		purgeObjects();
	}
	}

	void draw(Graphics g) {
		rocket.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for (int o = 0; o < projectiles.size(); o++) {
			projectiles.get(o).draw(g);

		}
		String score1 = String.valueOf(getScore());
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score1, 10, 20);
	}

	void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
			for (int o = 0; o < projectiles.size(); o++) {
				if (projectiles.get(o).isActive == false) {
					projectiles.remove(o);
				}
			}
		}
	}
	void clearAliens() {
		while (aliens.size() > 0) {
				aliens.remove(0);
			}

	 
		
	}
	//rocket.collisionBox.intersects(alien.collisionBox)
	void checkCollision() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).collisionBox.intersects(rocket.collisionBox)) {
				rocket.isActive = false;
				System.out.println("rocket died");
				
			}
			for (int o = 0; o < projectiles.size(); o++) {
			if (aliens.get(i).collisionBox.intersects(projectiles.get(o).collisionBox)) {
				System.out.println("alien died");
				aliens.remove(i);
				projectiles.remove(o);
				score++;
			}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("alien added");
		addAlien();
	}
}
