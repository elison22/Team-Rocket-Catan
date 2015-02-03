package test.proxy;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import proxy.ProxyFacade;
import proxy.ServerException;
import shared.dto.*;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

public class ProxyMovesTest {
	
	ProxyFacade facade;
	Random rand = new Random();
	
	@Before
	public void init() throws ServerException {
		facade = new ProxyFacade("localhost", "8081");
		
		// Attempt to login
		facade.login(new Login_Params("Sam", "sam"));
		
		
		// Join default game
		facade.join(new JoinGame_Params(0, "orange"));
		
		// Reset game
		facade.reset();
	}

	@Test
	public void testSendChat() throws ServerException {
		// Send chat to server
		assertTrue(facade.sendChat(new SendChat_Params(0, "Testing Chat!")) != null);
	}
	
	@Test
	public void testRollNumber() throws ServerException {
		// Send the rolled number to the server
		assertTrue(facade.rollNumber(new RollNumber_Params(1, 8)) != null);
	}
	
//	@Test 
//	public void testRobPlayer() throws ServerException {
//		// Choose random player to rob
//		int victim = rand.nextInt(3-1)+1;
//		
//		// Arbitrary Hex to put robber
//		HexLocation hex = new HexLocation(0,0);
//		
//		// Rob player
//		assertTrue(facade.robPlayer(new RobPlayer_Params(0, victim, hex)) != null);
//	}
	
	@Test
	public void testFinishTurn() throws ServerException {
		// End turn
		assertTrue(facade.finishTurn(new FinishTurn_Params(2)) != null);
	}
	
	@Test
	public void testBuyDevCard() throws ServerException {
		// Buy a dev card
		assertTrue(facade.buyDevCard(new BuyDevCard_Params(2)) != null);
	}
	
	@Test
	public void testYearOfPlenty() throws ServerException {
		// Use Year of Plenty card
		assertTrue(facade.Year_of_Plenty(new YearOfPlenty_Params(3, "Ore", "Sheep")) != null);
	}

	@Test
	public void testRoad_Building() throws ServerException {
		// 1st road spot
		EdgeLocation spot1 = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.NorthEast);
		
		// 2nd road spot
		EdgeLocation spot2 = new EdgeLocation(new HexLocation(1, -1), EdgeDirection.South);
		
		// Play Road Building card
		assertTrue(facade.Road_Building(new RoadBuilding_Params(0, spot1, spot2)) != null);
	}
}
