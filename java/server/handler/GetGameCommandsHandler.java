package handler;

import java.io.IOException;
import java.net.HttpURLConnection;

import serializer.ServerSerializer;
import user.IUserFacade;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import command.ICommandObject;
import facade.IModelFacade;

public class GetGameCommandsHandler extends NonMoveHandler {
	
	private IModelFacade modelFacade;
	private IUserFacade userFacade;
	
	public GetGameCommandsHandler(IModelFacade modelFacade, IUserFacade userFacade) {
		super();
		this.modelFacade = modelFacade;
		this.userFacade = userFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String[] cookies = decodeCookie(exchange);
		Headers head = null;
		
		
		String method = exchange.getRequestMethod();
		if(method.equals("GET")) {
			
			if(cookies.length != 4) {
				head = exchange.getResponseHeaders();
				head.set("Content-Type", "text/html");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				sendResponseBody(exchange, "user must be logged in and in game");
				exchange.close();
				return;
			}
			
			if(userFacade.hasUser(cookies[0])) {
				head = exchange.getResponseHeaders();
				head.set("Content-Type", "application/json");
				String commands = modelFacade.getGameCommands(new Integer(cookies[3]));
				if(commands != null) {
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
					sendResponseBody(exchange, commands);
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
		} else if(method.equals("POST")) {

			StringBuilder jsonString = handleRequestBody(exchange);
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "application/json");

			ICommandObject[] objects = null;
			String string = null;
			try {
				objects = new ServerSerializer().deSerializeCommands(jsonString.toString());
				string = modelFacade.executeGameCommands(objects);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(objects != null) {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				sendResponseBody(exchange, string);
			} else {
				head = exchange.getResponseHeaders();
				head.set("Content-Type", "text/html");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				sendResponseBody(exchange, "Invalid format");
			}
			
		}
		exchange.close();
	}

}
