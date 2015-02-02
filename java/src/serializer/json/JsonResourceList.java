package serializer.json;

public class JsonResourceList
{

	public JsonResourceList(int brick, int ore, int sheep, int wheat, int wood)
	{
		this.brick = brick;
		this.ore = ore;
		this.sheep = sheep;
		this.wheat = wheat;
		this.wood = wood;
	}

	private int	brick;
	private int	ore;
	private int	sheep;
	private int	wheat;
	private int	wood;

	public int getBrick()
	{
		return brick;
	}

	public int getOre()
	{
		return ore;
	}

	public int getSheep()
	{
		return sheep;
	}

	public int getWheat()
	{
		return wheat;
	}

	public int getWood()
	{
		return wood;
	}
}
