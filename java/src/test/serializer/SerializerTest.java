package test.serializer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import serializer.*;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import model.board.Board;
import model.board.BoardException;
import model.game.GameModel;
import model.game.TurnState;

import org.junit.Before;
import org.junit.Test;

public class SerializerTest
{
	
	private File jsonFile;
	private Scanner jsonScanner;
	private String json;
	private Serializer serializer;
	private GameModel newModel;

	@Before
	public void initSerializer()
	{
		try{
			jsonFile = new File(System.getProperty("user.dir") + "/java/src/test/JunitJsonFiles/Json4.json");
			jsonScanner = new Scanner(jsonFile).useDelimiter("\\Z");
			json = jsonScanner.next();
			jsonScanner.close();
			serializer = new Serializer();
			newModel = new GameModel();
			newModel = serializer.deSerializeFromServer(newModel, json);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Ensures the GameBank's resources and development cards
	 * 	are properly updated by the serializer
	 */
	@Test
	public void testGameBank()
	{
		assertTrue(newModel.getCardBank().getDevCards().size() == 25);
		assertTrue(newModel.getCardBank().canGiveDevCard());
		assertTrue(newModel.getCardBank().getResCards().get(ResourceType.BRICK) == 19);
		assertTrue(newModel.getCardBank().getResCards().get(ResourceType.WOOD) == 16);
		assertTrue(newModel.getCardBank().getResCards().get(ResourceType.SHEEP) == 22);
		assertTrue(newModel.getCardBank().getResCards().get(ResourceType.WHEAT) == 20);
		assertTrue(newModel.getCardBank().getResCards().get(ResourceType.ORE) == 19);
	}
	/**
	 * Ensures the TurnTracker's state is properly set by the serializer
	 */
	@Test
	public void testTurnTracker()
	{
		assertTrue(newModel.getTurnTracker().getCurrentState() == TurnState.Playing);
		assertTrue(newModel.getTurnTracker().getLargestArmyPlayerIndex() == -1);
		assertTrue(newModel.getTurnTracker().getLongestRoadPlayerIndex() == -1);
		assertTrue(newModel.getTurnTracker().getCurrentPlayerIndex() == 0);
	}

	/**
	 * Tests the construction of the player list and players
	 */
	@Test
	public void testPlayerList()
	{
		//Ensures the correct number of players exist
		assertTrue(newModel.getPlayerList().size() == 4);
		//Checks the resources of Player 1
		assertTrue(newModel.getPlayerList().get(0).getBank().getResCards().get(ResourceType.BRICK) == 6);
		assertTrue(newModel.getPlayerList().get(0).getBank().getResCards().get(ResourceType.WOOD) == 6);
		assertTrue(newModel.getPlayerList().get(0).getBank().getResCards().get(ResourceType.SHEEP) == 6);
		assertTrue(newModel.getPlayerList().get(0).getBank().getResCards().get(ResourceType.WHEAT) == 6);
		assertTrue(newModel.getPlayerList().get(0).getBank().getResCards().get(ResourceType.ORE) == 6);
		//Checks the old and new development cards of Player 1
		assertTrue(newModel.getPlayerList().get(0).getBank().getNewDevCards().size() == 1);
		assertTrue(newModel.getPlayerList().get(0).getBank().getOldDevCards().size() == 5);
		//Checks the name, remaining constructables, victory points, color, and ID of Player 1
		assertTrue(newModel.getPlayerList().get(0).getName().equals("Test"));
		assertTrue(newModel.getPlayerList().get(0).getRemainingCities() == 4);
		assertTrue(newModel.getPlayerList().get(0).getRemainingSettlements() == 3);
		assertTrue(newModel.getPlayerList().get(0).getRemainingRoads() == 13);
		assertTrue(newModel.getPlayerList().get(0).getVictoryPoints() == 9);
		assertTrue(newModel.getPlayerList().get(0).getColor().equals("blue"));
		assertTrue(newModel.getPlayerList().get(0).getPlayerID() == 13);
		//Repeats tests for Player 2
		assertTrue(newModel.getPlayerList().get(1).getBank().getResCards().get(ResourceType.BRICK) == 1);
		assertTrue(newModel.getPlayerList().get(1).getBank().getResCards().get(ResourceType.WOOD) == 3);
		assertTrue(newModel.getPlayerList().get(1).getBank().getResCards().get(ResourceType.SHEEP) == 0);
		assertTrue(newModel.getPlayerList().get(1).getBank().getResCards().get(ResourceType.WHEAT) == 0);
		assertTrue(newModel.getPlayerList().get(1).getBank().getResCards().get(ResourceType.ORE) == 1);
		assertTrue(newModel.getPlayerList().get(1).getBank().getDevCards().size() == 0);
		assertTrue(newModel.getPlayerList().get(1).getName().equals("Hannah"));
		assertTrue(newModel.getPlayerList().get(1).getRemainingCities() == 4);
		assertTrue(newModel.getPlayerList().get(1).getRemainingSettlements() == 3);
		assertTrue(newModel.getPlayerList().get(1).getRemainingRoads() == 13);
		assertTrue(newModel.getPlayerList().get(1).getVictoryPoints() == 2);
		assertTrue(newModel.getPlayerList().get(1).getColor().equals("red"));
		assertTrue(newModel.getPlayerList().get(1).getPlayerID() == -2);
	}
	
	/**
	 * Ensures the Board object is built accurately by the serializer
	 * @throws BoardException
	 * @throws FileNotFoundException
	 */
	@Test
	public void testMap() throws BoardException, FileNotFoundException
	{
		jsonFile = new File(System.getProperty("user.dir") + "/java/src/test/JunitJsonFiles/Json7.json");//"bin/test/JunitJsonFiles/Json7.json");
		jsonScanner = new Scanner(jsonFile).useDelimiter("\\Z");
		json = jsonScanner.next();
		jsonScanner.close();
		newModel = new GameModel();
		newModel = serializer.deSerializeFromServer(newModel, json);
		//Manually builds test board
		Board testBoard = new Board(false, false, false);
		testBoard.doBuildRoad(new EdgeLocation(new HexLocation(0, 1), EdgeDirection.South), 0);
        testBoard.doBuildRoad(new EdgeLocation(new HexLocation(1, 1), EdgeDirection.NorthEast), 0);
        testBoard.doBuildRoad(new EdgeLocation(new HexLocation(0, 0), EdgeDirection.South), 1);
        testBoard.doBuildRoad(new EdgeLocation(new HexLocation(1,-1), EdgeDirection.South), 1);
        testBoard.doBuildRoad(new EdgeLocation(new HexLocation(1,-1), EdgeDirection.NorthEast), 2);
        testBoard.doBuildRoad(new EdgeLocation(new HexLocation(-1,1), EdgeDirection.SouthWest), 2);
        testBoard.doBuildRoad(new EdgeLocation(new HexLocation(-1,0), EdgeDirection.North), 3);
        testBoard.doBuildRoad(new EdgeLocation(new HexLocation(-2,1), EdgeDirection.SouthWest), 3);
        testBoard.doBuildSettlement(new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast), 0);
        testBoard.doBuildSettlement(new VertexLocation(new HexLocation(1, 1), VertexDirection.SouthWest), 0);
        testBoard.doBuildSettlement(new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthWest), 1);
        testBoard.doBuildSettlement(new VertexLocation(new HexLocation(1,-1), VertexDirection.SouthWest), 1);
        testBoard.doBuildSettlement(new VertexLocation(new HexLocation(1,-1), VertexDirection.NorthEast), 2);
        testBoard.doBuildSettlement(new VertexLocation(new HexLocation(-1,1), VertexDirection.SouthWest), 2);
        testBoard.doBuildSettlement(new VertexLocation(new HexLocation(-1,0), VertexDirection.NorthWest), 3);
        testBoard.doBuildSettlement(new VertexLocation(new HexLocation(-2,1), VertexDirection.SouthWest), 3);
        //Compares test board to automatically generated board
        assertTrue(newModel.getMap().equals(testBoard));
        testBoard = new Board(false, false, false);
        //Compares empty board to automatically generated board
        assertFalse(newModel.getMap().equals(testBoard));
	}
	

