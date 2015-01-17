package model.board;

import model.board.mapunits.HexTile;
import shared.definitions.HexType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by brandt on 1/17/15.
 */

public class BoardManager {

    private ArrayList<HexTile> tiles = new ArrayList<HexTile>();

    /**
     * Creates a
     */
    public BoardManager(){}


//    public boolean setRobber(int x, int y) {
//        if (robber.getX()==x && robber.getY()==y)
//            return false;
//        else {
//            setRobber(0, x, y);
//            return true;
//        }
//    }


    public boolean buildRoad(EdgeLocation edge){
        return true;
    }



}
