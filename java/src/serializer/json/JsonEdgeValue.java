package serializer.json;

public class JsonEdgeValue
{

	public JsonEdgeValue(int owner, JsonEdgeLocation location)
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
