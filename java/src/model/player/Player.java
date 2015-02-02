package model.player;

import java.util.HashMap;

import model.cards.PlayerBank;
import model.cards.ResourceSet;
import shared.definitions.ResourceType;

/**
 * @author Hayden
 * Player holds all data for a specific player in the game. That data includes
 * the player's hand of cards called a PlayerBank, their name, the number of
 * victory points they have earned, and their index in the list of players.
 * This index also functions as an identifier for each player. This class also
 * implements the methods the Facade will call to check if a trade can be offered,
 * accepted, or if a certain constructable can be built.
 */
public class Player {

    private int playerIdx;				// The player's index in list of players
    private PlayerBank bank;			// The player's bank of cards
    private String playerName;			// The player's name
    private int victoryPoints;			// The player's victory points
    private int remainingRoads;			// The number of roads the player has left to build
    private int remainingSettlements;	// The number of settlements the player has left to build
    private int remainingCities;		// The number of cities the player can build

    /**
     * Creates a new Player object with an empty PlayerBank object and zero victory points
     * @param playerIdx Initializes the player's index in the list of players
     * @param playerName The name the user entered upon starting the game.
     */
    public Player(int playerIdx, String playerName){
        this.playerName = playerName;
        this.victoryPoints = 0;
        this.bank = new PlayerBank();
        this.playerIdx = playerIdx;
        this.remainingRoads = 15;
        this.remainingSettlements = 5;
        this.remainingCities = 4;
    }

    /**
     * Called by the Facade to update players after polling
     * @param bank The player's bank of cards
     * @param victoryPoints The player's victoryPoints
     * @param remainingRoads The number of roads the player has remaining
     * @param remainingSettlements The number of settlements the player has remaining
     * @param remainingCities The number of cities the player has remaining
     */
    public void update(PlayerBank bank, int victoryPoints, int remainingRoads, int remainingSettlements, int remainingCities){
        this.bank = bank;
        this.victoryPoints = victoryPoints;
        this.remainingRoads = remainingRoads;
        this.remainingSettlements = remainingSettlements;
        this.remainingCities = remainingCities;
    }

    /**
     * Called by the Facade to determine if a player has the resources necessary to offer
     * a certain trade
     * @param playerIdx The player that offered the trade
     * @param offeredRes A map of offered resource cards
     * @return return whether a trade can be offered or not
     */
    public boolean canOfferTrade(int playerIdx, HashMap<ResourceType,Integer> offeredRes){
        return bank.hasResCards(offeredRes);
    }
    
    public boolean canOfferTrade(int playerIdx, ResourceSet offeredRes){
        return bank.hasResCards(offeredRes.getResources());
    }

    /**
     * Called by the Facade to determine if a player has the resources necessary to accept
     * a certain trade
     * @param playerIdx The player that offered the trade
     * @param desiredRes A list of resource cards desired by the other player
     * @return return whether a trade was accepted or not
     */
    public boolean canAcceptTrade(int playerIdx, ResourceSet desiredRes){
        return bank.hasResCards(desiredRes.getResources());
    }
    
    /**
     * @param devCard type of dev card player wants to play
     * @return true if player has that dev card and hasn't already played it
     */
    public boolean canPlayDevCard(String devCard) {
    	switch (devCard) {
    		case "YearOfPlenty":
    			
    			break;
    		
    		case "Soldier":
    			
    			break;
    		
    		case "Monopoly":
    			
    			break;
    			
    		case "Monument":
    			
    			break;
    			
    		case "RoadBuilder":
    			
    			break;
    	}
    	return false;
    }
    
    public int getVictoryPoints() {
    	return victoryPoints;
    }
    
    public String getName() {
    	return playerName;
    }
    
    public boolean canDiscardCards(ResourceSet cards) {
    	
    	return true;
    }
    
    public boolean canBuyDevCard() {
    	if(bank.canBuyDevCard())
    		return true;
    	else
    		return false;
    }

    public boolean canBuildRoad(){
    	if(remainingRoads > 0)
    		return true;
    	else
    		return false;
    }
    
    public boolean canBuildCity(){
    	if(remainingCities > 0)
    		return true;
    	else 
    		return false;
    }
    
    public boolean canBuildSettlement(){
    	if(remainingSettlements > 0)
    		return true;
    	else
    		return false;
    }

}
