package shared.definitions;

public enum PortType
{
	
	WOOD, BRICK, SHEEP, WHEAT, ORE, THREE_FOR_ONE;
    
    public static PortType convert(String type){

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
            case "three for one":
            case "threeforone":
            case "three-for-one":
            case "three_for_one":
                return THREE_FOR_ONE;
            default:
                return null;
        }
    }
    
    public static PortType convertResourceType(ResourceType t) {
    	
    	switch (t) {
    	case BRICK:
    		return BRICK;
    	case SHEEP:
    		return SHEEP;
    	case ORE:
    		return ORE;
    	case WHEAT:
    		return WHEAT;
    	case WOOD:
    		return WOOD;
    	default:
    		return null;
    	}
    }
}

