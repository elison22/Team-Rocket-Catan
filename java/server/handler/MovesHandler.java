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

public abstract class MovesHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {}
	
	public String handleCookie(HttpExchange exchange, IModelFacade modelFacade) throws IOException {

		Headers reqHead = exchange.getRequestHeaders();
		List<String> cookie = reqHead.get("Set-cookie");
		

		return null;
	}

}
