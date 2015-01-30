package serializer.json;

public class VertexLocation
{

	public VertexLocation(int x, int y, String direction)
	{
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	private int		x;
	private int		y;
	private String	direction;

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
