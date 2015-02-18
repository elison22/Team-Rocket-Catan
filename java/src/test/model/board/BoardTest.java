package test.model.board;

import model.board.Board;
import model.board.BoardException;
import static org.junit.Assert.*;

import org.junit.*;

import shared.definitions.HexType;
import shared.definitions.PortType;
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
    EdgeLocation edgemidNE = new EdgeLocation(hexMID, EdgeDirection.NorthEast);
    EdgeLocation edgemidS = new EdgeLocation(hexMID, EdgeDirection.South);
    EdgeLocation edgemidSW = new EdgeLocation(hexMID, EdgeDirection.SouthWest);
    EdgeLocation edgeleftN = new EdgeLocation(hexSW, EdgeDirection.North);

    /** Automatically puts 10 roads on the board. This allows canDo's to run as if initial placement is finished. */
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

    /** Creates a new, non-random board. */
    @Before
    public void setup() throws BoardException {
        testBoard = new Board(false, false, false);
    }

//== BOARD GENERATION ==//

    /** Passes if the standard Board constructor works with no exception. */
    @Test
    public void presetBoard() {

        try {
            testBoard = new Board(false, false, false);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if the randomized Board constructor works with no exception. */
    @Test
    public void randomBoard() {

        try {
            testBoard = new Board(true, true, true);
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if Board.equals successfully verifies equality */
    @Test
    public void testBoardEqualsMethod() throws BoardException {
        Board test1 = new Board(false, false, false);
        Board test2 = new Board(false, false, false);
        assertTrue(test1.equals(test2));                // Two identical Board objects return true
        test2 = new Board(true, true, true);
        assertFalse(test1.equals(test2));               // Two different Board objects return false
    }

//== ROBBER PLACEMENT ==//

    /** Passes if the robber actually starts on the desert. */
    @Test
    public void robberStartsOnDesert() throws BoardException {

        assertTrue(testBoard.getRobberTile().getType() == HexType.DESERT);      // The robber starts on the desert in standard board
        testBoard = new Board(true, true, true);
        assertTrue(testBoard.getRobberTile().getType() == HexType.DESERT);      // The robber starts on the desert in random board
    }

    /** Passes if you can place the robber only on an empty hex */
    @Test
    public void canOnlyPlaceRobberOnEmptyHex() {

        HexLocation original = new HexLocation(0, -2);
        HexLocation center = new HexLocation(0, 0);
        try {
            result = testBoard.canPlayRobber(original);
            assertFalse(result);                                // Cannot place the robber on itself (the initial hex)
            result = testBoard.canPlayRobber(center);
            assertTrue(result);                                 // Can place robber on a different hex

            testBoard.doPlayRobber(center);                     // Move the robber

            result = testBoard.canPlayRobber(original);
            assertTrue(result);                                 // Cannot place robber on its new hex again
            result = testBoard.canPlayRobber(center);
            assertFalse(result);                                // Can now place robber on the initial hex

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

//== PORTS & MARITIME TRADE ==//

    /** Passes if you cannot maritime trade on a port that has no neighbor settlement */
    @Test
    public void cannotMaritimeTradeIfNoPortsOwned() {

        result = testBoard.canPlayMaritimeTrade(0, PortType.THREE_FOR_ONE);
        assertFalse(result);                                                    // nobody owns this port
    }

    /** Passes if you cannot maritime trade on a port that has an opponents settlement */
    @Test
    public void cannotMaritimeTradeIfOtherOwner() {
        try{
            VertexLocation portLoc = new VertexLocation(hexBottom, VertexDirection.SouthEast);
            testBoard.doBuildSettlement(portLoc, 1);
            result = testBoard.canPlayMaritimeTrade(0, PortType.THREE_FOR_ONE);
            assertFalse(result);                                                // an opponent owns this port
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    /** Passes if you cannot maritime trade on a port of a different type than one you own */
    @Test
    public void cannotMaritimeTradeWithWrongPort() {

        try {
            testBoard.doBuildSettlement(new VertexLocation(hexBottom, VertexDirection.SouthWest), 0);
            result = testBoard.canPlayMaritimeTrade(0, PortType.BRICK);
            assertFalse(result);                                                // you own a different port
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if you can maritime trade on a port you own */
    @Test
    public void canMaritimeTradeWithOwnedPort() {

        try{
            VertexLocation portLoc = new VertexLocation(hexBottom, VertexDirection.SouthEast);
            testBoard.doBuildSettlement(portLoc, 0);
            result = testBoard.canPlayMaritimeTrade(0, PortType.THREE_FOR_ONE);
            assertTrue(result);                                                 // you own this port
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

//== BUILDING PLACEMENT ==//

    /** Passes if you cannot build a building that's isolated from other pieces */
    @Test
    public void cannotBuildIsolatedBuilding() {

        try {
            result = testBoard.canBuildSettlement(vertmidNW, 0);
            assertFalse(result);                                    // trying to build an isolated settlement

            result = testBoard.canBuildCity(vertmidNW, 0);
            assertFalse(result);                                    // trying to build an isolated city
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if you cannot build a settlement on another settlement */
    @Test
    public void cannotBuiltSettlementOnSettlement() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);

            result = testBoard.canBuildSettlement(vertmidNW, 0);
            assertFalse(result);                                    //trying to build a settlement on itself
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if you cannot build a settlement next to another settlement */
    @Test
    public void cannotBuildNeighborSettlement() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildSettlement(vertmidW, 0);
            assertFalse(result);                        //trying to build a settlement immediately adjacent one you own

            result = testBoard.canBuildSettlement(vertmidW, 1);
            assertFalse(result);                        //trying to build a settlement immediately adjacent an opponents
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if you cannot build a settlement only by an opponents road (rather than your own road) */
    @Test
    public void cannotBuildSettlementByOnlyOpponentRoad () {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);

            result = testBoard.canBuildSettlement(vertleftNW, 1);
            assertFalse(result);                        //during initial: trying to build a player 1 settlement by player 0 road only

            bypassInitialSetup();

            result = testBoard.canBuildSettlement(vertleftNW, 1);
            assertFalse(result);                        //during main: trying to build a player 1 settlement by player 0 road only

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if you can build a settlement by your own road away from other settlements */
    @Test
    public void canBuildSettlementConnectedByRoads() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);

            result = testBoard.canBuildSettlement(vertleftNW, 0);
            assertTrue(result);                         //during initial: trying to build a valid settlement

            bypassInitialSetup();

            result = testBoard.canBuildSettlement(vertleftNW, 0);
            assertTrue(result);                         //during main: trying to build a valid settlement

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    /** Passes if you cannot build a city on another city */
    @Test
    public void cannotBuildCityOnACity() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);
            testBoard.doBuildSettlement(vertleftNW, 0);
            testBoard.doBuildCity(vertleftNW, 0);

            result = testBoard.canBuildCity(vertleftNW, 0);
            assertFalse(result);                        //trying to build a city on another city that you own

            result = testBoard.canBuildCity(vertleftNW, 1);
            assertFalse(result);                        //trying to build a city on another city that an opponent owns

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if you cannot build a city without a settlement */
    @Test
    public void cannotBuildCityWithoutSettlement() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);

            result = testBoard.canBuildCity(vertleftNW, 0);
            assertFalse(result);                        //trying to build a city where a settlement could be played
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if you can build a city ONLY on a settlement that you own */
    @Test
    public void canBuildCityOnlyOnOwnedSettlement() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);
            testBoard.doBuildSettlement(vertleftNW, 0);

            result = testBoard.canBuildCity(vertleftNW, 0);
            assertTrue(result);                             //trying to build a city on a settlement that you own
            result = testBoard.canBuildCity(vertmidNW, 1);
            assertFalse(result);                            //trying to build a city on another player's settlement
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

//== ROAD PLACEMENT ==//

    /** Passes if you cannot build a road on another road */
    @Test
    public void cannotBuildRoadOnRoad() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);                            //during initial: cannot build a road on another owned road
            result = testBoard.canBuildRoad(edgemidNW, 1);
            assertFalse(result);                            //during initial: cannot build a road on an opponent road

            bypassInitialSetup();

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);                            //during main: cannot build a road on another owned road
            result = testBoard.canBuildRoad(edgemidNW, 1);
            assertFalse(result);                            //during main: cannot build a road on an opponent road

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if you can build a completely isolated road during initial setup */
    @Test
    public void canBuildIsolatedRoadDuringInit() {

        try {
            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertTrue(result);                             //during initial: trying to build an isolated road
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    /** Passes if you cannot build a road next to another settlement during setup */
    @Test
    public void cannotBuildRoadBySettlementDuringInit() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);                            //during initial: trying to build
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    /**
     * Passes if you cannot build a road where other settlements are too close to
     * allow you to build on either of the vertices on the ends of this road
     */
    @Test
    public void cannotBuildRoadByFarNeighborSettlementDuringInit() {
        try {
            testBoard.doBuildSettlement(vertmidE, 0);       //settlement on the East vertex
            testBoard.doBuildSettlement(vertmidW, 3);       //settlement on the West vertex

            result = testBoard.canBuildRoad(edgemidN, 0);
            assertFalse(result);                            //during initial: trying to build where the road is too near to other settlements
            result = testBoard.canBuildRoad(edgemidS, 1);
            assertFalse(result);                            //during initial: same as before but with a different player
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    /** Passes of you can build a road by only an opponent road during initial placement */
    @Test
    public void canBuildRoadByOnlyOpponentRoadDuringInit() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildRoad(edgeleftN, 1);
            assertTrue(result);                             //during initial: trying to build by an opponents road only
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if you cannot build a loner road during main game play */
    @Test
    public void cannotBuildIsolatedRoadDuringMain() {

        try {
            bypassInitialSetup();

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertFalse(result);                            //during main: trying to build a road away from other pieces
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    /** Passes if you cannot build a road by only an opponent road during main game play*/
    @Test
    public void cannotBuildRoadByOnlyOpponentRoadDuringMain() {

        try {
            bypassInitialSetup();
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidNW, 0);

            result = testBoard.canBuildRoad(edgeleftN, 1);
            assertFalse(result);                            //during main: trying to build a road by only an opponent road
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    /** Passes if you can build a road by another road that you own */
    @Test
    public void canBuildRoadByOwnedRoad() {

        try {
            testBoard.doBuildSettlement(vertleftNW, 0);
            testBoard.doBuildRoad(edgeleftN, 0);
            testBoard.doBuildRoad(edgemidN, 1);

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertTrue(result);                             //during initial: trying to build a road by an owned road

            bypassInitialSetup();

            result = testBoard.canBuildRoad(edgemidNW, 0);
            assertTrue(result);                             //during main: trying to build a road by an owned road

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

//== ROAD BUILD CARD ==//

    @Test
    public void cannotBuildSecondRoadOnRoad() {

        try {
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidN, 0);
            testBoard.doBuildRoad(edgeleftN, 1);

            bypassInitialSetup();

            result = testBoard.canBuildRoad(edgemidNW, edgemidN, 0);
            assertFalse(result);                            // cannot build a road on another owned road
            result = testBoard.canBuildRoad(edgemidNW, edgeleftN, 0);
            assertFalse(result);                            // cannot build a road on an opponent road
            result = testBoard.canBuildRoad(edgemidNW, edgemidN, 1);
            assertFalse(result);                            // cannot build a road on an opponent road

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void cannotBuildIsolatedSecondRoad() {

        try {
            bypassInitialSetup();
            testBoard.doBuildRoad(edgemidN, 0);

            result = testBoard.canBuildRoad(edgemidNE, edgemidSW, 0);
            assertFalse(result);                            // trying to build a road away from other pieces
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void cannotBuildSecondRoadByOnlyOpponentRoad() {

        try {
            bypassInitialSetup();
            testBoard.doBuildSettlement(vertmidNW, 0);
            testBoard.doBuildRoad(edgemidN, 0);
            testBoard.doBuildRoad(edgeleftN, 1);

            result = testBoard.canBuildRoad(edgemidNE, edgemidSW, 0);
            assertFalse(result);                            // trying to build a road by only an opponent road
        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }

    }

    @Test
    public void canBuildSecondRoadByAnyOwnedRoad() {

        try {
            testBoard.doBuildSettlement(vertleftNW, 1);
            testBoard.doBuildRoad(edgeleftN, 1);
            testBoard.doBuildRoad(edgemidN, 0);

            bypassInitialSetup();

            result = testBoard.canBuildRoad(edgemidNW, edgemidNE, 0);
            assertTrue(result);                             // trying to build a road by an owned road
            result = testBoard.canBuildRoad(edgemidNE, edgemidNW, 0);
            assertTrue(result);                             // trying to build a road by an owned road
            result = testBoard.canBuildRoad(edgemidNW, edgemidSW, 1);
            assertTrue(result);                             // trying to build a road by an owned road

        } catch (BoardException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    public void cannotBuildSecondRoadOnFirstRoad() {

        try{
            bypassInitialSetup();
            testBoard.doBuildRoad(edgemidN, 0);

            result = testBoard.canBuildRoad(edgemidNW, edgemidNW, 0);
            assertFalse(result);

        } catch (BoardException e) {
            assertFalse(true);
        }

    }

    @Test
    public void canBuildSecondRoadByOnlyFirstRoad() {

        try {
            bypassInitialSetup();
            testBoard.doBuildRoad(edgeleftN, 0);
            testBoard.doBuildRoad(edgemidNE, 1);

            result = testBoard.canBuildRoad(edgemidNW, edgemidN, 0);
            assertTrue(result);
            result = testBoard.canBuildRoad(edgemidSW, edgemidS, 0);
            assertTrue(result);
        } catch (BoardException e) {
            assertFalse(true);
        }

    }

}
