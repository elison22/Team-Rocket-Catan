package model.cards;

import shared.definitions.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

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
public class CardBank {

    /** Map of resource cards */
    protected HashMap<ResourceType,Integer> resCards = new HashMap<ResourceType, Integer>(); //Changed to HashMap because they're more efficient for inserting and removing which we'll be doing a lot
    /** List of development cards */
    protected ArrayList<DevCard> devCards = new ArrayList<DevCard>();
    protected ArrayList<DevCard> oldDevs = new ArrayList<>();
    protected ArrayList<DevCard> newDevs = new ArrayList<>();
    
    /**
     * Used to give a resource card to a player and decrements the total number of available
     * resource cards of that type.
     * @param card The type of resource card to be given to the player
     * @return Whether a card was given to the player or not.
     */
    public boolean giveCard(ResourceType card){
        //decrement the count for that resource
        return true;
    }

    /**
     * Used by players to take cards from the game bank and used by the game bank to take
     * cards back from players when they've used them.
     * @param card The type of resource taken.
     * @return Whether a card was taken or not.
     */
    public boolean takeCard(ResourceType card){
        //increment the count for that resource
        return true;
    }

}
