package serializer.json;

public class JsonHex
{

	public JsonHex(JsonHexLocation location)
	{
		this.location = location;
		resource = "desert";
		number = 0;
	}

	public JsonHex(JsonHexLocation location, String resource, int number)
	{
		this.location = location;
		this.resource = resource;
		this.number = number;
	}

	private JsonHexLocation location;
	private String		resource;
	private int			number;

	public JsonHexLocation getLocation()
	{
		return location;
	}

	public String getResource()
	{
		return resource;
	}

	public int getNumber()
	{
		return number;
	}
}
