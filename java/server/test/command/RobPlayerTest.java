package test.command;

import static org.junit.Assert.*;

import java.util.Map;

import model.sgame.ServerGame;
import model.sgame.ServerTurnState;
import model.splayer.ServerPlayer;

import org.junit.Before;
import org.junit.Test;

import facade.IModelFacade;
import facade.ModelFacade;
import shared.definitions.ResourceType;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.CreateGame_Params;
import shared.dto.FinishTurn_Params;
import shared.dto.JoinGame_Params;
import shared.dto.RobPlayer_Params;
import shared.dto.RollNumber_Params;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class RobPlayerTest {
	
	IModelFacade modelFacade;

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
        
        // Reset all player's resources to zero
        for (ServerPlayer player : game.getPlayerList()) {
        	for (Map.Entry<ResourceType, Integer> entry : player.getBank().getResCards().entrySet()) {
        		player.getBank().getResCards().put(entry.getKey(), 0);
        	}
        }
        
        // Roll a 6 to give player[3] 1 wood then end the turn
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 6)));
        assertTrue(game.getPlayerList().get(3).getBank().getResCards().get(ResourceType.WOOD) == 1);
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        
        // Roll a 7 and check that the game is in the robbing state
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 7)));
        assertTrue(game.getTurnState() == ServerTurnState.Robbing);
        
        // Attempt to rob player[3]
        assertNotNull(modelFacade.robPlayer(0, new RobPlayer_Params(1, 3, new HexLocation(-2, 0))));
        
        // Make sure the 1 wood was taken and given to player 1
        assertTrue(game.getPlayerList().get(1).getBank().getResCards().get(ResourceType.WOOD) == 1);
        assertTrue(game.getPlayerList().get(3).getBank().getResCards().get(ResourceType.WOOD) == 0);
        
        // Make sure the current game state is set back to Playing
        assertTrue(game.getTurnState() == ServerTurnState.Playing);
        
        // End turn and roll another seven
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(1)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(2, 7)));
        assertTrue(game.getTurnState() == ServerTurnState.Robbing);
        
        // Attempt to steal from player[0] who has no resources
        assertTrue(game.getPlayerList().get(0).getBank().getResCount() == 0);
        assertNull(modelFacade.robPlayer(0, new RobPlayer_Params(2, 0, new HexLocation(2, -2))));
	}

}
