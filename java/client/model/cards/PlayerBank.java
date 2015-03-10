package model.cards;

import java.util.ArrayList;
import java.util.HashMap;

import serializer.json.JsonDevCardList;
import serializer.json.JsonResourceList;
import shared.definitions.DevCardType;
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
        this.resCards.put(ResourceType.BRICK, 0);
        this.resCards.put(ResourceType.WOOD, 0);
        this.resCards.put(ResourceType.WHEAT, 0);
        this.resCards.put(ResourceType.SHEEP, 0);
        this.resCards.put(ResourceType.ORE, 0);
    }

    public PlayerBank(JsonResourceList resources, JsonDevCardList oldDevs, JsonDevCardList newDevs){
        this.resCards.clear();
        this.resCards.put(ResourceType.BRICK, resources.getBrick());
        this.resCards.put(ResourceType.WOOD, resources.getWood());
        this.resCards.put(ResourceType.WHEAT, resources.getWheat());
        this.resCards.put(ResourceType.SHEEP, resources.getSheep());
        this.resCards.put(ResourceType.ORE, resources.getOre());

        this.newDevs.clear();
        this.oldDevs.clear();
        setDevCards(oldDevs, this.oldDevs);
        setDevCards(newDevs, this.newDevs);
    }

    public void setDevCards(JsonDevCardList jsonDevCards, ArrayList<DevCard> devCardList){
        for(int i = 0; i < jsonDevCards.getMonopoly(); i++){
            devCardList.add(new DevCard(DevCardType.MONOPOLY, "Monopoly"));
        }
        for(int i = 0; i< jsonDevCards.getMonument(); i++){
            devCardList.add(new DevCard(DevCardType.MONUMENT, "Monument"));
            // TODO We need some way to know which monument card the player has (ie. Library, University, etc.)
        }
        for(int i = 0; i < jsonDevCards.getRoadBuilding(); i++){
            devCardList.add(new DevCard(DevCardType.ROAD_BUILD, "Road Building"));
        }
        for(int i = 0; i < jsonDevCards.getSoldier(); i++){
            devCardList.add(new DevCard(DevCardType.SOLDIER, "Soldier"));
        }
        for(int i = 0; i < jsonDevCards.getYearOfPlenty(); i++){
            devCardList.add(new DevCard(DevCardType.YEAR_OF_PLENTY, "Year of Plenty"));
        }
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
    
    public ArrayList<DevCard> getOldDevCards() {
    	return oldDevs;
    }

    public ArrayList<DevCard> getNewDevCards() {
        return newDevs;
    }
    
    public boolean hasResCards(HashMap<ResourceType, Integer> resources){
        for (ResourceType key : resources.keySet()){
            if(resources.get(key) > this.resCards.get(key)){
                return false;
            }
        }
        return true;
    }

    public int getResCount() {
        int count = 0;
        for (ResourceType key : resCards.keySet()){
            count += resCards.get(key);
        }
        return count;
    }
    
    public int getSoldierCount()
    {
    	int soldiers = 0;
    	for (DevCard card : oldDevs)
    		if(card.getType() == DevCardType.SOLDIER)
    			soldiers++;
        for (DevCard card : newDevs)
            if(card.getType() == DevCardType.SOLDIER)
                soldiers++;
    	return soldiers;
    }
    public int getMonopolyCount()
    {
    	int monopolies = 0;
    	for (DevCard card : oldDevs)
    		if(card.getType() == DevCardType.MONOPOLY)
    			monopolies++;
        for (DevCard card : newDevs)
            if(card.getType() == DevCardType.MONOPOLY)
                monopolies++;
    	return monopolies;
    }
    public int getRoadBuildingCount()
    {
    	int roadBuilding = 0;
    	for (DevCard card : oldDevs)
    		if(card.getType() == DevCardType.ROAD_BUILD)
    			roadBuilding++;
        for (DevCard card : newDevs)
            if(card.getType() == DevCardType.ROAD_BUILD)
                roadBuilding++;
    	return roadBuilding;
    }
    public int getYearOfPlentyCount()
    {
    	int yearOfPlenty = 0;
    	for (DevCard card : oldDevs)
    		if(card.getType() == DevCardType.YEAR_OF_PLENTY)
    			yearOfPlenty++;
        for (DevCard card : newDevs)
            if(card.getType() == DevCardType.YEAR_OF_PLENTY)
                yearOfPlenty++;
    	return yearOfPlenty;
    }
    public int getMonumentCount()
    {
    	int monument = 0;
    	for (DevCard card : oldDevs)
    		if(card.getType() == DevCardType.MONUMENT)
    			monument++;
        for (DevCard card : newDevs)
            if(card.getType() == DevCardType.MONUMENT)
                monument++;
    	return monument;
    }
    
}
