package model;

import java.util.ArrayList;
import java.util.List;

import model.board.Board;
import model.cards.GameBank;
import model.cards.ResourceSet;
import model.game.GameModel;
import model.player.Player;
import model.trade.ITradeOffer;
import shared.locations.VertexLocation;

// this class needs to have canDo methods as well Do methods for any player actions
public class ClientFacade implements IClientFacade {

    private GameModel game;
    private int playerIndex;

    /**
     * Creates a new Game Object
     */
    public ClientFacade() {

    }

	@Override
	public boolean canUserLogin(String user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUserLogin(String user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canUserRegister(String user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUserRegister(String user, String password) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
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
	public boolean canBuildRoad(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doBuildRoad(VertexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doBuildSettlement(VertexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canBuildCity(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doBuildCity(VertexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canOfferTrade(ITradeOffer trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doOfferTrade(ITradeOffer trade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canAcceptTrade(ITradeOffer trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doAcceptTrade(ITradeOffer trade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canMaritimeTrade(ITradeOffer trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doMaritimeTrade(ITradeOffer trade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canDiscardCards(ResourceSet cards) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doDiscardCards(ResourceSet cards) {
		// TODO Auto-generated method stub
		
	}

}