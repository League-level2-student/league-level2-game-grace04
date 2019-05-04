import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

		Timer timer;
		final int MENU_STATE = 0;
		final int GAME_STATE = 1;
		final int END_STATE = 2;
		int currentState = MENU_STATE;
		Font titleFont;
		Human hu = new Human(400, 600, 50, 100);
		
		GamePanel(){
			timer = new Timer(1000/60, this);
			titleFont = new Font("Courier", Font.PLAIN, 72);
		}

		void startGame() {
			timer.start();
		}
		
		void updateMenuState() {
			
		}
		void updateGameState() {

		}
		void updateEndState() {
			
		}
		
		void drawMenuState(Graphics g) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, EmuRun.WI, EmuRun.HE);
			g.setFont(titleFont);
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("Emu Run", 600, 450);
		}
		void drawGameState(Graphics g) {
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, EmuRun.WI, 700);
			g.setColor(Color.GREEN);
			g.fillRect(0, 700, EmuRun.WI, EmuRun.HE);
			hu.draw(g);
		}
		void drawEndState(Graphics g) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, EmuRun.WI, EmuRun.HE); 
			g.setColor(Color.BLACK);
			g.setFont(titleFont);
			g.drawString("Game Over", 550, 450);
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("message0");
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			char kp = (char) e.getKeyChar();
			int kc = (int) e.getKeyCode();
			if (kp == 10) {
				if (currentState == MENU_STATE) {
					currentState = GAME_STATE;
				} else if (currentState == GAME_STATE) {
					currentState = END_STATE;
				} else if (currentState == END_STATE) {
					currentState = MENU_STATE;
					//rs.isAlive = true;
					//om = new ObjectManager(rs);
				}
			}
			
			if (kp == 'a') {
				//rs.le = true;
			}
			if (kp == 'd') {
				//rs.ri = true;
			}
			if (kp == 'w') {
				//rs.up = true;
			}
			if (kp == 's') {
				//rs.dw = true;
			}
			if (kp == 'k') {
				//om.addProjectile(new Projectile(rs.x + 21, rs.y, 10, 10));
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("message2");
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
