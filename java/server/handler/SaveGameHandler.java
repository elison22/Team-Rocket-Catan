package handler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class SaveGameHandler extends NonMoveHandler {
	
	private IModelFacade modelFacade;
	private Gson gson;
	
	public SaveGameHandler(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
		gson = new Gson();
	//	super(modelFacade);
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers head = null;
		StringBuilder string = handleRequestBody(exchange);
//System.out.println(string.toString());
//		JsonObject js = new JsonParser().parse(string.toString()).getAsJsonObject();
//System.out.println(js.has("name"));
//System.out.println("here");
//System.out.println(js);
//		JsonElement gameId = js.get("id");
//		JsonElement fileName = js.get("name");
//System.out.println(gameId.getAsString() + ", " + fileName.getAsString());
//		if(fileName.isJsonNull() || gameId.isJsonNull()) {
//			head = exchange.getResponseHeaders();
//			head.set("Content-Type", "text/html");
//			head.add("Set-cookie", "catan.game=" + 0 + ";Path=/;");
//			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
//			sendResponseBody(exchange, "Invalid request");
//			exchange.close();
//			return;
//		}
		
		if(!string.toString().equals("")) {
			String[] values = string.toString().split(",");
			
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
System.out.println("here");
			if(modelFacade.saveGame(new Integer(gameId), fileName)) {
				System.out.println("here1");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				sendResponseBody(exchange, "Success");
			} else {
				System.out.println("here2");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				sendResponseBody(exchange, "Could not save game");
			}
//System.out.println("here");
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
