package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.util.List;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class NonMoveHandler implements HttpHandler {
	
	protected Gson gson;
	
	public NonMoveHandler() {
		gson = new Gson();
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub

	}
	
	public String[] decodeCookie(HttpExchange exchange) throws IOException {
		
		Headers head = exchange.getRequestHeaders();
		List<String> cookies = head.get("Cookie");
		String cookieHeader = cookies.get(0);
		
		String headerValue = cookieHeader.substring(11);
		String value = URLDecoder.decode(headerValue, "UTF-8");
		String[] values = value.split(",");
		
		String[] idGame = values[3].split(";");
		
		String[] cookieItems = new String[4];
		cookieItems[0] = values[1].substring(8, values[1].length() - 1);
		cookieItems[1] = values[2].substring(12, values[2].length() - 1);
		cookieItems[2] = idGame[0].substring(11, idGame[0].length() - 1);
		if(idGame.length == 2)
			cookieItems[3] = idGame[1].substring(12, idGame[1].length());
		
		return cookieItems;
	}
	
	public StringBuilder handleRequestBody(HttpExchange exchange) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
		StringBuilder stringBuilder = new StringBuilder();
		
		String request;
		while ((request = bufferedReader.readLine()) != null)
			stringBuilder.append(request);
		
		return stringBuilder;
	}
	
	public void sendResponseBody(HttpExchange exchange, String message) throws IOException {
		OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
		os.write(message);
		os.close();
	}
	
	public void setGameCookie(HttpExchange exchange) {
		
	}

}
