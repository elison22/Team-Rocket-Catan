package shared.dto;

public class AcceptTrade_Params {
	
	private String type = "acceptTrade";
	private int playerIndex;
	private boolean willAccept;

	public AcceptTrade_Params() {}

	public AcceptTrade_Params(int playerIndex, boolean willAccept) {
		super();
		setPlayerIndex(playerIndex);
		setWillAccept(willAccept);
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

	public boolean isWillAccept() {
		return willAccept;
	}

	public void setWillAccept(boolean willAccept) {
		this.willAccept = willAccept;
	}

}
