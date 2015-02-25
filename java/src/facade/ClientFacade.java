package facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import model.board.BoardException;
import model.board.Constructable;
import model.cards.ResourceSet;
import model.chat.Message;
import model.game.GameModel;
import model.game.TurnState;
import model.game.TurnTracker;
import model.player.Player;
import model.trade.DomesticTrade;
import model.trade.MaritimeTrade;
import proxy.IProxyFacade;
import proxy.MockProxy;
import proxy.ProxyFacade;
import proxy.ServerException;
import serializer.Serializer;
import serverpoller.ServerPoller;
import shared.definitions.*;
import shared.dto.AcceptTrade_Params;
import shared.dto.AddAI_Params;
import shared.dto.BuildCity_Params;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.BuyDevCard_Params;
import shared.dto.CreateGame_Params;
import shared.dto.DiscardCards_Params;
import shared.dto.FinishTurn_Params;
import shared.dto.Game_DTO;
import shared.dto.JoinGame_Params;
import shared.dto.Login_Params;
import shared.dto.MaritimeTrade_Params;
import shared.dto.Monopoly_Params;
import shared.dto.Monument_Params;
import shared.dto.OfferTrade_Params;
import shared.dto.Player_DTO;
import shared.dto.RoadBuilding_Params;
import shared.dto.RobPlayer_Params;
import shared.dto.RollNumber_Params;
import shared.dto.SendChat_Params;
import shared.dto.Soldier_Params;
import shared.dto.YearOfPlenty_Params;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.communication.LogEntry;
import client.data.PlayerInfo;

public class ClientFacade extends Observable implements IClientFacade {

    private GameModel game;
    private Player_DTO localPlayer;
    private int playerIndex;
    private IProxyFacade proxy;
    private Serializer serializer;
    private ServerPoller poller;
    public int versionNumber;
    
    public void updateGameModel(String json)
    {
    	try 
    	{
			game = serializer.deSerializeFromServer(game, json);
			
			if (playerIndex < 0)
				updatePlayerIndex();
				
			updated();
		} 
    	catch (BoardException e) 
    	{
			e.printStackTrace();
		}
    }

	public void updateGameList(String gameListJson) {
    	
    	// Notifies observers when gameList needs to be updated
		update(serializer.deSerializeGameList(gameListJson));
	}
    
    // For testing
    public ClientFacade() throws BoardException {
    	proxy = new MockProxy();
    	game = new GameModel();
    	serializer = new Serializer();
    }

    /**
     * Creates a new Game Object
     */
    public ClientFacade(String host, String port) throws BoardException {
    	proxy = new ProxyFacade(host, port);
    	game = new GameModel();
    	serializer = new Serializer();
    	localPlayer = new Player_DTO();
    	playerIndex = -1;
    }

