package test.model.board;

import model.board.Board;
import model.board.BoardException;
import static org.junit.Assert.*;

import org.junit.*;

import shared.locations.*;

public class BoardTest {

    private Board testBoard;

    boolean exception;
    boolean result;
    HexLocation hexmid = new HexLocation(0, 0);
    HexLocation hexleft = new HexLocation(-1, 1);
    VertexLocation vertmidNW = new VertexLocation(hexmid, VertexDirection.NorthWest);
    VertexLocation vertmidW = new VertexLocation(hexmid, VertexDirection.West);
    VertexLocation vertleftNW = new VertexLocation(hexleft, VertexDirection.NorthWest);
    EdgeLocation edgemidNW = new EdgeLocation(hexmid, EdgeDirection.NorthWest);
    EdgeLocation edgeleftN = new EdgeLocation(hexleft, EdgeDirection.North);

    @Before
    public void init() throws BoardException {
        testBoard = new Board(true, true, true);
    }

    @Test
    public void testPresetBoard() {

        exception = false;
        try {
            testBoard = new Board(false, false, false);
        } catch (BoardException e) {
            e.printStackTrace();
            exception = true;
        }
        assertFalse(exception);

    }

    @Test
    public void testRandomBoard() {

        exception = false;
        try {
            testBoard = new Board(true, true, true);
        } catch (BoardException e) {
            e.printStackTrace();
            exception = true;
        }
        assertFalse(exception);

    }

    @Test
    public void testCannotBuildIsolateBuilding() {

        exception = false;
        try {
            result = testBoard.canBuildSettlement(vertmidNW, 0);
            assertFalse(result);

            result = testBoard.canBuildCity(vertmidNW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            exception = true;
        }
        assertFalse(exception);

    }

    @Test
    public void testCannotBuiltSettlementOnSettlement() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);

            result = testBoard.canBuildSettlement(vertmidNW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            exception = true;
        }
        assertFalse(exception);

    }

    @Test
    public void testCannotBuildIsolateRoad() {

        exception = false;
        try {
            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            exception = true;
        }
        assertFalse(exception);

    }

    @Test
    public void testCanBuildRoadBySettlement() {

        exception = false;

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertTrue(result);
        } catch (BoardException e) {
            e.printStackTrace();
            exception = true;
        }
        assertFalse(exception);

    }

    @Test
    public void testCannotBuildDuplicateRoad() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            exception = true;
        }
        assertFalse(exception);

    }

    @Test
    public void testCannotBuildNeighborSettlement() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildSettlement(vertmidW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            exception = true;
        }
        assertFalse(exception);

    }

    @Test
    public void testCannotBuildRoadOnlyByOpponentRoad() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildRoad(edgeleftN, 1);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            exception = true;
        }
        assertFalse(exception);

    }

    @Test
    public void testCanBuildRoadByRoad() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildRoad(edgeleftN, 0);
            assertTrue(result);
        } catch (BoardException e) {
            e.printStackTrace();
        }
        assertFalse(exception);
    }

    @Test
    public void testCannotBuildSettlementOnlyByOpponentRoad () {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);

            result = testBoard.canBuildSettlement(vertleftNW, 1);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
        }
        assertFalse(exception);

    }

    @Test
    public void testCannotBuildCityWithoutSettlement() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);

            result = testBoard.canBuildCity(vertleftNW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
        }
        assertFalse(exception);

    }

    @Test
    public void testCanBuildSettlementConnectedByRoads() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);

            result = testBoard.canBuildSettlement(vertleftNW, 0);
            assertTrue(result);
        } catch (BoardException e) {
            e.printStackTrace();
        }
        assertFalse(exception);
    }

    @Test
    public void testCannotBuildCityOnOpponentSettlement() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);
            testBoard.doBuildSettlement(vertleftNW, 0);

            result = testBoard.canBuildCity(vertleftNW, 1);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
        }
        assertFalse(exception);
    }

    @Test
    public void testCanBuildCityOnlyOnOwnedSettlement() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);
            testBoard.doBuildSettlement(vertleftNW, 0);

            result = testBoard.canBuildCity(vertleftNW, 0);
            assertTrue(result);

            testBoard.doBuildCity(vertleftNW, 0);
        } catch (BoardException e) {
            e.printStackTrace();
        }
        assertFalse(exception);
    }

    @Test
    public void testCannotBuildCityOnACity() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);
            testBoard.doBuildSettlement(vertleftNW, 0);
            testBoard.doBuildCity(vertleftNW, 0);

            result = testBoard.canBuildCity(vertleftNW, 0);
            assertFalse(result);

        } catch (BoardException e) {
            exception = true;
        }
        assertFalse(exception);

    }

}
