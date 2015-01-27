package model.chat;

import java.util.ArrayList;

/**
 * @author Hayden
 * GameHistory contains a list of turn summaries. The list is automatically populated by the
 * server at the end of a player's turn.
 */
public class GameHistory {

    /**The list of turn summaries*/
    private ArrayList<String> turnLog;

    /**
     * Creates a GameHistory object with the list of turn summaries that have occurred so far.
     * @param turnLog The list of actions players took on their turns
     */
    public GameHistory(ArrayList<String> turnLog){
        this.turnLog = turnLog;
    }
}