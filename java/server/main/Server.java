package main;

import handler.AcceptTradeHandler;
import handler.BuildCityHandler;
import handler.BuildRoadHandler;
import handler.BuildSettlementHandler;
import handler.BuyDevCardHandler;
import handler.ChangeLogHandler;
import handler.CreateGameHandler;
import handler.DiscardCardsHandler;
import handler.DoGameCommandsHandler;
import handler.FinishTurnHandler;
import handler.GetGameCommandsHandler;
import handler.GetGameModelHandler;
import handler.GetGamesHandler;
import handler.JoinGameHandler;
import handler.LoadGameHandler;
import handler.LoginHandler;
import handler.MaritimeTradeHandler;
import handler.MonopolyHandler;
import handler.MonumentHandler;
import handler.OfferTradeHandler;
import handler.RegisterHandler;
import handler.ResetGameHandler;
import handler.RoadBuildingHandler;
import handler.RobPlayerHandler;
import handler.RollHandler;
import handler.SaveGameHandler;
import handler.SendChatHandler;
import handler.SoldierHandler;
import handler.YearOfPlentyHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

import user.IUserFacade;
import user.UserFacade;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import facade.IModelFacade;
import facade.ModelFacade;

public class Server {
	
	private static final int SERVER_PORT_NUMBER = 8081;
    private static final int MAX_WAITING_CONNECTIONS = 10;
    
    private IModelFacade modelFacade = new ModelFacade();
    private IUserFacade userFacade = new UserFacade();
    
    private HttpHandler loginHandler = new LoginHandler(userFacade);
    private HttpHandler registerHandler = new RegisterHandler(userFacade);
    private HttpHandler getGameModelHandler = new GetGameModelHandler(modelFacade);
    private HttpHandler resetGameHandler = new ResetGameHandler(modelFacade);
    private HttpHandler getGameCommandsHandler = new GetGameCommandsHandler(modelFacade);
    private HttpHandler doGameCommandsHandler = new DoGameCommandsHandler(modelFacade);
    private HttpHandler getGamesHandler = new GetGamesHandler(modelFacade);
    private HttpHandler createGameHandler = new CreateGameHandler(modelFacade);
    private HttpHandler joinGameHandler = new JoinGameHandler(modelFacade);
    private HttpHandler saveGameHandler = new SaveGameHandler(modelFacade);
    private HttpHandler loadGameHandler = new LoadGameHandler(modelFacade);
    private HttpHandler changeLogHandler = new ChangeLogHandler(modelFacade);
    
    // moves
    private HttpHandler sendChatHandler = new SendChatHandler(modelFacade);
    private HttpHandler rollHandler = new RollHandler(modelFacade);
    private HttpHandler robPlayerHandler = new RobPlayerHandler(modelFacade);
    private HttpHandler finishTurnHandler = new FinishTurnHandler(modelFacade);
    private HttpHandler buyDevCardHandler = new BuyDevCardHandler(modelFacade);
    private HttpHandler yearOfPlentyHandler = new YearOfPlentyHandler(modelFacade);
    private HttpHandler roadBuildingHandler = new RoadBuildingHandler(modelFacade);
    private HttpHandler soldierHandler = new SoldierHandler(modelFacade);
    private HttpHandler monopolyHandler = new MonopolyHandler(modelFacade);
    private HttpHandler monumentHandler = new MonumentHandler(modelFacade);
    private HttpHandler buildRoadHandler = new BuildRoadHandler(modelFacade);
    private HttpHandler buildSettlementHandler = new BuildSettlementHandler(modelFacade);
    private HttpHandler buildCityHandler = new BuildCityHandler(modelFacade);
    private HttpHandler offerTradeHandler = new OfferTradeHandler(modelFacade);
    private HttpHandler acceptTradeHandler = new AcceptTradeHandler(modelFacade);
    private HttpHandler maritimeTradeHandler = new MaritimeTradeHandler(modelFacade);
    private HttpHandler discardCardsHandler = new DiscardCardsHandler(modelFacade);

    
    private HttpServer server;
    
    private Server() {
        return;
    }
    
    private void run(Integer port) {
    	
    	// initialize server facade here
    	// initialize user facade here
    	IModelFacade modelFacade = new ModelFacade();
    	
    	try {
            if (port == null)
                port = SERVER_PORT_NUMBER;
            
            server = HttpServer.create(new InetSocketAddress(port), MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
        	// handle server exception; return 400/500 error code
            return;
        }
    	
    	server.setExecutor(null);
    	
    	server.createContext("/user/login", loginHandler);						// POST
        server.createContext("/user/register", registerHandler);				// POST
        server.createContext("/game/model", getGameModelHandler);				// GET
        server.createContext("/game/reset", resetGameHandler);					// POST
        server.createContext("/game/commands", getGameCommandsHandler);			// GET
        server.createContext("/game/commands", doGameCommandsHandler);			// POST
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
        server.createContext("/moves/Monopoly", monopolyHandler);				// POST		not listed in spec?
        server.createContext("/moves/Monument", monumentHandler);				// POST
        server.createContext("/moves/buildRoad", buildRoadHandler);				// POST
        server.createContext("/moves/buildSettlement", buildSettlementHandler);	// POST
        server.createContext("/moves/buildCity", buildCityHandler);				// POST
        server.createContext("/moves/offerTrade", offerTradeHandler);			// POST
        server.createContext("/moves/acceptTrade", acceptTradeHandler);			// POST
        server.createContext("/moves/maritimeTrade", maritimeTradeHandler);		// POST
        server.createContext("/moves/discardCards", discardCardsHandler);		// POST
        
    	
    	server.start();
    }

	public static void main(String[] args) {
		Integer i = new Integer(args[0]);
        new Server().run(i);
	}

}
