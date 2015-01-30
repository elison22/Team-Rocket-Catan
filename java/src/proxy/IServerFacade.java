package proxy;

/**@author Chad
 * Jan 22, 2015
 * 
 * Provides declarations for all of the methods to communicate with the server.
 * 
 */
public interface IServerFacade {

	/**POSTs login info to the server, which will then attempt
	 * to log the user in. 
	 * 
	 * @param loginParams should contain a username (String) and
	 * a password (String).
	 * @return http response from server
	 * @throws ServerException is thrown either when the output from the server
	 * is different than what is expected by the client, or if there was a 
	 * problem connecting to the server.
	 */
	public boolean login(Object loginParams) throws ServerException;
	
	/**POSTs new user info to the server which will attempt
	 * to create a new user account if the username is not 
	 * already in use. 
	 * 
	 * @param registerParams should contain a username (String),
	 * and a password (String).
	 * @return http response from server
	 * @throws ServerException is thrown either when the output from the server
	 * is different than what is expected by the client, or if there was a 
	 * problem connecting to the server.
	 */
	public boolean register(Object registerParams) throws ServerException;

	/** (GET) Retrieves information about all the currently running 
	 * games on the server. 
	 * 
	 * @return Json String with the format:
	 * Game { title (String), id (int), players (array[Player]) }
	 * Player { color (String), name (String), id (int) }.
	 * @throws ServerException 
	 */
	public String list() throws ServerException;
	
	/** (POST) Creates a new game on the server with the specified
	 * random options.
	 * 
	 * @param createParams Should contain randomTiles (boolean),
	 * randomNumbers (boolean), randomPorts (boolean), and 
	 * name (String).
	 * @return Json String with the new game's title (String), id (int), and
	 * an array[4] with empty Player objects.
	 * @throws ServerException 
	 */
	public String create(Object createParams) throws ServerException;
	
	/** (POST) Adds the player to a specified game.
	 * 
	 * @param joinParams Should contain the game's id (int) and
	 * the player's chosen color (String).
	 * @return server's http response
	 * @throws ServerException 
	 */
	public Object join(Object joinParams) throws ServerException;
	
	/** (POST) Saves the current state of a given game to a file.
	 * 
	 * @param saveParams contains the game's id (int) and the
	 * name (String) of the file to save it to.
	 * @return server's http response
	 * @throws ServerException 
	 */
	public Object save(Object saveParams) throws ServerException;
	
	/** (POST) loads a previously saved game from file.
	 * 
	 * @param loadParams containts the name (String) of the file.
	 * @return server's http response
	 * @throws ServerException 
	 */
	public Object load(Object loadParams) throws ServerException;
	
	/**(GET) Asks the server for the current state of the
	 * game in Json format.
	 * 
	 * @return Returns the game's json client model.
	 * @throws ServerException 
	 */
	public String model(int modelVersion) throws ServerException;

	/**(POST) Asks the server to clear the current game's command 
	 * history. For user-created games this reverts to the game's
	 * beginning (before the initial placement rounds). For the
	 * server's default games this reverts to the state immediately
	 * following the initial placement rounds.
	 * 
	 * @return Returns the game's json client model.
	 * @throws ServerException 
	 */
	public String reset() throws ServerException;
	
	/**(POST) This method is used for testing/debugging purposes.
	 * It asks the server to execute the specified commands. The
	 * server will return the state of the game after the commands
	 * have been applied.
	 * 
	 * @param commands List of commands to be executed.
	 * @return Returns the game's json client model (See model()).
	 */
	public Object commandsPOST(Object commands);
	
	/**(GET) Prompts the server for valid commands to be passed 
	 * to the Game.commandsPOST() method for testing/debugging.
	 * 
	 * @return List of commands.
	 */
	public Object commandsGET();
	
	/**(POST) Requests the server to add an AI of the specified
	 * type to the current game.
	 * 
	 * @param addAIParams Should contain AIType (String). Valid 
	 * types can be retrieved from Game.listAI().
	 * @return True if successful, false if otherwise.
	 * @throws ServerException 
	 */
	public boolean addAI(Object addAIParams) throws ServerException;
	
	/**(GET) Requests a list of valid AI types to be used as 
	 * parameters in Game.addAI().
	 * 
	 * @return List of AI types.
	 * @throws ServerException 
	 */
	public Object listAI() throws ServerException;
	
	/**(POST) Sends a player's chat message to the server.
	 * 
	 * @param sendChatParams Should contain the playerIndex (int)
	 * and the message content (String)
	 * @return Returns the game's json client model (See model()).
	 */
	public String sendChat(Object sendChatParams);
	
	/**(POST) Sends the current player's dice roll to the server.
	 * 
	 * @param rollNumberParams Should contain the playerIndex (int 0-3)
	 * and the rolled number (int 2-12)
	 * @return Returns the game's json client model (See model()).
	 */
	public String rollNumber(Object rollNumberParams);
	
	/**(POST) Sends the new robber location as well as the index of
	 * the player to be robbed to the server.
	 * 
	 * @param robPlayerParams Should contain the current player's 
	 * playerIndex (int 0-3), the victimIndex (int 0-3), and the 
	 * robber's new location (HexLocation).
	 * @return Returns the game's json client model (See model()).
	 */
	public String robPlayer(Object robPlayerParams);
	
