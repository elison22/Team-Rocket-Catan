package model.board.mapunits;

import shared.definitions.ResourceType;
import shared.locations.VertexLocation;

import java.util.TreeMap;

/**
 * Created by brandt on 1/17/15.
 * Contains all the information related to a
 */
public class HexTile {

    private int diceNum;
    private ResourceType type;
    private TreeMap<VertexLocation, Vertex> vertices;

    public HexTile(ResourceType type) {
        this.type = type;
    }

    public HexTile(ResourceType type, int diceNum) {
        this.type = type;
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

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public Vertex getVertex(VertexLocation vLoc) {
        return vertices.get(vLoc);
    }

//    public void addSettlement(Vertex newBuilding) {
//        if(newBuilding)
//        buildings.add(newBuilding);
//    }
}
