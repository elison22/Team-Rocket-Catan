package test.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import model.sgame.ServerGame;
import model.splayer.ServerPlayer;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.ResourceType;
import shared.dto.*;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import facade.IModelFacade;
import facade.ModelFacade;

public class OfferAndAcceptTradeTest {
	
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
        
        // Roll dice to get 2 wood for player[1] and finish turn
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 3)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        
        // Roll dice to get 1 brick for player[1] and 1 wood for player[3]
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 4)));
        
        // Offer a trade that player[3] can't accept
        HashMap<ResourceType, Integer> tradeOffer = new HashMap<ResourceType, Integer>();
        tradeOffer.put(ResourceType.BRICK, 1);
        tradeOffer.put(ResourceType.ORE, 0);
        tradeOffer.put(ResourceType.SHEEP, 0);
        tradeOffer.put(ResourceType.WHEAT, 0);
        tradeOffer.put(ResourceType.WOOD, -1);
        assertNotNull(modelFacade.offerTrade(0, new OfferTrade_Params(1, tradeOffer, 3)));
        
        // Check that the trade got offered
        assertNotNull(game.getTradeOffer());
        
        // Attempt to accept the trade
        assertNull(modelFacade.acceptTrade(0, new AcceptTrade_Params(3, true)));
        
        // Reject the trade
        assertNotNull(modelFacade.acceptTrade(0, new AcceptTrade_Params(3, false)));
        
        // Make sure the trade is no longer being offered
        assertNull(game.getTradeOffer());
        
        // Now offer a trade that player[3] can accept
        tradeOffer.put(ResourceType.BRICK, -1); // Resources for player[1] to give
        tradeOffer.put(ResourceType.ORE, 0);
        tradeOffer.put(ResourceType.SHEEP, 0);
        tradeOffer.put(ResourceType.WHEAT, 0);
        tradeOffer.put(ResourceType.WOOD, 1); // Resource for player[1] to receive
        assertNotNull(modelFacade.offerTrade(0, new OfferTrade_Params(1, tradeOffer, 3)));
        
        // Check that the trade got offered
        assertNotNull(game.getTradeOffer());
        
        // First reject the trade
        assertNotNull(modelFacade.acceptTrade(0, new AcceptTrade_Params(3, false)));
        
        // Make sure the trade is no longer being offered
        assertNull(game.getTradeOffer());
        
        // Offer the trade again
        assertNotNull(modelFacade.offerTrade(0, new OfferTrade_Params(1, tradeOffer, 3)));
        
        // Remember the resource amount before the trade is accepted
        int brickBefore = game.getPlayerList().get(1).getBank().getResCards().get(ResourceType.BRICK);
        int woodBefore = game.getPlayerList().get(1).getBank().getResCards().get(ResourceType.WOOD);
        int recieverWoodBefore = game.getPlayerList().get(3).getBank().getResCards().get(ResourceType.WOOD);
        assertTrue(game.getPlayerList().get(3).getBank().getResCards().get(ResourceType.BRICK) == 0);
        
        // Accept the trade
        assertNotNull(modelFacade.acceptTrade(0, new AcceptTrade_Params(3, true)));
        
        // Make sure the trade is no longer being offered
        assertNull(game.getTradeOffer());
        
        // Check to see if the resources got updated
        assertTrue(game.getPlayerList().get(1).getBank().getResCards().get(ResourceType.BRICK) == --brickBefore);
        assertTrue(game.getPlayerList().get(1).getBank().getResCards().get(ResourceType.WOOD) == ++woodBefore);
        assertTrue(game.getPlayerList().get(3).getBank().getResCards().get(ResourceType.BRICK) == 1);
        assertTrue(game.getPlayerList().get(3).getBank().getResCards().get(ResourceType.WOOD) == --recieverWoodBefore);
        
	}

}
