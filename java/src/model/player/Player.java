package model.player;

import model.cards.PlayerBank;

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

    /**
     * Creates a new Player object with an empty PlayerBank object and zero victory points
     * @param playerIdx Initializes the player's index in the list of players
     * @param name The name the user entered upon starting the game.
     */
    public Player(int playerIdx, String playerName){
        this.playerName = playerName;
        this.victoryPoints = 0;
        this.bank = new PlayerBank();
        this.playerIdx = playerIdx;
    }

    /**
     * Called by the Facade to update players after polling
     * @param bank The player's bank of cards
     * @param victoryPoints The player's victoryPoints
     */
    public update(PlayerBank bank, int victoryPoints){
        this.bank = bank;
        this.victoryPoints = victoryPoints;
    }

    /**
     * Called by the Facade to determine if a player has the resources necessary to offer
     * a certain trade
     * @param playerIdx The player that offered the trade
     * @param offeredRes A list of offerened resource cards
     * @return return whether a trade can be offered or not
     */
    public boolean canOfferTrade(int playerIdx, List<Integer> offeredRes){
        return true;
    }

    /**
     * Called by the Facade to determine if a player has the resources necessary to accept
     * a certain trade
     * @param playerIdx The player that offered the trade
     * @param desiredRes A list of resource cards desired by the other player
     * @return return whether a trade was accepted or not
     */
    public boolean canAcceptTrade(int playerIdx, , List<Integer> desiredRes){
        return true;
    }

}
