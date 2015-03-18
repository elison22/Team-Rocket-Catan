package handler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class MovesHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange arg0) throws IOException {}
	
	public void handleCookie(HttpExchange arg0) {
		
	}

}
