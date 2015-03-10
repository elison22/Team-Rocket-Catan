package client.map.states;

import client.map.MapController;
import model.game.TurnState;
/**
 * Created by brandt on 2/20/15.
 */
public class RollingMapState extends AbstractMapState{

    public RollingMapState(){}


    @Override
    public void update(MapController controller){
        curState = TurnState.Rolling;
    }


}
