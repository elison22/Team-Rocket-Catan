package handler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import facade.IModelFacade;

public class GetGamesHandler implements HttpHandler {
	
	private IModelFacade modelFacade;
	
	public GetGamesHandler(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		String games = modelFacade.listGames();
		
		Headers head = exchange.getResponseHeaders();
		head.set("Content-Type", "application/json");
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		
		OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
		os.write(games);
		os.close();

	}

}
