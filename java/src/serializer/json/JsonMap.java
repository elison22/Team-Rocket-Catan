package serializer.json;

public class JsonMap
{

	public JsonMap(JsonHex[] hexes, JsonPort[] ports, JsonEdgeLocation[] roads,
                   JsonVertexObject[] settlements, JsonVertexObject[] cities, int radius,
                   JsonHexLocation robber)
	{
		this.hexes = hexes;
		this.ports = ports;
		this.roads = roads;
		this.settlements = settlements;
		this.cities = cities;
		this.radius = radius;
		this.robber = robber;
	}

	private JsonHex[]			hexes;
	private JsonPort[]			ports;
	private JsonEdgeLocation[]	roads;
	private JsonVertexObject[]	settlements;
	private JsonVertexObject[]	cities;
	private int				radius;
	private JsonHexLocation robber;

	public JsonHex[] getHexes()
	{
		return hexes;
	}

	public JsonPort[] getPorts()
	{
		return ports;
	}

	public JsonEdgeLocation[] getRoads()
	{
		return roads;
	}

	public JsonVertexObject[] getSettlements()
	{
		return settlements;
	}

	public JsonVertexObject[] getCities()
	{
		return cities;
	}

	public int getRadius()
	{
		return radius;
	}

	public JsonHexLocation getRobber()
	{
		return robber;
	}
}
