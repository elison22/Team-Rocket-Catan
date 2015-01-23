package model.board;

import model.board.mapunits.HexTile;
import shared.locations.EdgeLocation;

import java.util.ArrayList;

/**
 * Created by brandt on 1/17/15.
 */
@Deprecated
public class BoardManager {

    private ArrayList<HexTile> tiles = new ArrayList<HexTile>();




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