package shared.dto;

import com.google.gson.Gson;

public class Monopoly_Params {
	
	private String type = "Monopoly";
	private String resource;
	private int playerIndex;

	public Monopoly_Params() {}
	
	public Monopoly_Params(String resource, int playerIndex) {
		setResource(resource);
		setPlayerIndex(playerIndex);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = capitalizeResource(resource);
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	private String capitalizeResource(String resource) {
		switch (resource.toLowerCase())
        {
            case "wood":
                return "Wood";
            case "brick":
                return "Brick";
            case "sheep":
                return "Sheep";
            case "wheat":
                return "Wheat";
            case "ore":
                return "Ore";
            default:
                return null;
        }
	}
	
	public static void main(String[] args) {
		Monopoly_Params p = new Monopoly_Params("Brick", 0);
		Gson gson = new Gson();
		System.out.println(gson.toJson(p));
	}

}
