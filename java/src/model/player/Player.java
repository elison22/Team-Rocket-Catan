package model.player;

import java.util.ArrayList;
import java.util.HashMap;

import model.cards.DevCard;
import model.cards.PlayerBank;
import model.cards.ResourceSet;
import serializer.json.JsonPlayer;
import shared.definitions.DevCardType;
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

    private PlayerBank  bank;			        // The player's bank of cards
    private String      color;                  // The player's color
    private boolean     discarded;              // True if the player has discarded this turn.
    private int         monumentDevs;           // The number of monument cards a player holds. Used to determine when the cards can be played.
    private boolean     playedDevCard;          // True if the player has used a dev card this turn.
    private int         playerID;               // The player's unique ID
    private int         playerIdx;				// The player's index in list of players
    private String      playerName;			    // The player's name
    private int         remainingCities;		// The number of cities the player can build
    private int         remainingRoads;			// The number of roads the player has left to build
    private int         remainingSettlements;	// The number of settlements the player has left to build
    private int         soldierDevs;            // The number of played soldier cards. Used to determine largest army
    private int         victoryPoints;			// The player's victory points

    /**
     * Creates a new Player object with an empty PlayerBank object and zero victory points
     * @param playerIdx Initializes the player's index in the list of players
     * @param playerName The name the user entered upon starting the game.
     */
    public Player(int playerIdx, int playerID, String playerName, String color){
        this.bank = new PlayerBank();
        this.color = color;
        this.discarded = false;
        this.monumentDevs = 0;
        this.remainingCities = 4;
        this.remainingRoads = 15;
        this.remainingSettlements = 5;
        this.playedDevCard = false;
        this.playerID = playerID;
        this.playerIdx = playerIdx;
        this.playerName = playerName;
        this.soldierDevs = 0;
        this.victoryPoints = 0;
    }

    public Player(JsonPlayer jsonPlayer){
        this.bank = new PlayerBank(jsonPlayer.getResources(), jsonPlayer.getOldDevCards(), jsonPlayer.getNewDevCards());
        this.color = jsonPlayer.getColor();
        this.discarded = jsonPlayer.isDiscarded();
        this.monumentDevs = jsonPlayer.getMonuments();
        this.remainingCities = jsonPlayer.getCities();
        this.remainingRoads = jsonPlayer.getRoads();
        this.remainingSettlements = jsonPlayer.getSettlements();
        this.playedDevCard = jsonPlayer.isPlayedDevCard();
        this.playerID = jsonPlayer.getPlayerID();
        this.playerIdx = jsonPlayer.getPlayerIndex();
        this.playerName = jsonPlayer.getName();
        this.soldierDevs = jsonPlayer.getSoldiers();
        this.victoryPoints = jsonPlayer.getVictoryPoints();
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

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public String getName() {
        return playerName;
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
     * @return true if player has that dev card, hasn't already played it, and meets the
     * requirement to play that certain dev card.
     */
    public boolean canPlayDevCard(DevCardType devCard) {
    	ArrayList<DevCard> devCards = bank.getDevCards();
        for(DevCard playerDev : devCards){
            if(playerDev.getType() == devCard){
                if(!playerDev.hasBeenPlayed()){
                    switch(devCard){
                        case ROAD_BUILD:
                            return remainingRoads >= 2;
                        case MONUMENT:
                            return (victoryPoints + monumentDevs) >= 10;
                    }
                    return true;
                }
            }
        }
    	return false;
    }

    public boolean canBuyDevCard() {
        return bank.canAffordDevCard();
    }

    public boolean canDiscardCards(ResourceSet cards) {
        bank.hasResCards(cards.getResources());
    	return true;
    }
    
    public boolean canBuildRoad(){
    	if(remainingRoads > 0){
            if(bank.canAffordRoad()){
    		    return true;
            }
        }
        return false;
    }

    public boolean canBuildSettlement(){
        if(remainingSettlements > 0){
            if(bank.canAffordSettlement()){
                return true;
            }
        }
        return false;
    }

    public boolean canBuildCity(){
    	if(remainingCities > 0){
            if(bank.canAffordCity()){
    		    return true;
            }
        }
        return false;
    }
}
