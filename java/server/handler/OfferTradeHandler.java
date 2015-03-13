package handler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import command.ICommandObject;

import facade.IModelFacade;

public class OfferTradeHandler implements HttpHandler {
	
	private ICommandObject commandObject;
	private IModelFacade modelFacade;
	
	public OfferTradeHandler(IModelFacade modelFacade) {
		super();
		this.modelFacade = modelFacade;
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub

	}

}
