package shared.dto;

import java.util.HashMap;
import java.util.Map;

import shared.definitions.ResourceType;

public class OfferTrade_Params {
	
	private String type = "offerTrade";
	private int playerIndex;
	@SuppressWarnings("unused")
	private ResourceTrade offer;
	private int receiver;
    private HashMap<ResourceType, Integer> offeredRes;
	
	@SuppressWarnings("unused")
	private class ResourceTrade {
		int brick;
		int ore;
		int sheep;
		int wheat;
		int wood;
		
		public ResourceTrade(Map<ResourceType, Integer> resources) {
			brick = resources.get(ResourceType.BRICK);
			ore = resources.get(ResourceType.ORE);
			sheep = resources.get(ResourceType.SHEEP);
			wheat = resources.get(ResourceType.WHEAT);
			wood = resources.get(ResourceType.WOOD);
		}
	}

	public OfferTrade_Params() {}

	public OfferTrade_Params(int playerIndex, Map<ResourceType, Integer> resources, int receiver) {
		super();
		setPlayerIndex(playerIndex);
        offeredRes = (HashMap<ResourceType, Integer>) resources;
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

    public HashMap<ResourceType, Integer> getOfferedResources() { return offeredRes; }

}
