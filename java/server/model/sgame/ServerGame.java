package model.sgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import model.sboard.ServerBoard;
import model.sboard.ServerBoardException;
import model.sboard.ServerConstructable;
import model.scards.ServerDevCard;
import model.scards.ServerGameBank;
import model.schat.ServerChat;
import model.schat.ServerMessage;
import model.sgame.ServerTurnState;
import model.sgame.ServerTurnTracker;
import model.splayer.ServerPlayer;
import model.strade.ServerDomesticTrade;
import model.strade.ServerMaritimeTrade;
import model.strade.ServerTradeOffer;
import shared.definitions.*;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;


public class ServerGame {
	private String gameName;
    private int versionNumber;
    private ArrayList<ServerPlayer> playerList;   // holds all the players
    private ServerGameBank cardBank;              // holds all the resource cards and all the dev cards
    private ServerTurnTracker turnTracker;        // holds whose turn it is, as well as game state
    private ServerBoard map;
    private ServerChat chat;
    private ServerChat gameHistory;
    private ArrayList<String> aiList;
    private int winner;
    private ServerTradeOffer tradeOffer;
    private int gameId;

    //**********************************************************
    //**** CONSTRUCTORS ****************************************
    //**********************************************************

    public ServerGame() throws ServerBoardException {
    	aiList = new ArrayList<String>();
    	aiList.add("LARGEST_ARMY");
        map = new ServerBoard(false, false, false);
        versionNumber = -1;
    	//winner = -1;
    }

    public ServerGame(boolean randNumbers, boolean randTiles, boolean randPorts, String title) throws ServerBoardException {
    	this.gameName = title;
    	versionNumber = 0;
    	playerList = new ArrayList<ServerPlayer>();
    	cardBank = new ServerGameBank();
    	turnTracker = new ServerTurnTracker();
    	map = new ServerBoard(randNumbers, randTiles, randPorts);
    	chat = new ServerChat();
    	gameHistory = new ServerChat();
    }

    //**********************************************************
    //**** GETTERS AND SETTERS *********************************
    //**********************************************************

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

