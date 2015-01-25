package proxy;

/**@author Chad
 * Jan 19, 2015
 * 
 * This class handles contacting the server to change the server's Log Level.
 */
public class ProxyUtil extends ServerProxy {
	
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
	 * @return boolean indicating if the request was successful or not
	 * @throws ServerException is thrown either when the output from the server
	 * is different than what is expected by the client, or if there was a 
	 * problem connecting to the server.
	 */
	public boolean changeLogLevel(Object changeLogLevelParams) throws ServerException {
		
		// Get server response
		String result = doPost("/util/changeLogLevel", changeLogLevelParams);
		
		if (result.equals("Success\n"))
			return true;
		
		return false;
	}
	
//	// FOR TESTING:
//	
//	public class ChangeLogLevelTest {
//		String logLevel;
//
//		public String getLogLevel() {
//			return logLevel;
//		}
//
//		public void setLogLevel(String logLevel) {
//			this.logLevel = logLevel;
//		}
//	}
//	
//	public ChangeLogLevelTest getCLLT() {
//		return new ChangeLogLevelTest();
//	}
//
//	public static void main(String args[]) {
//		ProxyUtil pu = new ProxyUtil("localhost", "8081");
//		ChangeLogLevelTest changeLogLevelParams = pu.getCLLT();
//		changeLogLevelParams.setLogLevel("SEVERE");
//		try {
//			System.out.println(pu.changeLogLevel(changeLogLevelParams));
//			
//			changeLogLevelParams.setLogLevel("INFO");
//			System.out.println(pu.changeLogLevel(changeLogLevelParams));
//			
//			changeLogLevelParams.setLogLevel("OFF");
//			System.out.println(pu.changeLogLevel(changeLogLevelParams));
//		} catch (ServerException e) {
//			System.out.println(e.getMessage());
//		}
//	}
}
