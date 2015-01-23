package model.board.mapunits;

import model.board.BoardException;
import model.board.construction.Building;
import model.board.construction.City;
import model.board.construction.Road;
import model.board.construction.Settlement;
import shared.definitions.HexType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

import java.util.TreeMap;

/**
 * Created by brandt on 1/17/15.
 * <p>
 * Contains all the information related to an individual hex tile
 * on a Catan board. This includes the tile's type, location, dice roll token value,
 * and a map pairing each of its vertex locations to the actual vertex object they refer to.
 * </p>
 * This object is primarily used for storage, so its methods are mostly getters and setters.
 */
public class HexTile {

    /** The value of the dice roll token for this HexTile. */
    private int diceNum;
    /** The resource type for this HexTile. */
    private HexType type;
    /** The location for this HexTile. */
    private HexLocation location;
    /** A map that pairs this HexTile's relative vertex location to the actual Vertex object*/
    @Deprecated
    private TreeMap<VertexDirection, Vertex> vertices;
    /** A map that pairs this HexTile's relative vertex location to the actual Building object*/
    private TreeMap<VertexDirection, Building> buildings;
    /** A map that pairs this HexTile's relative edge location to the actual Road object*/
    private  TreeMap<EdgeDirection, Road> roads;

    /**
     * Constructor to initialize the field type.
     * @param type The HexType for this HexTile.
     */
    public HexTile(HexType type) {
        this.type = type;
        buildings = new TreeMap<VertexDirection, Building>();
        roads = new TreeMap<EdgeDirection, Road>();
//        vertices = new TreeMap<VertexDirection, Vertex>();
    }

    /**
     * Constructor to initialize the fields type and location.
     * @param type The HexType for this HexTile.
     * @param location The HexLocation for this HexTile.
     */
    public HexTile(HexType type, HexLocation location) {
        this(type);
        this.location = location;
    }

    /**
     * Constructor to initialize the fields type, location, and diceNum.
     * @param type The HexType for this HexTile.
     * @param diceNum An integer 0-12 indicating the value of the dice roll token. diceNum may
     *                be set to zero only in the case of HexType as Desert.
     * @param location The HexLocation for this HexTile.
     * @throws BoardException Thrown when diceNum is outside the range 0-12.
     */
    public HexTile(HexType type, int diceNum, HexLocation location) throws BoardException{
        this(type,location);
        if(diceNum < 0 || diceNum > 12) throw new BoardException("Cannot set diceNum less than 0 or greater than 12 in HexTile constructor");
        this.diceNum = diceNum;
    }

    /**
     * Verifies if it is possible to build a Settlement at the desired vertex. This is
     * only possible when there is no Building present at that vertex or any of its neighbors.
     * @param direction The vertex where the player wishes to build.
     * @return True if the player can build on that vertex.
     */
    public boolean canBuildSettlement(VertexDirection direction) {
        if (buildings.get(direction)!=null) return false;
        //check all the neighboring vertices to make sure no Buildings are built there.
        return true;
    }

    /**
     * Verifies if it is possible to build a City at the desired vertex. This is only possible
     * when a Settlement is already present at that vertex.
     * @param direction The vertex where the player wishes to build.
     * @return True if the player can build on that vertex.
     */
    public boolean canBuildCity(VertexDirection direction) {
        if (buildings.get(direction)!=null) return false;
        return buildings.get(direction).isSettlement();
    }

    /**
     * Verifies if it is possible to build a Road at the desired edge. This is only possible
     * when an edge has no Road present and there is another Road at an adjacent edge.
     * @param direction The edge where the player wishes to build.
     * @return True if the player can build on that edge.
     */
    public boolean canBuildRoad(EdgeDirection direction) { return true; }

    /**
     * Actually creates a Settlement object and adds it to this HexTile. canBuildSettlement
     * should be called prior to this to ensure that building is successful.
     * @param direction The vertex upon which to build.
     * @param owner The owner of that Settlement, which must be in the range 0-3 inclusive.
     * @throws BoardException Thrown if the owner param is outside the range 0-3.
     */
    public void buildSettlement(VertexDirection direction, int owner) throws BoardException {
        buildings.put(direction, new Settlement(owner, new VertexLocation(location, direction)));
    }

    /**
     * Actually creates a City object and replaces the existing Settlement on this HexTile.
     * canBuildCity should be called prior to this to ensure that building is successful.
     * @param direction The vertex upon which to build.
     * @param owner The owner of that City, which must be in the range 0-3 inclusive.
     * @throws BoardException Thrown if the owner param is outside the range 0-3.
     */
    public void buildCity(VertexDirection direction, int owner) throws BoardException {
        buildings.put(direction, new City(owner, new VertexLocation(location, direction)));
    }

    /**
     * Actually creates a Road object and adds it to this HexTile. canBuildRoad should be
     * called prior to this to ensure that building is successful.
     * @param direction The edge upon which to build.
     * @param owner The owner of that Road, which must be in the range 0-3 inclusive.
     * @throws BoardException Thrown if the owner param is outside the range 0-3.
     */
    public void buildRoad(EdgeDirection direction, int owner) throws BoardException {}

    //public void getPlayerResources(){};

    /**
     * Setter for the diceNum field, which checks to make sure the diceNum param is valid.
     * @param diceNum An integer 2-12 inclusive.
     */
    public void setDiceNum(int diceNum) throws BoardException {
        if(diceNum < 2 || diceNum > 12) throw new BoardException("diceNum param outside the range 2-12");
        this.diceNum = diceNum;
    }

    /**
     * Obtains a Vertex object using the VertexDirection relative to this HexTile.
     * @param vLoc The location of a vertex relative to this HexTile.
     * @return The actual Vertex object indicated by the vLoc parameter.
     */
    @Deprecated
    public Vertex getVertex(VertexDirection vLoc) {
        return vertices.get(vLoc);
    }

    public HexLocation getLocation() { return location; }

    public int getDiceNum() {
        return diceNum;
    }

    public void setType(HexType type) {
        this.type = type;
    }

    public HexType getType() {
        return type;
    }

    public void setLocation(HexLocation location) { this.location = location; }

}
