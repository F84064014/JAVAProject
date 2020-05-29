package game;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Rectangle;

public abstract class Tank {
	
	protected double dx;
	protected double dy;
	protected double da;
	protected double x;
	protected double y;
	protected int w;
	protected int h;
	protected double angle;
	protected List<Shell> tankshell;
	protected List<wall> wallist;
	protected List<Integer> keybuffer;
	protected Image image;
	protected int armor;
	protected int damage;
	protected int ammo;
	protected int cdammo;
	
	final private int MAX_HEALTH=100;
	final protected double ROTATION_RAD = 0.65;
	final protected double MOVING_SPEED = 1.3;
	final protected int MAX_AMMO = 5;
	final protected int CD_AMMO = 6;
	
	//abstract method
	protected abstract void loadImage();
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	
	public Tank(int startx, int starty,double startangle, List<wall> wl) {
		armor = MAX_HEALTH;
		x = startx;
		y = starty;
		angle = startangle;
		this.wallist = wl;
		loadImage();
		tankshell = new ArrayList<Shell>();
		keybuffer = new ArrayList<Integer>();
		ammo = MAX_AMMO;
		cdammo = CD_AMMO;
	}
	
	public void move() {
		angle += da;
		if(angle>2*Math.PI)
			angle -= 2*Math.PI;
		else if(angle<-2*Math.PI)
			angle += 2*Math.PI;
		if(!detectCollision()){
			x += dx;
			y -= dy;
		}
		this.updateShell();
	}
	
	public double getX() {
		
		return x;	
	}
	
	public double getY() {
		
		return y;		
	}
	
	public double getangle() {
		
		return angle;
	}
	
	public int getWidth() {
		
		return w;
	}
	public int getHeight() {
		
		return h;
	}
	public Image getImage() {
		
		return image;
	}
	
	public List<Shell> getShell() {
		return tankshell;
	}
	
	public void fire() {
		if(this.ammo >= 1)
		{
			this.ammo--;
			Shell temp = new Shell(x+(w/2)+((h+4)*Math.sin(angle))/2, y+(h/2)-((h+4)*Math.cos(angle)/2), angle,wallist);
			tankshell.add(temp);
	
		}
		else // reload
		{
			this.cdammo--;
			if(this.cdammo <= 0)
			{
				this.cdammo = this.CD_AMMO;
				this.ammo = this.MAX_AMMO;
			}
		}

	}
	
	public int right()
	{
		return (int)(x + w);
	}
	public int bottom()
	{
		return (int)(y+h);
	}
	public void takedamage(int a) {
		armor-=a;
		System.out.println(armor);
	}
	public void heal(int a) {
		armor+=a;
	}
	public int getArmor() {
		return armor;
	}
	protected void setArmor(int a) {
		this.armor = a;
		ammo = MAX_AMMO;
		cdammo = CD_AMMO;
	}
	protected void setPosition(double px, double py, double pangle) {
		this.x = px;
		this.y = py;
		this.angle = pangle;
	}
	public boolean detectCollision()
	{
		Rectangle target ;
		Rectangle rr = new Rectangle((int)((this.x)+dx), (int)((this.y)-dy), this.w, this.h);

		//border collision
		if(rr.intersectsLine(new Line2D.Float(0,0,750,0))
				|| rr.intersectsLine(new Line2D.Float(0,0,0,600))
				|| rr.intersectsLine(new Line2D.Float(0,570,750,570))
				|| rr.intersectsLine(new Line2D.Float(750,0,750,600))) {
			return true;
		}
		
		//wall collision
		for(wall obj: wallist) {
			target = new Rectangle((int)obj.getX(), (int)obj.getY(), (int)obj.getW(),(int)obj.getH());
			if(rr.intersects(target))
				return true;
		}
		
		rr = null; target = null;
		return false;
	}
	
	public void updateShell() {
		Iterator<Shell> it = tankshell.iterator();
		while(it.hasNext()) {
			boolean status = it.next().getStatus();
			if(status == false)
				it.remove();
		}
	}
	

}

