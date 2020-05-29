package game;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Tank1 tanker1;
	private Tank2 tanker2;
	private Map background;
	private int gamestatus;
	public static final int DELAY=14;
	public static final int P1_WIN = 0;
	public static final int P2_WIN = 1;
	public static final int EVEN = 3;
	public static final int NEXT = 4;
	public static final int MENU = 5;
	public static final int RESTART = 6;
	
	private List<JButton> menubtnlist;
	private List<JButton> returnbtnlist;
	
	public Board() {
		initUI();
		startGame();
	}
	
	private void initUI() {
		gamestatus = MENU;
		menubtnlist = new ArrayList<JButton>();
		returnbtnlist = new ArrayList<JButton>();
		this.setLayout(null);
		BtnListener BtnListen = new BtnListener(this);
		
		
		JButton Button1 = new JButton("Play");
		Button1.setBounds(325, 100, 100, 60);
		this.add(Button1);
		Button1.addActionListener(BtnListen);

		JButton Button2 = new JButton("bt2");
		Button2.setBounds(325, 200, 100, 60);
		this.add(Button2);
		Button2.addActionListener(BtnListen);
		
		JButton returnButton = new JButton("return to Menu");
		returnButton.setBounds(275, 200, 250, 60);
		this.add(returnButton);
		returnButton.addActionListener(BtnListen);
		returnButton.setVisible(false);
		
		JButton restartButton = new JButton("restart");
		restartButton.setBounds(275, 300, 250, 60);
		this.add(restartButton);
		restartButton.addActionListener(BtnListen);
		restartButton.setVisible(false);
		
		returnbtnlist.add(returnButton);
		returnbtnlist.add(restartButton);
		menubtnlist.add(Button1);
		menubtnlist.add(Button2);
	}
	
	private void startGame() {
		addKeyListener(new TAdapter());
		setBackground(Color.gray);
		setFocusable(true);
		background = new Map_basic();
		tanker1 = new Tank1(100,100, 3*Math.PI/4,background.getwall()); //start x, start y, start angle, wall
		tanker2 = new Tank2(600,500, -Math.PI/4,background.getwall());
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(gamestatus != MENU)
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
		if(gamestatus == NEXT) {
			updateTanker();
			updateShell();
			updataGameStatus();
			repaint();
		}
		if(gamestatus == Board.P1_WIN || gamestatus == Board.P2_WIN || gamestatus == Board.EVEN) {
			returntoMenu();
		}
		if(gamestatus == Board.MENU) {
			updateGameSet();
		}
		if(gamestatus == Board.RESTART) {
			updateGameSet();
			gamestatus = NEXT;
		}
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
	
	private boolean updataGameStatus() {
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
	
	private void updateGameSet() {
		tanker1.setArmor(100);
		tanker2.setArmor(100);
		tanker1.setPosition(100,100, 3*Math.PI/4);
		tanker2.setPosition(600,500, -Math.PI/4);
	}
	
	private void returntoMenu() {
		for(JButton obj: returnbtnlist) {
			obj.setVisible(true);
		}
	}
	
	public void setGameStatus(int s) {
		gamestatus = s;
	}
	
	public List<JButton> getMenuBtnList() {
		return menubtnlist;
	}
	
	public List<JButton> getReturnBtnList() {
		return returnbtnlist;
	}

	
	private class TAdapter extends KeyAdapter{
		
		@Override
		public void keyReleased(KeyEvent e) {
			if(gamestatus != MENU) {
				tanker1.keyReleased(e);
				tanker2.keyReleased(e);
			}
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(gamestatus != MENU) {
				tanker1.keyPressed(e);
				tanker2.keyPressed(e);
			}
		}
	}
}
