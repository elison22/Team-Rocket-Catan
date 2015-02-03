package shared.locations;

public enum VertexDirection
{
	West, NorthWest, NorthEast, East, SouthEast, SouthWest;
	
	private VertexDirection opposite;
	
	static
	{
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;
	}
	
	public VertexDirection getOppositeDirection()
	{
		return opposite;
	}

    public static VertexDirection convert(String type) {

        switch (type.toLowerCase()) {
            case "west":
                return West;
            case "northwest":
            case "north west":
                return NorthWest;
            case "northeast":
            case "north east":
                return NorthEast;
            case "east":
                return East;
            case "southeast":
            case "south east":
                return SouthEast;
            case "southwest":
            case "south west":
                return SouthWest;
            default:
                return null;
        }
    }

}

