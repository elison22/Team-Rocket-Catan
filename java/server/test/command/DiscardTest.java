package test.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import model.sgame.ServerGame;
import model.sgame.ServerTurnState;
import model.splayer.ServerPlayer;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.ResourceType;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.CreateGame_Params;
import shared.dto.DiscardCards_Params;
import shared.dto.FinishTurn_Params;
import shared.dto.JoinGame_Params;
import shared.dto.RollNumber_Params;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import facade.IModelFacade;
import facade.ModelFacade;

public class DiscardTest {
	
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
        
		// Set up inital raods/settlements for all players
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
        
        // Roll dice to give all players over 7 resources
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 9)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 9)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(1)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(2, 9)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(3, 9)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(3)));
        // Green: 4 Ore, Purple: 4 Ore, Yellow: 4 Sheep 4 Ore, Puce: 8 Sheep
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 10)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 10)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(1)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(2, 10)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(3, 10)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(3)));
        // Green: 4 Sheep 4 Ore, Purple: 4 Sheep 4 Ore, Yellow: 8 Sheep 4 Ore, Puce: 8 Sheep
        
        // Confirm that the players have the above resources
        for (ServerPlayer player : game.getPlayerList()) {
        	HashMap<ResourceType, Integer> resources = player.getBank().getResCards();
        	
        	switch (player.getPlayerIdx()) {
        		case 0:
        			assertTrue(resources.get(ResourceType.SHEEP) == 4);
        			assertTrue(resources.get(ResourceType.ORE) == 4);
        			break;
        		case 1:
        			assertTrue(resources.get(ResourceType.SHEEP) == 4);
        			assertTrue(resources.get(ResourceType.ORE) == 4);
        			break;
        		case 2:
        			assertTrue(resources.get(ResourceType.SHEEP) == 8);
        			assertTrue(resources.get(ResourceType.ORE) == 4);
        			break;
        		case 3:
        			assertTrue(resources.get(ResourceType.SHEEP) == 8);
        			break;
        		default:
        			assertTrue(false);
        		
        	}
        }
        
        // Roll a 7 and make sure the game enters the discarding state
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 7)));
        assertTrue(game.getTurnTracker().getCurrentState() == ServerTurnState.Discarding);
        
        // Discard for player[0]
        HashMap<ResourceType, Integer> resources = new HashMap<ResourceType, Integer>();
        resources.put(ResourceType.BRICK, 0);
        resources.put(ResourceType.ORE, 4);
        resources.put(ResourceType.SHEEP, 0);
        resources.put(ResourceType.WHEAT, 0);
        resources.put(ResourceType.WOOD, 0);
        assertNotNull(modelFacade.discardCards(0, new DiscardCards_Params(0, resources)));
        
        // Make sure player[0]'s resources were properly decremented and the 
        // current state is still discarding
        ServerPlayer player = game.getPlayerList().get(0);
        assertTrue(player.getBank().getResCards().get(ResourceType.ORE) == 0);
        assertTrue(player.getBank().getResCards().get(ResourceType.SHEEP) == 4);
        
        // Make sure player[0] can no longer discard
        resources.put(ResourceType.ORE, 0);
        resources.put(ResourceType.SHEEP, 2);
        assertNull(modelFacade.discardCards(0, new DiscardCards_Params(0, resources)));
        assertTrue(player.getBank().getResCards().get(ResourceType.SHEEP) == 4);
        
        // Discard for other players and make sure that current turn state is
        // discarding until all players have discarded
        resources.put(ResourceType.SHEEP, 4);
        assertNotNull(modelFacade.discardCards(0, new DiscardCards_Params(1, resources)));
        assertTrue(game.getTurnTracker().getCurrentState() == ServerTurnState.Discarding);
        
        assertNotNull(modelFacade.discardCards(0, new DiscardCards_Params(3, resources)));
        assertTrue(game.getTurnTracker().getCurrentState() == ServerTurnState.Discarding);
        
        resources.put(ResourceType.SHEEP, 6);
        assertNotNull(modelFacade.discardCards(0, new DiscardCards_Params(2, resources)));
        
        // Make sure after all players have discarded the game enters the
        // robbing state
        assertTrue(game.getTurnTracker().getCurrentState() == ServerTurnState.Robbing);
	}

}
