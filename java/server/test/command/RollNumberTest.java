package test.command;

import facade.IModelFacade;
import facade.ModelFacade;
import model.sgame.ServerGame;
import model.sgame.ServerTurnState;
import org.junit.Before;
import org.junit.Test;
import shared.dto.*;
import shared.locations.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Student on 3/23/2015.
 */
public class RollNumberTest {
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
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "white"), "test1", 0));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "green"), "test2", 1));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "puce"), "test3", 2));
        assertTrue(modelFacade.joinGame(new JoinGame_Params(0, "blue"), "test4", 3));

        ServerGame game = modelFacade.getGame(0);

        // Should fail the canDo check because rolling is not allowed in this state
        assertNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 6)));
        assertNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 5)));

        // Build initial roads and settlements
        modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.North), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(1, 1), VertexDirection.NorthEast), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(-2, -1), EdgeDirection.SouthEast), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(-2, -1), VertexDirection.East), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(0, -2), EdgeDirection.NorthEast), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(0, -2), VertexDirection.NorthEast), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(3, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.South), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(3, new VertexLocation(new HexLocation(1, 1), VertexDirection.SouthWest), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(3, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.North), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(3, new VertexLocation(new HexLocation(1, -1), VertexDirection.NorthEast), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.NorthWest), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.West), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(0, 1), EdgeDirection.NorthEast), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(0, 1), VertexDirection.NorthEast), true));

        modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-1, 2), EdgeDirection.North), true));
        modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(-1, 2), VertexDirection.NorthWest), true));

        // Check that the game is in the correct state
        assertTrue(game.getTurnState() == ServerTurnState.Rolling);

        // Check if the player can roll correctly
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 6)));
    }


}
