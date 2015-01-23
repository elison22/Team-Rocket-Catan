package model;

/**
 * Stores and updates data about the current state of the turn
 * Created by brandt on 1/23/15.
 */
public class TurnTracker {

    /**
     * Keeps track of the which player is active
     */
    private int currentPlayerIndex;

    /**
     * Tracks which part of the turn the game is on
     */
    private TurnState currentState;

    /**
     * Constructor
     */
    public TurnTracker()
    {

    }

    /**
     * Updates the current player at the end of a turn
     * @param nextPlayerIndex index of the new player who will be active
     */
    public void setCurrentPlayerIndex(int nextPlayerIndex)
    {

    }

    /**
     * Change the state of the game as the turn progresses
     * @param newState state to be entered into
     */
    public void setCurrentState(TurnState newState)
    {

    }

    public int getCurrentPlayerIndex()
    {
        return currentPlayerIndex;
    }

    public TurnState getCurrentState()
    {
        return currentState;
    }
}
