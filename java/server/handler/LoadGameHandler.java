package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import facade.IModelFacade;

public class LoadGameHandler implements HttpHandler {
	
	private IModelFacade modelFacade;
	
	public LoadGameHandler(IModelFacade modelFacade) {
		super();
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
		StringBuilder stringBuilder = new StringBuilder();
		
		String request;
		while ((request = bufferedReader.readLine()) != null)
			stringBuilder.append(request);

		System.out.println(stringBuilder.toString());
	}

}
