package handler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;

import shared.dto.JoinGame_Params;
import shared.dto.Login_Params;
import user.IUserFacade;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import facade.IModelFacade;

public class AddAIHandler extends NonMoveHandler{
	
	private IModelFacade modelFacade;
	private IUserFacade userFacade;
	
	private HashMap<Integer, String> aiPlayers = new HashMap<Integer, String>();
	private TreeSet<String> inGamePlayers = new TreeSet<String>();

	public AddAIHandler(IModelFacade modelFacade, IUserFacade userFacade) {
		this.modelFacade = modelFacade;
		this.userFacade = userFacade;
		aiPlayers.put(-5, "John");
		aiPlayers.put(-6, "Steven");
		aiPlayers.put(-8, "Stacey");
	}
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		String[] cookie = decodeCookie(exchange);
		JoinGame_Params params = new JoinGame_Params();
		
		Iterator<Entry<Integer, String>> it = aiPlayers.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<Integer, String> pair = (Entry<Integer, String>)it.next();
	        if(!inGamePlayers.contains(pair.getValue())) {
	        	inGamePlayers.add(pair.getValue());
	        	params.setId(pair.getKey());
	        	switch(pair.getValue()) {
		        	case "John":
		        		params.setColor("puce");
		        		break;
		        	case "Steven":
		        		params.setColor("yellow");
		        		break;
		        	case "Stacey":
		        		params.setColor("pink");
		        		break;
	        	}
	        	break;
	        }
		}
		
		
		Headers head = exchange.getResponseHeaders();
		head.set("Content-Type", "text/html");
		int gameId = modelFacade.getCreatedGameId();
		String encode = "catan.game=" + gameId + ";Path=/;";
		head.add("Set-cookie", encode);
		
		boolean work = false;
		
		while(!work) {
			
			work = modelFacade.joinGame(params, cookie[1], new Integer(cookie[2]));
		}
		
		if(modelFacade.joinGame(params, cookie[1], new Integer(cookie[2]))) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			sendResponseBody(exchange, "Success");
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
			sendResponseBody(exchange, "Internal Error!");
		}
		exchange.close();
	}
	
	public void registerAI(Login_Params params) throws IOException {
		userFacade.register(params);
	}
}
