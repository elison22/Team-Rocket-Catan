package client.map.states;

import client.base.OverlayView;
import client.data.RobPlayerInfo;
import client.map.MapController;
import client.map.RobView;
import facade.ClientFacade;
import model.game.TurnState;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by brandt on 2/20/15.
 */
public class PlayingMapState extends AbstractMapState {

	public PlayingMapState(ClientFacade facade){
        super(facade);
    }

    @Override
    public void update(){

    }

    @Override
    public void start(MapController controller){
        OverlayView.killView("wait");
        curState = TurnState.Playing;
    }

    @Override
    public boolean canBuildRoad(EdgeLocation edgeLoc) {
    	return facade.canBuildRoad(edgeLoc);
    }

    @Override
    public boolean canBuildSettlement(VertexLocation vertLoc) {
        return facade.canBuildSettlement(vertLoc);
    }

    @Override
    public boolean canBuildCity(VertexLocation vertLoc) {
        return facade.canBuildCity(vertLoc);
    }

    @Override
    public boolean canPlaceRobber(HexLocation hexLoc) {
        return false;
    }

    @Override
    public void placeRoad(EdgeLocation edgeLoc) {
    	facade.doBuildRoad(edgeLoc, false);
    }

    @Override
    public void placeSettlement(VertexLocation vertLoc) {
    	facade.doBuildSettlement(vertLoc, false);
    }

    @Override
    public void placeCity(VertexLocation vertLoc) {
    	facade.doBuildCity(vertLoc);
    }

    @Override
    public RobPlayerInfo[] placeRobber(HexLocation hexLoc) {
        return null;
    }

    @Override
    public void startMove(PieceType pieceType) {
    	return;
    }

    @Override
    public void cancelMove() {
    	return;
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