	/**
	 * Ensures the Chat is initialized properly by the serializer by making
	 *  sure enough messages are created and the source and message are accurate
	 * @throws FileNotFoundException 
	 * @throws BoardException 
	 */
	@Test
	public void testChat() throws FileNotFoundException, BoardException
	{
		jsonFile = new File(System.getProperty("user.dir") + "/java/src/test/JunitJsonFiles/Json7.json");
		jsonScanner = new Scanner(jsonFile).useDelimiter("\\Z");
		json = jsonScanner.next();
		jsonScanner.close();
		newModel = new GameModel();
		newModel = serializer.deSerializeFromServer(newModel, json);
		assertTrue(newModel.getChat().getChatMessages().size() == 1);
		System.out.println("\"" + newModel.getChat().getChatMessages().get(0).getMessage() + "\"");
		System.out.println(" - " + newModel.getChat().getChatMessages().get(0).getOwner());
		assertTrue(newModel.getChat().getChatMessages().get(0).getMessage().equals("Hayden and Brandt are dummies."));
		assertTrue(newModel.getChat().getChatMessages().get(0).getOwner().equals("Chad"));
	}
	
	/**
	 * Ensures the GameHistory is initialized properly by the serializer
	 * 	by making sure enough objects are created
	 */
	@Test
	public void testLog()
	{
		assertTrue(newModel.getGameHistory().getChatMessages().size() == 30);
	}
}
