package model.board.mapunits;

import model.board.BoardException;
import shared.locations.EdgeLocation;

/**
 * Created by brandt on 1/17/15.
 * <p>
 * Represents an edge in Catan, which may or may not contain a road. The presence
 * of a road is indicated by the field 'owner', which contains the index 0-3 of the
 * player who owns the road or -1 if no player has built a road on this space.
 * </p>
 */
public class Edge {

    /** Keeps track of road ownership. -1 means no road, 0-3 means the index of the road owner. */
    private int owner = -1;

    /** The location of this Edge on the game board. */
    private EdgeLocation location;

    /**
     * Default constructor with owner initialized to -1. The constructor requires
     * a non-null EdgeLocation for the location parameter.
     * @param location The non-null location of this Edge object.
     * @throws BoardException When the location parameter is null.
     */
    public Edge(EdgeLocation location) throws BoardException{
        if(location == null) throw new BoardException("EdgeLocation cannot be null in Edge constructor");
        this.location = location;
    }

    /**
     * Create a new Edge object with the field owner set to the index of the player who has
     * build a road on this edge. Values may be integers from -1 to 3 inclusive.
     * @param location The location of this Edge
     * @param owner The value to which the owner field is initialized.
     * @throws BoardException Thrown when the owner parameter is outside the bounds -1 to 3 or
     * when the location parameter is null.
     */
    public Edge(EdgeLocation location, int owner) throws BoardException{
        this(location);
        if (owner < -1 || owner > 3) throw new BoardException("Player index out of range in Edge constructor");
        this.owner = owner;
    }

}
