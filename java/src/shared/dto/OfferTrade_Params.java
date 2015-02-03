package shared.dto;

public class OfferTrade_Params {
	
	private String type = "offerTrade";
	private int playerIndex;
	@SuppressWarnings("unused")
	private ResourceTrade offer;
	private int receiver;
	
	@SuppressWarnings("unused")
	private class ResourceTrade {
		int brick;
		int ore;
		int sheep;
		int wheat;
		int wood;
		
		public ResourceTrade(int[] resources) {
			brick = resources[0];
			ore = resources[1];
			sheep = resources[2];
			wheat = resources[3];
			wood = resources[4];
		}
	}

	public OfferTrade_Params() {}

	public OfferTrade_Params(int playerIndex, int[] resources, int receiver) {
		super();
		setPlayerIndex(playerIndex);
		offer = new ResourceTrade(resources);
		setReceiver(receiver);
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

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

}
