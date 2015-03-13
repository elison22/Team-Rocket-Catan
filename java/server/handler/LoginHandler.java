package handler;

import java.io.IOException;

import user.IUserFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import command.ICommandObject;

public class LoginHandler implements HttpHandler {
	
	private ICommandObject commandObject;
	private IUserFacade userFacade;
	
	public LoginHandler(IUserFacade userFacade) {
		super();
		this.userFacade = userFacade;
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub

	}

}
