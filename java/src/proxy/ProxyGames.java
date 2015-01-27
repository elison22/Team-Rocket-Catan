package proxy;

/**@author Chad
 * Jan 1, 2015
 * 
 * This class handles all general game related communications with the server, 
 * including joining or creating new games, and saving or loading created games.
 */
public class ProxyGames extends ServerProxy {
	
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
	 * @throws ServerException thrown if there was any problem reading the 
	 * given URL or if the server returns a non-OK response code.
	 */
	public Object list() throws ServerException {
		return doGet("/games/list");
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
	public Object create(Object createParams) throws ServerException {
		return doPost("/games/create", createParams);
	}
	
	/** (POST) Adds the player to a specified game.
	 * 
	 * @param joinParams Should contain the game's id (int) and
	 * the player's chosen color (String).
	 * @return server's http response
	 * @throws ServerException 
	 */
	public Object join(Object joinParams) throws ServerException {
		return doPost("/games/join", joinParams);
	}
	
	/** (POST) Saves the current state of a given game to a file.
	 * 
	 * @param saveParams contains the game's id (int) and the
	 * name (String) of the file to save it to.
	 * @return server's http response
	 * @throws ServerException 
	 */
	public Object save(Object saveParams) throws ServerException {
		return doPost("/games/save", saveParams);
	}
	
	/** (POST) loads a previously saved game from file.
	 * 
	 * @param loadParams containts the name (String) of the file.
	 * @return server's http response
	 * @throws ServerException 
	 */
	public Object load(Object loadParams) throws ServerException {
		return doPost("/games/load", loadParams);
	}
	
	 // FOR TESTING:
	
//	public static void main(String args[]) {
//		ProxyGames pg = new ProxyGames("localhost", "8081");
//		try {
//			System.out.println(pg.list());
//		} catch (ServerException e) {
//			System.out.println(e.getMessage());
//		}
//	}
}
