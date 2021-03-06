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
    	currentPlayerIndex = -1;
    	currentState = null;
    	longestRoadPlayerIndex = -1;
    	largestArmyPlayerIndex = -1;
    }
    
    public TurnTracker(JsonTurnTracker tracker)
    {
    	currentPlayerIndex = tracker.getCurrentTurn();
    	if(tracker.getStatus().equals("Rolling"))
    		currentState = TurnState.Rolling;
    	else if(tracker.getStatus().equals("Robbing"))
    		currentState = TurnState.Robbing;
    	else if(tracker.getStatus().equals("Playing"))
    		currentState = TurnState.Playing;
    	else if(tracker.getStatus().equals("Discarding"))
    		currentState = TurnState.Discarding;
    	else if(tracker.getStatus().equals("FirstRound"))
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
    	currentPlayerIndex = nextPlayerIndex;
    }

    /**
     * Change the state of the game as the turn progresses
     * @param newState state to be entered into
     */
    public void setCurrentState(TurnState newState)
    {
    	currentState = newState;
    }

    /**
     * Updates the player with the largest army. Checks for valid input
     * @param largestArmy index of the player taking the largest army bonus
     */
    public void setLargestArmyPlayerIndex(int largestArmy) {
    	largestArmyPlayerIndex = largestArmy;
    }

    /**
     * Updates the player with the longest road. Checks for valid input
     * @param longestRoadPlayerIndex index of the player taking the longest road bonus
     */
    public void setLongestRoadPlayerIndex(int longestRoadIndex) {
    	longestRoadPlayerIndex = longestRoadIndex;
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
    
    public boolean canPlayerBuildRoadSettlement(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == TurnState.Playing || currentState == TurnState.FirstRound || 
    				currentState == TurnState.SecondRound)
    			return true;
    	}
    	return false;
    }

    public boolean canPlayerPlayDev(int playerIndex){
        if(currentPlayerIndex == playerIndex){
            if(currentState == TurnState.Playing || currentState == TurnState.Rolling){
                return true;
            }
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
    
    public boolean canPlayerTrade(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == TurnState.Playing)
    			return true;
    	}
    	return false;
    }
}
