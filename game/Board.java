package game;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Tank1 tanker;
	private Tank2 tanker2;
	private Map background;
	private final int DELAY=12;
	
	
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.gray);
		setFocusable(true);
		background = new Map();
		tanker = new Tank1(100,100, 3*Math.PI/4,background.getwall()); //start x, start y, start angle, wall
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
        t.rotate(tanker.getangle(), tanker.getX()+tanker.getWidth()/2, tanker.getY()+tanker.getHeight()/2);
        t.translate(tanker.getX(), tanker.getY());
        t.scale(1, 1); // scale = 1
		g2d.drawImage(tanker.getImage(), t, this);

        //tanker2 graph
        t2.rotate(tanker2.getangle(), tanker2.getX()+tanker2.getWidth()/2, tanker2.getY()+tanker2.getHeight()/2);
        t2.translate(tanker2.getX(), tanker2.getY());
        t2.scale(1, 1); // scale = 1
		g2d.drawImage(tanker2.getImage(), t2, this);
		
        //shell for tanker graph
        if(tanker.getShell()!=null) {
        	for(Shell obj: tanker.getShell()) {
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
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//update both in a single function
		updateTanker();
		updateShell();
		repaint();
	}
	
	private void updateTanker() {
		tanker.move();
		tanker2.move();
	}
	
	private void updateShell() {
		if(tanker.getShell()!=null) {
			for(Shell obj: tanker.getShell()) {
				obj.move();
			}
		}
		if(tanker2.getShell()!=null) {
			for(Shell obj: tanker2.getShell()) {
				obj.move();
			}
		}
	}
	
	
	private class TAdapter extends KeyAdapter{
		
		@Override
		public void keyReleased(KeyEvent e) {
			tanker.keyReleased(e);
			tanker2.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			tanker.keyPressed(e);
			tanker2.keyPressed(e);
		}
	}
}
