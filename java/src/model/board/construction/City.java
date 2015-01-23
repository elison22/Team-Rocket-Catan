package model.board.construction;

import model.board.BoardException;
import shared.locations.VertexDirection;

/**
 * Created by brandt on 1/22/15.
 */
public class City extends Building{

    public City(int owner, VertexDirection position) throws BoardException {
        super(owner, position);
    }

    @Override
    public int getVpValue() {
        return 2;
    }

    @Override
    public boolean isSettlement() {
        return false;
    }

}
