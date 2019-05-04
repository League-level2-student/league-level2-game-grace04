import java.awt.Color;
import java.awt.Graphics;

public class Human extends GameObject{

	Human(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	void update() {
		super.update();
		if (jump == true) {
			y += 5;
		}
		if (duck == true) {
			y -= 5;
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
	}
}
