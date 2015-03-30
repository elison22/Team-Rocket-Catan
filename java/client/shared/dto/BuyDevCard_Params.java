package shared.dto;

public class BuyDevCard_Params {
	
	private String type = "buyDevCard";
	private int playerIndex;

	public BuyDevCard_Params() {}

	public BuyDevCard_Params(int playerIndex) {
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
