package client.map.states;

import client.map.MapController;
import facade.ClientFacade;
import model.game.TurnState;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 * Created by brandt on 2/20/15.
 */
public class Round2MapState extends AbstractMapState{

    public Round2MapState(ClientFacade facade){
        super(facade);
    }

    @Override
    public void update(MapController controller){
        if(!facade.isYourTurn())
            return;
        if(curState == TurnState.SecondRound)
            return;

        controller.getView().startDrop(PieceType.SETTLEMENT, facade.getPlayerInfo().getColor(), false);
        if (facade.getLocalPlayer().getRemainingRoads() == 14)
            controller.getView().startDrop(PieceType.ROAD, facade.getPlayerInfo().getColor(), false);

        curState = TurnState.SecondRound;
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
    public void placeRoad(EdgeLocation edgeLoc) {

        facade.doBuildRoad(edgeLoc, true);

    }

    @Override
    public void placeSettlement(VertexLocation vertLoc) {
        facade.doBuildSettlement(vertLoc, true);
        endTurn();
    }

    public void endTurn() {
        facade.finishTurn();
    }

}
