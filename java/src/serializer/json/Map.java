package serializer.json;

public class Map
{

	public Map(Hex[] hexes, Port[] ports, EdgeLocation[] roads,
			VertexObject[] settlements, VertexObject[] cities, int radius,
			HexLocation robber)
	{
		this.hexes = hexes;
		this.ports = ports;
		this.roads = roads;
		this.settlements = settlements;
		this.cities = cities;
		this.radius = radius;
		this.robber = robber;
	}

	private Hex[]			hexes;
	private Port[]			ports;
	private EdgeLocation[]	roads;
	private VertexObject[]	settlements;
	private VertexObject[]	cities;
	private int				radius;
	private HexLocation		robber;

	public Hex[] getHexes()
	{
		return hexes;
	}

	public Port[] getPorts()
	{
		return ports;
	}

	public EdgeLocation[] getRoads()
	{
		return roads;
	}

	public VertexObject[] getSettlements()
	{
		return settlements;
	}

	public VertexObject[] getCities()
	{
		return cities;
	}

	public int getRadius()
	{
		return radius;
	}

	public HexLocation getRobber()
	{
		return robber;
	}
}
