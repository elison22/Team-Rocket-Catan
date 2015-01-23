package proxy;

import com.google.gson.JsonObject;

/**@author Chad
 * Jan 19, 2015
 * 
 * This class handles contacting the server to change the server's Log Level.
 */
public class ProxyUtil extends ServerProxy {

	public ProxyUtil() {}
	
	/**Initializes the server's host and port that the proxy
	 * will operate with.
	 * 
	 * @param host the name or IP address of the server
	 * @param port the port that the server will use
	 * */
	public ProxyUtil(String host, String port) {
		super(host, port);
	}
	
	/**(POST) Changes the server's log level.
	 * 
	 * @param changeLogLevelParams contains the logLevel (String). 
	 * Accepted values include: ALL, SEVERE, WARNING, INFO, CONFIG,
	 * FINE, FINER, FINEST, OFF.
	 * @return server's http response
	 */
	public Object changeLogLevel(JsonObject changeLogLevelParams) {
		return null;
	}

}
