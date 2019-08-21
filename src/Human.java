import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Timer;

public class Human extends GameObject{

	double velocity = 0;
	double gravity = 1;
	double power = 20;
	boolean canJump = false;
	
	Human(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	void update() {
		super.update();
		velocity += gravity;
		y += velocity;
		if (y>=550) {
			y = 550;
			velocity = 0;
			canJump = true;
		}
	}
	
	public void jump() {
		if(canJump) {
			velocity -= power;
			canJump = false;
		}
	}
	
	void draw(Graphics g) {
		g.drawImage(GamePanel.pnog, x, y, width, height, null);
	}
}
