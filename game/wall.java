package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class wall {
	
	private double x;
	private double y;
	private Image image;
	private double w;
	private double h;
	
	public wall(double wallx, double wally, String filepath) {
		
		ImageIcon ii = new ImageIcon(filepath);
		image = ii.getImage();
	
		x = wallx;
		y = wally;
		w = image.getWidth(null);
		h = image.getHeight(null);
	
		
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getW() {
		return w;
	}
	public double getH() {
		return h;
	}
	public Image getImage() {
		return image;
	}
	public int right()
	{
		return (int)(x + w);
	}
	public int bottom()
	{
		return (int)(y+h);
	}
}
