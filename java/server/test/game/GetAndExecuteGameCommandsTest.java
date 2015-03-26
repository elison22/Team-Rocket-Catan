package test.game;

import static org.junit.Assert.*;

import model.sgame.ServerGame;
import org.junit.Before;
import org.junit.Test;

import shared.dto.*;
import facade.IModelFacade;
import facade.ModelFacade;
import shared.locations.*;

/**
 * Created by brandt on 3/25/15.
 */
public class GetAndExecuteGameCommandsTest {

    IModelFacade modelFacade;

    @Before
    public void setUp() throws Exception {
        modelFacade = new ModelFacade();
    }

    @Test
    public void test() {
        // Create a game
        modelFacade.createGame(new CreateGame_Params(false, false, false, "RollNumberTest"));

        // Populate game with players
        modelFacade.joinGame(new JoinGame_Params(0, "blue"), "test1", 0);
        modelFacade.joinGame(new JoinGame_Params(0, "green"), "test2", 1);
        modelFacade.joinGame(new JoinGame_Params(0, "red"), "test3", 2);
        modelFacade.joinGame(new JoinGame_Params(0, "purple"), "test4", 3);

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

        ServerGame game = modelFacade.getGame(0);

        // Roll 11's to get player[3] more than 4 wheat
        modelFacade.rollNumber(0, new RollNumber_Params(0, 11));
        modelFacade.finishTurn(0, new FinishTurn_Params(0));
        modelFacade.rollNumber(0, new RollNumber_Params(1, 11));
        modelFacade.finishTurn(0, new FinishTurn_Params(1));
        modelFacade.rollNumber(0, new RollNumber_Params(2, 11));





    }

}
