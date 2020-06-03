package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.List;
import javax.swing.ImageIcon;

public class Shell{
	
	protected double x;
	protected double y;
	protected double angle;
	protected double da;
	protected int w;
	protected int h;
	protected Image image;
	protected boolean hasshot;
	protected boolean shellStatus; //still exist or not
	protected List<Wall> wallist;
	protected Line2D traject;

	private int DAMAGE;
	private int SPEED;
	
	public Shell(double px, double py, double pa, List<Wall> wl, int damage, int speed) {
		this.set_position(px,py,pa);
		this.loadImage();
		wallist = wl;
		hasshot = false;
		shellStatus = true;
		DAMAGE = damage;
		SPEED = speed;
	}
	
	public void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/shell.png");
		image = ii.getImage();
		
		w = image.getWidth(null);
		h = image.getHeight(null);
	}
	
	public void set_position(double px, double py, double pa) {
		x = px;
		y = py;
		angle = pa;
	}
	
	public void move() {

		int px = (int)x;
		double py = (int)y;
		
		x += SPEED*Math.sin(angle);
		y -= SPEED*Math.cos(angle);
		this.traject = new Line2D.Float((int)px, (int)py, (int)x, (int)y);
		
		this.shellStatus = updateStatus();

	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getAngle() {
		return angle;
	}
	public Image getImage() {
		return image;
	}
	public boolean getStatus() {
		return shellStatus;
	}
	
	public boolean updateStatus()
	{
		Rectangle target ;
		
		//border collision
		if(traject.intersectsLine(new Line2D.Float(0,0,750,0))
				|| traject.intersectsLine(new Line2D.Float(0,0,0,600))
				|| traject.intersectsLine(new Line2D.Float(0,570,750,570))
				|| traject.intersectsLine(new Line2D.Float(750,0,750,600))) {
			return false;
		}
		
		//wall collision
		for(Wall obj: wallist)
		{
			if(obj.getIgnoreShell() == true)
				continue;
			target = new Rectangle((int)obj.getX(), (int)obj.getY(), (int)obj.getW(),(int)obj.getH());
			if(traject.intersects(target))
				return false;
		}
		target = null;
		return true;
	}
	
	public void strike(Tank enemy) {
		Rectangle rr = new Rectangle((int)enemy.getX(), (int)enemy.getY(), enemy.getWidth(), enemy.getHeight());
		if(rr.intersectsLine(traject)) {
			System.out.println("hit");
			this.shellStatus = false;
			enemy.takedamage(DAMAGE);
		}
	}
	

		
}
