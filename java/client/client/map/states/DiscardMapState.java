package client.map.states;

import client.base.OverlayView;
import client.map.MapController;
import facade.ClientFacade;
import model.game.TurnState;

/**
 * Created by brandt on 2/20/15.
 */
public class DiscardMapState extends AbstractMapState{

    public DiscardMapState(ClientFacade facade) {
        super(facade);

    }

    @Override
    public void update(MapController controller){
        if(curState == TurnState.Rolling)
            OverlayView.bringForward("rollresult");
        curState = TurnState.Discarding;
    }


}
