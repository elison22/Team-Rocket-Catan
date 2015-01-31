package model.board;

import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.locations.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by brandt on 1/17/15.
 * <p>
 * This class contains all the data that describes the Catan map. It also contains methods
 * needed to update the information in this Board. The Board keeps track of the HexTiles,
 * Buildings, Roads, and the robber.
 * </p>
 */
public class Board {

    /** The HexTile objects that make up this Board */
    private TreeMap<HexLocation, HexTile> tiles = new TreeMap<HexLocation, HexTile>();
    /** The CITY and SETTLEMENT type Constructable objects on this Board */
    private TreeMap<VertexLocation, Constructable> buildings = new TreeMap<VertexLocation, Constructable>();
    /** The ROAD type Constructable objects on this Board */
    private TreeMap<EdgeLocation, Constructable> roads = new TreeMap<EdgeLocation, Constructable>();
    /** The Port vertices on this Board */
    private TreeMap<VertexLocation, Port> ports = new TreeMap<VertexLocation, Port>();
    /** The HexLocation that represents the robber */
    private HexLocation robber;

    /**
     * Creates a new Board object with HexTile objects for the whole board including
     * diceNums, and actual HexLocations. It also initializes the robbers location to
     * the Desert, which has a diceNum of 0.
     * @param randomTileTypes Whether or not the tiles resource types should be randomized.
     * @param randomDiceNums Whether or not the diceNums should be randomized.
     * @throws BoardException Thrown only when there's a problem with the code that initializes this object.
     */
    public Board(boolean randomTileTypes, boolean randomDiceNums, boolean randomPorts) throws BoardException {

        Random randomTypes = new Random();
        Random randomNums = new Random();
        int nextTypeRand;
        int nextNumRand;
        int toSubtract = 0;
        HexType nextType;

        ArrayList<HexType> typeSetupArray = new ArrayList<HexType>();
        typeSetupArray.add(HexType.DESERT);
        for(int i = 0; i < 3; i++) typeSetupArray.add(HexType.BRICK);
        for(int i = 0; i < 3; i++) typeSetupArray.add(HexType.ORE);
        for(int i = 0; i < 4; i++) typeSetupArray.add(HexType.WHEAT);
        for(int i = 0; i < 4; i++) typeSetupArray.add(HexType.WOOD);
        for(int i = 0; i < 4; i++) typeSetupArray.add(HexType.SHEEP);

        int[] diceNumSetupArray = {5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11};
        int[] x = {0, 1, 2, 2, 2, 1, 0, -1, -2, -2 ,-2, -1, 0, 1, 1, 0, -1, -1, 0};
        int[] y = {2, 1, 0, -1, -2, -2, -2, -1, 0, 1, 2, 2, 1, 0, -1, -1, 0, 1, 0};

        //Beginning for random types but set dice numbers
//        for(int i = 0; i < 19; i++) {
//            nextTypeRand = randomTypes.nextInt(typeSetupArray.size());
//            nextType = typeSetupArray.get(nextTypeRand);
//            tiles.add(
//                    new HexTile(
//                            nextType,
//                            nextType != HexType.DESERT ? diceNumSetupArray[i - toSubtract] : setRobber(toSubtract++, x[i], y[i]),
//                            new HexLocation(x[i], y[i])
//                    ));
//            typeSetupArray.remove(nextTypeRand);
//        }
        //End for random types but set dice numbers
    }

//    /**
//     * This is called in the constructor to simultaneously set the diceNum of a
//     * HexTile and assign the robber to that same HexTile.
//     * @param diceNum The diceNum for the HexTile to receive the robber.
//     * @param x The x location for the robber.
//     * @param y The y location for the robber.
//     * @return The diceNum for the HexTile to receive the robber.
//     * @throws BoardException Thrown when the x or y values are outside the range -2 to 2
//     */
//    private int setRobber(int diceNum, int x, int y) throws BoardException {
//        setRobber(x, y);
//        return diceNum;
//    }

    /**
     * Set the robber's new location using x/y coordinates
     * @param x The robber's new x location
     * @param y The robber's new y location
     * @throws BoardException Thrown when the x or y values are outside the range -2 to 2
     */
    public void setRobber(int x, int y) throws BoardException {
        //TODO: implement the check to make sure the robber is actually on the board
        robber = new HexLocation(x, y);
    }

    /**
     * Set the robber's new location using a HexLocation object
     * @param newLocation The HexLocation for the robber's new location
     * @throws BoardException Thrown when the x or y values are outside the range -2 to 2
     */
    public void setRobber(HexLocation newLocation) throws BoardException {
        //TODO: implement the check to make sure the robber is actually on the board
        robber = newLocation;
    }

