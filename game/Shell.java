package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Shell{
	
	
	protected double x;
	protected double y;
	protected double angle;
	final protected double dx = 2;
	final protected double dy = 2;
	protected double da;
	protected int w;
	protected int h;
	protected Image image;
	protected boolean hasshot;
	
	public Shell(double px, double py, double pa) {
		this.set_position(px,py,pa);
		this.loadImage();
		hasshot = false;
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
		
		x += dx*Math.sin(angle);
		y -= dy*Math.cos(angle);
		if(x == -1f || y == -1f)
		{
			return ;
		}
		
		Line2D l1 = new Line2D.Float((int)px, (int)py, (int)x, (int)y);

		if(l1.intersectsLine(new Line2D.Float(0,0,600,0))
				|| l1.intersectsLine(new Line2D.Float(0,0,0,750)))
		{
			System.out.println("Hit wall");
		}
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
	

		
}
