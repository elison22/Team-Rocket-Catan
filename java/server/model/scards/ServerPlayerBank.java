package model.scards;

import serializer.json.JsonDevCardList;
import serializer.json.JsonResourceList;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Hayden
 * Extension of the CardBank class that holds all of the cards
 * each player has in their hand. Upon creation, the PlayerBank will
 * be empty.
 */
public class ServerPlayerBank extends ServerCardBank {


    /**
     * Creates a PlayerBank object and initializes the player's
     * held resources to zero
     */
    public ServerPlayerBank(){
        this.resCards.put(ResourceType.BRICK, 0);
        this.resCards.put(ResourceType.WOOD, 0);
        this.resCards.put(ResourceType.WHEAT, 0);
        this.resCards.put(ResourceType.SHEEP, 0);
        this.resCards.put(ResourceType.ORE, 0);
    }

    public ServerPlayerBank(JsonResourceList resources, JsonDevCardList oldDevs, JsonDevCardList newDevs){
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

    public void setDevCards(JsonDevCardList jsonDevCards, ArrayList<ServerDevCard> devCardList){
        for(int i = 0; i < jsonDevCards.getMonopoly(); i++){
            devCardList.add(new ServerDevCard(DevCardType.MONOPOLY));
        }
        for(int i = 0; i< jsonDevCards.getMonument(); i++){
            devCardList.add(new ServerDevCard(DevCardType.MONUMENT));
            // TODO We need some way to know which monument card the player has (ie. Library, University, etc.)
        }
        for(int i = 0; i < jsonDevCards.getRoadBuilding(); i++){
            devCardList.add(new ServerDevCard(DevCardType.ROAD_BUILD));
        }
        for(int i = 0; i < jsonDevCards.getSoldier(); i++){
            devCardList.add(new ServerDevCard(DevCardType.SOLDIER));
        }
        for(int i = 0; i < jsonDevCards.getYearOfPlenty(); i++){
            devCardList.add(new ServerDevCard(DevCardType.YEAR_OF_PLENTY));
        }
    }

    /**
     * Used to take a development card from the GameBank and add it
     * to the player's list of development cards
     * @return True if the player successfully took a DevCard
     */
    public void buyDevCard(ServerDevCard card){
        resCards.put(ResourceType.ORE, resCards.get(ResourceType.BRICK) - 1);
        resCards.put(ResourceType.SHEEP, resCards.get(ResourceType.SHEEP) - 1);
        resCards.put(ResourceType.WHEAT, resCards.get(ResourceType.WHEAT) - 1);
        devCards.add(card);
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

    public ArrayList<ServerDevCard> getOldDevCards() {
        return oldDevs;
    }

    public ArrayList<ServerDevCard> getNewDevCards() {
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
        for (ServerDevCard card : oldDevs)
            if(card.getType() == DevCardType.SOLDIER)
                soldiers++;
        for (ServerDevCard card : newDevs)
            if(card.getType() == DevCardType.SOLDIER)
                soldiers++;
        return soldiers;
    }
    public int getMonopolyCount()
    {
        int monopolies = 0;
        for (ServerDevCard card : oldDevs)
            if(card.getType() == DevCardType.MONOPOLY)
                monopolies++;
        for (ServerDevCard card : newDevs)
            if(card.getType() == DevCardType.MONOPOLY)
                monopolies++;
        return monopolies;
    }
    public int getRoadBuildingCount()
    {
        int roadBuilding = 0;
        for (ServerDevCard card : oldDevs)
            if(card.getType() == DevCardType.ROAD_BUILD)
                roadBuilding++;
        for (ServerDevCard card : newDevs)
            if(card.getType() == DevCardType.ROAD_BUILD)
                roadBuilding++;
        return roadBuilding;
    }
    public int getYearOfPlentyCount()
    {
        int yearOfPlenty = 0;
        for (ServerDevCard card : oldDevs)
            if(card.getType() == DevCardType.YEAR_OF_PLENTY)
                yearOfPlenty++;
        for (ServerDevCard card : newDevs)
            if(card.getType() == DevCardType.YEAR_OF_PLENTY)
                yearOfPlenty++;
        return yearOfPlenty;
    }
    public int getMonumentCount()
    {
        int monument = 0;
        for (ServerDevCard card : oldDevs)
            if(card.getType() == DevCardType.MONUMENT)
                monument++;
        for (ServerDevCard card : newDevs)
            if(card.getType() == DevCardType.MONUMENT)
                monument++;
        return monument;
    }

    public void buyPiece(PieceType type) {

        switch (type) {
            case ROAD:
                resCards.put(ResourceType.WOOD, resCards.get(ResourceType.WOOD) - 1);
                resCards.put(ResourceType.BRICK, resCards.get(ResourceType.BRICK) - 1);
                return;
            case SETTLEMENT:
                resCards.put(ResourceType.WOOD, resCards.get(ResourceType.WOOD) - 1);
                resCards.put(ResourceType.BRICK, resCards.get(ResourceType.BRICK) - 1);
                resCards.put(ResourceType.SHEEP, resCards.get(ResourceType.SHEEP) - 1);
                resCards.put(ResourceType.WHEAT, resCards.get(ResourceType.WHEAT) - 1);
                return;
            case CITY:
                resCards.put(ResourceType.WHEAT, resCards.get(ResourceType.WHEAT) - 2);
                resCards.put(ResourceType.ORE, resCards.get(ResourceType.ORE) - 3);
                return;
            default:
        }

    }



}
