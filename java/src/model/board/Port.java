package model.board;

import shared.definitions.PortType;

/**
 * Created by brandt on 1/26/15.
 * <p>
 *     Represents one of the two vertices that connection to a port. Keeps track
 *     of the PortType and the port owner if there is one.
 * </p>
 */
public class Port {

    /** The index of the player that owns this object */
    int owner;
    /** The type of this object (wood, brick, sheep, wheat, ore, or three for one) */
    PortType type;


    /**
     * Constructor to initialize type.
     * @param type The type as a PortType.
     */
    public Port(PortType type) {
        this.type = type;
        owner = -1;
    }

    /**
     * Constructor to initialize type and owner.
     * @param type The type as a PortType enum.
     * @param owner The object owner, which can only be in the range 0-3 inclusive.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range 0-3
     */
    public Port(PortType type, int owner) throws BoardException {
        this.type = type;
        setOwner(owner);
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

    public int getOwner() {
        return owner;
    }
    
    public PortType getType() {
    	return type;
    }
}
