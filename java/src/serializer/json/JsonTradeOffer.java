package serializer.json;

public class JsonTradeOffer
{

	public JsonTradeOffer(int sender, int receiver, JsonResourceList offer)
	{
		this.sender = sender;
		this.receiver = receiver;
		this.offer = offer;
	}

	private int				sender;
	private int				receiver;
	private JsonResourceList offer;

	public int getSender()
	{
		return sender;
	}
	public int getReceiver()
	{
		return receiver;
	}
	public JsonResourceList getOffer()
	{
		return offer;
	}
}
