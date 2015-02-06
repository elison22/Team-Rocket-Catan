package test.model.board;

import model.board.Board;
import model.board.BoardException;
import static org.junit.Assert.*;

import org.junit.*;

import shared.locations.*;

public class BoardTest {

    private Board testBoard;

    boolean result;
    HexLocation hexMID = new HexLocation(0, 0);
    HexLocation hexSW = new HexLocation(-1, 1);
    HexLocation hexBottom = new HexLocation(0, 2);
    HexLocation hexTop = new HexLocation(0, -2);
    VertexLocation vertmidNW = new VertexLocation(hexMID, VertexDirection.NorthWest);
    VertexLocation vertmidW = new VertexLocation(hexMID, VertexDirection.West);
    VertexLocation vertmidE = new VertexLocation(hexMID, VertexDirection.East);
    VertexLocation vertleftNW = new VertexLocation(hexSW, VertexDirection.NorthWest);
    EdgeLocation edgemidN = new EdgeLocation(hexMID, EdgeDirection.North);
    EdgeLocation edgemidNW = new EdgeLocation(hexMID, EdgeDirection.NorthWest);
    EdgeLocation edgemidS = new EdgeLocation(hexMID, EdgeDirection.South);
    EdgeLocation edgeleftN = new EdgeLocation(hexSW, EdgeDirection.North);

    public void bypassInitialSetup() throws BoardException{
        testBoard.doBuildRoad(new EdgeLocation(hexBottom, EdgeDirection.NorthWest), 0);
        testBoard.doBuildRoad(new EdgeLocation(hexBottom, EdgeDirection.SouthWest), 0);
        testBoard.doBuildRoad(new EdgeLocation(hexBottom, EdgeDirection.South), 0);
        testBoard.doBuildRoad(new EdgeLocation(hexBottom, EdgeDirection.SouthEast), 1);
        testBoard.doBuildRoad(new EdgeLocation(hexBottom, EdgeDirection.NorthEast), 1);
        testBoard.doBuildRoad(new EdgeLocation(hexTop, EdgeDirection.SouthEast), 2);
        testBoard.doBuildRoad(new EdgeLocation(hexTop, EdgeDirection.NorthEast), 2);
        testBoard.doBuildRoad(new EdgeLocation(hexTop, EdgeDirection.North), 3);
        testBoard.doBuildRoad(new EdgeLocation(hexTop, EdgeDirection.NorthWest), 3);
        testBoard.doBuildRoad(new EdgeLocation(hexTop, EdgeDirection.SouthWest), 3);
    }

    @Before
    public void setup() throws BoardException {
        testBoard = new Board(false, false, false);
    }

//== BOARD GENERATION ==//

    @Test
    public void presetBoard() {

        try {
            testBoard = new Board(false, false, false);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void randomBoard() {

        try {
            testBoard = new Board(true, true, true);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

//== ROBBER PLACEMENT ==//

    @Test
    public void canOnlyPlaceRobberOnEmptyHex(){

        HexLocation original = new HexLocation(0, -2);
        HexLocation center = new HexLocation(0, 0);
        try {
            result = testBoard.canPlayRobber(original);
            assertFalse(result);
            result = testBoard.canPlayRobber(center);
            assertTrue(result);

            testBoard.doPlayRobber(center);

            result = testBoard.canPlayRobber(original);
            assertTrue(result);
            result = testBoard.canPlayRobber(center);
            assertFalse(result);

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

//== BUILDING PLACEMENT ==//

    @Test
    public void cannotBuildIsolatedBuilding() {

        try {
            result = testBoard.canBuildSettlement(vertmidNW, 0);
            assertFalse(result);

            result = testBoard.canBuildCity(vertmidNW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void cannotBuiltSettlementOnSettlement() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);

            result = testBoard.canBuildSettlement(vertmidNW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void cannotBuildNeighborSettlement() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildSettlement(vertmidW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void cannotBuildSettlementByOnlyOpponentRoad () {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);

            result = testBoard.canBuildSettlement(vertleftNW, 1);
            assertFalse(result);

            bypassInitialSetup();

            result = testBoard.canBuildSettlement(vertleftNW, 1);
            assertFalse(result);

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void canBuildSettlementConnectedByRoads() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);

            result = testBoard.canBuildSettlement(vertleftNW, 0);
            assertTrue(result);

            bypassInitialSetup();

            result = testBoard.canBuildSettlement(vertleftNW, 0);
            assertTrue(result);

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void cannotBuildCityOnACity() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);
            testBoard.doBuildSettlement(vertleftNW, 0);
            testBoard.doBuildCity(vertleftNW, 0);

            result = testBoard.canBuildCity(vertleftNW, 0);
            assertFalse(result);

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void cannotBuildCityWithoutSettlement() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);

            result = testBoard.canBuildCity(vertleftNW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void canBuildCityOnlyOnOwnedSettlement() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);
            testBoard.doBuildSettlement(vertleftNW, 0);

            result = testBoard.canBuildCity(vertleftNW, 0);
            assertTrue(result);
            result = testBoard.canBuildCity(vertmidNW, 1);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

//== ROAD PLACEMENT ==//

    @Test
    public void cannotBuildRoadOnRoad() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);
            result = testBoard.canBuildRoad(edgemidNW, 1);
            assertFalse(result);

            bypassInitialSetup();

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);
            result = testBoard.canBuildRoad(edgemidNW, 1);
            assertFalse(result);

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void canBuildIsolatedRoadDuringInit() {

        try {
            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertTrue(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void cannotBuildRoadBySettlementDuringInit() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void cannotBuildRoadByNeighborSettlementDuringInit() {
        try {
            testBoard.doBuildSettlement(vertmidE, 0);
            testBoard.doBuildSettlement(vertmidW, 0);

            result = testBoard.canBuildRoad(edgemidN, 0);
            assertFalse(result);
            result = testBoard.canBuildRoad(edgemidS, 1);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void canBuildRoadByOnlyOpponentRoadDuringInit() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildRoad(edgeleftN, 1);
            assertTrue(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void cannotBuildIsolatedRoadDuringMain() {

        try {
            bypassInitialSetup();

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void cannotBuildRoadWithoutNeighborRoadMain() {

        try {
            bypassInitialSetup();
            testBoard.doBuildSettlement(vertmidNW, 0);

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);

            testBoard.doBuildRoad(edgemidN, 0);

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertTrue(result);

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void cannotBuildRoadByOnlyOpponentRoadDuringMain() {

        try {
            bypassInitialSetup();
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildRoad(edgeleftN, 1);
            assertFalse(result);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void canBuildRoadByOwnedRoad() {

        try {
            testBoard.doBuildSettlement(vertleftNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);
            testBoard.doBuildRoad(edgemidN, 1);

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertTrue(result);

            bypassInitialSetup();

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertTrue(result);

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

}
