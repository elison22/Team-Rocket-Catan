package handler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import command.ICommandObject;
import facade.IModelFacade;

public class GetGamesHandler implements HttpHandler {
	
	private IModelFacade modelFacade;
	
	public GetGamesHandler(IModelFacade modelFacade) {
		super();
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("It's getting here");

	}

}
