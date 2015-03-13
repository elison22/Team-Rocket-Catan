package handler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import command.ICommandObject;
import facade.IModelFacade;

public class CreateGameHandler implements HttpHandler {
	
	private ICommandObject commandObject;
	private IModelFacade modelFacade;
	
	public CreateGameHandler(IModelFacade modelFacade) {
		super();
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub

	}

}