	/**(POST) Tells the server to end the current player's turn.
	 * 
	 * @param finishTurnParams Should contain the playerIndex (int).
	 * @return Returns the game's json client model (See model()).
	 */
	public String finishTurn(Object finishTurnParams);
	
	/**(POST) Tells the server that the current player wants to buy a 
	 * development card.
	 * 
	 * @param buyDevCardParams Should contain the playerIndex(integer).
	 * @return Returns the game's json client model (See model()).
	 */
	public String buyDevCard(Object buyDevCardParams);
	
	/**(POST) Sends the server the current player and their two 
	 * resources to receive.
	 * 
	 * @param yearOfPlentyParams Should contain the playerIndex (int 0-3),
	 * resource1 (Resource), and resource2 (Resource).
	 * @return Returns the game's json client model (See model()).
	 */
	public String Year_of_Plenty(Object yearOfPlentyParams);
	
	/**(POST) Sends the server the locations for the two roads to be
	 * build.
	 * 
	 * @param roadBuildingParams Should contain the playerIndex (int 0-3),
	 * spot1 (EdgeLocation), and spot2 (EdgeLocation).
	 * @return Returns the game's json client model (See model()).
	 */
	public String Road_Building(Object roadBuildingParams);
	
	/**(POST) Tells the server the robber's new location as well as 
	 * which player will be robbed.
	 * 
	 * @param soldierParams Should contain the playerIndex (int 0-3),
	 * victimIndex (int 0-3), and the robber's new location (HexLocation).
	 * @return Returns the game's json client model (See model()).
	 */
	public String Soldier(Object soldierParams);
	
	/**(POST) Tells the server to reward the current player with a 
	 * victory Point.
	 * 
	 * @param monumentParams Should contain the current 
	 * playerIndex (int 0-3).
	 * @return Returns the game's json client model (See model()).
	 */
	public String Monument(Object monumentParams);
	
	/**(POST) Sends the server where the current player is building a
	 * road. It also tells the server whether or not the road is an 
	 * initial placement (free).
	 * 
	 * @param buildRoadParams Should contain
	 * the current player's playerIndex (int) and the 
	 * roadLocation (EdgeLocation) and whether or not the road is
	 * free (Boolean).
	 * @return Returns the game's json client model (See model()).
	 */
	public String buildRoad(Object buildRoadParams);
	
	/**(POST) Tells the server to build a settlement for the current
	 * player at the specified location. Also tells the server whether
	 * or not the settlement is an initial placement (free).
	 * 
	 * @param buildSettlementParams Should contain the current 
	 * playerIndex (int 0-3), the settlement's vertexLocation 
	 * (VertexLocation), and whether or not it is free (Boolean).
	 * @return Returns the game's json client model (See model()).
	 */
	public String buildSettlement(Object buildSettlementParams);
	
	/**(POST) Tells the server to build a city at the specified
	 * settlement location.
	 * 
	 * @param buildCityParams Should contain the current 
	 * playerIndex (int 0-3), and the city's vertexLocation
	 * (VertexLocation).
	 * @return Returns the game's json client model (See model()).
	 */
	public String buildCity(Object buildCityParams);
	
	/**(POST) Tells the server to offer a trade from the current
	 * player to the receiving player as well as the trade offer.
	 * The offering player will send a ResourceList with positive
	 * numbers representing what they will receive, and negative
	 * numbers representing what they will give.
	 * 
	 * @param offerTradeParams Should contain the current 
	 * playerIndex (int 0-3), the offer (ResourceList), and the
	 * receive (int 0-3).
	 * @return Returns the game's json client model (See model()).
	 */
	public String offerTrade(Object offerTradeParams);
	
	/**(POST) Tells the server to accept or reject and offered
	 * trade from another player.
	 * 
	 * @param acceptTradeParams Should contain the receiving
	 * player's playerIndex (int 0-3) and they're decision
	 * willAccept (boolean).
	 * @return Returns the game's json client model (See model()).
	 */
	public String acceptTrade(Object acceptTradeParams);
	
	/**(POST) Sends the server the current player's desired
	 * maritime trade. The trade includes the resources to 
	 * be traded as well as the desired resource, and the 
	 * maritime trade's ratio.
	 * 
	 * @param maritimeTradeParams Should contain the current
	 * player's playerIndex (int 0-3). Optional parameters
	 * include the ratio (int), the player's inputResource
	 * (String), and the bank's outputResource (String).
	 * @return Returns the game's json client model (See model()).
	 */
	public String maritimeTrade(Object maritimeTradeParams);
	
	/**(POST) Tells the server to discard the current player's 
	 * specified resources.
	 * 
	 * @param discardCardsParams Should contain the current
	 * player's playerIndex (int) and the discardedCards 
	 * (ResourceList).
	 * @return Returns the game's json client model (See model()).
	 */
	public String discardCards(Object discardCardsParams);
	
	/**(POST) Changes the server's log level.
	 * 
	 * @param changeLogLevelParams contains the logLevel (String). 
	 * Accepted values include: ALL, SEVERE, WARNING, INFO, CONFIG,
	 * FINE, FINER, FINEST, OFF.
	 * @return server's http response
	 * @throws ServerException 
	 */
	public boolean changeLogLevel(Object changeLogLevelParams) throws ServerException;
}
