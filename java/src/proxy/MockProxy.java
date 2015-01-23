package proxy;

import com.google.gson.JsonObject;

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
	public Object login(JsonObject loginParams) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object register(JsonObject registerParams) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object create(JsonObject createParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object join(JsonObject joinParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object save(JsonObject saveParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(JsonObject loadParams) {
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
	public Object addAI(JsonObject addAIParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object listAI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object sendChat(JsonObject sendChatParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object rollNumber(JsonObject rollNumberParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object robPlayer(JsonObject robPlayerParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object finishTurn(JsonObject finishTurnParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object buyDevCard(JsonObject buyDevCardParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Year_of_Plenty(JsonObject yearOfPlentyParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Road_Building(JsonObject roadBuildingParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Soldier(JsonObject soldierParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object Monument(JsonObject monumentParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object buildRoad(JsonObject buildRoadParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object buildSettlement(JsonObject buildSettlementParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object buildCity(JsonObject buildCityParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object offerTrade(JsonObject offerTradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object acceptTrade(JsonObject acceptTradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object maritimeTrade(JsonObject maritimeTradeParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object discardCards(JsonObject discardCardsParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object changeLogLevel(JsonObject changeLogLevelParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
