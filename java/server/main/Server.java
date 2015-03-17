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
import handler.SwaggerHandler;
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
    
    private HttpServer server;
    
    private Server() {
        return;
    }
    
    private void run(Integer port) {
    	
    	IModelFacade modelFacade = new ModelFacade();
    	IUserFacade userFacade = new UserFacade();
    	
    	try {
            if (port == null)
                port = SERVER_PORT_NUMBER;
            
            server = HttpServer.create(new InetSocketAddress(port), MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
        	System.out.println("Server was unable to start");
            return;
        }
    	
    	server.setExecutor(null);
    	
    	server.createContext("/user/login", new LoginHandler(userFacade));							// POST
        server.createContext("/user/register", new RegisterHandler(userFacade));					// POST
        server.createContext("/game/model", new GetGameModelHandler(modelFacade));					// GET
        server.createContext("/game/reset", new ResetGameHandler(modelFacade));						// POST
        server.createContext("/game/commands", new GetGameCommandsHandler(modelFacade));			// GET
        server.createContext("/game/commands", new DoGameCommandsHandler(modelFacade));				// POST
        server.createContext("/games/list", new GetGamesHandler(modelFacade));						// GET
        server.createContext("/games/create", new CreateGameHandler(modelFacade));					// POST
        server.createContext("/games/join", new JoinGameHandler(modelFacade));						// POST
        server.createContext("/games/save", new SaveGameHandler(modelFacade));						// POST
        server.createContext("/games/load", new LoadGameHandler(modelFacade));						// POST
        server.createContext("/util/changeLogLevel", new ChangeLogHandler(modelFacade));			// POST
        
        // moves
        server.createContext("/moves/sendChat", new SendChatHandler(modelFacade));					// POST
        server.createContext("/moves/rollNumber", new RollHandler(modelFacade));					// POST
        server.createContext("/moves/robPlayer", new RobPlayerHandler(modelFacade));				// POST
        server.createContext("/moves/finishTurn", new FinishTurnHandler(modelFacade));				// POST
        server.createContext("/moves/buyDevCard", new BuyDevCardHandler(modelFacade));				// POST
        server.createContext("/moves/Year_of_Plenty", new YearOfPlentyHandler(modelFacade));		// POST
        server.createContext("/moves/Road_Building", new RoadBuildingHandler(modelFacade));			// POST
        server.createContext("/moves/Soldier", new SoldierHandler(modelFacade));					// POST
        server.createContext("/moves/Monopoly", new MonopolyHandler(modelFacade));					// POST	
        server.createContext("/moves/Monument", new MonumentHandler(modelFacade));					// POST
        server.createContext("/moves/buildRoad", new BuildRoadHandler(modelFacade));				// POST
        server.createContext("/moves/buildSettlement", new BuildSettlementHandler(modelFacade));	// POST
        server.createContext("/moves/buildCity", new BuildCityHandler(modelFacade));				// POST
        server.createContext("/moves/offerTrade", new OfferTradeHandler(modelFacade));				// POST
        server.createContext("/moves/acceptTrade", new AcceptTradeHandler(modelFacade));			// POST
        server.createContext("/moves/maritimeTrade", new MaritimeTradeHandler(modelFacade));		// POST
        server.createContext("/moves/discardCards", new DiscardCardsHandler(modelFacade));			// POST

        // swagger
        server.createContext("/docs/api/data", new SwaggerHandler.JSONAppender(""));
        server.createContext("/docs/api/view", new SwaggerHandler.BasicFile(""));    
    	
    	server.start();
    }

	public static void main(String[] args) {
		Integer i = new Integer(args[0]);
        new Server().run(i);
	}

}
