package model.board.construction;

import model.board.BoardException;
import shared.locations.VertexDirection;

/**
 * Created by brandt on 1/22/15.
 */
public class Settlement extends Building{


    public Settlement(int owner, VertexDirection position) throws BoardException {
        super(owner, position);
    }


    public City upgrade() {
        City toReturn = null;
        try {
            toReturn = new City(owner, this.position);
        } catch (BoardException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    @Override
    public int getVpValue() {
        return 1;
    }

    @Override
    public boolean isSettlement() {
        return true;
    }
}
