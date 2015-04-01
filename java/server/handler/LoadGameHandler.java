package handler;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class LoadGameHandler extends NonMoveHandler {
	
	private IModelFacade modelFacade;
	
	public LoadGameHandler(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
		//super(modelFacade);
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers head = null;
		StringBuilder string = handleRequestBody(exchange);

		if(!string.toString().equals("")) {
			String[] values = string.toString().split(",");
			String fileName = values[0].substring(12, values[0].length() - 2);

			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			
			
			if(modelFacade.loadGame(fileName)) {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				sendResponseBody(exchange, "Success");
			} else {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				sendResponseBody(exchange, "Could not load game");
			}
			
		} else {
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			head.add("Set-cookie", "catan.game=" + 0 + ";Path=/;");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Unable to load game");
		}
		exchange.close();
	}

}
