package serializer.json;

public class JsonPlayer
{
	public JsonPlayer(int cities, String color, boolean discarded, int monuments,
                      String name, JsonDevCardList newDevCards, JsonDevCardList oldDevCards,
                      int playerIndex, boolean playedDevCard, int playerID,
                      JsonResourceList resources, int roads, int settlements, int soldiers,
                      int victoryPoints)
	{
		this.cities = cities;
		this.color = color;
		this.discarded = discarded;
		this.monuments = monuments;
		this.name = name;
		this.newDevCards = newDevCards;
		this.oldDevCards = oldDevCards;
		this.playerIndex = playerIndex;
		this.playedDevCard = playedDevCard;
		this.playerID = playerID;
		this.resources = resources;
		this.roads = roads;
		this.settlements = settlements;
		this.soldiers = soldiers;
		this.victoryPoints = victoryPoints;
	}

	private int				cities;
	private String			color;
	private boolean			discarded;
	private int				monuments;
	private String			name;
	private JsonDevCardList newDevCards;
	private JsonDevCardList oldDevCards;
	private int				playerIndex;
	private boolean			playedDevCard;
	private int				playerID;
	private JsonResourceList resources;
	private int				roads;
	private int				settlements;
	private int				soldiers;
	private int				victoryPoints;

	public int getCities()
	{
		return cities;
	}
	public String getColor()
	{
		return color;
	}
	public boolean isDiscarded()
	{
		return discarded;
	}
	public int getMonuments()
	{
		return monuments;
	}
	public String getName()
	{
		return name;
	}
	public JsonDevCardList getNewDevCards()
	{
		return newDevCards;
	}
	public JsonDevCardList getOldDevCards()
	{
		return oldDevCards;
	}
	public int getPlayerIndex()
	{
		return playerIndex;
	}
	public boolean isPlayedDevCard()
	{
		return playedDevCard;
	}
	public int getPlayerID()
	{
		return playerID;
	}
	public JsonResourceList getResources()
	{
		return resources;
	}
	public int getRoads()
	{
		return roads;
	}
	public int getSettlements()
	{
		return settlements;
	}
	public int getSoldiers()
	{
		return soldiers;
	}
	public int getVictoryPoints()
	{
		return victoryPoints;
	}
}
