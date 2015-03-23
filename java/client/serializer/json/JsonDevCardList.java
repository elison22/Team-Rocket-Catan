package serializer.json;

public class JsonDevCardList
{
	public JsonDevCardList(int monopoly, int monument, int roadBuilding,
                           int soldier, int yearOfPlenty)
	{
		this.monopoly = monopoly;
		this.monument = monument;
		this.roadBuilding = roadBuilding;
		this.soldier = soldier;
		this.yearOfPlenty = yearOfPlenty;
	}

	private int	yearOfPlenty;
	private int	monopoly;
	private int	soldier;
	private int	roadBuilding;
	private int	monument;

	public int getMonopoly()
	{
		return monopoly;
	}
	public int getMonument()
	{
		return monument;
	}
	public int getRoadBuilding()
	{
		return roadBuilding;
	}
	public int getSoldier()
	{
		return soldier;
	}
	public int getYearOfPlenty()
	{
		return yearOfPlenty;
	}
}
