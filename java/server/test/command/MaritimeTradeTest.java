package test.command;

import static org.junit.Assert.*;
import model.sgame.ServerGame;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.ResourceType;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.CreateGame_Params;
import shared.dto.FinishTurn_Params;
import shared.dto.JoinGame_Params;
import shared.dto.MaritimeTrade_Params;
import shared.dto.RollNumber_Params;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import facade.IModelFacade;
import facade.ModelFacade;

public class MaritimeTradeTest {
	
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

        assertNotNull (modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.South), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(1, -1), VertexDirection.SouthWest), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.NorthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(1, -1), VertexDirection.NorthEast), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.SouthWest), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(-2, 1), VertexDirection.SouthWest), true)));
        
        ServerGame game = modelFacade.getGame(0);
        
        // Roll 11's to get player[3] more than 4 wheat
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 11)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 11)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(1)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(2, 11)));

        // Attempt to do a 2 wheat for 1 sheep trade for player[3], should fail
        assertNull(modelFacade.maritimeTrade(0, new MaritimeTrade_Params(2, 2, "wheat", "sheep")));
        
        // Attempt to do a 3 wheat for 1 sheep trade for player[3], should fail
        assertNull(modelFacade.maritimeTrade(0, new MaritimeTrade_Params(2, 3, "wheat", "sheep")));
        
        // Attempt to do a 3 wheat for 1 sheep trade for player[3] and compare
        // before and after values
        int wheatBefore = game.getPlayerList().get(2).getBank().getResCards().get(ResourceType.WHEAT);
        int sheepBefore = game.getPlayerList().get(2).getBank().getResCards().get(ResourceType.SHEEP);
        assertTrue(wheatBefore >= 4);
        assertNotNull(modelFacade.maritimeTrade(0, new MaritimeTrade_Params(2, 4, "wheat", "sheep")));
        assertTrue(game.getPlayerList().get(2).getBank().getResCards().get(ResourceType.WHEAT) == wheatBefore - 4);
        assertTrue(game.getPlayerList().get(2).getBank().getResCards().get(ResourceType.SHEEP) == sheepBefore + 1);
        
        // Attempt to do a maritime trade that the player can't afford
        assertTrue(game.getPlayerList().get(2).getBank().getResCards().get(ResourceType.ORE) < 4);
        assertNull(modelFacade.maritimeTrade(0, new MaritimeTrade_Params(2, 4, "ore", "sheep")));
        
        // Progress to player[0]'s turn while rolling 6's to get wood
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(3, 6)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(3)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 6)));
        
        // Attempt a 2 wood for 1 ore trade and compare values
        int woodBefore = game.getPlayerList().get(0).getBank().getResCards().get(ResourceType.WOOD);
        assertNotNull(modelFacade.maritimeTrade(0, new MaritimeTrade_Params(0, 2, "wood", "ore")));
        assertTrue(game.getPlayerList().get(0).getBank().getResCards().get(ResourceType.WOOD) == woodBefore - 2);
        assertTrue(game.getPlayerList().get(0).getBank().getResCards().get(ResourceType.ORE) == 1);
        
        // Progress to player[3]'s turn while rolling 4's
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 4)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(1)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(2, 4)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(3, 4)));
        
        // Attempt a 3 wood for 1 ore and compare values
        woodBefore = game.getPlayerList().get(3).getBank().getResCards().get(ResourceType.WOOD);
        assertNotNull(modelFacade.maritimeTrade(0, new MaritimeTrade_Params(3, 3, "wood", "ore")));
        assertTrue(game.getPlayerList().get(3).getBank().getResCards().get(ResourceType.WOOD) == woodBefore - 3);
        assertTrue(game.getPlayerList().get(3).getBank().getResCards().get(ResourceType.ORE) == 1);
	}

}
