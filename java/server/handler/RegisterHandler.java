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

public class RegisterHandler implements HttpHandler {
	
	private IUserFacade userFacade;
	private Gson gson;
	
	public RegisterHandler(IUserFacade userFacade) {
		super();
		this.userFacade = userFacade;
		this.gson = new Gson();
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// Prepare to read the RequestBody inputStream
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
		StringBuilder stringBuilder = new StringBuilder();
		
		// Process inputStream
		String request;
		while ((request = bufferedReader.readLine()) != null)
			stringBuilder.append(request);
		
		// Create parameter object
		Login_Params params = gson.fromJson(stringBuilder.toString(), Login_Params.class);

		if (userFacade.register(params)) {
			
			Headers head = exchange.getResponseHeaders();
			head.set("Content-Type", "text/plain");
			
			//Prepare Cookies
			String encode = "{\"authentication\":" + "\"" + (new Long(System.currentTimeMillis())).toString() 
						  + "\",\"name\":\"" + params.getUser() + "\",\"password\":\"" + params.getPassword() 
						  + "\",\"playerID\":" + userFacade.getUserID(params.getUser()) + "}";
			String encoded = URLEncoder.encode(encode, "UTF-8");
			head.add("Set-cookie", "catan.user=" + encoded + ";Path=/;");
			
			// Login successful? HTTP_OK
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			
			// write response body
			OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
			os.write(new String("success"));
			os.close();
			
		} else {
			// Login failed? HTTP_BAD_REQUEST
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}
		
		exchange.close();
	}

}
