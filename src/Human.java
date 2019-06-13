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
		if (y>550) {
			velocity = 0;
			y = 550;
		}
		y += velocity;
	}
	
	public void jump() {
		if (y>=540) {
			velocity = -20;
		}
	}
	
	void draw(Graphics g) {
		g.drawImage(GamePanel.pnog, x, y, width, height, null);
	}
}
