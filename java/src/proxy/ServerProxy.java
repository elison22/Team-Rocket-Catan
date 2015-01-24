package proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

abstract class ServerProxy {
	
	/**The server's name or IP address*/
	private final String host;
	
	/**The port that the server uses*/
	private final String port;
	
	/**Contains the server's URL. This is determined by http://host:port*/
	private final String url_prefix;
	
	private Gson gson;

	/**Initializes the server's host and port that the proxy
	 * will communicate with.
	 * 
	 * @param host the name or IP address of the server
	 * @param port the port that the server will use
	 * */
	public ServerProxy(String host, String port) {
		this.host = host;
		this.port = port;
		this.url_prefix = "http://" + host + ":" + port;
		
		gson = new Gson();
	}
	
	/**Performs a GET to the specified url and returns the
	 * the http result.
	 * 
	 * @param methodPath the url of the desired object
	 * @return http response from server
	 */
	public Object doGet(String methodPath) {
		return null;
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
	 * @throws ServerException thrown if there was any problem reading the give 
	 * URL, or writing the parameters to the server connection, or if the 
	 * server returns a non-OK response code. 
	 */
	public String doPost(String methodPath, Object postParams) throws ServerException {
		//TODO add comments, finish implementation
		try {
			// Open connection with server
			URL url = new URL(url_prefix + methodPath);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			// Write postParams to the connection as Json
			OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
			os.write(gson.toJson(postParams));
			os.close();
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Read response from server
				return responseToString(connection.getInputStream());
				
			} else {
				// Throw ServerException with the server's response code
				throw new ServerException(String.format("doPost failed: Server response code %s,", 
														connection.getResponseCode()));
			}
			
		} catch (MalformedURLException e) {
			throw new ServerException(String.format("doPost failed: %s", e.getMessage(), e));
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
	
	private String responseToString(InputStream is) throws IOException {
		// Convert server's inputStream to a String
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		return sb.toString();
	}
}
