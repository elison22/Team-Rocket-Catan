package facade;

import java.util.ArrayList;
import java.util.List;

import command.ICommandObject;

import shared.dto.Game_DTO;
import shared.dto.Player_DTO;
import model.sboard.ServerBoardException;
import model.sgame.ServerGame;
import model.splayer.ServerPlayer;

/**
 * The GameManager holds the list of different games the server currently is running.
 * It will implement most of the save and load functions when those become relevant.
 * It also controls access to the games.
 * @author geccles
 *
 */
public class GameManager {
	
	private ArrayList<ServerGame> games;
	private ArrayList<ArrayList<ICommandObject>> commandsList;
	
	public GameManager()
	{
		games = new ArrayList<ServerGame>();
		commandsList = new ArrayList<ArrayList<ICommandObject>>();
	}
	
	/**
	 * Allows access to a specific game based on the games index
	 * @param indexOfGame
	 * @return The CatanModel associated with the given index.
	 */
	public ServerGame getGame(int indexOfGame)
	{
		if(indexOfGame < 0 || indexOfGame > games.size() - 1)
			return null;
		return games.get(indexOfGame);
	}
	
	public ICommandObject[] getCommands(int index)
	{
		ArrayList<ICommandObject> temp = commandsList.get(index);
		return temp.toArray(new ICommandObject[temp.size()]);
	}
	
	public void addCommand(int gameIndex, ICommandObject command)
	{
		commandsList.get(gameIndex).add(command);
	}
	
	/**
	 * Allows access to the list of all available games.
	 * @return all available CatanModels
	 */
	public ArrayList<ServerGame> getGames()
	{
		return games;
	}
	
	public Game_DTO getNewestGame() {
		ServerGame model = games.get(games.size() - 1);
		List<Player_DTO> playerList = new ArrayList<Player_DTO>();
		
		for (ServerPlayer player : model.getPlayerList()) {
			playerList.add(new Player_DTO(player.getColor(), player.getName(), player.getPlayerID()));
		}
		
		// If there are less than 4 players, fill it with empty player objects
		while (playerList.size() < 4) {
			playerList.add(new Player_DTO());
		}
		
		// Convert list to array
		Player_DTO[] arr = playerList.toArray(new Player_DTO[playerList.size()]);
		
		return new Game_DTO(model.getGameName(), model.getGameId(), arr);
	}
	
	public int getNewestGameId() {
		return games.size() - 1;
	}
	
	public Game_DTO[] getGameList() {
		List<Game_DTO> gameList = new ArrayList<Game_DTO>();
		
		for (ServerGame game : games) {
			
			if (game == null)
				continue;
			
			List<Player_DTO> playerList = new ArrayList<Player_DTO>();
			
			// Retrieve the players who have joined the game
			for (ServerPlayer player : game.getPlayerList()) {
				playerList.add(new Player_DTO(player.getColor(), player.getName(), player.getPlayerID()));
			}
			
			// If there are less than 4 players, fill it with empty player objects
			while (playerList.size() < 4) {
				playerList.add(new Player_DTO());
			}
			
			// Convert playerlist to array
			Player_DTO[] arr = playerList.toArray(new Player_DTO[playerList.size()]);
			
			gameList.add(new Game_DTO(game.getGameName(), game.getGameId(), arr));
		}
		
		return gameList.toArray(new Game_DTO[gameList.size()]);
	}
	
	/**
	 * Creates a new game
	 * @throws ServerBoardException 
	 */
	public void createGame(boolean randNumbers, boolean randTiles, boolean randPorts, String title, int seed) throws ServerBoardException
	{
		games.add(new ServerGame(randNumbers, randTiles, randPorts, title, seed));
		games.get(games.size() - 1).setGameId(games.size() - 1);
		commandsList.add(new ArrayList<ICommandObject>());
	}
	
	public boolean addPlayerToGame(int gameId, String playerName, int playerId, String color) {
		
		// Check that the gameId is valid
        if (games.size() <= gameId) {
            return false;
        }
        
		ServerGame game = games.get(gameId);
		
		// These variables are used if a player has already joined a game
		boolean alreadyInGame = false;
		int playerIndex = -1;

		// Make sure the player hasn't already joined the game or
		// the color isn't already taken
		for (ServerPlayer gamePlayer : game.getPlayerList()) {
			
			// If the color has already been taken, don't let them join
			if (gamePlayer.getColor().equalsIgnoreCase(color) && !gamePlayer.getName().equals(playerName))
				return false;

			// If the player is already in the game, don't re-add them, just 
			// change their color and update their id
			if (gamePlayer.getName().equals(playerName)) {
				alreadyInGame = true;
				playerIndex = gamePlayer.getPlayerIdx();
			}			
		}

		if (alreadyInGame)
			game.updatePlayerInfo(playerIndex, playerId, color);
		else if (game.getPlayerList().size() < 4)
			game.addPlayer(playerName, playerId, color);
		else 
			return false;
		return true;
	}
	
	public int getVersionId(int gameId) {
		return games.get(gameId).getVersionNumber();
	}
	
	public void addGame(ServerGame game) {
		
		// Pad gamesList if the game id is higher than the gamesList size
		while (games.size() <= game.getGameId())
			games.add(null);
		
		games.set(game.getGameId(), game);
	}
}
