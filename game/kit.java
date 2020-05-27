package game;

import java.awt.Image;

public class kit {

	private double x;
	private double y;
	private double w;
	private double h;
	private int inc_health;
	private Image image;
	
	public kit(int init_x, int init_y) {
		x = init_x;
		y = init_y;
	}
	
	public int get_inc_health() {
		return inc_health;
	}
	public int right()
	{
		return (int)(x + w);
	}
}
