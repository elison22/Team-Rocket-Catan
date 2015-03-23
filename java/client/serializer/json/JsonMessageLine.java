package serializer.json;

public class JsonMessageLine
{

	public JsonMessageLine(String message, String source)
	{
		this.message = message;
		this.source = source;
	}

	private String	source;
	private String	message;

	public String getMessage()
	{
		return message;
	}

	public String getSource()
	{
		return source;
	}
}
