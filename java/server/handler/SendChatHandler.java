package handler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class SendChatHandler extends MovesHandler {
	
	public SendChatHandler(IModelFacade modelFacade) {
		super(modelFacade);
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
