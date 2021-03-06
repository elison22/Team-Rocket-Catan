package model.sboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import serializer.json.JsonHex;
import serializer.json.JsonMap;
import serializer.json.JsonPort;
import serializer.json.JsonRoad;
import serializer.json.JsonVertexObject;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * Created by brandt on 1/17/15.
 * <p>
 * This class contains all the data that describes the Catan map. It also contains methods
 * needed to update the information in this Board. The Board keeps track of the HexTiles,
 * Buildings, Roads, and the robber.
 * </p>
 */
public class ServerBoard {

    /** The HexTile objects that make up this Board */
    private HashMap<HexLocation, ServerHexTile> tiles = new HashMap<HexLocation, ServerHexTile>();
    /** The CITY and SETTLEMENT type Constructable objects on this Board */
    private HashMap<VertexLocation, ServerConstructable> buildings = new HashMap<VertexLocation, ServerConstructable>();
    /** The ROAD type Constructable objects on this Board */
    private HashMap<EdgeLocation, ServerConstructable> roads = new HashMap<EdgeLocation, ServerConstructable>();
    /** The port vertices on this Board */
    private HashMap<VertexLocation, PortType> ports = new HashMap<VertexLocation, PortType>();
    private HashMap<EdgeLocation, PortType> portTypes = new HashMap<EdgeLocation, PortType>();
    /** The HexLocation that represents the robber */
    private HexLocation robber;
    
    public ServerBoard(ServerBoard original)
    {
    	this.tiles = original.getTiles();
    	this.robber = new HexLocation(original.getRobberLoc().getX(), original.getRobberLoc().getY());
    	this.ports = original.getPorts();
    	this.portTypes = original.getPortTypes();
    }
    
