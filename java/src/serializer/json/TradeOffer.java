package serializer.json;

public class TradeOffer
{

	public TradeOffer(int sender, int receiver, ResourceList offer)
	{
		this.sender = sender;
		this.receiver = receiver;
		this.offer = offer;
	}

	private int				sender;
	private int				receiver;
	private ResourceList	offer;

	public int getSender()
	{
		return sender;
	}
	public int getReceiver()
	{
		return receiver;
	}
	public ResourceList getOffer()
	{
		return offer;
	}
}
