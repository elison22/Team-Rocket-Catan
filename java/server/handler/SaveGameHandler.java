package handler;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class SaveGameHandler extends NonMoveHandler {
	
	private IModelFacade modelFacade;
	
	public SaveGameHandler(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers head = null;
		StringBuilder string = handleRequestBody(exchange);
		
		if(!string.toString().equals("")) {
			String[] values = string.toString().split(",");
			if(values.length != 2) {
				head = exchange.getResponseHeaders();
				head.set("Content-Type", "text/html");
				head.add("Set-cookie", "catan.game=" + 0 + ";Path=/;");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				sendResponseBody(exchange, "Invalid request");
				exchange.close();
				return;
			}
	
			String[] game = values[0].split(":");
			String[] file = values[1].split(":");
			if(game.length != 2 || file.length != 2) {
				head = exchange.getResponseHeaders();
				head.set("Content-Type", "text/html");
				head.add("Set-cookie", "catan.game=" + 0 + ";Path=/;");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				sendResponseBody(exchange, "Invalid request");
				exchange.close();
				return;
			}

			String gameId = game[1].trim().replaceAll("\"", "");
			String fileName = file[1].substring(0, file[1].length() - 1).trim().replaceAll("\"", "");
			if(!fileName.matches("^[a-zA-z0-9]{1,25}$")) {
				head = exchange.getResponseHeaders();
				head.set("Content-Type", "text/html");
				head.add("Set-cookie", "catan.game=" + 0 + ";Path=/;");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				sendResponseBody(exchange, "Invalid request\n file name must be between 1-25 alphanumeric characters");
				exchange.close();
				return;
			} 

			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			head.add("Set-cookie", "catan.game=" + gameId + ";Path=/;");

			if(modelFacade.saveGame(new Integer(gameId), fileName)) {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				sendResponseBody(exchange, "Success");
			} else {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				sendResponseBody(exchange, "Could not save game");
			}

		} else {
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			head.add("Set-cookie", "catan.game=" + 0 + ";Path=/;");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request");
		}
		exchange.close();
	}
	
	class SaveGameParams {
		public int gameId;
		public String fileName;
		
		
	}

}
