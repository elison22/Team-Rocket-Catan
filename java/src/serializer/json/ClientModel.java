package serializer.json;

public class ClientModel {

	public ClientModel(ResourceList bank, MessageList chat, MessageList log,
			Map map, Player[] players, TradeOffer tradeOffer, DevCardList devCards,
			TurnTracker turnTracker, int version, int winner)
	{
		this.bank = bank;
		this.chat = chat;
		this.log = log;
		this.map = map;
		this.players = players;
		this.tradeOffer = tradeOffer;
		this.turnTracker = turnTracker;
		this.version = version;
		this.winner = winner;
		this.devCards = devCards;
	}

	private ResourceList	bank;
	private MessageList		chat;
	private MessageList		log;
	private DevCardList		devCards;
	private Map				map;
	private Player[]		players;
	private TradeOffer		tradeOffer;
	private TurnTracker		turnTracker;
	private int				version;
	private int				winner;

	public ResourceList getBank()
	{
		return bank;
	}

	public MessageList getChat()
	{
		return chat;
	}

	public MessageList getLog()
	{
		return log;
	}

	public Map getMap()
	{
		return map;
	}

	public Player[] getPlayers()
	{
		return players;
	}

	public TradeOffer getTradeOffer()
	{
		return tradeOffer;
	}

	public TurnTracker getTurnTracker()
	{
		return turnTracker;
	}

	public int getVersion()
	{
		return version;
	}

	public int getWinner()
	{
		return winner;
	}

	public DevCardList getDevCards()
	{
		return devCards;
	}

}
