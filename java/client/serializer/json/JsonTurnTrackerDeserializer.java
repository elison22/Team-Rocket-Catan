package serializer.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class JsonTurnTrackerDeserializer implements JsonDeserializer<JsonTurnTracker> {

	@Override
	public JsonTurnTracker deserialize(JsonElement arg0, Type typeOfT, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject Json = (JsonObject)arg0;
		JsonTurnTracker jsonTurnTracker;
		int currentTurn = Json.get("currentTurn").getAsInt();
		String status = Json.get("status").getAsString();
		if(Json.has("longestRoad") && Json.has("largestArmy"))
		{
			int longestRoad = Json.get("longestRoad").getAsInt();
			int largestArmy = Json.get("largestArmy").getAsInt();
			jsonTurnTracker = new JsonTurnTracker(currentTurn, status, longestRoad, largestArmy);
		}
		else
		{
			jsonTurnTracker = new JsonTurnTracker(currentTurn, status);
			if(Json.has("longestRoad"))
			{
				int longestRoad = Json.get("longestRoad").getAsInt();
				jsonTurnTracker.setLongestRoad(longestRoad);
			}
			else jsonTurnTracker.setLongestRoad(-1);
			if(Json.has("largestArmy"))
			{
				int largestArmy = Json.get("largestArmy").getAsInt();
				jsonTurnTracker.setLargestArmy(largestArmy);
			}
			else jsonTurnTracker.setLargestArmy(-1);
		}
		return jsonTurnTracker;
	}

}
