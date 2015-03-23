package shared.dto;

import java.util.HashMap;
import java.util.Map;
import shared.definitions.ResourceType;

public class DiscardCards_Params {
	
	private String type = "discardCards";
	private int playerIndex;
	@SuppressWarnings("unused")
	private Resources discardedCards;
    private HashMap<ResourceType, Integer> resources;
	
	@SuppressWarnings("unused")
	private class Resources {
		int brick;
		int ore;
		int sheep;
		int wheat;
		int wood;
		
		public Resources(Map<ResourceType, Integer> resources) {
			brick = resources.get(ResourceType.BRICK);
			ore = resources.get(ResourceType.ORE);
			sheep = resources.get(ResourceType.SHEEP);
			wheat = resources.get(ResourceType.WHEAT);
			wood = resources.get(ResourceType.WOOD);
		}
	}

	public DiscardCards_Params() {}

	public DiscardCards_Params(int playerIndex, Map<ResourceType, Integer> resources) {
		super();
		setPlayerIndex(playerIndex);
        this.resources = (HashMap<ResourceType, Integer>)resources;
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

    public HashMap<ResourceType, Integer> getDiscardedCards() { return resources; }

}
