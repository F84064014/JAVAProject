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
 	private List<wall> wallist;
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
		wallist = new ArrayList<wall>();
		setMap("default");
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
	
	public String getName() {
		return name;
	}
	
	public void setMap(String mapname) {
		this.name = mapname;
		wallist.clear();
		kitlist.clear();
		if(mapname.equals("default")) {
			wall w1 = new wall(325,275,"src/resources/bedrock.png");
			wall w2 = new wall(375, 275, "src/resources/bedrock.png");
			wall w3 = new wall(400, 400, "src/resources/bedrock.png");
			wallist.add(w1);
			wallist.add(w2);
			wallist.add(w3);
		}
		if(mapname.equals("test1")) {
			wall w1 = new wall(300, 300, "src/resources/xx.png");
			wallist.add(w1);
		}
		if(mapname.equals("test2")) {
			wall w1 = new wall(400, 388, "src/resources/xx.png");
			wall w2 = new wall(444,333, "src/resources/bedrock.png");
			wallist.add(w1);
			wallist.add(w2);
		}
		
	}
}
