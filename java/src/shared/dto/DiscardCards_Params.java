package shared.dto;

import model.cards.ResourceSet;

public class DiscardCards_Params {
	
	private String type = "discardCards";
	private int playerIndex;
	@SuppressWarnings("unused")
	private Resources discardedCards;
	
	@SuppressWarnings("unused")
	private class Resources {
		int brick;
		int ore;
		int sheep;
		int wheat;
		int wood;
		
		public Resources(int[] resources) {
			brick = resources[0];
			ore = resources[1];
			sheep = resources[2];
			wheat = resources[3];
			wood = resources[4];
		}
	}

	public DiscardCards_Params() {}

	public DiscardCards_Params(int playerIndex, ResourceSet resources) {
		super();
		setPlayerIndex(playerIndex);
		discardedCards = new Resources(resources);
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
