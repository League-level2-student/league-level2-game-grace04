
public class EmuRun {
	JFrame frame;
	final static int WI = 1500;
	final static int HE = 900;
	//GamePanel gp;
	
	public static void main(String[] args) {
		EmuRun er = new EmuRun();
		er.setup();
	}
	
	EmuRun(){
		frame = new JFrame();
		//gp = new GamePanel();
	}
	
	public void setup() {
		//frame.add(gp);
		//frame.addKeyListener(gp);
		frame.getContentPane().setPreferredSize(new Dimension(WI, HE));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//gp.startGame();
	}
}
