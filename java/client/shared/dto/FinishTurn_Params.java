package shared.dto;

public class FinishTurn_Params {
	
	private String type = "finishTurn";
	private int playerIndex;

	public FinishTurn_Params() {}

	public FinishTurn_Params(int playerIndex) {
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
