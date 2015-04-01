package test.command;

import facade.IModelFacade;
import facade.ModelFacade;
import model.sgame.ServerTurnState;

import org.junit.Before;
import org.junit.Test;

import shared.dto.*;
import shared.locations.*;
import static org.junit.Assert.*;

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
        
    }

    @Test
    public void test() {
    	// Build player[0]'s initial road and settlement
    	assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.North), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(1, 1), VertexDirection.NorthEast), true)));
        
        // Check that it's player[1]'s turn
        assertTrue(modelFacade.getGame(0).getTurnTracker().getCurrentPlayerIndex() == 1);
        
        // Build player[1]-[3]'s initial road and settlement
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(-2, -1), EdgeDirection.SouthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(-2, -1), VertexDirection.East), true)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(0, -2), EdgeDirection.NorthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(0, -2), VertexDirection.NorthEast), true)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(3, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.South), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(3, new VertexLocation(new HexLocation(1, 1), VertexDirection.SouthWest), true)));
        
        // Check that it's still player[3]'s turn
        assertTrue(modelFacade.getGame(0).getTurnTracker().getCurrentPlayerIndex() == 3);
        
        // Build player[3]'s second road and settlement
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(3, new EdgeLocation(new HexLocation(1, -1), EdgeDirection.North), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(3, new VertexLocation(new HexLocation(1, -1), VertexDirection.NorthEast), true)));
        
        // Check that it's player[2]'s turn
        assertTrue(modelFacade.getGame(0).getTurnTracker().getCurrentPlayerIndex() == 2);
        
        // Build player[2]-[0]'s second road and settlement
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(2, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.NorthWest), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(2, new VertexLocation(new HexLocation(0, 0), VertexDirection.West), true)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(1, new EdgeLocation(new HexLocation(0, 1), EdgeDirection.NorthEast), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(1, new VertexLocation(new HexLocation(0, 1), VertexDirection.NorthEast), true)));
        assertNotNull(modelFacade.buildRoad(0, new BuildRoad_Params(0, new EdgeLocation(new HexLocation(-1, 2), EdgeDirection.North), true)));
        assertNotNull(modelFacade.buildSettlement(0, new BuildSettlement_Params(0, new VertexLocation(new HexLocation(-1, 2), VertexDirection.NorthWest), true)));
        
        // Check that it's player[0]'s turn
        assertTrue(modelFacade.getGame(0).getTurnTracker().getCurrentPlayerIndex() == 0);
        
        // Test the correct player ending their turn
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(0, 6)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(0)));
        
        // Test someone ending their turn when it's not their turn currently
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(1, 6)));
        assertNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));
        
        // Progress through everyone's turn so it's back to player[0]
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(1)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(2, 6)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(2)));
        assertNotNull(modelFacade.rollNumber(0, new RollNumber_Params(3, 6)));
        assertNotNull(modelFacade.finishTurn(0, new FinishTurn_Params(3)));
        
        // Check that it's player[0]'s turn again
        assertTrue(modelFacade.getGame(0).getTurnTracker().getCurrentPlayerIndex() == 0);

        // After turn ends, the game state should be Rolling
        assertTrue(modelFacade.getGame(0).getTurnState() == ServerTurnState.Rolling);
        
    }
}
