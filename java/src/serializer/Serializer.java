package serializer;

import proxy.ProxyFacade;
import serializer.json.ClientModel;

import com.google.gson.*;

import model.game.*;

/**
 * This class handles the translation of Java objects to and from Json, which is used for data transfer between server and client
 * @author Owner
 *
 */
public class Serializer {
	
	/**
	 * Automates translation of Json
	 */
	private Gson gson;	
	/**
	 * Instance of the game that holds the objects the Serializer handles
	 */
	private ClientModel catanInstance;
	
	/**
	 * Constructor
	 * @param proxy
	 */
	public Serializer()
	{
		gson = new Gson();
	}
	
	/**
	 * Converts Json string into java object(s)
	 * @param json String received from the server that needs to be made into Java object(s)
	 */
	public ClientModel deSerializeFromServer(String json)
	{
		return gson.fromJson(json, ClientModel.class);
	}
	
	/**
	 * Serializes object to send to server
	 * @param object
	 */
	public void sendToServer(Object object)
	{
		
	}

}
