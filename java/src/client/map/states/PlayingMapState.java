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
public class PlayingMapState extends AbstractMapState {

    HexLocation newRobberLocation;

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
        return facade.canPlaceRobber(hexLoc);
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
        if (tempList.size() == 0){
            robPlayer(new RobPlayerInfo(-1));
        }
        RobPlayerInfo[] tempCast = new RobPlayerInfo[0];
        return tempList.toArray(tempCast);
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
        facade.doUseSoldier(victim.getPlayerIndex(), newRobberLocation);
    }



}
