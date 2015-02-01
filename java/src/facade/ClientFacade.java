package facade;

import java.util.ArrayList;

import model.cards.ResourceSet;
import model.game.GameModel;
import model.trade.DomesticTrade;
import model.trade.MaritimeTrade;
import proxy.ProxyFacade;
import proxy.ServerException;
import serializer.Serializer;
import shared.dto.Login_Params;
import shared.locations.EdgeLocation;
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
			//e.printStackTrace();
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
			// e.printStackTrace();
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
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doSendChat(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRollDice() {
		return game.canRollDice(playerIndex);
	}

	@Override
	public void rollDice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canRobPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doRobPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canFinishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void finishTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canBuyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void buyDevCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canPlayYearOfPlenty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doPlayYearOfPlenty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canPlayRoadBuilding() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doPlayRoadBuilding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canPlaySoldier() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doPlaySoldier() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canPlayMonopoly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doPlayMonopoly() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canPlayMonument() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doPlayMonument() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		return game.canBuildRoad(playerIndex, location);
	}

	@Override
	public void doBuildRoad(EdgeLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		return game.canBuildSettlement(playerIndex, location);
	}

	@Override
	public void doBuildSettlement(VertexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canBuildCity(VertexLocation location) {
		return game.canBuildCity(playerIndex, location);
	}

	@Override
	public void doBuildCity(VertexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canOfferTrade(DomesticTrade trade) {
		return game.canOfferTrade(playerIndex, trade);
	}

	@Override
	public void doOfferTrade(DomesticTrade trade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canAcceptTrade(DomesticTrade trade) {
		return game.canAcceptTrade(playerIndex, trade);
	}

	@Override
	public void doAcceptTrade(DomesticTrade trade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canMaritimeTrade(MaritimeTrade trade) {
		return game.canMaritimeTrade(playerIndex, trade);
	}

	@Override
	public void doMaritimeTrade(MaritimeTrade trade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canDiscardCards(ResourceSet cards) {
		return game.canDiscardCards(playerIndex, cards);
	}

	@Override
	public void doDiscardCards(ResourceSet cards) {
		// TODO Auto-generated method stub
		
	}

}