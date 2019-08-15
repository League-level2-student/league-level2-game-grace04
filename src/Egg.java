import java.awt.Color;
import java.awt.Graphics;

public class Egg extends GameObject{

	Egg(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	void update() {
	    super.update();
		x -= 15;
		if(x<0) {
			isAlive = false;
		}
	}
	
	void draw(Graphics g) {
		g.drawImage(GamePanel.gi, x, y, width, height, null);
	}
}
