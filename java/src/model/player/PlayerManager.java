package model.player;

import java.util.ArrayList;

/**
 * Created by brandt on 1/17/15.
 * <p>
 * This class holds a collection of Player objects and contains the methods necessary to
 * create and remove players from a game. The Model Facade will also communicate with
 * this class to determine if a player can be added or not
 * </p>
 */
public class PlayerManager {

    /**The List of Player objects in the game. The number of players can range from 0-4*/
    ArrayList<Player> players;

    /**
     * Method called by Facade to determine if a player can be added to the current game
     */
    public boolean canAddPlayer(){
        return true;
    }

    /**
     * Creates a new Player object and adds it to the List of current players.
     * @return Whether the Player object was successfully created or not
     * @throws createPlayerException Thrown if there is insufficient data to create a new Player. Also
     * acts as a second check to make sure no more than four players are in a game at a time.
     */
    public boolean createPlayer() throws createPlayerException{
        //add a player to the list
        return true;
    }

    /**
     * Removes a Player object from the List of current players.
     * @param The index of the player to be removed
     * @return Whether the specified player was removed or not.
     * @throws removePlayerException Thrown if there is a client-side error in removing the player
     */
    public boolean removePlayer(Player.playerIdx) throws removePlayerException{
        //remove a player from the list
        return true;
    }

}
