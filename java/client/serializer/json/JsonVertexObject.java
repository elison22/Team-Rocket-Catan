package serializer.json;

public class JsonVertexObject
{

	public JsonVertexObject(int owner, JsonVertexLocation location)
	{
		this.owner = owner;
		this.location = location;
	}

	private int				owner;
	private JsonVertexLocation location;

	public int getOwner()
	{
		return owner;
	}

	public JsonVertexLocation getLocation()
	{
		return location;
	}
}
