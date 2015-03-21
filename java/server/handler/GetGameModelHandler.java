package handler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class GetGameModelHandler extends MovesHandler {
	
	private IModelFacade modelFacade;
	
	public GetGameModelHandler(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {	
		String[] cookieItems = decodeCookie(exchange);
		String userVersionId = exchange.getRequestURI().getQuery();
		userVersionId = userVersionId.substring(8, userVersionId.length());
		
		setResponseCookie(exchange, cookieItems[3]);
		
		if(new Integer(userVersionId) == modelFacade.getVersionId(new Integer(cookieItems[3]))) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			sendResponseBody(exchange, "true");
		} else {
			String gameModel = modelFacade.getGameModel(new Integer(cookieItems[3]));
			
			if(gameModel != null) {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				sendResponseBody(exchange, gameModel);
			} else {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
				sendResponseBody(exchange, "Internal Error!");
			}
		}
		exchange.close();
	}
}
