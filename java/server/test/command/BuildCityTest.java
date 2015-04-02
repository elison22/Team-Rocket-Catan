package test.command;

import static org.junit.Assert.*;

import java.util.Map;

import model.sgame.ServerGame;
import model.sgame.ServerTurnState;
import model.splayer.ServerPlayer;

import org.junit.Before;
import org.junit.Test;

import facade.ModelFacade;
import shared.definitions.ResourceType;
import shared.dto.BuildCity_Params;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.CreateGame_Params;
import shared.dto.FinishTurn_Params;
import shared.dto.JoinGame_Params;
import shared.dto.RollNumber_Params;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class BuildCityTest {

	private ModelFacade modelFacade;
	
	@Before
	public void setUp() throws Exception {
		modelFacade = new ModelFacade();
	}
	
	@Test
	public void test() {
		// Create a game
        assertNotNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "RollNumberTest")));

        // Populate game with players
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "green"), "test1", 0));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "purple"), "test2", 1));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "yellow"), "test3", 2));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "puce"), "test4", 3));
        
		// Set up initial roads/settlements for all players
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.North), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(-1, 0), VertexDirection.NorthWest), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.NorthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(-1, 0), VertexDirection.East), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.South), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(-1, 0), VertexDirection.SouthWest), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(3, new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.SouthWest), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(3, new VertexLocation(new HexLocation(-1, 1), VertexDirection.SouthWest), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(3, new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.SouthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(3, new VertexLocation(new HexLocation(-1, 1), VertexDirection.East), true)));

        assertNotNull (modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.SouthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.East), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(0, -1), EdgeDirection.SouthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(0, -1), VertexDirection.East), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.NorthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(1, -1), VertexDirection.East), true)));
        
        ServerGame game = modelFacade.getGame(0);
        
        // Roll dice to set turn state
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 9)));
        
        //Try to build a city without enough resources
        assertNull(modelFacade.buildCity(0, new BuildCity_Params(0, new VertexLocation(new HexLocation(-1, 0), VertexDirection.NorthWest))));
        
        // Give player enough resources
        game.getPlayerList().get(0).getBank().getResCards().put(ResourceType.ORE, 5);
        game.getPlayerList().get(0).getBank().getResCards().put(ResourceType.WHEAT, 5);
        
        // Try to build a city where there is no settlement
        assertNull(modelFacade.buildCity(0, new BuildCity_Params(0, new VertexLocation(new HexLocation(-2, 2), VertexDirection.East))));
        
        // Upgrade a settlement into a city
        assertNotNull(modelFacade.buildCity(0, new BuildCity_Params(0, new VertexLocation(new HexLocation(-1, 0), VertexDirection.NorthWest))));
	}

}
