package handler;

import java.io.IOException;
import java.net.HttpURLConnection;

import shared.dto.CreateGame_Params;

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
		
		if(stringBuild.length() == 0) {
			Headers head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			String encode = "catan.game=" + "-1" + ";Path=/;";
			head.add("Set-cookie", encode);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request");
			exchange.close();
			return;
		}
		
		String[] values = stringBuild.toString().split(",");
		if(values.length != 4) {
			Headers head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			String encode = "catan.game=" + "-1" + ";Path=/;";
			head.add("Set-cookie", encode);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request");
			exchange.close();
			return;
		}
		
		// make sure each of 4 params have a value

	/*	String tiles = values[0].replaceAll("\\s+","");
		tiles = tiles.replaceAll("\"","");
		tiles = tiles.substring(13, tiles.length());
		String nums = values[1].replaceAll("\\s+","");
		nums = nums.replaceAll("\"", "");
		nums = nums.substring(14, nums.length());
		String ports = values[2].replaceAll("\\s+","");
		ports = ports.replaceAll("\"", "");
		ports = ports.substring(12, ports.length());
		String name = values[3].replaceAll("\\s+","");
		name = name.replaceAll("\"", "");
		name = name.substring(5, name.length() - 1);

		// if game name is invalid length
		if(name.length() < 1 || name.length() > 25) {
			Headers head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			String encode = "catan.game=" + "-1" + ";Path=/;";
			head.add("Set-cookie", encode);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request\n game name must be between 1-25 alphanumeric characters");
			exchange.close();
			return;
		}

		// if booleans are invalid values or game name is null
		if((!(tiles.toLowerCase().equals("true") || tiles.toLowerCase().equals("false")) ||
				!(nums.toLowerCase().equals("true") || nums.toLowerCase().equals("false")) ||
				!(ports.toLowerCase().equals("true") || ports.toLowerCase().equals("false"))) ||
				name == null) {
			Headers head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			String encode = "catan.game=" + "-1" + ";Path=/;";
			head.add("Set-cookie", encode);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request\n boolean values must be either \"true\" or \"false\"\n game name can't be null");
			exchange.close();
			return;
		}*/

		CreateGame_Params gameParams = gson.fromJson(stringBuild.toString(), CreateGame_Params.class);		
		String jsonString = modelFacade.createGame(gameParams);
		Headers head = exchange.getResponseHeaders();
		
		int gameId = modelFacade.getCreatedGameId();
		String encode = "catan.game=" + gameId + ";Path=/;";
		head.add("Set-cookie", encode);
		
		if(jsonString != null) {
			head.set("Content-Type", "application/json");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			sendResponseBody(exchange, jsonString);
		} else if(jsonString == null) {
			head.set("Content-Type", "text/html");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request");
		}
		exchange.close();
	}
}