    public ArrayList<ServerPlayer> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<ServerPlayer> playerList) {
        this.playerList = playerList;
    }

    public HashMap<ResourceType, Integer> getCardBank() {
        return cardBank.getResCards();
    }
    
    public ArrayList<ServerDevCard> getDevBank() {
    	return cardBank.getDevCards();
    }

    public void setCardBank(ServerGameBank cardBank) {
        this.cardBank = cardBank;
    }

    public int getPlayerTurn() {
        return turnTracker.getCurrentPlayerIndex();
    }

    public ServerTurnState getTurnState() {
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

    public void setTurnTracker(ServerTurnTracker turnTracker) {
        this.turnTracker = turnTracker;
    }

    public ServerBoard getMap() {
        return map;
    }

    public void setMap(ServerBoard map) {
        this.map = map;
    }

    public ArrayList<String> getAiList() {
    	return aiList;
    }

    public void setChat(ServerChat chat) {
		this.chat = chat;
	}

	public void setGameHistory(ServerChat gameHistory) {
		this.gameHistory = gameHistory;
	}

	public int getWinner() {
		for(ServerPlayer p: playerList) {
			if(winner == p.getPlayerID())
				return p.getPlayerIdx();
		}
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public void setTradeOffer(ServerTradeOffer trade) {
		tradeOffer = trade;
	}

	public ServerTradeOffer getTradeOffer() {
		return tradeOffer;
	}

    public ServerTurnTracker getTurnTracker()
    {
        return turnTracker;
    }

    public ArrayList<ServerMessage> getChat()
    {
        return chat.getChatMessages();
    }

    public ArrayList<ServerMessage> getGameHistory()
    {
        return gameHistory.getChatMessages();
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

    public HashMap<EdgeLocation, ServerConstructable> getRoadPieces() {
        return map.getRoadPieces();
    }

    public HashMap<VertexLocation, ServerConstructable> getBuildingPieces() {
        return map.getBuildingPieces();
    }

    public CatanColor getPlayerColorByName(String name) {
        for (ServerPlayer player : playerList) {
            if (player.getName().equals(name))
                return CatanColor.convert(player.getColor());
        }
        return null;
    }

    public CatanColor getPlayerColorByIndex(int playerIndex) {
        for (ServerPlayer player : playerList) {
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    //**********************************************************
    //**** DO METHODS ******************************************
    //**********************************************************

	/**
	 * Builds a road for a player at the given location
	 * @param playerIndex blah
	 * @param location blah
	 * @return true if valid and successful, else false
	 */
	public boolean doBuildRoad(int playerIndex, EdgeLocation location, boolean isFree) {
        try {
            map.doBuildRoad(location, playerIndex);
            playerList.get(playerIndex).doBuildRoad(isFree);
            cardBank.buyPiece(PieceType.ROAD);
        } catch (ServerBoardException e) {
            return false;
        }

        return true;
	}

    /**
     * Builds a settlement for the player at the given location
     * @param playerIndex blah
     * @param location blah
     * @return true if valid and successful, else false
     */
    public boolean doBuildSettlement(int playerIndex, VertexLocation location) {
        try {
            map.doBuildSettlement(location, playerIndex);
            playerList.get(playerIndex).doBuildSettlement();
            playerList.get(playerIndex).addPoint();
            cardBank.buyPiece(PieceType.SETTLEMENT);
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Builds a city for the player at the given location
     * @param playerIndex blah
     * @param location blah
     * @return true if valid and successful, else false
     */
    public boolean doBuildCity(int playerIndex, VertexLocation location) {
        try {
            map.doBuildCity(location, playerIndex);
            playerList.get(playerIndex).doBuildCity();
            playerList.get(playerIndex).addPoint();
            cardBank.buyPiece(PieceType.CITY);
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

	/**
	 * Moves the robber and robs a player
	 * @param robber index of player robbing
	 * @param robbee index of player being robbed
	 * @param location blah
	 * @return true if valid and successful, else false
	 */
	public boolean doPlaceRobber(int robber, int robbee, HexLocation location) {
        try {
            map.doPlayRobber(location);
            ResourceType stolenRes = playerList.get(robber).getRandRes();
            playerList.get(robbee).incResource(stolenRes);
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}

    /***/
    public boolean doMonopoly(int playerIdx, ResourceType resource) {
        for(int i = 0; i < 4; i++) {
            if(i == playerIdx) continue;
            while(playerList.get(i).decResource(resource))
                playerList.get(playerIdx).incResource(resource);
        }
        playerList.get(playerIdx).playDevCard(DevCardType.MONOPOLY);
        return true;
    }

    /***/
    public boolean doMonument(int playerIndex) {
        playerList.get(playerIndex).addPoint();
        playerList.get(playerIndex).playDevCard(DevCardType.MONUMENT);
        return true;
    }

    /***/
    public boolean doRoadBuilding(int playerIdx, EdgeLocation road1, EdgeLocation road2) {

        try {
            map.doBuildRoad(road1, playerIdx);
            map.doBuildRoad(road2, playerIdx);
            playerList.get(playerIdx).doBuildRoad(true);
            playerList.get(playerIdx).doBuildRoad(true);
            playerList.get(playerIdx).playDevCard(DevCardType.ROAD_BUILD);
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

	/**
	 * Buys a development card for the player
	 * @param playerIndex blah
	 * @return true if valid and successful, else false
	 */
	public boolean doBuyDevCard(int playerIndex) {
		// canDo should be called prior to this
        ServerDevCard chosenCard = cardBank.giveDevCard();
        playerList.get(playerIndex).addDevCard(chosenCard.getType());
		return true;
	}

	/**
	 * Rewards resources or starts discard phase based on the number rolled
	 * @param playerIndex blah
	 * @param numberRolled blah
	 * @return true if valid and successful, else false
	 */
	public boolean doRoll(int playerIndex, int numberRolled)
	{
        //What do we use playerIndex for? According to the Swagger page, this is part of the /rollNumber operation
        //but shouldn't we already know whose turn it is from the TurnTracker?
        turnTracker.setNumRolled(numberRolled);
		return true;
	}

    public boolean doDiscardCards(int playerIndex, HashMap<Integer, ResourceType> resourceList){
        ServerPlayer playerDiscarding = playerList.get(playerIndex);
        for(Map.Entry<Integer, ResourceType> entry : resourceList.entrySet()){
            for(int i = 0; i < entry.getKey(); i++){
                playerDiscarding.decResource(entry.getValue());
            }
        }
        return false;
    }
	
	/**
	 * Completes a maritime trade for the given player with the resources stored in the ServerTradeOffer
	 * @param playerIndex blah
	 * @param tradeOffer blah
	 * @return true if valid and successful, else false
	 */
	public boolean doMaritimeTrade(int playerIndex, ServerTradeOffer tradeOffer)
	{
		return true;
	}

    /**
     * Changes resources listed in the ServerTradeOffer between the offerer and receiver
     * @param offerer blah
     * @param receiver blah
     * @param tradeOffer blah
     * @return blah
     */
    public boolean doDomesticTrade(int offerer, int receiver, ServerTradeOffer tradeOffer)
    {
        ServerPlayer offeringPlayer = playerList.get(offerer);
        ServerPlayer receivingPlayer = playerList.get(receiver);
        return true;
    }
	
	/**
	 * Sends an offer of a trade to the receiving player with the resources stored in the ServerTradeOffer
	 * @param offerer blah
	 * @param receiver blah
	 * @param tradeOffer blah
	 * @return blah
	 */
	public boolean sendDomesticTradeOffer(int offerer, int receiver, ServerTradeOffer tradeOffer)
	{
		return true;
	}

    public void addPlayer(String player, int playerId, String color) {
        playerList.add(new ServerPlayer(playerList.size(), playerId, player, color));
    }

    //**********************************************************
    //**** CAN DO METHODS **************************************
    //**********************************************************

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
        } catch (ServerBoardException e) {
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
    
    /**
     *  Checks to see if the gui should allow a player to build a road or not.
     * @param playerIndex
     * @return
     */
    public boolean canBuildRoad(int playerIndex) {
    	return turnTracker.canPlayerBuildRoadSettlement(playerIndex) &&
    		   playerList.get(playerIndex).canBuildRoad();
    }
    
    /**
     * Checks with turn state, player, and board to see if road can be built in certain location 
     * @param playerIndex index of player wanting to build road
     * @param location	where the player wants to place road
     * @return	true if player can place road at location
     */
    public boolean canBuildRoad(int playerIndex, EdgeLocation location) {

        try {
    	    if(!turnTracker.canPlayerBuildRoadSettlement(playerIndex))     //check the turn
                return false;
            if(!map.canBuildRoad(location, playerIndex))                   //check the board
                return false;
            if(!playerList.get(playerIndex).canBuildRoad())                //check the player
                return false;
        } catch (ServerBoardException e) {
            return false;
        }
        return true;

    }

	/**
	 * Checks to see if the player can build the second road of the RoadBuilding card
	 * @param first
	 * @param second
	 * @param playerID
	 * @return
	 */
    public boolean canBuildSecondRoad(EdgeLocation first, EdgeLocation second, int playerID) {
        try {
            return map.canBuildSecondRoad(first, second, playerID);
        } catch (ServerBoardException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks to see if the player picked a valid location for his initial roads
     * @param playerId
     * @param location
     * @return
     */
    public boolean canBuildInitRoad(int playerId, EdgeLocation location) {
        //This will only be called during the setup rounds so the player doesn't need to check if
        //it can build a road. It is assumed it has enough road pieces and they are free to place.
        if(turnTracker.canPlayerBuildRoadSettlement(playerId)) {
            try {
                if(map.canBuildRoad(location, -1))
                    return true;
            }catch (ServerBoardException e) {
                return false;
            }
        }
        return false;
    }
    
    /**
     *  Checks to see if the gui should allow a player to build a settlement or not.
     * @param playerIndex
     * @return
     */
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

        try {
            if(!turnTracker.canPlayerBuildRoadSettlement(playerId))     //check the turn
                return false;
            if(!map.canBuildSettlement(location, playerId))             //check the board
                return false;
            if(!playerList.get(playerId).canBuildSettlement())          //check the player
                return false;
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Checks to see if the player picked a valid location for his initial settlement
     * @param playerId
     * @param location
     * @return
     */
    public boolean canBuildInitSettlement(int playerId, VertexLocation location) {
        //This will only be called during the setup rounds so the player doesn't need to check if
        //it can build a settlement. It is assumed it has enough settlement pieces and they are free to place.
        if(turnTracker.canPlayerBuildRoadSettlement(playerId)) {
            try {
                if(map.canBuildSettlement(location, playerId))
                    return true;
            }catch (ServerBoardException e) {
                return false;
            }
        }
        return false;
    }

    /**
     *  Checks to see if the gui should allow a player to build a city or not.
     * @param playerIndex
     * @return
     */
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

        try {
            if(!turnTracker.canPlayerBuild(playerId))                   //check the turn
                return false;
            if(!map.canBuildCity(location, playerId))             //check the board
                return false;
            if(!playerList.get(playerId).canBuildCity())          //check the player
                return false;
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Checks if player has available resources to give in trade
     * @param playerId	index of player wanting to trade
     * @param trade	list of resources player wants to give and receive
     * @return	true if player has adequate resources to give in trade
     */
    public boolean canOfferTrade(int playerId, ServerDomesticTrade trade) {
    	if(turnTracker.canPlayerBuild(playerId)) {
            if (playerList.get(playerId).canOfferTrade(playerId, trade.getOffer())){
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
    public boolean canAcceptTrade(int playerId, ServerDomesticTrade trade) {
    	if(turnTracker.canPlayerBuild(playerId)) {
            if (playerList.get(playerId).canAcceptTrade(playerId, trade.getOffer())){
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
    public boolean canMaritimeTrade(int playerId, ServerMaritimeTrade trade) {
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


}
