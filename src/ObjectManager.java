import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	
	Human hu;
	GameObject go;
	Projectile bu;
	ArrayList<Projectile> pro = new ArrayList<Projectile>();
	ArrayList<Emu> emu = new ArrayList<Emu>();
	long enemyTimer = 0;
	int enemySpawnTime;
	int kill = 0;
	Random gen = new Random();

	
	ObjectManager(Human human) {
		this.hu = human;
	}
	
	void update() {
		hu.update();
		purgeObjects();
		for (Projectile pr : pro) {
			pr.update();
		}
		for (Emu em : emu) {
			em.update();
		}
	}
	
	void draw(Graphics g) {
		hu.draw(g);
		for (Projectile pr : pro) {
			pr.draw(g);
		}
		for (Emu em : emu) {
			em.draw(g);
		}
	}
	
	void addProjectile(Projectile proj) {
		pro.add(proj);
	}
	void addEmu(Emu e) {
		emu.add(e);
	}
	
	public void manageEnemies() {
		enemySpawnTime = gen.nextInt(5000)+500;
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addEmu(new Emu(1500, 600, 120, 100));
			enemyTimer = System.currentTimeMillis();
		}
	}
	
	void purgeObjects() {
		for(int i=pro.size()-1; i>=0; i--) {
			if(pro.get(i).isAlive==false) {
				pro.remove(i);
			}
		}
		
		for(int i=emu.size()-1; i>=0; i--) {
			if(emu.get(i).isAlive==false) {
				emu.remove(i);
				kill++;
			}
		}
	}
	
	void checkCollision(){
		for(Emu e : emu){
	        if(hu.collisionBox.intersects(e.collisionBox)){
	        		hu.isAlive = false;
	        }
	        for(Projectile pr : pro) {
				if(pr.collisionBox.intersects(e.collisionBox)) {
					e.isAlive = false;
					pr.isAlive = false;
				}
			}
		}
	}
}