	@Override
	public boolean canUserLogin(String user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUserLogin(String user, String password) {
		Login_Params login = new Login_Params(user, password);
		try {
			proxy.login(login);
		} catch (ServerException e) {
			return false;
		}
		
		// If login successful, initialize poller
		poller = new ServerPoller(3000, proxy, this);
		
		localPlayer = serializer.deSerializeUserCookie(proxy.getUserCookie());
		return true;
	}

	@Override
	public boolean canUserRegister(String user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUserRegister(String user, String password) {
		Login_Params login = new Login_Params(user, password);
		try {
			proxy.register(login);
		} catch (ServerException e) {
			return false;
		}
		
		// If login successful, initialize poller
		poller = new ServerPoller(3000, proxy, this);
				
		localPlayer = serializer.deSerializeUserCookie(proxy.getUserCookie());
		return true;
	}

	@Override
	public Game_DTO[] getGames() {
		try {
			return serializer.deSerializeGameList(proxy.list());
		} catch (ServerException e) {
			//e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean CreateGame(String gameName, boolean randTiles,
			boolean randPorts, boolean randNums) {
		try {
			// Create a game
			Game_DTO test = serializer.deSerializeGame(proxy.create(new CreateGame_Params(randTiles, randNums, randPorts, gameName)));
			
			// Join created game with default color
			proxy.join(new JoinGame_Params(test.getId(), CatanColor.WHITE.toString()));
			
		} catch (ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canJoinGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean joinGame(int gameId, String color) {
		try {
			proxy.join(new JoinGame_Params(gameId, color));
			updateGameModel(proxy.model(-1));
			poller.setInGame(true);
		} catch (ServerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void setPollerState(boolean waitingForPlayers) {
		poller.setWaitingForOtherPlayers(waitingForPlayers);
	}

	@Override
	public boolean canSave() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doSave() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doLoad(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canReset() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doReset() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResourceSet getPlayerResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getVersionNumber() {
		return game.getVersionNumber();
	}

	@Override
	public boolean canAddAI() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doAddAI(String AIType) {
		try {
			proxy.addAI(new AddAI_Params(AIType));
			updateGameModel(proxy.model(-1));
		} catch (ServerException e) {
			return false;
		}
		
		return true;
	}
	
	public Player_DTO getLocalPlayerInfo() {
		if (game.getPlayerList() != null && !game.getPlayerList().isEmpty()) {
			localPlayer.setColor(game.getPlayerList().get(playerIndex).getColor());
		}
		return localPlayer;
	}
	
	public Player_DTO[] getPlayerList() {
		Player_DTO[] players = new Player_DTO[game.getPlayerList().size()];
		for (int i = 0; i < players.length; ++i) {
			String color = game.getPlayerList().get(i).getColor();
			String name = game.getPlayerList().get(i).getName();
			int id = game.getPlayerList().get(i).getPlayerID();
			players[i] = new Player_DTO(color, name, id);
		}
		return players;
	}

	@Override
	public String[] getAIList() {
		String[] aiList = new String[game.getAiList().size()];
		aiList = game.getAiList().toArray(aiList);
		return aiList;
	}

	@Override
	public boolean doSendChat(String message) {
		try {
			updateGameModel(proxy.sendChat(new SendChat_Params(playerIndex, message)));
		} catch (ServerException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canRollDice() {
		return game.canRollDice(playerIndex);
	}

	@Override
	public boolean rollDice(int randomNum) {
	    try {
			updateGameModel(proxy.rollNumber(new RollNumber_Params(playerIndex, randomNum)));	
		} catch (ServerException e) {
			return false;
		}
	    return true;
	}

	@Override
	public boolean canPlaceRobber(HexLocation loc) {
		return game.canPlaceRobber(playerIndex, loc);
	}

	@Override
	public boolean doPlaceRobber(int victimIndex, HexLocation loc) {
		try {
			updateGameModel(proxy.robPlayer(new RobPlayer_Params(playerIndex, victimIndex, loc)));
		} catch (ServerException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canFinishTurn() {
		return game.canFinishTurn(playerIndex);
	}

	@Override
	public boolean finishTurn() {
		try {
			updateGameModel(proxy.finishTurn(new FinishTurn_Params(playerIndex)));
            //updateGameModel(proxy.model(-1));
		} catch (ServerException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canBuyDevCard() {
		return game.canBuyDevCard(playerIndex);
	}

	@Override
	public boolean buyDevCard() {
		try {
			updateGameModel(proxy.buyDevCard(new BuyDevCard_Params(playerIndex)));
		} catch (ServerException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canUseYearOfPlenty() {
		return game.canPlayDevCard(playerIndex, DevCardType.YEAR_OF_PLENTY);
	}

	@Override
	public boolean doUseYearOfPlenty(String resource1, String resource2) {		
		try {
			updateGameModel(proxy.Year_of_Plenty(new YearOfPlenty_Params(playerIndex, resource1, resource2)));
		} catch (ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canUseRoadBuilder() {
		return game.canPlayDevCard(playerIndex, DevCardType.ROAD_BUILD);
	}

	@Override
	public boolean doUseRoadBuilder(EdgeLocation location1, EdgeLocation location2) {
		try {
			updateGameModel(proxy.Road_Building(new RoadBuilding_Params(playerIndex, location1, location2)));
		} catch (ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canUseSoldier() {
		return game.canPlayDevCard(playerIndex, DevCardType.SOLDIER);
	}

	@Override
	public boolean doUseSoldier(int vicIndex, HexLocation loc) {
		try {
			updateGameModel(proxy.Soldier(new Soldier_Params(playerIndex, vicIndex, loc)));
		} catch (ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canUseMonopoly() {
		return game.canPlayDevCard(playerIndex, DevCardType.MONOPOLY);
	}

	@Override
	public boolean doUseMonopoly(String resource) {
		try {
			updateGameModel(proxy.Monopoly(new Monopoly_Params(resource, playerIndex)));
		} catch (ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canUseMonument() {
		return game.canPlayDevCard(playerIndex, DevCardType.MONUMENT);
	}

	@Override
	public boolean doUseMonument() {
		try {
			updateGameModel(proxy.Monument(new Monument_Params(playerIndex)));
		} catch (ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		return game.canBuildRoad(playerIndex, location);
	}

	@Override
	public boolean canBuildInitRoad(EdgeLocation location) {
		return game.canBuildInitRoad(playerIndex, location);
	}

	@Override
	public boolean doBuildRoad(EdgeLocation location, boolean freebie) {
		try {
			//updateGameModel(proxy.buildRoad(new BuildRoad_Params(playerIndex, location, freebie)));
			game = serializer.deSerializeFromServer(game, proxy.buildRoad(new BuildRoad_Params(playerIndex, location, freebie)));
		} catch (ServerException | BoardException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		return game.canBuildSettlement(playerIndex, location);
	}

    @Override
    public boolean canBuildInitSettlement(VertexLocation location) {
        return game.canBuildInitSettlement(playerIndex, location);
    }

	@Override
	public boolean doBuildSettlement(VertexLocation location, boolean freebie) {
		try {
			//updateGameModel(proxy.buildSettlement(new BuildSettlement_Params(playerIndex, location, freebie)));
			game = serializer.deSerializeFromServer(game, proxy.buildSettlement(new BuildSettlement_Params(playerIndex, location, freebie)));
		} catch (ServerException | BoardException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canBuildCity(VertexLocation location) {
		return game.canBuildCity(playerIndex, location);
	}

	@Override
	public boolean doBuildCity(VertexLocation location) {
		try {
			updateGameModel(proxy.buildCity(new BuildCity_Params(playerIndex, location)));
		} catch (ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canOfferTrade(DomesticTrade trade) {
		return game.canOfferTrade(playerIndex, trade);
	}

	@Override
	public boolean doOfferTrade(DomesticTrade trade) {
		try {
			updateGameModel(proxy.offerTrade(new OfferTrade_Params(playerIndex, trade.getOffer().getResources(), trade.getReceiver())));
		} catch (ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canAcceptTrade(DomesticTrade trade) {
		return game.canAcceptTrade(playerIndex, trade);
	}

	@Override
	public boolean doAcceptTrade(DomesticTrade trade, boolean accept) {
		try {
			updateGameModel(proxy.acceptTrade(new AcceptTrade_Params(playerIndex, accept)));
		} catch (ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canMaritimeTrade(MaritimeTrade trade) {
		return game.canMaritimeTrade(playerIndex, trade);
	}

	@Override
	public boolean doMaritimeTrade(MaritimeTrade trade) {
		try {
			updateGameModel(proxy.maritimeTrade(new MaritimeTrade_Params(playerIndex, 
							trade.getRatio(), trade.getResourceToGive().toString(), trade.getResourceToReceive().toString())));
		} catch (ServerException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canDiscardCards(HashMap<ResourceType, Integer> cards) {
		return game.canDiscardCards(playerIndex, cards);
	}

	@Override
	public boolean doDiscardCards(HashMap<ResourceType, Integer> cards) {
		try {
			updateGameModel(proxy.discardCards(new DiscardCards_Params(playerIndex, cards)));
		} catch (ServerException e) {
			return false;
		}
		return true;
	}
	
	public int getLocalPlayerIndex() {
		return playerIndex;
	}
	
	public boolean isYourTurn(int playerIndx){
		if(game.getPlayerTurn() == playerIndx)
			return true;
		
		return false;
	}
	
	public boolean isYourTurn(){
		if(game.getPlayerTurn() == playerIndex)
			return true;
		
		return false;
	}
	
	public boolean hasLongestRoad(int playerIndx) {
		return playerIndx == game.getLongestRoad();
	}
	
	public boolean hasLargestArmy(int playerIndx) {
		return playerIndx == game.getLargestArmy();
	}
	
	public Player getLocalPlayer() {
		return game.getPlayerList().get(playerIndex);
	}
	
	public PlayerInfo getPlayerInfo() {
		Player player = game.getPlayerList().get(playerIndex);
		PlayerInfo info = new PlayerInfo();
		
		info.setId(player.getPlayerID());
		info.setPlayerIndex(playerIndex);
		info.setName(player.getName());
		info.setColor(CatanColor.convert(player.getColor()));
		return info;
	}
	
	public ArrayList<Player> getPlayersOfGame() {
		return game.getPlayerList();
	}

    public HashMap<EdgeLocation, Constructable> getRoadPieces() {
        return game.getRoadPieces();
    }

    public HashMap<VertexLocation, Constructable> getBuildingPieces(){
        return game.getBuildingPieces();
    }
    
    public int getGameWinner() {
    	return game.getWinner();
    }
	
	public void updated() {
		versionNumber = game.getVersionNumber();
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	// This allows us to pass objects to our observers. It will be up to the
	// observers to make sure that the object passed is one they should use.
	public void update(Object obj) {
		setChanged();
		notifyObservers(obj);
		clearChanged();
	}

    public GameModel getLocalGame() {
        return game;
    }

    public TurnState getState() {
        return game.getTurnState();
    }
    
    public TurnTracker getTurnTacker() {
    	return game.getTurnTracker();
    }

    public HexLocation getRobberLoc() {
        return game.getRobberLoc();
    }

    public PortType getPortType(EdgeLocation loc) {
        return game.getPortType(loc);
    }

    public HexType getHexType(HexLocation loc) {
        return game.getHexType(loc);
    }

    public int getChit(HexLocation loc) {
        return game.getChit(loc);
    }

	/** Retrieves the chat messages in the current game model, then converts
	 * them to LogEntries.
	 * 
	 * @return A list of LogEntries associated with each record chat message.
	 */
	public List<LogEntry> getChatMessages() {
		
		List<LogEntry> chatEntries = new ArrayList<LogEntry>();
		for (Message message : game.getChat().getChatMessages()) {
			chatEntries.add(new LogEntry(game.getPlayerColorByName(message.getOwner()), message.getMessage()));
		}
		
		return chatEntries;
	}
	
	/** Retrieves the game history messages in the current game model, then converts
	 * them to LogEntries.
	 * 
	 * @return A list of LogEntries associated with each record chat message.
	 */
	public List<LogEntry> getHistoryMessages() {
		
		List<LogEntry> historyEntries = new ArrayList<LogEntry>();
		for (Message message : game.getGameHistory().getChatMessages()) {
			historyEntries.add(new LogEntry(game.getPlayerColorByName(message.getOwner()), message.getMessage()));
		}
		
		return historyEntries;
	}
	
	/** Updates the playIndex kept by the proxyFacade by finding the player
	 * with a matching id from the list of players in the current game.
	 */
	private void updatePlayerIndex() {
		for (Player p : game.getPlayerList()) {
			if (p.getPlayerID() == localPlayer.getId()) {
				playerIndex = p.getPlayerIdx();
				break;
			}
		}
	}

}
