package serializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import serializer.json.*;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.dto.Game_DTO;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import model.sboard.ServerBoard;
import model.sboard.ServerHexTile;
import model.scards.ServerDevCard;
import model.schat.ServerMessage;
import model.sgame.ServerGame;
import model.splayer.ServerPlayer;

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
								   null,
								   convertDevCardList(serverModel.getDevBank()),
								   null,
								   -1,
								   -1);
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
						   null,
						   null,
						   null,
						   -1,
						   null);
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

}
