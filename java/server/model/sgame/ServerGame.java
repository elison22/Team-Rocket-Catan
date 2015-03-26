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
    private ServerBoard originalBoard;

    //**********************************************************
    //**** CONSTRUCTORS ****************************************
    //**********************************************************

    public ServerGame() throws ServerBoardException {
    	aiList = new ArrayList<String>();
    	aiList.add("LARGEST_ARMY");
        map = new ServerBoard(false, false, false);
    	originalBoard = map;
        versionNumber = 0;
    	winner = -1;
    }

    public ServerGame(boolean randNumbers, boolean randTiles, boolean randPorts, String title) throws ServerBoardException {
    	this.gameName = title;
    	versionNumber = 0;
    	playerList = new ArrayList<ServerPlayer>();
    	cardBank = new ServerGameBank();
    	turnTracker = new ServerTurnTracker();
    	map = new ServerBoard(randNumbers, randTiles, randPorts);
    	originalBoard = new ServerBoard(map);
    	chat = new ServerChat();
    	gameHistory = new ServerChat();
    	winner = -1;
    }
    
    public ServerGame(ServerBoard board, String title) {
    	this.gameName = title;
    	versionNumber = 0;
    	playerList = new ArrayList<ServerPlayer>();
    	cardBank = new ServerGameBank();
    	turnTracker = new ServerTurnTracker();
    	map = board;
    	originalBoard = new ServerBoard(map);
    	chat = new ServerChat();
    	gameHistory = new ServerChat();
    	winner = -1;
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
    
    public void incVersionNumber() {
    	versionNumber += 1;
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
     * Resets the entire game to before the initial placement phase
     * @return 
     * @return
     */
    public boolean resetGame() {
    	versionNumber = 0;
    	for(ServerPlayer player : playerList)
    		player.resetPlayer();
    	cardBank = new ServerGameBank();
    	turnTracker = new ServerTurnTracker();
    	map = new ServerBoard(originalBoard);
    	chat = new ServerChat();
    	gameHistory = new ServerChat();
    	winner = -1;
    	return true;
    }
    
    public boolean doSendChat(int playerIndex, String message) {
    	chat.sendChat(playerList.get(playerIndex).getName(), message);
        incVersionNumber();
    	return true;
    }

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
            
            calculateLongestRoad(playerIndex);
            
        } catch (ServerBoardException e) {
            return false;
        }
        
        // Update game history
        String name = playerList.get(playerIndex).getName();
        gameHistory.sendChat(name, name + " built a road");
        
        incVersionNumber();
        return true;
	}

    /**
     * Builds a settlement for the player at the given location
     * @param playerIndex blah
     * @param location blah
     * @return true if valid and successful, else false
     */
    public boolean doBuildSettlement(int playerIndex, VertexLocation location, boolean isFree) {
        try {
            map.doBuildSettlement(location, playerIndex);
            playerList.get(playerIndex).doBuildSettlement(isFree);
            
            if (playerList.get(playerIndex).addPoint() >= 10)
            	winner = playerIndex;
            
            cardBank.buyPiece(PieceType.SETTLEMENT);
         
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }

        //Distribute resources for second settlement placed in setup rounds
        if(isFree && turnTracker.getCurrentState() == ServerTurnState.SecondRound){
            ServerPlayer player = playerList.get(playerIndex);
            ArrayList<HexType> adjHexes = map.getAdjacentHexTypes(location);
            for(HexType type : adjHexes){
                switch(type){
                    case BRICK:
                        player.incResource(ResourceType.BRICK);
                        break;
                    case WOOD:
                        player.incResource(ResourceType.WOOD);
                        break;
                    case WHEAT:
                        player.incResource(ResourceType.WHEAT);
                        break;
                    case ORE:
                        player.incResource(ResourceType.ORE);
                        break;
                    case SHEEP:
                        player.incResource(ResourceType.SHEEP);
                        break;
                    default:
                        break;
                }
            }
        }
        
        // Update game history
        String name = playerList.get(playerIndex).getName();
        gameHistory.sendChat(name, name + " built a settlement");
        
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
            
            if (playerList.get(playerIndex).addPoint() >= 10)
            	winner = playerIndex;
            
            cardBank.buyPiece(PieceType.CITY);
            
            // Update game history
            String name = playerList.get(playerIndex).getName();
            gameHistory.sendChat(name, name + " built a city");
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
        incVersionNumber();
        return true;
    }

    /**
     * Moves the playerIndex and robs a player
     * @param playerIndex index of player robbing
     * @param victimIndex index of player being robbed
     * @param location blah
     * @return true if valid and successful, else false
     */
    public ResourceType doPlaceRobber(int playerIndex, int victimIndex, HexLocation location, ResourceType resource, boolean beenCalled) throws ServerBoardException {
    	if(beenCalled)
    	{
    		try {
            	// Place robber
                map.doPlayRobber(location);
                
                // Only steal if there actually is a victim
                if (victimIndex > -1) {
                	// Steal resources
                    playerList.get(victimIndex).decResource(resource);
                    playerList.get(playerIndex).incResource(resource);
                }
                
                // Set state to playing
                turnTracker.setCurrentState(ServerTurnState.Playing);
            } catch (ServerBoardException e) {
                e.printStackTrace();
                return null;
            }
    		return resource;
    	}
    	ResourceType stolenRes = null;
        // Place robber
        map.doPlayRobber(location);

        // Only steal if there actually is a victim
        if (victimIndex > -1) {
            // Steal resources
            stolenRes = playerList.get(victimIndex).getRandRes();
            playerList.get(playerIndex).incResource(stolenRes);

            // Update game history
            String name = playerList.get(playerIndex).getName();
            String victim = playerList.get(victimIndex).getName();
            gameHistory.sendChat(name, name + " moved the robber and robbed " + victim);
        } else {
            // Update game history
            String name = playerList.get(playerIndex).getName();
            gameHistory.sendChat(name, name + " moved the robber but couldn't rob anyone!");
        }

        // Set state to playing
        turnTracker.setCurrentState(ServerTurnState.Playing);
        
        incVersionNumber();
        return stolenRes;
    }

    /**
     *
     */
    public ResourceType doSoldier(int playerIndex, int victimIndex, HexLocation location, ResourceType stolenResource, boolean beenCalled) throws ServerBoardException {
    	ResourceType resource = doPlaceRobber(playerIndex, victimIndex, location, stolenResource, beenCalled);
        playerList.get(playerIndex).playDevCard(DevCardType.SOLDIER);
        calculateLargestArmy(playerIndex);
        incVersionNumber();
        return resource;

    }

    /**
     * blah
     */
    public boolean doYearOfPlenty(int playerIndex, ResourceType res1, ResourceType res2) {


        if(cardBank.canRemoveResource(res1)) {
            cardBank.removeResourceCard(res1);
            if (cardBank.canRemoveResource(res2)) {
                cardBank.removeResourceCard(res2);
                playerList.get(playerIndex).incResource(res1);
                playerList.get(playerIndex).incResource(res2);
            }
            else {
                cardBank.receiveResourceCard(res1);
                return false;
            }
        }
        else return false;
        playerList.get(playerIndex).playDevCard(DevCardType.YEAR_OF_PLENTY);
        
        // Update game history
        String name = playerList.get(playerIndex).getName();
        gameHistory.sendChat(name, name + " played a year of plenty card");
        
        incVersionNumber();
        return true;
    }

    /***/
    public boolean doMonopoly(int playerIndex, ResourceType resource) {
        for(int i = 0; i < 4; i++) {
            if(i == playerIndex) continue;
            while(playerList.get(i).decResource(resource))
                playerList.get(playerIndex).incResource(resource);
        }
        playerList.get(playerIndex).playDevCard(DevCardType.MONOPOLY);
        
        // Update game history
        String name = playerList.get(playerIndex).getName();
        gameHistory.sendChat(name, name + " played a monopoly card");
        
        incVersionNumber();
        return true;
    }

    /***/
    public boolean doMonument(int playerIndex) {
    	
    	 if (playerList.get(playerIndex).addPoint() >= 10)
         	winner = playerIndex;
    	 
        playerList.get(playerIndex).playDevCard(DevCardType.MONUMENT);
        
        // Update game history
        String name = playerList.get(playerIndex).getName();
        gameHistory.sendChat(name, name + " played a monument card");
        
        incVersionNumber();
        return true;
    }

    /***/
    public boolean doRoadBuilding(int playerIndex, EdgeLocation road1, EdgeLocation road2) {

        try {
            map.doBuildRoad(road1, playerIndex);
            map.doBuildRoad(road2, playerIndex);
            playerList.get(playerIndex).doBuildRoad(true);
            playerList.get(playerIndex).doBuildRoad(true);
            playerList.get(playerIndex).playDevCard(DevCardType.ROAD_BUILD);
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
        
        // Update game history
        String name = playerList.get(playerIndex).getName();
        gameHistory.sendChat(name, name + " played a road building card");
        
        incVersionNumber();
        return true;
    }

	/**
	 * Buys a development card for the player
	 * @param playerIndex blah
	 * @return true if valid and successful, else false
	 */
	public DevCardType doBuyDevCard(int playerIndex, DevCardType devCardType) {
        ServerDevCard chosenCard;
		if(devCardType != null)
		{
			chosenCard = cardBank.giveDevCard(devCardType);
			
		}
		else chosenCard = cardBank.giveDevCard();

        // adds a random dev card to the player's hand which also decrements the necessary res cards used to buy it
        playerList.get(playerIndex).addDevCard(chosenCard.getType());
        
        // Update game history
        String name = playerList.get(playerIndex).getName();
        gameHistory.sendChat(name, name + " bought a dev card");
        
        incVersionNumber();
		return chosenCard.getType();
	}

	/**
	 * Rewards resources or starts discard phase based on the number rolled
	 * @param playerIndex blah
	 * @param numberRolled blah
	 * @return true if valid and successful, else false
	 */
	public boolean doRoll(int playerIndex, int numberRolled) {
        turnTracker.setNumRolled(numberRolled);
        
        // Update game history
        String name = playerList.get(playerIndex).getName();
        gameHistory.sendChat(name, name + " rolled a " + numberRolled);
        
        // If the number rolled is a 7, begin discard phase
        if(numberRolled == 7){
        	
        	// Keep track of which players need to discard
        	ArrayList<Integer> playersToDiscard = new ArrayList<Integer>();
        	
        	// Figure out which players have more than 7 resources
            for(ServerPlayer player : playerList) {
                if(player.getResCount() > 7)
                    playersToDiscard.add(player.getPlayerIdx());
            }
            
            // If there are any players who need to discard, change to the
            // discarding phase and store the players
            if (!playersToDiscard.isEmpty()) {
            	turnTracker.setPlayersToDiscard(playersToDiscard);
            	turnTracker.setCurrentState(ServerTurnState.Discarding);
            }
            
            // If no players need to discard, enter robbing phase
            else {
            	turnTracker.setCurrentState(ServerTurnState.Robbing);
            }
        } 
        
        // Otherwise distribute resources as normal and reset playing state
        else {
        	
        	// Retrieve hexes whose chits match the dice roll
        	ArrayList<HexLocation> hexlocs = map.getHexLocsByNum(numberRolled);
            ResourceType resource = null;

            // For each hex matching the dice roll, distribute its resource to
            // players who have buildings next to it
            for (HexLocation location : hexlocs) {
                HexType hexType = map.getHexType(location);
                switch(hexType){
                    case WATER:
                        continue;
                    case DESERT:
                        continue;
                    case BRICK:
                        resource = ResourceType.BRICK;
                        break;
                    case WHEAT:
                        resource = ResourceType.WHEAT;
                        break;
                    case WOOD:
                        resource = ResourceType.WOOD;
                        break;
                    case SHEEP:
                        resource = ResourceType.SHEEP;
                        break;
                    case ORE:
                        resource = ResourceType.ORE;
                        break;
                    default:
                        continue;
                }

                // Give resources to players who have a city or settlement next
                // to the hex
                ArrayList<ServerConstructable> constructables = map.getAdjacentBuildings(location);
                for(ServerConstructable constructable : constructables){
                    int amount = 1;
                    if(!constructable.isSettlement())
                        amount = 2;
                    int owner = constructable.getOwner();
                    ServerPlayer player = playerList.get(owner);
                    for(int i = 0; i < amount; i++){
                        player.incResource(resource);
                    }
                }
            }
            
            // Set turn state to playing
            turnTracker.setCurrentState(ServerTurnState.Playing);
        }
        
        incVersionNumber();
		return true;
	}

    public boolean doDiscardCards(int playerIndex, HashMap<ResourceType, Integer> resourceList){
    	ServerPlayer player = playerList.get(playerIndex);
        player.setDiscarded();
    	// Discard the given resources for the given player
        ServerPlayer playerDiscarding = playerList.get(playerIndex);
        for(Map.Entry<ResourceType, Integer> entry : resourceList.entrySet()){
            for(int i = 0; i < entry.getValue(); i++){
                playerDiscarding.decResource(entry.getKey());
            }
        }
        
        // Remove player from the toDiscard list in the turntracker
        ArrayList<Integer> playersToDiscard = turnTracker.getPlayersToDiscard();
        for (int i = 0; i < playersToDiscard.size(); ++i) {
        	
        	// Find the player who has discarded and remove them from the list
        	if (playersToDiscard.get(i) == playerDiscarding.getPlayerIdx()) {
        		playersToDiscard.remove(i);
        		
        		// Update playersToDiscardList
                turnTracker.setPlayersToDiscard(playersToDiscard);
        		break;
        	}
        }
        
        // If everyone who needs to discard has done so, proceed to robbing
        // state, otherwise stay in discarding state
        if (turnTracker.getPlayersToDiscard().size() == 0)
        	turnTracker.setCurrentState(ServerTurnState.Robbing);
        
        incVersionNumber();
        return true;
    }
	
	/**
	 * Completes a maritime trade for the given player with the resources stored in the ServerTradeOffer
	 * @param playerIndex blah
	 * @param trade blah
	 * @return true if valid and successful, else false
	 */
	public boolean doMaritimeTrade(int playerIndex, ServerMaritimeTrade trade) {
        ServerPlayer player = playerList.get(playerIndex);
        int ratio = trade.getRatio();
        for(int i = 0; i < ratio; i++){
            player.decResource(trade.getResourceToGive());
        }
        player.incResource(trade.getResourceToReceive());
        incVersionNumber();
		return true;
	}

    /**
     * Changes resources listed in the ServerTradeOffer between the offerer and receiver
     * @param receiver blah
     * @return blah
     */
    public boolean doDomesticTrade(int receiver, boolean willAccept) {
        if (willAccept) {
        	ServerPlayer offeringPlayer = playerList.get(tradeOffer.getSender());
            ServerPlayer receivingPlayer = playerList.get(receiver);
            HashMap<ResourceType, Integer> offer = tradeOffer.getResources();
            
            for ( ResourceType resource : offer.keySet() ) {
                int amount = offer.get(resource);
                
                // If amount is greater than zero, then increase the resource
                // for the offering player and decrease for the accepting 
                // player
                if ( amount > 0 ) {
                    for ( int i = 0; i < amount; i++ ) {
                        offeringPlayer.decResource(resource);
                        receivingPlayer.incResource(resource);
                    }
                }
                
                // If amount is less than zero, then decrease the resource
                // for the offering player and increase for the accepting 
                // player
                else if (amount < 0) {
                    for ( int i = amount; i < 0; i++ ) {
                        offeringPlayer.incResource(resource);
                        receivingPlayer.decResource(resource);
                    }
                }
            }
        }
        
        tradeOffer = null;
        incVersionNumber();
        return true;
    }
	
	/**
	 * Sends an offer of a trade to the receiving player with the resources stored in the ServerTradeOffer
	 * @param offerer blah
	 * @param receiver blah
	 * @param tradeOffer blah
	 * @return blah
	 */
	public boolean offerDomesticTrade(int offerer, int receiver, HashMap<ResourceType, Integer> tradeOffer)
	{
        this.tradeOffer = new ServerTradeOffer(offerer, receiver, tradeOffer);
        incVersionNumber();
		return true;
	}

    public boolean finishTurn(int playerIndex){

    	// Update game history
        String name = playerList.get(playerIndex).getName();
        gameHistory.sendChat(name, name + "'s turn just ended");

        playerList.get(playerIndex).endTurn();
    	// If in the first round, increment turn order normally
    	if (turnTracker.getCurrentState() == ServerTurnState.FirstRound) {
    		if (playerIndex == 3)
    			turnTracker.setCurrentState(ServerTurnState.SecondRound);
    		else
    			++playerIndex;
    		turnTracker.setCurrentPlayerIndex(playerIndex);
    	}
    	
    	// If in the second round, decrement turn count
    	else if (turnTracker.getCurrentState() == ServerTurnState.SecondRound) {
    		if (playerIndex == 0)
    			turnTracker.setCurrentState(ServerTurnState.Rolling);
    		else
    			--playerIndex;
    		turnTracker.setCurrentPlayerIndex(playerIndex);
    	}
    	
    	// If in that playing state, turn order increases normally
    	else if (turnTracker.getCurrentState() == ServerTurnState.Playing) {
    		if(playerIndex == 3)
                turnTracker.setCurrentPlayerIndex(0);
            else
                turnTracker.setCurrentPlayerIndex(++playerIndex);
            turnTracker.setCurrentState(ServerTurnState.Rolling);
    	}
    	
        incVersionNumber();
        return true;
    }

    public void addPlayer(String player, int playerId, String color) {
        playerList.add(new ServerPlayer(playerList.size(), playerId, player, color));
    }
    
    public void updatePlayerColor(int playerIndex, String color) {
    	playerList.get(playerIndex).setColor(color);
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
            if(!map.canPlayRobber(loc))
                return false;
            if(!turnTracker.canPlayerRob(playerId))
                return false;
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
		return true;
	}
    
    /** Checks that the given victim index has any resources to be stolen
     * 
     * @param victimIdx The index of the victim
     * @return True if the victim has any resources, false otherwise
     */
    public boolean canRobPlayer(int victimIdx) {
    	ServerPlayer victim = playerList.get(victimIdx);
    	if (victim.getResCount() > 0)
    		return true;
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
     * @return	true if player is able to accept trade
     */
    public boolean canAcceptTrade(int playerId) {
        ServerTradeOffer trade = tradeOffer;
        if (playerList.get(playerId).canAcceptTrade(playerId, trade.getResources())){
        	return true;
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
    
    private void calculateLongestRoad(int playerIndex) {
    	
    	// If no player has the longest road yet
    	if (turnTracker.getLongestRoadPlayerIndex() < 0) {
    		
    		// If the player that just built a road should have longest road
    		if (playerList.get(playerIndex).getRemainingRoads() <= 10) {
    			turnTracker.setLongestRoadPlayerIndex(playerIndex);
    			if (playerList.get(playerIndex).addPoints(2) >= 10)
    				winner = playerIndex;
    		}
    	} 
    	
    	// Otherwise find out if the player that just built a road has fewer
    	// remaining roads than the current longest road player
    	else {
    		
    		if (playerList.get(playerIndex).getRemainingRoads() < playerList.get(turnTracker.getLongestRoadPlayerIndex()).getRemainingRoads()) {
    			
    			// Swap longest road card
    			playerList.get(turnTracker.getLongestRoadPlayerIndex()).addPoints(-2);
    			turnTracker.setLongestRoadPlayerIndex(playerIndex);
    			if (playerList.get(playerIndex).addPoints(2) >= 10)
    				winner = playerIndex;
    			
    		}
    	}
    }

    private void calculateLargestArmy(int playerIndex) {

        if(playerList.get(playerIndex).getSoldierDevs() < 3)
            return;

        if(turnTracker.getLargestArmyPlayerIndex() == playerIndex)
            return;

        // nobody has the longest road yet
        if(turnTracker.getLargestArmyPlayerIndex() < 0) {
            playerList.get(playerIndex).addPoints(2);
            turnTracker.setLargestArmyPlayerIndex(playerIndex);
        }
        // somebody has it
        // the current player will replace the old player
        else if(playerList.get(playerIndex).getSoldierDevs() > playerList.get(turnTracker.getLargestArmyPlayerIndex()).getSoldierDevs()) {
            playerList.get(playerIndex).addPoints(2);
            playerList.get(turnTracker.getLargestArmyPlayerIndex()).addPoints(-2);
            turnTracker.setLargestArmyPlayerIndex(playerIndex);
        }

    }
}
