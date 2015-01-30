package proxy;

/**@author Chad
 * Jan 1, 2015
 * 
 * This class handles all general communications involving the current game. 
 * This includes updating the current game's state, reseting the command 
 * history, testing commands, and adding AIs to the current game.
 */
public class ProxyGame {
	
	public ProxyGame() {}
	
	/**(GET) Asks the server for the current state of the
	 * game in Json format.
	 * 
	 * @return Returns the game's json client model.
	 * @throws ServerException 
	 */
	public String model(int modelVersion) throws ServerException {
		return ServerProxy.getInstance().doGet("/game/model", modelVersion);
	}

	/**(POST) Asks the server to clear the current game's command 
	 * history. For user-created games this reverts to the game's
	 * beginning (before the initial placement rounds). For the
	 * server's default games this reverts to the state immediately
	 * following the initial placement rounds.
	 * 
	 * @return Returns the game's json client model (See Game.model()).
	 * @throws ServerException 
	 */
	public String reset() throws ServerException {
		return ServerProxy.getInstance().doPost("/game/reset", null);
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
	 * @return True if successful, false if otherwise.
	 * @throws ServerException 
	 */
	public boolean addAI(Object addAIParams) throws ServerException {
		// Get server response
		String result = ServerProxy.getInstance().doPost("/game/addAI", addAIParams);
				
		if (result.equals("Success\n")) 
			return true;
				
		return false;
	}
	
	/**(GET) Requests a list of valid AI types to be used as 
	 * parameters in Game.addAI().
	 * 
	 * @return List of AI types.
	 * @throws ServerException 
	 */
	public Object listAI() throws ServerException {
		return ServerProxy.getInstance().doGet("/game/listAI", null);
	}
}
