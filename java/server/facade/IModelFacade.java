package facade;


import java.util.List;

import shared.dto.AcceptTrade_Params;
import shared.dto.BuildCity_Params;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;
import shared.dto.MaritimeTrade_Params;
import shared.dto.Monopoly_Params;
import shared.dto.RoadBuilding_Params;
import shared.dto.RobPlayer_Params;
import shared.dto.RollNumber_Params;
import shared.dto.Soldier_Params;
import shared.dto.YearOfPlenty_Params;

/**
 *
 */
public interface IModelFacade {

    public String listGames();

    public String createGame(CreateGame_Params params);

    public boolean joinGame(JoinGame_Params params, String user, int userId);

    public boolean saveGame(int gameID, String fileName);

    public String loadGame(String fileName);

    public String getGameModel(int gameID);

    public String resetGame(int gameID);

    public String getGameCommands(int gameID);

    public String executeGameCommands(int gameID, String gameCommands);

    public String sendChat(int gameID, int playerIdx, String message);

    public String rollNumber(int gameID, RollNumber_Params rollNum);

    public String robPlayer(int gameID, RobPlayer_Params robParams);

    public String finishTurn(int gameID, int playerIdx);

    public String buyDevCard(int gameID, int playerIdx);

    public String doYearOfPlenty(int gameID, YearOfPlenty_Params params);

    public String doRoadBuilding(int gameID, RoadBuilding_Params roadParams);

    public String doSoldier(int gameID, Soldier_Params params);

    public String doMonopoly(int gameID, Monopoly_Params params);

    public String doMonument(int gameID, int playerIdx);

    public String buildRoad(int gameID, BuildRoad_Params roadParams);

    public String buildSettlement(int gameID, BuildSettlement_Params params);

    public String buildCity(int gameID, BuildCity_Params params);

    public String offerTrade(int gameID, int playerIdx, int receiverIdx, List<Integer> offer);

    public String acceptTrade(int gameID, AcceptTrade_Params acceptParams);

    public String maritimeTrade(int gameID, MaritimeTrade_Params tradeParams);

    public String discardCards(int gameID, int playerIdx, List<Integer> discardedResources);

	int getCreatedGameId();
	
	public int getVersionId(int gameId);
}