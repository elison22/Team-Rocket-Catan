package client.map.states;

import client.map.IMapController;
import client.map.MapController;

/**
 * Created by brandt on 2/18/15.
 */
public abstract class AbstractMapState {

    public abstract void update();

    public abstract void start(IMapController controller);

}
