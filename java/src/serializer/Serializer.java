package serializer;

import proxy.ProxyFacade;

import com.google.gson.*;

import model.game.GameModel;

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
	 * Proxy through which the serializer contacts the server 
	 */
	private ProxyFacade proxy;
	
	/**
	 * Instance of the game that holds the objects the Serializer handles
	 */
	private GameModel catanInstance;
	
	/**
	 * Constructor
	 * @param proxy
	 */
	public Serializer(ProxyFacade proxy)
	{
		
	}
	
	/**
	 * Converts Json string into java object(s)
	 * @param json String received from the server that needs to be made into Java object(s)
	 */
	public void deSerializeFromServer(String json)
	{
		
	}
	
	/**
	 * Serializes object to send to server
	 * @param object
	 */
	public void sendToServer(Object object)
	{
		
	}

}
