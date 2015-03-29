package command;

import shared.dto.AcceptTrade_Params;
import shared.dto.BuildCity_Params;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.BuyDevCard_Params;
import shared.dto.CreateGame_Params;
import shared.dto.DiscardCards_Params;
import shared.dto.FinishTurn_Params;
import shared.dto.JoinGame_Params;
import shared.dto.MaritimeTrade_Params;
import shared.dto.Monopoly_Params;
import shared.dto.Monument_Params;
import shared.dto.OfferTrade_Params;
import shared.dto.RoadBuilding_Params;
import shared.dto.RobPlayer_Params;
import shared.dto.RollNumber_Params;
import shared.dto.SendChat_Params;
import shared.dto.Soldier_Params;
import shared.dto.YearOfPlenty_Params;
import facade.GameManager;
import model.sgame.ServerGame;

public class CommandWrapper {
	
	private String type;
	
	private AcceptTrade_Params acceptTradeParams;
	private BuildCity_Params buildCityParams;
	private BuildRoad_Params buildRoadParams;
	private BuildSettlement_Params buildSettlementParams;
	private BuyDevCard_Params buyDevCardParams;
	private CreateGame_Params createGameParams;
	private DiscardCards_Params discardCardsParams;
	private FinishTurn_Params finishTurnParams;
	private JoinGame_Params joinGameParams;
	private MaritimeTrade_Params maritimeTradeParams;
	private Monopoly_Params monopolyParams;
	private Monument_Params monumentParams;
	private OfferTrade_Params offerTradeParams;
	private RoadBuilding_Params roadBuildingParams;
	private RobPlayer_Params robPlayerParams;
	private RollNumber_Params rollNumberParams;
	private SendChat_Params chatParams;
	private Soldier_Params soldierParams;
	private YearOfPlenty_Params yearOfPlentyParams;
	
	private ServerGame game;
	private GameManager manager;
	
	private Integer seed;

	public CommandWrapper() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ServerGame getGame() {
		return game;
	}

	public void setGame(ServerGame game) {
		this.game = game;
	}

	public GameManager getManager() {
		return manager;
	}

	public void setManager(GameManager manager) {
		this.manager = manager;
	}

	public AcceptTrade_Params getAcceptTradeParams() {
		return acceptTradeParams;
	}

	public void setAcceptTradeParams(AcceptTrade_Params acceptTradeParams) {
		this.acceptTradeParams = acceptTradeParams;
	}

	public BuildCity_Params getBuildCityParams() {
		return buildCityParams;
	}

	public void setBuildCityParams(BuildCity_Params buildCityParams) {
		this.buildCityParams = buildCityParams;
	}

	public BuildRoad_Params getBuildRoadParams() {
		return buildRoadParams;
	}

	public void setBuildRoadParams(BuildRoad_Params buildRoadParams) {
		this.buildRoadParams = buildRoadParams;
	}

	public BuildSettlement_Params getBuildSettlementParams() {
		return buildSettlementParams;
	}

	public void setBuildSettlementParams(
			BuildSettlement_Params buildSettlementParams) {
		this.buildSettlementParams = buildSettlementParams;
	}

	public BuyDevCard_Params getBuyDevCardParams() {
		return buyDevCardParams;
	}

	public void setBuyDevCardParams(BuyDevCard_Params buyDevCardParams) {
		this.buyDevCardParams = buyDevCardParams;
	}

	public CreateGame_Params getCreateGameParams() {
		return createGameParams;
	}

	public void setCreateGameParams(CreateGame_Params createGameParams) {
		this.createGameParams = createGameParams;
	}

	public DiscardCards_Params getDiscardCardsParams() {
		return discardCardsParams;
	}

	public void setDiscardCardsParams(DiscardCards_Params discardCardsParams) {
		this.discardCardsParams = discardCardsParams;
	}

	public FinishTurn_Params getFinishTurnParams() {
		return finishTurnParams;
	}

	public void setFinishTurnParams(FinishTurn_Params finishTurnParams) {
		this.finishTurnParams = finishTurnParams;
	}

	public JoinGame_Params getJoinGameParams() {
		return joinGameParams;
	}

	public void setJoinGameParams(JoinGame_Params joinGameParams) {
		this.joinGameParams = joinGameParams;
	}

	public MaritimeTrade_Params getMaritimeTradeParams() {
		return maritimeTradeParams;
	}

	public void setMaritimeTradeParams(MaritimeTrade_Params maritimeTradeParams) {
		this.maritimeTradeParams = maritimeTradeParams;
	}

	public Monopoly_Params getMonopolyParams() {
		return monopolyParams;
	}

	public void setMonopolyParams(Monopoly_Params monopolyParams) {
		this.monopolyParams = monopolyParams;
	}

	public Monument_Params getMonumentParams() {
		return monumentParams;
	}

	public void setMonumentParams(Monument_Params monumentParams) {
		this.monumentParams = monumentParams;
	}

	public OfferTrade_Params getOfferTradeParams() {
		return offerTradeParams;
	}

	public void setOfferTradeParams(OfferTrade_Params offerTradeParams) {
		this.offerTradeParams = offerTradeParams;
	}

	public RoadBuilding_Params getRoadBuildingParams() {
		return roadBuildingParams;
	}

	public void setRoadBuildingParams(RoadBuilding_Params roadBuildingParams) {
		this.roadBuildingParams = roadBuildingParams;
	}

	public RobPlayer_Params getRobPlayerParams() {
		return robPlayerParams;
	}

	public void setRobPlayerParams(RobPlayer_Params robPlayerParams) {
		this.robPlayerParams = robPlayerParams;
	}

	public RollNumber_Params getRollNumberParams() {
		return rollNumberParams;
	}

	public void setRollNumberParams(RollNumber_Params rollNumberParams) {
		this.rollNumberParams = rollNumberParams;
	}

	public SendChat_Params getChatParams() {
		return chatParams;
	}

	public void setChatParams(SendChat_Params chatParams) {
		this.chatParams = chatParams;
	}

	public Soldier_Params getSoldierParams() {
		return soldierParams;
	}

	public void setSoldierParams(Soldier_Params soldierParams) {
		this.soldierParams = soldierParams;
	}

	public YearOfPlenty_Params getYearOfPlentyParams() {
		return yearOfPlentyParams;
	}

	public void setYearOfPlentyParams(YearOfPlenty_Params yearOfPlentyParams) {
		this.yearOfPlentyParams = yearOfPlentyParams;
	}

	public Integer getSeed() {
		return seed;
	}

	public void setSeed(Integer seed) {
		this.seed = seed;
	}

}
