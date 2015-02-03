package shared.dto;

import com.google.gson.Gson;
import shared.locations.EdgeLocation;

public class BuildRoad_Params {
	
	private String type = "buildRoad";
	private int playerindex;
	private Location roadLocation;
	private boolean free;
	
	private class Location {
		int x;
		int y;
		String direction;
		
		public Location(int x, int y, String direction) {
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
		this.setType(type);
		this.playerindex = playerindex;
		initLocation(roadLocation);
		this.setFree(free);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerindex() {
		return playerindex;
	}

	public void setPlayerindex(int playerindex) {
		this.playerindex = playerindex;
	}

	public Location getRoadLocation() {
		return roadLocation;
	}

	public void setRoadLocation(int x, int y, String direction) {
		roadLocation = new Location(x, y, direction);
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
		String direction = edge.getDir().toString();
		roadLocation = new Location(x, y, direction);
	}

	public static void main(String[] args) {
		BuildRoad_Params p = new BuildRoad_Params();
		p.setPlayerindex(0);
		p.setRoadLocation(0,0,"NORTH");
		p.setFree(true);
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(p));
	}
}
