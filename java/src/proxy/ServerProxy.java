package proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.google.gson.Gson;

/**@author Chad
 * Jan 19, 2015
 * 
 * This class stores the server host and port and also contains methods to 
 * perform GET and POST on the Catan server. All other proxy classes inherit 
 * from this class. Both Get and PORT operations return a String representation
 * of the server's response, so the subclass which made the call must return
 * a class representing the response or return a boolean indicating the call
 * was successful, depending on the particular method call.
 */

public class ServerProxy {
	
	private static ServerProxy instance = null;
	
	/**The server's name or IP address*/
	private String host;
	
	/**The port that the server uses*/
	private String port;
	
	/**Contains the server's URL. This is determined by http://host:port*/
	private String url_prefix;
	
	// Used to determine which cookie was received from the server
	private final String userPrefix = "catan.user=";
	private final String gamePrefix = "catan.game=";
	
	/**Contains the decoded user cookie in json format*/
	private String userCookie;
	
	/**Contains the decoded user cookie in json format*/
	private String gameCookie;
	
	private Gson gson;

	/**Initializes the server's host and port that the proxy
	 * will communicate with.
	 * 
	 * @param host the name or IP address of the server
	 * @param port the port that the server will use
	 * */
	private ServerProxy() {
		gson = new Gson();
	}
	
	public static ServerProxy getInstance() {
		if (instance == null) {
			instance = new ServerProxy();
		}
		
		return instance;
	}
	
	public void initProxy(String host, String port) {
		this.host = host;
		this.port = port;
		this.url_prefix = "http://" + host + ":" + port;
		
		// Seperated from the constructor so the class can be used in testing
		this.userCookie = null;
		this.gameCookie = null;
	}
	
	/**Performs a GET to the specified url and returns the
	 * the http result.
	 * 
	 * @param methodPath the url of the desired object
	 * @return http response from server
	 * @throws ServerException thrown if there was any problem reading the 
	 * given URL or if the server returns a non-OK response code.
	 */
	public String doGet(String methodPath, Object getParams) throws ServerException {
		try {
			// Establish connection with server
			HttpURLConnection connection = getConnection(new URL(url_prefix + methodPath), "GET");
			
			if (getParams != null) {// If there are getParams
				// Write getParams to the connection as Json
				OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
				os.write(gson.toJson(getParams));//System.out.println(gson.toJson(postParams));
				os.close();
			}
			
			// Verify request was successful
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				
				// Read response from server
				return responseToString(connection.getInputStream());
				
			} else {
				
				// Throw ServerException with the server's response code
				String errorMessage = "doGet failed: Server response code %s,";
				throw new ServerException(String.format(errorMessage, connection.getResponseCode()));
			}
			
		} catch (IOException e) {
			throw new ServerException(String.format("doGet failed: %s", e.getMessage(), e));
		}
	}

	/**Performs a POST to the specified url with the given data. The data, 
	 * which should be a DTO with the data the correlates with the method 
	 * to be called, is converted to json and then sent to the server as 
	 * part of the POST requst body. The server's response is returned as a
	 * String.
	 * 
	 * @param methodPath the url of the desired server method
	 * @param postParams the object that contains the data to be posted
	 * @return String containing the server's response
	 * @throws ServerException thrown if there was any problem reading the 
	 * given URL, or writing the parameters to the server connection, or if the 
	 * server returns a non-OK response code. 
	 */
	public String doPost(String methodPath, Object postParams) throws ServerException {
		try {
			// Establish connection with server
			HttpURLConnection connection = getConnection(new URL(url_prefix + methodPath), "POST");
			
			// Write postParams to the connection as Json
			OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
			os.write(gson.toJson(postParams));//System.out.println(gson.toJson(postParams));
			os.close();
			
			// Verify request was successful
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				
				// Acquire cookie header if there is one
				decodeCookie(connection.getHeaderField("Set-cookie"));
				
				// Read response from server
				return responseToString(connection.getInputStream());
				
			} else {
				// Throw ServerException with the server's response code
				String errorMessage = "doPost failed: Server response code %s";
				throw new ServerException(String.format(errorMessage, connection.getResponseCode()));
			}
			
		} catch (IOException e) {
			throw new ServerException(String.format("doPost failed: %s", e.getMessage(), e));
		}
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getUrl_Prefix() {
		return url_prefix;
	}
	
	/** Writes the response stream from the server as a String.
	 * 
	 * @param is the Server's InputStream
	 * @return The String representation of the InputStream
	 * @throws IOException thrown if there was any problem reading the stream
	 */
	private String responseToString(InputStream is) throws IOException {
		
		// Convert server's inputStream to a String
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		
		br.close();
		
		return sb.toString();
	}
	
	/** Opens a HttpURLConnection with the server, sets the given request
	 * Method. If the method is a POST then it sets up the connection for
	 * output.
	 * 
	 * @param url the server url for the given method
	 * @param request whether the request is a POST or a GET
	 * @return the established Connection
	 * @throws IOException
	 * @throws ServerException 
	 */
	private HttpURLConnection getConnection(URL url, String requestMethod) throws ServerException {
		try {
			// Open URL connection with server
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestMethod);
			
			// Apply cookies to header
			connection.setRequestProperty("Cookie", encodeCookie());
			
			// Set connection up for output
			connection.setDoOutput(true);
			
			// Give server 10 seconds to respond
			connection.setReadTimeout(10*1000);
			
			connection.connect();
			return connection;
			
		} catch (UnsupportedEncodingException e) {
			String errorMessage = "doPost failed: Could not decode cookie";
			throw new ServerException(String.format(errorMessage, e));
		} catch (MalformedURLException e) {
			throw new ServerException(String.format(requestMethod + " failed: %s", e.getMessage(), e));
		} catch (IOException e) {
			throw new ServerException(String.format("doPost failed: %s", e.getMessage(), e));
		}
	}
	
	private void decodeCookie(String cookieHeader) throws UnsupportedEncodingException {
		if (cookieHeader != null) {
			
			// Remember the prefix to determine what kind of cookie it is
			String prefix = cookieHeader.substring(0, 11);
			
			// Strip the prefix and suffix
			String headerValue = cookieHeader.substring(11, cookieHeader.length()-8);
			
			// Decode cookie value
			String value = URLDecoder.decode(headerValue, "UTF-8");
			
			if (prefix.equals(userPrefix)) {
				userCookie = value;
			} else if (prefix.equals(gamePrefix)) {
				gameCookie = value;
			} else throw new UnsupportedEncodingException();
		}
	}
	
	private String encodeCookie() throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		if (userCookie != null) {
			sb.append(userPrefix + URLEncoder.encode(userCookie, "UTF-8"));
			if (gameCookie != null) {
				sb.append("; " + gamePrefix + URLEncoder.encode(gameCookie, "UTF-8"));
			}
		}
		return new String(sb);
	}
	
	public String getCookies() {
		return userCookie + "\n" + gameCookie;
	}
}
