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

	ObjectManager(Rocketship ship) {
		rocket = ship;
	}

	void addProjectile(Projectile project) {
		projectiles.add(project);
	}

	void addAlien(Alien ali) {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	void update() {
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

	}

	void draw(Graphics g) {
		rocket.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for (int o = 0; o < projectiles.size(); o++) {
			projectiles.get(o).draw(g);

		}
	}

	void purgeObject() {
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
