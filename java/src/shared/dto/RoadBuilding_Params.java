package shared.dto;

import com.google.gson.Gson;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

public class RoadBuilding_Params {

	private String type = "Road_Building";
	int playerIndex;
	RoadLocation spot1;
	RoadLocation spot2;
	
	@SuppressWarnings("unused")
	private class RoadLocation {
		private int x;
		private int y;
		private String direction;
		
		public RoadLocation(int x, int y, String direction) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}
	
	public RoadBuilding_Params() {}
	
	public RoadBuilding_Params(int playerIndex, EdgeLocation loc1, EdgeLocation loc2) {
		setPlayerIndex(playerIndex);
		initLocations(loc1, loc2);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	private void initLocations(EdgeLocation loc1, EdgeLocation loc2) {
		int x = loc1.getHexLoc().getX();
		int y = loc1.getHexLoc().getY();
		String dir = setDirection(loc1.getDir());
		spot1 = new RoadLocation(x, y, dir);
		
		x = loc2.getHexLoc().getX();
		y = loc2.getHexLoc().getY();
		dir = setDirection(loc2.getDir()); 
		spot2 = new RoadLocation(x, y, dir);
	}
	
	private String setDirection(EdgeDirection dir) {
		
		switch (dir) {
        	case NorthWest:
        		return "NW";
        	case North:
        		return "N";
        	case NorthEast:
        		return "NE";
        	case SouthEast:
        		return "SE";
        	case South:
        		return "S";
        	case SouthWest:
        		return "SW";
        	default:
        		return null;
		}
	}
	
	public static void main(String[] args) {
		RoadBuilding_Params p = new RoadBuilding_Params(0, new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthEast), new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthEast));
		Gson gson = new Gson();
		System.out.println(gson.toJson(p));
	}
}
