package shared.dto;

import java.util.HashMap;
import java.util.Map;
import shared.definitions.ResourceType;

public class DiscardCards_Params {
	
	private String type = "discardCards";
	private int playerIndex;
	private Resources discardedCards;
	
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

		@Override
		public String toString() {
			return "Resources [brick=" + brick + ", ore=" + ore + ", sheep="
					+ sheep + ", wheat=" + wheat + ", wood=" + wood + "]";
		}
	}

	public DiscardCards_Params() {}

	public DiscardCards_Params(int playerIndex, Map<ResourceType, Integer> resources) {
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

    public HashMap<ResourceType, Integer> getDiscardedCards() { 
    	HashMap<ResourceType, Integer> discardCardsMap = new HashMap<ResourceType, Integer>();
    	discardCardsMap.put(ResourceType.BRICK, discardedCards.brick);
    	discardCardsMap.put(ResourceType.ORE, discardedCards.ore);
    	discardCardsMap.put(ResourceType.SHEEP, discardedCards.sheep);
    	discardCardsMap.put(ResourceType.WHEAT, discardedCards.wheat);
    	discardCardsMap.put(ResourceType.WOOD, discardedCards.wood);
    	return discardCardsMap; 
    }

	@Override
	public String toString() {
		return "DiscardCards_Params [type=" + type + ", playerIndex="
				+ playerIndex + ", discardedCards=" + discardedCards + "]";
	}

}
