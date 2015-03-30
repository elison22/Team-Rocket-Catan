package test.proxy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import proxy.ProxyFacade;
import proxy.ServerException;
import shared.dto.AddAI_Params;
import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;
import shared.dto.Login_Params;

public class ProxyGameTest {
	
	ProxyFacade facade; 
	
	@Before
	public void initProxy() throws ServerException {
		facade = new ProxyFacade("localhost", "8081");
		
		// Attempt to login/register
		try {
			facade.login(new Login_Params("Test", "Test"));
		} catch (ServerException e) {
			facade.register(new Login_Params("Test", "Test"));
		}
		
		// Create a game and get its id
		String result = facade.create(new CreateGame_Params(false, false, false, "Test"));
		JsonObject json = new Gson().fromJson(result, JsonObject.class);
		int gameId = json.get("id").getAsInt();
		
		// Join created game
		facade.join(new JoinGame_Params(gameId, "blue"));
	}
	
	// For each of the following tests, an assertion is made to determine
	// if the returned object from the server is NOT null. Due to the way
	// the proxy is set up, the only way that the returned object isn't
	// null is if the server returned a 200 response code. 
	//
	// The one exception to this is if the server doesn't return anything for a 
	// given operation (like login). In this case the proxy returns true 
	// (for 200) or false.

	@Test
	public void testGetModel() throws ServerException {
		assertNotNull(facade.model(-1));
	}
	
	@Test
	public void testReset() throws ServerException {
		assertNotNull(facade.reset());
	}
	
	@Test
	public void testGetCommands() throws ServerException {
		assertNull(facade.commandsGET());
	}
	
	@Test
	public void testAddAI() throws ServerException {
		assertTrue(facade.addAI(new AddAI_Params("LARGEST_ARMY")));
	}
	
	@Test
	public void testListAI() throws ServerException {
		assertNotNull(facade.listAI());
	}
}




