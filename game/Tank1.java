package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

public class Tank1 extends Tank{

	public Tank1(int startx, int starty, double startangle, List<wall> wl) {
		super(startx, starty, startangle, wl);
		this.wallist = wl;
		loadImage();
		tankshell = new ArrayList<Shell>();
		keybuffer = new ArrayList<Integer>();
		ammo = MAX_AMMO;
		cdammo = CD_AMMO;
	}
	
	protected void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/tanker.png");
		image = ii.getImage();
		
		w = image.getWidth(null);
		h = image.getHeight(null);
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) 
			keybuffer.add(KeyEvent.VK_LEFT);
		if(key == KeyEvent.VK_RIGHT)
			keybuffer.add(KeyEvent.VK_RIGHT);
		if(key == KeyEvent.VK_UP)
			keybuffer.add(KeyEvent.VK_UP);
		if(key == KeyEvent.VK_DOWN)
			keybuffer.add(KeyEvent.VK_DOWN);
		
		
		if(keybuffer.contains(KeyEvent.VK_RIGHT))
			da = 3*Math.toRadians(this.ROTATION_RAD);
		if(keybuffer.contains(KeyEvent.VK_LEFT))
			da = 3*Math.toRadians(-this.ROTATION_RAD);
		if(keybuffer.contains(KeyEvent.VK_UP)) {
			if(keybuffer.contains(KeyEvent.VK_RIGHT)) {
				dx = MOVING_SPEED*Math.sin(this.angle+da);
				dy = MOVING_SPEED*Math.cos(this.angle+da);
				da = Math.toRadians(this.ROTATION_RAD);

			}
			else if(keybuffer.contains(KeyEvent.VK_LEFT)) {
				dx = MOVING_SPEED*Math.sin(this.angle+da);
				dy = MOVING_SPEED*Math.cos(this.angle+da);
				da = Math.toRadians(-this.ROTATION_RAD);
			}
			else {
				dx = MOVING_SPEED*Math.sin(this.angle+da);
				dy = MOVING_SPEED*Math.cos(this.angle+da);
				da = 0;
			}
		}
		if(keybuffer.contains(KeyEvent.VK_DOWN)) {
			if(keybuffer.contains(KeyEvent.VK_RIGHT)) {
				dx = -MOVING_SPEED*Math.sin(this.angle+da);
				dy = -MOVING_SPEED*Math.cos(this.angle+da);
				da = Math.toRadians(this.ROTATION_RAD);
			}
			else if(keybuffer.contains(KeyEvent.VK_LEFT)) {
				dx = -MOVING_SPEED*Math.sin(this.angle+da);
				dy = -MOVING_SPEED*Math.cos(this.angle+da);
				da = Math.toRadians(-this.ROTATION_RAD);
			}
			else {
				dx = -MOVING_SPEED*Math.sin(this.angle+da);
				dy = -MOVING_SPEED*Math.cos(this.angle+da);
				da = 0;
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			keybuffer.removeAll(Arrays.asList(KeyEvent.VK_LEFT));
			da = 0;
		}
		if(key == KeyEvent.VK_RIGHT) {
			keybuffer.removeAll(Arrays.asList(KeyEvent.VK_RIGHT));
			da = 0;
		}
		if(key == KeyEvent.VK_UP) {
			keybuffer.removeAll(Arrays.asList(KeyEvent.VK_UP));
			dy = 0;
			dx = 0;
			da = 0;
		}
		if(key == KeyEvent.VK_DOWN) {
			keybuffer.removeAll(Arrays.asList(KeyEvent.VK_DOWN));
			dx = 0;
			dy = 0;
			da = 0;
		}
		if(key == KeyEvent.VK_SPACE) {
			fire();
		}
	}
}
