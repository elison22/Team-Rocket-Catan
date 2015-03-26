package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.util.List;

import shared.dto.AcceptTrade_Params;
import shared.dto.BuildCity_Params;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.BuyDevCard_Params;
import shared.dto.DiscardCards_Params;
import shared.dto.FinishTurn_Params;
import shared.dto.MaritimeTrade_Params;
import shared.dto.Monopoly_Params;
import shared.dto.Monument_Params;
import shared.dto.OfferTrade_Params;
import shared.dto.RoadBuilding_Params;
import shared.dto.RobPlayer_Params;
import shared.dto.RollNumber_Params;
import shared.dto.SendChat_Params;
import shared.dto.Soldier_Params;
import shared.dto.YearOfPlenty_Params;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import facade.IModelFacade;

public class MovesHandler implements HttpHandler {

	protected Gson gson;
	protected IModelFacade modelFacade;
	
	public MovesHandler(IModelFacade facade) {
		gson = new Gson();
		modelFacade = facade;
	}
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String[] cookieItems = decodeCookie(exchange);
		StringBuilder stringBuild = handleRequestBody(exchange);
		String jsonString = null;
		String path = exchange.getRequestURI().getPath();
		path = path.substring(7);
		switch (path) {
			case "sendChat":
				SendChat_Params chatParams = gson.fromJson(stringBuild.toString(), SendChat_Params.class);
				jsonString = modelFacade.sendChat(new Integer(cookieItems[3]), chatParams);
				break;
			case "rollNumber":
				RollNumber_Params numParams = gson.fromJson(stringBuild.toString(), RollNumber_Params.class);
				jsonString = modelFacade.rollNumber(new Integer(cookieItems[3]), numParams);
				break;
			case "robPlayer":
				RobPlayer_Params robParams = gson.fromJson(stringBuild.toString(), RobPlayer_Params.class);
				jsonString = modelFacade.robPlayer(new Integer(cookieItems[3]), robParams);
				break;
			case "finishTurn":
				FinishTurn_Params finishTurnParams = gson.fromJson(stringBuild.toString(), FinishTurn_Params.class);
				jsonString = modelFacade.finishTurn(new Integer(cookieItems[3]), finishTurnParams);
				break;
			case "buyDevCard":
				BuyDevCard_Params devCarParams = gson.fromJson(stringBuild.toString(), BuyDevCard_Params.class);
				jsonString = modelFacade.buyDevCard(new Integer(cookieItems[3]), devCarParams);
				break;
			case "Year_of_Plenty":
				YearOfPlenty_Params Params = gson.fromJson(stringBuild.toString(), YearOfPlenty_Params.class);
				jsonString = modelFacade.doYearOfPlenty(new Integer(cookieItems[3]), Params);
				break;
			case "Road_Building":
				RoadBuilding_Params roadParams = gson.fromJson(stringBuild.toString(), RoadBuilding_Params.class);
				jsonString = modelFacade.doRoadBuilding(new Integer(cookieItems[3]), roadParams);
				break;
			case "Soldier":
				Soldier_Params soldierParams = gson.fromJson(stringBuild.toString(), Soldier_Params.class);
				jsonString = modelFacade.doSoldier(new Integer(cookieItems[3]), soldierParams);
				break;
			case "Monopoly":
				Monopoly_Params monopolyParams = gson.fromJson(stringBuild.toString(), Monopoly_Params.class);
				jsonString = modelFacade.doMonopoly(new Integer(cookieItems[3]), monopolyParams);
				break;
			case "Monument":
				Monument_Params monumentParams = gson.fromJson(stringBuild.toString(), Monument_Params.class);
				jsonString = modelFacade.doMonument(new Integer(cookieItems[3]), monumentParams);
				break;
			case "buildRoad":
				BuildRoad_Params buildRoadParams = gson.fromJson(stringBuild.toString(), BuildRoad_Params.class);
				jsonString = modelFacade.buildRoad(new Integer(cookieItems[3]), buildRoadParams);
				break;
			case "buildSettlement":
				BuildSettlement_Params settlementParams = gson.fromJson(stringBuild.toString(), BuildSettlement_Params.class);
				jsonString = modelFacade.buildSettlement(new Integer(cookieItems[3]), settlementParams);
				break;
			case "buildCity":
				BuildCity_Params cityParams = gson.fromJson(stringBuild.toString(), BuildCity_Params.class);
				jsonString = modelFacade.buildCity(new Integer(cookieItems[3]), cityParams);
				break;
			case "offerTrade":
				OfferTrade_Params tradeParams = gson.fromJson(stringBuild.toString(), OfferTrade_Params.class);
				jsonString = modelFacade.offerTrade(new Integer(cookieItems[3]), tradeParams);
				break;
			case "acceptTrade":
				AcceptTrade_Params acceptParams = gson.fromJson(stringBuild.toString(), AcceptTrade_Params.class);
				jsonString = modelFacade.acceptTrade(new Integer(cookieItems[3]), acceptParams);
				break;
			case "maritimeTrade":
				MaritimeTrade_Params marTradeParams = gson.fromJson(stringBuild.toString(), MaritimeTrade_Params.class);
				jsonString = modelFacade.maritimeTrade(new Integer(cookieItems[3]), marTradeParams);
				break;
			case "discardCards":
				DiscardCards_Params discardParams = gson.fromJson(stringBuild.toString(), DiscardCards_Params.class);
				jsonString = modelFacade.discardCards(new Integer(cookieItems[3]), discardParams);
				break;
	
		}
		setResponseCookie(exchange, cookieItems[3]);
		if(jsonString != null) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			sendResponseBody(exchange, jsonString);
		} else if(jsonString == null) {
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			sendResponseBody(exchange, "Internal Error!");
		}
		exchange.close();
	}
	
	public String[] decodeCookie(HttpExchange exchange) throws IOException {		
		Headers head = exchange.getRequestHeaders();
		List<String> cookies = head.get("Cookie");
		String cookieHeader = cookies.get(0);
		
		String headerValue = cookieHeader.substring(11);
		String value = URLDecoder.decode(headerValue, "UTF-8");
		String[] values = value.split(",");
		
		String[] idGame = values[3].split(";");
		
		String[] cookieItems = new String[4];
		cookieItems[0] = values[1].substring(8, values[1].length() - 1);
		cookieItems[1] = values[2].substring(12, values[2].length() - 1);
		cookieItems[2] = idGame[0].substring(11, idGame[0].length() - 1);
		cookieItems[3] = idGame[1].substring(12, idGame[1].length());
		
		return cookieItems;
	}
	
	public StringBuilder handleRequestBody(HttpExchange exchange) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
		StringBuilder stringBuilder = new StringBuilder();
		
		String request;
		while ((request = bufferedReader.readLine()) != null)
			stringBuilder.append(request);
		
		return stringBuilder;
	}
	
	public void sendResponseBody(HttpExchange exchange, String message) throws IOException {
		OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
		os.write(message);
		os.close();
	}
	
	public void setResponseCookie(HttpExchange exchange, String gameId) {
		Headers head = exchange.getResponseHeaders();
		head.set("Content-Type", "application/json");
		head.add("Set-cookie", "catan.game=" + gameId + ";Path=/;");
	}
}
