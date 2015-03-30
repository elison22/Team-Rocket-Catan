package test.proxy;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import proxy.ProxyFacade;
import proxy.ServerException;
import shared.definitions.ResourceType;
import shared.dto.*;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class ProxyMovesTest {
	
	ProxyFacade facade;
	Random rand = new Random();
	
	boolean setup = false;
	
	@Before
	public void init() throws ServerException {
		if (!setup) {
			facade = new ProxyFacade("localhost", "8081");
			
			// Attempt to login
			facade.login(new Login_Params("Sam", "sam"));
			
			// Join default game
			facade.join(new JoinGame_Params(0, "orange"));
			
			setup = true;
		}
	}
	
	// All of the following tests asserts that the object returned by the 
	// server (which should be a game model json, although this isn't 
	// explicitly checked) is not null.

	@Test
	public void testSendChat() throws ServerException {
		
		// Send chat to server
		assertNotNull(facade.sendChat(new SendChat_Params(0, "Testing Chat!")));
	}
	
	@Test
	public void testRollNumber() throws ServerException {
		// Send the rolled number to the server
		assertNotNull(facade.rollNumber(new RollNumber_Params(1, 8)));
	}
	
	@Test 
	public void testRobPlayerAndSoldier() throws ServerException {
		// Arbitrary Hex to put robber
		HexLocation hex = new HexLocation(0,0);
		
		// Rob player
		assertNotNull(facade.robPlayer(new RobPlayer_Params(0, 2, hex)));
		
		// Play soldier; move robber and rob victim
		assertNotNull(facade.Soldier(new Soldier_Params(1, 2, new HexLocation(-2,0))));
	}
	
	@Test
	public void testFinishTurn() throws ServerException {
		// End turn
		assertNotNull(facade.finishTurn(new FinishTurn_Params(2)));
	}
	
	@Test
	public void testBuyDevCard() throws ServerException {
		// Buy a dev card
		assertNotNull(facade.buyDevCard(new BuyDevCard_Params(2)));
	}
	
	@Test
	public void testYearOfPlenty() throws ServerException {
		// Use Year of Plenty card
		assertNotNull(facade.Year_of_Plenty(new YearOfPlenty_Params(3, "Ore", "Sheep")));
	}

	@Test
	public void testRoadBuilding() throws ServerException {
		// 1st road spot
		EdgeLocation spot1 = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.NorthEast);
		
		// 2nd road spot
		EdgeLocation spot2 = new EdgeLocation(new HexLocation(1, -1), EdgeDirection.South);
		
		// Play Road Building card
		assertNotNull(facade.Road_Building(new RoadBuilding_Params(0, spot1, spot2)));
	}
	
//	@Test
//	public void testSoldier() throws ServerException {
//		// Play soldier; move robber and rob victim
//		assertNotNull(facade.Soldier(new Soldier_Params(1, 2, new HexLocation(-2,0))));
//	}
	
	@Test
	public void testMonopoly() throws ServerException {
		// Play monopoly card
		assertNotNull(facade.Monopoly(new Monopoly_Params("Brick", 0)));
	}
	
	@Test
	public void testMonument() throws ServerException {
		// Play Monument card
		assertNotNull(facade.Monument(new Monument_Params(1)));
	}
	
	@Test
	public void testBuildRoad() throws ServerException {
		// Specify edge to build road
		EdgeLocation spot = new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.NorthEast);
		
		// Try building a road
		assertNotNull(facade.buildRoad(new BuildRoad_Params(2, spot, false)));
	}
	
	@Test
	public void testBuildSettlementAndCity() throws ServerException {
		// Specify vertex to build on
		VertexLocation vertex = new VertexLocation(new HexLocation(-1, -1), VertexDirection.NorthEast);
		
		// Try building a settlement
		assertNotNull(facade.buildSettlement(new BuildSettlement_Params(2, vertex, true)));
		
		// Try building a city in the same spot
		assertNotNull(facade.buildCity(new BuildCity_Params(2, vertex)));
	}
	
	@Test
	public void testOfferAndAcceptTrade() throws ServerException {
		// Set resources to offer (1 brick for 2 wood)
		HashMap<ResourceType, Integer> resources = new HashMap<ResourceType, Integer>();
		resources.put(ResourceType.BRICK, 1);
		resources.put(ResourceType.ORE, 0);
		resources.put(ResourceType.SHEEP, 0);
		resources.put(ResourceType.WHEAT, 0);
		resources.put(ResourceType.WOOD, 2);
		
		// Offer trade
		assertNotNull(facade.offerTrade(new OfferTrade_Params(3, resources, 2)));
		
		// Accept trade
		assertNotNull(facade.acceptTrade(new AcceptTrade_Params(2, true)));
	}
	
	@Test
	public void testMaritimeTrade() throws ServerException {
		// Prepare params for maritime trade (optional params omitted)
		MaritimeTrade_Params params = new MaritimeTrade_Params(0, 2, "WOOD", "ORE");
		
		// Test maritime trade
		assertNotNull(facade.maritimeTrade(params));
	}
	
	@Test
	public void testDiscardCards() throws ServerException {
		// Set resources to be discarded
		HashMap<ResourceType, Integer> resources = new HashMap<ResourceType, Integer>();
		resources.put(ResourceType.BRICK, 0);
		resources.put(ResourceType.ORE, 1);
		resources.put(ResourceType.SHEEP, 1);
		resources.put(ResourceType.WHEAT, 3);
		resources.put(ResourceType.WOOD, 0);
		
		// Discard resources
		assertNotNull(facade.discardCards(new DiscardCards_Params(0, resources)));
	}
}
