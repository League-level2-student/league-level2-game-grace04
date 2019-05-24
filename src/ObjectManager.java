import java.awt.Graphics;

public class ObjectManager {
	Human hu;
	
	ObjectManager(Human human) {
		this.hu = human;
	}
	
	void update() {
		hu.update();
	}
	
	void draw(Graphics g) {
		hu.draw(g);
	}
}
