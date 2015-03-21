package serializer;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import serializer.json.JsonClientModel;
import serializer.json.JsonMessageLine;
import serializer.json.JsonMessageList;
import serializer.json.JsonPlayer;
import serializer.json.JsonResourceList;
import shared.definitions.ResourceType;
import shared.dto.Game_DTO;
import model.schat.ServerMessage;
import model.sgame.ServerGame;

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
	
	private JsonClientModel convertServerModel(ServerGame serverModel) {
		return new JsonClientModel(convertResourceList(serverModel.getCardBank()),
								   convertChatList(serverModel.getChat()),
								   null,
								   null,
								   null,
								   null,
								   null,
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

}
