package test.proxy;

import static org.junit.Assert.*;

import org.junit.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import proxy.ProxyGames;
import proxy.ProxyUser;
import proxy.ServerException;
import proxy.ServerProxy;
import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;
import shared.dto.LoadGame_Params;
import shared.dto.Login_Params;
import shared.dto.SaveGame_Params;

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
	
	ProxyGames proxyGames = new ProxyGames();
	
	@Before
	public void initProxy() {
		ServerProxy.getInstance().initProxy("localhost","8081");
	}
	
	@Test
	public void listGamesTest() throws ServerException {
		assertTrue(proxyGames.list() != null);
	}
	
	@Test
	public void createGameTest() throws ServerException {
		CreateGame_Params createParams = new CreateGame_Params(false, false, false, "Sam");
		assertTrue(proxyGames.create(createParams) != null);
	}
	
	@Test
	public void joinGameTest() throws ServerException {
		Login_Params loginParams = new Login_Params("Test", "Test");
		ProxyUser pu = new ProxyUser();
		
		// If "Test"/"Test" is already registered, try logging in
		try {
			pu.register(loginParams);
		} catch (ServerException e) {
			assertTrue(pu.login(loginParams));
		}
		
		// Create a game to join
		CreateGame_Params createParams = new CreateGame_Params(false, false, false, "Test");
		String result = (String) proxyGames.create(createParams);
		assertTrue(result != null);
		
		// Get created game's id from json String
		JsonObject json = new Gson().fromJson(result, JsonObject.class);
		int gameId = json.get("id").getAsInt();
		
		// Attempt to join game
		JoinGame_Params joinParams = new JoinGame_Params(gameId, "blue");
		assertTrue(proxyGames.join(joinParams) != null);
	}
	
// TODO figure out how saving/loading is supposed to work
//	@Test
//	public void saveAndLoadTest() throws ServerException {
//		Login_Params loginParams = new Login_Params("Test", "Test");
//		ProxyUser pu = new ProxyUser();
//		
//		// If "Test"/"Test" is already registered, try logging in
//		try {
//			pu.register(loginParams);
//		} catch (ServerException e) {
//			assertTrue(pu.login(loginParams));
//		}
//		
//		// Create a game to join
//		CreateGame_Params createParams = new CreateGame_Params(false, false, false, "Test");
//		String result = (String) proxyGames.create(createParams);
//		assertTrue(result != null);
//		
//		// Get created game's id from json String
//		JsonObject json = new Gson().fromJson(result, JsonObject.class);
//		int gameId = json.get("id").getAsInt();
//		
//		// Attempt to join game
//		JoinGame_Params joinParams = new JoinGame_Params(gameId, "blue");
//		assertTrue(proxyGames.join(joinParams) != null);
//		
//		// Attempt to save the game
//		SaveGame_Params saveParams = new SaveGame_Params(gameId, "testsave");
//		assertTrue(proxyGames.save(saveParams) != null);
//		
//		// Attempt to load the saved game
//		LoadGame_Params loadParams = new LoadGame_Params("testsave");
//		assertTrue(proxyGames.load(loadParams) != null);
//	}
}
