package model.sboard;

import model.sboard.ServerBoardException;
import model.sboard.ServerHexTile;
import shared.definitions.HexType;

/**
 * Created by Hayden on 3/12/15.
 */
public class ServerHexTile {
	 /** The value of the dice roll token for this HexTile. */
    private int diceNum = 0;
    /** The resource type for this HexTile. */
    private HexType type;


    /**
     * Constructor to initialize the field type.
     * @param type The HexType for this HexTile.
     */
    public ServerHexTile(HexType type) {
        this.type = type;
    }

    /**
     * Constructor to initialize the fields type and diceNum.
     * @param type The HexType for this HexTile.
     * @param diceNum An integer 0-12 indicating the value of the dice roll token. diceNum may
     *                be set to zero only in the case of HexType as Desert.
     * @throws ServerBoardException Thrown when diceNum is outside the range 0-12.
     */
    public ServerHexTile(HexType type, int diceNum) throws ServerBoardException {
        this.type = type;
        setDiceNum(diceNum);
    }

    /**
     * Setter for the diceNum field, which checks to make sure the diceNum param is valid.
     * @param diceNum An integer 2-12 inclusive.
     * @throws ServerBoardException Thrown when diceNum is outside the range 0-12.
     */
    public void setDiceNum(int diceNum) throws ServerBoardException {
        if(diceNum < 0 || diceNum > 12) throw new ServerBoardException("diceNum param outside the range 0-12");
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        ServerHexTile other = (ServerHexTile)obj;
        if(diceNum != other.diceNum)
            return false;
        if(type != other.type)
            return false;
        return true;
    }
}
