package proxy;

/**@author Chad
 * Jan 22, 2015
 * 
 * ProxyFacade recieves all requests to be sent to the server and calls the
 * Proxy subclass that handles the particular request. The subclasses can then
 * validate the data and pass it to the server.
 */

public class ProxyFacade implements IServerFacade {
	
	final String HOST;
	final String PORT;
	
	
	private ProxyUser user;
	private ProxyGames games;
	private ProxyGame game;
	private ProxyMoves moves;
	private ProxyUtil util;

	public ProxyFacade(String host, String port) {
		this.HOST = host;
		this.PORT = port;
		
		user = new ProxyUser(HOST, PORT);
		games = new ProxyGames(HOST, PORT);
		game = new ProxyGame(HOST, PORT);
		moves = new ProxyMoves(HOST, PORT);
		util = new ProxyUtil(HOST, PORT);
	}

	@Override
	public boolean login(Object loginParams) throws ServerException {
		return user.login(loginParams);
	}

	@Override
	public boolean register(Object registerParams) throws ServerException {
		return user.register(registerParams);
	}

	@Override
	public Object list() throws ServerException {
		return games.list();
	}

	@Override
	public Object create(Object createParams) {
		return games.create(createParams);
	}

	@Override
	public Object join(Object joinParams) {
		return games.join(joinParams);
	}

	@Override
	public Object save(Object saveParams) {
		return games.save(saveParams);
	}

	@Override
	public Object load(Object loadParams) {
		return games.load(loadParams);
	}

	@Override
	public Object model() {
		return game.model();
	}

	@Override
	public Object reset() {
		return game.reset();
	}

	@Override
	public Object commandsPOST(Object commands) {
		return game.commandsPOST(commands);
	}

	@Override
	public Object commandsGET() {
		return game.commandsGET();
	}

	@Override
	public Object addAI(Object addAIParams) {
		return game.addAI(addAIParams);
	}

	@Override
	public Object listAI() {
		return game.listAI();
	}

	@Override
	public Object sendChat(Object sendChatParams) {
		return moves.sendChat(sendChatParams);
	}

	@Override
	public Object rollNumber(Object rollNumberParams) {
		return moves.rollNumber(rollNumberParams);
	}

	@Override
	public Object robPlayer(Object robPlayerParams) {
		return moves.robPlayer(robPlayerParams);
	}

	@Override
	public Object finishTurn(Object finishTurnParams) {
		return moves.finishTurn(finishTurnParams);
	}

	@Override
	public Object buyDevCard(Object buyDevCardParams) {
		return moves.buyDevCard(buyDevCardParams);
	}

	@Override
	public Object Year_of_Plenty(Object yearOfPlentyParams) {
		return moves.Year_of_Plenty(yearOfPlentyParams);
	}

	@Override
	public Object Road_Building(Object roadBuildingParams) {
		return moves.Road_Building(roadBuildingParams);
	}

	@Override
	public Object Soldier(Object soldierParams) {
		return moves.Soldier(soldierParams);
	}

	@Override
	public Object Monument(Object monumentParams) {
		return moves.Monument(monumentParams);
	}

	@Override
	public Object buildRoad(Object buildRoadParams) {
		return moves.buildRoad(buildRoadParams);
	}

	@Override
	public Object buildSettlement(Object buildSettlementParams) {
		return moves.buildSettlement(buildSettlementParams);
	}

	@Override
	public Object buildCity(Object buildCityParams) {
		return moves.buildCity(buildCityParams);
	}

	@Override
	public Object offerTrade(Object offerTradeParams) {
		return moves.offerTrade(offerTradeParams);
	}

	@Override
	public Object acceptTrade(Object acceptTradeParams) {
		return moves.acceptTrade(acceptTradeParams);
	}

	@Override
	public Object maritimeTrade(Object maritimeTradeParams) {
		return moves.maritimeTrade(maritimeTradeParams);
	}

	@Override
	public Object discardCards(Object discardCardsParams) {
		return moves.discardCards(discardCardsParams);
	}

	@Override
	public boolean changeLogLevel(Object changeLogLevelParams) throws ServerException {
		return util.changeLogLevel(changeLogLevelParams);
	}

}
