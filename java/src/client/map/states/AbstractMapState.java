package client.map.states;

import client.data.PlayerInfo;
import client.data.RobPlayerInfo;
import client.map.IMapController;
import client.map.MapController;
import facade.ClientFacade;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by brandt on 2/18/15.
 */
public abstract class AbstractMapState {

    protected ClientFacade facade;

    protected AbstractMapState(){}
    protected AbstractMapState(ClientFacade facade) {
        this.facade = facade;
    }

    public abstract void update();

    public abstract void start(MapController controller);

    public abstract boolean canBuildRoad(EdgeLocation edgeLoc);

    public abstract boolean canBuildSettlement(VertexLocation vertLoc);

    public abstract boolean canBuildCity(VertexLocation vertLoc);

    public abstract boolean canPlaceRobber(HexLocation hexLoc);

    public abstract void placeRoad(EdgeLocation edgeLoc);

    public abstract void placeSettlement(VertexLocation vertLoc);

    public abstract void placeCity(VertexLocation vertLoc);

    public abstract RobPlayerInfo[] placeRobber(HexLocation hexLoc);

    public abstract void startMove(PieceType pieceType);

    public abstract void cancelMove();

    public abstract void playSoldierCard();

    public abstract void playRoadBuildingCard();

    public abstract void robPlayer(RobPlayerInfo victim);


}
