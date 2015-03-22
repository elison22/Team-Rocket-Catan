package handler;

import java.io.IOException;
import java.net.HttpURLConnection;

import shared.dto.BuildRoad_Params;

import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class BuildRoadHandler extends MovesHandler {
	
	private IModelFacade modelFacade;
	
	public BuildRoadHandler(IModelFacade modelFacade) {
		super();
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		String[] cookieItems = decodeCookie(exchange);
		StringBuilder stringBuild = handleRequestBody(exchange);
		BuildRoad_Params roadParams = gson.fromJson(stringBuild.toString(), BuildRoad_Params.class);
		String jsonString = modelFacade.buildRoad(new Integer(cookieItems[3]), roadParams);
		
		setResponseCookie(exchange, cookieItems[3]);
		if(jsonString != null) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			sendResponseBody(exchange, jsonString);
		} else if(jsonString == null) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
			sendResponseBody(exchange, "Internal Error!");
		}
		exchange.close();
	}
}
