package serializer.json;

public class MessageList
{

	public MessageList(MessageLine[] lines)
	{
		this.lines = lines;
	}

	private MessageLine[]	lines;

	public MessageLine[] getLines()
	{
		return lines;
	}

}
