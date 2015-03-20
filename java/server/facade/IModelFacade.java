package facade;


import shared.definitions.ResourceType;
import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.List;

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

    public String rollNumber(int gameID, int playerIdx, int numRolled);

    public String robPlayer(int gameID, int playerIdx, int victimIdx, HexLocation location);

    public String finishTurn(int gameID, int playerIdx);

    public String buyDevCard(int gameID, int playerIdx);

    public String doYearOfPlenty(int gameID, int playerIdx, ResourceType resource1, ResourceType resource2);

    public String doRoadBuilding(int gameID, int playerIdx, EdgeLocation road1, EdgeLocation road2);

    public String doSoldier(int gameID, int playerIdx, int victimIdx, HexLocation location);

    public String doMonopoly(int gameID, int playerIdx, ResourceType resource);

    public String doMonument(int gameID, int playerIdx);

    public String buildRoad(int gameID, int playerIdx, EdgeLocation location, boolean free);

    public String buildSettlement(int gameID, int playerIdx, VertexLocation location, boolean free);

    public String buildCity(int gameID, int playerIdx, VertexLocation location);

    public String offerTrade(int gameID, int playerIdx, int receiverIdx, List<Integer> offer);

    public String acceptTrade(int gameID, int playerIdx, boolean tradeAccepted);

    public String maritimeTrade(int gameID, int playerIdx, int ratio, ResourceType input, ResourceType output);

    public String discardCards(int gameID, int playerIdx, List<Integer> discardedResources);

	int getCreatedGameId();
}