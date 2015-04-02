package test.proxy;

import static org.junit.Assert.*;

import org.junit.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import proxy.ProxyFacade;
import proxy.ServerException;
import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;
import shared.dto.Login_Params;

/**
 * @author Chad
 * Jan 26, 2015
 * 
 * This class tests all of the methods for the ProxyGames class. In each 
 * instance only connectivity is tested (i.e. the server responds with 
 * something other than an error). The data sent by the server is assumed 
 * to be correct. 
 * 
 * SERVER MUST BE RUNNING BEFORE TESTING
 */
public class ProxyGamesTest {
	
	ProxyFacade facade;
	
	@Before
	public void initProxy() {
		facade = new ProxyFacade("localhost", "8081");
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
	public void testListGames() throws ServerException {
		assertNotNull(facade.list());
	}
	
	/*@Test
	public void testCreateGame() throws ServerException {
				
		CreateGame_Params createParams = new CreateGame_Params(false, false, false, "Sam");
		assertNotNull(facade.create(createParams));
	}*/
	
	@Test
	public void testJoinGame() throws ServerException {
		Login_Params loginParams = new Login_Params("Test", "Test");
		
		// If "Test"/"Test" is already registered, try logging in
		try {
			facade.register(loginParams);
		} catch (ServerException e) {
			assertTrue(facade.login(loginParams));
		}
		
		// Create a game to join
		CreateGame_Params createParams = new CreateGame_Params(false, false, false, "Test");
		String result = (String) facade.create(createParams);
		assertNotNull(result);
		
		// Get created game's id from json String
		JsonObject json = new Gson().fromJson(result, JsonObject.class);
		int gameId = json.get("id").getAsInt();
		
		// Attempt to join game
		JoinGame_Params joinParams = new JoinGame_Params(gameId, "blue");
		assertNotNull(facade.join(joinParams));
	}
	
// TODO figure out how saving/loading is supposed to work
//	@Test
//	public void saveAndLoadTest() throws ServerException {
//		Login_Params loginParams = new Login_Params("Sam", "sam");
//		ProxyUser pu = new ProxyUser();
//		
//		// If "Test"/"Test" is already registered, try logging in
//		try {
//			pu.register(loginParams);
//		} catch (ServerException e) {
//			assertTrue(pu.login(loginParams));
//		}
//		
//		// Attempt to join game
//		JoinGame_Params joinParams = new JoinGame_Params(0, "orange");
//		assertTrue(facade.join(joinParams) != null);
//		
//		// Attempt to save the game
//		SaveGame_Params saveParams = new SaveGame_Params(0, "testsave");
//		assertTrue(facade.save(saveParams) != null);
//		
//		// Attempt to load the saved game
//		LoadGame_Params loadParams = new LoadGame_Params("testsave");
//		assertTrue(facade.load(loadParams) != null);
//	}
}
