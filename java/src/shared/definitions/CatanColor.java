package shared.definitions;

import java.awt.Color;

public enum CatanColor
{
	RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BROWN;
	
	private Color color;
	
	static
	{
		RED.color = new Color(227, 66, 52);
		ORANGE.color = new Color(255, 165, 0);
		YELLOW.color = new Color(253, 224, 105);
		BLUE.color = new Color(111, 183, 246);
		GREEN.color = new Color(109, 192, 102);
		PURPLE.color = new Color(157, 140, 212);
		PUCE.color = new Color(204, 136, 153);
		WHITE.color = new Color(223, 223, 223);
		BROWN.color = new Color(161, 143, 112);
	}
	
	private String name;
	
	static
	{
		RED.name = "red";
		ORANGE.name = "orange";
		YELLOW.name = "yellow";
		BLUE.name = "blue";
		GREEN.name = "green";
		PURPLE.name = "purple";
		PUCE.name = "puce";
		WHITE.name = "white";
		BROWN.name = "brown";
	}
	
	public static CatanColor convert(String type){
		if (type == null)
			return null;

        switch (type.toLowerCase())
        {
            case "red":
                return RED;
            case "orange":
                return ORANGE;
            case "yellow":
                return YELLOW;
            case "blue":
                return BLUE;
            case "green":
                return GREEN;
            case "purple":
            	return PURPLE;
            case "puce":
            	return PUCE;
            case "white":
            	return WHITE;
            case "brown":
            	return BROWN;
            default:
                return null;
        }
    }
	
	public Color getJavaColor()
	{
		return color;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

