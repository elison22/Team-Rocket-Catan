package client.map.states;

import java.util.ArrayList;

import model.game.TurnState;
import shared.definitions.PieceType;
import shared.locations.HexLocation;
import client.base.OverlayView;
import client.data.RobPlayerInfo;
import client.map.MapController;
import facade.ClientFacade;

/**
 * Created by brandt on 2/20/15.
 */
public class RobbingMapState extends AbstractMapState{

    HexLocation newRobberLocation;

    public RobbingMapState(ClientFacade facade) {
        super(facade);
    }

    @Override
    public void update(MapController controller){
        if (curState == TurnState.Robbing)
            return;
        OverlayView.killView("wait");
        if (facade.isYourTurn()) controller.getView().startDrop(PieceType.ROBBER, facade.getPlayerInfo().getColor(), false);
        if (curState == TurnState.Rolling)
            OverlayView.bringForward("rollresult");
        curState = TurnState.Robbing;
    }

    @Override
    public boolean canPlaceRobber(HexLocation hexLoc) {
        return facade.canPlaceRobber(hexLoc);
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
    public void robPlayer(RobPlayerInfo victim) {

        facade.doPlaceRobber(victim.getPlayerIndex(), newRobberLocation);

    }

}
