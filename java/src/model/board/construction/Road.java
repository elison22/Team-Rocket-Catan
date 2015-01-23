package model.board.construction;

import shared.locations.EdgeLocation;

/**
 * Created by brandt on 1/22/15.
 */
public class Road {

    private int owner;
    private EdgeLocation location;

    public Road(int owner, EdgeLocation location) {
        this.owner = owner;
        this.location = location;
    }

    public int getOwner() {
        return owner;
    }

    public EdgeLocation getLocation() {
        return location;
    }
}
