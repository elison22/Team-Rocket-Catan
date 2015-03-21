package handler;

import java.io.IOException;
import java.net.HttpURLConnection;

import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class CreateGameHandler extends NonMoveHandler {
	
	private IModelFacade modelFacade;
	
	public CreateGameHandler(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		StringBuilder stringBuild = handleRequestBody(exchange);
		CreateGame_Params gameParams = gson.fromJson(stringBuild.toString(), CreateGame_Params.class);
		String jsonString = modelFacade.createGame(gameParams);
		
		Headers head = exchange.getResponseHeaders();
		head.set("Content-Type", "application/json");
		int gameId = modelFacade.getCreatedGameId();
		String encode = "catan.game=" + gameId + ";Path=/;";
		head.add("Set-cookie", encode);
		
		if(jsonString != null) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			sendResponseBody(exchange, jsonString);
		} else if(jsonString == null) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
			sendResponseBody(exchange, "Internal Error!");
		}
		exchange.close();
	}
}
