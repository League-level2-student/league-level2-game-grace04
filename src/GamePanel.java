import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

		Timer timer;
		final int MENU_STATE = 0;
		final int GAME_STATE = 1;
		final int END_STATE = 2;
		int currentState = MENU_STATE;
		Font titleFont;
		Font normalFont;
		Human hu = new Human(400, 550, 100, 150);
		ObjectManager om = new ObjectManager(hu);
		Projectile bu;
		public static BufferedImage gi;
		public static BufferedImage ei;
		public static BufferedImage pg;
		public static BufferedImage pnog;
		
		GamePanel(){
			timer = new Timer(1000/60, this);
			titleFont = new Font("Courier", Font.PLAIN, 72);
			normalFont = new Font("Courier", Font.ITALIC, 48);
			try {
				gi = ImageIO.read(this.getClass().getResourceAsStream("eggimage.png"));
                ei = ImageIO.read(this.getClass().getResourceAsStream("emuimage.png"));
                pg = ImageIO.read(this.getClass().getResourceAsStream("persongun.png"));
                pnog = ImageIO.read(this.getClass().getResourceAsStream("personnogun.png"));
			} 
			catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        	}
		}

		void startGame() {
			timer.start();
		}
		
		void updateMenuState() {
			
		}
		void updateGameState() {
			om.update();
			om.manageEnemies();
			om.manageEggs();
			om.checkCollision();
			if(hu.isAlive==false) {
				currentState = END_STATE;
			}
		}
		void updateEndState() {
			
		}
		
		void drawMenuState(Graphics g) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, EmuRun.WI, EmuRun.HE);
			g.setFont(titleFont);
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("Emu Run", 600, 450);
			g.setFont(normalFont);
			g.drawString("Down to Jump, Up to Shoot, Don't Die", 250, 600);
		}
		void drawGameState(Graphics g) {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, EmuRun.WI, 700);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 700, EmuRun.WI, EmuRun.HE);
			om.draw(g);
		}
		void drawEndState(Graphics g) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, EmuRun.WI, EmuRun.HE); 
			g.setColor(Color.BLACK);
			g.setFont(titleFont);
			g.drawString("Game Over", 550, 450);
			g.setFont(normalFont);
			g.drawString(om.kill + " emus avoided", 530, 600);
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			char kp = (char) e.getKeyChar();
			int kc = (int) e.getKeyCode();
			System.out.println("kc is " + kc);
			if (kp == 10) {
				if (currentState == MENU_STATE) {
					currentState = GAME_STATE;
				} else if (currentState == GAME_STATE) {
					currentState = END_STATE;
				} else if (currentState == END_STATE) {
					currentState = MENU_STATE;
					hu.isAlive = true;
					om = new ObjectManager(hu);
				}
			}
		
			if (kc == 38) {
				System.out.println("jump");
				hu.jump();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			char kr = (char) e.getKeyChar();
			int kc = (int) e.getKeyCode();
			if (kc == 38) {
				System.out.println("up");
				hu.jump = false;
			}
			if (kc == 40) {
				om.addProjectile(new Projectile(hu.x+100, hu.y+55, 10, 10));
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			repaint();
	        if(currentState == MENU_STATE){
                updateMenuState();
	        }
	        else if(currentState == GAME_STATE){
                updateGameState();
	        }
	        else if(currentState == END_STATE){
                updateEndState();
	        }
		}
		
		@Override
		public void paintComponent(Graphics g){
			if (currentState == MENU_STATE) {
				drawMenuState(g);
			}
			else if (currentState == GAME_STATE) {
				drawGameState(g);
			}
			else if (currentState == END_STATE) {
				drawEndState(g);
			}
			repaint();
		}
}
