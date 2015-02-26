package client.map.states;

import client.data.RobPlayerInfo;
import client.map.MapController;
import facade.ClientFacade;
import model.game.TurnState;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by brandt on 2/20/15.
 */
public class RobbingMapState extends AbstractMapState{

    public RobbingMapState(ClientFacade facade) {
        super(facade);
    }

    @Override
    public void start(MapController controller){
    	controller.getRobView().showModal();
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
    	// do nothing
    }

    @Override
    public void placeSettlement(VertexLocation vertLoc) {
    	// do nothing
    }

    @Override
    public void placeCity(VertexLocation vertLoc) {
    	// do nothing
    }

    @Override
    public void placeRobber(HexLocation hexLoc) {

    }

    @Override
    public void startMove(PieceType pieceType) {

    }

    @Override
    public void cancelMove() {
    	// do nothing
    }

    @Override
    public void playSoldierCard() {

    }

    @Override
    public void playRoadBuildingCard() {
    	// do nothing
    }

    @Override
    public void robPlayer(RobPlayerInfo victim) {

    }

    @Override
    public void update(){
        if (facade.getState() == TurnState.Robbing)
            System.out.println("Robbing");
    }
}
