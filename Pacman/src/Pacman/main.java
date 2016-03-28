package Pacman;
import javax.swing.JFrame;


public class main extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("Game One");
		frame.add(new Game());
		frame.setSize(757, 855);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
