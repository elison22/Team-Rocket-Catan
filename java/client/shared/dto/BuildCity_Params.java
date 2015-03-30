package shared.dto;

import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class BuildCity_Params {
	
	private String type = "buildCity";
	private int playerIndex;
	@SuppressWarnings("unused")
	private Vertex vertexLocation;
	
	@SuppressWarnings("unused")
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

	public BuildCity_Params() {}

	public BuildCity_Params(int playerIndex, VertexLocation loc) {
		super();
		setPlayerIndex(playerIndex);
		initVertex(loc);
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
}
