package client.map.states;

import client.map.MapController;
import shared.definitions.PieceType;

/**
 * Created by brandt on 2/20/15.
 */
public class Round1MapState extends AbstractMapState {

    @Override
    public void update(){

    }

    public void start(MapController controller){
        if(controller.modalOpen){
            return;
        }
        controller.startMove(PieceType.SETTLEMENT, true, false);
        controller.startMove(PieceType.ROAD, true, true);
    }
}
