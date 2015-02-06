package model.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import serializer.json.*;
import shared.locations.*;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;

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
    private HashMap<HexLocation, HexTile> tiles = new HashMap<HexLocation, HexTile>();
    /** The CITY and SETTLEMENT type Constructable objects on this Board */
    private HashMap<VertexLocation, Constructable> buildings = new HashMap<VertexLocation, Constructable>();
    /** The ROAD type Constructable objects on this Board */
    private HashMap<EdgeLocation, Constructable> roads = new HashMap<EdgeLocation, Constructable>();
    /** The Port vertices on this Board */
    private HashMap<VertexLocation, Port> ports = new HashMap<VertexLocation, Port>();
    /** The HexLocation that represents the robber */
    private HexLocation robber;

    /**
     * Creates a new Board object with HexTile objects for the whole board including
     * diceNums, and actual HexLocations. It also initializes the robbers location to
     * the Desert, which has a diceNum of 0.
     * @param randomTileTypes Whether or not the tiles resource types should be randomized.
     * @param randomDiceNums Whether or not the diceNums should be randomized.
     * @param randomPortTypes Whether of not the port types should be randomized.
     * @throws BoardException Thrown only when there's a problem with the code that initializes this object.
     */
    public Board(boolean randomTileTypes, boolean randomDiceNums, boolean randomPortTypes) throws BoardException {

        Random typeRand = new Random();
        Random numRand = new Random();
        Random portRand = new Random();
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
                HexTile newTile;
                if (randomTileTypes) typeIndex = typeRand.nextInt(hexTypeArray.size());
                newTile = new HexTile(hexTypeArray.get(typeIndex));
                hexTypeArray.remove(typeIndex);
                if (newTile.getType() == HexType.DESERT) {
                    tiles.put(newLoc, newTile);
                    robber = newLoc;
                    continue;
                }
                if (randomDiceNums) numIndex = numRand.nextInt(numArray.size());
                newTile.setDiceNum(numArray.get(numIndex));
                numArray.remove(numIndex);
                tiles.put(newLoc, newTile);
            }
            if (yEnd == -2) yStart--;
            else yEnd--;
        }

        for (EdgeLocation location : portLocArray) {
            if (randomPortTypes) portIndex = portRand.nextInt(portTypeArray.size());
            buildPortPair(location, portTypeArray.get(portIndex));
            portTypeArray.remove(portIndex);
        }

    }

    /**
     * Creates a new Board object from the de-serialized json to replace the old Board object.
     * @param newMap A de-serialized json object.
     * @throws BoardException Thrown if newMap is null or if a HexTile's diceNum
     * is outside the range 0 to 12.
     */
    public Board(JsonMap newMap) throws BoardException {

        if (newMap == null) throw new BoardException("Param newMap cannot be null.");
        HexLocation hexloc;
        VertexLocation vertloc;
        Constructable piece;
        EdgeLocation edge;
        HexTile tile;

        // Update the hexes
        int originalCount = tiles.size();
        for (JsonHex hex : newMap.getHexes()) {
            hexloc = new HexLocation(hex.getLocation().getX(), hex.getLocation().getY());
            tile = new HexTile(HexType.convert(hex.getResource()),hex.getNumber());
            tiles.put(hexloc, tile);
        }
        //if (originalCount != tiles.size()) throw new BoardException("The tiles map had new elements added.");

        // Update the ports
        originalCount = ports.size();
        for (JsonPort doublePort : newMap.getPorts()) {
            hexloc = new HexLocation(doublePort.getLocation().getX(), doublePort.getLocation().getY());
            edge = new EdgeLocation(hexloc, EdgeDirection.convert(doublePort.getDirection()));
            if(doublePort.getRatio() == 3)
            	buildPortPair(edge, PortType.THREE_FOR_ONE);
            else
            	buildPortPair(edge, PortType.convert(doublePort.getResource()));
        }
        //if (originalCount != ports.size()) throw new BoardException("The ports map had new elements added.");

        // Update the settlements
        for (JsonVertexObject settlement : newMap.getSettlements()) {
            hexloc = new HexLocation(settlement.getLocation().getX(), settlement.getLocation().getY());
            vertloc = new VertexLocation(hexloc, VertexDirection.convert(settlement.getLocation().getDirection()));
            piece = new Constructable(PieceType.SETTLEMENT, settlement.getOwner());
            buildings.put(vertloc.getNormalizedLocation(), piece);
        }

        // Update the cities
        for (JsonVertexObject city : newMap.getCities()) {
            hexloc = new HexLocation(city.getLocation().getX(), city.getLocation().getY());
            vertloc = new VertexLocation(hexloc, VertexDirection.convert(city.getLocation().getDirection()));
            piece = new Constructable(PieceType.CITY, city.getOwner());
            buildings.put(vertloc.getNormalizedLocation(), piece);
        }

        // Update the roads
        for (JsonRoad road : newMap.getRoads()) {
            hexloc = new HexLocation(road.getLocation().getX(), road.getLocation().getY());
            edge = new EdgeLocation(hexloc, EdgeDirection.convert(road.getLocation().getDirection()));
            piece = new Constructable(PieceType.ROAD, road.getOwner());
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
    public boolean canPlayRobber(HexLocation newLocation) throws BoardException {
        if (newLocation == null) throw new BoardException("Param newLocation cannot be null.");
        return !robber.equals(newLocation);
    }

//    /**
//     * Set the robber's new location using x/y coordinates
//     * @param x The robber's new x location
//     * @param y The robber's new y location
//     * @throws BoardException Thrown when the x or y values represent a location that isn't on the board.
//     */
//    public void doPlayRobber(int x, int y) throws BoardException {
//        doPlayRobber(new HexLocation(x, y));
//    }

    /**
     * Set the robber's new location using a HexLocation object
     * @param newLocation The HexLocation for the robber's new location
     * @throws BoardException Thrown when the newLocation param represent a location that isn't on the board.
     */
    public void doPlayRobber(HexLocation newLocation) throws BoardException {
        if (!tiles.containsKey(newLocation)) throw new BoardException("Attempted to place the robber off the board.");
        robber = newLocation;
    }

    /**
     * Determines whether or not a player can build a Settlement-type Constructable on this
     * location. The conditions are that no Constructable is already present at the location
     * param or any of its neighbors. Also, the prospective builder must have a road adjacent
     * to the VertexLocation upon which he wants to build.
     * @param location Where to check for availability.
     * @param owner The owner of the VertexLocation to be tested. Must be in the range 0 to 3.
     * @return True if the vertex is available for a Settlement.
     * @throws BoardException Thrown when attempting to check with an owner index outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public boolean canBuildSettlement(VertexLocation location, int owner) throws BoardException {
        if (owner < 0 || owner > 3) throw new BoardException("Param owner must be in the range 0 to 3");
        if (location == null) throw new BoardException("Param location cannot be null.");
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
     * @throws BoardException Thrown when attempting to check an owner with an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
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
     * conditions depend on what phase of the game the builder is in (initial
     * build or building during the main part of the game).
     * @param location Where to verify for availability for a road.
     * @param owner The index of the player who wants to build a road.
     * @return True if the edge is available for a road.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    public boolean canBuildRoad(EdgeLocation location, int owner) throws BoardException {
        if (roads.size() > 8) return canBuildNormalRoad(location, owner);
        else return canBuildInitRoad(location, owner);
    }

    /**
     * Determines whether or not a player can build a Road on this location. The
     * conditions are that no Road is already present at the location param and that there
     * is no building immediately adjacent or neighboring the adjacent vertices.
     * @param location Where to verify for availability for a road.
     * @param owner The index of the player who wants to build a road.
     * @return True if the edge is available for a road.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    private boolean canBuildInitRoad(EdgeLocation location, int owner) throws BoardException {
        if (owner < 0 || owner > 3) throw new BoardException("Param owner must be in the range 0 to 3.");
        if (location == null) throw new BoardException("Param location cannot be null.");
        if (roads.get(location.getNormalizedLocation()) != null) return false;
        if (hasNeighborBuilding(location, owner)) return false;
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
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range 0 to 3. Also thrown if the location param is passed in null.
     */
    private boolean canBuildNormalRoad(EdgeLocation location, int owner) throws BoardException {
        if (owner < 0 || owner > 3) throw new BoardException("Param owner must be in the range 0 to 3.");
        if (location == null) throw new BoardException("Param location cannot be null.");
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
     * Check if there are any roads immediately next to this VertexLocation, and if there
     * are, check if their owner field matches the owner parameter.
     * @param location The VertexLocation to check for neighbor roads.
     * @param owner The building owner to check for. Must be in the range -1 to 3.
     *              If -1 is passed in, any building may count a match.
     * @return True if there is a neighbor road with the field owner matching the param owner.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range -1 to 3. Also thrown if the location param is passed in null.
     */
    private boolean hasNeighborRoad(VertexLocation location, int owner) throws BoardException {
        if (owner < -1 || owner > 3) throw new BoardException("Param owner must be in the range -1 to 3.");
        if (location == null) throw new BoardException("Param location cannot be null.");

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
            if (roads.get(counterclockwise).getOwner() == owner) return true;
        }
        return false;
    }

    /**
     * Given an EdgeLocation and a PortType, this method creates 2 Port objects of matching type and adds
     * them to the ports map. The keys are the VertexLocations on either side of the location param.
     * @param location The location that connects the 2 new port vertices. The location must
     *                 represent an edge that actually borders the water.
     * @param type The type of port to be created. Cannot be null.
     * @throws BoardException Thrown if either of the parameters is null or if the location
     * param does not actually border the water.
     */
    private void buildPortPair(EdgeLocation location, PortType type) throws BoardException {
        if (location == null) throw new BoardException("Param location cannot be null.");
        if (type == null) throw new BoardException("Param type cannot be null.");
        HexLocation opposite = location.getHexLoc().getNeighborLoc(location.getDir());
        EdgeLocation toUse = location;
        if (tiles.containsKey(location.getHexLoc())) {
            if (tiles.containsKey(opposite)) throw new BoardException("Param location must on the coast.");
        }
        else {
            if (!tiles.containsKey(opposite)) throw new BoardException("Param location must on the coast.");
            toUse = new EdgeLocation(opposite, location.getDir().getOppositeDirection());
        }
        VertexLocation clockwise = toUse.getClockwiseVertex().getNormalizedLocation();
        VertexLocation counterclockwise = toUse.getCounterClockwiseVertex().getNormalizedLocation();
        ports.put(clockwise, new Port(type));
        ports.put(counterclockwise, new Port(type));
    }

    /**
     * @param playerIndex index of player who wants to perform maritime trade
     * @param type type of resource he wants to maritime trade
     * @return	true if player has building on the port type that he wants to trade
     */
    public boolean canPlayerMaritimeTrade(int playerIndex, PortType type) {
    	for(Map.Entry<VertexLocation, Port> entry : ports.entrySet()){
    		if(entry.getValue().getType() == type) {
    			if(entry.getValue().getOwner() == playerIndex)
    				return true;
    		}
    	}
    	return false;
    }

	public HashMap<HexLocation, HexTile> getTiles()
	{
		return tiles;
	}

	public HashMap<VertexLocation, Constructable> getBuildings()
	{
		return buildings;
	}

	public HashMap<EdgeLocation, Constructable> getRoads()
	{
		return roads;
	}

	public HashMap<VertexLocation, Port> getPorts()
	{
		return ports;
	}

	public HexLocation getRobber()
	{
		return robber;
	}


}

