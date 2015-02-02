package model.cards;

import shared.definitions.DevCardType;
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
        resCards.put(ResourceType.BRICK, 19);
        resCards.put(ResourceType.WOOD, 19);
        resCards.put(ResourceType.WHEAT, 19);
        resCards.put(ResourceType.SHEEP, 19);
        resCards.put(ResourceType.ORE, 19);
        initDevCards();
    }

    public void initDevCards(){
        //initialize monument cards
        devCards.add(new DevCard(DevCardType.MONUMENT, "Library"));
        devCards.add(new DevCard(DevCardType.MONUMENT, "University"));
        devCards.add(new DevCard(DevCardType.MONUMENT, "Market"));
        devCards.add(new DevCard(DevCardType.MONUMENT, "Chapel"));
        devCards.add(new DevCard(DevCardType.MONUMENT, "Palace"));

        //initialize soldier cards
        for(int i = 0; i < 14; i++){
            devCards.add(new DevCard(DevCardType.SOLDIER, "Soldier"));
        }

        //initialize progress cards
        for(int i = 0; i < 2; i++){
            devCards.add(new DevCard(DevCardType.MONOPOLY, "Monopoly"));
            devCards.add(new DevCard(DevCardType.ROAD_BUILD, "Road Building"));
            devCards.add(new DevCard(DevCardType.YEAR_OF_PLENTY, "Year of Plenty"));
        }
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
        //check if there are any dev cards to give
        return devCards.size() > 0;
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
