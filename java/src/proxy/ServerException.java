package proxy;

/**@author Chad
 * Jan 20, 2015
 *
 *This Exception should be thrown if there is any difficulty communicating with 
 *the server. Specifically if the server's output is different than what is 
 *expected, or if the proxy isn't able to connect to the server, or if there
 *was a problem decoding cookies.
 */
@SuppressWarnings("serial")
public class ServerException extends Exception {

	public ServerException() {
		// TODO Auto-generated constructor stub
	}

	public ServerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ServerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServerException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
