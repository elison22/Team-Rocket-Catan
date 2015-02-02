package serializer.json;

public class JsonMessageLine
{

	public JsonMessageLine(String message, String source)
	{
		this.message = message;
		this.source = source;
	}

	private String	message;
	private String	source;

	public String getMessage()
	{
		return message;
	}

	public String getSource()
	{
		return source;
	}
}
