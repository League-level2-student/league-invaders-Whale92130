import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rocket;
	ArrayList projectile = new ArrayList();
	ArrayList alien = new ArrayList();
	Random random = new Random();
	ObjectManager(Rocketship ship) {
		rocket = ship;
	}
	void addProjectile(Projectile project) {
		projectile.add(project);
	}
	void addAlien(Alien ali) {
		alien.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	void update() {
		
	}
}
