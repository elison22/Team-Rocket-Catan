package proxy;

/**@author Chad
 * Jan 22, 2015
 * 
 * Used for testing. It does not communicate with the server but instead
 * returns predefined outputs based on the method to be tested.
 */
public class MockProxy implements IServerFacade {

	public MockProxy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean login(Object loginParams) throws ServerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(Object registerParams) throws ServerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String create(Object createParams) {
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
	public String model(int modelVersion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reset() {
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
	public boolean addAI(Object addAIParams) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object listAI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendChat(Object sendChatParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rollNumber(Object rollNumberParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String robPlayer(Object robPlayerParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String finishTurn(Object finishTurnParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buyDevCard(Object buyDevCardParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Year_of_Plenty(Object yearOfPlentyParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Road_Building(Object roadBuildingParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Soldier(Object soldierParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Monument(Object monumentParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildRoad(Object buildRoadParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSettlement(Object buildSettlementParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildCity(Object buildCityParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String offerTrade(Object offerTradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String acceptTrade(Object acceptTradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String maritimeTrade(Object maritimeTradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String discardCards(Object discardCardsParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeLogLevel(Object changeLogLevelParams) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
