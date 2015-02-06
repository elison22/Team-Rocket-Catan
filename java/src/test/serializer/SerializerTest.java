package test.serializer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import serializer.*;
import shared.definitions.ResourceType;
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
	public void initSerializer() //throws FileNotFoundException, BoardException
	{
		try{
			jsonFile = new File("test/JunitJsonFiles/Json4.json");
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

	@Test
	public void testTurnTracker()
	{
		assertTrue(newModel.getTurnTracker().getCurrentState() == TurnState.Playing);
		assertTrue(newModel.getTurnTracker().getLargestArmyPlayerIndex() == -1);
		assertTrue(newModel.getTurnTracker().getLongestRoadPlayerIndex() == -1);
		assertTrue(newModel.getTurnTracker().getCurrentPlayerIndex() == 0);
	}

	@Test
	public void testPlayerList()
	{
		assertTrue(newModel.getPlayerList().size() == 4);
		assertTrue(newModel.getPlayerList().get(0).getBank().getResCards().get(ResourceType.BRICK) == 6);
		assertTrue(newModel.getPlayerList().get(0).getBank().getResCards().get(ResourceType.WOOD) == 6);
		assertTrue(newModel.getPlayerList().get(0).getBank().getResCards().get(ResourceType.SHEEP) == 6);
		assertTrue(newModel.getPlayerList().get(0).getBank().getResCards().get(ResourceType.WHEAT) == 6);
		assertTrue(newModel.getPlayerList().get(0).getBank().getResCards().get(ResourceType.ORE) == 6);
		//assertTrue(newModel.getPlayerList().get(0).getBank().getDevCards().size() == 6);
		assertTrue(newModel.getPlayerList().get(0).getName().equals("Test"));
		assertTrue(newModel.getPlayerList().get(0).getRemainingCities() == 4);
		assertTrue(newModel.getPlayerList().get(0).getRemainingSettlements() == 3);
		assertTrue(newModel.getPlayerList().get(0).getRemainingRoads() == 13);
		assertTrue(newModel.getPlayerList().get(0).getVictoryPoints() == 9);
		assertTrue(newModel.getPlayerList().get(0).getColor().equals("blue"));
		assertTrue(newModel.getPlayerList().get(0).getPlayerID() == 13);
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
	
	@Test
	public void testMap()
	{
		assertTrue(newModel.getMap().getRobber().getX() == 0);
		assertTrue(newModel.getMap().getRobber().getY() == -2);
		//assertTrue(newModel.getMap().getTiles().get(key));
	}
}
