package facade;

import java.util.ArrayList;
import java.util.Random;

import model.cards.ResourceSet;
import model.game.GameModel;
import model.trade.DomesticTrade;
import model.trade.MaritimeTrade;
import proxy.ProxyFacade;
import proxy.ServerException;
import serializer.Serializer;
import shared.definitions.DevCardType;
import shared.dto.BuyDevCard_Params;
import shared.dto.FinishTurn_Params;
import shared.dto.Login_Params;
import shared.dto.RobPlayer_Params;
import shared.dto.RollNumber_Params;
import shared.dto.SendChat_Params;
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
    private ProxyFacade proxy;
    private Serializer serializer;

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
	public void CreateGame(String gameName, boolean randTiles,
			boolean randPorts, boolean randNums) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canJoinGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void joinGame(int playerId, String color) {
		// TODO Auto-generated method stub
		
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
			serializer.deSerializeFromServer(proxy.sendChat(new SendChat_Params(playerIndex, message)));
		} catch (ServerException e) {
			// e.printStackTrace();
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
			proxy.rollNumber(new RollNumber_Params(playerIndex, randomNum));	
		} catch (ServerException e) {
			// e.printStackTrace();
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
			serializer.deSerializeFromServer(proxy.robPlayer(new RobPlayer_Params(playerIndex, victimIndex, loc)));
		} catch (ServerException e) {
			// e.printStackTrace();
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
			serializer.deSerializeFromServer(proxy.finishTurn(new FinishTurn_Params(playerIndex)));
		} catch (ServerException e) {
			// e.printStackTrace();
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
			serializer.deSerializeFromServer(proxy.buyDevCard(new BuyDevCard_Params(playerIndex)));
		} catch (ServerException e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean canUseYearOfPlenty() {
		return game.canPlayDevCard(playerIndex, DevCardType.YEAR_OF_PLENTY);
	}

	@Override
	public boolean doUseYearOfPlenty() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUseRoadBuilder() {
		return game.canPlayDevCard(playerIndex, DevCardType.ROAD_BUILD);
	}

	@Override
	public boolean doUseRoadBuilder() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUseSoldier() {
		return game.canPlayDevCard(playerIndex, DevCardType.SOLDIER);
	}

	@Override
	public boolean doUseSoldier() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUseMonopoly() {
		return game.canPlayDevCard(playerIndex, DevCardType.MONOPOLY);
	}

	@Override
	public boolean doUseMonopoly() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUseMonument() {
		return game.canPlayDevCard(playerIndex, DevCardType.MONUMENT);
	}

	@Override
	public boolean doUseMonument() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		return game.canBuildRoad(playerIndex, location);
	}

	@Override
	public boolean doBuildRoad(EdgeLocation location) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		return game.canBuildSettlement(playerIndex, location);
	}

	@Override
	public boolean doBuildSettlement(VertexLocation location) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canBuildCity(VertexLocation location) {
		return game.canBuildCity(playerIndex, location);
	}

	@Override
	public boolean doBuildCity(VertexLocation location) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canOfferTrade(DomesticTrade trade) {
		return game.canOfferTrade(playerIndex, trade);
	}

	@Override
	public boolean doOfferTrade(DomesticTrade trade) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canAcceptTrade(DomesticTrade trade) {
		return game.canAcceptTrade(playerIndex, trade);
	}

	@Override
	public boolean doAcceptTrade(DomesticTrade trade) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canMaritimeTrade(MaritimeTrade trade) {
		return game.canMaritimeTrade(playerIndex, trade);
	}

	@Override
	public boolean doMaritimeTrade(MaritimeTrade trade) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canDiscardCards(ResourceSet cards) {
		return game.canDiscardCards(playerIndex, cards);
	}

	@Override
	public boolean doDiscardCards(ResourceSet cards) {
		// TODO Auto-generated method stub
		return true;
	}

}