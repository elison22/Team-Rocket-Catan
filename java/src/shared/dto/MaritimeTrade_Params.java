package shared.dto;

public class MaritimeTrade_Params {
	
	private String type = "maritimeTrade";
	private int playerIndex;
	private int ratio;
	private String inputResource;
	private String outputResource;

	public MaritimeTrade_Params() {}

	public MaritimeTrade_Params(int playerIndex, int ratio,
			String inputResource, String outputResource) {
		super();
		setPlayerIndex(playerIndex);
		setRatio(ratio);
		setInputResource(inputResource);
		setOutputResource(outputResource);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public String getInputResource() {
		return inputResource;
	}

	public void setInputResource(String inputResource) {
		this.inputResource = capitalizeResource(inputResource);
	}

	public String getOutputResource() {
		return outputResource;
	}

	public void setOutputResource(String outputResource) {
		this.outputResource = capitalizeResource(outputResource);
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
}
