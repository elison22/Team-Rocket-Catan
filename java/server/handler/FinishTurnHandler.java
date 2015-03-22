package handler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class FinishTurnHandler extends MovesHandler {
	
	public FinishTurnHandler(IModelFacade modelFacade) {
		super(modelFacade);
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
