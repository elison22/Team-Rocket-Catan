package shared.definitions;

public enum PieceType
{
	
	ROAD, SETTLEMENT, CITY, ROBBER;

    public static PieceType convert(String type){

        switch (type.toLowerCase())
        {
            case "road":
                return ROAD;
            case "settlement":
                return SETTLEMENT;
            case "city":
                return CITY;
            case "robber":
                return ROBBER;
            default:
                return null;
        }

    }

}

