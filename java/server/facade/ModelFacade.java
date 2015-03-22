package facade;

import serializer.ServerSerializer;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.dto.AcceptTrade_Params;
import shared.dto.BuildCity_Params;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.CreateGame_Params;
import shared.dto.DiscardCards_Params;
import shared.dto.JoinGame_Params;
import shared.dto.MaritimeTrade_Params;
import shared.dto.Monopoly_Params;
import shared.dto.OfferTrade_Params;
import shared.dto.RoadBuilding_Params;
import shared.dto.RobPlayer_Params;
import shared.dto.RollNumber_Params;
import shared.dto.SendChat_Params;
import shared.dto.Soldier_Params;
import shared.dto.YearOfPlenty_Params;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.List;

import model.sboard.ServerBoardException;

/**
 * The ModelFacade will be called by the Command Objects for any operation that deals with 
 * reading from or modifying a game. Every operation from the swagger page under the 'game,'
 * 'games,' 'moves,' and 'util' sections are handled here. The ModelFacade in turn will call
 * methods on its instance of the GameManager to ensure that methods are being called on the
 * correct game.
 */
public class ModelFacade implements IModelFacade {

	/**
	 * The ModelFacade's instance of GameManager
	 */
	private GameManager gameManager;
	private ServerSerializer serializer;
	
	public ModelFacade() {
		super();
		this.gameManager = new GameManager();
		this.serializer = new ServerSerializer();
	}

	/**
	 * Lists all games currently on the server
	 * @return returns a JSON string of the available games.
	 */
	@Override
	public String listGames() {
		return serializer.serializeGameList(gameManager.getGameList());
	}

	/**
	 * Creates a new game on the server
	 * @param params blah
	 * @return returns a JSON string with the new game information
	 */
	@Override
	public String createGame(CreateGame_Params params) {
		
		// Check that the game name is valid (alphanumeric under 25 chars)
		String namePattern = "^[0-9a-zA-Z]{1,25}$";
		if (!params.getName().matches(namePattern))
			return null;
		
		try {
			gameManager.createGame(params.getRandomNumbers(), params.getRandomTiles(), params.getRandomPorts(), params.getName());
		} catch (ServerBoardException e) {
			return null;
		}
		
		return serializer.serializeNewGame(gameManager.getNewestGame());
	}

	/**
	 * Allows a user to join a game if the game isn't full
	 * @param params blah
	 * @param player blah
     * @param playerId blah
	 * @return returns success or failure
	 */
	@Override
	public boolean joinGame(JoinGame_Params params, String player, int playerId) {
		// Verify the color is valid
		if (CatanColor.convert(params.getColor()) == null)
			return false;
		else
		    return gameManager.addPlayerToGame(params.getId(), player, playerId, params.getColor());
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
	public String loadGame(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the information for a specific game on the server
	 * @param gameID The ID of the game that has been requested
	 * @return returns the JSON string of the game information
	 */
	@Override
	public String getGameModel(int gameID) {
		return serializer.serializeGameModel(gameManager.getGame(gameID));
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
	 * @param gameCommands A JSON string of commands to run on the game
	 * @return returns a JSON of the game model after the commands are executed
	 */
	@Override
	public String executeGameCommands(int gameID, String gameCommands) {
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
     * @param playerIdx Who's sending this command
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String finishTurn(int gameID, int playerIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to buy a development card if there are any left
     * @param gameID The ID of the game that has been requested
     * @param playerIdx Who's buying this dev card
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String buyDevCard(int gameID, int playerIdx) {
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
     * @param playerIdx Who's playing this dev card
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doMonument(int gameID, int playerIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player do build a road
     * @param gameID The ID of the game that has been requested
     * @param roadParams all the params needed to build a road
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
     * @param params contains playerIdx sending offer, receiverIdx receiving offer, what you get/give
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
	public int getCreatedGameId() {
		return gameManager.getNewestGameId();
	}

	@Override
	public int getVersionId(int gameId) {
		return gameManager.getVersionId(gameId);
	}
}
