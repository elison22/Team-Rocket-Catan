package model.board.construction;

import model.board.BoardException;
import shared.locations.VertexDirection;

/**
 * Created by brandt on 1/22/15.
 */
public abstract class Building {

    int owner;
    VertexDirection position;

    protected Building(int owner, VertexDirection position) throws BoardException {
        setOwner(owner);
        this.position = position;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) throws BoardException {
        if (owner < 0 || owner > 3) throw new BoardException("Owner cannot be outside range 0 - 3");
        this.owner = owner;
    }

    public VertexDirection getPosition() {
        return position;
    }

    public void setPosition(VertexDirection position) {
        this.position = position;
    }

    public abstract int getVpValue();

    public abstract boolean isSettlement();
}
