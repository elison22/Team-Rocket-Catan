package proxy;

/**@author Chad
 * Jan 1, 2015
 * 
 * This class handles all general communications involving the current game. 
 * This includes updating the current game's state, reseting the command 
 * history, testing commands, and adding AIs to the current game.
 */
public class ProxyGame extends ServerProxy {
	
	/**Initializes the server's host and port 
	 * that the proxy will communicate with.
	 * 
	 * @param host the name or IP address of the server
	 * @param port the port that the server will use
	 * */
	public ProxyGame(String host, String port) {
		super(host, port);
	}
	
	/**(GET) Asks the server for the current state of the
	 * game in Json format.
	 * 
	 * @return Returns the game's json client model.
	 */
	public Object model() {
		return null;
	}

	/**(POST) Asks the server to clear the current game's command 
	 * history. For user-created games this reverts to the game's
	 * beginning (before the initial placement rounds). For the
	 * server's default games this reverts to the state immediately
	 * following the initial placement rounds.
	 * 
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public Object reset() {
		return null;
	}
	
	/**(POST) This method is used for testing/debugging purposes.
	 * It asks the server to execute the specified commands. The
	 * server will return the state of the game after the commands
	 * have been applied.
	 * 
	 * @param commands List of commands to be executed.
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public Object commandsPOST(Object commands) {
		return null;
	}
	
	/**(GET) Prompts the server for valid commands to be passed 
	 * to the Game.commandsPOST() method for testing/debugging.
	 * 
	 * @return List of commands.
	 */
	public Object commandsGET() {
		return null;
	}
	
	/**(POST) Requests the server to add an AI of the specified
	 * type to the current game.
	 * 
	 * @param addAIParams Should contain AIType (String). Valid 
	 * types can be retrieved from Game.listAI().
	 * @return Server's http response.
	 */
	public Object addAI(Object addAIParams) {
		return null;
	}
	
	/**(GET) Requests a list of valid AI types to be used as 
	 * parameters in Game.addAI().
	 * 
	 * @return List of AI types.
	 */
	public Object listAI() {
		return null;
	}
}
