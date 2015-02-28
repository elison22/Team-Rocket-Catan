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
public class Round1MapState extends AbstractMapState {

    public Round1MapState(ClientFacade facade){
        super(facade);
    }

    @Override
    public void update(){

    }

    @Override
    public void start(MapController controller){
        if(!facade.isYourTurn())
            return;
        if(curState == TurnState.FirstRound)
            return;

//        controller.modalOpen = true;
        controller.getView().startDrop(PieceType.SETTLEMENT, facade.getPlayerInfo().getColor(), false);
        if (facade.getLocalPlayer().getRemainingRoads() == 15)
            controller.getView().startDrop(PieceType.ROAD, facade.getPlayerInfo().getColor(), false);
//        controller.modalOpen = false;
        curState = TurnState.FirstRound;
    }

    @Override
    public boolean canBuildRoad(EdgeLocation edgeLoc) {
        return facade.canBuildInitRoad(edgeLoc);
    }

    @Override
    public boolean canBuildSettlement(VertexLocation vertLoc) {
        return facade.canBuildInitSettlement(vertLoc);
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
        facade.doBuildRoad(edgeLoc, true);
    }

    @Override
    public void placeSettlement(VertexLocation vertLoc) {
        facade.doBuildSettlement(vertLoc, true);
        endTurn();
    }

    @Override
    public void placeCity(VertexLocation vertLoc) {

    }

    @Override
    public RobPlayerInfo[] placeRobber(HexLocation hexLoc) {

        return null;
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

    public void endTurn() {
        facade.finishTurn();
    }
}
