package serializer.json;

/**
 * Created by brandt on 2/2/15.
 */
public class JsonRoad {

    public JsonRoad(int owner, JsonEdgeLocation location)
    {
        this.owner = owner;
        this.location = location;
    }

    private int				owner;
    private JsonEdgeLocation location;

    public int getOwner()
    {
        return owner;
    }

    public JsonEdgeLocation getLocation()
    {
        return location;
    }

}
