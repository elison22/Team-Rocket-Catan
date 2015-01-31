package model.player;

import java.util.HashMap;
import java.util.List;

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

    /** The player's index in the list of players */
    private int playerIdx;
    /** The player's bank of cards */
    private PlayerBank bank;
    /** The player's name */
    private String playerName;
    /** The player's victory points */
    private int victoryPoints;
    /** The number of roads the player can build */
    private int remainingRoads;
    /** The number of settlements the player can build */
    private int remainingSettlements;
    /** The number of cities the player can build */
    private int remainingCities;

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
    public boolean canOfferTrade(int playerIdx, HashMap<ResourceType, Integer> offeredRes){
        return bank.hasResCards(offeredRes);
    }

    /**
     * Called by the Facade to determine if a player has the resources necessary to accept
     * a certain trade
     * @param playerIdx The player that offered the trade
     * @param desiredRes A list of resource cards desired by the other player
     * @return return whether a trade was accepted or not
     */
    public boolean canAcceptTrade(int playerIdx, HashMap<ResourceType, Integer> desiredRes){
        return bank.hasResCards(desiredRes);
    }

    public boolean canBuildRoad(){
        return true;
    }

}
