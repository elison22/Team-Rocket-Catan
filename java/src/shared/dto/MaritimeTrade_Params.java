package shared.dto;

import com.google.gson.Gson;

public class MaritimeTrade_Params {
	
	private String type = "maritimeTrade";
	private int playerIndex;
	private int ratio;
	private String inputResource;	// resource player is giving
	private String outputResource;	// resource player is receiving

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
		this.inputResource = inputResource.toLowerCase();
	}

	public String getOutputResource() {
		return outputResource;
	}

	public void setOutputResource(String outputResource) {
		this.outputResource = outputResource.toLowerCase();
	}
	
	public static void main(String[] args) {
		MaritimeTrade_Params p = new MaritimeTrade_Params(0, 4, "ORE", "BRICK");
		Gson gson = new Gson();
		System.out.println(gson.toJson(p));
	}
}
