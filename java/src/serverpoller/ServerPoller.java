package serverpoller;

import model.board.*;
import proxy.*;
import serializer.*;
/**
 * This class checks with server every few seconds to see if the client model needs to be updated
 * @author Owner
 *
 */
public class ServerPoller {
	
	/**
	 * Proxy through which the poller contacts the server
	 */
	private ProxyFacade serverProxy;
	
	/**
	 * Serializer that handles the Json received from the server
	 */
	private Serializer serializer;
	
	/**
	 * Constructor
	 * @param serverProxy
	 */
	public ServerPoller(ProxyFacade serverProxy)
	{
		
	}
	
	/**
	 * Checks the version of the current client against the version of the server 
	 * @param currentVersion Current version the client is running
	 */
	public void pollServer(String currentVersion)
	{
		
	}
	
	/**
	 * Receives new data from the server for updating the client model and passes it to the Serializer
	 */
	public void getNewVersionFromServer()
	{
		
	}
	
	/**
	 * Tracks the time since pollServer() was last called
	 */
	public void timer()
	{
		
	}
}
