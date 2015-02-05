package shared.dto;

public class YearOfPlenty_Params {
	
	private String type = "Year_of_Plenty";
	private int playerIndex;
	private String resource1;
	private String resource2;

	public YearOfPlenty_Params() {}

	public YearOfPlenty_Params(int playerIndex, String resource1,
			String resource2) {
		super();
		setPlayerIndex(playerIndex);
		setResource1(resource1);
		setResource2(resource2);
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

	public String getResource1() {
		return resource1;
	}

	public void setResource1(String resource1) {
		this.resource1 = resource1.toLowerCase();
	}

	public String getResource2() {
		return resource2;
	}

	public void setResource2(String resource2) {
		this.resource2 = resource2.toLowerCase();
	}
}
