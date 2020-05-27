package game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainClass extends JFrame {
	
	public MainClass() {
		initUI();
	}
	
	private void initUI() {
		add(new Board());
		setSize(750,600);
		setTitle("JAVA Project Tank game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(()->{
			MainClass ex = new MainClass();
			ex.setVisible(true); //show app screen
		});

	}

}