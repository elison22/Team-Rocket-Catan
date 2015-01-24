package proxy;

/**@author Chad
 * Jan 22, 2015
 * 
 * ProxyFacade recieves all requests to be sent to the server and calls the
 * Proxy subclass that handles the particular request. The subclasses can then
 * validate the data and pass it to the server.
 */
@SuppressWarnings("unused")
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
	public Object list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object create(Object createParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object join(Object joinParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object save(Object saveParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(Object loadParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object model() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object reset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object commandsPOST(Object commands) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object commandsGET() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addAI(Object addAIParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object listAI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object sendChat(Object sendChatParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object rollNumber(Object rollNumberParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object robPlayer(Object robPlayerParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object finishTurn(Object finishTurnParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object buyDevCard(Object buyDevCardParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Year_of_Plenty(Object yearOfPlentyParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Road_Building(Object roadBuildingParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Soldier(Object soldierParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Monument(Object monumentParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object buildRoad(Object buildRoadParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object buildSettlement(Object buildSettlementParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object buildCity(Object buildCityParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object offerTrade(Object offerTradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object acceptTrade(Object acceptTradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object maritimeTrade(Object maritimeTradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object discardCards(Object discardCardsParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object changeLogLevel(Object changeLogLevelParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
