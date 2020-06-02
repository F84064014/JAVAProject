package game;

import java.util.List;

import javax.swing.ImageIcon;

public class Tank1 extends Tank{

	final private int MAX_HEALTH=1200;
	final private double ROTATION_RAD = 0.65;
	final private double MOVING_SPEED = 1;
	final private int MAX_AMMO = 5;
	final private int CD_AMMO = 6;
	final private int DAMAGE = 250;
	final private int SHELL_SPEED = 10;
	final private long REFILL_CD = 5000;
	
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
	public long getREFILL_CD() {
		return this.REFILL_CD;
	}
	
	public Tank1(int startx, int starty, double startangle, List<Wall> wl, List<Tank> pl, String ctrset) {
		super(startx, starty, startangle, wl, pl, ctrset);
		loadImage();
		resetArmor();
		setAmmo(this.MAX_AMMO);
	}
	
	protected void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/tanker.png");
		setImage(ii.getImage());	
		setWidth(this.getImage().getWidth(null));
		setHeight(this.getImage().getHeight(null));
	}
	

}
