import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	
	Human hu;
	GameObject go;
	Projectile bu;
	ArrayList<Projectile> pro = new ArrayList<Projectile>();
	ArrayList<Emu> emu = new ArrayList<Emu>();
	ArrayList<Egg> egg = new ArrayList<Egg>();
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
		for (Egg eg : egg) {
			eg.update();
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
		for (Egg eg : egg) {
			eg.draw(g);
		}
	}
	
	void addProjectile(Projectile proj) {
		pro.add(proj);
	}
	void addEmu(Emu e) {
		emu.add(e);
	}
	void addEgg(Egg g) {
		egg.add(g);
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
		for(int i=egg.size()-1; i>=0; i--) {
			if(egg.get(i).isAlive==false) {
				egg.remove(i);
			}
		}
	}
	
	void checkCollision(){
		for(Projectile pr : pro) {
			for(Emu e : emu){
				if(hu.collisionBox.intersects(e.collisionBox)){
	        		hu.isAlive = false;
				}
				if(pr.collisionBox.intersects(e.collisionBox)) {
					e.isAlive = false;
					pr.isAlive = false;
				}
			}
			for(Egg g : egg){
				if(hu.collisionBox.intersects(g.collisionBox)){
	        		hu.isAlive = false;
				}
				if(pr.collisionBox.intersects(g.collisionBox)) {
					g.isAlive = false;
					pr.isAlive = false;
				}
			}
		}
	}
}
