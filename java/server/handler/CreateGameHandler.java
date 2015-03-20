package handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import facade.IModelFacade;

public class CreateGameHandler implements HttpHandler {
	
	private IModelFacade modelFacade;
	
	public CreateGameHandler(IModelFacade modelFacade) {
		super();
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers head = exchange.getRequestHeaders();
		List<String> cookies = head.get("Cookie");
		InputStream is = exchange.getRequestBody();
		is.close();
	}

}
