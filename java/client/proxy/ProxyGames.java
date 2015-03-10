package proxy;

/**@author Chad
 * Jan 1, 2015
 * 
 * This class handles all general game related communications with the server, 
 * including joining or creating new games, and saving or loading created games.
 */
public class ProxyGames {
	
	public ProxyGames() {}

	/** (GET) Retrieves information about all the currently running 
	 * games on the server. 
	 * 
	 * @return Json with the format:
	 * Game { title (String), id (int), players (array[Player]) }
	 * Player { color (String), name (String), id (int) }.
	 * @throws ServerException thrown if there was any problem reading the 
	 * given URL or if the server returns a non-OK response code.
	 */
	public String list() throws ServerException {
		return ServerProxy.getInstance().doGet("/games/list", null);
	}
	
	/** (POST) Creates a new game on the server with the specified
	 * random options.
	 * 
	 * @param createParams Should contain randomTiles (boolean),
	 * randomNumbers (boolean), randomPorts (boolean), and 
	 * name (String).
	 * @return Json with the new game's title (String), id (int), and
	 * an array[4] with empty Player objects.
	 * @throws ServerException 
	 */
	public String create(Object createParams) throws ServerException {
		return ServerProxy.getInstance().doPost("/games/create", createParams);
	}
	
	/** (POST) Adds the player to a specified game.
	 * 
	 * @param joinParams Should contain the game's id (int) and
	 * the player's chosen color (String).
	 * @return server's http response
	 * @throws ServerException 
	 */
	public Object join(Object joinParams) throws ServerException {
		return ServerProxy.getInstance().doPost("/games/join", joinParams);
	}
	
	/** (POST) Saves the current state of a given game to a file.
	 * 
	 * @param saveParams contains the game's id (int) and the
	 * name (String) of the file to save it to.
	 * @return server's http response
	 * @throws ServerException 
	 */
	public Object save(Object saveParams) throws ServerException {
		return ServerProxy.getInstance().doPost("/games/save", saveParams);
	}
	
	/** (POST) loads a previously saved game from file.
	 * 
	 * @param loadParams containts the name (String) of the file.
	 * @return server's http response
	 * @throws ServerException 
	 */
	public Object load(Object loadParams) throws ServerException {
		return ServerProxy.getInstance().doPost("/games/load", loadParams);
	}
}
