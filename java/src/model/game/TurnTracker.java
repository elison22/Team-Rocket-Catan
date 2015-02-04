package model.game;

import serializer.json.JsonTurnTracker;

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
     * Index of the player with the longest road
     */
    private int longestRoadPlayerIndex;

    /**
     * Index of the player with the largest army
     */
    private int largestArmyPlayerIndex;

    /**
     * Constructor
     */
    public TurnTracker()
    {

    }
    
    public TurnTracker(JsonTurnTracker tracker)
    {
    	currentPlayerIndex = tracker.getCurrentTurn();
    	if(tracker.getStatus() == "Rolling")
    		currentState = TurnState.Rolling;
    	else if(tracker.getStatus() == "Robbing")
    		currentState = TurnState.Robbing;
    	else if(tracker.getStatus() == "Playing")
    		currentState = TurnState.Playing;
    	else if(tracker.getStatus() == "Discarding")
    		currentState = TurnState.Discarding;
    	else if(tracker.getStatus() == "FirstRound")
    		currentState = TurnState.FirstRound;
    	else currentState = TurnState.SecondRound;
    	largestArmyPlayerIndex = tracker.getLargestArmy();
    	longestRoadPlayerIndex = tracker.getLongestRoad();
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

    /**
     * Updates the player with the largest army. Checks for valid input
     * @param largestArmyPlayerIndex index of the player taking the largest army bonus
     */
    public void setLargestArmyPlayerIndex(int largestArmyPlayerIndex) {

    }

    /**
     * Updates the player with the longest road. Checks for valid input
     * @param longestRoadPlayerIndex index of the player taking the longest road bonus
     */
    public void setLongestRoadPlayerIndex(int longestRoadPlayerIndex) {

    }

    public int getLargestArmyPlayerIndex() {
        return largestArmyPlayerIndex;
    }

    public int getLongestRoadPlayerIndex() {
        return longestRoadPlayerIndex;
    }

    public int getCurrentPlayerIndex()
    {
        return currentPlayerIndex;
    }

    public TurnState getCurrentState()
    {
        return currentState;
    }
    
    public boolean canPlayerBuild(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == TurnState.Playing)
    			return true;
    	}
    	return false;
    }
    
    public boolean canPlayerRob(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == TurnState.Robbing)
    			return true;
    	}
    	return false;
    }
    
    public boolean canPlayerRoll(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == TurnState.Rolling)
    			return true;
    	}
    	return false;
    }
    
    public boolean canPlayerDiscard(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == TurnState.Discarding)
    			return true;
    	}
    	return false;
    }
}
