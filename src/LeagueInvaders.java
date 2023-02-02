import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame gameFrame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;		
		public static void main(String[] args) {
			LeagueInvaders game = new LeagueInvaders();
			game.setup();
		}
		LeagueInvaders() {
			this.gameFrame = new JFrame();;
		}
		void setup() {
			gameFrame.setSize(WIDTH, HEIGHT);
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameFrame.setVisible(true);
		}
}
