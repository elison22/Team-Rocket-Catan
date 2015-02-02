package model.cards;

import java.util.ArrayList;
import java.util.HashMap;

import shared.definitions.ResourceType;

/**
 * @author Hayden
 * Extension of the CardBank class that holds all of the cards
 * each player has in their hand. Upon creation, the PlayerBank will
 * be empty.
 */
public class PlayerBank extends CardBank{

    /**
     * Creates a PlayerBank object and initializes the player's
     * held resources to zero
     */
    public PlayerBank(){
        resCards.put(ResourceType.BRICK, 0);
        resCards.put(ResourceType.WOOD, 0);
        resCards.put(ResourceType.WHEAT, 0);
        resCards.put(ResourceType.SHEEP, 0);
        resCards.put(ResourceType.ORE, 0);
    }

    /**
     * Used to take a development card from the GameBank and add it
     * to the player's list of development cards
     * @return True if the player successfully took a DevCard
     */
    public boolean takeDevCard(){
        //take from the bank
        //add to card list
        return true;
    }

    public boolean canAffordDevCard() {
    	return resCards.get(ResourceType.ORE) >= 1 && resCards.get(ResourceType.SHEEP) >= 1 && resCards.get(ResourceType.WHEAT) >= 1;
    }

    public boolean canAffordRoad() {
        return resCards.get(ResourceType.BRICK) >= 1 && resCards.get(ResourceType.WOOD) >= 1;
    }

    public boolean canAffordSettlement() {
        return resCards.get(ResourceType.BRICK) >= 1 && resCards.get(ResourceType.WOOD) >= 1 && resCards.get(ResourceType.SHEEP) >= 1 && resCards.get(ResourceType.WHEAT) >= 1;
    }

    public boolean canAffordCity() {
        return resCards.get(ResourceType.ORE) >= 3 && resCards.get(ResourceType.WHEAT) >= 2;
    }

    public HashMap<ResourceType, Integer> getResCards(){
        return this.resCards;
    }
    
    public ArrayList<DevCard> getDevCards() {
    	return devCards;
    }

    public boolean hasResCards(HashMap<ResourceType, Integer> resources){
        for (ResourceType key : resources.keySet()){
            if(resources.get(key) > this.resCards.get(key)){
                return false;
            }
        }
        return true;
    }
}
