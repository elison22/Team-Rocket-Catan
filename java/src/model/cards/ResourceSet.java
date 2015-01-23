package model.cards;

import java.util.TreeMap;

import shared.definitions.ResourceType;

/**
 * Created by brandt on 1/23/15.
 * <p>
 *     Just a suggestion? It will make resource collection easier perhaps. It can
 *     also go with the CardBank classes as a wrapper for the resources.
 * </p>
 */
public class ResourceSet {
	/** Map of resource cards */
    protected TreeMap<ResourceType,Integer> resCards = new TreeMap<ResourceType, Integer>();
}
