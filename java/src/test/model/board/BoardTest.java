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
    HexLocation hexloc1 = new HexLocation(0, 0);
    HexLocation hexloc2 = new HexLocation(-1, 1);
    VertexLocation vertloc1 = new VertexLocation(hexloc1, VertexDirection.NorthWest);
    VertexLocation vertloc2 = new VertexLocation(hexloc1, VertexDirection.West);
    VertexLocation vertloc3 = new VertexLocation(hexloc2, VertexDirection.NorthWest);
    EdgeLocation edgeloc1 = new EdgeLocation(hexloc1, EdgeDirection.NorthWest);
    EdgeLocation edgeloc2 = new EdgeLocation(hexloc2, EdgeDirection.North);

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
            result = testBoard.canBuildSettlement(vertloc1, 0);
            assertFalse(result);

            result = testBoard.canBuildCity(vertloc1, 0);
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
            testBoard.doBuildSettlement(vertloc1, 0);

            result = testBoard.canBuildSettlement(vertloc1, 0);
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
            result = testBoard.canBuildRoad(edgeloc1, 0);
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
            testBoard.doBuildSettlement(vertloc1, 0);

            result = testBoard.canBuildRoad(edgeloc1, 0);
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
            testBoard.doBuildSettlement(vertloc1, 0);
            testBoard.doBuildRoad(edgeloc1, 0);

            result = testBoard.canBuildRoad(edgeloc1, 0);
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
            testBoard.doBuildSettlement(vertloc1, 0);
            testBoard.doBuildRoad(edgeloc1, 0);

            result = testBoard.canBuildSettlement(vertloc2, 0);
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
            testBoard.doBuildSettlement(vertloc1, 0);
            testBoard.doBuildRoad(edgeloc1, 0);

            result = testBoard.canBuildRoad(edgeloc2, 1);
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
            testBoard.doBuildSettlement(vertloc1, 0);
            testBoard.doBuildRoad(edgeloc1, 0);

            result = testBoard.canBuildRoad(edgeloc2, 0);
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
            testBoard.doBuildSettlement(vertloc1, 0);
            testBoard.doBuildRoad(edgeloc1, 0);
            testBoard.doBuildRoad(edgeloc2, 0);

            result = testBoard.canBuildSettlement(vertloc3, 1);
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
            testBoard.doBuildSettlement(vertloc1, 0);
            testBoard.doBuildRoad(edgeloc1, 0);
            testBoard.doBuildRoad(edgeloc2, 0);

            result = testBoard.canBuildCity(vertloc3, 0);
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
            testBoard.doBuildSettlement(vertloc1, 0);
            testBoard.doBuildRoad(edgeloc1, 0);
            testBoard.doBuildRoad(edgeloc2, 0);

            result = testBoard.canBuildSettlement(vertloc3, 0);
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
            testBoard.doBuildSettlement(vertloc1, 0);
            testBoard.doBuildRoad(edgeloc1, 0);
            testBoard.doBuildRoad(edgeloc2, 0);
            testBoard.doBuildSettlement(vertloc3, 0);

            result = testBoard.canBuildCity(vertloc3, 1);
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
            testBoard.doBuildSettlement(vertloc1, 0);
            testBoard.doBuildRoad(edgeloc1, 0);
            testBoard.doBuildRoad(edgeloc2, 0);
            testBoard.doBuildSettlement(vertloc3, 0);

            result = testBoard.canBuildCity(vertloc3, 0);
            assertTrue(result);

            testBoard.doBuildCity(vertloc3, 0);
        } catch (BoardException e) {
            e.printStackTrace();
        }
        assertFalse(exception);
    }

    @Test
    public void testCannotBuildCityOnACity() {

        exception = false;
        try {
            testBoard.doBuildSettlement(vertloc1, 0);
            testBoard.doBuildRoad(edgeloc1, 0);
            testBoard.doBuildRoad(edgeloc2, 0);
            testBoard.doBuildSettlement(vertloc3, 0);
            testBoard.doBuildCity(vertloc3, 0);

            result = testBoard.canBuildCity(vertloc3, 0);
            assertFalse(result);

        } catch (BoardException e) {
            exception = true;
        }
        assertFalse(exception);

    }

}
