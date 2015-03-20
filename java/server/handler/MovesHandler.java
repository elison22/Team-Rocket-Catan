package handler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
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
	
	public String[] decodeCookie(HttpExchange exchange) throws IOException {
		Headers head = exchange.getRequestHeaders();
		List<String> cookies = head.get("Cookie");
		String cookieHeader = cookies.get(0);
		
		String headerValue = cookieHeader.substring(11);
		String value = URLDecoder.decode(headerValue, "UTF-8");
		String[] values = value.split(",");
		
		String[] cookieItems = new String[3];
		cookieItems[0] = values[1].substring(8, values[1].length() - 1);
		cookieItems[1] = values[2].substring(12, values[2].length() - 1);
		cookieItems[2] = values[3].substring(11, values[3].length() - 1);
		
		return cookieItems;

	}

}