    /**
     * Creates a new Board object with HexTile objects for the whole board including
     * diceNums, and actual HexLocations. It also initializes the robbers location to
     * the Desert, which has a diceNum of 0.
     * @param randomTileTypes Whether or not the tiles resource types should be randomized.
     * @param randomDiceNums Whether or not the diceNums should be randomized.
     * @param randomPortTypes Whether of not the port types should be randomized.
     * @throws ServerBoardException Thrown only when there's a problem with the code that initializes this object.
     */
    public ServerBoard(boolean randomTileTypes, boolean randomDiceNums, boolean randomPortTypes, int seed) throws ServerBoardException {

        Random rand = new Random(seed);
        int typeIndex = 0;
        int numIndex = 0;
        int portIndex = 0;
        int yStart = 2;
        int yEnd = 0;

        ArrayList<HexType> hexTypeArray = new ArrayList<HexType>();
        ArrayList<Integer> numArray = new ArrayList<Integer>();
        ArrayList<PortType> portTypeArray = new ArrayList<PortType>();
        ArrayList<EdgeLocation> portLocArray = new ArrayList<EdgeLocation>();

        hexTypeArray.add(HexType.WOOD);
        hexTypeArray.add(HexType.WHEAT);
        hexTypeArray.add(HexType.ORE);
        hexTypeArray.add(HexType.ORE);
        hexTypeArray.add(HexType.SHEEP);
        hexTypeArray.add(HexType.SHEEP);
        hexTypeArray.add(HexType.BRICK);
        hexTypeArray.add(HexType.WHEAT);
        hexTypeArray.add(HexType.WOOD);
        hexTypeArray.add(HexType.WHEAT);
        hexTypeArray.add(HexType.WOOD);
        hexTypeArray.add(HexType.DESERT);
        hexTypeArray.add(HexType.SHEEP);
        hexTypeArray.add(HexType.BRICK);
        hexTypeArray.add(HexType.ORE);
        hexTypeArray.add(HexType.BRICK);
        hexTypeArray.add(HexType.WHEAT);
        hexTypeArray.add(HexType.SHEEP);
        hexTypeArray.add(HexType.WOOD);

        numArray.add(6);
        numArray.add(2);
        numArray.add(5);
        numArray.add(3);
        numArray.add(9);
        numArray.add(10);
        numArray.add(8);
        numArray.add(8);
        numArray.add(4);
        numArray.add(11);
        numArray.add(3);
        numArray.add(10);
        numArray.add(5);
        numArray.add(9);
        numArray.add(4);
        numArray.add(6);
        numArray.add(12);
        numArray.add(11);

        portTypeArray.add(PortType.THREE_FOR_ONE);
        portTypeArray.add(PortType.THREE_FOR_ONE);
        portTypeArray.add(PortType.SHEEP);
        portTypeArray.add(PortType.THREE_FOR_ONE);
        portTypeArray.add(PortType.ORE);
        portTypeArray.add(PortType.WHEAT);
        portTypeArray.add(PortType.THREE_FOR_ONE);
        portTypeArray.add(PortType.WOOD);
        portTypeArray.add(PortType.BRICK);

        portLocArray.add(new EdgeLocation(new HexLocation(0, 2), EdgeDirection.South));
        portLocArray.add(new EdgeLocation(new HexLocation(1, 1), EdgeDirection.SouthEast));
        portLocArray.add(new EdgeLocation(new HexLocation(2, -1), EdgeDirection.SouthEast));
        portLocArray.add(new EdgeLocation(new HexLocation(2, -2), EdgeDirection.NorthEast));
        portLocArray.add(new EdgeLocation(new HexLocation(1, -2), EdgeDirection.North));
        portLocArray.add(new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.North));
        portLocArray.add(new EdgeLocation(new HexLocation(-2, 0), EdgeDirection.NorthWest));
        portLocArray.add(new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.SouthWest));
        portLocArray.add(new EdgeLocation(new HexLocation(-1, 2), EdgeDirection.SouthWest));

        for (int i = -2; i <= 2; i++) {
            for (int j = yStart; j >= yEnd; j--) {
                HexLocation newLoc = new HexLocation(i, j);
                ServerHexTile newTile;
                if (randomTileTypes) typeIndex = rand.nextInt(hexTypeArray.size());
                newTile = new ServerHexTile(hexTypeArray.get(typeIndex));
                hexTypeArray.remove(typeIndex);
                if (newTile.getType() == HexType.DESERT) {
                    tiles.put(newLoc, newTile);
                    robber = newLoc;
                    continue;
                }
                if (randomDiceNums) numIndex = rand.nextInt(numArray.size());
                newTile.setDiceNum(numArray.get(numIndex));
                numArray.remove(numIndex);
                tiles.put(newLoc, newTile);
            }
            if (yEnd == -2) yStart--;
            else yEnd--;
        }

        for (EdgeLocation location : portLocArray) {
            if (randomPortTypes) portIndex = rand.nextInt(portTypeArray.size());
            portTypes.put(location.getNormalizedLocation(), portTypeArray.get(portIndex));
            buildPortPair(location, portTypeArray.get(portIndex));
            portTypeArray.remove(portIndex);
        }

    }

    /**
     * Creates a new Board object from the de-serialized json to replace the old Board object.
     * @param newMap A de-serialized json object.
     * @throws ServerBoardException Thrown if newMap is null or if a HexTile's diceNum
     * is outside the range 0 to 12.
     */
    
    public ServerBoard(JsonMap newMap) throws ServerBoardException {

        if (newMap == null) throw new ServerBoardException("Param newMap cannot be null.");
        HexLocation hexloc;
        VertexLocation vertloc;
        ServerConstructable piece;
        EdgeLocation edge;
        ServerHexTile tile;

        // Update the hexes
        for (JsonHex hex : newMap.getHexes()) {
            hexloc = new HexLocation(hex.getLocation().getX(), hex.getLocation().getY());
            tile = new ServerHexTile(HexType.convert(hex.getResource()),hex.getNumber());
            tiles.put(hexloc, tile);
        }

        // Update the ports
        for (JsonPort doublePort : newMap.getPorts()) {
            hexloc = new HexLocation(doublePort.getLocation().getX(), doublePort.getLocation().getY());
            edge = new EdgeLocation(hexloc, EdgeDirection.convert(doublePort.getDirection()));
            if(doublePort.getRatio() == 3) {
                buildPortPair(edge, PortType.THREE_FOR_ONE);
                portTypes.put(edge.getNormalizedLocation(), PortType.THREE_FOR_ONE);
            }
            else {
                buildPortPair(edge, PortType.convert(doublePort.getResource()));
                portTypes.put(edge.getNormalizedLocation(), PortType.convert(doublePort.getResource()));
            }
        }

        // Update the settlements
        for (JsonVertexObject settlement : newMap.getSettlements()) {
            hexloc = new HexLocation(settlement.getLocation().getX(), settlement.getLocation().getY());
            vertloc = new VertexLocation(hexloc, VertexDirection.convert(settlement.getLocation().getDirection()));
            piece = new ServerConstructable(PieceType.SETTLEMENT, settlement.getOwner());
            buildings.put(vertloc.getNormalizedLocation(), piece);
        }

        // Update the cities
        for (JsonVertexObject city : newMap.getCities()) {
            hexloc = new HexLocation(city.getLocation().getX(), city.getLocation().getY());
            vertloc = new VertexLocation(hexloc, VertexDirection.convert(city.getLocation().getDirection()));
            piece = new ServerConstructable(PieceType.CITY, city.getOwner());
            buildings.put(vertloc.getNormalizedLocation(), piece);
        }

        // Update the roads
        for (JsonRoad road : newMap.getRoads()) {
            hexloc = new HexLocation(road.getLocation().getX(), road.getLocation().getY());
            edge = new EdgeLocation(hexloc, EdgeDirection.convert(road.getLocation().getDirection()));
            piece = new ServerConstructable(PieceType.ROAD, road.getOwner());
            roads.put(edge.getNormalizedLocation(), piece);
        }

        // Update the robber
        robber = new HexLocation(newMap.getRobber().getX(), newMap.getRobber().getY());
    }

    /**
     * Determines whether or not the robber can be moved to the location
     * represented by newLocation.
     * @param newLocation The desired new location of the robber.
     * @return True if the robber can be placed on newLocation.
     */
    public boolean canPlayRobber(HexLocation newLocation) throws ServerBoardException {
        if (newLocation == null) throw new ServerBoardException("Param newLocation cannot be null.");
        if (!tiles.containsKey(newLocation)) return false;
        return !robber.equals(newLocation);
    }

    /**
     * Set the robber's new location using a HexLocation object
     * @param newLocation The HexLocation for the robber's new location
     * @throws ServerBoardException Thrown when the newLocation param represent a location that isn't on the board.
     */
    public void doPlayRobber(HexLocation newLocation) throws ServerBoardException {
        if (!tiles.containsKey(newLocation)) throw new ServerBoardException("Attempted to place the robber off the board.");
        robber = newLocation;
    }

    /**
     * Get the robbers hex. For testing.
     * @return The HexTile that the robber is on.
     */
    public ServerHexTile getRobberTile() {
        return tiles.get(robber);
    }

    /**...*/
    public HexLocation getRobberLoc() { return robber; }

    public HashSet<Integer> getPlayersToRob(HexLocation robber) {
        HashSet<Integer> playersToRob = new HashSet<Integer>();
        checkPlayerIndex(new VertexLocation(robber, VertexDirection.West), playersToRob);
        checkPlayerIndex(new VertexLocation(robber, VertexDirection.NorthWest), playersToRob);
        checkPlayerIndex(new VertexLocation(robber, VertexDirection.NorthEast), playersToRob);
        checkPlayerIndex(new VertexLocation(robber, VertexDirection.East), playersToRob);
        checkPlayerIndex(new VertexLocation(robber, VertexDirection.SouthEast), playersToRob);
        checkPlayerIndex(new VertexLocation(robber, VertexDirection.SouthWest), playersToRob);
        return playersToRob;
    }
    
    public HashMap<HexLocation, ServerHexTile> getTiles() {
		return tiles;
	}

    private void checkPlayerIndex(VertexLocation loc, HashSet<Integer> playerSet) {
        loc = loc.getNormalizedLocation();
        if (buildings.containsKey(loc.getNormalizedLocation()))
            playerSet.add(buildings.get(loc).getOwner());
    }

    /**
     * Determines whether or not a player can build a Settlement-type Constructable on this
     * location. The conditions are that no Constructable is already present at the location
     * param or any of its neighbors. Also, the prospective builder must have a road adjacent
     * to the VertexLocation upon which he wants to build.
     * @param location Where to check for availability.
     * @param owner The owner of the VertexLocation to be tested. Must be in the range 0 to 3.
     * @return True if the vertex is available for a Settlement.
     * @throws ServerBoardException Thrown when attempting to check with an owner index outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public boolean canBuildSettlement(VertexLocation location, int owner) throws ServerBoardException {
        if (owner < 0 || owner > 3) throw new ServerBoardException("Param owner must be in the range 0 to 3");
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
        VertexLocation normalized = location.getNormalizedLocation();
        if (buildings.get(normalized) != null) return false;
        if (hasNeighborBuilding(normalized)) return false;
        if (!hasNeighborRoad(location, owner)) return false;
        return true;
    }

    /**
     * Determines whether or not a player can build a City-type Constructable on this
     * location. The conditions are that there is already a Settlement-type Constructable
     * present at the location param that belongs to the player with index owner.
     * @param location Where to check for availability.
     * @param owner The owner of the VertexLocation to be tested. Must be in the range 0 to 3.
     * @return True if the vertex is available for a City.
     * @throws ServerBoardException Thrown when attempting to check an owner with an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public boolean canBuildCity(VertexLocation location, int owner) throws ServerBoardException {
        if (owner < 0 || owner > 3) throw new ServerBoardException("The param owner must be between 0 and 3.");
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
        VertexLocation normalized = location.getNormalizedLocation();
        if (buildings.get(normalized) == null) return false;
        if (!buildings.get(normalized).isSettlement()) return false;
        if (buildings.get(normalized).getOwner()!=owner) return false;
        return true;
    }

    /**
     * Determines whether or not a player can build a Road on this location. The
     * conditions depend on what phase of the game the builder is in (initial
     * build or building during the main part of the game).
     * @param location Where to verify for availability for a road.
     * @param owner The index of the player who wants to build a road.
     * @return True if the edge is available for a road.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public boolean canBuildRoad(EdgeLocation location, int owner) throws ServerBoardException {
        if (!isEdgeOnBoard(location)) return false;
        if (roads.containsKey(location.getNormalizedLocation())) return false;
        if (roads.size() >= 8) return canBuildNormalRoad(location, owner);
        else return canBuildInitRoad(location, owner);
    }

    /**
     * A canDo function purely to check validity of a potential second road placement
     * after the first has been designated while playing the Road Building dev card.
     * @param firstloc An EdgeLocation representing the first road already decided upon.
     * @param secondloc An EdgeLocation representing the second road to be considered.
     * @param owner The owner of both roads.
     * @return True if this is a valid position for the second road to be placed.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the firstloc or secondloc param is passed in null.
     */
    public boolean canBuildSecondRoad(EdgeLocation firstloc, EdgeLocation secondloc, int owner) throws ServerBoardException {
        if (owner < 0 || owner > 3) throw new ServerBoardException("Param owner must be in the range 0 to 3.");
        if (firstloc == null) throw new ServerBoardException("Param location cannot be null.");
        if (secondloc == null) throw new ServerBoardException("Param location cannot be null.");

        if (!isEdgeOnBoard(secondloc)) return false;
        ServerConstructable road = new ServerConstructable(PieceType.ROAD, owner);
        roads.put(firstloc.getNormalizedLocation(), road);
        boolean toReturn = canBuildNormalRoad(secondloc, owner);
        roads.remove(firstloc.getNormalizedLocation());

        return toReturn;
    }

    /**
     * Determines whether or not a player can build a Road on this location. The
     * conditions are that no Road is already present at the location param and that there
     * is no building immediately adjacent or neighboring the adjacent vertices.
     * @param location Where to verify for availability for a road.
     * @param owner The index of the player who wants to build a road.
     * @return True if the edge is available for a road.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    private boolean canBuildInitRoad(EdgeLocation location, int owner) throws ServerBoardException {
        if (owner < -1 || owner > 3) throw new ServerBoardException("Param owner must be in the range 0 to 3.");
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
        if (roads.get(location.getNormalizedLocation()) != null) return false;
        if (hasNeighborBuilding(location, -1)) return false;
        if (!hasNeighborBuilding(location.getClockwiseVertex())) return true;
        if (!hasNeighborBuilding(location.getCounterClockwiseVertex())) return true;
        return false;
    }

    /**
     * Determines whether or not a player can build a Road on this location. The
     * conditions are that no Road is already present at the location param and that there
     * is a building or road belonging to owner adjacent to the location param.
     * @param location Where to verify for availability for a road.
     * @param owner The index of the player who wants to build a road.
     * @return True if the edge is available for a road.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    private boolean canBuildNormalRoad(EdgeLocation location, int owner) throws ServerBoardException {
        if (owner < 0 || owner > 3) throw new ServerBoardException("Param owner must be in the range 0 to 3.");
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
        EdgeLocation normalized = location.getNormalizedLocation();
        if (roads.get(normalized) != null) return false;
        if (!hasNeighborRoad(location, owner)) return false;
        return true;
    }

    /**
     * Inserts a new Settlement-type Constructable object into the buildings map with the
     * key set to the normalized location param and the owner field set to the owner param.
     * @param location The location where the settlement should be built.
     * @param owner The owner of the settlement to be built.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public void doBuildSettlement(VertexLocation location, int owner) throws ServerBoardException {
        if (owner < 0 || owner > 3) throw new ServerBoardException("Param owner must be in the range 0 to 3.");
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
        ServerConstructable settlement = new ServerConstructable(PieceType.SETTLEMENT, owner);
        VertexLocation normalized = location.getNormalizedLocation();
        buildings.put(normalized, settlement);
    }

    /**
     * Inserts a new City-type Constructable object into the buildings map with the
     * key set to the normalized location param and the owner field set to the owner param.
     * The canBuildSettlement function should be called prior to using this function.
     * @param location The location where the city should be built.
     * @param owner The owner of the city to be built.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public void doBuildCity(VertexLocation location, int owner) throws ServerBoardException {
        if (owner < 0 || owner > 3) throw new ServerBoardException("Param owner must be in the range 0 to 3.");
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
        ServerConstructable city = new ServerConstructable(PieceType.CITY, owner);
        buildings.put(location.getNormalizedLocation(), city);
    }

    /**
     * Inserts a new Road-type Constructable object into the roads map with the
     * key set to the normalized location param and the owner field set to the owner param.
     * The canBuildCity function should be called prior to using this function.
     * @param location The location where the road should be built.
     * @param owner The owner of the road to be built.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public void doBuildRoad(EdgeLocation location, int owner) throws ServerBoardException {
        if (owner < 0 || owner > 3) throw new ServerBoardException("Param owner must be in the range 0 to 3.");
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
        ServerConstructable road = new ServerConstructable(PieceType.ROAD, owner);
        roads.put(location.getNormalizedLocation(), road);
    }

    /**
     * Check if there are any buildings immediately next to this VertexLocation.
     * @param location The location around which to check.
     * @return True if there are neighbor buildings.
     * @throws ServerBoardException Thrown if the location param is passed in null.
     */
    private boolean hasNeighborBuilding(VertexLocation location) throws ServerBoardException {
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
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

            return !(
                    buildings.get(east) == null &&
                            buildings.get(northwest) == null &&
                            buildings.get(southwest) == null );
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

            return !(
                    buildings.get(west) == null &&
                            buildings.get(northeast) == null &&
                            buildings.get(southeast) == null );
        }
    }

    /**
     * Check if there are any buildings immediately next to this EdgeLocation, and if
     * there are, check if their owner field matches the owner parameter.
     * @param location The EdgeLocation to check for neighbor buildings.
     * @param owner The building owner to check for. Must be in the range -1 to 3.
     *              If -1 is passed in, any building may count a match.
     * @return True if there is a neighbor building with the field owner matching the param owner.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range -1 to 3. Also thrown if the location param is passed in null.
     */
    private boolean hasNeighborBuilding(EdgeLocation location, int owner) throws ServerBoardException {
        if (owner < -1 || owner > 3) throw new ServerBoardException("Param owner must be in the range -1 to 3.");
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
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
     * Check if there are any roads immediately next to this VertexLocation, and if there
     * are, check if their owner field matches the owner parameter.
     * @param location The VertexLocation to check for neighbor roads.
     * @param owner The building owner to check for. Must be in the range -1 to 3.
     *              If -1 is passed in, any building may count a match.
     * @return True if there is a neighbor road with the field owner matching the param owner.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range -1 to 3. Also thrown if the location param is passed in null.
     */
    private boolean hasNeighborRoad(VertexLocation location, int owner) throws ServerBoardException {
        if (owner < -1 || owner > 3) throw new ServerBoardException("Param owner must be in the range -1 to 3.");
        if (location == null) throw new ServerBoardException("Param location cannot be null.");

        VertexLocation normalized = location.getNormalizedLocation();
        EdgeLocation north = new EdgeLocation(normalized.getHexLoc(), EdgeDirection.North);

        if (roads.get(north) != null) {
            if (owner == -1) return true;
            if (roads.get(north).getOwner() == owner) return true;
        }
        if (normalized.getDir() == VertexDirection.NorthEast) {
            EdgeLocation northeast = new EdgeLocation(normalized.getHexLoc(), EdgeDirection.NorthEast);
            if(roads.get(northeast) != null) {
                if (owner == -1) return true;
                if (roads.get(northeast).getOwner() == owner) return true;
            }
            HexLocation opposite = normalized.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast);
            EdgeLocation far = new EdgeLocation(opposite, EdgeDirection.NorthWest);
            if(roads.get(far) != null) {
                if (owner == -1) return true;
                if (roads.get(far).getOwner() == owner) return true;
            }
        }
        else { //Northwest
            EdgeLocation northwest = new EdgeLocation(normalized.getHexLoc(), EdgeDirection.NorthWest);
            if(roads.get(northwest) != null) {
                if (owner == -1) return true;
                if (roads.get(northwest).getOwner() == owner) return true;
            }
            HexLocation opposite = normalized.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest);
            EdgeLocation far = new EdgeLocation(opposite, EdgeDirection.NorthEast);
            if(roads.get(far) != null) {
                if (owner == -1) return true;
                if (roads.get(far).getOwner() == owner) return true;
            }
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
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range -1 to 3. Also thrown if the location param is passed in null.
     */
    private boolean hasNeighborRoad(EdgeLocation location, int owner) throws ServerBoardException {
        if (owner < -1 || owner > 3) throw new ServerBoardException("Param owner must be in the range -1 to 3.");
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
        EdgeLocation clockwiseEdge = location.getClockwiseEdge().getNormalizedLocation();
        VertexLocation clockwiseVert = location.getClockwiseVertex().getNormalizedLocation();
        EdgeLocation counterclockwiseEdge = location.getCounterClockwiseEdge().getNormalizedLocation();
        VertexLocation counterclockwiseVert = location.getCounterClockwiseVertex().getNormalizedLocation();
        if (roads.containsKey(clockwiseEdge)) {
            if (owner == -1) return true;
            if (roads.get(clockwiseEdge).getOwner() == owner) {
                if (buildings.containsKey(clockwiseVert)) {
                    if (buildings.get(clockwiseVert).getOwner() == owner) return true;
                }
                else return true;
            }
        }
        if (roads.containsKey(counterclockwiseEdge)) {
            if (owner == -1) return true;
            if (roads.get(counterclockwiseEdge).getOwner() == owner) {
                if (buildings.containsKey(counterclockwiseVert)) {
                    if (buildings.get(counterclockwiseVert).getOwner() == owner) return true;
                }
                else return true;
            }
        }
        EdgeLocation opposite = new EdgeLocation(location.getHexLoc().getNeighborLoc(location.getDir()),location.getDir().getOppositeDirection());
        clockwiseEdge = opposite.getClockwiseEdge().getNormalizedLocation();
        counterclockwiseEdge = opposite.getCounterClockwiseEdge().getNormalizedLocation();
        if (roads.containsKey(clockwiseEdge)) {
            if (owner == -1) return true;
            if (roads.get(clockwiseEdge).getOwner() == owner) {
                if (buildings.containsKey(counterclockwiseVert)) {
                    if (buildings.get(counterclockwiseVert).getOwner() == owner) return true;
                }
                else return true;
            }
        }
        if (roads.containsKey(counterclockwiseEdge)) {
            if (owner == -1) return true;
            if (roads.get(counterclockwiseEdge).getOwner() == owner) {
                if (buildings.containsKey(clockwiseVert)) {
                    if (buildings.get(clockwiseVert).getOwner() == owner) return true;
                }
                else return true;
            }
        }
        return false;
    }

    /**...*/
    private boolean isEdgeOnBoard(EdgeLocation location) throws ServerBoardException {
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
        HexLocation testHex = location.getHexLoc();
        if (tiles.containsKey(testHex)) return true;
        HexLocation testHex2 = testHex.getNeighborLoc(location.getDir());
        if (tiles.containsKey(testHex2)) return true;
        return false;
    }

    /**
     * Given an EdgeLocation and a PortType, this method creates 2 ports of matching type and adds
     * them to the ports map. The keys are the VertexLocations on either side of the location param.
     * @param location The location that connects the 2 new port vertices. The location must
     *                 represent an edge that actually borders the water.
     * @param type The type of port to be created. Cannot be null.
     * @throws ServerBoardException Thrown if either of the parameters is null or if the location
     * param does not actually border the water.
     */
    private void buildPortPair(EdgeLocation location, PortType type) throws ServerBoardException {
        if (location == null) throw new ServerBoardException("Param location cannot be null.");
        if (type == null) throw new ServerBoardException("Param type cannot be null.");
        HexLocation opposite = location.getHexLoc().getNeighborLoc(location.getDir());
        EdgeLocation toUse = location;
        if (tiles.containsKey(location.getHexLoc())) {
            if (tiles.containsKey(opposite)) throw new ServerBoardException("Param location must on the coast.");
        }
        else {
            if (!tiles.containsKey(opposite)) throw new ServerBoardException("Param location must on the coast.");
            toUse = new EdgeLocation(opposite, location.getDir().getOppositeDirection());
        }
        VertexLocation clockwise = toUse.getClockwiseVertex().getNormalizedLocation();
        VertexLocation counterclockwise = toUse.getCounterClockwiseVertex().getNormalizedLocation();
        ports.put(clockwise, type);
        ports.put(counterclockwise, type);
    }

    /**
     * @param playerIndex index of player who wants to perform maritime trade
     * @param type type of resource he wants to maritime trade
     * @return	true if player has building on the port type that he wants to trade
     */
    public boolean canPlayMaritimeTrade(int playerIndex, PortType type) {
        if (type == null)
            return true;

        for(Map.Entry<VertexLocation, PortType> entry : ports.entrySet()){
            if(entry.getValue() == type) {
                if(buildings.get(entry.getKey()) == null) continue;
                if(buildings.get(entry.getKey()).getOwner() == playerIndex) return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        ServerBoard other = (ServerBoard)obj;
        if(!robber.equals(other.robber))
            return false;
        if(!tiles.equals(other.tiles))
            return false;
        if(!buildings.equals(other.buildings))
            return false;
        if(!roads.equals(other.roads))
            return false;
        if(!ports.equals(other.ports))
            return false;
        return true;
    }

//==================== NEW BULL CRAP

    public PortType getPortType(EdgeLocation loc) {
        return portTypes.get(loc.getNormalizedLocation());
    }

    public HexType getHexType(HexLocation loc){
        ServerHexTile tile = tiles.get(loc);
        return tile != null ? tile.getType() : HexType.WATER;
    }

    public int getChit(HexLocation loc){
        ServerHexTile tile = tiles.get(loc);
        return tile != null ? tile.getDiceNum() : 0;
    }

    public HashMap<VertexLocation, ServerConstructable> getBuildingPieces() {
        return buildings;
    }

    public HashMap<EdgeLocation, ServerConstructable> getRoadPieces() {
        return roads;
    }
    
    public HashMap<VertexLocation, PortType> getPorts()
    {
    	return ports;
    }
    
    

    public HashSet<PortType> getPlayerPorts(int index) {

        HashSet<PortType> toReturn = new HashSet<PortType>();
        for (VertexLocation vloc : ports.keySet()) {
            if (buildings.containsKey(vloc) && buildings.get(vloc).getOwner() == index)
                toReturn.add(ports.get(vloc));
        }
        return toReturn;
    }

	public HashMap<EdgeLocation, PortType> getPortTypes() {
		return portTypes;
	}

    public ArrayList<HexLocation> getHexLocsByNum(int diceNum) {
        ArrayList<HexLocation> hexes = new ArrayList<HexLocation>();
        for(HexLocation loc : tiles.keySet()) {
            if (diceNum == tiles.get(loc).getDiceNum())
                hexes.add(loc);
        }
        return hexes;
    }

    public ArrayList<ServerConstructable> getAdjacentBuildings(HexLocation loc) {

        ArrayList<ServerConstructable> adjBuildings = new ArrayList<ServerConstructable>();

        VertexLocation vloc = new VertexLocation(loc, VertexDirection.West).getNormalizedLocation();
        if(buildings.containsKey(vloc))
            adjBuildings.add(buildings.get(vloc));
        vloc = new VertexLocation(loc, VertexDirection.NorthEast).getNormalizedLocation();
        if(buildings.containsKey(vloc))
            adjBuildings.add(buildings.get(vloc));
        vloc = new VertexLocation(loc, VertexDirection.NorthWest).getNormalizedLocation();
        if(buildings.containsKey(vloc))
            adjBuildings.add(buildings.get(vloc));
        vloc = new VertexLocation(loc, VertexDirection.East).getNormalizedLocation();
        if(buildings.containsKey(vloc))
            adjBuildings.add(buildings.get(vloc));
        vloc = new VertexLocation(loc, VertexDirection.SouthEast).getNormalizedLocation();
        if(buildings.containsKey(vloc))
            adjBuildings.add(buildings.get(vloc));
        vloc = new VertexLocation(loc, VertexDirection.SouthWest).getNormalizedLocation();
        if(buildings.containsKey(vloc))
            adjBuildings.add(buildings.get(vloc));

        return adjBuildings;
    }


    public ArrayList<HexType> getAdjacentHexTypes(VertexLocation loc) {

        ArrayList<HexType> toReturn = new ArrayList<HexType>();

        //normalizing it makes everything easier
        loc = loc.getNormalizedLocation();
        HexLocation tempHex = loc.getHexLoc();

        //check the hex south of this vertex
        if(tiles.containsKey(tempHex) && tiles.get(tempHex).getType() != HexType.DESERT)
            toReturn.add(tiles.get(tempHex).getType());

        //check the hex north of this vertex
        tempHex = tempHex.getNeighborLoc(EdgeDirection.North);
        if(tiles.containsKey(tempHex) && tiles.get(tempHex).getType() != HexType.DESERT)
            toReturn.add(tiles.get(tempHex).getType());

        //check the third hex, which depends on whether loc is NW or NE
        if(loc.getDir() == VertexDirection.NorthWest) {
            //look to the left
            tempHex = tempHex.getNeighborLoc(EdgeDirection.SouthWest);
            if(tiles.containsKey(tempHex) && tiles.get(tempHex).getType() != HexType.DESERT)
                toReturn.add(tiles.get(tempHex).getType());
        }
        if(loc.getDir() == VertexDirection.NorthEast) {
            //look to the right
            tempHex = tempHex.getNeighborLoc(EdgeDirection.SouthEast);
            if(tiles.containsKey(tempHex) && tiles.get(tempHex).getType() != HexType.DESERT)
                toReturn.add(tiles.get(tempHex).getType());
        }

        return toReturn;

    }

}

