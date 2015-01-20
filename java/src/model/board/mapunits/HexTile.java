package model.board.mapunits;

import shared.definitions.HexType;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.TreeMap;

/**
 * Created by brandt on 1/17/15.
 * Contains all the information related to an individual hex tile
 * on a Catan board. This includes the tile's type, location, dice roll token value,
 * and a map pairing each of its vertex locations to the actual vertex object they refer to.
 *
 * This object is primarily used for storage, so its methods are mostly getters and setters.
 */
public class HexTile {

    private int diceNum;
    private HexType type;
    private HexLocation location;
    private TreeMap<VertexLocation, Vertex> vertices;

    public HexTile(HexType type) {
        this.type = type;
        vertices = new TreeMap<VertexLocation, Vertex>();
    }

    public HexTile(HexType type, HexLocation location) {
        this(type);
        this.location = location;
    }

    public HexTile(HexType type, int diceNum, HexLocation location) {
        this(type,location);
        this.diceNum = diceNum;
    }

    public int getDiceNum() {
        return diceNum;
    }

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

    public Vertex getVertex(VertexLocation vLoc) {
        return vertices.get(vLoc);
    }

    public HexLocation getLocation() { return location; }

    public void setLocation(HexLocation location) { this.location = location; }

}
