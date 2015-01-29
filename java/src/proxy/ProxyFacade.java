package proxy;

/**@author Chad
 * Jan 22, 2015
 * 
 * ProxyFacade recieves all requests to be sent to the server and calls the
 * Proxy subclass that handles the particular request. The subclasses can then
 * validate the data and pass it to the server.
 */

public class ProxyFacade implements IServerFacade {

	private ProxyUser user;
	private ProxyGames games;
	private ProxyGame game;
	private ProxyMoves moves;
	private ProxyUtil util;

	public ProxyFacade(String host, String port) {
		ServerProxy.getInstance().initProxy(host, port);
		
		user = new ProxyUser();
		games = new ProxyGames();
		game = new ProxyGame();
		moves = new ProxyMoves();
		util = new ProxyUtil();
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
	public String list() throws ServerException {
		return games.list();
	}

	@Override
	public String create(Object createParams) throws ServerException {
		return games.create(createParams);
	}

	@Override
	public Object join(Object joinParams) throws ServerException {
		return games.join(joinParams);
	}

	@Override
	public Object save(Object saveParams) throws ServerException {
		return games.save(saveParams);
	}

	@Override
	public Object load(Object loadParams) throws ServerException {
		return games.load(loadParams);
	}

	@Override
	public String model(int modelVersion) throws ServerException {
		return game.model(modelVersion);
	}

	@Override
	public String reset() throws ServerException {
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
	public Object addAI(Object addAIParams) throws ServerException {
		return game.addAI(addAIParams);
	}

	@Override
	public Object listAI() throws ServerException {
		return game.listAI();
	}

	@Override
	public String sendChat(Object sendChatParams) {
		return moves.sendChat(sendChatParams);
	}

	@Override
	public String rollNumber(Object rollNumberParams) {
		return moves.rollNumber(rollNumberParams);
	}

	@Override
	public String robPlayer(Object robPlayerParams) {
		return moves.robPlayer(robPlayerParams);
	}

	@Override
	public String finishTurn(Object finishTurnParams) {
		return moves.finishTurn(finishTurnParams);
	}

	@Override
	public String buyDevCard(Object buyDevCardParams) {
		return moves.buyDevCard(buyDevCardParams);
	}

	@Override
	public String Year_of_Plenty(Object yearOfPlentyParams) {
		return moves.Year_of_Plenty(yearOfPlentyParams);
	}

	@Override
	public String Road_Building(Object roadBuildingParams) {
		return moves.Road_Building(roadBuildingParams);
	}

	@Override
	public String Soldier(Object soldierParams) {
		return moves.Soldier(soldierParams);
	}

	@Override
	public String Monument(Object monumentParams) {
		return moves.Monument(monumentParams);
	}

	@Override
	public String buildRoad(Object buildRoadParams) {
		return moves.buildRoad(buildRoadParams);
	}

	@Override
	public String buildSettlement(Object buildSettlementParams) {
		return moves.buildSettlement(buildSettlementParams);
	}

	@Override
	public String buildCity(Object buildCityParams) {
		return moves.buildCity(buildCityParams);
	}

	@Override
	public String offerTrade(Object offerTradeParams) {
		return moves.offerTrade(offerTradeParams);
	}

	@Override
	public String acceptTrade(Object acceptTradeParams) {
		return moves.acceptTrade(acceptTradeParams);
	}

	@Override
	public String maritimeTrade(Object maritimeTradeParams) {
		return moves.maritimeTrade(maritimeTradeParams);
	}

	@Override
	public String discardCards(Object discardCardsParams) {
		return moves.discardCards(discardCardsParams);
	}

	@Override
	public boolean changeLogLevel(Object changeLogLevelParams) throws ServerException {
		return util.changeLogLevel(changeLogLevelParams);
	}
}
