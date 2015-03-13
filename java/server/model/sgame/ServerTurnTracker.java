package model.sgame;

import model.sgame.ServerTurnState;
import serializer.json.JsonTurnTracker;

public class ServerTurnTracker
{

    /**
     * Keeps track of the which player is active
     */
    private int currentPlayerIndex;

    /**
     * Tracks which part of the turn the game is on
     */
    private ServerTurnState currentState;

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
    public ServerTurnTracker()
    {
    	currentPlayerIndex = -1;
    	currentState = null;
    	longestRoadPlayerIndex = -1;
    	largestArmyPlayerIndex = -1;
    }
    
    public ServerTurnTracker(JsonTurnTracker tracker)
    {
    	currentPlayerIndex = tracker.getCurrentTurn();
    	if(tracker.getStatus().equals("Rolling"))
    		currentState = ServerTurnState.Rolling;
    	else if(tracker.getStatus().equals("Robbing"))
    		currentState = ServerTurnState.Robbing;
    	else if(tracker.getStatus().equals("Playing"))
    		currentState = ServerTurnState.Playing;
    	else if(tracker.getStatus().equals("Discarding"))
    		currentState = ServerTurnState.Discarding;
    	else if(tracker.getStatus().equals("FirstRound"))
    		currentState = ServerTurnState.FirstRound;
    	else currentState = ServerTurnState.SecondRound;
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
    public void setCurrentState(ServerTurnState newState)
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

    public ServerTurnState getCurrentState()
    {
        return currentState;
    }
    
    public boolean canPlayerBuild(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == ServerTurnState.Playing)
    			return true;
    	}
    	return false;
    }
    
    public boolean canPlayerBuildRoadSettlement(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == ServerTurnState.Playing || currentState == ServerTurnState.FirstRound || 
    				currentState == ServerTurnState.SecondRound)
    			return true;
    	}
    	return false;
    }

    public boolean canPlayerPlayDev(int playerIndex){
        if(currentPlayerIndex == playerIndex){
            if(currentState == ServerTurnState.Playing || currentState == ServerTurnState.Rolling){
                return true;
            }
        }
        return false;
    }
    
    public boolean canPlayerRob(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == ServerTurnState.Robbing)
    			return true;
    	}
    	return false;
    }
    
    public boolean canPlayerRoll(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == ServerTurnState.Rolling)
    			return true;
    	}
    	return false;
    }
    
    public boolean canPlayerDiscard(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == ServerTurnState.Discarding)
    			return true;
    	}
    	return false;
    }
    
    public boolean canPlayerTrade(int playerIndex) {
    	if(currentPlayerIndex == playerIndex) {
    		if(currentState == ServerTurnState.Playing)
    			return true;
    	}
    	return false;
    }
}
