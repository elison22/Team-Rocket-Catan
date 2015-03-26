package handler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import command.ICommandObject;
import facade.IModelFacade;

public class LoadGameHandler extends MovesHandler {
	
	public LoadGameHandler(IModelFacade modelFacade) {
		super(modelFacade);
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers head = null;
		StringBuilder string = handleRequestBody(exchange);
//System.out.println(string);

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
				sendResponseBody(exchange, "Could not save game");
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
