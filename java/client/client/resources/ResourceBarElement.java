package client.resources;

public enum ResourceBarElement
{
	WOOD, BRICK, SHEEP, WHEAT, ORE, ROAD, SETTLEMENT, CITY, BUY_CARD, PLAY_CARD, SOLDIERS;
	
	public static ResourceBarElement convert(String type){
		if (type == null)
			return null;

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
}

