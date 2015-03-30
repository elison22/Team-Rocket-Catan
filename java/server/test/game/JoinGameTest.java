package test.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import shared.dto.CreateGame_Params;
import shared.dto.Game_DTO;
import shared.dto.JoinGame_Params;
import facade.IModelFacade;
import facade.ModelFacade;

public class JoinGameTest {
	
	IModelFacade facade;

	@Before
	public void setUp() throws Exception {
		facade = new ModelFacade();
	}
	
	@Test
	public void testJoinGame() {
		// Create a game to join
		assertNotNull(facade.createGame(new CreateGame_Params(false, false, false, "testJoin")));
		
		// Test being able to join the game
		assertTrue(facade.joinGame(new JoinGame_Params(0, "blue"), "TestUser", 15));
		
		// Test attempting to rejoin the game. It should allow them to re-join
		// a game, but the model shouldn't re-add them as a new player
		assertTrue(facade.joinGame(new JoinGame_Params(0, "white"), "TestUser", 15));
		
		// Make sure the model didn't re-add the player to the game
		Gson gson = new Gson();
		Game_DTO[] games = gson.fromJson(facade.listGames(), Game_DTO[].class);
		assertTrue(games[0].getPlayers()[0].getId() == 15);
		assertTrue(games[0].getPlayers()[1].getId() == -1);
		
		// Make sure the player was able to update their color
		assertTrue(games[0].getPlayers()[0].getColor().equalsIgnoreCase("white"));
		
		// Try to add another user with the same color
		assertFalse(facade.joinGame(new JoinGame_Params(0, "white"), "TestUser1", 16));
		
		// Try joining with an invalid color
		assertFalse(facade.joinGame(new JoinGame_Params(0, "invalid"), "TestUser1", 16));
		
		// Add more users
		assertTrue(facade.joinGame(new JoinGame_Params(0, "blue"), "TestUser1", 16));
		assertTrue(facade.joinGame(new JoinGame_Params(0, "green"), "TestUser2", 17));
		assertTrue(facade.joinGame(new JoinGame_Params(0, "brown"), "TestUser3", 18));
		
		// Try adding a fifth player
		assertFalse(facade.joinGame(new JoinGame_Params(0, "brown"), "TestUser4", 19));
		
		// Try re-joining with each player
		assertTrue(facade.joinGame(new JoinGame_Params(0, "brown"), "TestUser3", 18));
		assertTrue(facade.joinGame(new JoinGame_Params(0, "green"), "TestUser2", 17));
		assertTrue(facade.joinGame(new JoinGame_Params(0, "blue"), "TestUser1", 16));
		assertTrue(facade.joinGame(new JoinGame_Params(0, "white"), "TestUser", 15));
	}

}
