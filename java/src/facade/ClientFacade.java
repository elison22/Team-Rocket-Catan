package facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.board.BoardException;
import model.cards.ResourceSet;
import model.game.GameModel;
import model.trade.DomesticTrade;
import model.trade.MaritimeTrade;
import proxy.IProxyFacade;
import proxy.MockProxy;
import proxy.ProxyFacade;
import proxy.ServerException;
import serializer.Serializer;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.dto.*;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

// this class needs to have canDo methods as well Do methods for any player actions
public class ClientFacade implements IClientFacade {

    // create DTO's to pass to the proxy as parameters of the do methods
    // when calling do methods, will receive a json string
    // pass this json string to the serializer to deserialize it
    // serializer will return client model object
    // use the client model object to make a game model object
    private GameModel game;
    private int playerIndex;
    private IProxyFacade proxy;
    private Serializer serializer;
    
    public void updateGameModel(String json)
    {
    	try 
    	{
			game = serializer.deSerializeFromServer(game, json);
		} 
    	catch (BoardException e) 
    	{
			e.printStackTrace();
		}
    }
    
    // For testing
    public ClientFacade() {
    	proxy = new MockProxy();
    	game = new GameModel();
    	serializer = new Serializer();
    }

    /**
     * Creates a new Game Object
     */
    public ClientFacade(String host, String port) {
    	proxy = new ProxyFacade(host, port);
    	game = new GameModel();
    	serializer = new Serializer();
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
			proxy.login(login);
		} catch (ServerException e) {
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<GameModel> getGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean CreateGame(String gameName, boolean randTiles,
			boolean randPorts, boolean randNums) {
		try {
			game = serializer.deSerializeFromServer(game, proxy.create(new CreateGame_Params(randTiles, randNums, randPorts, gameName)));
		} catch (ServerException | BoardException e) {
			// e.printStackTrace();
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
	public void joinGame(int gameId, String color) {
		//game = serializer.deSerializeFromServer(game, proxy.join(new JoinGame_Params(gameId, color)));
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
	public void doAddAI(String AIType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> getAIList() {
		return game.getAiList();
	}

	@Override
	public boolean doSendChat(String message) {
		try {
			game = serializer.deSerializeFromServer(game, proxy.sendChat(new SendChat_Params(playerIndex, message)));
		} catch (ServerException | BoardException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canRollDice() {
		return game.canRollDice(playerIndex);
	}

	@Override
	public boolean rollDice() {
		int min = 2;
		int max = 12;
		
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    try {
			game = serializer.deSerializeFromServer(game, proxy.rollNumber(new RollNumber_Params(playerIndex, randomNum)));	
		} catch (ServerException | BoardException e) {
			return false;
		}
	    return true;
	}

	@Override
	public boolean canPlaceRobber() {
		return game.canPlaceRobber(playerIndex);
	}

	@Override
	public boolean doPlaceRobber(int victimIndex, HexLocation loc) {
		try {
			game = serializer.deSerializeFromServer(game, proxy.robPlayer(new RobPlayer_Params(playerIndex, victimIndex, loc)));
		} catch (ServerException | BoardException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.finishTurn(new FinishTurn_Params(playerIndex)));
		} catch (ServerException | BoardException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.buyDevCard(new BuyDevCard_Params(playerIndex)));
		} catch (ServerException | BoardException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.Year_of_Plenty(new YearOfPlenty_Params(playerIndex, resource1, resource2)));
		} catch (BoardException | ServerException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.Road_Building(new RoadBuilding_Params(playerIndex, location1, location2)));
		} catch (BoardException | ServerException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.Soldier(new Soldier_Params(playerIndex, vicIndex, loc)));
		} catch (BoardException | ServerException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.Monopoly(new Monopoly_Params(resource, playerIndex)));
		} catch (BoardException | ServerException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.Monument(new Monument_Params(playerIndex)));
		} catch (BoardException | ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		return game.canBuildRoad(playerIndex, location);
	}

	@Override
	public boolean doBuildRoad(EdgeLocation location, boolean freebie) {
		try {
			game = serializer.deSerializeFromServer(game, proxy.buildRoad(new BuildRoad_Params(playerIndex, location, freebie)));
		} catch (BoardException | ServerException e) {
			return false;
		} 
		return true;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		return game.canBuildSettlement(playerIndex, location);
	}

	@Override
	public boolean doBuildSettlement(VertexLocation location, boolean freebie) {
		try {
			game = serializer.deSerializeFromServer(game, proxy.buildSettlement(new BuildSettlement_Params(playerIndex, location, freebie)));
		} catch (BoardException | ServerException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.buildCity(new BuildCity_Params(playerIndex, location)));
		} catch (BoardException | ServerException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.offerTrade(new OfferTrade_Params(playerIndex, trade.getOffer().getResources(), trade.getReceiver())));
		} catch (BoardException | ServerException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.acceptTrade(new AcceptTrade_Params(playerIndex, accept)));
		} catch (BoardException | ServerException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.maritimeTrade(new MaritimeTrade_Params(playerIndex, 
					trade.getRatio(), trade.getResourceToGive().toString(), trade.getResourceToReceive().toString())));
		} catch (BoardException | ServerException e) {
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
			game = serializer.deSerializeFromServer(game, proxy.discardCards(new DiscardCards_Params(playerIndex, cards)));
		} catch (BoardException | ServerException e) {
			return false;
		}
		return true;
	}

}