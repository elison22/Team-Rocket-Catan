package server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Server {
	
	private static final int SERVER_PORT_NUMBER = 39640;
    private static final int MAX_WAITING_CONNECTIONS = 10;
    
    private HttpServer server;
    
    private Server() {
        return;
    }
    
    private void run(Integer port) {
    	
    	// initialize server facade here
    	// initialize user facade here
    	
    	try {
            if (port == null)
                port = SERVER_PORT_NUMBER;
            
            server = HttpServer.create(new InetSocketAddress(port), MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
        	// handle server exception; return 400/500 error code
            return;
        }
    	
    	server.setExecutor(null);
    	
    	/*server.createContext("/user/login", loginHandler);					// POST
        server.createContext("/user/register", registerHandler);				// POST
        server.createContext("/game/model", getGameModelHandler);				// GET
        server.createContext("/games/list", getGamesHandler);					// GET
        server.createContext("/games/create", createGameHandler);				// POST
        server.createContext("/games/join", joinGameHandler);					// POST
        server.createContext("/games/save", saveGameHandler);					// POST
        server.createContext("/games/load", loadGameHandler);					// POST
        server.createContext("/util/changeLogLevel", changeLogHandler);			// POST
        
        // moves
        server.createContext("/moves/sendChat", sendChatHandler);				// POST
        server.createContext("/moves/rollNumber", rollHandler);					// POST
        server.createContext("/moves/robPlayer", robPlayerHandler);				// POST
        server.createContext("/moves/finishTurn", finishTurnHandler);			// POST
        server.createContext("/moves/buyDevCard", buyDevCardHandler);			// POST
        server.createContext("/moves/Year_of_Plenty", yearOfPlentyHandler);		// POST
        server.createContext("/moves/Road_Building", roadBuildingHandler);		// POST
        server.createContext("/moves/Soldier", soldierHandler);					// POST
        server.createContext("/moves/Monopoly", monopolyHandler);				// POST
        server.createContext("/moves/Monument", monumentHandler);				// POST
        server.createContext("/moves/buildRoad", buildRoadHandler);				// POST
        server.createContext("/moves/buildSettlement", buildSettlementHandler);	// POST
        server.createContext("/moves/buildCity", buildCityHandler);				// POST
        server.createContext("/moves/offerTrade", offerTradeHandler);			// POST
        server.createContext("/moves/acceptTrade", acceptTradeHandler);			// POST
        server.createContext("/moves/maritimeTrade", maritimeTradeHandler);		// POST
        server.createContext("/moves/discardCards", discardCardsHandler);		// POST
        */
    	
    	server.start();
    	
    }
    
   /*
    private HttpHandler loginHandler = new LoginHandler();
    private HttpHandler registerHandler = new RegisterHandler();
    private HttpHandler getGameModelHandler = new GetGameModelHandler();
    private HttpHandler getGamesHandler = new GetGamesHandler();
    private HttpHandler createGameHandler = new CreateGameHandler();
    private HttpHandler joinGameHandler = new JoinGameHandler();
    private HttpHandler saveGameHandler = new SaveGameHandler();
    private HttpHandler loadGameHandler = new LoadGameHandler();
    private HttpHandler changeLogHandler = new ChangeLogHandler();
    
    // moves
    private HttpHandler sendChatHandler = new SendChatHandler();
    private HttpHandler rollHandler = new RollHandler();
    private HttpHandler robPlayerHandler = new RobPlayerHandler();
    private HttpHandler finishTurnHandler = new FinishTurnHandler();
    private HttpHandler buyDevCardHandler = new BuyDevCardHandler();
    private HttpHandler yearOfPlentyHandler = new YearOfPlentyHandler();
    private HttpHandler roadBuildingHandler = new RoadBuildingHandler();
    private HttpHandler soldierHandler = new SoldierHandler();
    private HttpHandler monopolyHandler = new MonopolyHandler();
    private HttpHandler monumentHandler = new MonumentHandler();
    private HttpHandler buildRoadHandler = new BuildRoadHandler();
    private HttpHandler buildSettlementHandler = new BuildSettlementHandler();
    private HttpHandler buildCityHandler = new BuildCityHandler();
    private HttpHandler offerTradeHandler = new OfferTradeHandler();
    private HttpHandler acceptTradeHandler = new AcceptTradeHandler();
    private HttpHandler maritimeTradeHandler = new MaritimeTradeHandler();
    private HttpHandler discardCardsHandler = new DiscardCardsHandler();
    */

	public static void main(String[] args) {
		Integer i = new Integer(args[0]);
        new Server().run(i);
	}

}
