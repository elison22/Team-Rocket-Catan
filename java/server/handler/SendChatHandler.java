package handler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import command.ICommandObject;
import facade.IModelFacade;

public class SendChatHandler extends MovesHandler {
	
	private IModelFacade modelFacade;
	
	public SendChatHandler(IModelFacade modelFacade) {
		super();
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
