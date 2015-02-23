package client.map.states;

import client.data.RobPlayerInfo;
import client.map.MapController;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by brandt on 2/20/15.
 */
public class DiscardMapState extends AbstractMapState{

    @Override
    public void update(){

    }

    @Override
    public void start(MapController controller){

    }

    @Override
    public boolean canBuildRoad(EdgeLocation edgeLoc) {
        return false;
    }

    @Override
    public boolean canBuildSettlement(VertexLocation vertLoc) {
        return false;
    }

    @Override
    public boolean canBuildCity(VertexLocation vertLoc) {
        return false;
    }

    @Override
    public boolean canPlaceRobber(HexLocation hexLoc) {
        return false;
    }

    @Override
    public void placeRoad(EdgeLocation edgeLoc) {

    }

    @Override
    public void placeSettlement(VertexLocation vertLoc) {

    }

    @Override
    public void placeCity(VertexLocation vertLoc) {

    }

    @Override
    public void placeRobber(HexLocation hexLoc) {

    }

    @Override
    public void startMove(PieceType pieceType) {

    }

    @Override
    public void cancelMove() {

    }

    @Override
    public void playSoldierCard() {

    }

    @Override
    public void playRoadBuildingCard() {

    }

    @Override
    public void robPlayer(RobPlayerInfo victim) {

    }

}
