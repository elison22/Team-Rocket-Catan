package model.cards;

import java.util.HashMap;

import shared.definitions.ResourceType;

/**
 * Created by brandt on 1/23/15.
 * 
 *     Just a suggestion? It will make resource collection easier perhaps. It can
 *     also go with the CardBank classes as a wrapper for the resources.
 *
 *     I think this will just make the calls really long. If I need to get the cards
 *     for a player, I'd have to call PlayerBank.getResourceSet.getResCards() or something instead
 *     of just PlayerBank.getResCards.
 * 
 */
public class ResourceSet {
	
    private HashMap<ResourceType,Integer> resCards;
    
    public ResourceSet(int brick, int ore, int sheep, int wheat, int wood) {
    	resCards = new HashMap<ResourceType, Integer>();
    	resCards.put(ResourceType.BRICK, brick);
    	resCards.put(ResourceType.ORE, ore);
    	resCards.put(ResourceType.SHEEP, sheep);
    	resCards.put(ResourceType.WHEAT, wheat);
    	resCards.put(ResourceType.WOOD, wood);
    }
    
    public HashMap<ResourceType, Integer> getResources() {
    	return resCards;
    }
}
