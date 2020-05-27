package game;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Tank1 tanker1;
	private Tank2 tanker2;
	private Map background;
	private int gamestatus;
	private final int DELAY=12;
	private final int P1_WIN = 0;
	private final int P2_WIN = 1;
	private final int EVEN = 3;
	private final int NEXT = 4;
	
	
	public Board() {
		initUI();
		startGame();
	}
	
	private void initUI() {
		JButton bot = new JButton("Play");
		this.setLayout(null);//allow using setBounds
		bot.setBounds(325,100,100,50);
//		bot.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
		this.add(bot);
	}
	
	private void startGame() {
		addKeyListener(new TAdapter());
		setBackground(Color.gray);
		setFocusable(true);
		gamestatus = NEXT;
		background = new Map();
		tanker1 = new Tank1(100,100, 3*Math.PI/4,background.getwall()); //start x, start y, start angle, wall
		tanker2 = new Tank2(600,500, -Math.PI/4,background.getwall());
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		doDrawing(g);
		//Toolkit.getDefaultToolkit().sync();
	}
	
	private void doDrawing(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform initTrans = new AffineTransform(); //initialization
        AffineTransform t = new AffineTransform();//tanker
        AffineTransform t2 = new AffineTransform();//tanker2
        AffineTransform s = new AffineTransform();//shell
        AffineTransform w = new AffineTransform();//wall
        
        //tanker graph
        t.rotate(tanker1.getangle(), tanker1.getX()+tanker1.getWidth()/2, tanker1.getY()+tanker1.getHeight()/2);
        t.translate(tanker1.getX(), tanker1.getY());
        t.scale(1, 1); // scale = 1
		g2d.drawImage(tanker1.getImage(), t, this);

        //tanker2 graph
        t2.rotate(tanker2.getangle(), tanker2.getX()+tanker2.getWidth()/2, tanker2.getY()+tanker2.getHeight()/2);
        t2.translate(tanker2.getX(), tanker2.getY());
        t2.scale(1, 1); // scale = 1
		g2d.drawImage(tanker2.getImage(), t2, this);
		
        //shell for tanker graph
        if(tanker1.getShell()!=null) {
        	for(Shell obj: tanker1.getShell()) {
        		s.setTransform(initTrans);
        		s.rotate(obj.getAngle(), obj.getX(), obj.getY());
        		s.translate(obj.getX(), obj.getY());
        		s.scale(1, 1); // scale = 1
        		g2d.drawImage(obj.getImage(), s, this);
        	}
        }
        
        //shell for tanker2 graph
        if(tanker2.getShell()!=null) {
        	for(Shell obj: tanker2.getShell()) {
        		s.setTransform(initTrans);
        		s.rotate(obj.getAngle(), obj.getX(), obj.getY());
        		s.translate(obj.getX(), obj.getY());
        		s.scale(1, 1); // scale = 1
        		g2d.drawImage(obj.getImage(), s, this);
        	}
        }
        
        //wall graph
        if(background.getwall()!=null) {
        	for(wall obj: background.getwall()) {
        		w.setTransform(initTrans);
        		w.translate(obj.getX(), obj.getY());
        		w.scale(1, 1); // scale = 1
        		g2d.drawImage(obj.getImage(),w, this);
        	}
        }
		
		//background graph
		//g2d.drawImage(background.getImage(), 500,500,this);
        
        if(gamestatus!=NEXT) {
        	Graphics2D gwd = (Graphics2D)g;
        	gwd.setColor(Color.CYAN);
        	gwd.setFont(new Font("TimesRoman",Font.BOLD,50));
        	if(gamestatus==P1_WIN)
        		gwd.drawString("P1 WIN",300,100);
        	else if(gamestatus==P2_WIN)
        		gwd.drawString("P2 WIN",300,100);
        	else if(gamestatus==EVEN)
        		gwd.drawString("EVEN",300,100);
        }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//update both in a single function
		updateTanker();
		updateShell();
		if(ending_condition() == true) {
			System.out.println("end the game");
			//end the game
		}
		repaint();
	}
	
	private void updateTanker() {
		tanker1.move();
		tanker2.move();
	}
	
	private void updateShell() {
		if(tanker1.getShell()!=null) {
			for(Shell obj: tanker1.getShell()) {
				obj.move();
				obj.strike(tanker2);
			}
		}
		if(tanker2.getShell()!=null) {
			for(Shell obj: tanker2.getShell()) {
				obj.move();
				obj.strike(tanker1);
			}
		}
	}
	
	private boolean ending_condition() {
		if(tanker1.getArmor()<=0 && tanker2.getArmor()<=0) {
			gamestatus = EVEN;
			return true;
		}
		else if(tanker2.getArmor()<=0) {
			gamestatus = P1_WIN;
			return true;
		}
		else if(tanker1.getArmor()<=0) {
			gamestatus = P2_WIN;
			return true;
		}
		else {
			gamestatus = NEXT;
			return false;
		}
	}
	
	private class TAdapter extends KeyAdapter{
		
		@Override
		public void keyReleased(KeyEvent e) {
			tanker1.keyReleased(e);
			tanker2.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			tanker1.keyPressed(e);
			tanker2.keyPressed(e);
		}
	}
}
