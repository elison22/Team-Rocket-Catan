package model.cards;

import shared.definitions.ResourceType;

/**
 * @author Hayden
 * Extension of the CardBank class that holds all of the cards
 * each game will need. Upon creation, the GameBank will hold
 * all resource cards and a list of development cards
 */
public class GameBank extends CardBank{

    /**
     * Initializes map of resource cards to hold 19 of each type
     * and creates all DevCard objects such that there are five
     * of each type ["soldier", "yearOfPlenty", "roadBuilding",
     * "monopoly", "victoryPt"] in the development card List
     */
    public GameBank(){
        //initialize to 19 res cards
        //create all dev cards
    }

    /**
     * Called by the Facade to update the GameBank after polling
     * the server.
     */
    public void updateBank(){
        return;
    }
    /**
     * Used by the Facade to determine if a card can be taken from
     * the GameBank by the Player
     * @param card The type of resource to take
     * @return Whether a card can be taken or not
     */
    public boolean canGiveResCard(ResourceType card){
        //check if there is a card to take
        return true;
    }

    /**
     * Used by the Facade to determine if a development card can be taken
     * from the GameBank by the Player
     * @return Whether a development card can be taken or not
     */
    public boolean canGiveDevCard(){
        //check if there are any
        return true;
    }

    /**
     * Randomly selects a development card from the list.
     * @return DevCard object to be placed in the PlayerBank
     */
    public DevCard giveDevCard(){
        //random dev card
        return null;
    }

}
