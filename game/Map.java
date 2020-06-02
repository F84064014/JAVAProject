package game;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.*;

public class Map {
	
	private int x;
	private int y;
	private int w;
	private int h;
	private List<kit> kitlist;
 	private List<Wall> wallist;
 	private Image image;
	private String name;

	
	//******
	public void makekit() {}
	//******
	
	public Map() {
		loadImage();
		x = 400;
		y = 300;
		kitlist = new ArrayList<kit>();
		wallist = new ArrayList<Wall>();
		setMap("default");
	}
	
	protected void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/grass.png");
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
	
	public List<Wall> getwall(){
		return wallist;
	}
	
	public String getName() {
		return name;
	}
	
	public void setMap(String mapname) {
		this.name = mapname;
		wallist.clear();
		kitlist.clear();
		if(mapname.equals("default")) {
			Wall w1 = new Wall(325,275,"src/resources/bedrock.png");
			Wall w2 = new Wall(375, 275, "src/resources/bedrock.png");
			Wall w3 = new Wall(400, 400, "src/resources/bedrock.png");
			wallist.add(w1);
			wallist.add(w2);
			wallist.add(w3);
		}
		if(mapname.equals("test1")) {
			Wall w1 = new Wall(300, 300, "src/resources/xx.png");
			wallist.add(w1);
		}
		if(mapname.equals("test2")) {
			Wall w1 = new Wall(400, 388, "src/resources/xx.png");
			Wall w2 = new Wall(444,333, "src/resources/bedrock.png");
			wallist.add(w1);
			wallist.add(w2);
		}
		
	}
}
