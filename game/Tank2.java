package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

public class Tank2 extends Tank{
	
	public Tank2(int startx, int starty, double startangle, List<wall> wl) {
		super(startx, starty, startangle, wl);
		this.wallist = wl;
		loadImage();
		tankshell = new ArrayList<Shell>();
		keybuffer = new ArrayList<Integer>();
		ammo = MAX_AMMO;
		cdammo = CD_AMMO;
	}
	
	@Override
	protected void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/tanker2.png");
		image = ii.getImage();
		
		w = image.getWidth(null);
		h = image.getHeight(null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A) 
			keybuffer.add(KeyEvent.VK_A);
		if(key == KeyEvent.VK_D)
			keybuffer.add(KeyEvent.VK_D);
		if(key == KeyEvent.VK_W)
			keybuffer.add(KeyEvent.VK_W);
		if(key == KeyEvent.VK_S)
			keybuffer.add(KeyEvent.VK_S);
		
		
		if(keybuffer.contains(KeyEvent.VK_D))
			da = Math.toRadians(this.ROTATION_RAD);
		if(keybuffer.contains(KeyEvent.VK_A))
			da = Math.toRadians(-this.ROTATION_RAD);
		if(keybuffer.contains(KeyEvent.VK_W)) {
			if(keybuffer.contains(KeyEvent.VK_D)) {
				dx = MOVING_SPEED*Math.sin(this.angle+da);
				dy = MOVING_SPEED*Math.cos(this.angle+da);
				da = Math.toRadians(this.ROTATION_RAD);

			}
			else if(keybuffer.contains(KeyEvent.VK_A)) {
				dx = MOVING_SPEED*Math.sin(this.angle+da);
				dy = MOVING_SPEED*Math.cos(this.angle+da);
				da = Math.toRadians(-this.ROTATION_RAD);
			}
			else {
				dx = MOVING_SPEED*Math.sin(this.angle+da);
				dy = MOVING_SPEED*Math.cos(this.angle+da);
				da = 0;
			}
			if(detectCollision(wallist))
			{
				System.out.println("BOOM");
			}
		}
		if(keybuffer.contains(KeyEvent.VK_S)) {
			if(keybuffer.contains(KeyEvent.VK_D)) {
				dx = -MOVING_SPEED*Math.sin(this.angle+da);
				dy = -MOVING_SPEED*Math.cos(this.angle+da);
				da = Math.toRadians(this.ROTATION_RAD);
			}
			else if(keybuffer.contains(KeyEvent.VK_A)) {
				dx = -MOVING_SPEED*Math.sin(this.angle+da);
				dy = -MOVING_SPEED*Math.cos(this.angle+da);
				da = Math.toRadians(-this.ROTATION_RAD);
			}
			else {
				dx = -MOVING_SPEED*Math.sin(this.angle+da);
				dy = -MOVING_SPEED*Math.cos(this.angle+da);
				da = 0;
			}
			if(detectCollision(wallist))
			{
				System.out.println("BOOM");
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A) {
			keybuffer.removeAll(Arrays.asList(KeyEvent.VK_A));
			da = 0;
		}
		if(key == KeyEvent.VK_D) {
			keybuffer.removeAll(Arrays.asList(KeyEvent.VK_D));
			da = 0;
		}
		if(key == KeyEvent.VK_W) {
			keybuffer.removeAll(Arrays.asList(KeyEvent.VK_W));
			dy = 0;
			dx = 0;
			da = 0;
		}
		if(key == KeyEvent.VK_S) {
			keybuffer.removeAll(Arrays.asList(KeyEvent.VK_S));
			dx = 0;
			dy = 0;
			da = 0;
		}
		if(key == KeyEvent.VK_F) {
			fire();
		}
	}
}
