package client.map.states;

import client.data.RobPlayerInfo;
import client.map.MapController;
import model.game.TurnState;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

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
