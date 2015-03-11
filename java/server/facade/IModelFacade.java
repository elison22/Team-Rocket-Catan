package facade;


/**
 *
 */
public interface IModelFacade {

    public String listGames();

    public String createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name);

    public String joinGame(int gameID, String color);

    public String saveGame(int gameID, String fileName);

    public String loadGame(String fileName);

    public String getGameModel(int gameID);

    public String resetGame(int gameID);

    public String getGameCommands(int gameID);

    public String executeGameCommands(int gameID, String gameCommands);

    public String addAI(String AIType);

    public String listAI();

    public String sendChat();

    public String rollNumber();

    public String robPlayer();

    public String finishTurn();

    public String buyDevCard();

    public String doYearOfPlenty();

    public String doRoadBuilding();

    public String doSoldier();

    public String doMonopoly();

    public String doMonument();

    public String buildRoad();

    public String buildSettlement();

    public String buildCity();

    public String offerTrade();

    public String acceptTrade();

    public String maritimeTrade();

    public String discardCards();

    public String changeLogLevel();
}