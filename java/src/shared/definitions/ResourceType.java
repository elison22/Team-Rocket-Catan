package shared.definitions;

public enum ResourceType
{
	WOOD, BRICK, SHEEP, WHEAT, ORE;
	
	private static String name;

    public static ResourceType convert(String type){
    	name = type;

        switch (type.toLowerCase())
        {
            case "wood":
                return WOOD;
            case "brick":
                return BRICK;
            case "sheep":
                return SHEEP;
            case "wheat":
                return WHEAT;
            case "ore":
                return ORE;
            default:
                return null;
        }

    }
    
    @Override
    public String toString() {
    	return name;
    }

}

