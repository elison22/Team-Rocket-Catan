package model.board.construction;

import model.board.BoardException;
import shared.locations.VertexLocation;

/**
 * Created by brandt on 1/22/15.
 * <p>
 * This class contains the data and methods for a Catan city. Many of the fields and functions
 * are shared by the Building super class, but there are several methods that differentiate this
 * class from the Settlement class, namely the implementations of getVpValue and isSettlement.
 * </p>
 * City objects are exclusively stored and referenced from the HexTile class.
 */
public class City extends Building{

    /**
     * Calls the Building constructor, which initializes owner and location.
     * @param owner The index of the City's owner. Must be in the range 0-3 inclusive.
     * @param location The VertexLocation of this City.
     * @throws BoardException Thrown if the owner parameter is outside the range 0-3.
     */
    public City(int owner, VertexLocation location) throws BoardException {
        super(owner, location);
    }

    /**
     * A City is worth two points, so this always returns 2.
     * @return 2.
     */
    @Override
    public int getVpValue() {
        return 2;
    }

    /**
     * This is not a Settlement object, so it always returns false.
     * @return false.
     */
    @Override
    public boolean isSettlement() {
        return false;
    }

}
