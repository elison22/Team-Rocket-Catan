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
            case "w":
                return West;
            case "nw":
            case "northwest":
            case "north west":
                return NorthWest;
            case "ne":
            case "northeast":
            case "north east":
                return NorthEast;
            case "e":
            case "east":
                return East;
            case "se":
            case "southeast":
            case "south east":
                return SouthEast;
            case "sw":
            case "southwest":
            case "south west":
                return SouthWest;
            default:
                return null;
        }
    }
    
    public static String acronym(VertexDirection dir) {
    	switch (dir) {
    		case West:
    			return "W";
    		case NorthWest:
    			return "NW";
    		case NorthEast:
    			return "NE";
    		case East:
    			return "E";
    		case SouthEast:
    			return "SE";
    		case SouthWest:
    			return "SW";
    		default:
    			return null;
    	}
    }

}

