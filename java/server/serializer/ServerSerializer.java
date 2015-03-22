package serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.sboard.ServerBoard;
import model.sboard.ServerConstructable;
import model.sboard.ServerHexTile;
import model.scards.ServerDevCard;
import model.schat.ServerMessage;
import model.sgame.ServerGame;
import model.sgame.ServerTurnTracker;
import model.splayer.ServerPlayer;
import model.strade.ServerTradeOffer;
import serializer.json.JsonClientModel;
import serializer.json.JsonDevCardList;
import serializer.json.JsonEdgeLocation;
import serializer.json.JsonHex;
import serializer.json.JsonHexLocation;
import serializer.json.JsonMap;
import serializer.json.JsonMessageLine;
import serializer.json.JsonMessageList;
import serializer.json.JsonPlayer;
import serializer.json.JsonPort;
import serializer.json.JsonResourceList;
import serializer.json.JsonRoad;
import serializer.json.JsonTradeOffer;
import serializer.json.JsonTurnTracker;
import serializer.json.JsonVertexLocation;
import serializer.json.JsonVertexObject;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.dto.Game_DTO;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import com.google.gson.Gson;

/**
 * @author Chad
 *
 * This class serializes all data that needs to reach the client in JSON format.
 */
public class ServerSerializer {
	
	Gson gson;

	public ServerSerializer() {
		gson = new Gson();
	}
	
	/**Converts a given model into json format.
	 * 
	 * @param serverModel The model of the game to be serialized.
	 * @return The json format of the model as a String.
	 */
	public String serializeGameModel(ServerGame serverModel) {
		return gson.toJson(convertServerModel(serverModel));
	}
	
	/**Converts the current list of games to a json array inside a String.
	 * 
	 * @param games The array of games to be serialized.
	 * @return The String containing the json array of games.
	 */
	public String serializeGameList(Game_DTO[] games) {
		return gson.toJson(games);
	}
	
	/**Serializes a newly created game in json format.
	 * 
	 * @param newGame The newly created game to be serialized.
	 * @return The String containing the json format of the newly created game.
	 */
	public String serializeNewGame(Game_DTO newGame) {
		return gson.toJson(newGame);
	}
	
	/**Serializes the player data to be transmitted as a cookie.
	 * 
	 * @param player The player whose info needs to be serialized.
	 * @return The String containing the json data.
	 */
	public String serializeCookie(JsonPlayer player) {
		return null;
	}
	
	/**************************************************************************
	 *  All following methods are used in convert the serverModel into a client
	 *  compatible Json String. 
	 **************************************************************************/
	
	private JsonClientModel convertServerModel(ServerGame serverModel) {
		return new JsonClientModel(convertResourceList(serverModel.getCardBank()),
								   convertChatList(serverModel.getChat()),
								   convertChatList(serverModel.getGameHistory()),
								   convertMap(serverModel.getMap()),
								   convertPlayerList(serverModel.getPlayerList()),
								   convertTradeOffer(serverModel.getTradeOffer()),
								   convertDevCardList(serverModel.getDevBank()),
								   convertTurnTracker(serverModel.getTurnTracker()),
								   serverModel.getVersionNumber(),
								   serverModel.getWinner());
	}
	
	private JsonResourceList convertResourceList(Map<ResourceType, Integer> resourceMap) {
		return new JsonResourceList(resourceMap.get(ResourceType.BRICK), 
									resourceMap.get(ResourceType.ORE),
									resourceMap.get(ResourceType.SHEEP),
									resourceMap.get(ResourceType.WHEAT),
									resourceMap.get(ResourceType.WOOD));
	}
	
	private JsonMessageList convertChatList(List<ServerMessage> messages) {
		JsonMessageLine[] jsonMessages = new JsonMessageLine[messages.size()];
		
		int i = 0;
		for (ServerMessage sm : messages) {
			jsonMessages[i] = new JsonMessageLine(sm.getMessage(), sm.getOwner());
			++i;
		}
		return new JsonMessageList(jsonMessages);
	}
	
	private JsonMap convertMap(ServerBoard map) {
		return new JsonMap(convertHexes(map.getTiles()),
						   convertPorts(map.getPorts()),
						   convertRoads(map.getRoadPieces()),
						   convertSettlements(map.getBuildingPieces()),
						   convertCities(map.getBuildingPieces()),
						   3,
						   convertRobber(map.getRobberLoc()));
	}
	
