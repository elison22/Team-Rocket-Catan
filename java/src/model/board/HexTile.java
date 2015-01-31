package model.board;

import shared.definitions.HexType;


/**
 * Created by brandt on 1/17/15.
 * <p>
 * Contains the tile type and dice roll token value for this HexTile object.
 * </p>
 */
public class HexTile {

    /** The value of the dice roll token for this HexTile. */
    private int diceNum = 0;
    /** The resource type for this HexTile. */
    private HexType type;


    /**
     * Constructor to initialize the field type.
     * @param type The HexType for this HexTile.
     */
    public HexTile(HexType type) {
        this.type = type;
    }

    /**
     * Constructor to initialize the fields type and diceNum.
     * @param type The HexType for this HexTile.
     * @param diceNum An integer 0-12 indicating the value of the dice roll token. diceNum may
     *                be set to zero only in the case of HexType as Desert.
     * @throws BoardException Thrown when diceNum is outside the range 0-12.
     */
    public HexTile(HexType type, int diceNum) throws BoardException {
        this(type);
        setDiceNum(diceNum);
    }


    /**
     * Setter for the diceNum field, which checks to make sure the diceNum param is valid.
     * @param diceNum An integer 2-12 inclusive.
     * @throws BoardException Thrown when diceNum is outside the range 0-12.
     */
    public void setDiceNum(int diceNum) throws BoardException {
        if(diceNum < 2 || diceNum > 12) throw new BoardException("diceNum param outside the range 2-12");
        this.diceNum = diceNum;
    }

    public int getDiceNum() {
        return diceNum;
    }

    public void setType(HexType type) {
        this.type = type;
    }

    public HexType getType() {
        return type;
    }

}
