package game;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
	protected Image image;
	protected List<wall> wallist;
	protected List<Integer> keybuffer;
	//*****
	protected int health;
	protected int damage;
	public void takedamage(int a) {health-=a;} //health-a
	public void heal(int a) {health+=a;}
	//******
	protected int ammo;
	protected int cdammo;
	final protected double ROTATION_RAD = 0.65;
	final protected double MOVING_SPEED = 1.3;
	final protected int MAX_AMMO = 4;
	final protected int CD_AMMO = 6;
	
	public Tank(int startx, int starty,double startangle, List<wall> wl) {
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

	protected abstract void loadImage();
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	
	public final void move() {
		angle += da;
		if(angle>2*Math.PI)
			angle -= 2*Math.PI;
		else if(angle<-2*Math.PI)
			angle += 2*Math.PI;
		x += dx;
		y -= dy;
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
			Shell temp = new Shell(x+(w/2)+((h+4)*Math.sin(angle))/2, y+(h/2)-((h+4)*Math.cos(angle)/2), angle);
			tankshell.add(temp);
			System.out.println("Roger");
	
		}
		else // reload
		{
			System.out.println("Reloading");
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
	public boolean detectCollision(List<wall> w)
	{
		Rectangle rr;
		Rectangle target ;
		for(wall obj: w)
		{
			rr = new Rectangle((int)this.x, (int)this.y, this.w, this.h);
			target = new Rectangle((int)obj.getX(), (int)obj.getY(), (int)obj.getW(),(int)obj.getH());
			if(rr.intersects(target))
			{return true;}
			
		} // end for
		rr = null; target = null;
		return false;
	}

}

