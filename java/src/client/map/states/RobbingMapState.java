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

import java.util.ArrayList;

/**
 * Created by brandt on 2/20/15.
 */
public class RobbingMapState extends AbstractMapState{

    HexLocation newRobberLocation;

    public RobbingMapState(ClientFacade facade) {
        super(facade);
    }

    @Override
    public void start(MapController controller){
        if (curState == TurnState.Robbing)
            return;
        if (curState == TurnState.Discarding && !facade.isYourTurn() && OverlayView.getOverlayCount() == 1){
                controller.getRobView().closeModal();
        }
        if (facade.isYourTurn()) controller.getView().startDrop(PieceType.ROBBER, facade.getPlayerInfo().getColor(), false);
        curState = TurnState.Robbing;
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
        return facade.canPlaceRobber(hexLoc);
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
    public RobPlayerInfo[] placeRobber(HexLocation hexLoc) {

        newRobberLocation = hexLoc;
        ArrayList<RobPlayerInfo> tempList = new ArrayList<RobPlayerInfo>();
        RobPlayerInfo tempInfo;
        for (Integer index : facade.getPlayersToRob(hexLoc)) {
            tempInfo = new RobPlayerInfo(facade.getPlayerList()[index]);
            tempInfo.setPlayerIndex(index);
            tempInfo.setNumCards(facade.getPlayersOfGame().get(index).getResCount());
            if (tempInfo.getNumCards()==0) continue;
            if (tempInfo.getPlayerIndex() == facade.getLocalPlayerIndex()) continue;
            tempList.add(tempInfo);
        }
        if (tempList.size() == 0)
            robPlayer(new RobPlayerInfo(-1));
        RobPlayerInfo[] tempCast = new RobPlayerInfo[0];
        return tempList.toArray(tempCast);
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

        facade.doPlaceRobber(victim.getPlayerIndex(), newRobberLocation);

    }

    @Override
    public void update(){
        if (facade.getState() == TurnState.Robbing)
            System.out.println("Robbing");
    }
}
