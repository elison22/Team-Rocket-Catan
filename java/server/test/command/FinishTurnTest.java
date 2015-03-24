package test.command;

import facade.IModelFacade;
import facade.ModelFacade;
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
public class FinishTurnTest {
    IModelFacade modelFacade;

    @Before
    public void setUp() throws Exception {
        modelFacade = new ModelFacade();

        //Create game, add players, run setup rounds, and roll a number
        modelFacade.createGame(new CreateGame_Params(false, false, false, "RollNumberTest"));
        modelFacade.joinGame(new JoinGame_Params(0, "white"), "test1", 0);
        modelFacade.joinGame(new JoinGame_Params(0, "green"), "test2", 1);
        modelFacade.joinGame(new JoinGame_Params(0, "puce"), "test3", 2);
        modelFacade.joinGame(new JoinGame_Params(0, "blue"), "test4", 3);
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
        modelFacade.rollNumber(0, new RollNumber_Params(0, 6));
    }

    @Test
    public void test() {
        // Test someone ending their turn when it's not their turn currently
        assertNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));

        // Test the correct player ending their turn
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));

        // After turn ends, the game state should be Rolling
        assertTrue(modelFacade.getGame(0).getTurnState() == ServerTurnState.Rolling);
    }
}
