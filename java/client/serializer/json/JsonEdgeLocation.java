package serializer.json;

public class JsonEdgeLocation
{

	public JsonEdgeLocation(int x, int y, String direction)
	{
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	private String	direction;
	private int		x;
	private int		y;
	
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public String getDirection()
	{
		return direction;
	}
}
