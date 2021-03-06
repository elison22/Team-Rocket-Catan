package model.scards;

import shared.definitions.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Hayden
 * CardBank is the super class of GameBank and PlayerBank and contains methods that both
 * subclasses will use. This class contains a map of resource cards with the resource type
 * as the key mapped to an integer. This integer will maintain how many cards are
 * available of each resource type and will be incremented and decremented as resources are
 * given to and received from players. Since development cards are only given to players and never
 * taken back by the bank, there is a List that contains all of the development cards that will
 * be used each game.
 */
public class ServerCardBank {

    /** Map of resource cards */
    protected HashMap<ResourceType,Integer> resCards = new HashMap<ResourceType, Integer>(); //Changed to HashMap because they're more efficient for inserting and removing which we'll be doing a lot
    /** List of development cards */
    protected ArrayList<ServerDevCard> devCards = new ArrayList<ServerDevCard>();
    protected ArrayList<ServerDevCard> oldDevs = new ArrayList<>();
    protected ArrayList<ServerDevCard> newDevs = new ArrayList<>();
    protected Random rand;

    /**
     * Used to give a resource card to a player and decrements the total number of available
     * resource cards of that type.
     * @param card The type of resource card to be given to the player
     * @return Whether a card was given to the player or not.
     */
    public boolean removeResourceCard(ResourceType card){
    	if(canRemoveResource(card))
    	{
    		int resourceCount = resCards.get(card);
    		resourceCount--;
    		resCards.put(card, resourceCount);
    		return true;
    	}
    	else return false;
    }
    
    public boolean canRemoveResource(ResourceType card)
    {
    	if(resCards.get(card) > 0)
    		return true;
    	else return false;
    }

    /**
     * Used by players to take cards from the game bank and used by the game bank to take
     * cards back from players when they've used them.
     * @param card The type of resource taken.
     * @return Whether a card was taken or not.
     */
    public void receiveResourceCard(ResourceType card){
    	int resourceCount = resCards.get(card);
		resourceCount++;
		resCards.put(card, resourceCount);
    }

    public HashMap<ResourceType, Integer> getResCards()
    {
        return resCards;
    }

    public ArrayList<ServerDevCard> getDevCards()
    {
        return devCards;
    }

}
