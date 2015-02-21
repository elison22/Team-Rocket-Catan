package client.map.states;

import client.map.IMapController;
import client.map.MapController;

/**
 * Created by brandt on 2/20/15.
 */
public class Round1MapState extends AbstractMapState {

    @Override
    public void update(){

    }

    public void start(IMapController controller){
        controller.startMove();
    }
}
