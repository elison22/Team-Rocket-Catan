package shared.locations;

public enum EdgeDirection
{
	
	NorthWest, North, NorthEast, SouthEast, South, SouthWest;
	
	private EdgeDirection opposite;
	
	static
	{
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
	}
	
	public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}

    public static EdgeDirection convert(String type) {

        switch (type.toLowerCase()) {
            case "northwest":
            case "north west":
                return NorthWest;
            case "north":
                return North;
            case "northeast":
            case "north east":
                return NorthEast;
            case "southeast":
            case "south east":
                return SouthEast;
            case "south":
                return South;
            case "southwest":
            case "south west":
                return SouthWest;
            default:
                return null;
        }
    }

}

