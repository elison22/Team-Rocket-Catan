package handler;

import java.io.IOException;
import java.net.HttpURLConnection;

import shared.dto.JoinGame_Params;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class JoinGameHandler extends NonMoveHandler {
	
	private IModelFacade modelFacade;
	
	public JoinGameHandler(IModelFacade modelFacade) {
		super();
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String[] cookie = decodeCookie(exchange);
		StringBuilder stringBuild = handleRequestBody(exchange);
		JoinGame_Params params = gson.fromJson(stringBuild.toString(), JoinGame_Params.class);
		
		Headers head = exchange.getResponseHeaders();
		head.set("Content-Type", "text/html");
		int gameId = modelFacade.getCreatedGameId();
		String encode = "catan.game=" + gameId + ";Path=/;";
		head.add("Set-cookie", encode);
		
		if(modelFacade.joinGame(params, cookie[1], new Integer(cookie[2]))) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			sendResponseBody(exchange, "Success");
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
			sendResponseBody(exchange, "Internal Error!");
		}
		exchange.close();
	}

}
