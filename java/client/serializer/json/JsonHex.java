package serializer.json;

public class JsonHex
{

	public JsonHex(JsonHexLocation location)
	{
		this.location = location;
		resource = "desert";
		number = 0;
	}

	public JsonHex(JsonHexLocation location, String resource, Integer number)
	{
		this.location = location;
		this.resource = resource;
		this.number = number;
	}

	private String		resource;
	private JsonHexLocation location;
	private Integer			number;

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
		return number == null ? 0 : number;
	}
}
