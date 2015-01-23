package model.board.construction;

import model.board.BoardException;
import shared.locations.VertexLocation;

/**
 * Created by brandt on 1/22/15.
 * This is a super class for the City and Settlement classes. It captures their similarities, which
 * are owner and location as well as abstract functions for getting the victory point
 * value and a function to check if the building is a settlement.
 */
public abstract class Building {

    /** The index of the player that owns this building */
    int owner;
    /** The location of this building */
    VertexLocation location;

    /**
     * Called by the subclasses to initialize owner and location.
     * @param owner The building owner, which can only be in the range 0-3 inclusive.
     * @param location The VertexLocation of this building.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range 0-3
     */
    protected Building(int owner, VertexLocation location) throws BoardException {
        setOwner(owner);
        this.location = location;
    }

    public int getOwner() {
        return owner;
    }

    /**
     * Sets the field owner to the param owner if the owner is within 0-3 inclusive.
     * @param owner An integer representing the player index.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range 0-3
     */
    public void setOwner(int owner) throws BoardException {
        if (owner < 0 || owner > 3) throw new BoardException("Owner cannot be outside range 0-3.");
        this.owner = owner;
    }

    public VertexLocation getLocation() {
        return location;
    }

    public void setLocation(VertexLocation location) {
        this.location = location;
    }

    /**
     * Gets the number of victory points that this building is worth. It is also
     * the number of resources that this building earns when the diceNum of an
     * adjacent HexTile gets rolled.
     * @return An int representing the number of victory points.
     */
    public abstract int getVpValue();

    /**
     * Returns whether or not this Building is a settlement, which is used to check
     * if the Building can be upgraded to a City.
     * @return True is the building is a Settlement.
     */
    public abstract boolean isSettlement();
}
