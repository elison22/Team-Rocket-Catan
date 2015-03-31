package handler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class ResetGameHandler extends NonMoveHandler {
	
	private IModelFacade modelFacade;
	
	public ResetGameHandler(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers head = exchange.getRequestHeaders();
		List<String> cookies = head.get("Cookie");
		String cookieHeader = cookies.get(0);
		
		String headerValue = cookieHeader.substring(11);
		String value = URLDecoder.decode(headerValue, "UTF-8");
		String[] values = value.split(",");
		if(values.length < 4) {
			head.set("Content-Type", "text/html");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
			os.write("Invalid Request");
			os.close();
			exchange.close();
			return;
		}
		
		String[] val = values[0].split(";");
		String game = val[0];
		Integer gameId = new Integer(game);
		String jsonString = modelFacade.resetGame(new Integer(gameId));
		
		if(jsonString != null) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
			os.write(jsonString);
			os.close();
		} else {
			head.set("Content-Type", "text/html");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
			os.write("You gone done messed up paatna");
			os.close();
		}
		exchange.close();
	}
}
