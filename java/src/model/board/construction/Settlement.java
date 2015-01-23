package model.board.construction;

import model.board.BoardException;
import shared.locations.VertexLocation;

/**
 * Created by brandt on 1/22/15.
 * <p>
 * This class contains the data and methods for a Catan settlement. Many of the fields and functions
 * are shared by the Building super class, but there are several methods that differentiate this
 * class from the City class, namely upgrade and the implementations of getVpValue and isSettlement.
 * </p>
 * Settlement objects are exclusively stored and referenced from the HexTile class.
 */
public class Settlement extends Building{

    /**
     * Calls the Building constructor, which initializes owner and location.
     * @param owner The index of the Settlement's owner. Must be in the range 0-3 inclusive.
     * @param location The VertexLocation of this Settlement.
     * @throws BoardException Thrown if the owner parameter is outside the range 0-3.
     */
    public Settlement(int owner, VertexLocation location) throws BoardException {
        super(owner, location);
    }

    /**
     * Creates a City object using the owner and location values of this
     * Settlement. Can be used to upgrade an existing Settlement to a City.
     * @return A City with the same owner and location as this Settlement.
     */
    public Building upgrade() {
        City toReturn = null;
        try {
            toReturn = new City(owner, this.location);
        } catch (BoardException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    /**
     * A Settlement is worth one point, so this always returns 1.
     * @return 1.
     */
    @Override
    public int getVpValue() {
        return 1;
    }

    /**
     * This is a Settlement object, so it always returns true.
     * @return true.
     */
    @Override
    public boolean isSettlement() {
        return true;
    }
}
