package shared.definitions;

public enum HexType
{
	
	WOOD, BRICK, SHEEP, WHEAT, ORE, DESERT, WATER;

    public static HexType convert(String type) {

    	if(type == null)
    		return DESERT;
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
                return DESERT;
        }

    }

}

