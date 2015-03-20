package model.scards;

import serializer.json.JsonDevCardList;
import serializer.json.JsonResourceList;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;

/**
 * @author Hayden
 * Extension of the CardBank class that holds all of the cards
 * each game will need. Upon creation, the GameBank will hold
 * all resource cards and a list of development cards
 */
public class ServerGameBank extends ServerCardBank {


    /**
     * Initializes map of resource cards to hold 19 of each type
     * and creates all DevCard objects such that there are five
     * of each type ["soldier", "yearOfPlenty", "roadBuilding",
     * "monopoly", "victoryPt"] in the development card List
     */
    public ServerGameBank(){
        resCards.put(ResourceType.BRICK, 19);
        resCards.put(ResourceType.WOOD, 19);
        resCards.put(ResourceType.WHEAT, 19);
        resCards.put(ResourceType.SHEEP, 19);
        resCards.put(ResourceType.ORE, 19);
        initDevCards();
    }

    public ServerGameBank(JsonResourceList resources, JsonDevCardList jsonDevCards)
    {
        resCards.put(ResourceType.BRICK, resources.getBrick());
        resCards.put(ResourceType.WOOD, resources.getWood());
        resCards.put(ResourceType.WHEAT, resources.getWheat());
        resCards.put(ResourceType.SHEEP, resources.getSheep());
        resCards.put(ResourceType.ORE, resources.getOre());
        devCards.clear();
        for(int i = 0; i < jsonDevCards.getMonopoly(); i++)
            devCards.add(new ServerDevCard(DevCardType.MONOPOLY));
        for(int i = 0; i < jsonDevCards.getMonument(); i++)
            devCards.add(new ServerDevCard(DevCardType.MONUMENT));
        for(int i = 0; i < jsonDevCards.getRoadBuilding(); i++)
            devCards.add(new ServerDevCard(DevCardType.ROAD_BUILD));
        for(int i = 0; i < jsonDevCards.getSoldier(); i++)
            devCards.add(new ServerDevCard(DevCardType.SOLDIER));
        for(int i = 0; i < jsonDevCards.getYearOfPlenty(); i++)
            devCards.add(new ServerDevCard(DevCardType.YEAR_OF_PLENTY));
    }

    public void initDevCards(){
        //initialize monument cards
        for(int i = 0; i < 5; i++){
            devCards.add(new ServerDevCard(DevCardType.MONUMENT));
        }

        //initialize soldier cards
        for(int i = 0; i < 14; i++){
            devCards.add(new ServerDevCard(DevCardType.SOLDIER));
        }

        //initialize progress cards
        for(int i = 0; i < 2; i++){
            devCards.add(new ServerDevCard(DevCardType.MONOPOLY));
            devCards.add(new ServerDevCard(DevCardType.ROAD_BUILD));
            devCards.add(new ServerDevCard(DevCardType.YEAR_OF_PLENTY));
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
        if (resCards.get(card) > 0)
            return true;

//    	for(Map.Entry<ResourceType, Integer> entry : resCards.entrySet()){
//    		if(entry.getKey() == card){
//    			if(entry.getValue() > 0)
//    				return true;
//    		}
//    	}
        return false;
    }

    /**
     * Used by the Facade to determine if a development card can be taken
     * from the GameBank by the Player
     * @return Whether a development card can be taken or not
     */
    public boolean canGiveDevCard(){
        return devCards.size() > 0;
    }

    /**
     * Randomly selects a development card from the list.
     * @return DevCard object to be placed in the PlayerBank
     */
    public ServerDevCard giveDevCard(){
        //random dev card
        return null;
    }

    public void buyPiece(PieceType type) {

        switch (type) {
            case ROAD:
                resCards.put(ResourceType.WOOD, resCards.get(ResourceType.WOOD) + 1);
                resCards.put(ResourceType.BRICK, resCards.get(ResourceType.BRICK) + 1);
                return;
            case SETTLEMENT:
                resCards.put(ResourceType.WOOD, resCards.get(ResourceType.WOOD) + 1);
                resCards.put(ResourceType.BRICK, resCards.get(ResourceType.BRICK) + 1);
                resCards.put(ResourceType.SHEEP, resCards.get(ResourceType.SHEEP) + 1);
                resCards.put(ResourceType.WHEAT, resCards.get(ResourceType.WHEAT) + 1);
                return;
            case CITY:
                resCards.put(ResourceType.WHEAT, resCards.get(ResourceType.WHEAT) + 2);
                resCards.put(ResourceType.ORE, resCards.get(ResourceType.ORE) + 3);
                return;
            default:
        }

    }

}
