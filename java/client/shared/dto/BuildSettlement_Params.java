package shared.dto;

import com.google.gson.Gson;

import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class BuildSettlement_Params {
	
	private String type = "buildSettlement";
	private int playerIndex;
	private Vertex vertexLocation;
	boolean free;
	
	private class Vertex {
		int x;
		int y;
		String direction;
		
		public Vertex(int x, int y, String direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	public BuildSettlement_Params() {}

	public BuildSettlement_Params(int playerIndex, VertexLocation loc, boolean free) {
		super();
		setPlayerIndex(playerIndex);
		initVertex(loc);
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

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
	public int getVertexX() {
		return vertexLocation.x;
	}
	
	public int getVertexY() {
		return vertexLocation.y;
	}
	
	public String getVertexDir() {
		return vertexLocation.direction;
	}

	private void initVertex(VertexLocation loc) {
		int x = loc.getHexLoc().getX();
		int y = loc.getHexLoc().getY();
		String dir = setDirection(loc.getDir());
		vertexLocation = new Vertex(x, y, dir);
	}
	
	private String setDirection(VertexDirection vertexDirection) {
		
		switch (vertexDirection) {
			case West:
				return "W";
        	case NorthWest:
        		return "NW";
        	case NorthEast:
        		return "NE";
        	case East:
        		return "E";
        	case SouthEast:
        		return "SE";
        	case SouthWest:
        		return "SW";
        	default:
        		return null;
		}
	}

	public static void main(String[] args) {
		VertexLocation vertex = new VertexLocation(new HexLocation(-1, -1), VertexDirection.NorthEast);
		BuildSettlement_Params p = new BuildSettlement_Params(2, vertex, true);
		
		Gson gson = new Gson();
		
		System.out.println(gson.toJson(p));
	}
}
