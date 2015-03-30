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
	//	super(modelFacade);
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers head = null;
		StringBuilder string = handleRequestBody(exchange);

		if(!string.toString().equals("")) {
			String[] values = string.toString().split(",");
			String gameId = values[0].substring(10, values[0].length() - 1);
			String fileName = values[1].substring(11, values[1].length() - 2);

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

}
