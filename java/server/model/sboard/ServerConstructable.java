package model.sboard;

import model.sboard.ServerBoardException;
import model.sboard.ServerConstructable;
import shared.definitions.PieceType;

/**
 * Created by Hayden on 3/12/15.
 */
public class ServerConstructable {
	/** The index of the player that owns this object */
    private int owner = -1;
    /** The type of this object (settlement, building, or road) */
    private PieceType type;


    /**
     * Initializes the owner and type of this object.
     * @param type The type of constructable, which is 1 of the 3 options in the PieceType enum.
     * @param owner The object owner, which can only be in the range 0-3 inclusive.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range 0-3
     */
    public ServerConstructable(PieceType type, int owner) throws ServerBoardException {
        this.type = type;
        setOwner(owner);
    }


    /**
     * Sets the field owner to the param owner if the owner is within 0-3 inclusive.
     * @param owner An integer representing the player index.
     * @throws ServerBoardException Thrown when attempting to set the owner to an int outside
     * the range 0-3
     */
    public void setOwner(int owner) throws ServerBoardException {
        if (owner < 0 || owner > 3) throw new ServerBoardException("Owner cannot be outside range 0-3.");
        this.owner = owner;
    }

    public int getOwner() {
        return owner;
    }


    /**
     * Returns whether or not this ServerConstructable is a settlement, which is used to check
     * if the ServerConstructable can be upgraded to a City.
     *
     * @return True if the building is a Settlement.
     */
    public boolean isSettlement() {
        return type == PieceType.SETTLEMENT;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        ServerConstructable other = (ServerConstructable)obj;
        if(owner != other.owner)
            return false;
        if(type != other.type)
            return false;
        return true;
    }
}
