package facade;

import java.util.ArrayList;
import java.util.List;

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
	
	public GameManager()
	{
		games = new ArrayList<ServerGame>();
	}
	
	/**
	 * Allows access to a specific game based on the games index
	 * @param indexOfGame
	 * @return The CatanModel associated with the given index.
	 */
	public ServerGame getGame(int indexOfGame)
	{
		return games.get(indexOfGame);
	}
	
	/**
	 * Allows access to the list of all available games.
	 * @return all available CatanModels
	 */
	public ArrayList<ServerGame> getGames()
	{
		return games;
	}
	
	public ServerGame getNewestGame() {
		return games.get(games.size() - 1);
	}
	
	public Game_DTO[] getGameList() {
		List<Game_DTO> gameList = new ArrayList<Game_DTO>();
		
		for (ServerGame game : games) {
			
			List<ServerPlayer> players = game.getPlayerList();
			List<Player_DTO> playerList = new ArrayList<Player_DTO>();
			
			for (ServerPlayer player : players) {
				playerList.add(new Player_DTO(player.getColor(), player.getName(), player.getPlayerID()));
			}
			Player_DTO[] arr = playerList.toArray(new Player_DTO[playerList.size()]);
			gameList.add(new Game_DTO(game.getGameName(), game.getGameId(), arr));
		}
		
		return gameList.toArray(new Game_DTO[gameList.size()]);
	}
	
	/**
	 * Creates a new game
	 * @throws ServerBoardException 
	 */
	public void createGame() throws ServerBoardException
	{
		games.add(new ServerGame());
	}
}
