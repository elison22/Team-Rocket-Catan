package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import shared.dto.Login_Params;
import user.IUserFacade;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class UserHandler implements HttpHandler {
	
	protected IUserFacade userFacade;
	protected Gson gson;
	
	public UserHandler(IUserFacade facade) {
		userFacade = facade;
		gson = new Gson();
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {

	}
	
	public void sendInfo(HttpExchange exchange, String action) throws IOException {
		Headers head = null;
		
		// Prepare to read the RequestBody inputStream
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
		StringBuilder stringBuilder = new StringBuilder();
		
		// Process inputStream
		String request;
		while ((request = bufferedReader.readLine()) != null)
			stringBuilder.append(request);
		
		// if illegal # of parameters through swagger
		if(stringBuilder.toString().split(",").length != 2) {	
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			head.add("Set-cookie", "catan.user=invalid" + ";Path=/;");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request");
			exchange.close();
			return;
		}
		
		String[] values = stringBuilder.toString().split(",");
		
		if(values[0].length() < 17) {
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			head.add("Set-cookie", "catan.user=invalid" + ";Path=/;");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request");
			exchange.close();
			return;
		}
		
		// Create parameter object
		Login_Params params = gson.fromJson(stringBuilder.toString(), Login_Params.class);
		
		// if trying to log in w/ unregistered username
		if(!userFacade.hasUser(params.getUser()) && action.equals("login")) {
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			head.add("Set-cookie", "catan.user=invalid" + ";Path=/;");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request:\n username doesn't exist");
			exchange.close();
			return;
		}
		
		// if username is null through swagger
		if(params.getUser() == null || params.getPassword() == null) {
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			head.add("Set-cookie", "catan.user=invalid" + ";Path=/;");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request:\n username must be 3-7 alphanumeric characters\n password must be 5-25 alphanumeric characters");
			exchange.close();
			return;
		}
		
		// if trying to register with userName that is already taken
		if(userFacade.hasUser(params.getUser()) && action.equals("register")) {
			head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/html");
			head.add("Set-cookie", "catan.user=invalid" + ";Path=/;");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Username already taken");
			exchange.close();
			return;
		}
		
		head = exchange.getResponseHeaders();
		head.set("Content-Type", "text/html");
		if (execute(action, params)) {
			//Prepare Cookies
			String encode = "{\"authentication\":" + "\"" + (new Long(System.currentTimeMillis())).toString() 
						  + "\",\"name\":\"" + params.getUser() + "\",\"password\":\"" + params.getPassword() 
						  + "\",\"playerID\":" + userFacade.getUserID(params.getUser()) + "}";
			String encoded = URLEncoder.encode(encode, "UTF-8");
			head.add("Set-cookie", "catan.user=" + encoded + ";Path=/;");
			
			// Login successful? HTTP_OK
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			
			// write response body
			sendResponseBody(exchange, "Success");
			
		} else {
			// Login failed? HTTP_BAD_REQUEST
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Invalid request:\n username must be 3-7 alphanumeric characters\n password must be 5-25 alphanumeric characters");
		}
	
		exchange.close();
	}
	
	public void sendResponseBody(HttpExchange exchange, String message) throws IOException {
		OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
		os.write(message);
		os.close();
	}
	
	public boolean execute(String action, Login_Params params) {
		if(action == "register")
			return userFacade.register(params);
		else if(action == "login")
			return userFacade.login(params);
		return false;
	}

}
