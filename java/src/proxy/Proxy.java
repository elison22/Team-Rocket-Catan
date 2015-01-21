package proxy;

/**@author Chad
 * Jan 19, 2015
 * 
 * This class stores the server host and port and also contains methods to 
 * perform GET and POST on the Catan server. All other proxy classes inherit 
 * from this class.
 */

public class Proxy {
	
	/**The server's name or IP address*/
	protected final String HOST;
	
	/**The port that the server uses*/
	protected final String PORT;

	/**Initializes the server's host and port that the proxy
	 * will communicate with.
	 * 
	 * @param host the name or IP address of the server
	 * @param port the port that the server will use
	 * */
	public Proxy(String host, String port) {
		this.HOST = host;
		this.PORT = port;
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
	 * @param postData the object that contains the data to be posted
	 * @return
	 */
	public Object doPost(String url, Object postData) {
		return null;
	}
}
