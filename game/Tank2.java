package game;

import java.util.List;

import javax.swing.ImageIcon;

public class Tank2 extends Tank{
	
	final private int MAX_HEALTH= 200;
	final private double ROTATION_RAD = 0.65;
	final private double MOVING_SPEED = 0.5;
	final private int MAX_AMMO = 1;
	final private int CD_AMMO = 3;
	final private int DAMAGE = 20;
	final private int SHELL_SPEED = 13;
	
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
	
	public Tank2(int startx, int starty, double startangle, List<wall> wl,List<Tank> pl, String ctrset) {
		super(startx, starty, startangle, wl, pl, ctrset);
		loadImage();
		resetArmor();
		setAmmo(this.MAX_AMMO);
		setCDAmmo(this.CD_AMMO);
	}
	
	@Override
	protected void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/heavytank.png");
		setImage(ii.getImage());
		setWidth(this.getImage().getWidth(null));
		setHeight(this.getImage().getHeight(null));
	}
}
