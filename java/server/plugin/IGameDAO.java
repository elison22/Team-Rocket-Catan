package plugin;

/**
 * @author Chad
 * 
 * This class defines all of the methods that will be called by the server
 * to perform any game related operation with any given database.
 *
 */
public interface IGameDAO {
	
	/**Requests the database to insert or create a new entry for the given game.
	 * 
	 * @param gameJson The json of the new game.
	 * @return True if successful, false if otherwise.
	 */
	public boolean createGame(String gameJson);
	
	/**Requests the the entry for the given gameID gets updated or overwritten
	 * by the given game json.
	 * 
	 * @param gameID The ID of the game to be updated.
	 * @param gameJson The updated json of the game.
	 * @return True if successful, false if otherwise.
	 */
	public boolean updateGame(int gameID, String gameJson);
	
	/**Requests that the commands for the given gameID gets updated or 
	 * overwritten with the given commands json.
	 * 
	 * @param gameID The id of the game whose commands are to be updated.
	 * @param commandsJson The json formatted commands.
	 * @return True if successful, false if otherwise.
	 */
	public boolean updateGameCommands(int gameID, String commandsJson);
	
	/**Requests the database for the game with the given gameID.
	 * 
	 * @param gameID The id of the game to be retrieved.
	 * @return The game blob associated with the given id.
	 */
	public Object getGame(int gameID);
	
	/**Requests the database for the commands associated with the given gameID.
	 * 
	 * @param gameID The id of the game whose commands are needed.
	 * @return The blob of the commands associated with the given id.
	 */
	public Object getGameCommands(int gameID);
	
	/**Requests all of the games in the database.
	 * 
	 * @return A list of all of the game blobs stored in the database.
	 */
	public Object getAllGames();
	
	/**Requests all of the commands for each game stored in the database.
	 * 
	 * @return A list of all of the commands blobs for each game in the database.
	 */
	public Object getAllGameCommands();
	
	/**Requests the the game with the given id is deleted from the database.
	 * 
	 * @param gameID The id of the game to be deleted.
	 * @return True if successful, false if otherwise.
	 */
	public boolean removeGame(int gameID);

}
