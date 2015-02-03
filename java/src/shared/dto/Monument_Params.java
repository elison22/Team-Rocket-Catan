package shared.dto;

public class Monument_Params {
	
	String type = "Monument";
	int playerIndex;

	public Monument_Params() {}

	public Monument_Params(int playerIndex) {
		super();
		setPlayerIndex(playerIndex);
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
	
	

}