	private JsonHex[] convertHexes(HashMap<HexLocation, ServerHexTile> tiles) {
		JsonHex[] jsonHexes = new JsonHex[tiles.size()];
		
		int i = 0;
		for (Map.Entry<HexLocation, ServerHexTile> entry : tiles.entrySet()) {
			HexLocation hexLocation = entry.getKey();
			ServerHexTile serverHexTile = entry.getValue();
			
			jsonHexes[i] = new JsonHex(new JsonHexLocation(hexLocation.getX(), hexLocation.getY()),
									   serverHexTile.getType().toString(),
									   serverHexTile.getDiceNum());
			++i;
		}
		return jsonHexes;
	}
	
	private JsonPort[] convertPorts(HashMap<VertexLocation, PortType> ports) {
		JsonPort[] jsonPorts = new JsonPort[ports.size()];
		
		int i = 0;
		for (Map.Entry<VertexLocation, PortType> entry : ports.entrySet()) {
			VertexLocation vertex = entry.getKey();
			PortType portType = entry.getValue();
			
			String resource;
			int ratio;
			switch (portType) {
				case THREE_FOR_ONE:
					resource = null;
					ratio = 3;
				default:
					resource = portType.toString();
					ratio = 2;
			}
			
			jsonPorts[i] = new JsonPort(resource,
									    new JsonHexLocation(vertex.getHexLoc().getX(), vertex.getHexLoc().getY()),
									    vertex.getDir().toString(),
									    ratio);
			++i;
		}
		return jsonPorts;
	}
	
