package serializer;

import com.google.gson.Gson;

import serializer.json.JsonPlayer;
import shared.dto.Game_DTO;
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
	 * @param gameModel The model of the game to be serialized.
	 * @return The json format of the model as a String.
	 */
	public String serializeGameModel(ServerGame gameModel) {
		return null;
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
		return null;
	}
	
	/**Serializes the player data to be transmitted as a cookie.
	 * 
	 * @param player The player whose info needs to be serialized.
	 * @return The String containing the json data.
	 */
	public String serializeCookie(JsonPlayer player) {
		return null;
	}

}
