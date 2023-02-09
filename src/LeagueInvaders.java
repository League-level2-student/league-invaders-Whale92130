import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	JFrame gameFrame;
	GamePanel panel;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;		
		public static void main(String[] args) {
			LeagueInvaders game = new LeagueInvaders();
			game.setup();
		}
		LeagueInvaders() {
			this.gameFrame = new JFrame();;
			this.panel = new GamePanel();
		}
		 
		void setup() {
			gameFrame.add(panel);
			gameFrame.setSize(WIDTH, HEIGHT);
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameFrame.setVisible(true);
		}
}
