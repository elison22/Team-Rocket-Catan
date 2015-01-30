package serializer.json;

public class EdgeValue
{

	public EdgeValue(int owner, EdgeLocation location)
	{
		this.owner = owner;
		this.location = location;
	}

	private int				owner;
	private EdgeLocation	location;

	public int getOwner()
	{
		return owner;
	}

	public EdgeLocation getLocation()
	{
		return location;
	}
}
