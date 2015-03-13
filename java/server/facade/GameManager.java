package facade;

import java.util.ArrayList;

import model.sboard.ServerBoardException;
import model.sgame.ServerGame;

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
	
	/**
	 * Creates a new game
	 * @throws ServerBoardException 
	 */
	public void createGame() throws ServerBoardException
	{
		games.add(new ServerGame());
	}
}