	private JsonRoad[] convertRoads(HashMap<EdgeLocation, ServerConstructable> roadLoc) {
	
		ArrayList<JsonRoad> roads = new ArrayList<JsonRoad>();
		Iterator<Entry<EdgeLocation, ServerConstructable>> it = roadLoc.entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry<EdgeLocation, ServerConstructable> pair = (Entry<EdgeLocation, ServerConstructable>)it.next();
	        EdgeLocation edgeLoc = pair.getKey();
	        HexLocation hex = edgeLoc.getHexLoc();
	        String dir = edgeLoc.getDir().toString();
	        JsonEdgeLocation jEdgeLoc = new JsonEdgeLocation(hex.getX(), hex.getY(), dir);
	        int owner = pair.getValue().getOwner();
	        roads.add(new JsonRoad(owner, jEdgeLoc));
	    }
		if(roads.size() > 0) 
			return (JsonRoad[]) roads.toArray();
		else
			return new JsonRoad[0];
	}
	
	private JsonVertexObject[] convertSettlements(HashMap<VertexLocation, ServerConstructable> buildings) {
		ArrayList<JsonVertexObject> settlements = new ArrayList<JsonVertexObject>();
		Iterator<Entry<VertexLocation, ServerConstructable>> it = buildings.entrySet().iterator();
		
		while (it.hasNext()) {
	        Map.Entry<VertexLocation, ServerConstructable> pair = (Entry<VertexLocation, ServerConstructable>)it.next();
	        VertexLocation vertLoc = pair.getKey();
	        HexLocation hex = vertLoc.getHexLoc();
	        String dir = vertLoc.getDir().toString();
	        JsonVertexLocation jEdgeLoc = new JsonVertexLocation(hex.getX(), hex.getY(), dir);
	        int owner = pair.getValue().getOwner();
	        settlements.add(new JsonVertexObject(owner, jEdgeLoc));
	    }
		if(settlements.size() > 0)
			return (JsonVertexObject[]) settlements.toArray();
		else
			return new JsonVertexObject[0];
	}
	
	private JsonVertexObject[] convertCities(HashMap<VertexLocation, ServerConstructable> buildings) {
		ArrayList<JsonVertexObject> cities = new ArrayList<JsonVertexObject>();
		Iterator<Entry<VertexLocation, ServerConstructable>> it = buildings.entrySet().iterator();
		
		while (it.hasNext()) {
	        Map.Entry<VertexLocation, ServerConstructable> pair = (Entry<VertexLocation, ServerConstructable>)it.next();
	        VertexLocation vertLoc = pair.getKey();
	        HexLocation hex = vertLoc.getHexLoc();
	        String dir = vertLoc.getDir().toString();
	        JsonVertexLocation jEdgeLoc = new JsonVertexLocation(hex.getX(), hex.getY(), dir);
	        int owner = pair.getValue().getOwner();
	        cities.add(new JsonVertexObject(owner, jEdgeLoc));
	    }
		if(cities.size() > 0)
			return (JsonVertexObject[]) cities.toArray();
		else
			return new JsonVertexObject[0];
	}
	
	private JsonHexLocation convertRobber(HexLocation loc) {
		return new JsonHexLocation(loc.getX(), loc.getY());
	}
	
	private JsonPlayer[] convertPlayerList(List<ServerPlayer> players) {
		JsonPlayer[] jsonPlayers = new JsonPlayer[4];
		
		int i = 0;
		for (ServerPlayer player : players) {
			jsonPlayers[i] = new JsonPlayer(player.getRemainingCities(),
									 player.getColor(),
									 player.hasDiscarded(),
									 player.getBank().getMonumentCount(),
									 player.getName(),
									 convertDevCardList(player.getBank().getNewDevCards()),
									 convertDevCardList(player.getBank().getOldDevCards()),
									 player.getPlayerIdx(),
									 player.getPlayerDevCard(),
									 player.getPlayerID(),
									 convertResourceList(player.getBank().getResCards()),
									 player.getRemainingRoads(),
									 player.getRemainingSettlements(),
									 player.getSoldierDevs(),
									 player.getVictoryPoints());
			++i;
		}
		return jsonPlayers;
	}
	
	private JsonDevCardList convertDevCardList(List<ServerDevCard> devCards) {
		int monopoly = 0;
		int monument = 0;
		int roadBuilding = 0;
		int soldier = 0;
		int yearOfPlenty = 0;
		
		for (ServerDevCard devCard : devCards) {
			switch (devCard.getType()) {
				case MONOPOLY:
					++monopoly;
				case MONUMENT:
					++monument;
				case ROAD_BUILD:
					++roadBuilding;
				case SOLDIER:
					++soldier;
				case YEAR_OF_PLENTY:
					++yearOfPlenty;
			}
		}
		return new JsonDevCardList(monopoly, monument, roadBuilding, soldier, yearOfPlenty);
	}
	
	private JsonTurnTracker convertTurnTracker(ServerTurnTracker turnTracker) {
		int whosTurn = turnTracker.getCurrentPlayerIndex();
		String state = turnTracker.getCurrentState().toString();
		int longestRoad = turnTracker.getLongestRoadPlayerIndex();
		int largestArmy = turnTracker.getLargestArmyPlayerIndex();
		return new JsonTurnTracker(whosTurn, state, longestRoad, largestArmy);
	}
	
	private JsonTradeOffer convertTradeOffer(ServerTradeOffer tradeOffer) {
		if(tradeOffer != null) {
			int sender = tradeOffer.getSender();
			int receiver = tradeOffer.getReceiver();
			HashMap<ResourceType, Integer> offer = tradeOffer.getResources();
			Iterator<Entry<ResourceType, Integer>> it = offer.entrySet().iterator();
			
			int brick = 0;
			int ore = 0; 
			int sheep = 0;
			int wheat = 0;
			int wood = 0;
		    while (it.hasNext()) {
		        Map.Entry<ResourceType, Integer> pair = (Entry<ResourceType, Integer>)it.next();
		        if(pair.getKey() == ResourceType.BRICK)
		        	brick = pair.getValue();
		        else if(pair.getKey() == ResourceType.ORE)
		        	ore = pair.getValue();
		        else if(pair.getKey() == ResourceType.SHEEP)
		        	sheep = pair.getValue();
		        else if(pair.getKey() == ResourceType.WHEAT)
		        	wheat = pair.getValue();
		        else if(pair.getKey() == ResourceType.WOOD)
		        	wood = pair.getValue();
		    }
			JsonResourceList jResList = new JsonResourceList(brick, ore, sheep, wheat, wood);
			
			return new JsonTradeOffer(sender, receiver, jResList);
		}
		return null;
	}
}
