package test.proxy;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import proxy.ProxyFacade;
import proxy.ServerException;
import shared.dto.Login_Params;

/**@author Chad
 * Jan 27, 2015
 * 
 * Tests member methods of ProxyUser class. The test case is to generate a 
 * random string to use as a username/password and then register the user and
 * attempt to sign in. The reason a random string is used is so the test can
 * be repeated without clearing the server cache, otherwise registering would
 * fail everytime after the first.
 */
public class ProxyUserTest {
	ProxyFacade facade; 
	
	@Before
	public void initProxy() {
		facade = new ProxyFacade("localhost", "8081");
	}
	
	// If the operation succeeded (server returns 200 response code) then the
	// Proxy will return true, so these tests assert that the result from the
	// proxy is true.

	@Test
	public void testRegisterAndLogin() throws ServerException {
		// generate random string to use as username and password
		String randString = randomString(7);
		
		// login and register use same params
		Login_Params loginParams = new Login_Params(randString, randString);
		
		// Register a user, then log them in
		assertTrue(facade.register(loginParams));
		assertTrue(facade.login(loginParams));
	}
	
	// Used by helper method
	final String alphanum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZX";
	Random rnd = new Random();
	
	// Helper method: Generate random alphanumeric String of given length
	private String randomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for(int i = 0; i < length; i++) 
			sb.append(alphanum.charAt(rnd.nextInt(alphanum.length())));
		return sb.toString();
	}

}
