package handler;

import java.io.IOException;
import java.net.HttpURLConnection;

import shared.dto.JoinGame_Params;
import user.IUserFacade;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class JoinGameHandler extends NonMoveHandler {
	
	private IModelFacade modelFacade;
	private IUserFacade userFacade;
	
	public JoinGameHandler(IModelFacade modelFacade, IUserFacade userFacade) {
		super();
		this.modelFacade = modelFacade;
		this.userFacade = userFacade;
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
/*System.out.println(cookie[0] + ", " + cookie[2]);
		if(!userFacade.hasUser(cookie[0])) {
System.out.println("bad");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "User is not registered");
			exchange.close();
			return;
		}*/
		if(modelFacade.joinGame(params, cookie[0], new Integer(cookie[2]))) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			sendResponseBody(exchange, "Success");
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
			sendResponseBody(exchange, "Internal Error!");
		}
		exchange.close();
	}

}
