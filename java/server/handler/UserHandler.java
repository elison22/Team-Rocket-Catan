package handler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import shared.dto.Login_Params;
import user.IUserFacade;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class UserHandler implements HttpHandler {
	
	protected IUserFacade userFacade;
	
	public UserHandler(IUserFacade facade) {
		userFacade = facade;
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {

	}
	
	public void sendInfo(HttpExchange exchange, Login_Params params) throws IOException {
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
	}

}
