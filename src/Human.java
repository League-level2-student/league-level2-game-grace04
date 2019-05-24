import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Timer;

public class Human extends GameObject{

	double velocity;
	double gravity = 1;
	
	Human(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	void update() {
		super.update();
		velocity += gravity;
		if (y>650) {
			velocity = 0;
			y = 650;
		}
		y += velocity;
	}
	
	public void jump() {
		if (y>=650) {
			velocity = -15;
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, width, height);
	}
}
