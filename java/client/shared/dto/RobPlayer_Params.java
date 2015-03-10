package shared.dto;

import shared.locations.HexLocation;

public class RobPlayer_Params {
	
	private String type = "robPlayer";
	private int playerIndex;
	private int victimIndex;
	@SuppressWarnings("unused")
	private RobberLocation location;
	
	@SuppressWarnings("unused")
	private class RobberLocation {
		private int x;
		private int y;
		
		public RobberLocation(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public RobPlayer_Params() {}
	
	public RobPlayer_Params(int playerIndex, int victimIndex, HexLocation loc) {
		setPlayerIndex(playerIndex);
		setVictimIndex(victimIndex);
		initLocation(loc);
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

	public void setPlayerIndex(int playderIndex) {
		this.playerIndex = playderIndex;
	}

	public int getVictimIndex() {
		return victimIndex;
	}

	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}
	
	private void initLocation(HexLocation loc) {
		int x = loc.getX();
		int y = loc.getY();
		location = new RobberLocation(x, y);
	}
}
