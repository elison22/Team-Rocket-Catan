package serializer.json;

public class Port
{

	public Port(String resource, HexLocation location, String direction,
			int ratio)
	{
		this.resource = resource;
		this.location = location;
		this.direction = direction;
		this.ratio = ratio;
	}

	private String		resource;
	private HexLocation	location;
	private String		direction;
	private int			ratio;

	public String getResource()
	{
		return resource;
	}

	public HexLocation getLocation()
	{
		return location;
	}

	public String getDirection()
	{
		return direction;
	}

	public int getRatio()
	{
		return ratio;
	}

}
