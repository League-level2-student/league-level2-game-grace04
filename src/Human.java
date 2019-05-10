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
			y -= 6;
		}
		if (duck == true) {
			y += 6;
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, width, height);
	}
}
