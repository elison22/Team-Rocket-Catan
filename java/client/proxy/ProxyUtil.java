package proxy;

/**@author Chad
 * Jan 19, 2015
 * 
 * This class handles contacting the server to change the server's Log Level.
 */
public class ProxyUtil {
	
	public ProxyUtil() {}
	
	/**(POST) Changes the server's log level to the given input.
	 * 
	 * @param changeLogLevelParams contains the logLevel (String). 
	 * Accepted values include: ALL, SEVERE, WARNING, INFO, CONFIG,
	 * FINE, FINER, FINEST, OFF.
	 * @return boolean indicating if the request was successful or not
	 * @throws ServerException is thrown either when the output from the server
	 * is different than what is expected by the client, or if there was a 
	 * problem connecting to the server.
	 */
	public boolean changeLogLevel(Object changeLogLevelParams) throws ServerException {
		
		// Get server response
		String result = ServerProxy.getInstance().doPost("/util/changeLogLevel", changeLogLevelParams);
		
		if (result.equals("Success\n"))
			return true;
		
		return false;
	}
}
