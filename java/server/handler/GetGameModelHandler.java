package handler;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class GetGameModelHandler extends NonMoveHandler {
	
	private IModelFacade modelFacade;
	
	public GetGameModelHandler(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
//		super(modelFacade);
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {	
		String[] cookieItems = decodeCookie(exchange);
		String userVersionId = exchange.getRequestURI().getQuery();
		userVersionId = userVersionId.substring(8, userVersionId.length());
		
		setResponseCookie(exchange, cookieItems[3]);
		
		if(new Integer(userVersionId) == modelFacade.getVersionId(new Integer(cookieItems[3]))) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			sendResponseBody(exchange, "\"true\"\n");
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
