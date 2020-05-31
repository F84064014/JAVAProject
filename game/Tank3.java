package game;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

public class Tank3 extends Tank{

	final private int MAX_HEALTH= 200;
	final private double ROTATION_RAD = 0.65;
	final private double MOVING_SPEED = 1.3;
	final private int MAX_AMMO = 100;
	final private int CD_AMMO = 10;
	final private int DAMAGE = 1;
	final private int SHELL_SPEED = 13;
	private long refillCD;
	
	public int getMAX_ARMOR() {
		return this.MAX_HEALTH;
	}
	public double getROTATION_RAD() {
		return this.ROTATION_RAD;
	}
	public double getMOVING_SPEED() {
		return this.MOVING_SPEED;
	}
	public int getMAX_AMMO() {
		return this.MAX_AMMO;
	}
	public int getCD_AMMO() {
		return this.CD_AMMO;
	}
	public int getDAMAGE() {
		return this.DAMAGE;
	}
	public int getSHELL_SPEED() {
		return this.SHELL_SPEED;
	}
	
	public Tank3(int startx, int starty, double startangle, List<wall> wl,List<Tank> pl, String ctrset) {
		super(startx, starty, startangle, wl, pl, ctrset);
		loadImage();
		resetArmor();
		setAmmo(this.MAX_AMMO);
		setCDAmmo(this.CD_AMMO);
	}
	
	
	protected void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/MGtanker.png");
		setImage(ii.getImage());
		setWidth(this.getImage().getWidth(null));
		setHeight(this.getImage().getHeight(null));
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == getControlSet().get(2)) 
			getKeyBuffer().add(getControlSet().get(2));
		if(key == getControlSet().get(3))
			getKeyBuffer().add(getControlSet().get(3));
		if(key == getControlSet().get(0))
			getKeyBuffer().add(getControlSet().get(0));
		if(key == getControlSet().get(1))
			getKeyBuffer().add(getControlSet().get(1));
		if(key == getControlSet().get(4))
			getKeyBuffer().add(getControlSet().get(4));
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == getControlSet().get(2)) {
			getKeyBuffer().removeAll(Arrays.asList(getControlSet().get(2)));
			setDA(0);
		}
		if(key == getControlSet().get(3)) {
			getKeyBuffer().removeAll(Arrays.asList(getControlSet().get(3)));
			setDA(0);
		}
		if(key == getControlSet().get(0)) {
			getKeyBuffer().removeAll(Arrays.asList(getControlSet().get(0)));
			setDX(0);
			setDY(0);
			setDA(0);
		}
		if(key == getControlSet().get(1)) {
			getKeyBuffer().removeAll(Arrays.asList(getControlSet().get(1)));
			setDX(0);
			setDY(0);
			setDA(0);
		}
		if(key == getControlSet().get(4)) {
			getKeyBuffer().removeAll(Arrays.asList(getControlSet().get(4)));
		}
	}
	
	@Override
	public void machinegun() {
		if(getKeyBuffer().contains(getControlSet().get(4))) {
			if(getAmmo() > 0) {
				setDX(0.1*getDX());
				setDY(0.1*getDY());
				setDA(0.1*getDA());
			}
			for(int i=0;i<2;i++)
				fire();
		}
	}
	
	@Override
	public void fire() {
		if(getAmmo() >= 1)
		{
			setAmmo(getAmmo()-1);
			double startangle = getAngle();
			startangle+=(2*Math.random()-1)/10; //math.random=>between 1 and 0
			Shell temp = new Shell(getX()+(getWidth()/2)+((getHeight()+4)*Math.sin(startangle))/2, getY()+(getHeight()/2)-((getHeight()+4)*Math.cos(startangle)/2), startangle,getWallist(), getDAMAGE(), getSHELL_SPEED());
			getShell().add(temp);
			if(getAmmo()==0)
				refillCD = 5000+System.currentTimeMillis();
		}
		else if(getAmmo()==0) {
			if(System.currentTimeMillis() >= refillCD) {
				setAmmo(this.MAX_AMMO);
			}
		}

	}
	

	
}