    /**
     * Determines whether or not a player can build a Settlement-type Constructable on this
     * location. The conditions are that no Constructable is already present at the location
     * param or any of its neighbors.
     * @param location Where to check for availability.
     * @return True if the vertex is available for a Settlement.
     * @throws BoardException Thrown if the location param is passed in null.
     */
    public boolean canBuildSettlement(VertexLocation location) throws BoardException {
        if (location == null) throw new BoardException("Param location cannot be null.");
        VertexLocation normalized = location.getNormalizedLocation();
        return buildings.get(normalized) == null && !hasNeighborBuilding(normalized);
    }

    /**
     * Determines whether or not a player can build a City-type Constructable on this
     * location. The conditions are that there is already a Settlement-type Constructable
     * present at the location param that belongs to the player with index owner.
     * @param location Where to check for availability.
     * @param owner The owner of the VertexLocation to be tested. Must be in the range 0 to 3.
     * @return True if the vertex is available for a City.
     */
    public boolean canBuildCity(VertexLocation location, int owner) throws BoardException {
        if (owner < 0 || owner > 3) throw new BoardException("The param owner must be between 0 and 3.");
        if (location == null) throw new BoardException("Param location cannot be null.");
        VertexLocation normalized = location.getNormalizedLocation();
        if (buildings.get(normalized) == null) return false;
        if (!buildings.get(normalized).isSettlement()) return false;
        if (buildings.get(normalized).getOwner()!=owner) return false;
        return true;
    }

    /**
     * Determines whether or not a player can build a Road on this location. The
     * conditions are that no Road is already present at the location param or any
     * of its neighbors. Verifies with both adjacent HexTiles.
     * @param location Where to verify for availability for a road.
     * @param owner The index of the player who wants to build a road.
     * @return True if the edge is available for a road.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range -1 to 3. Also thrown if the location param is passed in null.
     */
    public boolean canBuildRoad(EdgeLocation location, int owner) throws BoardException {
        if (owner < -1 || owner > 3) throw new BoardException("Param owner must be in the range -1 to 3.");
        if (location == null) throw new BoardException("Param location cannot be null.");
        EdgeLocation normalized = location.getNormalizedLocation();
        if (roads.get(normalized) != null) return false;
        if (!hasNeighborRoad(location, owner) && !hasNeighborBuilding(normalized, owner)) return false;
        return true;
    }

    /**
     * Inserts a new Settlement-type Constructable object into the buildings map with the
     * key set to the normalized location param and the owner field set to the owner param.
     * @param location The location where the settlement should be built.
     * @param owner The owner of the settlement to be built.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public void doBuildSettlement(VertexLocation location, int owner) throws BoardException {
        if (owner < 0 || owner > 3) throw new BoardException("Param owner must be in the range 0 to 3.");
        if (location == null) throw new BoardException("Param location cannot be null.");
        Constructable settlement = new Constructable(PieceType.SETTLEMENT, owner);
        buildings.put(location.getNormalizedLocation(), settlement);
    }

    /**
     * Inserts a new City-type Constructable object into the buildings map with the
     * key set to the normalized location param and the owner field set to the owner param.
     * The canBuildSettlement function should be called prior to using this function.
     * @param location The location where the city should be built.
     * @param owner The owner of the city to be built.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public void doBuildCity(VertexLocation location, int owner) throws BoardException {
        if (owner < 0 || owner > 3) throw new BoardException("Param owner must be in the range 0 to 3.");
        if (location == null) throw new BoardException("Param location cannot be null.");
        Constructable city = new Constructable(PieceType.CITY, owner);
        buildings.put(location.getNormalizedLocation(), city);
    }

    /**
     * Inserts a new Road-type Constructable object into the roads map with the
     * key set to the normalized location param and the owner field set to the owner param.
     * The canBuildCity function should be called prior to using this function.
     * @param location The location where the road should be built.
     * @param owner The owner of the road to be built.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public void doBuildRoad(EdgeLocation location, int owner) throws BoardException {
        if (owner < 0 || owner > 3) throw new BoardException("Param owner must be in the range 0 to 3.");
        if (location == null) throw new BoardException("Param location cannot be null.");
        Constructable road = new Constructable(PieceType.ROAD, owner);
        roads.put(location.getNormalizedLocation(), road);
    }

    /**
     * Check if there are any buildings immediately next to this VertexLocation.
     * @param location The location around which to check.
     * @return True if there are neighbor buildings.
     * @throws BoardException Thrown if the location param is passed in null.
     */
    private boolean hasNeighborBuilding(VertexLocation location) throws BoardException {
        if (location == null) throw new BoardException("Param location cannot be null.");
        VertexLocation normalized = location.getNormalizedLocation();

        if (normalized.getDir() == VertexDirection.NorthWest) {

            VertexLocation northwest = new VertexLocation(
                    normalized.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest),
                    VertexDirection.NorthEast);
            VertexLocation southwest = new VertexLocation(
                    normalized.getHexLoc().getNeighborLoc(EdgeDirection.SouthWest),
                    VertexDirection.NorthEast);
            VertexLocation east = new VertexLocation(
                    normalized.getHexLoc(),
                    VertexDirection.NorthEast);

            if (
                    buildings.get(east) == null &&
                    buildings.get(northwest) == null &&
                    buildings.get(southwest) == null )
                return true;
            else return false;
        }

