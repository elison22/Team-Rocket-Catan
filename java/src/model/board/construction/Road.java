package model.board.construction;

import shared.locations.EdgeLocation;

/**
 * Created by brandt on 1/22/15.
 */
public class Road {

    int owner;
    EdgeLocation position;

    public Road(int owner, EdgeLocation position) {
        this.owner = owner;
        this.position = position;
    }

    public int getOwner() {
        return owner;
    }

    public EdgeLocation getPosition() {
        return position;
    }
}
