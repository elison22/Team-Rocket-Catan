package shared.dto;

import shared.locations.HexLocation;

public class Soldier_Params {
	
	private String type = "Soldier";
	private int playerIndex;
	private int victimIndex;
	Location location;
	
	@SuppressWarnings("unused")
	private class Location {
		int x;
		int y;
		
		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public Soldier_Params() {}
	
	public Soldier_Params(int playerIndex, int victimIndex, HexLocation loc) {
		setPlayerIndex(playerIndex);
		setVictimIndex(victimIndex);
		location = new Location(loc.getX(), loc.getY());
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

	public int getVictimIndex() {
		return victimIndex;
	}

	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}

    public HexLocation getLocation() {
        return new HexLocation(location.x, location.y);
    }

}
