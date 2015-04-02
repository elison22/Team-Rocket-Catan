package test.command;

import static org.junit.Assert.*;
import model.scards.ServerDevCard;
import model.sgame.ServerGame;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.*;
import shared.dto.BuildCity_Params;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.BuyDevCard_Params;
import shared.dto.CreateGame_Params;
import shared.dto.FinishTurn_Params;
import shared.dto.JoinGame_Params;
import shared.dto.Monopoly_Params;
import shared.dto.Monument_Params;
import shared.dto.RoadBuilding_Params;
import shared.dto.RollNumber_Params;
import shared.dto.Soldier_Params;
import shared.dto.YearOfPlenty_Params;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import facade.ModelFacade;

public class DevCardsTest {

	private ModelFacade modelFacade;
	private ServerGame game;
	
	@Before
	public void setUp() throws Exception {
		modelFacade = new ModelFacade();
		// Create a game
        assertNotNull(modelFacade.createGame(new CreateGame_Params(false, false, false, "RollNumberTest")));

        // Populate game with players
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "green"), "test1", 0));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "purple"), "test2", 1));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "yellow"), "test3", 2));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "puce"), "test4", 3));
        
        // Set up initial roads/settlements for all players
        modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.North), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(-1, 0), VertexDirection.NorthWest), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.SouthWest), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(-1, 1), VertexDirection.SouthWest), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.South), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthWest), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(3, new EdgeLocation(new HexLocation(0, 1), EdgeDirection.South), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(3, new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(3, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.NorthEast), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(3, new VertexLocation(new HexLocation(1, 1), VertexDirection.East), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.South), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(1, -1), VertexDirection.SouthWest), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.NorthEast), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(1, -1), VertexDirection.NorthEast), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.SouthWest), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(-2, 1), VertexDirection.SouthWest), true));
        
        game = modelFacade.getGame(0);
	}
	
	@Test
	public void testBuyDevCard() {
		// Roll dice to set turn state
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 9)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 8)));
        
        // Buy devCard without resources
        assertNull(modelFacade.buyDevCard(0, new BuyDevCard_Params(1)));
        
        // Give player 1 enough resources
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.ORE, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.WHEAT, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.SHEEP, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.WOOD, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.BRICK, 9);
        
        // Buy devCard with resources
        assertNotNull(modelFacade.buyDevCard(0, new BuyDevCard_Params(1)));
	}
	
	@Test
	public void testRobber() {
		
        
        // Roll dice to set turn state
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 9)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 8)));
        
        // Give player 1 enough resources
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.ORE, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.WHEAT, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.SHEEP, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.WOOD, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.BRICK, 9);
        
        //Give player 0 only one resource so that you can know what should get stolen
        game.getPlayerList().get(0).getBank().getResCards().put(ResourceType.ORE, 0);
        game.getPlayerList().get(0).getBank().getResCards().put(ResourceType.WHEAT, 0);
        game.getPlayerList().get(0).getBank().getResCards().put(ResourceType.SHEEP, 0);
        game.getPlayerList().get(0).getBank().getResCards().put(ResourceType.WOOD, 0);
        game.getPlayerList().get(0).getBank().getResCards().put(ResourceType.BRICK, 1);
        
        // Play soldier
        game.getPlayerList().get(1).getBank().getDevCards().add(new ServerDevCard(DevCardType.SOLDIER));
        game.getPlayerList().get(1).getBank().getOldDevCards().add(new ServerDevCard(DevCardType.SOLDIER));
        assertNotNull(modelFacade.doSoldier(0, new Soldier_Params(1, 0, new HexLocation(-1, 0))));
        
        // Ensure resource is stolen
        assertTrue(game.getPlayerList().get(1).getBank().getResCards().get(ResourceType.BRICK) == 10);
        assertTrue(game.getPlayerList().get(0).getBank().getResCards().get(ResourceType.BRICK) == 0);
        
	}
	
	@Test
	public void testYearOfPlenty() {
		// Roll dice to set turn state
		assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 9)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 8)));
        
        // Set resources to compare against
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.ORE, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.WHEAT, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.SHEEP, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.WOOD, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.BRICK, 9);
        
        // Play year of plenty card
        game.getPlayerList().get(1).getBank().getDevCards().add(new ServerDevCard(DevCardType.YEAR_OF_PLENTY));
        game.getPlayerList().get(1).getBank().getOldDevCards().add(new ServerDevCard(DevCardType.YEAR_OF_PLENTY));
        assertNotNull(modelFacade.doYearOfPlenty(0, new YearOfPlenty_Params(1, "SHEEP", "WOOD")));
        
        // Check that resources were received
        assertTrue(game.getPlayerList().get(1).getBank().getResCards().get(ResourceType.SHEEP) == 10);
        assertTrue(game.getPlayerList().get(1).getBank().getResCards().get(ResourceType.WOOD) == 10);
        
	}
	
	@Test
	public void testMonopoly() {
		// Roll dice to set turn state
		assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 9)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 8)));
        
        // Set resources to compare against
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.ORE, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.WHEAT, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.SHEEP, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.WOOD, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.BRICK, 9);
        
        game.getPlayerList().get(0).getBank().getResCards().put(ResourceType.WHEAT, 1);
        game.getPlayerList().get(2).getBank().getResCards().put(ResourceType.WHEAT, 1);
        game.getPlayerList().get(3).getBank().getResCards().put(ResourceType.WHEAT, 2);
        
        
        // Play monopoly card
        game.getPlayerList().get(1).getBank().getDevCards().add(new ServerDevCard(DevCardType.MONOPOLY));
        game.getPlayerList().get(1).getBank().getOldDevCards().add(new ServerDevCard(DevCardType.MONOPOLY));
        assertNotNull(modelFacade.doMonopoly(0, new Monopoly_Params("WHEAT", 1)));
        
        // Check that resources were received
        assertTrue(game.getPlayerList().get(1).getBank().getResCards().get(ResourceType.WHEAT) == 13);
        assertTrue(game.getPlayerList().get(0).getBank().getResCards().get(ResourceType.WHEAT) == 0);
        assertTrue(game.getPlayerList().get(2).getBank().getResCards().get(ResourceType.WHEAT) == 0);
        assertTrue(game.getPlayerList().get(3).getBank().getResCards().get(ResourceType.WHEAT) == 0);
        
	}
        
	
	@Test
	public void testRoadBuilding(){
		// Roll dice to set turn state
		assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 9)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 8)));
        
        
        // Play year of plenty card
        game.getPlayerList().get(1).getBank().getDevCards().add(new ServerDevCard(DevCardType.ROAD_BUILD));
        game.getPlayerList().get(1).getBank().getOldDevCards().add(new ServerDevCard(DevCardType.ROAD_BUILD));
        assertNotNull(modelFacade.doRoadBuilding(0, new RoadBuilding_Params(1, new EdgeLocation(new HexLocation(2, -2), EdgeDirection.South), new EdgeLocation(new HexLocation(2, -2), EdgeDirection.SouthEast))));
        
        // Check that resources were received
        assertTrue(game.getPlayerList().get(1).getRemainingRoads() == 11);
	}
        
	@Test
	public void testMonument() {    
        
		// Roll dice to set turn state
		assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 9)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 8)));
        
        // Set resources to compare against
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.ORE, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.WHEAT, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.SHEEP, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.WOOD, 9);
        game.getPlayerList().get(1).getBank().getResCards().put(ResourceType.BRICK, 9);
		
        // Play monument card without enough victory points
        game.getPlayerList().get(1).getBank().getDevCards().add(new ServerDevCard(DevCardType.MONUMENT));
        game.getPlayerList().get(1).getBank().getOldDevCards().add(new ServerDevCard(DevCardType.MONUMENT));
        assertNull(modelFacade.doMonument(0, new Monument_Params(1)));
        
        // Play monument card with enough victory points
        	// Build cities for victory points
        assertNotNull(modelFacade.buildCity(0, new BuildCity_Params(1, new VertexLocation(new HexLocation(-1, 1), VertexDirection.SouthWest))));
        assertNotNull(modelFacade.buildCity(0, new BuildCity_Params(1, new VertexLocation(new HexLocation(1, -1), VertexDirection.NorthEast))));
        	// Build 4 roads to get longest road and more victory points
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(2, -2), EdgeDirection.South), false)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(2, -2), EdgeDirection.SouthEast), false)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(2, -2), EdgeDirection.NorthEast), false)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(2, -2), EdgeDirection.North), false)));
        
        	// Check that player[1] has longest road
        assertTrue(game.getTurnTracker().getLongestRoadPlayerIndex() == 1);
        
        	// Check that player[1] was rewarded victory points for longest road and cities
        assertTrue(game.getPlayerList().get(1).getVictoryPoints() == 6);
        
        	//Give enough monument cards so that they can be played
        game.getPlayerList().get(1).getBank().getDevCards().add(new ServerDevCard(DevCardType.MONUMENT));
        game.getPlayerList().get(1).getBank().getDevCards().add(new ServerDevCard(DevCardType.MONUMENT));
        game.getPlayerList().get(1).getBank().getDevCards().add(new ServerDevCard(DevCardType.MONUMENT));
        game.getPlayerList().get(1).getBank().getOldDevCards().add(new ServerDevCard(DevCardType.MONUMENT));
        game.getPlayerList().get(1).getBank().getOldDevCards().add(new ServerDevCard(DevCardType.MONUMENT));
        game.getPlayerList().get(1).getBank().getOldDevCards().add(new ServerDevCard(DevCardType.MONUMENT));
        

        assertNotNull(modelFacade.doMonument(0, new Monument_Params(1)));
        
	}

}
