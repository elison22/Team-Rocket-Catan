package serializer.json;

public class JsonMessageList
{

	public JsonMessageList(JsonMessageLine[] lines)
	{
		this.lines = lines;
	}

	private JsonMessageLine[]	lines;

	public JsonMessageLine[] getLines()
	{
		return lines;
	}

}
