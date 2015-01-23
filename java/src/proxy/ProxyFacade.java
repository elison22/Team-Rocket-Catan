package proxy;

import com.google.gson.JsonObject;

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
	public Object login(JsonObject loginParams) throws ServerException {
		return user.login(loginParams);
	}

	@Override
	public Object register(JsonObject registerParams) throws ServerException {
		return user.register(registerParams);
	}

	@Override
	public Object list() {
		return games.list();
	}

	@Override
	public Object create(JsonObject createParams) {
		return games.create(createParams);
	}

	@Override
	public Object join(JsonObject joinParams) {
		return games.join(joinParams);
	}

	@Override
	public Object save(JsonObject saveParams) {
		return games.save(saveParams);
	}

	@Override
	public Object load(JsonObject loadParams) {
		return games.load(loadParams);
	}

	@Override
	public Object model() {
		return game.model();
	}

	@Override
	public Object reset() {
		return null;
	}

	@Override
	public Object commandsPOST(Object commands) {
		return null;
	}

	@Override
	public Object commandsGET() {
		return null;
	}

	@Override
	public Object addAI(JsonObject addAIParams) {
		return null;
	}

	@Override
	public Object listAI() {
		return null;
	}

	@Override
	public Object sendChat(JsonObject sendChatParams) {
		return moves.sendChat(sendChatParams);
	}

	@Override
	public Object rollNumber(JsonObject rollNumberParams) {
		return null;
	}

	@Override
	public Object robPlayer(JsonObject robPlayerParams) {
		return null;
	}

	@Override
	public Object finishTurn(JsonObject finishTurnParams) {
		return null;
	}

	@Override
	public Object buyDevCard(JsonObject buyDevCardParams) {
		return null;
	}

	@Override
	public Object Year_of_Plenty(JsonObject yearOfPlentyParams) {
		return null;
	}

	@Override
	public Object Road_Building(JsonObject roadBuildingParams) {
		return null;
	}

	@Override
	public Object Soldier(JsonObject soldierParams) {
		return null;
	}

	@Override
	public Object Monument(JsonObject monumentParams) {
		return null;
	}

	@Override
	public Object buildRoad(JsonObject buildRoadParams) {
		return null;
	}

	@Override
	public Object buildSettlement(JsonObject buildSettlementParams) {
		return null;
	}

	@Override
	public Object buildCity(JsonObject buildCityParams) {
		return null;
	}

	@Override
	public Object offerTrade(JsonObject offerTradeParams) {
		return null;
	}

	@Override
	public Object acceptTrade(JsonObject acceptTradeParams) {
		return null;
	}

	@Override
	public Object maritimeTrade(JsonObject maritimeTradeParams) {
		return null;
	}

	@Override
	public Object discardCards(JsonObject discardCardsParams) {
		return null;
	}

	@Override
	public Object changeLogLevel(JsonObject changeLogLevelParams) {
		return util.changeLogLevel(changeLogLevelParams);
	}

}
