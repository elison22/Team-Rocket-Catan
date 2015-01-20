package model.board.mapunits;

import model.board.BoardException;
import shared.definitions.HexType;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;

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
    private TreeMap<VertexDirection, Vertex> vertices;

    /**
     * Constructor to initialize the field type.
     * @param type The HexType for this HexTile.
     */
    public HexTile(HexType type) {
        this.type = type;
        vertices = new TreeMap<VertexDirection, Vertex>();
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

    public int getDiceNum() {
        return diceNum;
    }

    /**
     * Setter for the diceNum field, which checks to make sure the diceNum param is valid.
     * @param diceNum An integer 2-12 inclusive.
     * @return True unless diceNum is outside the range 2-12.
     */
    public boolean setDiceNum(int diceNum) {
        if(diceNum < 2 || diceNum > 12) return false;
        this.diceNum = diceNum;
        return true;
    }

    public HexType getType() {
        return type;
    }

    public void setType(HexType type) {
        this.type = type;
    }

    /**
     * Obtains a Vertex object using the VertexDirection relative to this HexTile.
     * @param vLoc The location of a vertex relative to this HexTile.
     * @return The actual Vertex object indicated by the vLoc parameter.
     */
    public Vertex getVertex(VertexDirection vLoc) {
        return vertices.get(vLoc);
    }

    public HexLocation getLocation() { return location; }

    public void setLocation(HexLocation location) { this.location = location; }

}
