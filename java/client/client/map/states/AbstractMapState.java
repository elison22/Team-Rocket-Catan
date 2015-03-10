package client.map.states;

import model.game.TurnState;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.data.RobPlayerInfo;
import client.map.MapController;
import facade.ClientFacade;

/**
 * Created by brandt on 2/18/15.
 */
public abstract class AbstractMapState {

    protected ClientFacade facade;
    protected static TurnState curState = TurnState.Playing;

    protected AbstractMapState(){}
    protected AbstractMapState(ClientFacade facade) {
        this.facade = facade;
    }

    public abstract void update(MapController controller);

    public boolean canBuildRoad(EdgeLocation edgeLoc){return false;}

    public boolean canBuildSettlement(VertexLocation vertLoc){return false;}

    public boolean canBuildCity(VertexLocation vertLoc){return false;}

    public boolean canPlaceRobber(HexLocation hexLoc){return false;}

    public void placeRoad(EdgeLocation edgeLoc){}

    public void placeSettlement(VertexLocation vertLoc){}

    public void placeCity(VertexLocation vertLoc){}

    public RobPlayerInfo[] placeRobber(HexLocation hexLoc){return null;}

    public void robPlayer(RobPlayerInfo victim){}


}
