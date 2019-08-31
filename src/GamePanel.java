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
import javax.swing.JOptionPane;
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
		Font smallFont;
		Human hu = new Human(400, 550, 90, 150);
		ObjectManager om = new ObjectManager(hu);
		Projectile bu;
		public static BufferedImage gi;
		public static BufferedImage ei;
		public static BufferedImage pg;
		public static BufferedImage pnog;
		String code = "ExtremE3MutilatioN1UmbrA";
		String ans;
		
		GamePanel(){
			timer = new Timer(1000/60, this);
			titleFont = new Font("Courier", Font.BOLD, 72);
			normalFont = new Font("Courier", Font.PLAIN, 48);
			smallFont = new Font("Courier", Font.ITALIC, 24);
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
			g.drawString("Emu Run", 610, 450);
			g.setFont(normalFont);
			g.drawString("Down to Jump, Up to Shoot, Don't Die", 230, 650);
		}
		void drawGameState(Graphics g) {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, EmuRun.WI, 700);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 700, EmuRun.WI, EmuRun.HE);
			om.draw(g);
			g.setColor(Color.BLACK);
			g.setFont(smallFont);
			g.drawString(12-om.bull + " bullets left", 10, 20);
		}
		void drawEndState(Graphics g) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, EmuRun.WI, EmuRun.HE);
			g.setColor(Color.BLACK);
			g.setFont(titleFont);
			g.drawString("Game Over", 555, 450);
			g.setFont(normalFont);
			g.drawString(om.kill + " EMUs avoided", 540, 600);
			g.setFont(smallFont);
			g.drawString("Just like the Australians...", 555, 750);
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
			
			if (kc == 16) {
				ans = JOptionPane.showInputDialog(" ");
				if (ans.contentEquals(code)) {
					om.bull -= 1000000;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int kc = (int) e.getKeyCode();
			if (kc == 38) {
				System.out.println("up");
			}
			if (kc == 40) {
				if(om.bull<12) {
					om.addProjectile(new Projectile(hu.x+100, hu.y+55, 10, 10));
					//JOptionPane.showMessageDialog(null, "The Austrailians didn't get bullets, neither do you!");
				}
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
