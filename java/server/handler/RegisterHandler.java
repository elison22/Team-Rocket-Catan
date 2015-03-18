package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import shared.dto.Login_Params;
import user.IUserFacade;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RegisterHandler implements HttpHandler {
	
	private IUserFacade userFacade;
	private Gson gson;
	
	public RegisterHandler(IUserFacade userFacade) {
		this.userFacade = userFacade;
		this.gson = new Gson();
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
		StringBuilder stringBuilder = new StringBuilder();
		
		// Process inputStream
		String request;
		while ((request = bufferedReader.readLine()) != null)
			stringBuilder.append(request);
		
		// Create parameter object
		Login_Params params = gson.fromJson(stringBuilder.toString(), Login_Params.class);
		
		if (userFacade.register(params)) {
			// Login successful? HTTP_OK
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			
			//Prepare Cookies
			
			
		} else {
			// Login failed? HTTP_BAD_REQUEST
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}
		
		exchange.close();
	}

}
