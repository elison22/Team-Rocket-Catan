package serializer.json;

public class VertexObject
{

	public VertexObject(int owner, VertexLocation location)
	{
		this.owner = owner;
		this.location = location;
	}

	private int				owner;
	private VertexLocation	location;

	public int getOwner()
	{
		return owner;
	}

	public VertexLocation getLocation()
	{
		return location;
	}
}
