package model.cards;

import java.util.TreeMap;

import shared.definitions.ResourceType;

/**
 * Created by brandt on 1/23/15.
 * <p>
 *     Just a suggestion? It will make resource collection easier perhaps. It can
 *     also go with the CardBank classes as a wrapper for the resources.
 *
 *     I think this will just make the calls really long. If I need to get the cards
 *     for a player, I'd have to call PlayerBank.getResourceSet.getResCards() or something instead
 *     of just PlayerBank.getResCards.
 * </p>
 */
public class ResourceSet {
	/** Map of resource cards */
    protected TreeMap<ResourceType,Integer> resCards = new TreeMap<ResourceType, Integer>();
}
