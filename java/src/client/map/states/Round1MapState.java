package client.map.states;

import client.map.IMapController;
import client.map.MapController;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;

/**
 * Created by brandt on 2/20/15.
 */
public class Round1MapState extends AbstractMapState {

    @Override
    public void update(){

    }

    public void start(IMapController controller){
        controller.startMove(PieceType.ROAD, true, true);
    }
}
