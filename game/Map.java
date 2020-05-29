package game;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.*;

public abstract class Map {
	
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	protected List<kit> k;
 	protected List<wall> wallist;
	protected Image image;
	public abstract void setMap();
	
	//******
	public void makekit() {}
	//******
	
	public Map() {
		loadImage();
		x = 400;
		y = 300;
		k = new ArrayList<kit>();
		wallist = new ArrayList<wall>();
	}
	
	protected void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/Xi.png");
		image = ii.getImage();
		
		w = image.getWidth(null);
		h = image.getHeight(null);
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public List<wall> getwall(){
		return wallist;
	}
}
