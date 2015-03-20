package handler;

import java.io.IOException;

import user.IUserFacade;

import com.sun.net.httpserver.HttpExchange;

public class LoginHandler extends UserHandler {

	public LoginHandler(IUserFacade userFacade) {
		super(userFacade);
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		sendInfo(exchange, "login");
	}
}
