package shared.dto;

public class SendChat_Params {
	
	private String type = "sendChat";
	private int playerIndex;
	private String content;

	public SendChat_Params() {}

	public SendChat_Params(int playerIndex, String content) {
		super();
		setPlayerIndex(playerIndex);
		setContent(content);
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
