package facade;

import command.ICommandObject;

import model.sgame.ServerGame;
import shared.dto.*;

/**
 * Mock implementation of the IModelFacade for use in testing the server.  Methods will always return the same values.
 * @author geccles
 *
 */
public class MockModelFacade implements IModelFacade
{

	/**
	 * The MockFacade's instance of GameManager
	 */
	@SuppressWarnings("unused")
	private GameManager gameManager;
	
	/**
	 * Lists all games currently on the server
	 * @return returns a JSON string of the available games.
	 */
	@Override
	public String listGames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates a new game on the server
	 * @param params blah
	 * @return returns a JSON string with the new game information
	 */
	@Override
	public String createGame(CreateGame_Params params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a user to join a game if the game isn't full
	 * @param userId blah
	 * @param user blah
	 * @return returns success or failure
	 */
	@Override
	public boolean joinGame(JoinGame_Params params, String user, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Allows a user to save the game
	 * @param gameID The ID of the game that has been requested
	 * @param fileName The name to save the file under
	 * @return returns success or failure
	 */
	@Override
	public boolean saveGame(int gameID, String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Allows a user to load a game
	 * @param fileName The name of the file to load
	 * @return returns a JSON string with the state of the saved game
	 */
	@Override
	public boolean loadGame(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gets the information for a specific game on the server
	 * @param gameID The ID of the game that has been requested
	 * @return returns the JSON string of the game information
	 */
	@Override
	public String getGameModel(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Resets a game to the default setup
	 * @param gameID The ID of the game that has been requested
	 * @return returns the JSON string of a default game
	 */
	@Override
	public String resetGame(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the commands the game has been given so far
	 * @param gameID The ID of the game that has been requested
	 * @return returns a JSON string of all previous commands
	 */
	@Override
	public String getGameCommands(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes a list of commands on a game to put it in a desired state
	 * @param gameID The ID of the game that has been requested
	 * @param commandsList list of commands
	 * @return returns a JSON of the game model after the commands are executed
	 */
	@Override
	public String executeGameCommands(ICommandObject[] commandsList) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates a new chat message for a specific player
     * @param gameID The ID of the game that has been requested
     * @param chatParams contains int playerIdx and string message
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String sendChat(int gameID, SendChat_Params chatParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Rolls the dice for a player's turn
     * @param gameID The ID of the game that has been requested
     * @param rollNum contains number rolled and player
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String rollNumber(int gameID, RollNumber_Params rollNum) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to rob another after placing the robber on a tile they own
     * @param gameID The ID of the game that has been requested
     * @param robParams contains robbing info
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String robPlayer(int gameID, RobPlayer_Params robParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ends a player's turn
     * @param gameID The ID of the game that has been requested
     * @param params Who's sending this command
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String finishTurn(int gameID, FinishTurn_Params params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to buy a development card if there are any left
     * @param gameID The ID of the game that has been requested
     * @param params Who's buying this dev card
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String buyDevCard(int gameID, BuyDevCard_Params params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes the effects of playing a year of plenty dev card
     * @param gameID The ID of the game that has been requested
     * @param params contains year plenty params
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doYearOfPlenty(int gameID, YearOfPlenty_Params params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes the effects of playing a road building dev card
     * @param gameID The ID of the game that has been requested
     * @param roadParams contains playerIndx, and 2 road locations
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doRoadBuilding(int gameID, RoadBuilding_Params roadParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes the effects of playing a soldier dev card
     * @param gameID The ID of the game that has been requested
     * @param params contains playerIdx of who played card, victimIdx, location to move robber 
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doSoldier(int gameID, Soldier_Params params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes the effects of playing a monopoly dev card
     * @param gameID The ID of the game that has been requested
     * @param params contains playerIdx and the resource type
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doMonopoly(int gameID, Monopoly_Params params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes the effects of playing a monument dev card
     * @param gameID The ID of the game that has been requested
     * @param params blah
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doMonument(int gameID, Monument_Params params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player do build a road
     * @param gameID The ID of the game that has been requested
     * @param roadParams The parameters of the action
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String buildRoad(int gameID, BuildRoad_Params roadParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to build a settlement
     * @param gameID The ID of the game that has been requested
     * @param params contains playerIdx, location, and whether/not its free 
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String buildSettlement(int gameID, BuildSettlement_Params params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to build a city
     * @param gameID The ID of the game that has been requested
     * @param params contains playerIdx and location 
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String buildCity(int gameID, BuildCity_Params params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to offer a trade to another player
     * @param gameID The ID of the game that has been requested
     * @param tradeParams contains playerIdx sending offer, receiverIdx receiving offer, what you get/give
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String offerTrade(int gameID, OfferTrade_Params tradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes a trade that was previously offered between two players
     * @param gameID The ID of the game that has been requested
     * @param acceptParams contains playerIdx of who is accept/reject and if they did accept
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String acceptTrade(int gameID, AcceptTrade_Params acceptParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes a maritime trade
     * @param gameID The ID of the game that has been requested
     * @param tradeParams contains playerIdx, ratio, resource to give, resource to receive 
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String maritimeTrade(int gameID, MaritimeTrade_Params tradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Removes resource cards from a player's hand if they have more than
	 * seven cards when a seven is rolled.
     * @param gameID The ID of the game that has been requested
     * @param cardParams contains playerIdx, list of resources being discarded 
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String discardCards(int gameID, DiscardCards_Params cardParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCreatedGameId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVersionId(int gameId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean verifyTurn(int gameId, int playerId, int playerIndex) {
		return false;
	}

	@Override
	public ServerGame getGame(int gameId) {
		// TODO Auto-generated method stub
		return null;
	}

}
