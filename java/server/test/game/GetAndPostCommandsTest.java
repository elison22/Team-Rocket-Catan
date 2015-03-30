package test.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import command.ICommandObject;

import facade.GameManager;
import facade.ModelFacade;
import serializer.ServerSerializer;
import shared.dto.BuildRoad_Params;
import shared.dto.BuildSettlement_Params;
import shared.dto.CreateGame_Params;
import shared.dto.JoinGame_Params;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class GetAndPostCommandsTest {

	ModelFacade modelFacade;
	
	@Before
	public void setUp() throws Exception {
		modelFacade = new ModelFacade();
	}

	@Test
	public void test() throws Exception {
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

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.SouthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.East), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(0, -1), EdgeDirection.SouthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(0, -1), VertexDirection.East), true)));

        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.NorthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(1, -1), VertexDirection.East), true)));
        
        // Make sure that the right amount of commands were stored (21)
        GameManager gameManager = modelFacade.getGameManager();
        assertTrue(gameManager.getCommands(0).length == 21);
        
        // Make sure that the facade is storing game commands
        assertNotNull(modelFacade.getGameCommands(0));
        
        // Attempt to deserialize the commands
        ServerSerializer ser = new ServerSerializer();
        ICommandObject[] commands = ser.deSerializeCommands(modelFacade.getGameCommands(0));
        assertNotNull(commands);
        
        // Make sure the deserialized list contains the same number of commands
        assertTrue(commands.length == gameManager.getCommands(0).length);
        
        // Save the current model
        String model1 = modelFacade.getGameModel(0);
        
        // Reset the facade
        modelFacade = new ModelFacade();
        
        // Execute the deserialized commands
        modelFacade.executeGameCommands(commands);
        
        // Make sure they all executed
        gameManager = modelFacade.getGameManager();
        assertTrue(gameManager.getCommands(0).length == 21);
        
        // Retrieve the new model and compare it to the old
        String model2 = modelFacade.getGameModel(0);
        assertTrue(model1.equals(model2));
	}
}
