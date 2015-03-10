package serializer.json;

public class JsonPort
{

	public JsonPort(String resource, JsonHexLocation location, String direction,
                    int ratio)
	{
		this.resource = resource;
		this.location = location;
		this.direction = direction;
		this.ratio = ratio;
	}

	private String		resource;
	private JsonHexLocation location;
	private String		direction;
	private int			ratio;

	public String getResource()
	{
		return resource;
	}

	public JsonHexLocation getLocation()
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
