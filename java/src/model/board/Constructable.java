package model.board;

/**
 * Created by brandt on 1/22/15.
 * This contains basic information for a Constructable object on the board. There
 * are three types: SETTLEMENT, CITY, ROAD. Each object remembers its owner.
 */
public class Constructable {

    /** The index of the player that owns this object */
    private int owner;
    /** The type of this object (settlement, building, or road) */
    private ConstructableType type;


    /**
     * Initializes the owner and type of this object.
     * @param type The type of constructable, which is 1 of the 3 options in the ConstructableType enum.
     * @param owner The object owner, which can only be in the range 0-3 inclusive.
     * @throws BoardException Thrown when attempting to set the owner to an int outside
     * the range 0-3
     */
    public Constructable(ConstructableType type, int owner) throws BoardException {
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


    /**
     * Returns whether or not this Constructable is a settlement, which is used to check
     * if the Constructable can be upgraded to a City.
     *
     * @return True if the building is a Settlement.
     */
    public boolean isSettlement() {
        return type == ConstructableType.SETTLEMENT;
    }

}
