package shared.dto;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;

public class BuildRoad_Params {
	
	private String type = "buildRoad";
	private int playerIndex;
	private RoadLocation roadLocation;
	
	/**Whether or not the road is free (i.e. during initial placement phase)*/
	private boolean free;
	
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

	public BuildRoad_Params() {}

	public BuildRoad_Params(String type, int playerindex,
			EdgeLocation roadLocation, boolean free) {
		super();
		setType(type);
		setPlayerIndex(playerindex);
		initLocation(roadLocation);
		setFree(free);
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

	public void setPlayerIndex(int playerindex) {
		this.playerIndex = playerindex;
	}

	public RoadLocation getRoadLocation() {
		return roadLocation;
	}

	public void setRoadLocation(int x, int y, String direction) {
		roadLocation = new RoadLocation(x, y, direction);
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
	private void initLocation(EdgeLocation edge) {
		int x = edge.getHexLoc().getX();
		int y = edge.getHexLoc().getY();
		String dir = setDirection(edge.getDir());
		roadLocation = new RoadLocation(x, y, dir);
	}
	
	private String setDirection(EdgeDirection dir) {
		
		switch (dir) {
        	case NorthWest:
        		return "NE";
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
}
