package model.player;

import model.cards.PlayerBank;

/**
 * Created by brandt on 1/17/15.
 * <p>
 * This class is used to hold all of the data for a specific player in the game. That
 * data includes the player's hand of cards called a PlayerBank, their name, the number
 * of victory points they have earned, and their index in the list of players. This index
 * functions as an identifier for each player.
 * </p>
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

    public update(PlayerBank bank, int victoryPoints){
        this.bank = bank;
        this.victoryPoints = victoryPoints;
    }
}
