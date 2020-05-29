package game;

public class Map_basic extends Map {
	
	public Map_basic(){
		super();
		setMap();
	}
	
	public void setMap() {
		wall w1 = new wall(325,275,"src/resources/bedrock.png");
		wall w2 = new wall(375, 275, "src/resources/bedrock.png");
		wall w3 = new wall(400, 400, "src/resources/bedrock.png");
		wallist.add(w1);
		wallist.add(w2);
		wallist.add(w3);
	}

}
