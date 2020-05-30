package game;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Tank1 extends Tank{

	final private int MAX_HEALTH=100;
	final private double ROTATION_RAD = 0.65;
	final private double MOVING_SPEED = 1;
	final private int MAX_AMMO = 5;
	final private int CD_AMMO = 6;
	final private int DAMAGE = 10;
	final private int SHELL_SPEED = 10;
	
	public int getMAX_HEALTH() {
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
	
	public Tank1(int startx, int starty, double startangle, List<wall> wl, String ctrset) {
		super(startx, starty, startangle, wl, ctrset);
		this.wallist = wl;
		loadImage();
		tankshell = new ArrayList<Shell>();
		keybuffer = new ArrayList<Integer>();
		setArmor(this.MAX_HEALTH);
		ammo = MAX_AMMO;
		cdammo = CD_AMMO;
	}
	
	protected void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/tanker.png");
		image = ii.getImage();
		
		w = image.getWidth(null);
		h = image.getHeight(null);
	}
	

}
