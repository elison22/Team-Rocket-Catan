package main;

import handler.ChangeLogHandler;
import handler.CreateGameHandler;
import handler.GetGameCommandsHandler;
import handler.GetGameModelHandler;
import handler.GetGamesHandler;
import handler.JoinGameHandler;
import handler.LoadGameHandler;
import handler.LoginHandler;
import handler.MovesHandler;
import handler.RegisterHandler;
import handler.ResetGameHandler;
import handler.SaveGameHandler;
import handler.SwaggerHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

import user.IUserFacade;
import user.UserFacade;

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
        server.createContext("/game/commands", new GetGameCommandsHandler(modelFacade, userFacade));			// GET
        server.createContext("/games/list", new GetGamesHandler(modelFacade));						// GET
        server.createContext("/games/create", new CreateGameHandler(modelFacade));					// POST
        server.createContext("/games/join", new JoinGameHandler(modelFacade, userFacade));						// POST
        server.createContext("/games/save", new SaveGameHandler(modelFacade));						// POST
        server.createContext("/games/load", new LoadGameHandler(modelFacade));						// POST
        server.createContext("/util/changeLogLevel", new ChangeLogHandler(modelFacade));			// POST
        
        // moves
        server.createContext("/moves/", new MovesHandler(modelFacade, userFacade));

        // swagger
        server.createContext("/docs/api/data", new SwaggerHandler.JSONAppender(""));
        server.createContext("/docs/api/view", new SwaggerHandler.BasicFile(""));    
    	
    	server.start();
    }

	public static void main(String[] args) {
		Integer i = null;
		if(args.length > 0)
			i = new Integer(args[0]);
		
        new Server().run(i);
	}

}
