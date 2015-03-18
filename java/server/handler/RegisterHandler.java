package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLDecoder;

import user.IUserFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RegisterHandler implements HttpHandler {
	
	private IUserFacade userFacade;
	
	public RegisterHandler(IUserFacade userFacade) {
		this.userFacade = userFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		InputStream body = exchange.getRequestBody();
		BufferedReader bw = new BufferedReader(new InputStreamReader(body));
		String decoded = URLDecoder.decode(bw.readLine(), "UTF-8");
		
		String[] params = decoded.split(",");
		String name = params[0].substring(13, params[0].length() - 1);
		String password = params[1].substring(12, params[1].length() - 2);
		
		// do something with name/password
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		exchange.close();
	}

}
