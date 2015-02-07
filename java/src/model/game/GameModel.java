package model.game;

import java.util.ArrayList;
import java.util.HashMap;
import model.board.Board;
import model.board.BoardException;
import model.cards.GameBank;
import model.chat.Chat;
import model.player.Player;
import model.trade.DomesticTrade;
import model.trade.MaritimeTrade;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class GameModel {

    private String gameName;
    private int versionNumber;
    private ArrayList<Player> playerList;   // holds all the players
    private GameBank cardBank;              // holds all the resource cards and all the dev cards
    private TurnTracker turnTracker;        // holds whos turn it is, as well as game state
    private Board map;
    private Chat chat;
    private Chat gameHistory;
    //private GameHistory gameHistory;
    private ArrayList<String> aiList;
    private int winner;

    public GameModel() {
    	aiList = new ArrayList<String>();
    	aiList.add("LARGEST_ARMY");
    	//winner = -1;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public GameBank getCardBank() {
        return cardBank;
    }

    public void setCardBank(GameBank cardBank) {
        this.cardBank = cardBank;
    }

    public int getPlayerTurn() {
        return turnTracker.getCurrentPlayerIndex();
    }

    public TurnState getTurnState() {
        return turnTracker.getCurrentState();
    }

    public int getLongestRoad() {
        return turnTracker.getLongestRoadPlayerIndex();
    }

    public void setTurnTracker(TurnTracker turnTracker) {
        this.turnTracker = turnTracker;
    }

    public Board getMap() {
        return map;
    }

    public void setMap(Board map) {
        this.map = map;
    }
    
    public ArrayList<String> getAiList() {
    	return aiList;
    }
    
    public void setChat(Chat chat) {
		this.chat = chat;
	}

	public void setGameHistory(Chat gameHistory) {
		this.gameHistory = gameHistory;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public boolean canPlayDevCard(int playerId, DevCardType devCard) {
    	if(turnTracker.canPlayerPlayDev(playerId)) {
    		if(playerList.get(playerId).canPlayDevCard(devCard)){
    			return true;
            }
    	}
    	return false;
    }
    
    public boolean canBuyDevCard(int playerId) {
    	if(turnTracker.canPlayerBuild(playerId)) {
            if(cardBank.canGiveDevCard()){
                if(playerList.get(playerId).canBuyDevCard()){
                    return true;
                }
            }
    	} 
    	
    	return false;
    }
    
    public boolean canPlaceRobber(int playerId) {
		if(turnTracker.canPlayerRoll(playerId)) {
			return true;
		}
		return false;
	}
    
    public boolean canRollDice(int playerId) {
    	if(playerId == turnTracker.getCurrentPlayerIndex()){
    		if(turnTracker.getCurrentState() == TurnState.Rolling) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean canBuildRoad(int playerId, EdgeLocation location) {
    	if(turnTracker.canPlayerBuild(playerId)) {
    		if(playerList.get(playerId).canBuildRoad()){
    			try {
					if(map.canBuildRoad(location, playerId))
						return true;
				} catch (BoardException e) {
					return false;
				}
			}
    	}
    	return false;
    }

    public boolean canBuildSettlement(int playerId, VertexLocation location) {
        if(turnTracker.canPlayerBuild(playerId)) {
            if(playerList.get(playerId).canBuildSettlement()){
                try {
                    if(map.canBuildSettlement(location, playerId))
                        return true;
                } catch (BoardException e) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean canBuildCity(int playerId, VertexLocation location) {
    	if(turnTracker.canPlayerBuild(playerId)) {
    		if(playerList.get(playerId).canBuildCity()){
    			try {
					if(map.canBuildCity(location, playerId))
						return true;
				} catch (BoardException e) {
					return false;
				}
			}
    	}
    	return false;
    }

    public boolean canOfferTrade(int playerId, DomesticTrade trade) {
    	if(turnTracker.canPlayerBuild(playerId)) {
    		if(playerList.get(playerId).canOfferTrade(playerId, trade.getOffer().getResources())){
    			return true;
			}
    	}
    	return false;
    }
    
    public boolean canAcceptTrade(int playerId, DomesticTrade trade) {
    	if(turnTracker.canPlayerBuild(playerId)) {
    		if(playerList.get(playerId).canAcceptTrade(playerId, trade.getOffer())){
    			return true;
			}
    	}
    	return false;
    }
    
    public boolean canMaritimeTrade(int playerId, MaritimeTrade trade) {
    	if(turnTracker.canPlayerBuild(playerId)) {
    		if(cardBank.canGiveResCard(trade.getResourceToReceive())) {
	    		HashMap<ResourceType, Integer> resource = new HashMap<ResourceType, Integer>();
	    		resource.put(trade.getResourceToGive(), trade.getRatio());
	    		if(playerList.get(playerId).canOfferTrade(playerId, resource)) {
	    			if(map.canPlayMaritimeTrade(playerId, trade.getPortType()))
	    				return true;
	    		}
    		}
    	}
    	return false;
    }
    
    public boolean canDiscardCards(int playerId, HashMap<ResourceType, Integer> cards) {
    	if(turnTracker.canPlayerDiscard(playerId)) {
    		if(playerList.get(playerId).canDiscardCards(cards)){
    			return true;
			}
    	}
    	return false;
    }
    
    public boolean canFinishTurn(int playerId) {
    	if(turnTracker.canPlayerBuild(playerId)){
    		return true;
        }
    	return false;
    }

	public TurnTracker getTurnTracker()
	{
		return turnTracker;
	}

	public Chat getChat()
	{
		return chat;
	}

	public Chat getGameHistory()
	{
		return gameHistory;
	}

}