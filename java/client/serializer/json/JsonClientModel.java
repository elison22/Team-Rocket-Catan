package serializer.json;

public class JsonClientModel {

	public JsonClientModel(JsonResourceList bank, JsonMessageList chat, JsonMessageList log,
                           JsonMap map, JsonPlayer[] players, JsonTradeOffer tradeOffer, JsonDevCardList deck,
                           JsonTurnTracker turnTracker, int version, int winner, String name)
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
		this.deck = deck;
		this.name = name;
	}

	private JsonDevCardList 	deck;
	private JsonMap 			map;
	private JsonPlayer[]		players;
	private JsonMessageList 	log;
	private JsonMessageList 	chat;
	private JsonResourceList 	bank;
	private JsonTurnTracker 	turnTracker;
	private JsonTradeOffer 		tradeOffer;
	private int					version;
	private int					winner;
	private String				name;

	public JsonResourceList getBank()
	{
		return bank;
	}

	public JsonMessageList getChat()
	{
		return chat;
	}

	public JsonMessageList getLog()
	{
		return log;
	}

	public JsonMap getMap()
	{
		return map;
	}

	public JsonPlayer[] getPlayers()
	{
		return players;
	}

	public JsonTradeOffer getTradeOffer()
	{
		return tradeOffer;
	}

	public JsonTurnTracker getTurnTracker()
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

	public JsonDevCardList getDevCards()
	{
		return deck;
	}
	
	public String getName() {
		return name;
	}

}
