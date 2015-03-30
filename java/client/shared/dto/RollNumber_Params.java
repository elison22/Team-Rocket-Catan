package shared.dto;

public class RollNumber_Params {
	
	private String type = "rollNumber";
	private int playerIndex;
	private int number;

	public RollNumber_Params() {}

	public RollNumber_Params(int playerIndex, int number) {
		super();
		setPlayerIndex(playerIndex);
		setNumber(number);
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