        else {

            VertexLocation northeast = new VertexLocation(
                    normalized.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast),
                    VertexDirection.NorthWest);
            VertexLocation southeast = new VertexLocation(
                    normalized.getHexLoc().getNeighborLoc(EdgeDirection.SouthEast),
                    VertexDirection.NorthWest);
            VertexLocation west = new VertexLocation(
                    normalized.getHexLoc(),
                    VertexDirection.NorthWest);

            if (
                    buildings.get(west) == null &&
                    buildings.get(northeast) == null &&
                    buildings.get(southeast) == null )
                return true;
            else return false;
        }

    }

    /**
     * Check if there are any buildings immediately next to this EdgeLocation, and if
     * there are, check if their owner field matches the owner parameter.
     * @param location The EdgeLocation to check for neighbor buildings.
     * @param owner The building owner to check for. Must be in the range -1 to 3.
     *              If -1 is passed in, any building may count a match.
     * @return True if there is a neighbor building with the field owner matching the param owner.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range -1 to 3. Also thrown if the location param is passed in null.
     */
    private boolean hasNeighborBuilding(EdgeLocation location, int owner) throws BoardException {
        if (owner < -1 || owner > 3) throw new BoardException("Param owner must be in the range -1 to 3.");
        if (location == null) throw new BoardException("Param location cannot be null.");
        VertexLocation clockwise = location.getClockwiseVertex().getNormalizedLocation();
        VertexLocation counterclockwise = location.getCounterClockwiseVertex().getNormalizedLocation();
        if (buildings.get(clockwise)!=null) {
            if (owner == -1) return true;
            if (buildings.get(clockwise).getOwner() == owner) return true;
        }
        if (buildings.get(counterclockwise)!=null) {
            if (owner == -1) return true;
            if (buildings.get(counterclockwise).getOwner() == owner) return true;
        }
        return false;
    }

    /**
     * Check if there are any roads immediately next to this EdgeLocation, and if there
     * are, check if their owner field matches the owner parameter.
     * @param location The EdgeLocation to check for neighbor roads.
     * @param owner The building owner to check for. Must be in the range -1 to 3.
     *              If -1 is passed in, any building may count a match.
     * @return True if there is a neighbor road with the field owner matching the param owner.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range -1 to 3. Also thrown if the location param is passed in null.
     */
    private boolean hasNeighborRoad(EdgeLocation location, int owner) throws BoardException {
        if (owner < -1 || owner > 3) throw new BoardException("Param owner must be in the range -1 to 3.");
        if (location == null) throw new BoardException("Param location cannot be null.");
        EdgeLocation clockwise = location.getClockwiseEdge().getNormalizedLocation();
        EdgeLocation counterclockwise = location.getCounterClockwiseEdge().getNormalizedLocation();
        if (roads.get(clockwise)!=null) {
            if (owner == -1) return true;
            if (roads.get(clockwise).getOwner() == owner) return true;
        }
        if (roads.get(counterclockwise)!=null) {
            if (owner == -1) return true;
            if (roads.get(clockwise).getOwner() == owner) return true;
        }
        EdgeLocation opposite = new EdgeLocation(location.getHexLoc().getNeighborLoc(location.getDir()),location.getDir().getOppositeDirection());
        clockwise = opposite.getClockwiseEdge().getNormalizedLocation();
        counterclockwise = opposite.getCounterClockwiseEdge().getNormalizedLocation();
        if (roads.get(clockwise)!=null) {
            if (owner == -1) return true;
            if (roads.get(clockwise).getOwner() == owner) return true;
        }
        if (roads.get(counterclockwise)!=null) {
            if (owner == -1) return true;
            if (roads.get(clockwise).getOwner() == owner) return true;
        }
        return false;
    }

}
