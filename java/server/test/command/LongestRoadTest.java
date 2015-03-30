package test.command;

import static org.junit.Assert.*;

import java.util.HashMap;

import model.sgame.ServerGame;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.ResourceType;
import shared.dto.AcceptTrade_Params;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.CreateGame_Params;
import shared.dto.FinishTurn_Params;
import shared.dto.JoinGame_Params;
import shared.dto.OfferTrade_Params;
import shared.dto.RollNumber_Params;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import facade.IModelFacade;
import facade.ModelFacade;

public class LongestRoadTest {
	
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
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "blue"), "test1", 0));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "green"), "test2", 1));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "red"), "test3", 2));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "purple"), "test4", 3));
        
		// Set up initial roads/settlements for all players
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.North), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(-1, 0), VertexDirection.NorthWest), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.SouthWest), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(-1, 1), VertexDirection.SouthWest), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.South), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthWest), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(3, new EdgeLocation(new HexLocation(0, 1), EdgeDirection.South), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(3, new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(3, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.NorthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(3, new VertexLocation(new HexLocation(1, 1), VertexDirection.East), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.South), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(1, -1), VertexDirection.SouthWest), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.NorthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(1, -1), VertexDirection.NorthEast), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.SouthWest), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(-2, 1), VertexDirection.SouthWest), true)));
        
        ServerGame game = modelFacade.getGame(0);
        
        // Roll 8's to get 5 brick for player[0]
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(1)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(2, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(3, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(3)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        
        // Roll 6's to get 5 wood for player[8]
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 6)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(1)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(2, 6)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(3, 6)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(3)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 6)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 6)));
        
        // Retrieve player[0]'s bricks
        HashMap<ResourceType, Integer> tradeOffer = new HashMap<ResourceType, Integer>();
        tradeOffer.put(ResourceType.BRICK, -5);
        tradeOffer.put(ResourceType.ORE, 1);
        tradeOffer.put(ResourceType.SHEEP, 0);
        tradeOffer.put(ResourceType.WHEAT, 0);
        tradeOffer.put(ResourceType.WOOD, 0);
        assertNotNull(modelFacade.offerTrade(0, new OfferTrade_Params(1, tradeOffer, 0)));
        assertNotNull(modelFacade.acceptTrade(0, new AcceptTrade_Params(0, true)));
        
        // Build 4 roads to get longest road
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(2, -2), EdgeDirection.South), false)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(2, -2), EdgeDirection.SouthEast), false)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(2, -2), EdgeDirection.NorthEast), false)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(2, -2), EdgeDirection.North), false)));
        
        // Check that player[1] has longest road
        assertTrue(game.getTurnTracker().getLongestRoadPlayerIndex() == 1);
        
        // Check that player[1] was rewarded victory points
        assertTrue(game.getPlayerList().get(1).getVictoryPoints() == 4);
        
        // Roll a bunch of 8's to get 6 brick and 6 wood for player[0]
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(1)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(2, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(3, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(3)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(1)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(2, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(3, 8)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(3)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 6)));
        
        // Build 4 roads to tie for longest road
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.SouthEast), false)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.NorthEast), false)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.North), false)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.NorthWest), false)));
        
        // Check that player[0] doesn't have longest road yet
        assertFalse(game.getTurnTracker().getLongestRoadPlayerIndex() == 0);
        
        // Build last road to get longest road
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.SouthWest), false)));
        
        // Check that player[0] has longest road
        assertTrue(game.getTurnTracker().getLongestRoadPlayerIndex() == 0);
        
        // Check that player[0] was rewarded victory points
        assertTrue(game.getPlayerList().get(0).getVictoryPoints() == 4);
        
        // Check that player[1] lost the longest road points
        assertTrue(game.getPlayerList().get(1).getVictoryPoints() == 2);
        
        
	}

}
