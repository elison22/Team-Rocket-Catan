package proxy;

import com.google.gson.JsonObject;

/**@author Chad
 * Jan 1, 2015
 * 
 * This class handles all general game related communications with the server, 
 * including joining or creating new games, and saving or loading created games.
 */
public class ProxyGames extends ServerProxy {

	public ProxyGames() {}
	
	/**Initializes the server's host and port that the proxy
	 * will operate with.
	 * 
	 * @param host the name or IP address of the server
	 * @param port the port that the server will use
	 * */
	public ProxyGames(String host, String port) {
		super(host, port);
	}

	/** (GET) Retrieves information about all the currently running 
	 * games on the server. 
	 * 
	 * @return Json with the format:
	 * Game { title (String), id (int), players (array[Player]) }
	 * Player { color (String), name (String), id (int) }.
	 */
	public Object list() {
		return null;
	}
	
	/** (POST) Creates a new game on the server with the specified
	 * random options.
	 * 
	 * @param createParams Should contain randomTiles (boolean),
	 * randomNumbers (boolean), randomPorts (boolean, and 
	 * name (String).
	 * @return Json with the new game's title (String), id (int), and
	 * an array[4] with empty Player objects.
	 */
	public Object create(JsonObject createParams) {
		return null;
	}
	
	/** (POST) Adds the player to a specified game.
	 * 
	 * @param joinParams Should contain the game's id (int) and
	 * the player's chosen color (String).
	 * @return server's http response
	 */
	public Object join(JsonObject joinParams) {
		return null;
	}
	
	/** (POST) Saves the current state of a given game to a file.
	 * 
	 * @param saveParams contains the game's id (int) and the
	 * name (String) of the file to save it to.
	 * @return server's http response
	 */
	public Object save(JsonObject saveParams) {
		return null;
	}
	
	/** (POST) loads a previously saved game from file.
	 * 
	 * @param loadParams containts the name (String) of the file.
	 * @return server's http response
	 */
	public Object load(JsonObject loadParams) {
		return null;
	}
}
