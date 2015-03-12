package facade;

/**
 * The ModelFacade will be called by the Command Objects for any operation that deals with 
 * reading from or modifying a game. Every operation from the swagger page under the 'game,'
 * 'games,' 'moves,' and 'util' sections are handled here. The ModelFacade in turn will call
 * methods on its instance of the GameManager to ensure that methods are being called on the
 * correct game.
 */
public class ModelFacade implements IModelFacade {

	/**
	 * The ModelFacade's instance of GameManager
	 */
	private GameManager gameManager;
	
	/**
	 * Lists all games currently on the server
	 * @return returns a JSON string of the available games.
	 */
	@Override
	public String listGames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates a new game on the server
	 * @param randomTiles whether the game should have randomly placed resource tiles
	 * @param randomNumbers whether the game should randomly place chits
	 * @param randomPorts whether the game should randomly place ports
	 * @param name the name of the game
	 * @return returns a JSON string with the new game information
	 */
	@Override
	public String createGame(boolean randomTiles, boolean randomNumbers,
			boolean randomPorts, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a user to join a game if the game isn't full
	 * @param gameID The ID of the game that has been requested
	 * @param color The color the player has selected upon joining the game
	 * @return returns success or failure
	 */
	@Override
	public boolean joinGame(int gameID, String color) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a user to save the game
	 * @param gameID The ID of the game that has been requested
	 * @param fileName The name to save the file under
	 * @return returns success or failure
	 */
	@Override
	public boolean saveGame(int gameID, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a user to load a game
	 * @param fileName The name of the file to load
	 * @return returns a JSON string with the state of the saved game
	 */
	@Override
	public String loadGame(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the information for a specific game on the server
	 * @param gameID The ID of the game that has been requested
	 * @return returns the JSON string of the game information
	 */
	@Override
	public String getGameModel(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Resets a game to the default setup
	 * @param gameID The ID of the game that has been requested
	 * @return returns the JSON string of a default game
	 */
	@Override
	public String resetGame(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the commands the game has been given so far
	 * @param gameID The ID of the game that has been requested
	 * @return returns a JSON string of all previous commands
	 */
	@Override
	public String getGameCommands(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes a list of commands on a game to put it in a desired state
	 * @param gameID The ID of the game that has been requested
	 * @param gameCommands A JSON string of commands to run on the game
	 * @return returns a JSON of the game model after the commands are executed
	 */
	@Override
	public String executeGameCommands(int gameID, String gameCommands) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates a new chat message for a specific player
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String sendChat() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Rolls the dice for a player's turn
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String rollNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to rob another after placing the robber on a tile they own
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String robPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ends a player's turn
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String finishTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to buy a development card if there are any left
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String buyDevCard() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes the effects of playing a year of plenty dev card
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doYearOfPlenty() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes the effects of playing a road building dev card
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doRoadBuilding() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes the effects of playing a soldier dev card
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doSoldier() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes the effects of playing a monopoly dev card
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doMonopoly() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes the effects of playing a monument dev card
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String doMonument() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player do build a road
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String buildRoad() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to build a settlement
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String buildSettlement() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to build a city
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String buildCity() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Allows a player to offer a trade to another player
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String offerTrade() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes a trade that was previously offered between two players
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String acceptTrade() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Executes a maritime trade
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String maritimeTrade() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Removes resource cards from a player's hand if they have more than
	 * seven cards when a seven is rolled.
	 * @return returns a JSON string of the resulting game model
	 */
	@Override
	public String discardCards() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//***These are the methods we'll use if the handlers pass the params objects into the facade***//
	
//	public String login(Object loginParams) throws ServerException {
//		return null;
//	}
//
//	public String register(Object registerParams) throws ServerException {
//		return null;
//	}
//
//	public String listGames() throws ServerException {
//		return null;
//	}
//
//	public String createGame(Object createParams) throws ServerException {
//		return null;
//	}
//
//	public String join(Object joinParams) throws ServerException {
//		return null;
//	}
//
//	public String save(Object saveParams) throws ServerException {
//		return null;
//	}
//
//	public String load(Object loadParams) throws ServerException {
//		return null;
//	}
//
//	public String model(int modelVersion) throws ServerException {
//		return null;
//	}
//
//	public String reset() throws ServerException {
//		return null;
//	}
//
//	public String executeCommands(Object commands) {
//		return null;
//	}
//
//	public String getCommands() {
//		return null;
//	}
//
//	public String addAI(Object addAIParams) throws ServerException {
//		return null;
//	}
//
//	public String listAI() throws ServerException {
//		return null;
//	}
//
//	public String sendChat(Object sendChatParams) throws ServerException {
//		return null;
//	}
//
//	public String rollNumber(Object rollNumberParams) throws ServerException {
//		return null;
//	}
//	
//	public String robPlayer(Object robPlayerParams) throws ServerException {
//		return null;
//	}
//	
//	public String finishTurn(Object finishTurnParams) throws ServerException {
//		return null;
//	}
//	 
//	public String buyDevCard(Object buyDevCardParams) throws ServerException {
//		return null;
//	}
//	 
//	public String Year_of_Plenty(Object yearOfPlentyParams) throws ServerException {
//		return null;
//	}
//	 
//	public String Road_Building(Object roadBuildingParams) throws ServerException {
//		return null;
//	}
//	 
//	public String Soldier(Object soldierParams) throws ServerException {
//		return null;
//	}
//
//	public String Monopoly(Object monopolyParams) throws ServerException {
//		return null;
//	}
//	 
//	public String Monument(Object monumentParams) throws ServerException {
//		return null;
//	}
//	 
//	public String buildRoad(Object buildRoadParams) throws ServerException {
//		return null;
//	}
//	 
//	public String buildSettlement(Object buildSettlementParams) throws ServerException {
//		return null;
//	}
//	 
//	public String buildCity(Object buildCityParams) throws ServerException {
//		return null;
//	}
//	 
//	public String offerTrade(Object offerTradeParams) throws ServerException {
//		return null;
//	}
//	 
//	public String acceptTrade(Object acceptTradeParams) throws ServerException {
//		return null;
//	}
//	
//	public String maritimeTrade(Object maritimeTradeParams) throws ServerException {
//		return null;
//	}
//	
//	public String discardCards(Object discardCardsParams) throws ServerException {
//		return null;
//	}
//	
//	public boolean changeLogLevel(Object changeLogLevelParams) throws ServerException {
//		return false;
//	}

}
