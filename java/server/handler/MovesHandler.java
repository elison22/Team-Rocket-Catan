package handler;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class MovesHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {}
	
	public void handleCookie(HttpExchange exchange) {
		
		
		
		Headers head = exchange.getRequestHeaders();

	}

}
