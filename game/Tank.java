package game;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.awt.Rectangle;

public abstract class Tank {
	
	private double dx;
	private double dy;
	private double da;
	private double x;
	private double y;
	private int w;
	private int h;
	private double angle;
	private List<Shell> tankshell;
	private List<Wall> wallist;
	private List<Integer> keybuffer;
	private List<Integer> controlset;
	private List<Tank> playerlist;
	private Image image;
	private int armor;
	private int ammo;
	private long CD;
	
		//abstract method
	protected abstract void loadImage();
	public abstract int getMAX_ARMOR();
	public abstract double getROTATION_RAD(); 
	public abstract double getMOVING_SPEED();
	public abstract int getMAX_AMMO();
	public abstract int getCD_AMMO();
	public abstract int getDAMAGE();
	public abstract int getSHELL_SPEED();
	public abstract long getREFILL_CD();
	
	
	public Tank(int startx, int starty,double startangle, List<Wall> wl, List<Tank> pl, String ctrset) {
		armor = getMAX_ARMOR();
		x = startx;
		y = starty;
		angle = startangle;
		this.wallist = wl;
		this.playerlist = pl;
		loadImage();
		tankshell = new ArrayList<Shell>();
		keybuffer = new ArrayList<Integer>();
		controlset = new ArrayList<Integer>();
		if(ctrset.equals("set1")) {
			controlset.add(KeyEvent.VK_UP);
			controlset.add(KeyEvent.VK_DOWN);
			controlset.add(KeyEvent.VK_LEFT);
			controlset.add(KeyEvent.VK_RIGHT);
			controlset.add(KeyEvent.VK_SPACE);
		}
		else if(ctrset.equals("set2")) {
			controlset.add(KeyEvent.VK_W);
			controlset.add(KeyEvent.VK_S);
			controlset.add(KeyEvent.VK_A);
			controlset.add(KeyEvent.VK_D);
			controlset.add(KeyEvent.VK_F);
		}
		ammo = getMAX_AMMO();
		CD = 0;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == controlset.get(2)) 
			keybuffer.add(controlset.get(2));
		if(key == controlset.get(3))
			keybuffer.add(controlset.get(3));
		if(key == controlset.get(0))
			keybuffer.add(controlset.get(0));
		if(key == controlset.get(1))
			keybuffer.add(controlset.get(1));
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == controlset.get(2)) {
			keybuffer.removeAll(Arrays.asList(controlset.get(2)));
			da = 0;
		}
		if(key == controlset.get(3)) {
			keybuffer.removeAll(Arrays.asList(controlset.get(3)));
			da = 0;
		}
		if(key == controlset.get(0)) {
			keybuffer.removeAll(Arrays.asList(controlset.get(0)));
			dy = 0;
			dx = 0;
			da = 0;
		}
		if(key == controlset.get(1)) {
			keybuffer.removeAll(Arrays.asList(controlset.get(1)));
			dx = 0;
			dy = 0;
			da = 0;
		}
		if(key == controlset.get(4)) {
			fire();
		}
	}
	
	public void move() {
		
		if(keybuffer.contains(controlset.get(3)))
			da = 1.5*Math.toRadians(this.getROTATION_RAD());
		if(keybuffer.contains(controlset.get(2)))
			da = 1.5*Math.toRadians(-this.getROTATION_RAD());
		if(keybuffer.contains(controlset.get(0))) {
			if(keybuffer.contains(controlset.get(3))) {
				dx = getMOVING_SPEED()*Math.sin(this.angle+da);
				dy = getMOVING_SPEED()*Math.cos(this.angle+da);
				da = Math.toRadians(this.getROTATION_RAD());
			}
			else if(keybuffer.contains(controlset.get(2))) {
				dx = getMOVING_SPEED()*Math.sin(this.angle+da);
				dy = getMOVING_SPEED()*Math.cos(this.angle+da);
				da = Math.toRadians(-this.getROTATION_RAD());
			}
			else {
				dx = getMOVING_SPEED()*Math.sin(this.angle+da);
				dy = getMOVING_SPEED()*Math.cos(this.angle+da);
				da = 0;
			}
		}
		if(keybuffer.contains(controlset.get(1))) {
			if(keybuffer.contains(controlset.get(3))) {
				dx = -getMOVING_SPEED()*Math.sin(this.angle+da);
				dy = -getMOVING_SPEED()*Math.cos(this.angle+da);
				da = Math.toRadians(this.getROTATION_RAD());
			}
			else if(keybuffer.contains(controlset.get(2))) {
				dx = -getMOVING_SPEED()*Math.sin(this.angle+da);
				dy = -getMOVING_SPEED()*Math.cos(this.angle+da);
				da = Math.toRadians(-this.getROTATION_RAD());
			}
			else {
				dx = -getMOVING_SPEED()*Math.sin(this.angle+da);
				dy = -getMOVING_SPEED()*Math.cos(this.angle+da);
				da = 0;
			}
		}
		

		machinegun();//Override if you need machine gun
		
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
	
	public double getAngle() {
		
		return angle;
	}
	
	public int getWidth() {
		
		return w;
	}
	
	public void setWidth(int pw) {
		
		this.w = pw;
	}
	
	public void setHeight(int ph) {
		
		this.h = ph;
	}
	
	public int getHeight() {
		
		return h;
	}
	public Image getImage() {
		
		return image;
	}
	public void setImage(Image pi) {
		this.image = pi;
	}
	
	public List<Shell> getShell() {
		return tankshell;
	}
	
	public void setAmmo(int pa) {
		this.ammo = pa;
	}
	public int getAmmo() {
		return this.ammo;
	}
	
	public List<Integer> getControlSet() {
		return controlset;
	}
	
	public List<Integer> getKeyBuffer(){
		return keybuffer;
	}
	public List<Wall> getWallist(){
		return wallist;
	}
	public void setDX(double d) {
		dx = d;
	}
	public void setDY(double d) {
		dy = d;
	}
	public void setDA(double d) {
		da = d;
	}
	public double getDX() {
		return dx;
	}
	public double getDY() {
		return dy;
	}
	public double getDA() {
		return da;
	}
	public long getCDLast() {
		return CD-System.currentTimeMillis();
	}
	public void setCD(long cd) {
		this.CD = cd;
	}
	public long getCD() {
		return this.CD;
	}
	
	public void fire() {
		if(this.ammo >= 1)
		{
			this.ammo--;
			Shell temp = new Shell(x+(w/2)+((h+4)*Math.sin(angle))/2, y+(h/2)-((h+4)*Math.cos(angle)/2), angle,wallist, getDAMAGE(), getSHELL_SPEED());
			tankshell.add(temp);
			if(this.ammo == 0) {
				CD = System.currentTimeMillis()+getREFILL_CD();
			}
		}
		else if(this.ammo == 0) {
			if(System.currentTimeMillis() >= CD) {
				this.ammo = this.getMAX_AMMO();
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
	}
	public void heal(int a) {
		armor+=a;
	}
	public int getArmor() {
		return armor;
	}
	protected void resetArmor() {
		this.armor = getMAX_ARMOR();
		ammo = getMAX_AMMO();
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
		for(Wall obj: wallist) {
			target = new Rectangle((int)obj.getX(), (int)obj.getY(), (int)obj.getW(),(int)obj.getH());
			if(rr.intersects(target)) {
				return true;
			}
		}
		
		//player collision
		for(Tank obj: playerlist) {
			target = new Rectangle((int)obj.getX(), (int)obj.getY(), obj.getWidth(), obj.getHeight());
			if(rr.intersects(target)) {
				return true;
			}
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
	
	public void machinegun() {
		return;
	}
	

}

