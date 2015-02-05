package serverpoller;

import facade.ClientFacade;
import proxy.*;

/**
 * This class checks with server every few seconds to see if the client model needs to be updated
 * @author Owner
 *
 */
public class ServerPoller implements Runnable{
	
	/**
	 * Proxy through which the poller contacts the server
	 */
	private ProxyFacade proxyFacade;
	
	/**
	 * Serializer that handles the Json received from the server
	 */
	private ClientFacade facade;
	
	private long lastPollTime;
	
	/**
	 * Constructor
	 * @param serverProxy
	 */
	public ServerPoller(ProxyFacade proxyFacade, ClientFacade facade)
	{
		this.facade = facade;
		this.proxyFacade = proxyFacade;
		lastPollTime = System.currentTimeMillis();
	}
	
	/**
	 * Checks the version of the current client against the version of the server 
	 * @param currentVersion Current version the client is running
	 */
	public void pollServer(int currentVersion)
	{
		try 
		{
			String result = proxyFacade.model(currentVersion);
			if(!result.equals("true"))
				facade.updateGameModel(result);
		} 
		catch (ServerException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		if(System.currentTimeMillis() - lastPollTime > 3000)
		{
			lastPollTime = System.currentTimeMillis();
			pollServer(facade.getVersionNumber());
		}
	}
}
