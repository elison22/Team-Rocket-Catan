package proxy;

/**@author Chad
 * Jan 19, 2015
 * 
 * This class stores the server host and port and also contains methods to 
 * perform GET and POST on the Catan server. All other proxy classes inherit 
 * from this class.
 */

abstract class ServerProxy {
	
	/**The server's name or IP address*/
	private String host;
	
	/**The port that the server uses*/
	private String port;
	
	public ServerProxy() {}

	/**Initializes the server's host and port that the proxy
	 * will communicate with.
	 * 
	 * @param host the name or IP address of the server
	 * @param port the port that the server will use
	 * */
	public ServerProxy(String host, String port) {
		this.setHost(host);
		this.setPort(port);
	}
	
	/**Performs a GET to the specified url and returns the
	 * the http result.
	 * 
	 * @param url the url of the desired object
	 * @return http response from server
	 */
	public Object doGet(String url) {
		return null;
	}
	
	/**Performs a POST to the specified url with the given
	 * data and returns the html result.
	 * 
	 * @param url the url of the desired server method
	 * @param postParams the object that contains the data to be posted
	 * @return http reponse from server
	 */
	public Object doPost(String url, Object postParams) {
		return null;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}