import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	
	Human hu;
	ArrayList<Projectile> pro = new ArrayList<Projectile>();
	
	ObjectManager(Human human) {
		this.hu = human;
	}
	
	void update() {
		hu.update();
		for (Projectile pr : pro) {
			pr.update();
		}
	}
	
	void draw(Graphics g) {
		hu.draw(g);
		for (Projectile pr : pro) {
			pr.draw(g);
		}
	}
	
	void addProjectile(Projectile proj) {
		pro.add(proj);
	}
}
