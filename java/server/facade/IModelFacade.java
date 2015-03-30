package facade;

import command.ICommandObject;
import model.sgame.ServerGame;
import shared.dto.*;

/**
 *
 */
public interface IModelFacade {

    public String listGames();

    public String createGame(CreateGame_Params params);

    public boolean joinGame(JoinGame_Params params, String user, int userId);

    public boolean saveGame(int gameID, String fileName);

    public boolean loadGame(String fileName);

    public String getGameModel(int gameID);

    public String resetGame(int gameID);

    public String getGameCommands(int gameID);

    public String executeGameCommands(ICommandObject[] commandsList);

    public String sendChat(int gameID, SendChat_Params chatParams);

    public String rollNumber(int gameID, RollNumber_Params rollNum);

    public String robPlayer(int gameID, RobPlayer_Params robParams);

    public String finishTurn(int gameID, FinishTurn_Params params);

    public String buyDevCard(int gameID, BuyDevCard_Params params);

    public String doYearOfPlenty(int gameID, YearOfPlenty_Params params);

    public String doRoadBuilding(int gameID, RoadBuilding_Params roadParams);

    public String doSoldier(int gameID, Soldier_Params params);

    public String doMonopoly(int gameID, Monopoly_Params params);

    public String doMonument(int gameID, Monument_Params params);

    public String buildRoad(int gameID, BuildRoad_Params roadParams);

    public String buildSettlement(int gameID, BuildSettlement_Params params);

    public String buildCity(int gameID, BuildCity_Params params);

    public String offerTrade(int gameID, OfferTrade_Params tradeParams);

    public String acceptTrade(int gameID, AcceptTrade_Params acceptParams);

    public String maritimeTrade(int gameID, MaritimeTrade_Params tradeParams);

    public String discardCards(int gameID, DiscardCards_Params cardParams);

	public Integer getCreatedGameId();
	
	public int getVersionId(int gameId);

	public ServerGame getGame(int gameId);
}