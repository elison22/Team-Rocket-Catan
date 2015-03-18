package handler;

import java.io.IOException;

import user.IUserFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import command.ICommandObject;

public class LoginHandler implements HttpHandler {
	
	private IUserFacade userFacade;
	
	public LoginHandler(IUserFacade userFacade) {
		super();
		this.userFacade = userFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
