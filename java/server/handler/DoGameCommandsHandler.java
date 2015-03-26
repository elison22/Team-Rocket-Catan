package handler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import user.IUserFacade;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import command.ICommandObject;

import facade.IModelFacade;

public class DoGameCommandsHandler extends MovesHandler {
	
	private IUserFacade userFacade;
	
	public DoGameCommandsHandler(IModelFacade modelFacade, IUserFacade userFacade) {
		super(modelFacade);
		this.userFacade = userFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String[] cookies = decodeCookie(exchange);
		Headers head = null;
		if(cookies.length != 4) {
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "user must be logged in and in game");
			exchange.close();
			return;
		}
		
		if(userFacade.hasUser(cookies[0])) {
			StringBuilder jsonString = handleRequestBody(exchange);
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "application/json");
			ArrayList<ICommandObject> objects = (ArrayList<ICommandObject>) gson.fromJson(jsonString.toString(), new TypeToken<ArrayList<ICommandObject>> (){}.getType());
			String string = modelFacade.executeGameCommands(new Integer(cookies[3]), objects);
			
			String commands = modelFacade.getGameCommands(new Integer(cookies[3]));
			if(commands != null) {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				sendResponseBody(exchange, commands);
				sendResponseBody(exchange, string);
			} else {
				head = exchange.getResponseHeaders();
				head.set("Content-Type", "text/html");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				sendResponseBody(exchange, "user must be logged in and in game");
			}
		} else {
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "user must be logged in and in game");
		}
		exchange.close();
	}

}
