package serializer.json;

public class JsonTurnTracker
{

	public JsonTurnTracker(int currentTurn, String status)
	{
		this.currentTurn = currentTurn;
		this.status = status;
	}
	public JsonTurnTracker(int currentTurn, String status, int largestArmy)
	{
		this.currentTurn = currentTurn;
		this.status = status;
		this.largestArmy = largestArmy;
	}
	public JsonTurnTracker(int currentTurn, String status, int longestRoad,
                           int largestArmy)
	{
		this.currentTurn = currentTurn;
		this.status = status;
		this.longestRoad = longestRoad;
		this.largestArmy = largestArmy;
	}

	private int		currentTurn;
	private String	status;
	private int		longestRoad;
	private int		largestArmy;

	public int getCurrentTurn()
	{
		return currentTurn;
	}
	public String getStatus()
	{
		return status;
	}
	public int getLongestRoad()
	{
		return longestRoad;
	}
	public int getLargestArmy()
	{
		return largestArmy;
	}

}
