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
	//	super(modelFacade);
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
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

		String jsonString = modelFacade.resetGame(new Integer(cookieItems[2]));
		
		head.set("Content-Type", "application/json");
		
		if(jsonString != null) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
			os.write(jsonString);
			os.close();
		} else {
			head.set("Content-Type", "text/html");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
			OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
			os.write("Unable to reset");
			os.close();
		}
		exchange.close();
	}

}
