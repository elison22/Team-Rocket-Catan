package test.command;

import static org.junit.Assert.*;
import model.sgame.ServerGame;
import model.sgame.ServerTurnState;

import org.junit.Before;
import org.junit.Test;

import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import facade.IModelFacade;
import facade.ModelFacade;

public class BuildSettlementTest {
	
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
		
		// Try Building a Settlement before building a road (it should fail)
		assertNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthEast), true)));
		
		// Try Building a road
		assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.South), true)));
		
		// Try Building a Settlement
		assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthEast), true)));
	}

}
