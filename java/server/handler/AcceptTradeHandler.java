package handler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import command.ICommandObject;

import facade.IModelFacade;

public class AcceptTradeHandler implements HttpHandler {
	
	private ICommandObject commandObject;
	private IModelFacade modelFacade;

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub

	}

}
