package proxy;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**@author Chad
 * Jan 19, 2015
 * 
 * Handles all login related operations, including creating a new user.
 */
public class ProxyUser extends ServerProxy {
	
	/**Initializes the server's host and port that the proxy
	 * will operate with.
	 * 
	 * @param host the name or IP address of the server
	 * @param port the port that the server will use
	 * */
	public ProxyUser(String host, String port) {
		super(host, port);
	}
	
	/**POSTs login info to the server, which will then attempt
	 * to log the user in. 
	 * 
	 * @param loginParams should contain a username (String) and
	 * a password (String).
	 * @return http response from server
	 * @throws ServerException is thrown either when the output from the server
	 * is different than what is expected by the client, or if there was a 
	 * problem connecting to the server.
	 */
	public Object login(JsonObject loginParams) throws ServerException {
		return null;
	}
	
	/**POSTs new user info to the server which will attempt
	 * to create a new user account if the username is not 
	 * already in use.
	 * 
	 * @param registerParams should contain a username (String),
	 * and a password (String).
	 * @return http response from server
	 * @throws ServerException is thrown either when the output from the server
	 * is different than what is expected by the client, or if there was a 
	 * problem connecting to the server.
	 */
	public Object register(JsonObject registerParams) throws ServerException {
		return null;
	}
	
//	public class jsonTest {
//		String username = "deuce";
//		String password = "deuce";
//	}
//	
//	public jsonTest getTest() {
//		return new jsonTest();
//	}
//
//	public static void main(String args[]) {
//		ProxyUser pu = new ProxyUser("localhost", "8081");
//		Gson gson = new Gson();
//		try {
//			pu.doPost("/user/regist", pu.getTest());
//		} catch (ServerException e) {
//			e.printStackTrace();
//		}
//	}
}


