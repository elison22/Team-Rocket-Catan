package model.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import model.board.Board;
import model.board.BoardException;
import model.board.Constructable;
import model.cards.GameBank;
import model.chat.Chat;
import model.player.Player;
import model.trade.DomesticTrade;
import model.trade.MaritimeTrade;
import model.trade.TradeOffer;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
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
    private ArrayList<String> aiList;
    private int winner;
    private TradeOffer tradeOffer;

    public GameModel() throws BoardException {
    	aiList = new ArrayList<String>();
    	aiList.add("LARGEST_ARMY");
        map = new Board(false, false, false);
        versionNumber = -1;
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
        if (turnTracker == null){
            return null;
        }
        return turnTracker.getCurrentState();
    }

    public int getLongestRoad() {
        return turnTracker.getLongestRoadPlayerIndex();
    }
    
    public int getLargestArmy() {
    	return turnTracker.getLargestArmyPlayerIndex();
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
		for(Player p: playerList) {
			if(winner == p.getPlayerID())
				return p.getPlayerIdx();
		}
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	public void setTradeOffer(TradeOffer trade) {
		tradeOffer = trade;
	}

	public TradeOffer getTradeOffer() {
		return tradeOffer;
	}
	
	public boolean canPlayerTrade(int playerIndex) {
		return turnTracker.canPlayerTrade(playerIndex);
	}

	/**
	 * Validates that game is in the correct game state, and the player owns and has not yet played the dev card 
	 * @param playerId	index of the player who wants to play Dev Card
	 * @param devCard	the specific development card wanting to be played
	 * @return	true if this card can be played by the player at this time
	 */
	public boolean canPlayDevCard(int playerId, DevCardType devCard) {
    	if(turnTracker.canPlayerPlayDev(playerId)) {
    		if(playerList.get(playerId).canPlayDevCard(devCard)){
    			return true;
            }
    	}
    	return false;
    }
    
    /**
     * Checks if game is in correct state, if there are any dev cards remaining, and if player has adequate resources to buy a dev card
     * @param	playerId index of the player wanting to buy a dev card
     * @return	true if player can buy a dev card now
     */
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
    
    /**
     * Checks if game state is in valid state for this operation
     * @param playerId	index of player wanting to place robber
     * @return	true if player can perform this action
     */
    public boolean canPlaceRobber(int playerId, HexLocation loc) {
        try {
            return map.canPlayRobber(loc);
        } catch (BoardException e) {
            e.printStackTrace();
        }
		return false;
	}
    
    /**
     * Checks the game state and index of who's turn it is to see if player can roll
     * @param playerId	index of player wanting to roll
     * @return	true if player can roll dice at this time
     */
    public boolean canRollDice(int playerId) {
    	if(turnTracker.canPlayerRoll(playerId))
    		return true;

    	return false;
    }
    
    // Checks to see if the gui should allow a player to build a road or not.
    public boolean canBuildRoad(int playerIndex) {
    	return turnTracker.canPlayerBuildRoadSettlement(playerIndex) &&
    		   playerList.get(playerIndex).canBuildRoad();
    }
    
    /**
     * Checks with turn state, player, and board to see if road can be built in certain location 
     * @param playerId	index of player wanting to build road
     * @param location	where the player wants to place road
     * @return	true if player can place road at location
     */
    public boolean canBuildRoad(int playerId, EdgeLocation location) {
    	if(turnTracker.canPlayerBuildRoadSettlement(playerId)) {
            try {
                if(map.canBuildRoad(location, playerId))
                    return true;
            } catch (BoardException e) {
                return false;
            }

    	}
    	return false;
    }

    public boolean canBuildSecondRoad(EdgeLocation first, EdgeLocation second, int playerID) {
        try {
            return map.canBuildSecondRoad(first, second, playerID);
        } catch (BoardException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean canBuildInitRoad(int playerId, EdgeLocation location) {
        //This will only be called during the setup rounds so the player doesn't need to check if
        //it can build a road. It is assumed it has enough road pieces and they are free to place.
        if(turnTracker.canPlayerBuildRoadSettlement(playerId)) {
            try {
                if(map.canBuildRoad(location, -1))
                    return true;
            }catch (BoardException e) {
                return false;
            }
        }
        return false;
    }
    
    // Checks to see if the gui should allow a player to build a settlement or not.
    public boolean canBuildSettlement(int playerIndex) {
    	return turnTracker.canPlayerBuildRoadSettlement(playerIndex) &&
    		   playerList.get(playerIndex).canBuildSettlement();
    }

    /**
     * Checks with game state, player, and board if player can build settlement at location
     * @param playerId	index of player wanting to build settlement
     * @param location	where player wants to build settlement
     * @return	true if player can build settlement at location
     */
    public boolean canBuildSettlement(int playerId, VertexLocation location) {
        if(turnTracker.canPlayerBuildRoadSettlement(playerId)) {
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

    public boolean canBuildInitSettlement(int playerId, VertexLocation location) {
        //This will only be called during the setup rounds so the player doesn't need to check if
        //it can build a settlement. It is assumed it has enough settlement pieces and they are free to place.
        if(turnTracker.canPlayerBuildRoadSettlement(playerId)) {
            try {
                if(map.canBuildSettlement(location, playerId))
                    return true;
            }catch (BoardException e) {
                return false;
            }
        }
        return false;
    }
    
    
    // Checks to see if the gui should allow a player to build a city or not.
    public boolean canBuildCity(int playerIndex) {
    	return turnTracker.canPlayerBuild(playerIndex) &&
    		   playerList.get(playerIndex).canBuildCity();
    }

    /**
     * Checks game state, player, and board to see if player can build city
     * @param playerId	index of player who wants to build city
     * @param location	where player wants to build city
     * @return	true if player can build city at location
     */
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

    /**
     * Checks if player has available resources to give in trade
     * @param playerId	index of player wanting to trade
     * @param trade	list of resources player wants to give and receive
     * @return	true if player has adequate resources to give in trade
     */
    public boolean canOfferTrade(int playerId, DomesticTrade trade) {
    	if(turnTracker.canPlayerBuild(playerId)) {
    		if(playerList.get(playerId).canOfferTrade(playerId, trade.getOffer())){
    			return true;
			}
    	}
    	return false;
    }
    
    /**
     * Checks if player has appropriate resources to give in trade offer
     * @param playerId	index of player to accept trade
     * @param trade	list of resources to give and receive
     * @return	true if player is able to accept trade
     */
    public boolean canAcceptTrade(int playerId, DomesticTrade trade) {
    	if(turnTracker.canPlayerBuild(playerId)) {
    		if(playerList.get(playerId).canAcceptTrade(playerId, trade.getOffer())){
    			return true;
			}
    	}
    	return false;
    }
    
    /**
     * Checks if player can perform this maritime trade
     * @param playerId	index of player wanting to trade
     * @param trade	object containing port type, what they want, etc.
     * @return	true if player can perform this maritimeTrade
     */
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
    
    /**
     * Checks to see if player is able to discard selected cards
     * @param playerId	index of player wanting to discard cards
     * @param cards	cards player is wanting to discard
     * @return	true if player can discard cards
     */
    public boolean canDiscardCards(int playerId, HashMap<ResourceType, Integer> cards) {
    	if(turnTracker.canPlayerDiscard(playerId)) {
    		if(playerList.get(playerId).canDiscardCards(cards)){
    			return true;
			}
    	}
    	return false;
    }
    
    /**
     * Checks to see if turn state is in playing mode 
     * @param playerId	index of player who wants to end their turn
     * @return	true if player can end their turn
     */
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

    public HexLocation getRobberLoc() {
        return map.getRobberLoc();
    }

    public PortType getPortType(EdgeLocation loc) {
        return map.getPortType(loc);
    }

    public HexType getHexType(HexLocation loc) {
        return map.getHexType(loc);
    }

    public int getChit(HexLocation loc) {
        return map.getChit(loc);
    }

    public HashMap<EdgeLocation, Constructable> getRoadPieces() {
        return map.getRoadPieces();
    }

    public HashMap<VertexLocation, Constructable> getBuildingPieces() {
        return map.getBuildingPieces();
    }
    
    /** Find the color associated with the given name.
     * @param name of the player.
     * @return Color of the player with the given name.
     */
    public CatanColor getPlayerColorByName(String name) {
    	for (Player player : playerList) {
    		if (player.getName().equals(name)) 
    			return CatanColor.convert(player.getColor());
    	}
    	return null;
    }

    /** Find the color associated with the given player index.
     * @param playerIndex of the player.
     * @return Color of the player with the given player index.
     */
    public CatanColor getPlayerColorByIndex(int playerIndex) {
        for (Player player : playerList) {
            if (player.getPlayerIdx() == playerIndex)
                return CatanColor.convert(player.getColor());
        }
        return null;
    }

    public HashSet<Integer> getPlayersToRob(HexLocation loc) {
        return map.getPlayersToRob(loc);
    }

    public HashSet<PortType> getPlayerPorts(int index) {
        return map.getPlayerPorts(index);
    }

}
