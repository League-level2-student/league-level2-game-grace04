import java.awt.Color;
import java.awt.Graphics;

public class Emu extends GameObject{

	Emu(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	void update() {
	    super.update();
		x -= 10;
		if(x<0) {
			isAlive = false;
		}
	}
	
	void draw(Graphics g) {
		g.drawImage(GamePanel.ei, x, y, width, height, null);
	}
}
