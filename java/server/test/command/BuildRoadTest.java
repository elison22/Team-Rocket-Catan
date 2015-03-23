package test.command;

import static org.junit.Assert.*;
import model.sboard.ServerConstructable;
import model.sgame.ServerGame;
import model.sgame.ServerTurnState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.dto.BuildRoad_Params;
import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import facade.IModelFacade;
import facade.ModelFacade;

public class BuildRoadTest {
	
	IModelFacade modelFacade;

	@Before
	public void setUp() throws Exception {
		modelFacade = new ModelFacade();
	}

	@Test
	public void test() {
		
		// Create a game
		assertNotNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "BuildRoadTest")));
		
		// Populate game with players
		assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "white"), "test1", 0));
		assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "green"), "test2", 1));
		assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "puce"), "test3", 2));
		assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "blue"), "test4", 3));
		
		ServerGame game = modelFacade.getGame(0);
		
		// Make sure the model is in the right turn state
		assertTrue(game.getTurnState() == ServerTurnState.FirstRound);
		
		// Try Building a road
		assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.South), true)));
		
		// Check that there is a road on the southern edge of hex 0,0 that belongs to player 0
		ServerConstructable road = game.getMap().getRoadPieces().get(new EdgeLocation(new HexLocation(0, 0), EdgeDirection.South).getNormalizedLocation());
		assertNotNull(road);
		assertTrue(road.getOwner() == 0);
	}

}
