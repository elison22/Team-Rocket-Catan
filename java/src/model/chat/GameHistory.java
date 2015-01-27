package model.chat;

import model.Game;

import java.util.List;

/**
 * @author Hayden
 * GameHistory contains a list of turn summaries. The list is automatically populated by the
 * server at the end of a player's turn.
 */
public class GameHistory {

    /**The list of turn summaries*/
    private arrayList<String> turnLog;

    /**
     * Creates a GameHistory object with the list of turn summaries that have occurred so far.
     * @param turnLog The list of actions players took on their turns
     */
    public GameHistory(arrayList<String> turnLog){
        this.turnLog = turnLog;
    }
}