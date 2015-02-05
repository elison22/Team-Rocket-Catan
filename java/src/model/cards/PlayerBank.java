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

        setDevCards(oldDevs, true);
        setDevCards(newDevs, true);
    }

    public void setDevCards(JsonDevCardList jsonDevCards, boolean hasBeenPlayed){
        this.devCards.clear();

        for(int i = 0; i < jsonDevCards.getMonopoly(); i++){
            this.devCards.add(new DevCard(DevCardType.MONOPOLY, "Monopoly", hasBeenPlayed));
        }
        for(int i = 0; i< jsonDevCards.getMonument(); i++){
            this.devCards.add(new DevCard(DevCardType.MONUMENT, "Monument", hasBeenPlayed));
            // TODO We need some way to know which monument card the player has (ie. Library, University, etc.)
        }
        for(int i = 0; i < jsonDevCards.getRoadBuilding(); i++){
            this.devCards.add(new DevCard(DevCardType.ROAD_BUILD, "Road Building", hasBeenPlayed));
        }
        for(int i = 0; i < jsonDevCards.getSoldier(); i++){
            this.devCards.add(new DevCard(DevCardType.SOLDIER, "Soldier", hasBeenPlayed));
        }
        for(int i = 0; i < jsonDevCards.getYearOfPlenty(); i++){
            this.devCards.add(new DevCard(DevCardType.YEAR_OF_PLENTY, "Year of Plenty", hasBeenPlayed));
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
